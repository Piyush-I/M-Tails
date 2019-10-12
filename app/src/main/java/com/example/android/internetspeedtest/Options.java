package com.example.android.internetspeedtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {
  private Button signalinfo,siminfo,baseinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        signalinfo = (Button) findViewById(R.id.signalInfo);
        signalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siminfoPage();
            }
        });
        siminfo = (Button) findViewById(R.id.simInfo);
        siminfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siminfoPage();
            }
        });
    }
    public void siminfoPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void siminfopage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void baseinfopage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
