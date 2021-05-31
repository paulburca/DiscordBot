package com.pein.commands;

import com.pein.bot.BotLauncher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;

public class Prefix extends Command {
    public Prefix(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public void run() {
        String[] commandArguments = getArguments();
        GuildMessageReceivedEvent event = getEvent();

        if (commandArguments.length != 2 || commandArguments[1].length() != 1) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle(BotLauncher.getMessages().getString("anotherPrefix"));
            usage.setDescription(BotLauncher.getMessages().getString("usage") + BotLauncher.getPrefix() + BotLauncher.getMessages().getString("prefix"));
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        if (BotLauncher.getPrefix().equals(commandArguments[1])) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle(BotLauncher.getMessages().getString("anotherPrefix"));
            usage.setDescription(BotLauncher.getMessages().getString("usage") + BotLauncher.getPrefix()
                    + BotLauncher.getMessages().getString("prefix") + "\n" + BotLauncher.getMessages().getString("alreadyPrefix"));
            return;
        }

        if (commandArguments[1].matches("[~!#&/>]")) {
            BotLauncher.setPrefix(commandArguments[1]);
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle(BotLauncher.getMessages().getString("newPrefix"));
            usage.setDescription(BotLauncher.getMessages().getString("successPrefix") + BotLauncher.getPrefix());
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
