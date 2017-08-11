package com.toped.app.newsped.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.toped.app.newsped.MainActivity;
import com.toped.app.newsped.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import support.adapter.ArticleAdapter;
import support.component.KeyboardComponent;
import support.dao.ArticleDao;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class ArticleListFragment extends BaseFragment {

    @BindView(R.id.list_articles)
    RecyclerView recyclerView;
    @BindView(R.id.search_view)
    EditText searchView;
    @BindView(R.id.search_clear)
    ImageView searchClear;

    private final String sourceId;
    private final String sourceName;
    private ArticleAdapter adapter;
    private ActionBar actionBar;

    public ArticleListFragment(String sourceId, String sourceName) {
        this.sourceId = sourceId;
        this.sourceName = sourceName;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_article_list, null, false);
        ButterKnife.bind(this, layout);

        ArticleDao.fetchArticles(sourceId, this);

        actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(sourceName);

        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    KeyboardComponent.hideKeyboard(getActivity());

                    performSearchResult(searchView.getText().toString());
                }
                return false;
            }

            private void performSearchResult(String text) {
                List data = ArticleDao.getArticles(getContext(), text);
                adapter = new ArticleAdapter(data, (MainActivity) getActivity());
                recyclerView.setAdapter(adapter);
                recyclerView.invalidate();

                Toast.makeText(getContext(), "Search \"" + searchView.getText() + "\", " + data.size() + " item found", Toast.LENGTH_LONG).show();

            }
        });

        searchClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setText("");
                KeyboardComponent.hideKeyboard(getActivity());
            }
        });
        return layout;
    }

    @Override
    public void callbackFailure() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Network problem", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void callbackResponse() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                adapter = new ArticleAdapter(ArticleDao.getArticles(getContext()), (MainActivity) getActivity());
                recyclerView.setAdapter(adapter);

            }
        });
    }

    public void onClearSearch(View v) {
        searchView.setText("");
    }
}
