package by.epam.lukyanau.rentService.controller.command;

import by.epam.lukyanau.rentService.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.REGISTRATION, new PassingToRegistration());
        commands.put(CommandName.SAVENEWUSER, new SignUp());
        commands.put(CommandName.GOTOSIGNINPAGE,new PassingToSignUp());
        commands.put(CommandName.GOTOERRORPAGE,new PassingToErrorPage());
        commands.put(CommandName.GOTOHOMEPAGE,new SignIn());
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }
}
