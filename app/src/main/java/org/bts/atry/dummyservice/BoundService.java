package org.bts.atry.dummyservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class BoundService extends Service {

    private static final String TAG = BoundService.class.getSimpleName();

    private IBinder mBinder = new Binder();


    public BoundService() {

        Log.i(BoundService.TAG, "Boundservice empty constructor");

    }

    public BoundService(IBinder serviceInfo) {

        Log.i(BoundService.TAG, "Boundservice filled constructor");

        this.mBinder = serviceInfo;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(BoundService.TAG, "onCreate");

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        Log.i(BoundService.TAG, "onBind");

        return this.mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.i(BoundService.TAG, "onUnbind");

        return super.onUnbind(intent);
    }

    public int getRandomNumber() {
        Random randomGenerator = new Random();
        int num = randomGenerator.nextInt();
        return num;
    }

    public String refactorText(String text) {
        String refactoredString = new StringBuilder(text).reverse().toString();
        return refactoredString;
    }


}
