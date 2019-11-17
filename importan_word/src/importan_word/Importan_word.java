/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importan_word;

import com.sun.java.accessibility.util.EventID;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Hiddenpants-H
 */
public class Importan_word {

    public static HashMap<String, Integer> dict = new HashMap<>();
    public static ArrayList<word> wordArrayList = new ArrayList<>();
    public static HashSet<String> stopWord = new HashSet<>();

    public static boolean is_upcase(String word) {
        if (word.length() <= 0) {
            return false;
        }
        boolean rs = false;
        if (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
            rs = true;
        }
        return rs;
    }

    public static void get_freq(String[] words) {
        String importanWord = "";
        boolean start_sentence = true;

        for (int i = 0; i < words.length; i++) {
            if (!start_sentence && is_upcase(words[i]) && !stopWord.contains(words[i].toLowerCase())) {

                start_sentence = words[i].endsWith(".");
                importanWord += words[i].replaceAll("\\.", "");
                if (dict.containsKey(words[i].replaceAll("\\.", ""))) {
                    dict.replace(words[i].replaceAll("\\.", ""), dict.get(words[i].replaceAll("\\.", "")) + 1);
                } else {
                    dict.put(words[i].replaceAll("\\.", ""), 1);
                }
                if (i + 1 < words.length - 1) {
                    while (is_upcase(words[++i]) && !start_sentence) {

                        start_sentence = words[i].endsWith(".");
                        importanWord += " " + words[i].replaceAll("\\.", "");

                        if (dict.containsKey(words[i].replaceAll("\\.", ""))) {
                            dict.replace(words[i].replaceAll("\\.", ""), dict.get(words[i].replaceAll("\\.", "")) + 1);
                        } else {
                            dict.put(words[i].replaceAll("\\.", ""), 1);
                        }
                        if (dict.containsKey(importanWord)) {
                            dict.replace(importanWord, dict.get(importanWord) + 1);
                        } else {
                            dict.put(importanWord, 1);
                        }
                    }
                }

                i--;

            } else {
                start_sentence = false;
                importanWord = "";

            }
        }
        dict.forEach((key, value) -> {
            word w = new word();
            w.freq = value;
            w.word = key;
            wordArrayList.add(w);
        });
        wordArrayList.sort(new Comparator<word>() {
            @Override
            public int compare(word m1, word m2) {
                return m2.freq - m1.freq;
            }
        });
    }

    public static void main(String[] args) {
        String[] words = null;
        try {
            File f1 = new File("test2.txt");
            FileReader fr1 = new FileReader(f1);

            BufferedReader br1 = new BufferedReader(fr1);

            String line1, line2;
            String line = "";
            while ((line1 = br1.readLine()) != null) {
                line += line1;
            }
            line = line.replaceAll("[^a-zA-Z_0-9\\.]", " ");
            words = line.split(" ");
            fr1.close();
            br1.close();

            File f2 = new File("test.txt");
            FileReader fr2 = new FileReader(f2);

            BufferedReader br2 = new BufferedReader(fr2);

            while ((line2 = br2.readLine()) != null) {
                stopWord.add(line2.toLowerCase());
            }

            fr1.close();
            br1.close();
            fr2.close();
            br2.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: " + ex);
        }
        get_freq(words);
        for (word w : wordArrayList) {
            System.out.println(w.freq + " " + w.word);
        }

    }

}
