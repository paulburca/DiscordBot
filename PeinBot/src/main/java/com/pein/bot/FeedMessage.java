package com.pein.bot;

import java.util.Date;

public class FeedMessage {
    private String entryTitle;
    private String entryDescription;
    private String entryLink;
    private String entryAuthor;
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

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public String getEntryLink() {
        return entryLink;
    }

    public void setEntryLink(String entryLink) {
        this.entryLink = entryLink;
    }

    public String getEntryAuthor() {
        return entryAuthor;
    }

    public void setEntryAuthor(String entryAuthor) {
        this.entryAuthor = entryAuthor;
    }

    public Date getEntryPublishedDate() {
        return entryPublishedDate;
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
