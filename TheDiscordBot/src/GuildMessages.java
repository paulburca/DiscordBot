import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessages extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] messageArguments = event.getMessage().getContentRaw().split("\\s+");
        User bot = event.getJDA().getSelfUser();
        // ignore
        if (event.getAuthor().isBot() && event.getAuthor().equals(bot)) {
            System.out.println("Can't receive command from another Pein");
            System.out.println(event.getAuthor());
            return;
        } else {
            // command is from a user
            //msg.getContentRaw().charAt(0) == '#'
            switch (messageArguments[0]) {
                case "#info":

                    EmbedBuilder information = new EmbedBuilder();
                    information.setTitle("(⌐■_■) Information");
                    information.setDescription("content information");
                    information.addField("Creator", "Burcă Paul (paulburca), Filimon Dănuț-Dumitru (Danie83)", false);
                    information.setColor(0xed1313);
                    information.setFooter("fill", event.getMember().getUser().getEffectiveAvatarUrl());


                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(information.build()).queue();
                    information.clear();
                    break;
                default:
                    break;
            }
        }
    }
}
