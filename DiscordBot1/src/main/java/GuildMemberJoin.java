import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.awt.*;
import java.util.List;
import java.util.Random;

public class GuildMemberJoin extends ListenerAdapter {

    String[] messages = {
            "[member] joined. You must construct additional pylons.",
            "Never gonna give [member] up. Never let [member] down!",
            "Hey! Listen! [member] has joined!",
            "Ha! [member] has joined! You activated my trap card!",
            "We've been expecting you, [member].",
            "It's dangerous to go alone, take [member]!",
            "Swoooosh. [member] just landed.",
            "Brace yourselves. [member] just joined the server.",
            "A wild [member] appeared."
    };

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Random random = new Random();

        int number = random.nextInt(messages.length);

        String user = event.getUser().getAsMention();

        JDA client = event.getJDA();

        List<TextChannel> channels = client.getTextChannelsByName("general",false);

        for (TextChannel channel : channels){
            EmbedBuilder join = new EmbedBuilder();
            join.setColor(Color.BLUE);
            join.setDescription(messages[number].replace("[member]", user));
            channel.sendMessage(join.build()).queue();
        }

        Guild guild = event.getGuild();
        List<Role> roleList = guild.getRolesByName("Member",true);

        for(Role role : roleList){
            guild.addRoleToMember(event.getMember(),role).complete();
        }
    }
}
