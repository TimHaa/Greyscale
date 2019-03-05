package com.example.android.greyscale;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.greyscale.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    int backgroundColor = intToGrey((int) (Math.random()*256));
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        SeekBar slider = (SeekBar) findViewById(R.id.slider);
        slider.setProgress(9);
        TextView puzzleField = findViewById(R.id.puzzle_field);
        puzzleField.setBackgroundColor(backgroundColor);

        final TextView colorField = findViewById(R.id.color_field);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorField.setBackgroundColor(intToGrey(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetProgress();
            }
        });

    }

    private void resetProgress(){
        SeekBar slider = (SeekBar) findViewById(R.id.slider);
        if ( intToGrey(slider.getProgress()) == backgroundColor) {

            Toast.makeText(MainActivity.this, "very good", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "you missed it by " +  ( slider.getProgress() - greyToInt(backgroundColor) ) + " points.", Toast.LENGTH_SHORT).show();
        }

    }
    public void setLvl(View v){
        Toast.makeText(this, "Choose lvl menu.", Toast.LENGTH_SHORT).show();
    }

    public void setBackground(View v){
        Toast.makeText(this, "Choose background setting.", Toast.LENGTH_SHORT).show();
    }
    public void helpAbout(View v){
        Toast.makeText(this, "That's what it's all about.", Toast.LENGTH_SHORT).show();
    }
    public void increase(View v){
        SeekBar slider = (SeekBar) findViewById(R.id.slider);
        slider.incrementProgressBy(1);
    }
    public void decrease(View v){
        SeekBar slider = (SeekBar) findViewById(R.id.slider);
        slider.incrementProgressBy(-1);
    }
    public int intToGrey(int num){
        return (int) (0xFF000000 + num * (1 + Math.pow(16, 2) + Math.pow(16, 4)));
    }
    public int greyToInt(int hexa){
        return (hexa - 0xFF000000) / (int) (1 + Math.pow(16, 2) + Math.pow(16, 4));
    }
}
