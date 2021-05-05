import java.util.ArrayList;
import java.util.List;

public class Feed {
    private final String feedTitle;
    private final String feedLink;
    private final String feedDescription;
    private final String feedLanguage;
    private final String feedCopyright;
    private final String feedPublicationDate;

    private final List<FeedMessage> entries = new ArrayList<FeedMessage>();


    public Feed(String feedTitle, String feedLink, String feedDescription,
                String feedLanguage, String feedCopyright, String feedPublicationDate) {
        this.feedTitle = feedTitle;
        this.feedLink = feedLink;
        this.feedDescription = feedDescription;
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

    public String getFeedDescription() {
        return feedDescription;
    }

    public String getFeedLanguage() {
        return feedLanguage;
    }

    public String getFeedCopyright() {
        return feedCopyright;
    }

    public String getFeedPublicationDate() {
        return feedPublicationDate;
    }

    public List<FeedMessage> getEntries() {
        return entries;
    }
}
