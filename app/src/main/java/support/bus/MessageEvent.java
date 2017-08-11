package support.bus;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.toped.app.newsped.R;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class MessageEvent {

    private Fragment fragment;
    private FragmentManager fm;

    public MessageEvent(Fragment fragment, FragmentManager fm) {
        this.fragment = fragment;
        this.fm = fm;
    }

    public void showFragment(){
        if (fragment != null) {
            FragmentManager fragmentManager = fm;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commit();
        }
    }
}
