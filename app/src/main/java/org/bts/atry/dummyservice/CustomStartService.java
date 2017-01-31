package org.bts.atry.dummyservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CustomStartService extends Service {

    private static final String TAG = CustomStartService.class.getSimpleName();

    public CustomStartService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CustomStartService.TAG, "Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags , int startId) {
        Log.i(CustomStartService.TAG, "Stared");
        waitForTime(5);
        Log.i(CustomStartService.TAG, "Done");

        return Service.START_NOT_STICKY;
    }

    public static void waitForTime(int i) {
//        TEST Heavy load not elegant
//        long threshold = 1000000000;
//        for(long cnt = 0; cnt < threshold; cnt++);

        long endTime = System.currentTimeMillis() + i * 1000;
        while(System.currentTimeMillis() < endTime){

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
