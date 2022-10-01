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
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.videolibrary.R;
import com.example.videolibrary.view.Adapters.VideosAdapter;
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
        model.getVideos().observe(this.getActivity(), videos -> {
            Log.e("SUSSUS", "AMOGUS");
            VideosAdapter adapter = new VideosAdapter(getContext(), R.layout.video_layout, videos);
            ListView listView = (ListView) getActivity().findViewById(R.id.videos_listview);
            listView.setAdapter(adapter);

        });

        return inflater.inflate(R.layout.fragment_videos, container, false);
    }
}
