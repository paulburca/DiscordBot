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
                    Clear clear = new Clear(commandArguments, event);
                    clear.handleCommand();
                }
                break;
            case "info":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "info")) {
                    Info info = new Info(commandArguments, event);
                    info.handleCommand();
                }
                break;
            case "news":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "news")) {
                    News news = new News(commandArguments, event);
                    news.handleCommand();
                }
                break;
            case "ask":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "ask")) {
                    Ask ask = new Ask(commandArguments, event);
                    ask.handleCommand();
                }
                break;
            case "prefix":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "prefix")) {
                    Prefix prefix = new Prefix(commandArguments, event);
                    prefix.handleCommand();
                }
                break;
            default:
                break;
        }
    }
}

