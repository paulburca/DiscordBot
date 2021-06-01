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

        if (commandArguments.length != 2 || !commandArguments[1].matches("[!#&/>]")) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.RED);
            usage.setTitle(BotLauncher.getMessages().getString("correct.usage"));
            usage.setDescription(BotLauncher.getMessages().getString("usage") + BotLauncher.getPrefix()
                    + BotLauncher.getMessages().getString("prefix")
                    + "\n\n" + BotLauncher.getMessages().getString("invalid.prefix"));
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        if (BotLauncher.getPrefix().equals(commandArguments[1])) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.RED);
            usage.setTitle(BotLauncher.getMessages().getString("correct.usage"));
            usage.setDescription(BotLauncher.getMessages().getString("usage") + BotLauncher.getPrefix()
                    + BotLauncher.getMessages().getString("prefix") + "\n\n"
                    + BotLauncher.getMessages().getString("already.prefix"));
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        if (commandArguments[1].matches("[~!#&/>]")) {
            BotLauncher.setPrefix(commandArguments[1]);
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.GREEN);
            usage.setTitle(BotLauncher.getMessages().getString("new.prefix"));
            usage.setDescription(BotLauncher.getMessages().getString("success.prefix") + BotLauncher.getPrefix());
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(usage.build()).queue();

            BufferedWriter fileWriter;
            try {
                fileWriter = new BufferedWriter(new FileWriter("src/main/resources/prefix.txt"));
                fileWriter.write(commandArguments[1]);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
