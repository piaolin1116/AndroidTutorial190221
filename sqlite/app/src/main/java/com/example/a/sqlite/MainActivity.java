package com.example.a.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestSQLiteHandler handler = new TestSQLiteHandler(this);
        handler.insert("kim",30,"한쿡");
        handler.insert("milk",31,"미쿡");
        handler.update("kim",32);
        handler.selectAll();
    }
}
