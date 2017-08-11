package com.toped.app.newsped;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.toped.app.newsped.fragment.ArticleListFragment;
import com.toped.app.newsped.fragment.SourceListFragment;

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
    }


    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if(count ==0)
            super.onBackPressed();
        else
            getFragmentManager().popBackStack();
    }
}
