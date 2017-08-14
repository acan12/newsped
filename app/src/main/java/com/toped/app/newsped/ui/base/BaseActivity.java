package com.toped.app.newsped.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.toped.app.newsped.R;
import com.toped.app.newsped.presentation.bus.MessageEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected EventBus bus = EventBus.getDefault();

    public EventBus getBus() {
        return bus;
    }

    public void showFragment(Fragment fragment) {

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        bus.unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
    }

}
