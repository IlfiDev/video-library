package com.example.videolibrary.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.videolibrary.MainActivity;
import com.example.videolibrary.R;
import com.example.videolibrary.model.Folder;
import com.example.videolibrary.model.Video;
import com.example.videolibrary.view.Adapters.FolderActivityAdapter;

import java.util.List;

public class FolderActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_activity);
        ListView listView = findViewById(R.id.folder_activity_viewModel);
        int folderId = getIntent().getIntExtra("folderIndex", -1);
        List<Video> videoList = MainActivity.getFolders().get(folderId).getVideos();
        FolderActivityAdapter adapter = new FolderActivityAdapter(this,R.layout.video_layout, videoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(videoList.get(i).getUri(), "video/*");
                startActivity(Intent.createChooser(intent, "Complete action using"));
            }
        });
    }


}
