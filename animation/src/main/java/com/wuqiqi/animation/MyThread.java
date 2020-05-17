package com.wuqiqi.animation;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.Nullable;

public class MyThread extends Thread {

    @Override
    public void run() {
        IntentService intentService = new IntentService("123") {
            @Override
            protected void onHandleIntent(@Nullable Intent intent) {

            }
        };
        Looper.prepare();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);
        new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == 3){

                }else if(msg.what == 4){

                }
                return false;
            }
        });
    }
}
