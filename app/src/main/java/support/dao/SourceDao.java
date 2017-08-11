package support.dao;

import android.content.Context;

import com.toped.app.newsped.fragment.BaseFragment;
import com.toped.app.newsped.model.Source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import support.api.Api;
import support.parser.SourceParser;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class SourceDao extends BaseDao {

    protected SourceDao(Context context) {
        super(context);
    }

    public static SourceDao instanceObject(Context context) {
        return new SourceDao(context);
    }

    public static void fetchSources(final BaseFragment fragment) {

        Api.getNewsSources(fragment.getActivity(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final StringBuffer json = new StringBuffer(response.body().string());

                    SourceParser.setSource(json.toString(), fragment.getActivity());

                    fragment.callbackResponse();
                }
            }
        });
    }

    public static void saveSource(SourceParser.Holder holder, Context context) {
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

    public static List<Source> getSources(Context context) {
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
