import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
public class RSSManager {
    URLConnection url;
    SyndFeedInput syndFeedInput;
    SyndFeedOutput syndFeedOutput;
    InputStream inputStream;
    SyndFeed syndFeed;
    GuildMessageReceivedEvent event;
    String feedString;


    public RSSManager(String url, GuildMessageReceivedEvent event) {
        this.event = event;
        boolean ok = false;
        try {
            this.url = new URL(url).openConnection();
            inputStream = new URL(url).openConnection().getInputStream();
            if ("gzip".equals(this.url.getContentEncoding())) {
                inputStream = new GZIPInputStream(inputStream);
            }
            InputSource inputSource = new InputSource(inputStream);
            syndFeedInput = new SyndFeedInput();
            syndFeed = syndFeedInput.build(inputSource);
            ok = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }
        List res = syndFeed.getEntries();
        for(int i=0; i<3;i++){
            event.getChannel().sendMessage(((SyndEntryImpl) res.get(i)).getLink()).queue();
        }
    }
}
