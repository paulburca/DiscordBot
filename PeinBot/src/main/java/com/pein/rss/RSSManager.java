package com.pein.rss;

import com.pein.bot.BotLauncher;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.xml.sax.InputSource;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class RSSManager {
    URLConnection url;
    SyndFeedInput syndFeedInput;
    InputStream inputStream;
    SyndFeed syndFeed;
    GuildMessageReceivedEvent event;

    public RSSManager(String url, GuildMessageReceivedEvent event, int numberOfEntries) {
        this.event = event;

        try {
            this.url = new URL(url).openConnection();
            inputStream = this.url.getInputStream();
            if ("gzip".equals(this.url.getContentEncoding())) {
                inputStream = new GZIPInputStream(inputStream);
            }
            InputSource inputSource = new InputSource(inputStream);
            syndFeedInput = new SyndFeedInput();
            syndFeed = syndFeedInput.build(inputSource);
        } catch (Exception exception) {
            EmbedBuilder error = new EmbedBuilder();
            error.setColor(Color.red);
            error.setDescription(BotLauncher.getMessages().getString("error"));
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(error.build()).queue();
            return;
        }
        List<SyndEntry> result = syndFeed.getEntries();
        for (int i = 0; i < numberOfEntries; i++) {
            FeedMessage feedMessage = new FeedMessage((result.get(i)).getTitle(),
                    (result.get(i)).getDescription().getValue(),
                    (result.get(i)).getLink(),
                    (result.get(i)).getAuthor(),
                    (result.get(i)).getPublishedDate());
            EmbedBuilder feedEmbed = new EmbedBuilder();
            feedEmbed.setTitle(feedMessage.getEntryTitle());
            feedEmbed.setDescription(BotLauncher.getMessages().getString("check.article") + feedMessage.getEntryLink() + ")\n"
                    + feedMessage.getEntryDescription().substring(0, Math.min(feedMessage.getEntryDescription().length() - 1, 1020)) + "...");
            feedEmbed.setColor(0xed1313);
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(feedEmbed.build()).queue();
            feedEmbed.clear();
        }
    }
}
