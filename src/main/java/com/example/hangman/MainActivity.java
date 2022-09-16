package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    AtomicInteger remainingTries = new AtomicInteger(10);
    ArrayList<Character> guessedLetters = new ArrayList<>();
    String word = "";
    TextView mainText;
    ImageView hangmanImage;
    Button buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH, buttonI, buttonJ, buttonK, buttonL, buttonM;
    Button buttonN, buttonO, buttonP, buttonQ, buttonR, buttonS, buttonT, buttonU, buttonV, buttonW, buttonX, buttonY, buttonZ;
    ArrayList<Character> buttonsClicked = new ArrayList<>();
    TextView testWordTextView;
    boolean gameOver = false;
    int globalCorrectGuesses = 0;
    Intent gameOverIntent;


    public void incorrectGuess() {

        remainingTries.getAndDecrement();
        mainText.setText("That letter is incorrect! Try again!");
        switch(remainingTries.get()) {
            case 9: hangmanImage.setImageResource(R.drawable.hangman_1); break;
            case 8: hangmanImage.setImageResource(R.drawable.hangman_2); break;
            case 7: hangmanImage.setImageResource(R.drawable.hangman_3); break;
            case 6: hangmanImage.setImageResource(R.drawable.hangman_4); break;
            case 5: hangmanImage.setImageResource(R.drawable.hangman_5); break;
            case 4: hangmanImage.setImageResource(R.drawable.hangman_6); break;
            case 3: hangmanImage.setImageResource(R.drawable.hangman_7); break;
            case 2: hangmanImage.setImageResource(R.drawable.hangman_8); break;
            case 1: hangmanImage.setImageResource(R.drawable.hangman_9); break;
            case 0: hangmanImage.setImageResource(R.drawable.hangman_10);
                    mainText.setText("Game Over!");
                    gameOver = true;
                    gameOverIntent.putExtra("MESSAGE","Game Over! You Lose!");
                    gameOverIntent.putExtra("IMAGE", "defeatImage");
                    gameOverIntent.putExtra("WORD_REVEAL_MESSAGE","The word was " + word + "!");
                    startActivity(gameOverIntent);
                    break;

        }

    }

    public String constructWordDisplay(ArrayList<Character> guessedLetters) {
        String word = "";
        for(int i = 0; i < guessedLetters.size(); i ++) {
            word += Character.toString(guessedLetters.get(i));
            word += " ";
        }
        testWordTextView.setText(word);
        return word;
    }

    public void pressedLetterButton(char letter, String word, Button button) {
        if(gameOver == true) {
            return;
        }
        if(buttonsClicked.contains(letter)) {
            mainText.setText("You've already selected that letter!");
        }
        else {

            button.setBackgroundColor(Color.RED);
            buttonsClicked.add(letter);
            int numberOfCorrectGuesses = 0;
            Log.d("Debug Tag","The letter of the button you pressed is: " + Character.toString(letter));

            for(int i = 0; i < word.length(); i++) {
                Log.d("Debug Tag","At i = " + i + ", the letter to be matched is " + word.charAt(i));
                if(word.charAt(i) == letter) {
                    Log.d("Debug Tag","There was a match found!");
                    guessedLetters.set(i, letter);
                    constructWordDisplay(guessedLetters);
                    numberOfCorrectGuesses++;
                }
            }
            globalCorrectGuesses += numberOfCorrectGuesses;
            if(globalCorrectGuesses >= word.length()) {
                mainText.setText("You win!");
                gameOver = true;
                gameOverIntent.putExtra("MESSAGE","You win! Congratulations!");
                gameOverIntent.putExtra("IMAGE", "victoryImage");
                gameOverIntent.putExtra("WORD_REVEAL_MESSAGE","Yeah! It's\t" + word + "!");
                startActivity(gameOverIntent);
            }
            else {
                if(numberOfCorrectGuesses == 0) {
                    incorrectGuess();
                }
                else if(numberOfCorrectGuesses > 1) {
                    mainText.setText("There are " + numberOfCorrectGuesses + " " + letter + "s!");
                }
                else {
                    mainText.setText("There is 1 " + letter + "!");
                }
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA = (Button) findViewById(R.id.button_A);
        buttonB = (Button) findViewById(R.id.button_B);
        buttonC = (Button) findViewById(R.id.button_C);
        buttonD = (Button) findViewById(R.id.button_D);
        buttonE = (Button) findViewById(R.id.button_E);
        buttonF = (Button) findViewById(R.id.button_F);
        buttonG = (Button) findViewById(R.id.button_G);
        buttonH = (Button) findViewById(R.id.button_H);
        buttonI = (Button) findViewById(R.id.button_I);
        buttonJ = (Button) findViewById(R.id.button_J);
        buttonK = (Button) findViewById(R.id.button_K);
        buttonL = (Button) findViewById(R.id.button_L);
        buttonM = (Button) findViewById(R.id.button_M);
        buttonN = (Button) findViewById(R.id.button_N);
        buttonO = (Button) findViewById(R.id.button_O);
        buttonP = (Button) findViewById(R.id.button_P);
        buttonQ = (Button) findViewById(R.id.button_Q);
        buttonR = (Button) findViewById(R.id.button_R);
        buttonS = (Button) findViewById(R.id.button_S);
        buttonT = (Button) findViewById(R.id.button_T);
        buttonU = (Button) findViewById(R.id.button_U);
        buttonV = (Button) findViewById(R.id.button_V);
        buttonW = (Button) findViewById(R.id.button_W);
        buttonX = (Button) findViewById(R.id.button_X);
        buttonY = (Button) findViewById(R.id.button_Y);
        buttonZ = (Button) findViewById(R.id.button_Z);
        mainText = (TextView)this.findViewById(R.id.textView);
        hangmanImage = (ImageView) findViewById(R.id.imageView);
        hangmanImage.setImageDrawable(null);
        mainText.setText("Welcome to Hangman! Select a letter to get started!");
        gameOverIntent = new Intent(this, GameOver.class);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('a',word, buttonA);
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               pressedLetterButton('b',word, buttonB);
           }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('c',word, buttonC);
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('d',word, buttonD);
            }
        });
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('e',word, buttonE);
            }
        });
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('f',word, buttonF);
            }
        });
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('g',word, buttonG);
            }
        });
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('h',word, buttonH);
            }
        });
        buttonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('i',word, buttonI);
            }
        });
        buttonJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('j',word, buttonJ);
            }
        });
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('k',word, buttonK);
            }
        });
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('l',word, buttonL);
            }
        });
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('m',word, buttonM);
            }
        });
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('n',word, buttonN);
            }
        });
        buttonO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('o',word, buttonO);
            }
        });
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('p',word, buttonP);
            }
        });
        buttonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('q',word, buttonQ);
            }
        });
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('r',word, buttonR);
            }
        });
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('s',word, buttonS);
            }
        });
        buttonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('t',word, buttonT);
            }
        });
        buttonU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('u',word, buttonU);
            }
        });
        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('v',word, buttonV);
            }
        });
        buttonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('w',word, buttonW);
            }
        });
        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('x',word, buttonX);
            }
        });
        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('y',word, buttonY);
            }
        });
        buttonZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLetterButton('z',word, buttonZ);
            }
        });
        try {

            word = generateWord();
            testWordTextView = (TextView)this.findViewById(R.id.testWord);
            String letterSlots = "";
            for(int i = 0; i < word.length(); i++) {
                guessedLetters.add('_');
            }
            letterSlots = constructWordDisplay(guessedLetters);
            testWordTextView.setText(letterSlots);
            Log.d("Debug Tag",word);

        }

        catch (IOException e) {

            e.printStackTrace();

        }
    }

    public String generateWord() throws IOException {
        String wordBank = wordBankFromFile();
        String[] wordBankArray = wordBank.split("\\R");
        String word = wordBankArray[(int)(Math.random() * wordBankArray.length)];
        return word;
    }

    public String wordBankFromFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.words);
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        for(int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
            out.append(buffer, 0, numRead);
        }
        return out.toString();
    }
}