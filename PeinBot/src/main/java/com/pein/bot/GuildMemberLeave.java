package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class GuildMemberLeave extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        String user = event.getUser().getAsMention();

        JDA client = event.getJDA();

        List<TextChannel> channels = client.getTextChannelsByName("general", false);

        try {
            for (TextChannel channel : channels) {
                EmbedBuilder join = new EmbedBuilder();
                join.setColor(Color.BLUE);
                join.setDescription(user + " left.");
                channel.sendMessage(join.build()).queue();
            }
        }catch(MissingAccessException exception){

        }
    }

}
