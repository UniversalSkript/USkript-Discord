package net.uskript.discord.commands;

import fr.belinguier.java.compiler.ClassFile;
import fr.belinguier.java.compiler.constant.ConstantUtf8;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.uskript.discord.Main;
import org.jetbrains.annotations.NotNull;

public class CompilerCommand extends CommandExecutor {

    @Override
    public CommandData getCommand() {
        CommandData commandData = new CommandData("compile", "Use uskript compiler.");
        SubcommandData subcommandData = new SubcommandData("class", "Compile class.");

        subcommandData.addOption(OptionType.STRING, "name", "Class's name", true);
        subcommandData.addOption(OptionType.STRING, "message", "Class's secret message in constant pool.");
        commandData.addSubcommands(subcommandData);
        return commandData;
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        final Member sender = event.getMember();
        final ClassFile classFile;
        final OptionMapping className;
        final OptionMapping message;

        if (sender != null && sender.getIdLong() == Main.getBotUser().getIdLong())
            return;
        else if (!event.getName().equals("compile") || !event.getSubcommandName().equals("class"))
            return;
        className = event.getOption("name");
        message = event.getOption("message");
        if (className == null)
            return;
        classFile = new ClassFile(className.getAsString(), "fr/mrcubee/virtual");
        classFile.getConstantPool().registerConstant(new ConstantUtf8("Author: " + event.getMember().getUser().getName()));
        if (message != null)
            classFile.getConstantPool().registerConstant(new ConstantUtf8("Message: " + message.getAsString()));
        event.reply("Result:").addFile(classFile.serialize(), className.getAsString() + ".class").queue();
    }
}
