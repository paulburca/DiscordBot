package com.pein.commands;

import com.pein.bot.BotLauncher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class Clear extends Command {
    public Clear(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public void run() {
        String[] commandArguments = getArguments();
        GuildMessageReceivedEvent event = getEvent();
        if (commandArguments.length != 2 || Integer.parseInt(commandArguments[1]) <= 3) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.RED);
            usage.setTitle("Specify amount to delete");
            usage.setDescription("Usage: '" + BotLauncher.getPrefix() + "clear [number of messages]'");
            event.getChannel().sendMessage(usage.build()).queue();
        } else {
            try {

                List<Message> messages = event.getChannel()
                        .getHistory()
                        .retrievePast(Integer.parseInt(commandArguments[1]))
                        .complete();
                event.getChannel().deleteMessages(messages).queue();

                EmbedBuilder success = new EmbedBuilder();
                success.setColor(Color.GREEN);
                success.setTitle("Successfully deleted " + commandArguments[1] + ".");
                success.setDescription("Messages older than 2 weeks can't be deleted.");
                event.getChannel().sendMessage(success.build()).queue();

            } catch (IllegalArgumentException e) {
                if (e.toString().startsWith("java.lang.IllegalArgument.Exception: Message retrieval")) {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.ORANGE);
                    error.setTitle("Too many messages selected.");
                    error.setDescription("Between 1-100 messages can be deleted one at a time.");
                    event.getChannel().sendMessage(error.build()).queue();
                } else {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.ORANGE);
                    error.setTitle("Selected messages are older that 2 weeks.");
                    error.setDescription("Messages older stan 2 weeks can't be deleted.");
                    event.getChannel().sendMessage(error.build()).queue();
                }
            }

        }
    }
}
