package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int i;
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.d("MyService","onCreate executed");
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                super.run();
                for(i=0;i<100;i++)
                {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.d("MyService","onDestroy executed");
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("MyService","onBind executed");
      return new MyBinder();
    }
    //对于onBind方法而言，要求返回IBinder对象，我们定义一个内部类，集成Binder类
    class MyBinder extends Binder{
        //实现进度监控
        public int getProcess(){
            return i;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MyService","onunbind executed");
        return super.onUnbind(intent);
    }
}
