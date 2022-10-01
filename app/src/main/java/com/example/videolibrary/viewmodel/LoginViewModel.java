package com.example.videolibrary.viewmodel;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.videolibrary.model.User;


public class LoginViewModel extends BaseObservable {
    private User user;
    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";
    @Bindable
    private String toastMessage = null;

    private FragmentViewModel model = new FragmentViewModel();
    public String getToastMessage() {
        return toastMessage;
    }

    private MutableLiveData<Integer> currentName;

    public MutableLiveData<Integer> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<Integer>();
        }
        return currentName;
    }

// Rest of the ViewModel...

    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }


    public void setUserEmail(String email) {
        user.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserEmail() {
        return user.getEmail();
    }

    @Bindable
    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String password) {
        user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public LoginViewModel() {
        user = new User("","", "");
    }

    private LiveData<Integer> currentFragment;

    public LiveData<Integer> getCurrentIndex() {
        if (currentFragment == null) {
            currentFragment = new LiveData<Integer>() {
            };
        }
        return currentFragment;
    }

    public void onLoginClicked() {
        currentName.setValue(1);
        if (isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        return true;

    }
    public void onRegisterClicked(){
    }
}



