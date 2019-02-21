package com.example.a.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0; i<100; i++) {
                Log.d("count", "count : " + i);
                try {
                    sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MyThread myThread = new MyThread();
               myThread.start();
            }
        });
    }
}
