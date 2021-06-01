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

    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void run() {
        String[] commandArguments = getArguments();
        GuildMessageReceivedEvent event = getEvent();

        if (commandArguments.length != 2 || !isNumeric(commandArguments[1]) || Integer.parseInt(commandArguments[1]) <= 3) {

            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.RED);
            usage.setTitle(BotLauncher.getMessages().getString("correct.usage"));
            usage.setDescription(BotLauncher.getMessages().getString("usage") + BotLauncher.getPrefix() + BotLauncher.getMessages().getString("clear.command"));
            event.getChannel().sendTyping().queue();
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
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(success.build()).queue();

            } catch (IllegalArgumentException e) {
                if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.ORANGE);
                    error.setTitle(BotLauncher.getMessages().getString("too.many"));
                    error.setDescription(BotLauncher.getMessages().getString("too.many.desc"));
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(error.build()).queue();
                } else {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(Color.ORANGE);
                    error.setTitle(BotLauncher.getMessages().getString("too.old"));
                    error.setDescription(BotLauncher.getMessages().getString("old.messages"));
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(error.build()).queue();
                }
            }

        }
    }
}
