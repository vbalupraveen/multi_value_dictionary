package com.ulab.spreetail.multi_value_dictionary.service;

import com.ulab.spreetail.multi_value_dictionary.exception.KeyNotExistException;
import com.ulab.spreetail.multi_value_dictionary.exception.MemberNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DictionaryServiceTest {
    DictionaryService dictionaryService;

    @BeforeEach
    void setUp() {
        dictionaryService = new DictionaryServiceImpl();
    }

    @Test
    void keys() throws Exception {
        dictionaryService.add("foo", "bar");
        dictionaryService.add("bar", "foo");
        assertThat(dictionaryService.keys().size()).isEqualTo(2);
    }

    @Test
    void members() throws Exception {
        dictionaryService.add("foo", "bar");
        dictionaryService.add("foo", "baz");
        assertThat(dictionaryService.members("foo").size()).isEqualTo(2);
    }

    @Test
    void add() throws Exception {
        dictionaryService.add("foo", "bar");
        assertThat(dictionaryService.keyExists("foo")).isTrue();
        assertThat(dictionaryService.keyExists("bar")).isFalse();
    }

    @Test
    void remove() throws Exception {
        dictionaryService.add("foo", "bar");
        dictionaryService.remove("foo", "bar");
        assertThat(dictionaryService.memberExists("foo", "bar")).isFalse();
        assertThat(dictionaryService.keyExists("foo")).isFalse();
        assertThrows(KeyNotExistException.class, () -> {
            dictionaryService.remove("bar", "foo");
        });
        dictionaryService.add("foo", "baz");
        assertThrows(MemberNotExistException.class, () -> {
            dictionaryService.remove("foo", "bar");
        });
    }


    @Test
    void removeAll() throws Exception {
        dictionaryService.add("foo", "bar");
        dictionaryService.add("foo", "baz");
        dictionaryService.removeAll("foo");
        assertThat(dictionaryService.keyExists("foo")).isFalse();
    }

    @Test
    void clear() throws Exception {
        dictionaryService.add("foo", "bar");
        dictionaryService.add("bar", "foo");
        assertThat(dictionaryService.items().size()).isEqualTo(2);
        dictionaryService.clear();
        assertThat(dictionaryService.items().size()).isEqualTo(0);
    }


    @Test
    void allMembers() throws Exception {
        dictionaryService.add("foo", "bar");
        dictionaryService.add("foo", "baz");
        dictionaryService.add("bar", "foo");
        assertThat(dictionaryService.allMembers().size()).isEqualTo(3);
    }

}