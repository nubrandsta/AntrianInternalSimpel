/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;
import Config.Koneksi;
import Form.Laporan;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author ussr
 */
public class Antrian extends javax.swing.JFrame {

    /**
     * Creates new form Antrian
     */
    public Antrian() {
        initComponents();
    }
    
    private DefaultTableModel modelTabel;
    public String selectedIdMenu;

    
    public void showData(){
        modelTabel = new DefaultTableModel();
        
        modelTabel.addColumn("ID Pesanan");
        modelTabel.addColumn("ID Menu");
        modelTabel.addColumn("No Urut");
        modelTabel.addColumn("Jumlah");
        modelTabel.addColumn("Harga");
        modelTabel.addColumn("Nama Pesanan");
        modelTabel.addColumn("Kategori");
        
        tbl_antrian.setModel(modelTabel);
        tbl_antrian.setAutoCreateRowSorter(true);
        
        java.sql.Connection conn1 = new Koneksi().connect();
        try{
            String query1="SELECT * FROM tb_antrian";
            String currentIdMenu;
            java.sql.Statement state1 = conn1.createStatement();
            java.sql.ResultSet result1 = state1.executeQuery(query1);
            int rowid = 0;
            while(result1.next()){
                
                modelTabel.addRow(new Object[] {
                result1.getString("id_pesanan"),
                result1.getString("id_menu"),
                result1.getString("no_urut"),
                result1.getString("jumlah")
            } );
                currentIdMenu = result1.getString("id_menu");
                
                java.sql.Connection conn2 = new Koneksi().connect();
                try{
                    String query2= "SELECT nama_menu,kategori_menu,harga_menu FROM tb_menu WHERE id_menu='"+currentIdMenu+"'";
                    java.sql.Statement state2 = conn2.createStatement();
                    java.sql.ResultSet result2 = state2.executeQuery(query2);
                    while(result2.next()){
                        modelTabel.setValueAt(result2.getString("harga_menu"),rowid,4);
                        modelTabel.setValueAt(result2.getString("nama_menu"),rowid,5);
                        modelTabel.setValueAt(result2.getString("kategori_menu"),rowid,6);
                    }
                }
                catch(Exception e){
                    
                }
                rowid++;
            }
        }
        catch(Exception e){
            
        }
        
        java.sql.Connection conn3 = new Koneksi().connect();
        try{
            String query3 = "SELECT id_menu FROM tb_menu";
            java.sql.Statement state3 = conn3.createStatement();
            java.sql.ResultSet result3 = state3.executeQuery(query3);
            while(result3.next()){
                cmb_idmenu.addItem(result3.getString("id_menu"));
            }
        }
        catch(Exception e){
            
        }
        
        String selectedMenu = cmb_idmenu.getSelectedItem().toString();
        java.sql.Connection conn4 = new Koneksi().connect();
        String query4 = "SELECT nama_menu FROM tb_menu WHERE id_menu ='"+selectedMenu+"'";
        try{
            java.sql.Statement state4 = conn4.createStatement();
            java.sql.ResultSet result4 = state4.executeQuery(query4);
            while(result4.next()){
                String nama = result4.getString("nama_menu");
                txt_namaMenu.setText(nama);
            }
            
           
        }
        catch(Exception e){
             e.printStackTrace();
        }
         
    }
    
    public int getLast(){
        int antrian = 0;
        
        String selectedMenu = cmb_idmenu.getSelectedItem().toString();
        java.sql.Connection conn = new Koneksi().connect();
        String query = "SELECT MAX(no_urut) FROM tb_antrian";
        try{
            java.sql.Statement state = conn.createStatement();
            java.sql.ResultSet result = state.executeQuery(query);
            while(result.next()){
                antrian = Integer.parseInt(result.getString("MAX(no_urut)"));
            }
            
           
        }
        catch(Exception e){
             e.printStackTrace();
        }
        
        return antrian;
    }
    
    public void clearInput(){
        txt_idPesanan.setText("");
        txt_namaMenu.setText("");
        txt_jumlah.setText("");
        
    }
    
