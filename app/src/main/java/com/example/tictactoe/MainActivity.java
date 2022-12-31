package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; //yellow;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameActive = true;
    Button playAgainButton;
    TextView winnerTextView;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000);
        counter.animate().translationYBy(1000).setDuration(200);

        TextView winning = (TextView) findViewById(R.id.winning);
        Button button =(Button) findViewById(R.id.button);
//        Log.i("tag",counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive)
        {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            Log.i("tag", Arrays.toString(gameState));

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String message = "";
                    if (activePlayer == 1) {
                        message = "Yellow Wins!!";
                    } else {
                        message = "Red Wins!!";
                    }

                    winning.setText(message);
                    winning.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view)
    {

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton = (Button) findViewById(R.id.button);
        winnerTextView = (TextView) findViewById(R.id.winning);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView counter =(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        activePlayer=0;//yellow
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgainButton = (Button) findViewById(R.id.button);
        winnerTextView = (TextView) findViewById(R.id.winning);
    }
}