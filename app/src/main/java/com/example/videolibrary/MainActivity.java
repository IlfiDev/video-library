package com.example.videolibrary;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.videolibrary.databinding.ActivityMainBinding;
import com.example.videolibrary.view.LoginFragment;
import com.example.videolibrary.viewmodel.LoginViewModel;


public class MainActivity extends AppCompatActivity {
    private LoginFragment loginFragment = new LoginFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new LoginFragment());


    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


}