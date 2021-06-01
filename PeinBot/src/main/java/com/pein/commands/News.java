package com.pein.commands;

import com.pein.Entities.CategoryEntity;
import com.pein.Entities.FeedEntity;
import com.pein.Entities.FeedcategoryEntity;
import com.pein.bot.BotLauncher;
import com.pein.bot.RSSManager;
import com.pein.repositories.CategoryRepository;
import com.pein.repositories.FeedCategoryRepository;
import com.pein.repositories.FeedRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class News extends Command {

    FeedRepository feedRepository = new FeedRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    FeedCategoryRepository feedCategoryRepository = new FeedCategoryRepository();

    public News(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        String url;
        String[] commandArguments = getArguments();
        GuildMessageReceivedEvent event = getEvent();
        int numberOfEntries = 3; // default

        switch (commandArguments.length) {
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
                    RSSManager rssManager1 = new RSSManager(url, event, Math.min(numberOfEntries,10));
                    break;
                }
            case 3:
                List<CategoryEntity> categoryEntities = categoryRepository.findByName(commandArguments[1]);
                if (categoryEntities.size() == 0) {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.RED);
                    error.setDescription(BotLauncher.getMessages().getString("not.a.category"));
                    event.getChannel().sendMessage(error.build()).queue();
                } else {
                    Long idCategory = categoryEntities.get(0).getId();
                    List<FeedcategoryEntity> feedcategoryEntities = feedCategoryRepository.findById2(idCategory);
                    if (isNumeric(commandArguments[commandArguments.length - 1])) {
                        numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length - 1]);
                    }
                    for (FeedcategoryEntity feedcategoryEntity : feedcategoryEntities) {
                        Long idFeed = feedcategoryEntity.getIdFeed();
                        FeedEntity feedEntity2 = feedRepository.findById(idFeed);
                        url = feedEntity2.getLink();
                        if (url != null) {
                            RSSManager rssManager2 = new RSSManager(url, event, Math.min(numberOfEntries,10));
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
