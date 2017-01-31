package org.bts.atry.dummyservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CustomIntentService extends IntentService {

    private static final String TAG = CustomIntentService.class.getSimpleName();

    public CustomIntentService() {
        super(CustomIntentService.TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(CustomIntentService.TAG, "Intent service being handeled");
        CustomStartService.waitForTime(5);
        Log.i(CustomIntentService.TAG, "Done");

    }
}
