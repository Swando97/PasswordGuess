package com.example.t00553882.passwordguess;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public double pass = 12345;
    public double range = 1000000000;
    TextView progressText;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressText = (TextView)findViewById(R.id.progressText);


    }

    public void go(View view){
        progressText.setText("Calculating...");
        progressBar.setProgress(2);

        new Potato().execute(range, pass);
    }

    private class Potato extends AsyncTask<Double, Double, String>{

        private double guess = 0;
        private double count = 0.0;



        @Override
        protected String doInBackground(Double... doubles) {
            Double theRange = doubles[0];
            Double thePass = doubles[1];

            Random ran = new Random();

            while(guess != thePass){
                guess = ran.nextDouble() % theRange;
                count+=1.0;
                publishProgress(count);

            }
            return "Found Pass: " + guess;
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress((int)count * 100/ (int)range);
        }
    }

}
