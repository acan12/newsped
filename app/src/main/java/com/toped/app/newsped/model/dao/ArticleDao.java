package com.toped.app.newsped.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.toped.app.newsped.model.Article;
import com.toped.app.newsped.model.api.Api;
import com.toped.app.newsped.model.dao.parser.ArticleParser;
import com.toped.app.newsped.ui.base.BaseFragment;
import com.toped.app.newsped.ui.component.DialogComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import io.realm.Sort;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class ArticleDao extends BaseDao {
    protected ArticleDao(Context context) {
        super(context);
    }

    public static ArticleDao instanceObject(android.content.Context context) {
        return new ArticleDao(context);
    }

    public static void fetchArticles(String sourceId, final BaseFragment fragment) {
        final ProgressDialog dialog = DialogComponent.setupLoadingProgress(fragment.getContext());
        removeAllArticles(); // clear all articles
        Api.getNewsArticle(sourceId, fragment.getActivity(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                fragment.callbackFailure(dialog);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final StringBuffer json = new StringBuffer(response.body().string());

                    ArticleParser.setArticle(json.toString(), fragment.getActivity());

                    fragment.callbackResponse(dialog);
                } else {
                    fragment.callbackFailure(dialog);
                }
            }
        });
    }

    public static void saveArticle(ArticleParser.Holder holder, android.content.Context context) {
        instanceObject(context);

        // if already exist not need create new object instance.
        if (getObjectByKeyRealm("title", holder.title, Article.class).size() > 0) return;

        Article article = new Article();
        article.setAuthor(holder.author);
        article.setTitle(holder.title);
        article.setDescription(holder.description);
        article.setUrl(holder.url);
        article.setUrlToImage(holder.urlToImage);
        article.setPublishedAt(holder.publishedAt);
        saveToRealm(article);
    }

    public static List<Article> getArticles(android.content.Context context) {
        return getArticles(context, "");
    }

    public static List<Article> getArticles(android.content.Context context, String keyword) {
        instanceObject(context);
        RealmResults<Article> data;
        try {
            data = (RealmResults<Article>) getSearchResultRealm(Article.class, keyword).sort("publishedAt", Sort.DESCENDING);

        } catch (Exception e) {
            data = null;
        }


        ArrayList<Article> articles = new ArrayList<Article>();
        for (Article item : data) {
            articles.add(item);
        }

        return articles;
    }

    public static void removeAllArticles() {
        deleteRealm(Article.class);
    }
}
