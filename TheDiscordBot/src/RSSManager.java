import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class RSSManager {
    URLConnection url;
    SyndFeedInput syndFeedInput;
    InputStream inputStream;
    SyndFeed syndFeed;
    public RSSManager(String url, GuildMessageReceivedEvent event){
        boolean ok = false;
        try {
            this.url= new URL(url).openConnection();
            inputStream = new URL(url).openConnection().getInputStream();
            if("gzip".equals(this.url.getContentEncoding())){
                inputStream = new GZIPInputStream(inputStream);
            }
            syndFeedInput = new SyndFeedInput();
            InputSource inputSource = new InputSource(inputStream);
            syndFeed = syndFeedInput.build(inputSource);
            ok=true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }
        if(ok){
            for(int i=0;i<5;i++){
                event.getChannel().sendMessage((String)syndFeed.getLinks().get(i)).queue();
            }
        }
    }

}
