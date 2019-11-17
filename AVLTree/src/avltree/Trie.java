package avltree;
import java.util.ArrayList;

// Java implementation of search and insert operations
// on Trie 
public class Trie {

    static final int ALPHABET_SIZE = 26;

    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;

        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode root = new TrieNode();


    static void insert(String key)
    {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if(index < 0 || index > 25) continue;
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }
        pCrawl.isEndOfWord = true;
    }
    static boolean search(String key, ArrayList<String> search)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }
        search(pCrawl, key, search);
        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    static void search(TrieNode pCrawl, String key, ArrayList<String> search)
    {
        //System.out.println(key);
        if (search.size() >= 5) {
            return;
        }

        if(pCrawl != null && pCrawl.isEndOfWord){
            search.add(key);
        }

        for (int i = 0; i < 26 ; i++){
            if(pCrawl.children[i] !=  null){
                search(pCrawl.children[i], key + (char)(i + 'a'), search);
            }
        }


    }

}
