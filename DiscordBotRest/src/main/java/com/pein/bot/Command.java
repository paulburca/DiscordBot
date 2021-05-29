package com.pein.bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {

    private static String[] arguments;
    private static GuildMessageReceivedEvent event;

    Command(String[] arguments){}


}