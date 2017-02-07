package org.bts.atry.dummyservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BoundService mBoundService;
    private boolean mBoundServiceStarted = false;

    private TextView mTVInfo;
    private EditText mETMain;

    private ServiceConnection mServConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder serviceInfo) {
            mBoundService = new BoundService(serviceInfo);
            mBoundServiceStarted = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBoundServiceStarted = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons Main Activity
        final Button btnStartService = (Button) findViewById(R.id.btn_main_activity_start_service);
        final Button btnSubmitET = (Button) findViewById(R.id.btn_main_activity_submit_et);
        final Button btnStartBoundService = (Button) findViewById(R.id.btn_main_activity_bound_start_service);
        final Button btnStartIntentService = (Button) findViewById(R.id.btn_main_activity_intent_start_service);
        btnStartService.setOnClickListener(this);
        btnSubmitET.setOnClickListener(this);
        btnStartBoundService.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);

        //EditText
        this.mTVInfo = (TextView) findViewById(R.id.tv_main_activity);

        //TextView
        this.mETMain = (EditText) findViewById(R.id.et_main_activity);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mBoundServiceStarted) {
            this.mBoundService.unbindService(this.mServConnection);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Intent boundServiceIntent = new Intent(this, BoundService.class);
        //this.mBoundService.stopService(boundServiceIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_activity_start_service:
                Log.i(MainActivity.TAG, "Start Service");
                Intent intent = new Intent(this, CustomStartService.class);
                startService(intent);
                break;
            case R.id.btn_main_activity_bound_start_service:
                Log.i(MainActivity.TAG, "Start Bound Service");
                Intent boundServiceIntent = new Intent(this, BoundService.class);
                startService(boundServiceIntent);
                bindService(boundServiceIntent, mServConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_main_activity_intent_start_service:
                Log.i(MainActivity.TAG, "Start Intent Service");
                Intent intent2 = new Intent(this, CustomIntentService.class);
                startService(intent2);
                break;
            case R.id.btn_main_activity_submit_et:
                Log.i(MainActivity.TAG, "Display status btn is clicked");

                if(mBoundServiceStarted && mETMain.getText().length() == 0 ) {
                    int num = this.mBoundService.getRandomNumber();
                    mTVInfo.setText("Random number: " + num);
                } else if(mBoundServiceStarted) {
                    String inputText = String.valueOf(mETMain.getText());
                    String refactoredText = this.mBoundService.refactorText(inputText);
                    mTVInfo.setText(refactoredText);
                } else {
                    mTVInfo.setText("First start bound service");
                }

                break;

            default:
                Log.w(MainActivity.TAG, "OnclickListener not matched");
                break;
        }
    }
}
