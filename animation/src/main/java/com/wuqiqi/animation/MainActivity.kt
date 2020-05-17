package com.wuqiqi.animation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import com.wuqiqi.animation.mvp.ui.act.LoginAct
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.security.AccessController.getContext


import java.util.HashMap

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setOnClickListener(this)
        val map1 = HashMap<String, Int>()
        map1.putAll(map1)
        map1["123"] = 4
        val editor:SharedPreferences.Editor=getPreferences(Context.MODE_PRIVATE).edit()
        editor.putFloat("123",13f)
        editor.putInt("123",1)
        editor.apply()

        getPreferences(Context.MODE_PRIVATE).edit {
            putFloat("13",13f)
            putInt("123",2)
        }

        getPreferences(Context.MODE_PRIVATE).modify {
            putFloat("13",13f)
            putInt("123",2)
        }
      /*  textView.setOnClickListener(){
            Toast.makeText(this@MainActivity,"123",Toast.LENGTH_LONG).show()
        }*/
        val stringBuffer:StringBuffer = StringBuffer("123")
        val stringBuilder:StringBuilder = StringBuilder("234")

       /* var myThread = MyThread()
        myThread.start()
        var message:Message = Message()
        message.what=3
        myThread.getHandler().sendMessage(message)
        Handler().postDelayed(Runnable { ->
            message.what=4
            myThread.getHandler().sendMessage(message)

        },2000)
        Handler().postDelayed(Runnable { ->
            message.what=3
            myThread.getHandler().sendMessage(message)

        },2000)*/
    }

    /*inner class MyThread:Thread(){
       private lateinit var myHandler: Handler
       fun getHandler():Handler{
           return myHandler
       }
       override fun run() {
           super.run()
           Looper.prepare()
           myHandler = Handler(Looper.myLooper(), Handler.Callback { msg ->
               if (msg.what == 3) {
                   Toast.makeText(this@MainActivity,"msg.what=3",Toast.LENGTH_LONG).show()
               } else if (msg.what == 4) {
                   Toast.makeText(this@MainActivity,"msg.what=4",Toast.LENGTH_LONG).show()
                   Looper.myLooper().quit()
               }
               false
           })
           Looper.loop()
       }
   }
*/
    fun SharedPreferences.modify(block:SharedPreferences.Editor.()->Unit){
        val editor:SharedPreferences.Editor= edit()
        editor.block()
        editor.apply()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.textView->{
              var intent:Intent = Intent(this@MainActivity,LoginAct::class.java)
              startActivity(intent)
           }
       }
    }
}

