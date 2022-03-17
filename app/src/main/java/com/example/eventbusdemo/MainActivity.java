package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册EventBus，注册要在onStart方法中
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册，一般在onDestroy或onStop
        EventBus.getDefault().unregister(this);
    }
    //创建方法，接收事件消息，该方法必须是public修饰
    //不能用static修饰，不能是抽象的
    //该方法需要用@Subscribe注解
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(EventMessage message){
        Log.e(TAG, "onReceiveMsg: " + message.toString() );
    }
}
















