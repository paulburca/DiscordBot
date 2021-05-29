package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info {

    private static GuildMessageReceivedEvent event;

    public Info(GuildMessageReceivedEvent event){
        setEvent(event);
    }

    public static void handleInfo(){
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

    public static GuildMessageReceivedEvent getEvent() {
        return event;
    }

    public static void setEvent(GuildMessageReceivedEvent event) {
        Info.event = event;
    }
}
