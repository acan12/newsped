package com.toped.app.newsped;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class ArticleDetailActivity extends BaseActivity {
    @BindView(R.id.webview_article_content)
    WebView webviewArticleContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("article_url");
        if (url.isEmpty()) return; // checking if url not exist

        getSupportActionBar().setTitle(url);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webviewArticleContent.getSettings().setBuiltInZoomControls(true);
        webviewArticleContent.loadUrl(url);

    }
}
