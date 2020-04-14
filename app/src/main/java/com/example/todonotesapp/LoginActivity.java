package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText textFullName,textUserName;
    Button loginButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textFullName = findViewById(R.id.textFullName);
        textUserName= findViewById(R.id.textUserName);
        loginButton=findViewById(R.id.LoginButton);

        setUpSharedPreferences();

        View.OnClickListener action= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = textFullName.getText().toString();
                String UserName = textUserName.getText().toString();
              if(!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(UserName)) {
                  Intent intent = new Intent(LoginActivity.this, MyNotesActivity.class);
                  intent.putExtra(AppConstant.FULL_NAME, fullname);
                  startActivity(intent);
                  // login // save the login state
                  savedLoginStatus();
                  savefullName(fullname);
              }else{
                  Toast.makeText(LoginActivity.this,"FullName and UserName Can't be Empty",Toast.LENGTH_SHORT).show();
              }
            }
        };

        loginButton.setOnClickListener(action);
    }
    private void savefullName(String fullName){
          editor = sharedPreferences.edit();

          editor.putString(PrefConstant.FULL_NAME,fullName);
          editor.apply();
    }

    private void savedLoginStatus(){
          editor = sharedPreferences.edit();


          editor.putBoolean(PrefConstant.IS_LOGGED_IN,true);
          editor.apply();
    }

    private void setUpSharedPreferences(){
         sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERNCE_NAME,MODE_PRIVATE);
    }
}
