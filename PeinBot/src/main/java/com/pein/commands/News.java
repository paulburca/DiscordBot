package com.pein.commands;

import com.pein.entities.CategoryEntity;
import com.pein.entities.FeedEntity;
import com.pein.entities.FeedcategoryEntity;
import com.pein.bot.BotLauncher;
import com.pein.rss.RSSManager;
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
                new RSSManager("https://www.news.ro/rss", event, numberOfEntries);
                break;
            case 2:

                if (isNumeric(commandArguments[commandArguments.length - 1])) {
                    numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length - 1]);
                    new RSSManager("https://www.news.ro/rss", event, Math.min(numberOfEntries, 10));
                    break;
                }
            case 3:

                List<CategoryEntity> categoryEntities = categoryRepository.findByName(commandArguments[1]);
                if (categoryEntities.size() == 0) {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.RED);
                    error.setDescription(BotLauncher.getMessages().getString("not.a.category"));
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(error.build()).queue();
                } else {
                    Long idCategory = categoryEntities.get(0).getId();
                    List<FeedcategoryEntity> feedCategoryEntities = feedCategoryRepository.findById2(idCategory);
                    if (isNumeric(commandArguments[commandArguments.length - 1])) {
                        numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length - 1]);
                    }
                    int total = numberOfEntries;
                    if (numberOfEntries < feedCategoryEntities.size()) {
                        numberOfEntries = 1;
                    } else {
                        numberOfEntries = numberOfEntries / feedCategoryEntities.size();
                    }

                    for (FeedcategoryEntity feedcategoryEntity : feedCategoryEntities) {
                        if (numberOfEntries * 2 > total) {
                            numberOfEntries = total;
                        }
                        Long idFeed = feedcategoryEntity.getIdFeed();
                        FeedEntity feedEntity2 = feedRepository.findById(idFeed);
                        url = feedEntity2.getLink();
                        if (url != null) {
                            new RSSManager(url, event, Math.min(numberOfEntries, 10));
                        }
                        total = total - numberOfEntries;
                        if (total <= 0) {
                            break;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
