package net.uskript.discord.commands;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public abstract class CommandExecutor extends ListenerAdapter {

    public abstract CommandData getCommand();

}
