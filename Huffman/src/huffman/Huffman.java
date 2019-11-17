/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author tranhoang
 */
import java.math.BigInteger;
import java.util.*;
import java.io.*;

class HuffmanNode {

    int freq;
    char c;
    String code = "";
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {

        return x.freq - y.freq;
    }
}

public class Huffman {

    static final int NUMBER_CHAR = 26;

    public static void encoding(HuffmanNode root) {
        if (root.left
                == null
                && root.right
                == null
                && Character.isLetter(root.c)) {
            return;
        }
        root.left.code = root.code + "0";
        root.right.code = root.code + "1";
        encoding(root.left);
        encoding(root.right);
    }

    public static void main(String[] args) {

        String data = "";
        String dataHuff = "";
        int freq[] = new int[NUMBER_CHAR];
        try {
            File f = new File("in.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                data += line;
            }
            fr.close();
            br.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: " + ex);
        }
        System.out.println(data);
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) >= 97 && data.charAt(i) <= 122) {
                freq[data.charAt(i) - 97]++;
            }
        }
        PriorityQueue<HuffmanNode> q
                = new PriorityQueue<HuffmanNode>(NUMBER_CHAR, new MyComparator());
        ArrayList<HuffmanNode> arr = new ArrayList<HuffmanNode>();
        for (int i = 0; i < NUMBER_CHAR; i++) {

            HuffmanNode hn = new HuffmanNode();
            if (freq[i] > 0) {
                hn.c = (char) (i + 97);
                hn.freq = freq[i];

                hn.left = null;
                hn.right = null;

                q.add(hn);
                arr.add(hn);
            }
        }

        HuffmanNode root = null;

        while (q.size() > 1) {

            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            f.freq = x.freq + y.freq;
            f.c = '-';

            f.left = y;

            f.right = x;

            root = f;

            q.add(f);
        }

        encoding(root);
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) >= 97 && data.charAt(i) <= 122) {
                for(int j = 0; j < arr.size(); j++){
                   if(arr.get(j).c == data.charAt(i)){
                       dataHuff += arr.get(j).code;
                   }
                }
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).c + " " + arr.get(i).code);
        }

        System.out.println(dataHuff);
        int thua = 8 - (dataHuff.length() % 8);
        for(int i = 0;i < thua;i++){
            dataHuff = "0" + dataHuff;
        }

        byte[] save = new byte[dataHuff.length()/8];
        int countSave = 0;
        //chuyen chuoi bit thanh mang byte
        String datahex = "";
        int dau1 = 0;
        int cuoi1 = 8;
        while (dau1 < dataHuff.length()){
            save[countSave] = (byte) Integer.parseInt(dataHuff.substring(dau1,cuoi1), 2);
            countSave++;
            dau1 = cuoi1;
            cuoi1 += 8;
        }


        //ghi file
        try {
            FileOutputStream fos = new FileOutputStream("b.bin");
            DataOutputStream dos = new DataOutputStream(fos);
            FileOutputStream fos2 = new FileOutputStream("code.bin");
            DataOutputStream dos2 = new DataOutputStream(fos2);
            dos2.writeInt(thua);

            for(int i = 0;i < arr.size();i++){
                dos2.writeBytes("\n" + arr.get(i).c + "\n" + arr.get(i).code);
            }

            dos.write(save);
            fos.close();
            dos.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        byte[] in = new byte[0];
        String dataIn = "";
        int thua2 = 0;
        ArrayList<HuffmanNode> arr2 = new ArrayList<HuffmanNode>();

        try {
            FileInputStream fis = new FileInputStream("b.bin");
            DataInputStream dis = new DataInputStream(fis);
            RandomAccessFile raf = new RandomAccessFile("code.bin", "rw");
            thua2 = raf.readInt();
            System.out.println(thua2);
            raf.seek(5);
            String tmp1 = raf.readLine();
            String tmp2 = raf.readLine();
            while(tmp2 != null){
                HuffmanNode hn = new HuffmanNode();

                    hn.c = tmp1.charAt(0);
                    hn.code = tmp2;

                    hn.left = null;
                    hn.right = null;

                    arr2.add(hn);
                tmp1 = raf.readLine();
                tmp2 = raf.readLine();
            }
            dis.readFully(in);
            raf.close();
            fis.close();
            dis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int countIn = 0;
        while(countIn < in.length ){
            int i = in[countIn];
            if(i < 0){
                i += 256;
            }
            String tmp = Integer.toBinaryString(i);
            countIn++;
            while (tmp.length() < 8){
                tmp = "0" + tmp;
            }
            dataIn += tmp;

        }
        dataIn = dataIn.substring(thua2);
        int batdau = 0;
        int ketthuc = 1;
        String decode = "";
        while(batdau < dataIn.length()){
            for(int j = 0;j < arr2.size(); j++){
                ketthuc = batdau + arr2.get(j).code.length();
                if(ketthuc > dataIn.length()){
                    break;
                }
                if(arr2.get(j).code.equals(dataIn.substring(batdau,ketthuc))){
                    decode += " " + arr2.get(j).c;
                    batdau = ketthuc;
                    break;
                }
            }
        }
        System.out.println(decode);
        int bytebandau = 0;
        for(int i = 0; i < arr.size();i++){
            bytebandau += arr.get(i).freq*8;
        }
        System.out.println("tong so bit ban dau: "+ bytebandau + "bits");
        int bytehuff = dataIn.length() + 4*8;
        for(int i = 0;i < arr2.size();i++){
            bytehuff += 1*8 + arr2.get(i).code.length()*8 + 4*8;
        }
        System.out.println("tong so bit sau khi huff: "+ bytehuff +"bits");

    }
}

