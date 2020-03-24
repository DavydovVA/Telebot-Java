package com.telebot;
import com.database.MongoDBPoperations;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;


public class Commands {
    // вообще переделатб надо бы
    private String[] commands = {"/start", "/help", "/cities"}; // надо брать из файла, или грузить из бд

    Commands() {}

    static String createStartString(String[] array) {
        StringBuilder str = new StringBuilder();

        for (String s : array) {
            String t = s + '\n';
            str.append(t);
        }

        return str.toString();
    }

    static String createString_fromCommands(String[] array) {
        StringBuilder str = new StringBuilder();

        for (int i = 2; i < array.length; i++){
            String t = array[i] + '\n';
            str.append(t);
        }

        return str.toString();
    }

    static String createCitiesString(MongoDBPoperations ops, MongoOperations mongoOp) {
        return ops.getCitiesList(mongoOp);
    }

    public String getDescription(String command, MongoDBPoperations ops, MongoOperations mongoOp) {
        // лень было делат по-нормальному, поэтому пока что свитч
        // изначально был хэш, но он инициализировался сразу, и в итоге, если вдруг список городов изменился,
        // то здесь не изменится, пока не пропишу изменение, и механизм изменения лень писать, хотя можно просто
        // сделать таблицу в бд для команд и грузить оттуда

        switch (command) {
            case "/help":
                return createString_fromCommands(this.commands);
            case "/start":
                return createStartString(this.commands);
            case "/cities":
                return createCitiesString(ops, mongoOp);
            default:
                return command + " not found.";
        }
    }
}
