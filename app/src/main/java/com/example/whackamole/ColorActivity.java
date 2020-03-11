package com.example.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ColorActivity extends AppCompatActivity {

    private RadioButton yellowButton;
    private RadioButton pinkButton;
    private RadioButton cyanButton;
    private int color;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        yellowButton = findViewById(R.id.yellowButton);
        pinkButton = findViewById(R.id.pinkButton);
        cyanButton = findViewById(R.id.cyanButton);
        Intent i = getIntent();
        int color = i.getIntExtra("COLOR", 0xffffffff);
        if (color ==0xffffff00)
            yellowButton.setChecked(true);
        else if (color == 0xffffc0cb)
            pinkButton.setChecked(true);
        else if (color == 0xff00ffff)
            cyanButton.setChecked(true);

    }

    @Override
    public void onBackPressed(){
        int color;
        if (yellowButton.isChecked())
            color = 0xffffff00;
        else if (pinkButton.isChecked())
            color = 0xffffc0cb;
        else
            color = 0xff00ffff;
        Intent i = new Intent();
        i.putExtra("COLOR", color);
        setResult(RESULT_OK, i);
        finish();
    }

}
