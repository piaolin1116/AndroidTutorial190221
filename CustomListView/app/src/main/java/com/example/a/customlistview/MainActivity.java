package com.example.a.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        int icon;
        String title;
        String desc;
    }

    ArrayList<MyData> myList = new ArrayList<>();

    private void init(){
        for(int i=0; i<100;i ++){
            MyData myData = new MyData();
            myData.title = "title "+i;
            myData.desc =  "desc "+i;
            myData.icon = i%2==0?R.mipmap.ic_launcher:R.mipmap.ic_launcher_round;
            myList.add(myData);
        }
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public Object getItem(int position) {
            return myList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_view, null);
            }

            ImageView imgView = convertView.findViewById(R.id.itemIcon);
            TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
            TextView textViewDesc = convertView.findViewById(R.id.textViewDesc);

            MyData myData = myList.get(position);
            imgView.setImageResource(myData.icon);
            textViewTitle.setText(myData.title);
            textViewDesc.setText(myData.desc);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ListView listView = findViewById(R.id.myListView);
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }
}
