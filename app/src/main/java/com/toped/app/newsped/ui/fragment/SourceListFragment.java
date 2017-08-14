package com.toped.app.newsped.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toped.app.newsped.MainActivity;
import com.toped.app.newsped.R;
import com.toped.app.newsped.model.dao.SourceDao;
import com.toped.app.newsped.ui.adapter.SourceAdapter;
import com.toped.app.newsped.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import support.utils.UiUtils;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class SourceListFragment extends BaseFragment {

    @BindView(R.id.list_sources)
    RecyclerView recyclerView;

    private final FragmentManager fm;
    private ActionBar actionBar;
    private ProgressDialog dialog;


    public SourceListFragment(FragmentManager fm) {
        this.fm = fm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_source_list, null, false);
        ButterKnife.bind(this, layout);

//        actionBar = ((MainActivity) getActivity()).getSupportActionBar();
//        actionBar.setTitle(getResources().getString(R.string.app_name));

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UiUtils.isActivityRunning(MainActivity.class, getContext()))
            SourceDao.fetchSources(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }

    public void callbackResponse(final ProgressDialog dialog) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                SourceAdapter sourceAdapter = new SourceAdapter(SourceDao.getSources(getContext()), (MainActivity) getActivity());
                recyclerView.setAdapter(sourceAdapter);

            }
        });

    }


}
