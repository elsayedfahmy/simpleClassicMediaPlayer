package com.example.elsayedfahmy_pc.classicmediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnStart,btnStop,test;
    ProgressBar pBarStatus;
    MediaPlayer mp;
    float x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initUI();
        initEventDriven();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnStart=(Button)findViewById(R.id.buttonStart);
        btnStop=(Button)findViewById(R.id.buttonStop);
        pBarStatus=(ProgressBar)findViewById(R.id.progressBarStatus);
        test=(Button)findViewById(R.id.buttonTest);
    }

    private void initUI() {
        setSupportActionBar(toolbar);
    }

    private void initEventDriven() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(mp!=null&&mp.isPlaying())) {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.song);
                    mp.start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            pBarStatus.setProgress((mp.getCurrentPosition()/mp.getDuration())*100);
                        }
                    }).start();
                }
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=mp.getCurrentPosition()/1000;
                Toast.makeText(MainActivity.this,x+"",Toast.LENGTH_SHORT).show();

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null&&mp.isPlaying()){
                    mp.stop();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
