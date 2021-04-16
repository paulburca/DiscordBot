import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessages extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        // ignore
        if(event.getAuthor().isBot()){
            System.out.println("Can't receive command from another Bot");
        }
    }
}
