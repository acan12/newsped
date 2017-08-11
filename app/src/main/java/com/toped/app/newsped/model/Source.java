package com.toped.app.newsped.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class Source extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    private String urlsToLogosSmall;
    private String urlsToLogosMedium;
    private String urlsToLogosLarge;
    private String sortBysAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrlsToLogosSmall() {
        return urlsToLogosSmall;
    }

    public void setUrlsToLogosSmall(String urlsToLogosSmall) {
        this.urlsToLogosSmall = urlsToLogosSmall;
    }

    public String getUrlsToLogosMedium() {
        return urlsToLogosMedium;
    }

    public void setUrlsToLogosMedium(String urlsToLogosMedium) {
        this.urlsToLogosMedium = urlsToLogosMedium;
    }

    public String getUrlsToLogosLarge() {
        return urlsToLogosLarge;
    }

    public void setUrlsToLogosLarge(String urlsToLogosLarge) {
        this.urlsToLogosLarge = urlsToLogosLarge;
    }

    public String getSortBysAvailable() {
        return sortBysAvailable;
    }

    public void setSortBysAvailable(String sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }
}
