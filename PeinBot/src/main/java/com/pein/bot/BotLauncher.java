package com.pein.bot;

import com.pein.Entities.FeedEntity;
import com.pein.repositories.FeedRepository;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileReader;

public class BotLauncher {
    private static String prefix;

    public static void setPrefixBot() {
        setPrefix("#");
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader("src/main/resources/prefix.txt"));
            String fileLine;
            if ((fileLine = fileReader.readLine()) != null) {
                if(fileLine.matches("[~!#&/>]") && fileLine.length() == 1){
                    String[] arguments = fileLine.split("\n");
                    setPrefix(arguments[0]);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws LoginException {

        setPrefixBot();
        String token = "ODMyNjU2OTAyMzk5ODUyNTc0.YHm-Kw.ShgtTv6QhmOPYqJyCqZM-F4g5oo";

        // All other events will be disabled.

        JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new GuildMemberJoin())
                .addEventListeners(new GuildMemberLeave())
                .addEventListeners(new CommandHandler())
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.watching("the world"))
                .build();

    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        BotLauncher.prefix = prefix;
    }
}
