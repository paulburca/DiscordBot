import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
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
    URL url;
    SyndFeedInput syndFeedInput;
    SyndFeedOutput syndFeedOutput;
    InputStream inputStream;
    SyndFeed syndFeed;
    GuildMessageReceivedEvent event;
    String feedString;
    public void addFooter(SyndEntry entry){
    }

    private Object createFooter(String original, String link, String title) {
        return 1;
    }

    public RSSManager(String url, GuildMessageReceivedEvent event){
        this.event= event;
        boolean ok = false;
        try {
            this.url= new URL(url);
            inputStream = new URL(url).openConnection().getInputStream();
            syndFeedInput = new SyndFeedInput();
            syndFeed = syndFeedInput.build(new XmlReader(this.url));
            ok=true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        if(ok){

            for (Object o : syndFeed.getEntries()) {

                SyndEntry entry = (SyndEntry) o;
                addFooter(entry);

            }
            StringWriter writer = new StringWriter();

            try {
                syndFeedOutput.output(syndFeed, writer);
            } catch (IOException | FeedException e) {
                e.printStackTrace();
            }

            feedString=writer.toString();
        }
    }

}
