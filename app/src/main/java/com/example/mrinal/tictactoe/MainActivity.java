package com.example.mrinal.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0=red, 1=green

    int counterChance=0;

    String name;
    int gameState[]={2,2,2,2,2,2,2,2,2};

    int winningPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive=true;

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        int tappedCounter=Integer.valueOf(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive==true) {

            gameState[tappedCounter]=counterChance;
            counter.setTranslationY(-1000f);

            if (counterChance == 0) {
                counter.setImageResource(R.drawable.unnamed);
                counterChance = 1;
            } else {
                counter.setImageResource(R.drawable.unnamed1);
                counterChance = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(1);

            for(int [] winningPosition:winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                        &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]]
                        &&
                        gameState[winningPosition[0]]!=2) {

                    //Someone won

                    if(gameState[winningPosition[0]]==0) {
                        name="Red Won";

                    }
                    if(gameState[winningPosition[0]]==1) {
                        name="Green Won";

                    }

                    gameActive=false;
                    TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(name);

                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);



                }
                else
                {
                    boolean gameOver=true;
                    for(int x: gameState)
                    {
                        if(x==2)
                            gameOver=false;
                    }
                    if(gameOver){
                        TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw!");

                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }


    }

    public void playAgain(View view)
    {
        gameActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        counterChance=0;

        for(int i=0;i<=8;i++)
        gameState[i]=2;

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
