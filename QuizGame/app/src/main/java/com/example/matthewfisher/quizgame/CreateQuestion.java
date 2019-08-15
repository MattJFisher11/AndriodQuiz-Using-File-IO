package com.example.matthewfisher.quizgame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.*;
import java.util.*;

public class CreateQuestion extends AppCompatActivity {

    //submit Buttons for submiting questions and answers
    private Button cSubmit;
    //Questions text box
    private EditText cQuestion;
    //Answers text box
    private EditText cAnswer;
    //Potential answers
    private EditText cPotentialAnswer1;
    //Potential answers
    private EditText cPotentialAnswer2;
    //Potential answers
    private EditText cPotentialAnswer3;
    //Potential answers
    private EditText cPotentialAnswer4;
    //Files Paths
    public String filePathQuestions = "Questions.txt";
    //Files Paths
    public String filePathAnswers = "Answers.txt";
    //Files Paths
    public String filePathPAnswers = "PAnswers.txt";


    @Override
    //run these commands as soon as the view is loaded
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        cSubmit = findViewById(R.id.cSubmit);
        cQuestion = findViewById(R.id.cQuestion);
        cAnswer = findViewById(R.id.cAnswer);
        cPotentialAnswer1 = findViewById(R.id.cPotentialAnswer1);
        cPotentialAnswer2 = findViewById(R.id.cPotentialAnswer2);
        cPotentialAnswer3 = findViewById(R.id.cPotentialAnswer3);
        cPotentialAnswer4 = findViewById(R.id.cPotentialAnswer4);

        cSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String cQuestionP = cQuestion.getText().toString();
              String cAnswerP = cAnswer.getText().toString();
              String cPotentialAnswer1P = cPotentialAnswer1.getText().toString();
              String cPotentialAnswer2P = cPotentialAnswer2.getText().toString();
              String cPotentialAnswer3P = cPotentialAnswer3.getText().toString();
              String cPotentialAnswer4P = cPotentialAnswer4.getText().toString();
              saveToFileQuestions(cQuestionP, filePathQuestions);
              saveToFileAnswers(cAnswerP, filePathAnswers);
              saveToFilePAnswers(cPotentialAnswer1P,filePathPAnswers);
              saveToFilePAnswers(cPotentialAnswer2P,filePathPAnswers);
              saveToFilePAnswers(cPotentialAnswer3P,filePathPAnswers);
              saveToFilePAnswers(cPotentialAnswer4P,filePathPAnswers);
              questionsCreatedMessage();
            }
        });
    }
    //saving the contents by passing the contents from the edit text field with the
    //file name of the file which this data will be appended to
    public void saveToFileQuestions(String fileContents, String fileName) {
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
    //saving the contents by passing the contents from the edit text field with the
    //file name of the file which this data will be appended to
    public void saveToFileAnswers(String fileContents, String fileName) {
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
    //saving the contents by passing the contents from the edit text field with the
    //file name of the file which this data will be appended to
    public void saveToFilePAnswers(String fileContents, String fileName) {
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
    //pop up message called once the submit message been clicked and data been written to file
    public void questionsCreatedMessage(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Create Questions");
        alertDialogBuilder.setMessage("Your Question has been added");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                openStartMenu();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    //returns user backs to the home menue
    private void openStartMenu(){
        Intent createQuestions = new Intent(this, StartMenu.class);
        startActivity(createQuestions);
    }
}
