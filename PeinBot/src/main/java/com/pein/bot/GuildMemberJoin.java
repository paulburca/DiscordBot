package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class GuildMemberJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        String user = event.getUser().getAsMention();

        JDA client = event.getJDA();

        List<TextChannel> channels = client.getTextChannelsByName("general", false);

        try {
            for (TextChannel channel : channels) {
                EmbedBuilder join = new EmbedBuilder();
                join.setColor(Color.BLUE);
                join.setDescription(BotLauncher.getMessages().getString("join").replace("[member]", user));
                channel.sendMessage(join.build()).queue();
            }
        } catch (MissingAccessException exception) {
            //
        }
    }
}
