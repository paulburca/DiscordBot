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

        if(event.getAuthor().isBot()){
            return;
        }

        if (commandArguments.length != 2 || Integer.parseInt(commandArguments[1]) <= 3) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.RED);
            usage.setTitle(BotLauncher.getMessages().getString("amount.to.delete"));
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
                success.setTitle(BotLauncher.getMessages().getString("success.delete") + commandArguments[1] + ".");
                success.setDescription(BotLauncher.getMessages().getString("old.messages"));
                event.getChannel().sendMessage(success.build()).queue();

            } catch (IllegalArgumentException e) {
                if (e.toString().startsWith("java.lang.IllegalArgument.Exception: Message retrieval")) {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.ORANGE);
                    error.setTitle(BotLauncher.getMessages().getString("too.many"));
                    error.setDescription(BotLauncher.getMessages().getString("too.many.desc"));
                    event.getChannel().sendMessage(error.build()).queue();
                } else {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.ORANGE);
                    error.setTitle(BotLauncher.getMessages().getString("too.old"));
                    error.setDescription(BotLauncher.getMessages().getString("old.messages"));
                    event.getChannel().sendMessage(error.build()).queue();
                }
            }

        }
    }
}
