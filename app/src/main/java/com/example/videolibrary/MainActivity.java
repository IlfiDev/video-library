package com.example.videolibrary;

import android.Manifest;
import android.app.FragmentManager;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.videolibrary.databinding.ActivityMainBinding;
import com.example.videolibrary.model.Folder;
import com.example.videolibrary.model.Video;
import com.example.videolibrary.view.LocalFoldersFragment;
import com.example.videolibrary.view.LoginFragment;
import com.example.videolibrary.view.SharedFoldersFragment;
import com.example.videolibrary.view.VideosFragment;
import com.example.videolibrary.viewmodel.FragmentViewModel;
import com.example.videolibrary.viewmodel.LoginViewModel;
import com.example.videolibrary.viewmodel.VideoDataBase;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    public LoginFragment loginFragment = new LoginFragment();
    public VideosFragment videosFragment = new VideosFragment();
    public LocalFoldersFragment localFoldersFragment = new LocalFoldersFragment();
    public SharedFoldersFragment sharedFoldersFragment = new SharedFoldersFragment();
    public List<Object> fragmentsList = new ArrayList<Object>();
    private BottomNavigationView bottomBar;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_MEDIA_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        fragmentsList.add(loginFragment);
        fragmentsList.add(videosFragment);
        fragmentsList.add(localFoldersFragment);
        fragmentsList.add(sharedFoldersFragment);
        replaceFragment(0);
        updateVideoList();
        bottomBar = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_videos:
                        Toast.makeText(MainActivity.this, "Videos", Toast.LENGTH_SHORT).show();
                        replaceFragment(1);
                        break;
                    case R.id.tab_local_folders:
                        Toast.makeText(MainActivity.this, "Local", Toast.LENGTH_SHORT).show();
                        replaceFragment(2);

                        break;
                    case R.id.tab_shared_folders:
                        Toast.makeText(MainActivity.this, "Shared", Toast.LENGTH_SHORT).show();
                        replaceFragment(3);

                        break;

                }
                return true;
            }
        });



    }
    public void replaceFragment(int index){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, (Fragment) fragmentsList.get(index));
        fragmentTransaction.commit();
    }
    public static List<Folder> folders = new ArrayList<Folder>();

    public void updateVideoList(){

        Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = new String[]{
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.RELATIVE_PATH
        };
        String selection = MediaStore.Video.Media.DURATION +
                " >= ?";
        String[] selectionArgs = new String[]{
                String.valueOf(TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS))};
        String sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC";
        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            // Cache column indices.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            int durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
            int pathid = cursor.getColumnIndex(MediaStore.Video.Media.RELATIVE_PATH);

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                int size = cursor.getInt(sizeColumn);
                String path = cursor.getString(pathid);
                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);
                Bitmap thumbnail = MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(),
                        id,
                        MediaStore.Video.Thumbnails.MINI_KIND,
                        (BitmapFactory.Options) null);
                int folderIndex = findFolderWithName(path);
                if(folderIndex != -1){
                    folders.get(folderIndex).addVideo(new Video(contentUri, name, duration, size, thumbnail));
                }
                else{
                    Folder folder = new Folder(path);
                    folder.addVideo(new Video(contentUri, name, duration, size, thumbnail));
                    folders.add(folder);
                }
                //MediaStore.getMediaUri(getApplication().getApplicationContext(), contentUri).getEncodedPath();

                Log.e("AMOGUS", path);
            }
        }
    }
    public static List<Folder> getFolders(){
        return folders;
    }
    public static List<Video> getAllVideos(){
        List<Video> videos =  new ArrayList<Video>();
        for(Folder folder : folders){
            List<Video> videosInFolder = folder.getVideos();
            videos.addAll(videosInFolder);
        }
        return videos;
    }
    static int findFolderWithName(String name){
        for(int i = 0; i < folders.size(); i++){
            if (Objects.equals(folders.get(i).getName(), name)){
                return i;
            }
        }
        return -1;
    }

}