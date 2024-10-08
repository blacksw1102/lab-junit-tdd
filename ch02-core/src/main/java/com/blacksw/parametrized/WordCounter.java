package com.blacksw.parametrized;

public class WordCounter {
    public int countWords(String sentence) {
        return sentence.split(" ").length;
    }
}
