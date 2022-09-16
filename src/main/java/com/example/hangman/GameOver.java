package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    TextView endSceneTextView;
    ImageView endSceneImageView;
    Button playAgain;
    Intent startIntent;
    TextView wordRevealTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        endSceneTextView = (TextView) findViewById(R.id.endSceneTextView);
        endSceneImageView = (ImageView) findViewById(R.id.endSceneImage);
        wordRevealTextView = (TextView) findViewById(R.id.wordReveal);
        startIntent = new Intent(this, MainActivity.class);
        playAgain = (Button) findViewById(R.id.playAgain);
        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        endSceneTextView.setText(message);
        String image = intent.getStringExtra("IMAGE");
        wordRevealTextView.setText(intent.getStringExtra("WORD_REVEAL_MESSAGE"));
        Log.d("Debug",image);
        endSceneImageView.setImageDrawable(null);
        if(image.equals("defeatImage")) {
            endSceneImageView.setImageResource(R.drawable.hangman_sad);
            Log.d("Debug","The image should be changed to a sad stickman.");
        }
        if(image.equals("victoryImage")) {
            endSceneImageView.setImageResource(R.drawable.hangman_celebrating);
            Log.d("Debug","The image should be changed to a happy stickman.");
        }
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(startIntent);
            }
        });
    }
}