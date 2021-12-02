package net.uskript.discord.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.uskript.discord.Main;

public class RegisterListeners {

    public static void register(JDA jda) {
        final ListenerAdapter[] listeners;
        final int size;

        if (jda == null)
            return;
        listeners =  new ListenerAdapter[] {
                new InfoButtonClick(),
                new GuildMessage()
        };
        for (ListenerAdapter listener : listeners)
            jda.addEventListener(listener);
        size = listeners.length;
        if (size > 1)
            Main.getLogger().info(size + " listeners registered.");
        else
            Main.getLogger().info(size + " listener registered.");
    }

}
