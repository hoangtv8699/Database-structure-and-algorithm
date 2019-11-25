/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasealgorithm;

import java.util.ArrayList;
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

    public boolean xetTuongDuong(List<String> phuThuocHamF, List<String> phuThuocHamG, String tapThuocTinh) {
        String tmp[];
        String tmpTail[];
        String baoDong;
        for (String phuThuocHamF1 : phuThuocHamF) {
            tmp = phuThuocHamF1.split("->");
            tmpTail = tmp[1].split("");
            baoDong = timBaoDong(phuThuocHamG, tmp[0]);
            for (String tmpTail1 : tmpTail) {
                if (baoDong.indexOf(tmpTail1) == -1) {
                    return false;
                }
            }
        }
        for (String phuThuocHamG1 : phuThuocHamG) {
            tmp = phuThuocHamG1.split("->");
            tmpTail = tmp[1].split("");
            baoDong = timBaoDong(phuThuocHamF, tmp[0]);
            for (String tmpTail1 : tmpTail) {
                if (baoDong.indexOf(tmpTail1) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> timPhuKhongDuThua(List<String> phuThuocHamF, String tapThuocTinh) {
        List<String> phuKhongDuThua = new ArrayList<>(phuThuocHamF);
        for (int i = 0; i < phuKhongDuThua.size(); i++) {
            phuKhongDuThua.remove(i);
            if (!xetTuongDuong(phuKhongDuThua, phuThuocHamF, tapThuocTinh)) {
                phuKhongDuThua.add(i, phuThuocHamF.get(i));
            } else {
                i--;
            }
        }
        return phuKhongDuThua;
    }

    public List<String> timPhuToiThieu(List<String> phuThuocHamF, String tapThuocTinh) {
        List<String> tmp1 = bienDoiTapF(phuThuocHamF);
        List<String> tmp2 = loaiThuocTinhThua(tmp1, tapThuocTinh);
        List<String> tmp3 = timPhuKhongDuThua(tmp2, tapThuocTinh);
        return tmp3;
    }

    public List<String> bienDoiTapF(List<String> phuThuocHamF) {
        List<String> tmp = new ArrayList<>();
        String[] phuThuocHam;
        String head;
        String[] tail;

        for (int i = 0; i < phuThuocHamF.size(); i++) {
            phuThuocHam = phuThuocHamF.get(i).split("->");
            head = phuThuocHam[0];
            tail = phuThuocHam[1].split("");

            if (tail.length <= 1) {
                tmp.add(phuThuocHamF.get(i));
            } else {
                for (int j = 0; j < tail.length; j++) {
                    tmp.add(head + "->" + tail[j]);
                }
            }
        }
        return tmp;
    }

    public List<String> loaiThuocTinhThua(List<String> phuThuocHamF, String tapThuocTinh) {
        List<String> loaiThuocTinhThua = new ArrayList<>();
        String phuThuocHam;

        String baoDong;

        for (int i = 0; i < phuThuocHamF.size(); i++) {
            loaiThuocTinhThua.add(i, loaiThuocTinhThua(phuThuocHamF, phuThuocHamF.get(i), tapThuocTinh));
        }
        return loaiThuocTinhThua;
    }

    public String loaiThuocTinhThua(List<String> phuThuocHamF, String phuThuocHam, String tapThuocTinh) {
        String[] split = phuThuocHam.split("->");
        String[] head = split[0].split("");
        String tail = split[1];
        String tmp;
        String baoDong;

        if (head.length > 1) {
            for (int j = 0; j < head.length; j++) {
                tmp = "";
                for (int k = 0; k < head.length; k++) {
                    if (j == k) {
                        continue;
                    }
                    tmp += head[k];
                }
                baoDong = timBaoDong(phuThuocHamF, tmp);
                if (baoDong.indexOf(tail) == -1) {
                    continue;
                }
                head[j] = "";
            }
        }
        tmp = "";
        for (int k = 0; k < head.length; k++) {
            tmp += head[k];
        }
        tmp += "->" + tail;
        return tmp;
    }

    public boolean kiemTraMatThongTin(String R, List<String> Rn, List<String> F) {
        String[][] bangTest = new String[R.length()][Rn.size()];
        boolean re = false;

        for (int i = 0; i < R.length(); i++) {
            for (int j = 0; j < Rn.size(); j++) {
                bangTest[i][j] = "b" + (i + 1) + (j + 1);
            }
        }
        for (int i = 0; i < Rn.size(); i++) {
            String[] tmp = Rn.get(i).split("");
            for (String s : tmp) {
                bangTest[i][R.indexOf(s)] = "a" + (R.indexOf(s) + 1);
            }
        }
        for (int j = 0; j < F.size(); j++) {
            String[] head = F.get(j).split("->")[0].split("");
            String tail = F.get(j).split("->")[1];
            int tmp = -1;

            for (int i = 0; i < Rn.size(); i++) {
               if(kiemTraPTH(R, bangTest[i], head, tail)){
                   tmp = i;
                   break;
               }
            }
            DONGNHAT : for(int i = 0; i < Rn.size(); i++){
                if(i == tmp){
                    continue;
                }
                for(String s : head){
                    if(!bangTest[i][R.indexOf(s)].equals(bangTest[tmp][R.indexOf(s)])){
                        continue DONGNHAT;
                    }
                }
                bangTest[i][R.indexOf(tail)] = bangTest[tmp][R.indexOf(tail)];
            }

        }

        for (String[] strings : bangTest) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < R.length(); i++) {
            for (int j = 0; j < Rn.size(); j++) {
                if(!bangTest[i][j].split("")[0].endsWith("a")){
                    continue;
                }
                if(j == Rn.size() - 1){
                    re = true;
                    break;
                }
            }
        }
        return re;
    }

    public boolean kiemTraPTH(String R, String[] bangTest, String[] head, String tail) {
        for (String s : head) {
            if (!bangTest[R.indexOf(s)].split("")[0].equals("a")) {
                return false;
            }
        }
        if (!bangTest[R.indexOf(tail)].split("")[0].equals("a")) {
            return false;
        }
        return true;
    }
}
