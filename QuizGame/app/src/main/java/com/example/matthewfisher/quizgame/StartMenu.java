package com.example.matthewfisher.quizgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.util.*;

public class StartMenu extends AppCompatActivity {


    private Button start;
    private Button createQuestion;
    private Button leaderboards;
    public EditText name;
    public String nameText;
    public String filePathsSoreAndName = "ScoreAndName.txt";

    @Override
    //run these commands as soon as the view is loaded
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        start = findViewById(R.id.startBtn);
        createQuestion = findViewById(R.id.createQBtn);
        leaderboards = findViewById(R.id.LeaderboardsBtn);
        name = findViewById(R.id.nameField);
       // test();
        start.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               nameText = name.getText().toString();
               saveToFileScoresAndNames(nameText,filePathsSoreAndName);
               openMain();

           }
        });
        createQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openCreateQuestions();
            }
        });
        leaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderboards();
            }
        });
    }
    //save the scores and the name of the user to the file so thatg it can be read into the list view
    public void saveToFileScoresAndNames(String fileContents, String fileName) {
        Context context = this;
        Log.d("ID","file dir = " + context.getFilesDir());
        try {
            File fp = new File(context.getFilesDir(), fileName);
            FileWriter out = new FileWriter(fp,true);
            out.write("\n" + fileContents);
            out.close();
        } catch (IOException e) {
            Log.d("Me","file error:" + e);
        }
    }
    // open the main menu for the user using intent
    private void openMain(){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
    //takes the user to the create questions view using intent
    private void openCreateQuestions(){
        Intent createQuestions = new Intent(this, CreateQuestion.class);
        startActivity(createQuestions);
    }
    //takes the user to the leaderboards view using intent
    private void openLeaderboards(){
        Intent leaderboards = new Intent(this, Leaderboards.class);
        startActivity(leaderboards);
    }
}
