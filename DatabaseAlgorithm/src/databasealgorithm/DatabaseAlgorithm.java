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

    // đầu vào danh sách phụ thuộc hàm, đầu ra bao đóng của tập thuộc tính
    public String timBaoDong(List<String> phuThuocHam, String thuocTinh) {
        for (int i = 0; i < phuThuocHam.size(); i++) {
            for (String phuThuocHam1 : phuThuocHam) {
                thuocTinh = timBaoDong(phuThuocHam1, thuocTinh);
            }
        }

        return Sort(thuocTinh.split(""));
    }

    // đầu vào phụ thuộc hàm, đầu ra thuộc tính sau khi xét với phụ thuộc hàm
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

    // sắp xếp đầu ra
    public String Sort(String s[]) {
        Arrays.sort(s);
        return toArr(s);
    }

    // chuyển từ mảng sang String
    public String toArr(String s[]) {
        String aString = "";
        for (String s1 : s) {
            aString += s1;
        }
        return aString;
    }

    // đầu vào danh sách phụ thuộc hàm, tập thuộc tính đầy đủ, đầu ra khóa tối thiểu
    public String timKhoaToiThieu(List<String> phuThuocHam, String tapThuocTinh) {
        String tapThuocTinhArr[] = tapThuocTinh.split("");
        String tmp = "";
        String baoDong = "";

        // mảng tập thuộc tính sẽ là khóa tối thiểu, ban đầu chính bằng tập thuộc tính
        //xét bỏ từng thuộc tính trong tập thuộc tính, tìm bao đóng với tập phụ thuộc hàm
        // nếu bao đóng trùng với tập thuọc tính thì bỏ đi thuôc tính đó
        for (int i = 0; i < tapThuocTinh.length(); i++) {
            tmp = "";

            for (int j = 0; j < tapThuocTinh.length(); j++) {
                //loại bỏ thử từng thuộc tính
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

    // đầu vào 2 tập phụ thuộc hàm, đầu ra xem có tương đương không
    public boolean xetTuongDuong(List<String> phuThuocHamF, List<String> phuThuocHamG) {
        String tmp[];
        String tmpTail[];
        String baoDong;
        //xét từng phụ thuộc hàm của tập F có thuộc G+
        //chỉ cần 1 phụ thuộc hàm trong F không thuộc G+ hoặc ngược lại thì trả về false
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
        //xét từng phụ thuộc hàm của tập G có thuộc F+
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

    // đầu vào danh sách phụ thuộc hàm, đầu ra phủ không dư thừa
    public List<String> timPhuKhongDuThua(List<String> phuThuocHamF) {
        List<String> phuKhongDuThua = new ArrayList<>(phuThuocHamF);
        //danh sách phuKhongDuThua ban đầu bằng danh sách phuThuocHamF
        //loại bỏ từng phụ thuộc hàm và xét tương đương 2 danh sách nếu tương đương thì xét tiếp,ngược lại thì thêm trả
        for (int i = 0; i < phuKhongDuThua.size(); i++) {
            phuKhongDuThua.remove(i);
            if (!xetTuongDuong(phuKhongDuThua, phuThuocHamF)) {
                phuKhongDuThua.add(i, phuThuocHamF.get(i));
            } else {
                i--;
            }
        }
        return phuKhongDuThua;
    }

    //đầu vào tập phụ thuộc hàm, đầu ra phủ tối thiểu
    public List<String> timPhuToiThieu(List<String> phuThuocHamF) {
        //biến đổi tập F về dạng X->A trong đó A là 1 thuộc tính
        List<String> tmp1 = bienDoiTapF(phuThuocHamF);
        //loại đi các thuộc tính thừa trong X của X->A
        List<String> tmp2 = loaiThuocTinhThua(tmp1);
        // tìm phủ không dư thừa
        List<String> tmp3 = timPhuKhongDuThua(tmp2);
        return tmp3;
    }

    public List<String> bienDoiTapF(List<String> phuThuocHamF) {
        List<String> tmp = new ArrayList<>();
        String[] phuThuocHam;
        String head;
        String[] tail;

        //xét từng phụ thuộc hàm, nếu vế trái(tail) có nhiều hơn 2 thuộc tính thì tách hết ra
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

    // trả về tập phụ thuộc hàm không có thuộc tính thừa bên vế trái
    public List<String> loaiThuocTinhThua(List<String> phuThuocHamF) {
        List<String> loaiThuocTinhThua = new ArrayList<>();

        for (int i = 0; i < phuThuocHamF.size(); i++) {
            loaiThuocTinhThua.add(i, loaiThuocTinhThua(phuThuocHamF, phuThuocHamF.get(i)));
        }
        return loaiThuocTinhThua;
    }

    public String loaiThuocTinhThua(List<String> phuThuocHamF, String phuThuocHam) {
        String[] split = phuThuocHam.split("->");
        String[] head = split[0].split("");
        String tail = split[1];
        String tmp;
        String baoDong;

        // nếu vế trái nhiều hơn 2 thuộc tính thì tiến hành loại thuộc tính thừa nếu có thể
        if (head.length > 1) {
            for (int j = 0; j < head.length; j++) {
                tmp = "";

                for (int k = 0; k < head.length; k++) {
                    //loại bỏ thử từng thuộc tính
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

    // đầu vào tập thuộc tính đầy đủ R, tập phép tách Rn, tập phụ thuộc hàm F
    public String[][] kiemTraMatThongTin(String R, List<String> Rn, List<String> F) {
        String[][] bangTest = new String[Rn.size()][R.length()];

        // set các giá trị bảng test là b
        for (int i = 0; i < Rn.size(); i++) {
            for (int j = 0; j < R.length(); j++) {
                bangTest[i][j] = "b" + (i + 1) + (j + 1);
            }
        }

        //set các giá trị a theo tập phép tách
        for (int i = 0; i < Rn.size(); i++) {
            String[] tmp = Rn.get(i).split("");
            for (String s : tmp) {
                bangTest[i][R.indexOf(s)] = "a" + (R.indexOf(s) + 1);
            }
        }

        // đồng nhất các hệ số theo phụ thuộc hàm
        // vòng for đầu tiên để đảm bảo không phụ thuộc hàm nào bị bỏ sót
        // vòng for thứ 2 để đồng nhất
        for (int x = 0; x < F.size(); x++) {
            for (int j = 0; j < F.size(); j++) {
                String[] head = F.get(j).split("->")[0].split("");
                String tail = F.get(j).split("->")[1];
                int tmp = -1;
                
                // tìm vị trí theo phụ thuộc hàm mà tại các thuộc tính trong bảng là a
                // ví dụ có tập thuộc tính là ABCD , AB->D thì tìm vị trí mà cột A,B,D có giá trị là a
                for (int i = 0; i < Rn.size(); i++) {
                    if (kiemTraPTH(R, bangTest[i], head, tail)) {
                        tmp = i;
                        break;
                    }
                }
                if (tmp == -1) {
                    // nếu không có vị trí nào thỏa mã thì nhảy vòng lặp tiếp theo
                    continue;
                }
                
                //đồng nhất các hàng khác nếu vế trái của phụ thuộc hàm là a
                DONGNHAT:
                for (int i = 0; i < Rn.size(); i++) {
                    if (i == tmp) {
                        continue;
                    }
                    for (String s : head) {
                        if (!bangTest[i][R.indexOf(s)].equals(bangTest[tmp][R.indexOf(s)])) {
                            //chỉ cần 1 vị trí của vế trái không có giá trị a thì bỏ qua
                            // ví dụ AC->D thì nếu cột A tại hàng cần xét có giá trị b thì bỏ qua, nhảy sang vong lặp tiếp theo của DONGNHAT
                            continue DONGNHAT;
                        }
                    }
                    //đồng nhất giá trị
                    bangTest[i][R.indexOf(tail)] = bangTest[tmp][R.indexOf(tail)];
                }

            }
        }

        for (String[] strings : bangTest) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }

        return bangTest;
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

    public boolean test(String[][] bangTest, int row, int col) {
        boolean re = false;

        for (int i = 0; i < row; i++) {
            int tmp = 0;
            for (int j = 0; j < col; j++) {
                if (!bangTest[i][j].startsWith("a")) {
                    tmp++;
                }
                if (tmp == col) {
                    re = true;
                    break;
                }
            }
        }
        return re;
    }
}
