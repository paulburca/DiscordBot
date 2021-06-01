package com.pein.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class BotLauncher {
    private static String prefix;
    private static Locale locale;
    private static ResourceBundle messages;

    public static void setup() {
        Locale.setDefault(Locale.UK);
        setLocale(Locale.getDefault());
        setMessages(ResourceBundle.getBundle("Lang.Lang", getLocale()));
    }

    public static void setPrefixBot() {
        setPrefix("#");
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader("src/main/resources/prefix.txt"));
            String fileLine;
            if ((fileLine = fileReader.readLine()) != null) {
                if (fileLine.matches("[~!#&/>]") && fileLine.length() == 1) {
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
        setup();
        String token = "ODMyNjU2OTAyMzk5ODUyNTc0.YHm-Kw.rP26G6MX42rjFe5jA4XvLEsOVkA";

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

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        BotLauncher.locale = locale;
    }

    public static ResourceBundle getMessages() {
        return messages;
    }

    public static void setMessages(ResourceBundle messages) {
        BotLauncher.messages = messages;
    }
}
