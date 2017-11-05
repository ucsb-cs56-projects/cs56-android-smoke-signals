package com.konukoii.smokesignals.api;

import java.util.HashMap;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class CommandManager {
    private HashMap<String, Command> commandMap;

    public final static String[] commands = {
            "Battery",
            "Bluetooth",
            "Calls",
            "Contact",
            "Help",
            "Joke",
            "Location",
            "Powersave",
            "Ring",
            "SMS",
            "Whitelist",
            "Wifi"
    };

    private static HashMap<String, Command> getCommands() {
        HashMap<String, Command> map = new HashMap<String, Command>();
        for(String command : commands) {
            Class<?> commandClass;
            try {
                commandClass = Class.forName("com.konukoii.smokesignals.api.commands." + command + "Command");
            } catch(ClassNotFoundException ex) {
                continue;
            }

            Command action;
            try {
                action = (Command) commandClass.newInstance();
            } catch(Exception ex) {
                continue;
            }

            map.put(command, action);
        }

        return map;
    }

    public CommandManager() {
        commandMap = getCommands();
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}
