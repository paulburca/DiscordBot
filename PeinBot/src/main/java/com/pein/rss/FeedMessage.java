package com.pein.rss;

import java.util.Date;

public class FeedMessage {
    private final String entryTitle;
    private final String entryDescription;
    private final String entryLink;
    private final String entryAuthor;
    private Date entryPublishedDate;

    public FeedMessage(String title, String description, String link, String author, Date publishedDate) {
        this.entryTitle = title;
        this.entryDescription = description;
        this.entryLink = link;
        this.entryAuthor = author;
        this.setEntryPublishedDate(publishedDate);
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public String getEntryLink() {
        return entryLink;
    }

    public void setEntryPublishedDate(Date entryPublishedDate) {
        this.entryPublishedDate = entryPublishedDate;
    }

    @Override
    public String toString() {
        return "FeedMessage{" +
                "entryTitle='" + entryTitle + '\'' +
                ", entryDescription='" + entryDescription + '\'' +
                ", entryLink='" + entryLink + '\'' +
                ", entryAuthor='" + entryAuthor + '\'' +
                ", entryPublishedDate=" + entryPublishedDate +
                '}';
    }
}
