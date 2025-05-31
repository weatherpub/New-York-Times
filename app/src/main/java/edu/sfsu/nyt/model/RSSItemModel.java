package edu.sfsu.nyt.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class stores the data for each item in the RSS feed.
 * This data includes the item's title, description, link, and publication date.
 * Most of the code for this clas is standard java code that lets you set and get this data.
 */
public class RSSItemModel {
    private String title = null;
    private String link = null;
    private String description = null;
    private String pubDate = null;

    private final SimpleDateFormat dateOutFormat = new SimpleDateFormat("EEEE h:mm a (MMM d)");
    private final SimpleDateFormat dateInFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public SimpleDateFormat getDateOutFormat() {
        return dateOutFormat;
    }

    public SimpleDateFormat getDateInFormat() {
        return dateInFormat;
    }

    public String getPubDateFormatted() {
        try {
            Date date = dateInFormat.parse(pubDate.trim());
            return dateOutFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}