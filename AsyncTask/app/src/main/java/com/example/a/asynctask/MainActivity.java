package com.example.a.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    class MyTask extends AsyncTask<Integer, Float, String >{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);
            textView.setText("count : " + values[0]);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int value = integers[0];
            //ui 요소 접근 불가
            for(int i=value; i<100; i++){
                try {
                    Thread.sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
                Log.d("count","count : " + i);
                publishProgress((float) i); // onProgressUpdate 호출함
            }
            return "done"; // onPostExecute 호출
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView.findViewById(R.id.textView);

        MyTask myTask = new MyTask();
        myTask.execute(30);
    }
}
