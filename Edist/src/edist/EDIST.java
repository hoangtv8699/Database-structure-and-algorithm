// A Dynamic Programming based Java program to find minimum 
// number operations to convert str1 to str2
package edist;

import javax.xml.stream.events.EndDocument;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class EDIST {

    static int min(int x, int y, int z) {
        if (x <= y && x <= z) {
            return x;
        }
        if (y <= x && y <= z) {
            return y;
        } else {
            return z;
        }
    }

    static int editDistDP(String str1, String str2, int m, int n) {
        // Create a table to store results of subproblems 
        int dp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner 
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // neu i = 0 tuc chuoi 1 rong thi phai them tat ca cac phan tu cua chuoi 2 sang chuoi 1
                // => edist = j
                if (i == 0) {
                    dp[i][j] = j;  // Min. operations = j 
                } // neu j = 0 tuc chuoi 2 rong thi phai xoa het cac phan tu cua chuoi 1
                // => edist = i
                else if (j == 0) {
                    dp[i][j] = i; // Min. operations = i 
                } // neu 2 ki tu cuoi cua chuoi giong nhau thi khong phai lam gi ca,bo qua ki tu cuoi
                // => edist[i][j] = edist[i-1][j-1]
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } // neu ki tu cuoi cua 2 chuoi khac nhau thi xem xet tat ca cac truong hop
                // them, xoa, thay the co the xay ra va lay min
                else {
                    dp[i][j] = 1 + min(dp[i][j - 1], // them
                            dp[i - 1][j], // xoa
                            dp[i - 1][j - 1]); // thay the
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String args[]) {
        LinkedList<String> dictionary = new LinkedList();
        String document = null;
        String[] word;
        //doc dictionary va document
        try {
            String inputLineH;
            FileReader dic = new FileReader("dic.txt");
            BufferedReader dicRead = new BufferedReader(dic);
            while ((inputLineH = dicRead.readLine()) != null) {
                dictionary.add(inputLineH);
            }
            FileReader doc = new FileReader("document.txt");
            BufferedReader docRead = new BufferedReader(doc);
            document = (String) docRead.readLine();
            docRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // xoa cac ki tu dac biet
        word = document.split("[\\s]");
        System.out.println(document);
        //vet can dictionary
        for (int i = 0; i < word.length; i++) {
            if (word[i].matches("(.*)[A-Z](.*)|(.*)[\\d*](.*)|(.*)[^a-zA-Z_0-9\\s](.*)")) {
                continue;
            }
            String[] recommed1 = new String[3];
            int[] recommed2 = new int[3];
            Arrays.fill(recommed1, null);
            Arrays.fill(recommed2, 1000);
            for (int j = 0; j < dictionary.size(); j++) {
                if (word[i].equals(dictionary.get(j))) {
                    break;
                } else {
                    int edist = editDistDP(word[i], dictionary.get(j).toLowerCase(), word[i].length(), dictionary.get(j).length());
               
                    if (edist < recommed2[0]) {
                        recommed1[2] = recommed1[1];
                        recommed1[1] = recommed1[0];
                        recommed1[0] = dictionary.get(j);
                        recommed2[2] = recommed2[1];
                        recommed2[1] = recommed2[0];
                        recommed2[0] = edist;
                    } else {
                        if (edist < recommed2[1]) {
                            recommed1[2] = recommed1[1];
                            recommed1[1] = dictionary.get(j);
                            recommed2[2] = recommed2[1];
                            recommed2[1] = edist;

                        } else {
                            if (edist < recommed2[2]) {
                                recommed1[2] = dictionary.get(j);
                                recommed2[2] = edist;
                            }
                        }
                    }

                    if (j == dictionary.size() - 1) {
                        System.out.println("word " + word[i] + " wrong,recommend:");
                        for (String str : recommed1) {
                            System.out.println(str);
                        }
                    }
                }
            }

        }

    }
}
