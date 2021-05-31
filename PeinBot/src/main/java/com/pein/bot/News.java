package com.pein.bot;

import com.pein.Entities.CategoryEntity;
import com.pein.Entities.FeedEntity;
import com.pein.Entities.FeedcategoryEntity;
import com.pein.repositories.CategoryRepository;
import com.pein.repositories.FeedCategoryRepository;
import com.pein.repositories.FeedRepository;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class News extends Command {

    FeedRepository feedRepository = new FeedRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    FeedCategoryRepository feedCategoryRepository = new FeedCategoryRepository();

    News(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void handleCommand() {
        String url = null;
        String[] commandArguments = getArguments();
        GuildMessageReceivedEvent event = getEvent();
        int numberOfEntries = 3; // default
        // daca if isNumeric (numar in cazul in care e numeric se face rss)

        switch(commandArguments.length){
            case 1:
                FeedEntity feedEntity = feedRepository.findById(1L);
                url = feedEntity.getLink();
                RSSManager rssManager = new RSSManager(url, event, numberOfEntries);
                break;
            case 2:
                FeedEntity feedEntity1;
                if (isNumeric(commandArguments[commandArguments.length - 1])) {
                    feedEntity1 = feedRepository.findById(1L);
                    url = feedEntity1.getLink();
                    numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length - 1]);
                    RSSManager rssManager1 = new RSSManager(url, event, numberOfEntries);
                    break;
                }
            case 3:
                List<CategoryEntity> categoryEntities = categoryRepository.findByName(commandArguments[1]);
                if(categoryEntities.size()==0)
                    event.getChannel().sendMessage("Sorry, but that's not a valid category.").queue();
                else{
                    Long idCategory = categoryEntities.get(0).getId();
                    List<FeedcategoryEntity> feedcategoryEntities = feedCategoryRepository.findById2(idCategory);
                    if (isNumeric(commandArguments[commandArguments.length - 1])) {
                        numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length - 1]);
                    }
                    for(FeedcategoryEntity feedcategoryEntity : feedcategoryEntities){
                        Long idFeed = feedcategoryEntity.getIdFeed();
                        FeedEntity feedEntity2 = feedRepository.findById(idFeed);
                        url = feedEntity2.getLink();
                        if (url != null) {
                            RSSManager rssManager2 = new RSSManager(url, event, numberOfEntries);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
