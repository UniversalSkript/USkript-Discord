package net.uskript.discord.listeners;

import net.dv8tion.jda.api.entities.MessageReference;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class InfoButtonClick extends ListenerAdapter {

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        MessageReference message;

        if (!event.getButton().getLabel().equalsIgnoreCase("info"))
            return;
        message = event.getMessage().getMessageReference();
        if (message == null)
            return;
        event.getMessage().reply("Sorry, my module is not yet installed.").queueAfter(1, TimeUnit.SECONDS);
    }

}
