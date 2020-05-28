package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                mostFrequentWords(getStringFromFile("C:\\Users\\Rottenberg\\Downloads\\Vibrio_cholerae.txt" ),  5).toString().replaceAll(",","")
                );
        // write your code here
    }

    public static String reverseComplement(String input){
        StringBuilder builder = new StringBuilder();

        for(int i = input.length() - 1 ; i >= 0 ; i--){
            char currentChar = input.charAt(i);
            switch (currentChar) {
                case 'A', 'a' -> builder.append("T");
                case 'C', 'c' -> builder.append("G");
                case 'G', 'g' -> builder.append("C");
                case 'T', 't' -> builder.append("A");
                default -> System.out.println("Not a valid DNA sequence");
            }
        }

        return builder.toString();
    }

    public static String getStringFromFile(String filename){
        try {
            FileInputStream fis = new FileInputStream(filename);
            return IOUtils.toString(fis, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static int PatternCount(String Text, String Pattern){
        int count = 0;
        for (int i = 0; i < (Text.length() - Pattern.length() + 1); i++ ){
            String subString = Text.substring(i, i + Pattern.length());
            if (subString.equals(Pattern)){
                count++;
            }
        }
        return count;
    }
    public static List<Integer> patternPosition(String Text, String Pattern){
        List<Integer> results = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < (Text.length() - Pattern.length() + 1); i++ ){
            String subString = Text.substring(i, i + Pattern.length());
            if (subString.equals(Pattern)){
                results.add(i);
                count++;
            }
        }
        return results;
    }

    public static List<String> mostFrequentWords(String Text, int kmerLength){
        List<String> results = new ArrayList<>();
        HashMap<String, Integer> maps = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < (Text.length() - kmerLength + 1); i++ ){
            String subString = Text.substring(i, i + kmerLength);
            Integer count = maps.get(subString);
            count = count == null ? 0 : count;
            maps.put(subString, ++count);
            if (count >= maxCount){
                maxCount = count;
            }
        }
        final int finalMax = maxCount;
        maps.forEach( (key, value) -> {
            if (value == finalMax){
                results.add(key);
            }
        });
        return results;
    }
}
