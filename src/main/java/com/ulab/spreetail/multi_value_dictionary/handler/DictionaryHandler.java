package com.ulab.spreetail.multi_value_dictionary.handler;

import com.ulab.spreetail.multi_value_dictionary.exception.KeyNotExistException;
import com.ulab.spreetail.multi_value_dictionary.exception.MemberAlreadyExistException;
import com.ulab.spreetail.multi_value_dictionary.exception.MemberNotExistException;
import com.ulab.spreetail.multi_value_dictionary.service.DictionaryService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class DictionaryHandler {

    @Autowired
    DictionaryService dictionaryService;

    public void keys() {
        List<String> keys = dictionaryService.keys();
        if (CollectionUtils.isEmpty(keys)) {
            System.out.println("(empty set)");
            return;
        }
        for (int i = 0; i < keys.size(); i++) {
            System.out.println((i + 1) + "." + keys.get(i));
        }
    }

    public void members(String key) {
        try {
            List<String> members = dictionaryService.members(key);
            for (int i = 0; i < members.size(); i++) {
                System.out.println((i + 1) + "." + members.get(i));
            }
        } catch (KeyNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(String key, String value) {
        try {
            dictionaryService.add(key, value);
            System.out.println("Added");
        } catch (MemberAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(String key, String value) {
        try {
            dictionaryService.remove(key, value);
            System.out.println("Removed");
        } catch (MemberNotExistException | KeyNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeAll(String key) {
        try {
            dictionaryService.removeAll(key);
            System.out.println("Removed");
        } catch (KeyNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clear() {
        dictionaryService.clear();
        System.out.println("Cleared");
    }

    public void keyExists(String key) {
        System.out.println(dictionaryService.keyExists(key));
    }

    public void memberExists(String key, String value) {
        System.out.println(dictionaryService.memberExists(key, value));
    }

    public void allMembers() {
        List<String> allMembers = dictionaryService.allMembers();
        if (CollectionUtils.isEmpty(allMembers)) {
            System.out.println("(empty set)");
        }
        for (int i = 0; i < allMembers.size(); i++) {
            System.out.println((i + 1) + "." + allMembers.get(i));
        }
    }

    public void items() {
        List<Pair<String, String>> items = dictionaryService.items();
        if (CollectionUtils.isEmpty(items)) {
            System.out.println("(empty set)");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i).getKey() + ":" + items.get(i).getValue());
        }
    }
}
