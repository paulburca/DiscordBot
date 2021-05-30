package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;

public class Prefix extends Command {
    Prefix(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    void handleCommand() {
        String[] commandArguments = getArguments();
        GuildMessageReceivedEvent event = getEvent();

        if (commandArguments.length != 2 || commandArguments[1].length() != 1) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle("Choose a new prefix for Pein:");
            usage.setDescription("Usage: '" + BotLauncher.getPrefix() + "prefix [!#&/>]'");
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        if (BotLauncher.getPrefix().equals(commandArguments[1])) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle("Choose a new prefix for Pein:");
            usage.setDescription("Usage: '" + BotLauncher.getPrefix() + "prefix [~!#&/>]' \n Current selected prefix is already in use.");
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        if (commandArguments[1].matches("[~!#&/>]")) {
            BotLauncher.setPrefix(commandArguments[1]);
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle("New prefix:");
            usage.setDescription("Successfully changed pre prefix to: " + BotLauncher.getPrefix());
            event.getChannel().sendMessage(usage.build()).queue();

            BufferedWriter fileWriter;
            try{
                fileWriter = new BufferedWriter(new FileWriter("src/main/resources/prefix.txt"));
                fileWriter.write(commandArguments[1]);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
