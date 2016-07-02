package com.microlands.android.nerdlauncher;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class NerdLauncherActivity extends SingleFragmentActivity {

    private static final String TAG = "NerdLauncherActivity";

    @Override
    protected Fragment createFragment() {
        Log.i(TAG, "createFragment()...");
        return NerdLauncherFragment.newInstance();
    }
}
