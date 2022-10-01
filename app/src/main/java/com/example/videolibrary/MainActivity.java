package com.example.videolibrary;

import android.Manifest;
import android.app.FragmentManager;
import android.content.ContentUris;
import android.database.Cursor;
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
import com.example.videolibrary.model.Video;
import com.example.videolibrary.view.LocalFoldersFragment;
import com.example.videolibrary.view.LoginFragment;
import com.example.videolibrary.view.SharedFoldersFragment;
import com.example.videolibrary.view.VideosFragment;
import com.example.videolibrary.viewmodel.FragmentViewModel;
import com.example.videolibrary.viewmodel.LoginViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    public LoginFragment loginFragment = new LoginFragment();
    public VideosFragment videosFragment = new VideosFragment();
    public LocalFoldersFragment localFoldersFragment = new LocalFoldersFragment();
    public SharedFoldersFragment sharedFoldersFragment = new SharedFoldersFragment();
    public List<Object> fragmentsList = new ArrayList<Object>();
    private BottomNavigationView bottomBar;
    private FragmentViewModel model;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_MEDIA_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        updateVideoList();
        fragmentsList.add(loginFragment);
        fragmentsList.add(videosFragment);
        fragmentsList.add(localFoldersFragment);
        fragmentsList.add(sharedFoldersFragment);
        replaceFragment(0);

        //replaceFragment(1);
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

    public void updateVideoList() {
        List<Video> videoList = new ArrayList<Video>();

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
                MediaStore.Video.Media.SIZE
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

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                int size = cursor.getInt(sizeColumn);

                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

                // Stores column values and the contentUri in a local object
                // that represents the media file.
                videoList.add(new Video(contentUri, name, duration, size));
            }
            for(Video vid : videoList){
                Log.e("ABOBA", vid.getName());
            }

        }
    }
}