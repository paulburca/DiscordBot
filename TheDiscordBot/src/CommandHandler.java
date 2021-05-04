import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class CommandHandler extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] commandArguments = event.getMessage().getContentRaw().split("\\s+");

        if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "clear")) {
            if (commandArguments.length != 2) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.ORANGE);
                usage.setTitle("Specify amount to delete");
                usage.setDescription("Usage: '" + BotLauncher.prefix + "clear [number of messages]'");
                event.getChannel().sendMessage(usage.build()).queue();
            } else {
                try {

//                    event.getChannel().getHistory().retrievePast(Integer.parseInt(commandArguments[1]))
//                            .map(messages -> messages.get(1))
//                            .queue(message -> System.out.println("Retrieving the first message channel before delete"));

                    List<Message> messages = event.getChannel()
                            .getHistory()
                            .retrievePast(Integer.parseInt(commandArguments[1]))
                            .complete();
                    event.getChannel().deleteMessages(messages).queue();

                    EmbedBuilder succes = new EmbedBuilder();
                    succes.setColor(Color.ORANGE);
                    succes.setTitle("Successfully deleted " + commandArguments[1] + ".");
                    succes.setDescription("Messages older than 2 weeks can't be deleted.");
                    event.getChannel().sendMessage(succes.build()).queue();

                } catch (IllegalArgumentException e) {
                    if (e.toString().startsWith("java.langIllegalArgument.Exception: Message retrieval")) {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.ORANGE);
                        error.setTitle("Too many messages selected.");
                        error.setDescription("Between 1-100 messages can be deleted one at a time.");
                        event.getChannel().sendMessage(error.build()).queue();
                    } else {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.ORANGE);
                        error.setTitle("Selected messages are older that 2 weeks.");
                        error.setDescription("Messages older stan 2 weeks can't be deleted.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }

            }
        }

        if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "info")) {
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
        if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "news")) {
            String url = null;
            if (commandArguments.length != 2) {
                url="https://www.news.ro/rss";
            }
            else{
                switch (commandArguments[1]){
                    case "sport":url="https://www.digisport.ro/rss"; break;
                    case "political":url="https://rss.politico.com/politics.xml"; break;
                    case "science":url="https://www.sciencenews.org/feed"; break;
                    case "IT": url="https://stackoverflow.com/feeds"; break;
                    default:event.getChannel().sendMessage("Sorry, but that's not a valid category.").queue();break;
                }
            }
            if(url!=null){
                RSSManager rssManager = new RSSManager(url,event);
            }
        }
    }
}
