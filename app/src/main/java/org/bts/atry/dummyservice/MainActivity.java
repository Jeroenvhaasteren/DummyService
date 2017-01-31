package org.bts.atry.dummyservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTVInfo;
    private EditText mETMain;

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_activity_start_service:
                Log.i(MainActivity.TAG, "Start Service");
                Intent intent = new Intent(this, CustomStartService.class);
                startService(intent);
                break;
            case R.id.btn_main_activity_bound_start_service:
                Log.i(MainActivity.TAG, "Start Bound Service");
                break;
            case R.id.btn_main_activity_intent_start_service:
                Log.i(MainActivity.TAG, "Start Intent Service");
                Intent intent2 = new Intent(this, CustomIntentService.class);
                startService(intent2);
                break;
            case R.id.btn_main_activity_submit_et:
                Log.i(MainActivity.TAG, "Start Submit Service");
                break;

            default:
                Log.w(MainActivity.TAG, "OnclickListener not matched");
                break;
        }
    }
}
