package com.ulab.spreetail.multi_value_dictionary;

import com.ulab.spreetail.multi_value_dictionary.handler.DictionaryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DictionaryCommands {
    @Autowired
    DictionaryHandler dictionaryHandler;

    @ShellMethod(key = {"keys", "KEYS"}, value = "Prints all Keys")
    public void keys() {
        dictionaryHandler.keys();
    }

    @ShellMethod(key = {"members", "MEMBERS"}, value = "Prints all Members of a Key")
    public void members(String key) {
        dictionaryHandler.members(key);
    }

    @ShellMethod(key = {"add", "ADD"}, value = "Add Key, Member in memory")
    public void add(String key, String value) {
        dictionaryHandler.add(key, value);
    }

    @ShellMethod(key = {"remove", "REMOVE"}, value = "Removes Key, Member from memory")
    public void remove(String key, String value) {
        dictionaryHandler.remove(key, value);
    }

    @ShellMethod(key = {"removeall", "REMOVEALL"}, value = "Removes all Members of a Key")
    public void removeAll(String key) {
        dictionaryHandler.removeAll(key);
    }

    @ShellMethod(key = {"clr", "CLEAR"}, value = "Clears the memory")
    public void clear() {
        dictionaryHandler.clear();
    }

    @ShellMethod(key = {"keyexists", "KEYEXISTS"}, value = "Checks if Key exists")
    public void keyExists(String key) {
        dictionaryHandler.keyExists(key);
    }

    @ShellMethod(key = {"memberexists", "MEMBEREXISTS"}, value = "Checks if Key, Value exists")
    public void memberExists(String key, String value) {
        dictionaryHandler.memberExists(key, value);
    }

    @ShellMethod(key = {"allmembers", "ALLMEMBERS"}, value = "Prints Members of all Keys")
    public void allMembers() {
        dictionaryHandler.allMembers();
    }

    @ShellMethod(key = {"items", "ITEMS"}, value = "Prints Members of all Key, Values")
    public void items() {
        dictionaryHandler.items();
    }
}
