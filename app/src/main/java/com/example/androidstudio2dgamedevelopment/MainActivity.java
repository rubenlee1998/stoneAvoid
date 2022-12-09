package com.example.androidstudio2dgamedevelopment;

import android.animation.Animator;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * MainActivity is the entry point to our application.
 */
public class MainActivity extends Activity {

    private Game game;
    private MediaPlayer mPlayer; // 배경음악
    private boolean isStart = true;
    private int cnt =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);

        mPlayer = MediaPlayer.create(this,R.raw.findmoris);
        mPlayer.setLooping(true);
        mPlayer.start();
        setContentView(R.layout.activity_main);
//        // Set content view to game, so that objects in the Game class can be rendered to the screen
        game = new Game(this);
        Button button1 = (Button) findViewById(R.id.button1) ;
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(game);
                isStart = false;
            }
        });

    }
    public void onClick(View target){
        isStart = false;
    }

    @Override
    protected void onStart() {
        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
