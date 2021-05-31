package com.pein.commands;

import com.pein.bot.BotLauncher;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Locale;
import java.util.ResourceBundle;

public class setLang extends Command{
    public setLang(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }
    @Override
    public void run() {
        String language = getArguments()[1];
        switch (language.toLowerCase()){
            case "ro":
            case "romana":
            case "romania":
                BotLauncher.setLocale(new Locale("ro","RO"));
                BotLauncher.setMessages(ResourceBundle.getBundle("Lang.Lang_ro"));
                break;
            case "en":
            case "engleza":
            case "english":
                BotLauncher.setLocale(new Locale("en","UK"));
                BotLauncher.setMessages(ResourceBundle.getBundle("Lang.Lang"));
                System.out.println("yes");
                break;
            default:
                break;
        }
    }
}
