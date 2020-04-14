package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView textViewTitle, textViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindViews();
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();

       String title = intent.getStringExtra(AppConstant.TITLE);
       String description =intent.getStringExtra(AppConstant.DESCRIPTION);

       textViewTitle.setText(title);
       textViewDescription.setText(description);
    }

    private void bindViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription=findViewById(R.id.textViewDescription);
    }
}
