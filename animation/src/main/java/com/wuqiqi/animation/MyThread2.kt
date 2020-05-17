package com.wuqiqi.animation

import android.os.Handler
import android.os.Looper

class MyThread2 : Thread() {
    override fun run() {
        Looper.prepare()
        Handler(Looper.myLooper(), Handler.Callback { msg ->
            if (msg.what == 3) {
            } else if (msg.what == 4) {
            }
            false
        })
    }
}