package com.example.singh.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int activePlayer=0;
    private int[] gameState={2,2,2,2,2,2,2,2,2};
    private boolean gameIsActive=true;
    private int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        ImageView counter=(ImageView) view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameIsActive==true) {
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
            for(int[] winningPosition:winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && (gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2)){
                    TextView t=(TextView)findViewById(R.id.playAgainText);
                    gameIsActive=false;
                    String winner="Red";
                    if(gameState[winningPosition[0]]==0){
                        winner="Yellow";
                    }
                    t.setText(winner+" Has Won");
                    t.animate().alpha(1f).setDuration(200);
                    Button bt=(Button)findViewById(R.id.playAgainButton);
                    bt.animate().alpha(1f).setDuration(100);
                } else {
                    boolean gameIsOver=true;
                    for(int i:gameState){
                        if(i==2) gameIsOver=false;
                    }
                    if(gameIsOver){
                        TextView t=(TextView)findViewById(R.id.playAgainText);
                        t.setText("It is A Draw");
                        t.animate().alpha(1f).setDuration(200);
                        Button bt=(Button)findViewById(R.id.playAgainButton);
                        bt.animate().alpha(1f).setDuration(100);
                    }
                }
            }
        }
        //counter.animate().rotationBy(180).setDuration(1400);
    }

    public void playAgain(View view){
        gameIsActive=true;
        TextView t=(TextView)findViewById(R.id.playAgainText);
        t.animate().alpha(0f).setDuration(1);
        Button bt=(Button)findViewById(R.id.playAgainButton);
        bt.animate().alpha(0f).setDuration(1);
        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        for(int i=0;i<grid.getChildCount();i++){
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
