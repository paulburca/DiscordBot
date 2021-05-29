package com.pein.bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class News {
    private static String[] args;
    private static GuildMessageReceivedEvent event;

    public News(String[] args, GuildMessageReceivedEvent event){
        setArgs(args);
        setEvent(event);
    }

    private List<PredefinedFeed> listPredefinedFeeds = getFeeds();

    public  List<PredefinedFeed> getFeeds() {
        List<PredefinedFeed> returnList = new ArrayList<>();
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader("src/main/resources/feeds.txt"));
            String fileLine;
            while ((fileLine = fileReader.readLine()) != null) {
                String[] feedArguments = fileLine.split(",");
                PredefinedFeed predefinedFeed = new PredefinedFeed("",feedArguments[0],
                        feedArguments[1],"romana","none", LocalDate.now());
                returnList.add(predefinedFeed);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return returnList;
    }

    public static boolean isNumeric(String string){
        if(string == null){
            return false;
        }
        try{
            int number = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void handleNews()
    {
        String url = null;
        String commandArguments[] = getArgs();
        GuildMessageReceivedEvent event = getEvent();
        int numberOfEntries = 3; // default

        if(commandArguments.length <=2){
            url = listPredefinedFeeds.get(0).getFeedLink();
        }
        else
        {
            int count = 0;
            boolean existence = false;
            for(PredefinedFeed predefinedFeed : listPredefinedFeeds){
                if(commandArguments[1].equals(predefinedFeed.getFeedCategory())){
                    url = listPredefinedFeeds.get(count).getFeedLink();
                    existence = true;
                }
                count++;
            }
            if(!existence){
                event.getChannel().sendMessage("Sorry, but that's not a valid category.").queue();
            }
        }

        if (isNumeric(commandArguments[commandArguments.length-1])) {
            numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length-1]);
        }

        if (url != null) {
            RSSManager rssManager = new RSSManager(url, event, numberOfEntries);
        }
    }

    public static String[] getArgs() {
        return args;
    }

    public static void setArgs(String[] args) {
        News.args = args;
    }

    public static GuildMessageReceivedEvent getEvent() {
        return event;
    }

    public static void setEvent(GuildMessageReceivedEvent event) {
        News.event = event;
    }
}
