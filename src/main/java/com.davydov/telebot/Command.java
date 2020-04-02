package com.davydov.telebot;

import java.util.Arrays;
import java.util.List;

public enum Command {

    START("/start", "Hello!"),
    CITIES("/cities", "shows a list of cities."),
    // Arrays.asList чтобы не дописывать код, если захочу еще одну команду в описание добавить
    HELP("/help", Arrays.asList(CITIES));


    private final String command;
    private final String description;


    Command(String command, List<Command> list) {
        this.command = command;

        StringBuilder sb = new StringBuilder();
        for (Command c: list) {
            sb.append(c.getCommand().concat(" "))
                    .append(c.getDescription().concat("\n"));
        }
        this.description = sb.toString();

    }

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
