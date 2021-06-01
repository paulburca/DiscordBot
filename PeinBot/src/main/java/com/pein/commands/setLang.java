package com.pein.commands;

import com.pein.bot.BotLauncher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class setLang extends Command {
    public setLang(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    @Override
    public void run() {
        String language = getArguments()[1];
        switch (language.toLowerCase()) {
            case "ro":
            case "romana":
            case "romania":

                if (BotLauncher.getLocale().getLanguage().equals("ro")) {
                    EmbedBuilder failRO = new EmbedBuilder();
                    failRO.setColor(Color.RED);
                    failRO.setDescription(BotLauncher.getMessages().getString("language.fail"));
                    getEvent().getChannel().sendTyping().queue();
                    getEvent().getChannel().sendMessage(failRO.build()).queue();
                    break;
                }

                BotLauncher.setLocale(new Locale("ro", "RO"));
                BotLauncher.setMessages(ResourceBundle.getBundle("Lang.Lang_ro"));

                EmbedBuilder successRO = new EmbedBuilder();
                successRO.setColor(Color.GREEN);
                successRO.setDescription(BotLauncher.getMessages().getString("language.success"));
                getEvent().getChannel().sendTyping().queue();
                getEvent().getChannel().sendMessage(successRO.build()).queue();
                break;
            case "en":
            case "engleza":
            case "english":

                if (BotLauncher.getLocale().getLanguage().equals("en")) {
                    EmbedBuilder failEN = new EmbedBuilder();
                    failEN.setColor(Color.RED);
                    failEN.setDescription(BotLauncher.getMessages().getString("language.fail"));
                    getEvent().getChannel().sendTyping().queue();
                    getEvent().getChannel().sendMessage(failEN.build()).queue();
                    break;
                }

                BotLauncher.setLocale(new Locale("en", "UK"));
                BotLauncher.setMessages(ResourceBundle.getBundle("Lang.Lang"));
                EmbedBuilder successEN = new EmbedBuilder();
                successEN.setColor(Color.GREEN);
                successEN.setDescription(BotLauncher.getMessages().getString("language.success"));
                getEvent().getChannel().sendTyping().queue();
                getEvent().getChannel().sendMessage(successEN.build()).queue();
                break;
            default:
                EmbedBuilder defaultCase = new EmbedBuilder();
                defaultCase.setColor(Color.ORANGE);
                defaultCase.setDescription(BotLauncher.getMessages().getString("language.support"));
                getEvent().getChannel().sendTyping().queue();
                getEvent().getChannel().sendMessage(defaultCase.build()).queue();
                break;
        }
    }
}
