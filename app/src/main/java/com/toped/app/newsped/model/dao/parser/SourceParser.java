package com.toped.app.newsped.model.dao.parser;

import android.content.Context;
import android.text.TextUtils;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import com.toped.app.newsped.model.Source;

import java.util.List;

import support.config.IConfig;
import com.toped.app.newsped.model.dao.SourceDao;


/**
 * Created by arysuryawan on 8/8/17.
 */

public class SourceParser {
    public static List<Source> setSource(String results, Context context) {
        JsonValue jsonValue = Json.parse(results).asObject().get(IConfig.API_ROOT_SOURCES);
        JsonArray jsonArray = jsonValue.asArray();

        for (JsonValue obj : jsonArray) {
            String id = "" + obj.asObject().getString("id", "");
            String name = obj.asObject().getString("name", "");
            String description = obj.asObject().getString("description", "");
            String url = obj.asObject().getString("url", "");
            String category = obj.asObject().getString("category", "");
            String language = obj.asObject().getString("language", "");
            String country = obj.asObject().getString("country", "");
            String urlsToLogosSmall = obj.asObject().get("urlsToLogos")
                    .asObject().getString("small", "");
            String urlsToLogosMedium = obj.asObject().get("urlsToLogos")
                    .asObject().getString("medium", "");
            String urlsToLogosLarge = obj.asObject().get("urlsToLogos")
                    .asObject().getString("large", "");
            String sortBysAvailable = TextUtils.join(",", obj.asObject().get("sortBysAvailable").asArray());

            SourceParser.Holder holder = new SourceParser.Holder(
                    id, name,
                    description,
                    url,
                    category,
                    language,
                    country,
                    urlsToLogosSmall,
                    urlsToLogosMedium,
                    urlsToLogosLarge,
                    sortBysAvailable);
            SourceDao.saveSource(holder, context);
        }

        return SourceDao.getSources(context);
    }

    public static class Holder {
        public String id;
        public String name;
        public String description;
        public String url;
        public String category;
        public String language;
        public String country;
        public String urlsToLogosSmall;
        public String urlsToLogosMedium;
        public String urlsToLogosLarge;
        public String sortBysAvailable;

        public Holder(String id, String name, String description, String url,
                      String category, String language,
                      String country,
                      String urlsToLogosSmall,
                      String urlsToLogosMedium,
                      String urlsToLogosLarge,
                      String sortBysAvailable) {

            this.id = id;
            this.name = name;
            this.description = description;
            this.url = url;
            this.category = category;
            this.language = language;
            this.country = country;
            this.urlsToLogosSmall = urlsToLogosSmall;
            this.urlsToLogosMedium = urlsToLogosMedium;
            this.urlsToLogosLarge = urlsToLogosLarge;
            this.sortBysAvailable = sortBysAvailable;
        }
    }

}
