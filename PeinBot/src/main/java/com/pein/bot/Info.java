package com.pein.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info extends Command {

    Info(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    void handleCommand() {
        GuildMessageReceivedEvent event = getEvent();

        String commandField = "[prefix]info -> This command will display information about PeinBot. \n\n" +
                "[prefix]clear [4-100] -> This command will clear between 4 to 100 messages that are not older than 2 weeks for you. \n\n" +
                "[prefix]prefix [!#&/>] -> You can change the prefix for PeinBot anytime, only { !, #, &, /, > } are available at the moment. \n\n" +
                "[prefix]news [category] -> This command will display the usual news with a given category, the category field is optional.\n\n" +
                "[prefix]ask [question] -> Given a question, PeinBot will try to asnwer your question using the Wolfram Alpha API.";

        String keepTrack = "PeinBot will display a proper message whenever a user joins or leaves the current discord channel.";

        String creators = "PeinBot was created by Burcă Paul ([paulburca](https://github.com/paulburca)), Filimon Dănuț-Dumitru ([Danie83](https://github.com/Danie83))";

        EmbedBuilder information = new EmbedBuilder();
        information.setTitle("(⌐■_■) Information");
        information.setDescription("content information");
        information.addField("Commands",commandField,false);
        information.addField("Tracking", keepTrack,false);
        information.addField("Creators", creators, false);
        information.setColor(0xed1313);
        information.setFooter("The source code for PeinBot is not available at the moment.", event.getMember().getUser().getEffectiveAvatarUrl());


        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(information.build()).queue();
        information.clear();

    }
}
