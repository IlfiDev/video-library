package com.example.videolibrary.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.videolibrary.MainActivity;
import com.example.videolibrary.R;
import com.example.videolibrary.model.Folder;
import com.example.videolibrary.model.Video;
import com.example.videolibrary.view.Adapters.FoldersFragmentAdapter;
import com.example.videolibrary.view.Adapters.VideosAdapter;

import java.util.List;


public class LocalFoldersFragment extends Fragment {

    public LocalFoldersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_folder_fragment, container, false);
        ListView listView = (ListView) view.findViewById(R.id.local_folders_listiew);
        FoldersFragmentAdapter adapter = new FoldersFragmentAdapter(getContext(), R.layout.video_layout, MainActivity.getFolders());
        Log.e("Suss", MainActivity.getAllVideos().toString());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), FolderActivity.class);
                Folder folder = MainActivity.getFolders().get(i);
                intent.putExtra("folderIndex", i);
                startActivity(intent);
            }
        });
        return view;
    }
}