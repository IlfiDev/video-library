package com.example.videolibrary.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.videolibrary.R;

public class Register extends AppCompatActivity {
    EditText mail;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        mail = findViewById(R.id.inEmailReg);
        password = findViewById(R.id.inPasswordReg);
        Button button = findViewById(R.id.register_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAndQuit();
                finish();
            }
        });
    }

    public void SaveAndQuit() {
        Intent data = new Intent();
        data.putExtra("login", mail.getText().toString());
        data.putExtra("password", password.getText().toString());
        setResult(RESULT_FIRST_USER, data);
    }
}