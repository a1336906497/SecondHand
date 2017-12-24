package com.example.qiao.secondhand.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiao.secondhand.MainActivity;
import com.example.qiao.secondhand.R;

import java.util.Timer;
import java.util.TimerTask;

public class WellComeActivity extends AppCompatActivity {

    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_come);


        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(WellComeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
