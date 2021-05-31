package com.pein.commands;

import com.pein.Entities.CategoryEntity;
import com.pein.Entities.FeedEntity;
import com.pein.Entities.FeedcategoryEntity;
import com.pein.bot.BotLauncher;
import com.pein.repositories.CategoryRepository;
import com.pein.repositories.FeedCategoryRepository;
import com.pein.repositories.FeedRepository;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class AddNews extends Command{
    public AddNews(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }
    URLConnection url;
    SyndFeedInput syndFeedInput;
    InputStream inputStream;
    SyndFeed syndFeed;
    CategoryRepository categoryRepository = new CategoryRepository();
    FeedRepository feedRepository = new FeedRepository();
    FeedCategoryRepository feedCategoryRepository = new FeedCategoryRepository();
    @Override
    public void run() {
        String url = getArguments()[2];
        try {
            this.url = new URL(url).openConnection();
            inputStream = this.url.getInputStream();
            if ("gzip".equals(this.url.getContentEncoding())) {
                inputStream = new GZIPInputStream(inputStream);
            }
            InputSource inputSource = new InputSource(inputStream);
            syndFeedInput = new SyndFeedInput();
            syndFeed = syndFeedInput.build(inputSource);
            if(getArguments().length<2)
                getEvent().getChannel().sendMessage(BotLauncher.getMessages().getString("specCateg")).queue();
            List<String> categories = new ArrayList<>(Arrays.asList(getArguments()).subList(3, getArguments().length));
            for(String category : categories){
                long id;
                List<CategoryEntity> categoryEntities = categoryRepository.findByName(category);
                if(categoryEntities.size()==0){
                    CategoryEntity categoryEntity = new CategoryEntity();
                    categoryEntity.setName(category);
                    categoryRepository.create(categoryEntity);
                    id=categoryEntity.getId();
                }
                else{
                    id = categoryEntities.get(0).getId();
                }
                FeedcategoryEntity feedcategoryEntity = new FeedcategoryEntity();
                feedcategoryEntity.setIdCategory(id);
                FeedEntity feedEntity = new FeedEntity();
                feedEntity.setLink(getArguments()[2]);
                feedEntity.setName(getArguments()[1]);
                feedRepository.create(feedEntity);
                feedcategoryEntity.setIdFeed(feedEntity.getId());
                feedCategoryRepository.create(feedcategoryEntity);
                getEvent().getChannel().sendMessage(BotLauncher.getMessages().getString("newsSuccess")).queue();
            }

        } catch (  Exception ex) {
            getEvent().getChannel().sendMessage(BotLauncher.getMessages().getString("error")).queue();
        }

    }
}
