/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasealgorithm;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hiddenpants-H
 */
public class MainFrame extends javax.swing.JFrame {

    public DatabaseAlgorithm databaseAlgorithm = new DatabaseAlgorithm();

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        output.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        timBaoDong = new javax.swing.JRadioButton();
        timKhoaToiThieu = new javax.swing.JRadioButton();
        timPhuKhongDuThua = new javax.swing.JRadioButton();
        timPhuToiThieu = new javax.swing.JRadioButton();
        input = new javax.swing.JTextField();
        find = new javax.swing.JButton();
        output = new javax.swing.JTextField();
        kiemTraMatMatThongTin = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangTest = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        buttonGroup1.add(timBaoDong);
        timBaoDong.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        timBaoDong.setText("Tìm bao đóng");

        buttonGroup1.add(timKhoaToiThieu);
        timKhoaToiThieu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        timKhoaToiThieu.setText("Tìm khóa tối thiểu");

        buttonGroup1.add(timPhuKhongDuThua);
        timPhuKhongDuThua.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        timPhuKhongDuThua.setText("Tìm phủ không dư thừa");

        buttonGroup1.add(timPhuToiThieu);
        timPhuToiThieu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        timPhuToiThieu.setText("Tìm phủ tối thiểu");

        input.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        find.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        find.setText("Tìm kiếm");
        find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findActionPerformed(evt);
            }
        });

        output.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        buttonGroup1.add(kiemTraMatMatThongTin);
        kiemTraMatMatThongTin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kiemTraMatMatThongTin.setText("Kiểm tra mất mát thông tin");

        bangTest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(bangTest);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timPhuKhongDuThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timKhoaToiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timBaoDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timPhuToiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kiemTraMatMatThongTin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(find, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(timBaoDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timKhoaToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(find, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timPhuKhongDuThua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timPhuToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kiemTraMatMatThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findActionPerformed
        choose();
    }//GEN-LAST:event_findActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    public void choose() {
        try {
            if (timBaoDong.isSelected()) {
                timBaoDong();
            } else if (timKhoaToiThieu.isSelected()) {
                timKhoaToiThieu();
            } else if (timPhuKhongDuThua.isSelected()) {
                timPhuKhongDuThua();
            } else if (timPhuToiThieu.isSelected()) {
                timPhuToiThieu();
            } else if (kiemTraMatMatThongTin.isSelected()) {
                kiemTraMatMatThongTin();
            } else{
                JOptionPane.showMessageDialog(new JFrame(), "Mời bạn chọn chức năng");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "nhập không đúng định dạng dữ liệu!");
        }
    }

    public void timBaoDong() {
        String text = input.getText().toUpperCase();
        String thuocTinh = text.split(", ")[0];
        String[] temp = text.split(", ");
        List<String> phuThuocHam = new ArrayList<>();
        String baoDong;

        for (int i = 1; i < temp.length; i++) {
            phuThuocHam.add(temp[i]);
        }
        baoDong = databaseAlgorithm.timBaoDong(phuThuocHam, thuocTinh);
        output.setText(baoDong);
    }

    public void timKhoaToiThieu() {
        String text = input.getText().toUpperCase();
        String thuocTinh = text.split(", ")[0];
        String[] temp = text.split(", ");
        List<String> phuThuocHam = new ArrayList<>();
        String khoaToiThieu;

        for (int i = 1; i < temp.length; i++) {
            phuThuocHam.add(temp[i]);
        }
        khoaToiThieu = databaseAlgorithm.timKhoaToiThieu(phuThuocHam, thuocTinh);
        output.setText(khoaToiThieu);
    }

    public void timPhuKhongDuThua() {
        String text = input.getText().toUpperCase();
        String[] temp = text.split(", ");
        List<String> phuThuocHam = new ArrayList<>();
        List<String> phuKhongDuThua;
        String khoa = "";

        for (int i = 0; i < temp.length; i++) {
            phuThuocHam.add(temp[i]);
        }
        phuKhongDuThua = databaseAlgorithm.timPhuKhongDuThua(phuThuocHam);
        for (int i = 0; i < phuKhongDuThua.size() - 1; i++) {
            khoa += phuKhongDuThua.get(i) + ", ";
        }
        khoa += phuKhongDuThua.get(phuKhongDuThua.size() - 1);

        output.setText(khoa);
    }

    public void timPhuToiThieu() {
        String text = input.getText().toUpperCase();
        String[] temp = text.split(", ");
        List<String> phuThuocHam = new ArrayList<>();
        List<String> phuKhongDuThua;
        String khoa = "";

        for (int i = 0; i < temp.length; i++) {
            phuThuocHam.add(temp[i]);
        }
        phuKhongDuThua = databaseAlgorithm.timPhuToiThieu(phuThuocHam);
        for (int i = 0; i < phuKhongDuThua.size() - 1; i++) {
            khoa += phuKhongDuThua.get(i) + ", ";
        }
        khoa += phuKhongDuThua.get(phuKhongDuThua.size() - 1);

        output.setText(khoa);
    }

    public void kiemTraMatMatThongTin() {
        String text = input.getText().toUpperCase();
        String[] temp = text.split(", ");
        String tapThuocTinh = temp[0];
        List<String> phepTach = new ArrayList<>();
        List<String> phuThuocHam = new ArrayList<>();
        String khoa = "";

        for (int i = 1; i < temp.length; i++) {
            if (temp[i].matches("(.*)->(.*)")) {
                phuThuocHam.add(temp[i]);
            } else {
                phepTach.add(temp[i]);
            }
        }
        String[][] bangTest = databaseAlgorithm.kiemTraMatThongTin(tapThuocTinh, phepTach, phuThuocHam);
        String[] col = new String[tapThuocTinh.length()+1];
        col[0] = "";
        String[][] data = new String[tapThuocTinh.length()][phepTach.size()+1];
        for(int i = 0; i < tapThuocTinh.length(); i++){
            for(int j = 1; j < phepTach.size()+1; j++){
                data[i][j] = bangTest[i][j - 1];
            }
        }
        for(int i = 1; i < tapThuocTinh.length() + 1; i++){
            col[i] = tapThuocTinh.split("")[i - 1];
        }
        for(int i = 0; i < phepTach.size(); i++){
            data[i][0] = phepTach.get(i);
        }
        if (databaseAlgorithm.test(bangTest, tapThuocTinh.length(), phepTach.size())) {
            output.setText("Không mất mát thông tin");
        }else{
            output.setText("Mất mát thông tin");
        }
        
        DefaultTableModel model = new DefaultTableModel(data,col);
        this.bangTest.setModel(model);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangTest;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton find;
    private javax.swing.JTextField input;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton kiemTraMatMatThongTin;
    private javax.swing.JTextField output;
    private javax.swing.JRadioButton timBaoDong;
    private javax.swing.JRadioButton timKhoaToiThieu;
    private javax.swing.JRadioButton timPhuKhongDuThua;
    private javax.swing.JRadioButton timPhuToiThieu;
    // End of variables declaration//GEN-END:variables
}
