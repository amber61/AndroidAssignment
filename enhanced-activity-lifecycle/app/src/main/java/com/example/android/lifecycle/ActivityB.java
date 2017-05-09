/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.lifecycle.util.StatusTracker;
import com.example.android.lifecycle.util.Utils;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityB extends Activity {

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    private static int onCreateCount;
    private static int onStartCount;
    private static int onRestartCount;
    private static int onResumeCount;
    private static int onPauseCount;
    private static int onStopCount;
    private static int onDestroyCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mActivityName = getString(R.string.activity_b_label);
        mStatusView = (TextView)findViewById(R.id.status_view_b);
        mStatusAllView = (TextView)findViewById(R.id.status_view_all_b);
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_create) + (++onCreateCount));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_start)+(++onStartCount));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart)+(++onRestartCount));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume)+(++onResumeCount));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause)+(++onPauseCount));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop) + (++onStopCount));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy) + (++onDestroyCount));
        onCreateCount=0;
        onStartCount=0;
        onRestartCount=0;
        onResumeCount=0;
        onPauseCount=0;
        onStopCount=0;
        onDestroyCount=0;
    }

    static final String STATE_ONCREATE = "onCreate";
    static final String STATE_ONSTART = "onStart";
    static final String STATE_ONRESTART = "onRestart";
    static final String STATE_ONRESUME = "onResume";
    static final String STATE_ONPAUSE = "onPause";
    static final String STATE_ONSTOP = "onStop";
    static final String STATE_ONDESTROY = "onDestroy";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_ONCREATE, onCreateCount);
        savedInstanceState.putInt(STATE_ONSTART, onStartCount);
        savedInstanceState.putInt(STATE_ONRESTART, onRestartCount);
        savedInstanceState.putInt(STATE_ONRESUME, onResumeCount);
        savedInstanceState.putInt(STATE_ONPAUSE, onPauseCount);
        savedInstanceState.putInt(STATE_ONSTOP, onStopCount);
        savedInstanceState.putInt(STATE_ONDESTROY, onDestroyCount);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        onCreateCount = savedInstanceState.getInt(STATE_ONCREATE)+1;
        onStartCount = savedInstanceState.getInt(STATE_ONSTART)+1;
        onRestartCount = savedInstanceState.getInt(STATE_ONRESTART);
        onResumeCount = savedInstanceState.getInt(STATE_ONRESUME);
        onPauseCount = savedInstanceState.getInt(STATE_ONPAUSE);
        onStopCount = savedInstanceState.getInt(STATE_ONSTOP);
        onDestroyCount = savedInstanceState.getInt(STATE_ONDESTROY);
    }

    public void startDialog(View v) {
        Intent intent = new Intent(ActivityB.this, DialogActivity.class);
        startActivity(intent);
    }

    public void startActivityA(View v) {
        Intent intent = new Intent(ActivityB.this, ActivityA.class);
        startActivity(intent);
    }

    public void startActivityC(View v) {
        Intent intent = new Intent(ActivityB.this, ActivityC.class);
        startActivity(intent);
    }

    public void finishActivityB(View v) {
        ActivityB.this.finish();
    }
}
