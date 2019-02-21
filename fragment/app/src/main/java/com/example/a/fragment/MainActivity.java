package com.example.a.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnRemove).setOnClickListener(this);
        findViewById(R.id.btnHide).setOnClickListener(this);
        findViewById(R.id.btnReplace).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.frame);
        switch (v.getId()){
            case R.id.btnAdd:
                if(fragment == null){
                    fragment = new BlankFragment();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.frame, fragment, "counter");
                    transaction.commit();
                }
                break;
            case R.id.btnRemove:
                if(fragment != null){
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.remove(fragment).commit();
                }
                break;
            case R.id.btnHide:
                if(fragment != null){
                    FragmentTransaction transaction = manager.beginTransaction();
                    if(fragment.isHidden()){
                        transaction.show(fragment).commit();
                    }else{
                        transaction.hide(fragment).commit();
                    }
                }
                break;
            case R.id.btnReplace:
                if(fragment != null){
                    FragmentTransaction transaction = manager.beginTransaction();
                    if(fragment.getTag().equals("counter")){
                        TextFragment textFragment = new TextFragment();
                        transaction.replace(R.id.frame,textFragment,"text").commit();
                    }else{
                        BlankFragment blankFragment = new BlankFragment();
                        transaction.replace(R.id.frame,blankFragment,"counter").commit();
                    }
                }
        }
    }
}
