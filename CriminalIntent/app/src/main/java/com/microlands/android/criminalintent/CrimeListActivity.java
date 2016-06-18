/*
 * Copyright (c) 2016. Microlands Systems
 *
 * {project}  Copyright (C) {year}  {fullname}
 * This program comes with ABSOLUTELY NO WARRANTY.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions.
 */

package com.microlands.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by luisvivero on 6/17/16.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
