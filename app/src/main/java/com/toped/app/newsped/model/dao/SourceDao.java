package com.toped.app.newsped.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.toped.app.newsped.model.Source;
import com.toped.app.newsped.model.api.Api;
import com.toped.app.newsped.model.dao.parser.SourceParser;
import com.toped.app.newsped.ui.base.BaseFragment;
import com.toped.app.newsped.ui.component.DialogComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class SourceDao extends BaseDao {

    protected SourceDao(Context context) {
        super(context);
    }

    public static SourceDao instanceObject(android.content.Context context) {
        return new SourceDao(context);
    }

    public static void fetchSources(final BaseFragment fragment) {
        final ProgressDialog dialog = DialogComponent.setupLoadingProgress(fragment.getContext());
        Api.getNewsSources(fragment.getActivity(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                fragment.callbackFailure(dialog);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final StringBuffer json = new StringBuffer(response.body().string());

                    SourceParser.setSource(json.toString(), fragment.getActivity());

                    fragment.callbackResponse(dialog);
                }
            }
        });
    }



    public static void saveSource(SourceParser.Holder holder, android.content.Context context) {
        instanceObject(context);

        // if already exist not need create new object instance.
        if (getObjectByKeyRealm("id", holder.id, Source.class).size() > 0) return;

        Source source = new Source();
        source.setId(holder.id);
        source.setName(holder.name);
        source.setDescription(holder.description);
        source.setUrl(holder.url);
        source.setCategory(holder.category);
        source.setLanguage(holder.language);
        source.setCountry(holder.country);
        source.setUrlsToLogosSmall(holder.urlsToLogosSmall);
        source.setUrlsToLogosMedium(holder.urlsToLogosMedium);
        source.setUrlsToLogosLarge(holder.urlsToLogosLarge);
        source.setSortBysAvailable(holder.sortBysAvailable);
        saveToRealm(source);
    }

    public static List<Source> getSources(android.content.Context context) {
        instanceObject(context);
        RealmResults<Source> data;
        try {
            data = (RealmResults<Source>) getObjectsRealm(Source.class).sort("name");

        } catch (Exception e) {
            data = null;
        }


        ArrayList<Source> sources = new ArrayList<Source>();
        for (Source item : data) {
            sources.add(item);
        }

        return sources;
    }
}
