package com.pein.commands;

import com.pein.bot.BotLauncher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Info extends Command {

    public Info(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public void run() {
        GuildMessageReceivedEvent event = getEvent();

        String commandField = new StringBuilder().append(BotLauncher.getPrefix())
                .append(BotLauncher.getMessages().getString("info1"))
                .append("\n\n").append(BotLauncher.getPrefix()).append(BotLauncher.getMessages().getString("info2"))
                .append("\n\n").append(BotLauncher.getPrefix()).append(BotLauncher.getMessages().getString("info3"))
                .append("\n\n").append(BotLauncher.getPrefix()).append(BotLauncher.getMessages().getString("info4"))
                .append("\n\n").append(BotLauncher.getPrefix()).append(BotLauncher.getMessages().getString("info5"))
                .append("\n\n").append(BotLauncher.getPrefix()).append(BotLauncher.getMessages().getString("info6"))
                .append("\n\n").append(BotLauncher.getPrefix()).append(BotLauncher.getMessages().getString("info7"))
                .append("\n\n").append(BotLauncher.getPrefix()).append(BotLauncher.getMessages().getString("info8")).toString();

        String keepTrack = BotLauncher.getMessages().getString("info.track");

        String creators = BotLauncher.getMessages().getString("info.creators");

        EmbedBuilder information = new EmbedBuilder();
        information.setColor(Color.PINK);

        information.setTitle(BotLauncher.getMessages().getString("info.title"));
        information.setDescription(BotLauncher.getMessages().getString("info.desc"));
        information.addField(BotLauncher.getMessages().getString("info.commands.title"), commandField, false);
        information.addField(BotLauncher.getMessages().getString("info.tracking.title"), keepTrack, false);
        information.addField(BotLauncher.getMessages().getString("info.creators.title"), creators, false);

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(information.build()).queue();
        information.clear();

    }
}
