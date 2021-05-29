package com.pein.bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] commandArguments = event.getMessage().getContentRaw().split("\\s+");

        String[] args = commandArguments[0].split(BotLauncher.prefix);

        switch (args[args.length - 1]) {
            case "clear":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "clear")) {
                    new Clear(commandArguments, event);
                    Clear.handleClear();
                }
                break;
            case "info":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "info")) {
                    new Info(event);
                    Info.handleInfo();
                }
                break;
            case "news":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "news")) {
                    News news = new News(commandArguments, event);
                    news.handleNews();
                }
            case "ask":
                if(commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "ask")){
                    new Ask(commandArguments,event);
                    Ask.handleAsk();

                }
            default:
                break;
        }
    }
}

