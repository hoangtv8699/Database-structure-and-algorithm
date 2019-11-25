/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasealgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tranhoang
 */
public class Main {

    public static void main(String[] args) {
        List<String> phuThuocHam = new ArrayList<String>();
//        phuThuocHam.add("AB->C");
//        phuThuocHam.add("BC->AD");
//        phuThuocHam.add("D->E");
//        phuThuocHam.add("CF->B");
        ////////
//        phuThuocHam.add("A->D");
//        phuThuocHam.add("AB->DE");
//        phuThuocHam.add("CE->G");
//        phuThuocHam.add("E->H");
        ///////
//        String thuocTinh = "AB";
//        String baoDong = new DatabaseAlgorithm().timBaoDong(phuThuocHam, thuocTinh);
//        System.out.println(baoDong);
        //////
//        phuThuocHam.add("AB->C");
//        phuThuocHam.add("AC->B");
//        phuThuocHam.add("BC->DE");
//        
//        String tapThuocTinh = "ABCDE";
//        System.out.println(new DatabaseAlgorithm().timKhoaToiThieu(phuThuocHam, tapThuocTinh));

//        List<String> phuThuocHamF = new ArrayList<String>();
//        List<String> phuThuocHamG = new ArrayList<String>();
        String tapPhuThuocHam = "ABCDEF";
//        phuThuocHamF.add("AB->C");
//        phuThuocHamF.add("D->EF");
//        phuThuocHamF.add("C->BD");
//        phuThuocHamG.add("AC->B");
//        phuThuocHamG.add("D->EF");
//        phuThuocHamG.add("B->CD");
        //////
//        phuThuocHamF.add("A->BC");
//        phuThuocHamF.add("A->D");
//        phuThuocHamF.add("CD->E");
//        phuThuocHamG.add("A->BCE");
//        phuThuocHamG.add("A->ABD");
//        phuThuocHamG.add("CD->E");


//        phuThuocHam.add("A->B");
//        phuThuocHam.add("ABCD->E");
//        phuThuocHam.add("EF->G");
//        phuThuocHam.add("ACDF->EG");
//        
        String R = "ABCD";
        List<String> Rn = new ArrayList<>();
        Rn.add("AB");
        Rn.add("BD");
        Rn.add("ABC");
        Rn.add("BCD");
        List<String> F = new ArrayList<>();
        F.add("A->C");
        F.add("B->C");
        F.add("CD->B");
        F.add("C->D");
              
        System.out.println(new DatabaseAlgorithm().kiemTraMatThongTin(R, Rn, F));

       
                  
    }

}
