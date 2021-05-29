package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info extends Command {

    Info(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    void handleCommand() {

        GuildMessageReceivedEvent event = getEvent();

        EmbedBuilder information = new EmbedBuilder();
        information.setTitle("(⌐■_■) Information");
        information.setDescription("content information");
        information.addField("Creator", "Burcă Paul (paulburca), Filimon Dănuț-Dumitru (Danie83)", false);
        information.setColor(0xed1313);
        information.setFooter("fill", event.getMember().getUser().getEffectiveAvatarUrl());


        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(information.build()).queue();
        information.clear();
    }
}
