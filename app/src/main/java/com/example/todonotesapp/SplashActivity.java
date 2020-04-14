package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUpsharedPrefrences();
        checkloginstatus();
    }

    private void setUpsharedPrefrences(){
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERNCE_NAME,MODE_PRIVATE);

    }

    private void checkloginstatus(){

        boolean isLoggedIn = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN,false);

        if(isLoggedIn){
            Intent intent = new Intent(SplashActivity.this, MyNotesActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }


    }


}
