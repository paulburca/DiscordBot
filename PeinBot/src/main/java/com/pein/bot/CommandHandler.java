package com.pein.bot;

import com.pein.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] commandArguments = event.getMessage().getContentRaw().split("\\s+");

        String firstArgument = commandArguments[0];

        String[] args = commandArguments[0].split(BotLauncher.getPrefix());

        if (event.getAuthor().isBot()) {
            return;
        }

        switch (args[args.length - 1]) {
            case "clear":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "clear")) {
                    Thread clear = new Thread(new Clear(commandArguments, event));
                    clear.start();
                }
                break;
            case "info":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "info")) {
                    Thread info = new Thread(new Info(commandArguments, event));
                    info.start();
                }
                break;
            case "news":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "news")) {
                    Thread news = new Thread(new News(commandArguments, event));
                    news.start();
                }
                break;
            case "ask":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "ask")) {
                    Thread ask = new Thread(new Ask(commandArguments, event));
                    ask.start();
                }
                break;
            case "prefix":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "prefix")) {
                    Thread prefix = new Thread(new Prefix(commandArguments, event));
                    prefix.start();
                }
                break;
            case "addNews":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "addNews")) {
                    if (commandArguments.length >= 4) {
                        Thread addNews = new Thread(new AddNews(commandArguments, event));
                        addNews.start();
                    } else {

                        EmbedBuilder wrongFormat = new EmbedBuilder();
                        wrongFormat.setColor(Color.RED);
                        wrongFormat.setTitle(BotLauncher.getMessages().getString("correct.usage"));
                        wrongFormat.setDescription(BotLauncher.getMessages().getString("has.structure") +
                                BotLauncher.getPrefix() + BotLauncher.getMessages().getString("add.news.structure"));
                        event.getChannel().sendTyping().queue();
                        event.getChannel().sendMessage(wrongFormat.build()).queue();

                    }
                }
                break;
            case "categories":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "categories")) {
                    Thread getCategories = new Thread(new Categories(commandArguments, event));
                    getCategories.start();
                }
                break;
            case "setLanguage":
                if (commandArguments[0].equalsIgnoreCase(BotLauncher.getPrefix() + "setLanguage")) {
                    Thread setLang = new Thread(new Language(commandArguments, event));
                    setLang.start();
                }
                break;
            default:
                char[] array = firstArgument.toCharArray();
                if (String.valueOf(array[0]).equals(BotLauncher.getPrefix())) {
                    EmbedBuilder invalid = new EmbedBuilder();
                    invalid.setColor(Color.RED);
                    invalid.setTitle(BotLauncher.getMessages().getString("invalid.command"));
                    invalid.setDescription(BotLauncher.getMessages().getString("redirect.info")
                            + BotLauncher.getPrefix() + "info");
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(invalid.build()).queue();
                }
                break;
        }
    }
}

