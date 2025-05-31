package edu.sfsu.nyt.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class stores the data for the RSS feed.
 * This data includes the feed's title and publication date
 * as well as an ArrayList object that stores RSSItem objects.
 * Most of the code for this class is standard Java code that lets you set the get data.
 * As a result, you shouldn't have much trouble understanding it.
 */
public class RSSFeedModel {
    private String title = null;
    private String link = null;
    private String language = null;
    private String copyright = null;
    private String lastBuildDate = null;
    private String pubDate = null;
    private ArrayList<RSSItemModel> items;

    private final SimpleDateFormat dateInFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    public RSSFeedModel() {
        items = new ArrayList<RSSItemModel>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubData) {
        this.pubDate = pubData;
    }

    public ArrayList<RSSItemModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<RSSItemModel> items) {
        this.items = items;
    }

    /**
     * The code within the getPubDateMillis method uses the SimpleDateFormat
     * object to parse the string for the date into a Date object.
     * Here, the parse method accepts a string in this format:
     * Fri, 10 Dec 2014 14:11:33 EST
     */
    public long getPubDateMillis() {
        try {
            Date date = dateInFormat.parse(pubDate.trim());
            return date.getTime();
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int addItem(RSSItemModel item) {
        items.add(item);
        return items.size();
    }

    public RSSItemModel getItem(int index) {
        return items.get(index);
    }

    public ArrayList<RSSItemModel> getAllItems() {
        return items;
    }
}