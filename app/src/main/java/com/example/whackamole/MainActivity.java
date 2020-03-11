package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler handler;
    private TextView Counter;
    private int timer;
    public Button Button;
    public int scores;
    public TextView Score;
    public boolean mole;
    public Time x;
    public moleMove y;
    private ConstraintLayout layout;
    private int color;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Button = findViewById(R.id.Button);


        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        imageViews = new ImageView[16];
        rand = new Random();
        handler = new Handler();
        moleLocation = rand.nextInt(16);
        Counter = findViewById(R.id.Counter);
        x = new Time();
        timer = 30;
        Score= findViewById(R.id.Score);
        mole = false;
        y = new moleMove();
        scores = 0;

        for(int i=0; i<16; i++) {
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if(i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }
    }

    public void colorPressed(View v) {
        Intent i = new Intent(this, NewmoleActivity.class);
        i.putExtra("COLOR", color);
        startActivityForResult(i, 1);
    }
        public void helpPressed(View v){
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int color = data.getIntExtra("COLOR", 0xffffffff);
        layout.setBackgroundColor(color);
    }

    private class moleMove implements Runnable {

        public void run(){
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(moleImage);
            handler.postDelayed(y,1000);
        }
    }

    public void pointsScore(View v) {
        if(mole == true && (v == imageViews[moleLocation])) {
            scores += 100;
            Score.setText("Score: " + scores);
            imageViews[moleLocation].setImageDrawable(null);
        }
  }

    private class Time implements Runnable {

        public void run(){
            if(timer > 0) {
                timer --;
                Counter.setText("" +timer);
                handler.postDelayed(x, 500);
            }else {
                mole = false;
                handler.removeCallbacks(x);
                handler.removeCallbacks(y);
            }
        }
    }

    public void startPressed(View v) {
        if(mole) {
            mole = false;
            handler.removeCallbacks(y);
            handler.removeCallbacks(x);
        } else{
            mole = true;
            timer = 30;
            Counter.setText(""+timer);
            handler.postDelayed(y, 1000);
            handler.postDelayed(x, 500);
        }

    }

}
