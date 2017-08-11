package com.toped.app.newsped;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class BaseActivity extends AppCompatActivity {

    public void showFragment(Fragment fragment) {

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack("main");
            fragmentTransaction.commit();
        }
    }
}
