package support.parser;

import android.content.Context;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

import support.config.IConfig;
import support.dao.ArticleDao;

/**
 * Created by arysuryawan on 8/8/17.
 */
public class ArticleParser {

    public static void setArticle(String results, Context context) {
        JsonValue jsonValue = Json.parse(results).asObject().get(IConfig.API_ROOT_ARTICLES);
        JsonArray jsonArray = jsonValue.asArray();

        for (JsonValue obj : jsonArray) {
            String author = "" + obj.asObject().getString("author", "");
            String title = obj.asObject().getString("title", "");
            String description = obj.asObject().getString("description", "");
            String url = obj.asObject().getString("url", "");
            String urlToImage = obj.asObject().getString("urlToImage", "");
            String publishedAt = obj.asObject().getString("publishedAt", "");

            Holder holder = new Holder(author, title,
                    description,
                    url,
                    urlToImage,
                    publishedAt);
            ArticleDao.saveArticle(holder, context);
        }

    }

    public static class Holder {
        public String author;
        public String title;
        public String description;
        public String url;
        public String urlToImage;
        public String publishedAt;

        public Holder(String author, String title, String description, String url, String urlToImage, String publishedAt) {

            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.urlToImage = urlToImage;
            this.publishedAt = publishedAt;
        }
    }

}
