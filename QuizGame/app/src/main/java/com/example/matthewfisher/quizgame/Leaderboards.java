package com.example.matthewfisher.quizgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Leaderboards extends AppCompatActivity {

    private Button home;
    public ArrayList<String> scores;
    public ListView leaderboard;
    public String value;
    @Override
    //run these commands as soon as the view is loaded
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        //these variables are given the values of the id from the xml
        home = findViewById(R.id.Homebtn);
        leaderboard = findViewById(R.id.leadList);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
        listView();

    }
    //creating a list view to display the results of the user
    public void listView(){

        // Get the reference of the list
        ListView List=(ListView)findViewById(R.id.leadList);
        scores = new ArrayList<String>();
        readScoreAndName();
        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, scores);
        // Set The Adapter
        List.setAdapter(arrayAdapter);
    }
    //read the final scores and the players from the file whihc the data is written to
    public void readScoreAndName(){
    //get the file name which is located ont he internal storage of the emulator
    String fileName = "ScoreAndName.txt";
    Context context = this;
    BufferedReader in = null;
    try {
        File fp = new File(context.getFilesDir(), fileName);
        in = new BufferedReader(new FileReader(fp));
        String line;
        int i = 0;
        while((line = in.readLine()) != null) {
            scores.add(line);
            Log.d("hello", scores.get(i));
            i++;
        }
    }
    catch (IOException e) {
        Log.d("ID", e.getMessage());
    }
}
    //open the home menu
    private void openHome(){
        Intent home = new Intent(this, StartMenu.class);
        startActivity(home);
    }

}
