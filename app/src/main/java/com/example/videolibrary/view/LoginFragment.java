package com.example.videolibrary.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.videolibrary.MainActivity;
import com.example.videolibrary.R;
import com.example.videolibrary.databinding.FragmentLoginBinding;
import com.example.videolibrary.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
//    private LoginViewModel model;

    public LoginFragment(){
        super(R.layout.fragment_login);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
       return binding.getRoot();
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

    }

    public void switchFragment(){
        ((MainActivity) getActivity()).replaceFragment(1);
    }

}
