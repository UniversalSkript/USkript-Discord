package net.uskript.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.uskript.discord.commands.RegisterCommands;
import net.uskript.discord.listeners.RegisterListeners;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Main {

    private static Logger logger;
    private static JDA jda;

    private static void readConsoleInput(final JDA jda) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder strBuilder = new StringBuilder();
        int inputValue;
        String inputLine;

        while (true) {
            try {
                inputValue = bufferedReader.read();
                if (inputValue != -1 && inputValue != 4) {
                    strBuilder.append((char) inputValue);
                    strBuilder.append(bufferedReader.readLine());
                    inputLine = strBuilder.toString();
                    strBuilder.setLength(0);
                    if ("stop".equalsIgnoreCase(inputLine))
                        break;
                } else
                    break;
            } catch (IOException ignored) {};
        }
    }

    public static void main(String[] args) {
        final JDABuilder jdaBuilder;

        if (args.length < 1)
            System.exit(1);
        jdaBuilder = JDABuilder.createLight(args[0],
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_BANS,
                GatewayIntent.DIRECT_MESSAGE_TYPING).setAutoReconnect(true);
        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(1);
        }
        logger = Logger.getLogger("USkript");
        logger.info("Test");
        RegisterCommands.register(jda);
        RegisterListeners.register(jda);
        jda.getPresence().setActivity(Activity.of(Activity.ActivityType.WATCHING,
        "GitHub repository of Java-Compiler", "https://github.com/eliott-belinguier/Java-Compiler"));
        readConsoleInput(jda);
        System.out.println("Stopping Uskript bot...");
        jda.shutdown();
    }

    public static JDA getJda() {
        return jda;
    }

    public static User getBotUser() {
        return jda.getSelfUser();
    }

    public static Logger getLogger() {
        return logger;
    }
}
