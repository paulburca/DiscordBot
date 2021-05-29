package com.pein.bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {

    private static String[] arguments;
    private static GuildMessageReceivedEvent event;

    Command(String[] arguments, GuildMessageReceivedEvent event) {
        setArguments(arguments);
        setEvent(event);
    }

    abstract void handleCommand();


    public static String[] getArguments() {
        return arguments;
    }

    public static void setArguments(String[] arguments) {
        Command.arguments = arguments;
    }

    public static GuildMessageReceivedEvent getEvent() {
        return event;
    }

    public static void setEvent(GuildMessageReceivedEvent event) {
        Command.event = event;
    }
}