import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStreamReader;

public class RSSManager {
    URL url;
    SyndFeedInput syndFeedInput;
    SyndFeed syndFeed;
    public RSSManager(String url){
        boolean ok = false;
        try {
            this.url= new URL(url);
            syndFeedInput = new SyndFeedInput();
            syndFeed = syndFeedInput.build(new XmlReader(new File(url)));
            ok=true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }
        if (!ok) {
            System.out.println();
            System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
            System.out.println("The first parameter must be the URL of the feed to read.");
            System.out.println();
        }

    }
}
