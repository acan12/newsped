package support.api;

import android.content.Context;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by arysuryawan on 1/25/17.
 */

class BaseApi {

    private static HttpUtilApi httpUtilApi;
    private static final String apiDomain = "";

    protected static HttpUtilApi getHttpUtilApi() {
        if (httpUtilApi == null) {

            httpUtilApi = new HttpUtilApi(apiDomain);
        }
        return httpUtilApi;
    }

    static class HttpUtilApi {
        private String url;
        private OkHttpClient client;

        private Call call;
        private Request request;
        private RequestBody body;
        private StringBuffer result;

        public enum Method {GET, POST, PUT, DELETE}


        public HttpUtilApi(String url) {
            this.url = url;
        }

        private OkHttpClient setupClient() {
            if (client == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();

                this.client = builder.build();
            }
            return this.client;
        }

        private static final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");


        // call this if not want to use signature
        public StringBuffer call(String apiUrl, String paramsJson, Method method, Context context, Callback callback) throws IOException {
            setupClient();
            String domain = url.concat(apiUrl);

            switch (method) {
                case GET:
                    body = RequestBody.create(JSON, paramsJson);
                    request = new Request.Builder()
                            .url(domain)
                            .get()
                            .build();

                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                case POST:
                    body = RequestBody.create(JSON, paramsJson);
                    request = new Request.Builder()
                            .url(domain)
                            .post(body)
                            .build();
                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                case PUT:
                    body = RequestBody.create(JSON, paramsJson);
                    request = new Request.Builder()
                            .url(domain)
                            .put(body)
                            .build();
                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                case DELETE:
                    body = RequestBody.create(JSON, paramsJson);
                    request = new Request.Builder()
                            .url(domain)
                            .delete(body)
                            .build();
                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                default:
                    result = new StringBuffer("");


            }
            return result;
        }

    }

}
