package com.example.a.xmlpullparser;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    enum DataType { none, hourType, dayType, tempType, wfKorType };
    DataType type = DataType.none;

    class WeatherData {
        int day;
        int hour;
        float temp;
        String wfKor;

        @Override
        public String toString() {
            return "WeatherData{" +
                    "day=" + day +
                    ", hour=" + hour +
                    ", temp=" + temp +
                    ", wfKor='" + wfKor + '\'' +
                    '}';
        }
    }

    ArrayList<WeatherData> list = new ArrayList<WeatherData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWeather).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyPullParserTask task = new MyPullParserTask();
        task.execute("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000");
    }

    class MyPullParserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("weather",s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String res = "";

            try {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();

                URL url = new URL(strings[0]);
                xpp.setInput(url.openStream(),"utf-8");

                WeatherData data = null;
                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if(xpp.getName().equals("data")){
                                data = new WeatherData();
                                list.add(data);
                            }else if(xpp.getName().equals("day")){
                                type = DataType.dayType;
                            }else if(xpp.getName().equals("hour")){
                                type = DataType.hourType;
                            }else if(xpp.getName().equals("temp")){
                                type = DataType.tempType;
                            }else if(xpp.getName().equals("wfKor")){
                                type = DataType.wfKorType;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            switch (type){
                                case dayType:
                                    data.day = Integer.parseInt(xpp.getText());
                                    break;
                                case hourType:
                                    data.hour = Integer.parseInt(xpp.getText());
                                    break;
                                case tempType:
                                    data.temp = Float.parseFloat(xpp.getText());
                                    break;
                                case wfKorType:
                                    data.wfKor = xpp.getText();
                                    break;

                            }
                            type = DataType.none;
                            break;
                    }
                    eventType = xpp.next();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            for(WeatherData wd : list){
                res += wd.toString() + "\n";
            }

            return res;
        }
    }
}
