package com.example.videolibrary.view;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.videolibrary.MainActivity;
import com.example.videolibrary.R;
import com.example.videolibrary.model.Video;
import com.example.videolibrary.view.Adapters.VideosAdapter;
import com.example.videolibrary.viewmodel.VideoDataBase;
import com.example.videolibrary.viewmodel.VideosViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VideosFragment extends Fragment {

    ListView listView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,1,1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        // ActivityResultLauncher, as an instance variable.
        List<Video> videos = MainActivity.getAllVideos();
        listView = (ListView) view.findViewById(R.id.videos_listview2);
        VideosAdapter adapter = new VideosAdapter(getContext(), R.layout.video_layout, videos);
        Log.e("Suss", MainActivity.getAllVideos().toString());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(videos.get(i).getUri(), "video/*");
                startActivity(Intent.createChooser(intent, "Complete action using"));
            }
        });


        return view;
    }
}
