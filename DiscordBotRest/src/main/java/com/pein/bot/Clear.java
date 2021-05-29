package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class Clear{
    private static String[] args;
    private static GuildMessageReceivedEvent event;

    public Clear(String[] args, GuildMessageReceivedEvent event){
        setArgs(args);
        setEvent(event);
    }

    public static void handleClear(){
        String commandArguments[] = getArgs();
        GuildMessageReceivedEvent event = getEvent();
        if (commandArguments.length != 2 || Integer.parseInt(commandArguments[1]) <= 3) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.ORANGE);
            usage.setTitle("Specify amount to delete");
            usage.setDescription("Usage: '" + BotLauncher.prefix + "clear [number of messages]'");
            event.getChannel().sendMessage(usage.build()).queue();
        } else {
            try {

//                    event.getChannel().getHistory().retrievePast(Integer.parseInt(commandArguments[1]))
//                            .map(messages -> messages.get(1))
//                            .queue(message -> System.out.println("Retrieving the first message channel before delete"));

                List<Message> messages = event.getChannel()
                        .getHistory()
                        .retrievePast(Integer.parseInt(commandArguments[1]))
                        .complete();
                event.getChannel().deleteMessages(messages).queue();

                EmbedBuilder success = new EmbedBuilder();
                success.setColor(Color.ORANGE);
                success.setTitle("Successfully deleted " + commandArguments[1] + ".");
                success.setDescription("Messages older than 2 weeks can't be deleted.");
                event.getChannel().sendMessage(success.build()).queue();

            } catch (IllegalArgumentException e) {
                if (e.toString().startsWith("java.langIllegalArgument.Exception: Message retrieval")) {
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

    public static String[] getArgs() {
        return args;
    }

    public static void setArgs(String[] args) {
        Clear.args = args;
    }

    public static GuildMessageReceivedEvent getEvent() {
        return event;
    }

    public static void setEvent(GuildMessageReceivedEvent event) {
        Clear.event = event;
    }
}
