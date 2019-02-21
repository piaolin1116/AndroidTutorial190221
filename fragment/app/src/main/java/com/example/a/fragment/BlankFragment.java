package com.example.a.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }

    TextView textViewCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
*/
        View v = inflater.inflate(R.layout.fragment_blank, container, false);;
        Button btnIncrease =  v.findViewById(R.id.btnIncrease);
        textViewCounter = v.findViewById(R.id.textViewCounter);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = textViewCounter.getText().toString();
                int value = Integer.parseInt(str);
                textViewCounter.setText((value+1)+"");
            }
        });
        return v;
    }

}
