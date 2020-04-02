package com.davydov.telebot;

public enum Command {

    START("/start", "Hello!"),
    HELP("/help", "shows a list of commands."),
    CITIES("/cities", "shows a list of cities.");


    private final String command;
    private final String description;

    Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    static Command parse(String command) {
        //Command[] list = values();

        for (Command command1 : values()) {
            if (command1.command.equals(command)) {
                return command1;
            }
        }
        return null;
    }

    public Command[] getCommands() {
        return values();
    }

    public String getDescription() {
        return this.description;
    }

    public String getCommand() {
        return this.command;
    }

}
