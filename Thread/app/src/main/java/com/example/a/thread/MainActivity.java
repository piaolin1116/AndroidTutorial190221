package com.example.a.thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStart;

    class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0; i<100; i++) {
                Log.d("count", "count : " + i);
                //btnStart.setText("count : " + i);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.arg1 = i;
                handler.sendMessage(msg);
                try {
                    sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                btnStart.setText("count : " + msg.arg1);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MyThread myThread = new MyThread();
               myThread.start();
            }
        });
    }
}
