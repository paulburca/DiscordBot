package com.pein.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command implements Runnable{

    private static String[] arguments;
    private static GuildMessageReceivedEvent event;

    public Command(String[] arguments, GuildMessageReceivedEvent event) {
        setArguments(arguments);
        setEvent(event);
    }

    @Override
    public abstract void run();

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