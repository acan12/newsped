package support.api;

import android.content.Context;

import java.io.IOException;

import okhttp3.Callback;
import support.config.IConfig;

/**
 * Created by arysuryawan on 1/26/17.
 */

public class Api extends BaseApi {

    public static void getNewsSources(Context context, Callback callback) {
        HttpUtilApi api = getHttpUtilApi();

        String url = IConfig.HOST_API_NEWS_URL.concat(IConfig.API_SOURCE_URL).concat("?language=en");

        try {
            api.call(url, "", HttpUtilApi.Method.GET, context, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getNewsArticle(String sourceId, Context context, Callback callback) {
        HttpUtilApi api = getHttpUtilApi();

        String url = IConfig.HOST_API_NEWS_URL.concat(IConfig.API_ARTICLE_URL).concat("?source=" + sourceId + "&sortBy=latest&apiKey=" + IConfig.API_KEY);

        try {
            api.call(url, "", HttpUtilApi.Method.GET, context, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
