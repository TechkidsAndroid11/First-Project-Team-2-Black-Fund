package com.example.admins.blackfund.activities.lockviewactivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.admins.blackfund.R;
import com.example.admins.blackfund.activities.MainActivity;

import java.util.List;

/**
 * Created by Admins on 10/30/2017.
 */

public class InputPasswordActivity extends AppCompatActivity {
    private static final String TAG = "InputPasswordActivity";
    PatternLockView mPatternLockView;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_input_password);
        SharedPreferences sharedPreferences = getSharedPreferences("PRESS", 0);
        password = sharedPreferences.getString("password", "0");
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                Log.d(TAG, "onComplete: ");
                if(password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                MainActivity.checkPass=false;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                    Toast.makeText(InputPasswordActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                }
            }

            @Override
            public void onCleared() {

            }
        });
    }

}
