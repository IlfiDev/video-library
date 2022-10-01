package com.example.videolibrary.view;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.videolibrary.R;
import com.example.videolibrary.viewmodel.VideosViewModel;

import java.util.ArrayList;

public class VideosFragment extends Fragment {

    VideosViewModel model;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,1,1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ActivityResultLauncher, as an instance variable.
        ActivityResultLauncher<String> requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {

                    } else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_MEDIA_LOCATION}, 1);
                    }
                });

        model = new ViewModelProvider(this).get(VideosViewModel.class);
        model.updateVideoList();
        model.getVideos().observe(this.getActivity(), videos -> {
            for(int i = 0; i < videos.size(); i++){
                Log.i("ABOBA", videos.get(i).getName());

            }
        });

        return inflater.inflate(R.layout.fragment_videos, container, false);
    }
    public void updateVideoList(){


        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.VideoColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri,
                projection,
                null,
                null,
                null);
        ArrayList<String> pathArrList = new ArrayList<>();
        if(cursor != null){
            while (cursor.moveToNext()){
                pathArrList.add(cursor.getString(0));
            }
            cursor.close();
        }
        Log.e("all path", pathArrList.toString());
    }
}
