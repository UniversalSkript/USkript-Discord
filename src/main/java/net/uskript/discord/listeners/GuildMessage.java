package net.uskript.discord.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReference;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import net.uskript.discord.Main;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class GuildMessage extends ListenerAdapter {


    private void propose(final GuildMessageReceivedEvent event, final Message.Attachment attachment) {
        event.getMessage().reply("Skript detected. Would you like to do something special with the **" + attachment.getFileName() + "** file?").setActionRow(Button.primary("info", "info")).queue();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Member sender = event.getMember();

        if (sender != null && sender.getIdLong() == Main.getBotUser().getIdLong())
            return;
        for (Message.Attachment attachment : event.getMessage().getAttachments()) {
            if (attachment.getFileExtension().equalsIgnoreCase("sk"))
                propose(event, attachment);
        }
    }

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        MessageReference message;

        if (!event.getButton().getLabel().equalsIgnoreCase("info"))
            return;
        message = event.getMessage().getMessageReference();
        if (message == null)
            return;
        message.getChannel().sendTyping().queue();
        event.getMessage().reply("Sorry, my module is not yet installed.").queueAfter(1, TimeUnit.SECONDS);
    }
}
