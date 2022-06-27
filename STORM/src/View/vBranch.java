/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sonys
 */
public class vBranch extends vBackground{
    JTextField jtNama;
    JButton btnSearch,btnAdd,btnSimpan;
    JTable tblBranch;
    DefaultTableModel dtmBranch;
    public vBranch(){
        JLabel lTitle=new JLabel("Data Cabang");
        lTitle.setBounds(786, 50, 600, 100);
        lTitle.setFont(new java.awt.Font("Calibri", 1, 36));
        lTitle.setForeground(Color.white);
        
        JLabel lnama=new JLabel("Nama Cabang");
        lnama.setBounds(636, 160, 180, 30);
        lnama.setFont(new java.awt.Font("Calibri", 1, 22));
        lnama.setForeground(Color.white);
        
        jtNama=new JTextField();
        jtNama.setBounds(780, 160, 200, 30);
        jtNama.setFont(new java.awt.Font("Calibri", 0, 21));
        jtNama.setForeground(Color.black);
        
        btnSearch=new JButton("Cari");
        btnSearch.setBounds(1001, 160, 120, 30);
        btnSearch.setFont(new java.awt.Font("Calibri", 0, 21));
        btnSearch.setForeground(Color.black);
        btnSearch.setBackground(Color.WHITE);
        
        tblBranch=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblBranch.setBounds(431, 201, 909, 431);
        tblBranch.setFont(new java.awt.Font("Calibri", 0, 18));
        tblBranch.setForeground(Color.black);
        tblBranch.setBackground(Color.WHITE);
        tblBranch.setRowHeight(30);
        tblBranch.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        tblBranch.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
//        tblBarang.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent me) {
//               showFormBarang();
//               tfkbarang.setText(tblBarang.getValueAt(tblBarang.getSelectedRow(), 0).toString()); 
//               tfkbarang.setEditable(false);
//               jtnamabarang.setText( tblBarang.getValueAt(tblBarang.getSelectedRow(), 1).toString()); 
//               jtstok.setText(tblBarang.getValueAt(tblBarang.getSelectedRow(), 2).toString());
//               jthbeli.setText( tblBarang.getValueAt(tblBarang.getSelectedRow(), 3).toString()); 
//               jthjual.setText( tblBarang.getValueAt(tblBarang.getSelectedRow(), 4).toString()); 
//               jtkdSuplier.setText( tblBarang.getValueAt(tblBarang.getSelectedRow(), 5).toString()); 
//               
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent me) {
//                
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent me) {
//                
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent me) {
//                
//            }
//
//            @Override
//            public void mouseExited(MouseEvent me) {
//                
//            }
//        });
       
        JScrollPane scrollPane = new JScrollPane(tblBranch);
        scrollPane.setBounds(431, 201, 909, 431);
        
        
        btnAdd=new JButton("Tambah");
        btnAdd.setForeground(Color.black);
        btnAdd.setFont(new java.awt.Font("Calibri", 1, 22));
        btnAdd.setBounds(1189,674, 150, 60);
        btnAdd.setBackground(Color.WHITE);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
       
            }
        });
        
        btnSimpan=new JButton("SIMPAN");
        btnSimpan.setBounds(30, 400, 160, 30);
        btnSimpan.setFont(new java.awt.Font("Calibri", 0, 21));
        btnSimpan.setForeground(Color.black);
        btnSimpan.setBackground(Color.white);
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        
        add(lTitle);
        add(lnama);
        add(jtNama);
        add(btnSearch);
        add(scrollPane);
        add(btnAdd);
        add(bg);
        
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1366,768);
        setVisible(true);
    }
    public void setTableCabang(ArrayList rows){
        dtmBranch=new DefaultTableModel();
        dtmBranch.setColumnCount(0);
        dtmBranch.addColumn("ID");
        dtmBranch.addColumn("Nama");
        dtmBranch.addColumn("Almat");
        dtmBranch.addColumn("Telepon");
        dtmBranch.addColumn("Penangung Jawab");
        dtmBranch.addColumn("User Update");
        dtmBranch.addColumn("Update At");

        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);

            dtmBranch.addRow(new Object[]{row.get("id"),row.get("nama_cabang"),row.get("alamat"),row.get("telepon"),row.get("branch_head"),row.get("user_update"),row.get("update_at")});
        }
        tblBranch.setModel(dtmBranch);
        
    }
    JFrame fdisplay;
    JTextField idCabang,namaCabang,alamat, telepon,idPenanggungJawab;
    JComboBox jcbUsers;
    public void showFormCabang(){
        
        fdisplay=new JFrame("DISPLAY PENGGUNA");
        
        JLabel lidPengguna=new JLabel("ID Cabang");
        lidPengguna.setBounds(30, 20, 180, 30);
        lidPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        lidPengguna.setForeground(Color.white);
        
        idCabang=new JTextField("0");
        idCabang.setBounds(200, 20, 180, 30);
        idCabang.setFont(new java.awt.Font("Calibri", 0, 21));
        idCabang.setForeground(Color.white);
        idCabang.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        idCabang.setEnabled(false);
        
        JLabel lnamaPengguna=new JLabel("Nama Cabang");
        lnamaPengguna.setBounds(30, 80, 180, 30);
        lnamaPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        lnamaPengguna.setForeground(Color.white);
        
        namaCabang=new JTextField();
        namaCabang.setBounds(200, 80, 180, 30);
        namaCabang.setFont(new java.awt.Font("Calibri", 0, 21));
        namaCabang.setForeground(Color.black);
        namaCabang.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lAlamatPengguna=new JLabel("Alamat");
        lAlamatPengguna.setBounds(30, 140, 180, 30);
        lAlamatPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        lAlamatPengguna.setForeground(Color.white);
        
        alamat=new JTextField();
        alamat.setBounds(200, 140, 180, 30);
        alamat.setFont(new java.awt.Font("Calibri", 0, 21));
        alamat.setForeground(Color.black);
        alamat.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JLabel lNoHP=new JLabel("Telepon");
        lNoHP.setBounds(30, 200, 180, 30);
        lNoHP.setFont(new java.awt.Font("Calibri", 0, 21));
        lNoHP.setForeground(Color.white);
        
        telepon=new JTextField();
        telepon.setBounds(200, 200, 180, 30);
        telepon.setFont(new java.awt.Font("Calibri", 0, 21));
        telepon.setForeground(Color.black);
        telepon.setBorder(BorderFactory.createLineBorder(Color.black, 1));   
        telepon.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
            String value = telepon.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
               telepon.setEditable(true);
               
            } else {
               telepon.setEditable(false);
          
            }
         }
      });
        
        JLabel lidBranch=new JLabel("ID HS");
        lidBranch.setBounds(30, 260, 130, 30);
        lidBranch.setFont(new java.awt.Font("Calibri", 1, 22));
        lidBranch.setForeground(Color.white);
        
        idPenanggungJawab=new JTextField("");
        idPenanggungJawab.setEnabled(false);
        idPenanggungJawab.setBounds(200, 260, 180, 30);
        idPenanggungJawab.setFont(new java.awt.Font("Calibri", 1, 22));
        idPenanggungJawab.setForeground(Color.black);
        idPenanggungJawab.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        idPenanggungJawab.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lnamaBranch=new JLabel("Nama HS");
        lnamaBranch.setBounds(30, 320, 130, 30);
        lnamaBranch.setFont(new java.awt.Font("Calibri", 1, 22));
        lnamaBranch.setForeground(Color.white);
        
        jcbUsers=new JComboBox();
        jcbUsers.setBounds(200, 320, 180, 30);
        jcbUsers.setFont(new java.awt.Font("Calibri", 1, 22));
        jcbUsers.setForeground(Color.black);
        jcbUsers.setBackground(Color.white);
        jcbUsers.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        
        fdisplay.add(lidPengguna);
        fdisplay.add(idCabang);
        fdisplay.add(lnamaPengguna);
        fdisplay.add(namaCabang);
        fdisplay.add(lAlamatPengguna);
        fdisplay.add(alamat);
        fdisplay.add(lNoHP);
        fdisplay.add(telepon);
        fdisplay.add(lidBranch);
        fdisplay.add(idPenanggungJawab);
        fdisplay.add(lnamaBranch);
        fdisplay.add(jcbUsers);
        
        fdisplay.add(btnSimpan);
        
        fdisplay.add(bg);
        fdisplay.setSize(450,500);
        fdisplay.setLayout(null);
        fdisplay.setVisible(true);
        fdisplay.setDefaultCloseOperation(1);
    }
    public HashMap getForm(){
        HashMap hashMap=new HashMap();
        hashMap.put("id", idCabang.getText().toString());
        hashMap.put("nama_cabang", namaCabang.getText().toString());
        hashMap.put("alamat", alamat.getText().toString());
        hashMap.put("telepon", telepon.getText().toString());
        hashMap.put("branch_head", idPenanggungJawab.getText().toString());
        hashMap.put("user_update", getUserID());
        return hashMap;
    }
    public JTextField getJtNama() {
        return jtNama;
    }

    public JTable getTblBranch() {
        return tblBranch;
    }

    public JTextField getIdCabang() {
        return idCabang;
    }

    public JTextField getNamaCabang() {
        return namaCabang;
    }

    public JTextField getAlamat() {
        return alamat;
    }

    public JTextField getTelepon() {
        return telepon;
    }

    public JTextField getIdPenanggungJawab() {
        return idPenanggungJawab;
    }

    public JComboBox getJcbUsers() {
        return jcbUsers;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnSimpan() {
        return btnSimpan;
    }
    
}
