package com.ulab.spreetail.multi_value_dictionary.service;

import com.ulab.spreetail.multi_value_dictionary.exception.KeyNotExistException;
import com.ulab.spreetail.multi_value_dictionary.exception.MemberAlreadyExistException;
import com.ulab.spreetail.multi_value_dictionary.exception.MemberNotExistException;
import javafx.util.Pair;

import java.util.List;

public interface DictionaryService<K, V> {
    /**
     * @return all the keys in the dictionary. Order is not guaranteed.
     */
    List<V> keys();

    /**
     * @param key String
     * @return the collection of strings for the given key. Return order is not guaranteed. Returns an error if the key does not exists.
     */
    List<V> members(K key) throws KeyNotExistException;

    /**
     * Adds a member to a collection for a given key. Displays an error if the member already exists for the key.
     *
     * @param key   String
     * @param value String
     */

    void add(K key, V value) throws MemberAlreadyExistException;

    /**
     * Removes a member from a key. If the last member is removed from the key, the key is removed from the dictionary. If the key or member does not exist, displays anerror.
     *
     * @param key   String
     * @param value String
     */
    void remove(K key, V value) throws MemberNotExistException, KeyNotExistException;

    /**
     * Removes all members for a key and removes the key from the dictionary. Returns an error if the key does not exist.
     *
     * @param key String
     */
    void removeAll(K key) throws KeyNotExistException;

    /**
     * Removes all keys and all members from the dictionary.
     */
    void clear();

    /**
     * Returns whether a key exists or not.
     *
     * @param key String
     * @return boolean
     */
    boolean keyExists(K key);

    /**
     * Returns whether a member exists within a key. Returns false if the key does not exist.
     *
     * @param key   String
     * @param value String
     * @return boolean
     */
    boolean memberExists(K key, V value);

    /**
     * Returns all the members in the dictionary. Returns nothing if there are none. Order is not guaranteed.
     *
     * @return Collection<String>
     */
    List<V> allMembers();

    /**
     * Returns all keys in the dictionary and all of their members. Returns nothing if there are none. Order is not guaranteed.
     *
     * @return List<Pair < String, String>>
     */
    List<Pair<K, V>> items();
}
