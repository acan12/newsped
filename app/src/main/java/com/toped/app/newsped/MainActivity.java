package com.toped.app.newsped;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.toped.app.newsped.presentation.bus.MessageEvent;
import com.toped.app.newsped.presentation.di.IProgress;
import com.toped.app.newsped.ui.base.BaseActivity;
import com.toped.app.newsped.ui.fragment.SourceListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.container)
    FrameLayout fragmentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showFragment(new SourceListFragment(getSupportFragmentManager()));

        IProgress progress = App.getComponent(this).getProgressStatus();
        getSupportActionBar().setTitle(progress.showProgressText("TEST DAGGER"));

    }

    @Override
    public void onResume() {
        super.onResume();
//        MessageEvent m = new MessageEvent("Test", getSupportActionBar());
//        bus.post(m);
    }

}
