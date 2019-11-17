/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasealgorithm;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tranhoang
 */
public class DatabaseAlgorithm {

    public String timBaoDong(List<String> phuThuocHam, String thuocTinh) {
        for (String phuThuocHam1 : phuThuocHam) {
            thuocTinh = timBaoDong(phuThuocHam1, thuocTinh);
        }

        return Sort(thuocTinh.split(""));
    }

    public String timBaoDong(String phuThuocHam, String thuocTinh) {
        String split[] = phuThuocHam.split("->");
        String head[] = split[0].split("");
        String tail[] = split[1].split("");
        for (String head1 : head) {
            if (thuocTinh.indexOf(head1) == -1) {
                return thuocTinh;
            }
        }
        for (String tail1 : tail) {
            if (thuocTinh.indexOf(tail1) == -1) {
                thuocTinh += tail1;
            }
        }
        return thuocTinh;
    }

    public String Sort(String s[]) {
        Arrays.sort(s);
        return toArr(s);
    }

    public String toArr(String s[]) {
        String aString = "";
        for (String s1 : s) {
            aString += s1;
        }
        return aString;
    }

    public String timKhoaToiThieu(List<String> phuThuocHam, String tapThuocTinh) {
        String tapThuocTinhArr[] = tapThuocTinh.split("");
        String tmp = "";
        String baoDong = "";
        for (int i = 0; i < tapThuocTinh.length(); i++) {
            tmp = "";
            for (int j = 0; j < tapThuocTinh.length(); j++) {
                if (i == j) {
                    continue;
                }
                tmp += tapThuocTinhArr[j];
            }
            baoDong = timBaoDong(phuThuocHam, tmp);
            if (baoDong.equals(tapThuocTinh)) {
                tapThuocTinhArr[i] = "";
            }
        }
        return toArr(tapThuocTinhArr);
    }

    public boolean xetTuongDuong(List<String> phuThuocHamF, List<String> phuThuocHamG, String tapThuocTinh){
        String tmp[];
        String tmpTail[];
        String baoDong;
        for (String phuThuocHamF1 : phuThuocHamF) {
            tmp = phuThuocHamF1.split("->");
            tmpTail = tmp[1].split("");
            baoDong = timBaoDong(phuThuocHamG, tmp[0]);
            for (String tmpTail1 : tmpTail) {
                if(baoDong.indexOf(tmpTail1) == -1){
                    return false;
                }
            }
        }
        for (String phuThuocHamG1 : phuThuocHamG) {
            tmp = phuThuocHamG1.split("->");
            tmpTail = tmp[1].split("");
            baoDong = timBaoDong(phuThuocHamF, tmp[0]);
            for (String tmpTail1 : tmpTail) {
                if(baoDong.indexOf(tmpTail1) == -1){
                    return false;
                }
            }
        }
        return true;
    }
    
}
