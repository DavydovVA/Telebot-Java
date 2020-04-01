package com.davydov.telebot;

public enum Command {

    START("/start", "Hello!"),
    HELP("/help", "mem"),
    CITIES("/cities", "list");


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

    public String getDescription() {
        return this.description;
    }

    public String getCommand() {
        return this.command;
    }

}
