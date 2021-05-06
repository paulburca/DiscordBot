import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PredefinedFeed {
    private final String feedTitle;
    private final String feedLink;
    private final String feedCategory;
    private final String feedLanguage;
    private final String feedCopyright;
    private final LocalDate feedPublicationDate;

    private final List<FeedMessage> entries = new ArrayList<FeedMessage>();


    public PredefinedFeed(String feedTitle, String feedLink, String feedCategory,
                          String feedLanguage, String feedCopyright, LocalDate feedPublicationDate) {
        this.feedTitle = feedTitle;
        this.feedLink = feedLink;
        this.feedCategory = feedCategory;
        this.feedLanguage = feedLanguage;
        this.feedCopyright = feedCopyright;
        this.feedPublicationDate = feedPublicationDate;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public String getFeedLink() {
        return feedLink;
    }

    public String getFeedCategory() {
        return feedCategory;
    }

    public String getFeedLanguage() {
        return feedLanguage;
    }

    public String getFeedCopyright() {
        return feedCopyright;
    }

    public LocalDate getFeedPublicationDate() {
        return feedPublicationDate;
    }

    public List<FeedMessage> getEntries() {
        return entries;
    }
}
