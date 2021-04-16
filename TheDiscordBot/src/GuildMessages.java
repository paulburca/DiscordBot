import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessages extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Message msg = event.getMessage();
        User bot = event.getJDA().getSelfUser();
        // ignore
        if(event.getAuthor().isBot() && event.getAuthor().equals(bot)){
            System.out.println("Can't receive command from another Pein");
            System.out.println(event.getAuthor());
            return;
        }
        else
        {
            // command is from a user
            MessageChannel channel = event.getChannel();
            //msg.getContentRaw().charAt(0) == '$'
            switch(event.getMessage().getContentRaw()){
                case "$ping":
                    channel.sendMessage("Pong!").queue();
                    break;
                case "$hello":
                    channel.sendMessage("You shall know pain").queue();
                    break;
                default:
                    break;
            }
        }
    }
}
