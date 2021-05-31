package com.pein.commands;

import com.pein.bot.BotLauncher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info extends Command {

    public Info(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public void run() {
        GuildMessageReceivedEvent event = getEvent();

        String commandField = new StringBuilder().append(BotLauncher.getMessages().getString("info1"))
                .append("\n\n").append(BotLauncher.getMessages().getString("info2"))
                .append("\n\n").append(BotLauncher.getMessages().getString("info3"))
                .append("\n\n").append(BotLauncher.getMessages().getString("info4"))
                .append("\n\n").append(BotLauncher.getMessages().getString("info5")).toString();

        String keepTrack = BotLauncher.getMessages().getString("info.track");

        String creators = BotLauncher.getMessages().getString("info.creators");

        EmbedBuilder information = new EmbedBuilder();
        information.setTitle(BotLauncher.getMessages().getString("info.title"));
        information.setDescription(BotLauncher.getMessages().getString("info.desc"));
        information.addField(BotLauncher.getMessages().getString("info.commands.title"), commandField, false);
        information.addField(BotLauncher.getMessages().getString("info.tracking.title"), keepTrack, false);
        information.addField(BotLauncher.getMessages().getString("info.creators.title"), creators, false);
        information.setColor(0xed1313);
        information.setFooter(BotLauncher.getMessages().getString("info.no.source"), event.getMember().getUser().getEffectiveAvatarUrl());


        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(information.build()).queue();
        information.clear();

    }
}
