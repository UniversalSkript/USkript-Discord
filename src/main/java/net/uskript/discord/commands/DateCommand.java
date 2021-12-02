package net.uskript.discord.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.uskript.discord.Main;
import org.jetbrains.annotations.NotNull;

public class DateCommand extends CommandExecutor {

    @Override
    public CommandData getCommand() {
        return new CommandData("date", "Get prototype date !");
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        Member sender = event.getMember();

        if (sender != null && sender.getIdLong() == Main.getBotUser().getIdLong())
            return;
        else if (!event.getName().equals("date"))
            return;
        event.getChannel().sendTyping().queue();
        event.reply("Very soon. For more information, please ask <@211534639045541890>.").queue();
    }
}
