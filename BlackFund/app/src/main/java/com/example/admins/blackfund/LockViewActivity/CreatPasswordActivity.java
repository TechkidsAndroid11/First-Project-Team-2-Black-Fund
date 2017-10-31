package com.example.admins.blackfund.LockViewActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.admins.blackfund.R;
import com.example.admins.blackfund.activities.MainActivity;

import java.util.List;

/**
 * Created by Admins on 10/30/2017.
 */

public class CreatPasswordActivity extends AppCompatActivity{
    private static final String TAG = "CreatPasswordActivity";
    PatternLockView mPatternLockView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatpassword);
        Log.d(TAG, "onCreate: ");
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
                SharedPreferences sharedPreferences= getSharedPreferences("PRESS", 0);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("password",  PatternLockUtils.patternToString(mPatternLockView, pattern));
                editor.apply();

                Intent intent= new Intent(getApplicationContext(), InputPasswordActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCleared() {

            }
        });
    }
}
