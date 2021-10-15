package com.ulab.spreetail.multi_value_dictionary.service;

import com.ulab.spreetail.multi_value_dictionary.exception.KeyNotExistException;
import com.ulab.spreetail.multi_value_dictionary.exception.MemberAlreadyExistException;
import com.ulab.spreetail.multi_value_dictionary.exception.MemberNotExistException;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService<String, String> {

    ConcurrentHashMap<String, List<String>> dictMap;

    public DictionaryServiceImpl() {
        this.dictMap = new ConcurrentHashMap<>();
    }


    @Override
    public List<String> keys() {
        return new ArrayList<>(dictMap.keySet());
    }


    @Override
    public List<String> members(String key) throws KeyNotExistException {
        if (!keyExists(key))
            throw new KeyNotExistException();
        return dictMap.get(key);
    }

    @Override
    public void add(String key, String value) throws MemberAlreadyExistException {
        if (memberExists(key, value))
            throw new MemberAlreadyExistException();
        if (!keyExists(key))
            dictMap.put(key, new ArrayList<>());
        dictMap.get(key).add(value);
    }

    @Override
    public void remove(String key, String value) throws MemberNotExistException, KeyNotExistException {
        if (!keyExists(key))
            throw new KeyNotExistException();
        if (!memberExists(key, value))
            throw new MemberNotExistException();
        dictMap.get(key).remove(value);
        if (dictMap.get(key).size() == 0)
            dictMap.remove(key);
    }

    @Override
    public void removeAll(String key) throws KeyNotExistException {
        if (!keyExists(key))
            throw new KeyNotExistException();
        dictMap.remove(key);
    }

    @Override
    public void clear() {
        dictMap.clear();
    }

    @Override
    public boolean keyExists(String key) {
        return dictMap.containsKey(key);
    }

    @Override
    public boolean memberExists(String key, String value) {
        return keyExists(key) && dictMap.get(key).contains(value);
    }

    @Override
    public List<String> allMembers() {
        return dictMap.values().stream().flatMap(v -> v.stream()).collect(Collectors.toList());
    }

    @Override
    public List<Pair<String, String>> items() {
        Set<Map.Entry<String, List<String>>> entries = dictMap.entrySet();
        List<Pair<String, String>> iEntries = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : entries) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (String value : values) {
                iEntries.add(new Pair<>(key, value));
            }
        }
        return iEntries;
    }
}
