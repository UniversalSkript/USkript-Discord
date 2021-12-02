package net.uskript.discord.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.uskript.discord.Main;

public class RegisterCommands {



    private static void registerCommand(final JDA jda, final CommandExecutor[] executors) {
        final CommandListUpdateAction commandUpdate;

        if (executors.length < 1)
            return;
        commandUpdate = jda.updateCommands();
        for (CommandExecutor executor : executors) {
            commandUpdate.addCommands(executor.getCommand());
            jda.addEventListener(executor);
        }
        commandUpdate.queue(commands -> {
            final int size = commands.size();

            if (size > 1)
                Main.getLogger().info(size + " commands registered.");
            else
                Main.getLogger().info(size + " command registered.");
        });
        commandUpdate.queue();
    }

    public static void register(final JDA jda) {
        final CommandExecutor[] executors;

        if (jda == null)
            return;
        executors =  new CommandExecutor[] {
                new CompilerCommand(),
                new DateCommand()
        };
        jda.retrieveCommands().queue(commands -> {
            for (Command command : commands) {
                if (jda.equals(command.getJDA()))
                    command.delete().queue();
            }
            registerCommand(jda, executors);
        });
    }

}
