package com.pein.bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] commandArguments = event.getMessage().getContentRaw().split("\\s+");

        String[] args = commandArguments[0].split(BotLauncher.getPrefix());

        switch (args[args.length - 1]) {
            case "clear":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "clear")) {
                    Clear clear = new Clear(commandArguments, event);
                    clear.handleCommand();
                }
                break;
            case "info":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "info")) {
                    Info info = new Info(commandArguments, event);
                    info.handleCommand();
                }
                break;
            case "news":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "news")) {
                    News news = new News(commandArguments, event);
                    news.handleCommand();
                }
                break;
            case "ask":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "ask")) {
                    Ask ask = new Ask(commandArguments, event);
                    ask.handleCommand();
                }
                break;
            case "prefix":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "prefix")) {
                    Prefix prefix = new Prefix(commandArguments, event);
                    prefix.handleCommand();
                }
                break;
            case "addNews":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "addNews")) {
                    if(commandArguments.length>=4){
                        AddNews addNews = new AddNews(commandArguments, event);
                        addNews.handleCommand();
                    }
                    else{
                        event.getChannel().sendMessage("The command has the following structure: "+
                                BotLauncher.getPrefix() +"addNews [name] [link] [categories]").queue();
                    }
                }
                break;
            case "categories":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "categories")) {
                    GetCategories getCategories = new GetCategories(commandArguments, event);
                    getCategories.handleCommand();
                }
            default:
                break;
        }
    }
}

