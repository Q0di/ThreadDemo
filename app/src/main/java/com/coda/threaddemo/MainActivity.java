package com.coda.threaddemo;

import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;


public class MainActivity extends ActionBarActivity {

    Handler handler = new Handler(){
        //handles UI
        @Override
        public void  handleMessage(Message msg){
            TextView msgTV = (TextView) findViewById(R.id.msgTV);
            if(msgTV.getText().toString().contentEquals("Crystal Gems!")){
            msgTV.setText("Good Job!");}
            else {
                msgTV.setText("Crystal Gems!");
            }

        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickMyButton(View view) {

        //2nd thread
        Runnable r = new Runnable() {
            @Override
            public void run() {
                long futuretime = System.currentTimeMillis()+10000;
                while(System.currentTimeMillis()< futuretime){
                    synchronized (this){
                        try {
                            wait(futuretime - System.currentTimeMillis());
                        }catch (Exception e){

                        }
                    }
                }//call handler
                handler.sendEmptyMessage(0);
            }
        };

        Thread myThread = new Thread(r);
        myThread.start();
    }
}
