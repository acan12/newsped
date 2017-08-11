package com.toped.app.newsped.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.toped.app.newsped.MainActivity;
import com.toped.app.newsped.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import support.adapter.SourceAdapter;
import support.dao.SourceDao;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class SourceListFragment extends BaseFragment {

    @BindView(R.id.list_sources)
    RecyclerView recyclerView;

    private final FragmentManager fm;


    public SourceListFragment(FragmentManager fm) {
        this.fm = fm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_source_list, null, false);
        ButterKnife.bind(this, layout);

        SourceDao.fetchSources(this);

        return layout;
    }



    public void callbackResponse(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                SourceAdapter sourceAdapter = new SourceAdapter(SourceDao.getSources(getContext()), (MainActivity) getActivity());
                recyclerView.setAdapter(sourceAdapter);

            }
        });

    }


}
