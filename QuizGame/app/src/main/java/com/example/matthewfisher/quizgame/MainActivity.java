package com.example.matthewfisher.quizgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;



public class MainActivity extends AppCompatActivity {

    StartMenu startMenu = new StartMenu();

    //variables which are stated
    private TextView questionText;
    private int num = 0;
    private int test;

    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    private String answer;

    public ArrayList<String> quest = new ArrayList<>();
    public ArrayList<String> answ = new ArrayList<>();
    public ArrayList<String> pAnsw = new ArrayList<>();

    private int lifesLeft = 3;
    private TextView lifes;
    private TextView score;
    public int scoredPoints = 0;
    public String filePathsSoreAndName = "ScoreAndName.txt";
    private Vibrator vibrate;
    private String scoreTextPass;

    //public static int score;



//    public void readQuestions() {
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(getAssets().open("Questions.txt")));
//            // do reading, usually loop until end of file reading
//            String line;
//            int i = 0;
//            while((line = reader.readLine()) != null){
//                quest.add(line);
//                Log.d("Tag", quest.get(i));
//                i++;
//            }
//        } catch (IOException e) {
//            //log the exception
//        }
//    }
//    public void readAnswers() {
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(getAssets().open("Answers.txt")));
//            // do reading, usually loop until end of file reading
//            String line;
//            int i = 0;
//            while((line = reader.readLine()) != null){
//                answ.add(line);
//                Log.d("hello", answ.get(i));
//                i++;
//            }
//        } catch (IOException e) {
//            //log the exception
//        }
//    }
//    public void readPotentialAnswers() {
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(getAssets().open("PAnswers.txt")));
//            // do reading, usually loop until end of file reading
//            String line;
//            int i = 0;
//            while((line = reader.readLine()) != null){
//                pAnsw.add(line);
//                Log.d("hello", pAnsw.get(i));
//                i++;
//            }
//        } catch (IOException e) {
//            //log the exception
//        }
//
//
//    }

    //Read the potnetiaal answers from the file which is in interal storage on the emulator
    public void readPotentialAnswers() {
        String fileName = "PAnswers.txt";
        Context context = this;
        BufferedReader in = null;
        try {
            File fp = new File(context.getFilesDir(), fileName);
            in = new BufferedReader(new FileReader(fp));
            String line;
            int i = 0;
            while((line = in.readLine()) != null) {
                pAnsw.add(line);
                Log.d("hello", pAnsw.get(i));
                i++;
            }
        }
        catch (IOException e) {
            Log.d("ID", e.getMessage());
        }
    }
    //Read the questions from the file which is in interal storage on the emulator
    public void readQuestions() {
        //the file name
        String fileName = "Questions.txt";
        Context context = this;
        BufferedReader in = null;
        try {
            File fp = new File(context.getFilesDir(), fileName);
            in = new BufferedReader(new FileReader(fp));
            String line;
            int i = 0;
            while((line = in.readLine()) != null) {
                quest.add(line);
                Log.d("hello", quest.get(i));
                i++;
            }
        }
        catch (IOException e) {
            Log.d("ID", e.getMessage());
        }
    }
    //Read the  answers from the file which is in interal storage on the emulator
    public void readAnswers() {
        String fileName = "Answers.txt";
        Context context = this;
        BufferedReader in = null;
        try {
            File fp = new File(context.getFilesDir(), fileName);
            in = new BufferedReader(new FileReader(fp));
            String line;
            int i = 0;
            while((line = in.readLine()) != null) {
                answ.add(line);
                Log.d("hello", answ.get(i));
                i++;
            }
        }
        catch (IOException e) {
            Log.d("ID", e.getMessage());
        }
    }
    @Override
    //run these commands as soon as the view is loaded
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //these variables are given the values of the id from the xml
        questionText = findViewById(R.id.questionText);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);
        score = findViewById(R.id.scoreSystem);
        lifes = findViewById(R.id.lifes);
        vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        //runs these methods when the view is loaded
        totalLifes();
        scoreSystem();
        readQuestions();
        readAnswers();
        readPotentialAnswers();
        setNewQuestions();


        //checks when the user clicks a potential answer the potential answers is read from the tex of the button and
        //compared to the answer file if correct load more questions
        answerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBtn1.getText().toString().equals(answer)) {
                    setNewQuestions();
                    scoredPoints++;
                    scoreSystem();
                    correctAnswerSounds();
                } else {
                    vibrate.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    Log.d("Console", "Wrong Answer");
                    lifesLeft = lifesLeft - 1;
                    totalLifes();
                    endGame();
                }
            }
        });
        answerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBtn2.getText().toString().equals(answer)) {
                    setNewQuestions();
                    scoredPoints++;
                    scoreSystem();
                    correctAnswerSounds();
                } else {
                    vibrate.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    Log.d("Console", "Wrong Answer");
                    lifesLeft = lifesLeft - 1;
                    totalLifes();
                    endGame();

                }
            }
        });
        answerBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBtn3.getText().toString().equals(answer)) {
                    setNewQuestions();
                    scoredPoints++;
                    scoreSystem();
                    correctAnswerSounds();
                } else {
                    vibrate.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    Log.d("Console", "Wrong Answer");
                    lifesLeft = lifesLeft - 1;
                    totalLifes();
                    endGame();
                }
            }
        });
        answerBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerBtn4.getText().toString().equals(answer)) {
                    setNewQuestions();
                    scoredPoints++;
                    scoreSystem();
                    correctAnswerSounds();
                } else {
                    vibrate.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    Log.d("Console", "Wrong Answer");
                    lifesLeft = lifesLeft - 1;
                    totalLifes();
                    endGame();
                }
            }
        });

    }
    //if the lifes left is less or equal to
    public void endGame() {
        if (lifesLeft <= 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            // Setting Alert Dialog Title
            alertDialogBuilder.setTitle("Game Over");
            alertDialogBuilder.setMessage("You Will Now Return To The Menu");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    saveToFileScoresAndNames(scoreTextPass,filePathsSoreAndName);
                    backToStart();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
    //calling to get new questions from the array list by incrimenting the array list
    public void setNewQuestions() {
        readPotentialAnswers();
        questionText.setText(quest.get(num));
        answer = answ.get(num);
        num = num + 1;
        answerBtn1.setText(pAnsw.get(test));
        test++;
        answerBtn2.setText(pAnsw.get(test));
        test++;
        answerBtn3.setText(pAnsw.get(test));
        test++;
        answerBtn4.setText(pAnsw.get(test));
        test++;
    }
    // gets the number of point then concatinated it into a string then into a text view
    public void scoreSystem(){
        String scoreText;
        scoreTextPass = Integer.toString(scoredPoints);
        scoreText = "Correct Answers: " + scoredPoints;
        score.setText(scoreText);

    }
    //saving the contents by passing the contents which wants to be saved to the file
    //file name of the file which this data will be appended to
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
    //will return user back to the home screen of the applciation
    public void backToStart(){
        Intent main = new Intent(this, StartMenu.class);
        startActivity(main);
    }
    //will display the total lifes the user has left by conatiating the scored points into a string
    public void totalLifes() {
        String livesText;
        livesText = "You have " + lifesLeft + " Lives Left";
        lifes.setText(livesText);
    }
    //everytime a user gets a
    public void correctAnswerSounds(){
        MediaPlayer ding = MediaPlayer.create(MainActivity.this, R.raw.ding);
        ding.start();
    }

}


