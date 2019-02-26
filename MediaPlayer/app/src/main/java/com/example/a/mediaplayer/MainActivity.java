package com.example.a.mediaplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;

// http://www.hochmuth.com/mp3/Haydn_Cello_Concerto_D-1.mp3
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mp = null;
    SeekBar mSeekBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    if(mp != null){
                        mp.seekTo(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPlay){
            musicPlay();
        }else if(v.getId() == R.id.btnStop){
            musicStop();
        }
    }

    private void musicPlay(){
        mp = new MediaPlayer();
        String path = "http://www.hochmuth.com/mp3/Haydn_Cello_Concerto_D-1.mp3";
        String localPath = Environment.getExternalStorageDirectory().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mp != null){
                    mSeekBar.setProgress(mp.getCurrentPosition());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
            mSeekBar.setMax(mp.getDuration());

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
