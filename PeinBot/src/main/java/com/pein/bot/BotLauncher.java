package com.pein.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class BotLauncher {
    public static String prefix = "#";


    public static void main(String[] args) throws LoginException {
        String token = "ODMyNjU2OTAyMzk5ODUyNTc0.YHm-Kw.ShgtTv6QhmOPYqJyCqZM-F4g5oo";
        // All other events will be disabled.
        JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new GuildMemberJoin())
                .addEventListeners(new GuildMemberLeave())
                .addEventListeners(new CommandHandler())
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.watching("the world"))
                .build();
//        SpringApplication.run(BotLauncher.class, args);

    }

}
