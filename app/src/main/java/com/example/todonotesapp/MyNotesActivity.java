package com.example.todonotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todonotesapp.Adapter.NotesAdapter;
import com.example.todonotesapp.Model.Notes;
import com.example.todonotesapp.clicklistener.ItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyNotesActivity extends AppCompatActivity {
    String actionBarName;
    FloatingActionButton fabButton;
  //  TextView textViewTitle,textViewDesc;
    RecyclerView recyclerViewList;
    SharedPreferences sharedPreferences;


    ArrayList<Notes> noteslist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        bindView();
        setUpsharedPrefrences();
        getIntentData();




        
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpDialogBox();
            }
        });
        getSupportActionBar().setTitle(actionBarName);


    }

    private void setUpsharedPrefrences(){
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERNCE_NAME,MODE_PRIVATE);
    }

    private void bindView() {
        fabButton = findViewById(R.id.fabButton);

//        textViewTitle=findViewById(R.id.textViewTitle);
//        textViewDesc=findViewById(R.id.textViewDesc);
        recyclerViewList = findViewById(R.id.RecycleViewlist);
    }

    private void getIntentData(){
        Intent intent = getIntent();
        actionBarName=intent.getStringExtra(AppConstant.FULL_NAME);
        if(TextUtils.isEmpty(actionBarName)){
            actionBarName=sharedPreferences.getString(PrefConstant.FULL_NAME,"");
        }

    }

    private void setUpDialogBox() {

        View view = LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_notes_dialog_layout,null);

        final EditText editTextTitle = view.findViewById(R.id.editTextTitle);
        final EditText editTextDescription=view.findViewById(R.id.editTextDescription);
        Button SubmitButton = view.findViewById(R.id.SubmitButton);

        final AlertDialog  dialog = new AlertDialog.Builder(this).setView(view).setCancelable(false).create();
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textViewTitle.setText(editTextTitle.getText().toString());
//                textViewDesc.setText(editTextDescription.getText().toString());
                 String textTitle=editTextTitle.getText().toString();
                 String textDesc= editTextDescription.getText().toString();
                 if(!TextUtils.isEmpty(textTitle)&& !TextUtils.isEmpty(textDesc)) {
                     Notes notes = new Notes();
                     notes.setTitle(textTitle);
                     notes.setDescription(textDesc);
                     noteslist.add(notes);
                 }
                 setUpRecyclerView();


                dialog.hide();
            }
        });

        dialog.show();


    }

    private void setUpRecyclerView() {

        // interface
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(Notes notes) {
                    Intent intent = new Intent(MyNotesActivity.this, DetailActivity.class);
                    intent.putExtra(AppConstant.TITLE,notes.getTitle());
                    intent.putExtra(AppConstant.DESCRIPTION,notes.getDescription());
                    startActivity(intent);
            }
        };




        NotesAdapter notesAdapter = new NotesAdapter(noteslist,itemClickListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyNotesActivity.this);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerViewList.setLayoutManager(linearLayoutManager);

        recyclerViewList.setAdapter(notesAdapter);
    }
}
