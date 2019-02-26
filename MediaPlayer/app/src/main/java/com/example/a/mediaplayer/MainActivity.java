package com.example.a.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

// http://www.hochmuth.com/mp3/Haydn_Cello_Concerto_D-1.mp3
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPlay){

        }else if(v.getId() == R.id.btnStop){

        }
    }

    private void musicPlay(){
        mp = new MediaPlayer();
        String path = "http://www.hochmuth.com/mp3/Haydn_Cello_Concerto_D-1.mp3";
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void musicStop(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
