package com.pein.bot;

import com.pein.Entities.FeedEntity;
import com.pein.repositories.FeedRepository;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class News extends Command {
    News(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }
    private List<PredefinedFeed> listPredefinedFeeds = getFeeds();
    FeedRepository feedRepository = new FeedRepository();

    public List<PredefinedFeed> getFeeds() {
        List<PredefinedFeed> returnList = new ArrayList<>();
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader("src/main/resources/feeds.txt"));
            String fileLine;
            while ((fileLine = fileReader.readLine()) != null) {
                String[] feedArguments = fileLine.split(",");
                PredefinedFeed predefinedFeed = new PredefinedFeed("", feedArguments[0],
                        feedArguments[1], "romana", "none", LocalDate.now());
                returnList.add(predefinedFeed);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return returnList;
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

        if (commandArguments.length <= 2) {
//            url = listPredefinedFeeds.get(0).getFeedLink();
//            FeedEntity feedEntity = feedRepository.findAllById(1L);
            FeedEntity feedEntity = feedRepository.findById(1L);
            url = feedEntity.getLink();
        } else {
            int count = 0;
            boolean existence = false;
            for (PredefinedFeed predefinedFeed : listPredefinedFeeds) {
                if (commandArguments[1].equals(predefinedFeed.getFeedCategory())) {
                    url = listPredefinedFeeds.get(count).getFeedLink();
                    existence = true;
                }
                count++;
            }
            if (!existence) {
                event.getChannel().sendMessage("Sorry, but that's not a valid category.").queue();
            }
        }

        if (isNumeric(commandArguments[commandArguments.length - 1])) {
            numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length - 1]);
        }

        if (url != null) {
            RSSManager rssManager = new RSSManager(url, event, numberOfEntries);
        }
    }

}
