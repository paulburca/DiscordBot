package com.pein.bot;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
    PredefinedFeed predefinedFeed;


    public RSSManager(String url, GuildMessageReceivedEvent event, int numberOfEntries) {
        this.event = event;
        boolean ok = false;
        try {
            this.url = new URL(url).openConnection();
            inputStream = this.url.getInputStream();
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
        List<SyndEntry> res = syndFeed.getEntries();
        for (int i = 0; i < numberOfEntries; i++) {
            //event.getChannel().sendMessage(( res.get(i)).getLink()).queue();
            //System.out.println( res.get(i));
            FeedMessage feedMessage = new FeedMessage((res.get(i)).getTitle(),
                    (res.get(i)).getDescription().getValue(),
                    (res.get(i)).getLink(),
                    (res.get(i)).getAuthor(),
                    (res.get(i)).getPublishedDate());
            EmbedBuilder feedEmbed = new EmbedBuilder();
            feedEmbed.setTitle(feedMessage.getEntryTitle());
            feedEmbed.setDescription(feedMessage.getEntryDescription().substring(0,Math.min(feedMessage.getEntryDescription().length() - 1,1020)) + "...");
            feedEmbed.setColor(0xed1313);
            feedEmbed.setFooter("fill", event.getMember().getUser().getEffectiveAvatarUrl());
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(feedEmbed.build()).queue();
            feedEmbed.clear();
            //System.out.println(feedMessage);
        }
    }
}
