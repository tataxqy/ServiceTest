package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start_btn;
    private Button stop_btn;
    private Button bind_btn;
    private Button onunbind_btn;

    //ServiceConnection用于绑定客户端和service
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
         MyService.MyBinder mb=(MyService.MyBinder)service;
         int step=mb.getProcess();//取得服务的进度
            Log.e("MainActivity","当前的i是"+step);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_btn=findViewById(R.id.start_service);
        stop_btn=findViewById(R.id.stop_service);
        bind_btn=findViewById(R.id.bind_service);
        onunbind_btn=findViewById(R.id.onbind_service);
        start_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);
        bind_btn.setOnClickListener(this);
        onunbind_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.start_service:
                Intent startintent=new Intent(this,MyService.class);
                startService(startintent);
                break;
            case R.id.stop_service:
                Intent stopintent=new Intent(this,MyService.class);
                stopService(stopintent);
                break;
            case R.id.bind_service:
                Intent bindintent=new Intent(this,MyService.class);
                bindService(bindintent, conn,BIND_AUTO_CREATE);
                break;
            case R.id.onbind_service:
                unbindService(conn);//conn要申请为全局变量
                break;
        }
    }
}
