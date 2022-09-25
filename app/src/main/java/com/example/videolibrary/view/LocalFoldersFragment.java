package com.example.videolibrary.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videolibrary.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocalFoldersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocalFoldersFragment extends Fragment {

    public LocalFoldersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocalFoldersFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static LocalFoldersFragment newInstance(String param1, String param2) {
//        LocalFoldersFragment fragment = new LocalFoldersFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_local_folders, container, false);
    }
}