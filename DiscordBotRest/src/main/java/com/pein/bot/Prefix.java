package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Prefix extends Command{
    Prefix(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    void handleCommand(){
        String[] commandArguments = getArguments();
        GuildMessageReceivedEvent event = getEvent();

        if(commandArguments.length != 2){
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle("Choose a new prefix for Pein:");
            usage.setDescription("Usage: '" + BotLauncher.prefix + "prefix [~!@#$*/>=]'");
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        if(BotLauncher.prefix.equals(commandArguments[1])){
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle("Choose a new prefix for Pein:");
            usage.setDescription("Usage: '" + BotLauncher.prefix + "prefix [~!@#$*/>=]' \n Current selected prefix is already in use.");
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }
    }
}
