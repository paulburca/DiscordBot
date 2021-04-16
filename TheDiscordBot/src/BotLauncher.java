import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;

public class BotLauncher{
    public static void main(String[] args) throws LoginException {
        String token = "ODMyNjU2OTAyMzk5ODUyNTc0.YHm-Kw.ShgtTv6QhmOPYqJyCqZM-F4g5oo";
        // All other events will be disabled.
        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new GuildMessages())
                .setActivity(Activity.playing("cake"))
                .build();
    }

}
