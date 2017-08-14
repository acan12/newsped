package com.toped.app.newsped.presentation.bus;


import android.support.v7.app.ActionBar;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class MessageEvent {

    private ActionBar actionBar;
    private String message;

    public MessageEvent(String message, ActionBar actionBar) {
        this.message = message;
        this.actionBar = actionBar;
    }

    public String getMessage() {
        if (actionBar != null) actionBar.setTitle(message);
        return message;
    }

    //    private Fragment fragment;
//    private FragmentManager fm;
//
//    public MessageEvent(Fragment fragment, FragmentManager fm) {
//        this.fragment = fragment;
//        this.fm = fm;
//    }
//
//    public void showFragment(){
//        if (fragment != null) {
//            FragmentManager fragmentManager = fm;
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container, fragment);
//            fragmentTransaction.detach(fragment);
//            fragmentTransaction.attach(fragment);
//            fragmentTransaction.commit();
//        }
//    }
}