    public void popAntrian(String currentAntrian){
        java.sql.Connection conn = new Koneksi().connect();
        
        try{
            PreparedStatement state = conn.prepareStatement("UPDATE tb_antrian SET no_urut = no_urut - 1 WHERE no_urut > ? AND no_urut > 0;");
                    try{
                            state.setString(1,currentAntrian);
                            state.executeUpdate();
                            showData();
                            
                    }
                    catch(Exception e){
                        
                        e.printStackTrace();
                        System.out.println(currentAntrian);
                    }
            
        }
        catch(Exception e){
            
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_antrian = new javax.swing.JTable();
        btn_selesai = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        txt_idPesanan = new javax.swing.JTextField();
        txt_namaMenu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_baru = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        cmb_idmenu = new javax.swing.JComboBox<>();
        txt_urut = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_display = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KASIR");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KASIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        tbl_antrian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Pesanan", "ID_Menu", "No_urut", "Jumlah", "Harga", "Nama_pesanan", "Kategori"
            }
        ));
        tbl_antrian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_antrianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_antrian);

        btn_selesai.setText("SELESAIKAN PESANAN");
        btn_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiActionPerformed(evt);
            }
        });

        btn_batal.setText("BATALKAN PESANAN");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        txt_idPesanan.setEditable(false);
        txt_idPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idPesananActionPerformed(evt);
            }
        });

        txt_namaMenu.setEditable(false);
        txt_namaMenu.setText(" ");

        jLabel2.setText("ID Pesanan");

        jLabel3.setText("ID Menu");

        jLabel4.setText("Menu");

        txt_jumlah.setText("1");
        txt_jumlah.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_jumlahInputMethodTextChanged(evt);
            }
        });
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });

        jLabel5.setText("Jumlah");

        btn_baru.setText("PESANAN BARU");
        btn_baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baruActionPerformed(evt);
            }
        });

        btn_edit.setText("EDIT");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_laporan.setText("Laporan");
        btn_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporanActionPerformed(evt);
            }
        });

        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        cmb_idmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_idmenuActionPerformed(evt);
            }
        });

        txt_urut.setEditable(false);
        txt_urut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_urutActionPerformed(evt);
            }
        });

        jLabel6.setText("Urutan");

        btn_display.setText("DISPLAY");
        btn_display.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_displayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_selesai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_batal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_idPesanan)
                                            .addComponent(txt_namaMenu)
                                            .addComponent(txt_jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(cmb_idmenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btn_baru)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txt_urut, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                            .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_selesai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_batal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_idPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmb_idmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_namaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambah)
                            .addComponent(txt_urut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_edit)
                            .addComponent(btn_baru))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_laporan)
                            .addComponent(btn_display)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        String idPesanan = txt_idPesanan.getText();
        String urutan = txt_urut.getText();
        java.sql.Connection conn = new Koneksi().connect();
        try{
                    PreparedStatement state = conn.prepareStatement("DELETE FROM tb_antrian WHERE id_pesanan = ?");
                    try{
                            state.setString(1,idPesanan);
                            int ya = JOptionPane.showConfirmDialog(null, "Apakah akan membatalkan pesanan ini?","Pesan",JOptionPane.YES_NO_OPTION);
                            if(ya == JOptionPane.YES_OPTION){
                                state.executeUpdate();}
                                showData();
                                JOptionPane.showMessageDialog(null, "Pesanan dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    }
                            
                            
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Gagal Dibatalkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                        e.printStackTrace();
                        System.out.println(idPesanan);
                    }
        
        }
        catch(Exception e){
            
        }
        popAntrian(urutan);
        
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baruActionPerformed
        // TODO add your handling code here:
        txt_idPesanan.setText("");
        String jumlah = txt_jumlah.getText();
        txt_urut.setText(String.valueOf(getLast()+1));
        cmb_idmenu.setEnabled(true);
        
        String selectedItemId = cmb_idmenu.getSelectedItem().toString();
        String itemGroup = selectedItemId.substring(0,2);
        String itemId = selectedItemId.substring(2,4);
        String antrianTerakhir = String.valueOf(getLast()+1);
        String date = java.time.LocalDate.now().toString();
        String day = date.substring(8,10);
        String mon = date.substring(5,7);
        String yer = date.substring(2,4);
        
        txt_idPesanan.setText(itemGroup+day+mon+yer+itemId+jumlah+antrianTerakhir);
        showData();
    }//GEN-LAST:event_btn_baruActionPerformed

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        // TODO add your handling code here:
        new Laporan().setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_laporanActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        showData();
    }//GEN-LAST:event_formWindowActivated

    private void tbl_antrianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_antrianMouseClicked
        // TODO add your handling code here:
        
        int dataPesanan = tbl_antrian.getSelectedRow();
        cmb_idmenu.setSelectedItem(modelTabel.getValueAt(dataPesanan,1).toString());
        txt_idPesanan.setText(modelTabel.getValueAt(dataPesanan,0).toString());
        txt_jumlah.setText(modelTabel.getValueAt(dataPesanan,3).toString());
        txt_urut.setText(modelTabel.getValueAt(dataPesanan,2).toString());
        cmb_idmenu.setEnabled(false);
        
        
        
        
        
    }//GEN-LAST:event_tbl_antrianMouseClicked

    private void txt_idPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idPesananActionPerformed

    private void cmb_idmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_idmenuActionPerformed
        // TODO add your handling code here:
        String selectedItemId = cmb_idmenu.getSelectedItem().toString();
        String itemGroup = selectedItemId.substring(0,2);
        String itemId = selectedItemId.substring(2,4);
        String antrianTerakhir = String.valueOf(getLast()+1);
        String date = java.time.LocalDate.now().toString();
        String day = date.substring(8,10);
        String mon = date.substring(5,7);
        String yer = date.substring(2,4);
        
        txt_idPesanan.setText(itemGroup+day+mon+yer+itemId+antrianTerakhir);
        showData();
    }//GEN-LAST:event_cmb_idmenuActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        String selectedMenu = cmb_idmenu.getSelectedItem().toString();
        java.sql.Connection conn = new Koneksi().connect();
        
        try{
            java.sql.PreparedStatement state = conn.prepareStatement("UPDATE tb_antrian SET jumlah = ? WHERE id_pesanan=?");
            try{
                state.setString(1, txt_jumlah.getText());
                state.setString(2, txt_idPesanan.getText());
                state.executeUpdate();
                showData();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception e){
                System.out.println("Error updating");
                JOptionPane.showMessageDialog(null, "Data Gagal Diedit", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
            
           
        }
        catch(Exception e){
             e.printStackTrace();
        }
        
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        java.sql.Connection conn = new Koneksi().connect();
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement("INSERT INTO tb_antrian (id_pesanan, id_menu, jumlah, no_urut) VALUES(?,?,?,?)");
            try {
                stmt.setString(1, txt_idPesanan.getText());
                stmt.setString(2, cmb_idmenu.getSelectedItem().toString());
                stmt.setString(3, txt_jumlah.getText());
                stmt.setString(4, String.valueOf(getLast()+1));
                
                stmt.executeUpdate();
                showData();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                
                clearInput();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan", "Pesan", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } catch (Exception e){
           
            }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void txt_urutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_urutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_urutActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
        if(txt_idPesanan.isEditable()){
            txt_idPesanan.setText("");
            String jumlah = txt_jumlah.getText();
            txt_urut.setText(String.valueOf(getLast()+1));
            cmb_idmenu.setEnabled(true);

            String selectedItemId = cmb_idmenu.getSelectedItem().toString();
            String itemGroup = selectedItemId.substring(0,2);
            String itemId = selectedItemId.substring(2,4);
            String antrianTerakhir = String.valueOf(getLast()+1);
            String date = java.time.LocalDate.now().toString();
            String day = date.substring(8,10);
            String mon = date.substring(5,7);
            String yer = date.substring(2,4);

            txt_idPesanan.setText(itemGroup+day+mon+yer+itemId+jumlah+antrianTerakhir);
            showData();
            }
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_jumlahInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_jumlahInputMethodTextChanged
        // TODO add your handling code here:
        if(txt_idPesanan.isEditable()){
            txt_idPesanan.setText("");
            String jumlah = txt_jumlah.getText();
            txt_urut.setText(String.valueOf(getLast()+1));
            cmb_idmenu.setEnabled(true);

            String selectedItemId = cmb_idmenu.getSelectedItem().toString();
            String itemGroup = selectedItemId.substring(0,2);
            String itemId = selectedItemId.substring(2,4);
            String antrianTerakhir = String.valueOf(getLast()+1);
            String date = java.time.LocalDate.now().toString();
            String day = date.substring(8,10);
            String mon = date.substring(5,7);
            String yer = date.substring(2,4);

            txt_idPesanan.setText(itemGroup+day+mon+yer+itemId+jumlah+antrianTerakhir);
            showData();
            }
        
    }//GEN-LAST:event_txt_jumlahInputMethodTextChanged

    private void btn_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiActionPerformed
        // TODO add your handling code here:
        String idPesanan = txt_idPesanan.getText();
        String idMenu = cmb_idmenu.getSelectedItem().toString();
        String urutan = txt_urut.getText();
        String jumlah = "";
        String nama_menu = "";
        String kategori_menu = "";
        String harga = "";
        
        java.sql.Connection conn = new Koneksi().connect();
        String query = "SELECT jumlah FROM tb_antrian WHERE id_pesanan='"+idPesanan+"'";
        try{
            java.sql.Statement state = conn.createStatement();
            java.sql.ResultSet result = state.executeQuery(query);
            while(result.next()){
                jumlah = result.getString("jumlah");
                
                
            }
        }
        catch(Exception e){
            
        }
        query = "SELECT nama_menu,kategori_menu,harga_menu FROM tb_menu WHERE id_menu='"+idMenu+"'";
        try{
            java.sql.Statement state = conn.createStatement();
            java.sql.ResultSet result = state.executeQuery(query);
            while(result.next()){
                nama_menu = result.getString("nama_menu");
                kategori_menu = result.getString("kategori_menu");
                harga = result.getString("harga_menu");
                
            }
        }
        catch(Exception e){

        }
        int total = Integer.parseInt(jumlah) * Integer.parseInt(harga);
        
        try{
            java.sql.PreparedStatement state = conn.prepareStatement("INSERT INTO tb_transaksi (id_transaksi,kategori_menu,id_menu,nama_menu,harga_menu,jumlah,total) VALUES(?,?,?,?,?,?,?)");
            try{
                state.setString(1,idPesanan);
                state.setString(2,kategori_menu);
                state.setString(3,idMenu);
                state.setString(4,nama_menu);
                state.setString(5, harga);
                state.setString(6, jumlah);
                state.setString(7, String.valueOf(total));
                state.executeUpdate();
                
                java.sql.Connection conn2 = new Koneksi().connect();
                try{
                    PreparedStatement delet = conn2.prepareStatement("DELETE FROM tb_antrian WHERE id_pesanan = ?");
                    try{
                            delet.setString(1,idPesanan);
                            delet.executeUpdate();
                            showData();
                            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                            popAntrian(urutan);
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                        e.printStackTrace();
                        System.out.println(idPesanan);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
                
            }
            catch(Exception e){
                
            }
        }
        catch(Exception e){
            
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_btn_selesaiActionPerformed

    private void btn_displayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_displayActionPerformed
        // TODO add your handling code here:
        new Display().setVisible(true);
        
    }//GEN-LAST:event_btn_displayActionPerformed

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
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Antrian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_baru;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_display;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_selesai;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox<String> cmb_idmenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_antrian;
    private javax.swing.JTextField txt_idPesanan;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_namaMenu;
    private javax.swing.JTextField txt_urut;
    // End of variables declaration//GEN-END:variables
}
