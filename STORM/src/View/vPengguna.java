package View;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sonys
 */
public class vPengguna extends vBackground{
    JButton btnTambah,btnSearch,btnSimpan;
    JTextField jtNama;
    JTable tblPengguna;
    JTextField idPengguna,namaPengguna,alamat,noHpPengguna;
    JPasswordField password;
    DefaultTableModel dtmPengguna;
    JComboBox jabatan;
    JFrame fdisplay;
    JComboBox jcbBranch;
    JTextField idBranch;
    public vPengguna(){
        
        JLabel lTitle=new JLabel("Data Pengguna");
        lTitle.setBounds(750, 50, 600, 100);
        lTitle.setFont(new java.awt.Font("Calibri", 1, 36));
        lTitle.setForeground(Color.white);
        
        JLabel lNama=new JLabel("Nama Pengguna");
        lNama.setBounds(636, 160, 180, 30); 
        lNama.setFont(new java.awt.Font("Calibri", 1, 22));
        lNama.setForeground(Color.white);

        btnTambah=new JButton("Tambah");
        btnTambah.setBounds(1189,674, 150, 60);
        btnTambah.setFont(new java.awt.Font("Calibri", 1, 21));
        btnTambah.setForeground(Color.black);
        btnTambah.setBackground(Color.white);

        jtNama=new JTextField();
        jtNama.setBounds(800, 160, 180, 30);
        jtNama.setFont(new java.awt.Font("Tahoma", 0, 21));
        jtNama.setForeground(Color.black);
        
        btnSearch=new JButton("Cari");
        btnSearch.setBounds(1001, 160, 120, 30);
        btnSearch.setFont(new java.awt.Font("Calibri", 1, 21));
        btnSearch.setForeground(Color.black);
        btnSearch.setBackground(Color.white);
        
        btnSimpan=new JButton("SIMPAN");
        btnSimpan.setBounds(30, 500, 160, 30);
        btnSimpan.setFont(new java.awt.Font("Calibri", 0, 21));
        btnSimpan.setForeground(Color.black);
        btnSimpan.setBackground(Color.white);
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(getFormPengguna());
            }
        });
         
        tblPengguna=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPengguna.setBounds(431, 201, 909, 431);
        tblPengguna.setFont(new java.awt.Font("Calibri", 0, 18));
        tblPengguna.setForeground(Color.black);
        tblPengguna.setBackground(Color.WHITE);
        tblPengguna.setRowHeight(30);
        tblPengguna.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        tblPengguna.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        
        JScrollPane scrollPane = new JScrollPane(tblPengguna);
        scrollPane.setBounds(431, 201, 909, 431);
        
        add(lTitle);
        add(lNama);
        add(jtNama);
        add(btnSearch);
        add(btnTambah);
        add(scrollPane);
        add(bg);
        
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1366,768);
        setVisible(true);
    }
    public JComboBox getJcbBranch(){
        return jcbBranch;
    }
    public JButton getbtnTambah(){
        return btnTambah;
    }
    public JButton getbtnSearch(){
        return btnSearch;
    }
    public JTextField getjtNama(){
        return jtNama;
    }
    public JTable getTblPengguna(){
        return tblPengguna;
    }
    public JTextField getId(){
        return idPengguna;
    }
    public JTextField getNama(){
        return namaPengguna;
    }
    public JTextField getAlamat(){
        return alamat;
    }
    public JTextField getNohp(){
        return noHpPengguna;
    }
    public JTextField getidBranch(){
        return idBranch;
    }
    
    public void setFormPengguna(HashMap a){
        idPengguna.setText(a.get("id_user").toString()); 
        namaPengguna.setText(a.get("nama_user").toString()); 
        alamat.setText(a.get("alamat").toString());
        noHpPengguna.setText(a.get("ponsel").toString()); 
        idBranch.setText(a.get("branch").toString());
    }
            

    public void setTablePengguna(ArrayList rows){
        dtmPengguna=new DefaultTableModel();
        dtmPengguna.setColumnCount(0);
        dtmPengguna.addColumn("ID");
        dtmPengguna.addColumn("Nama");
        dtmPengguna.addColumn("Alamat");
        dtmPengguna.addColumn("Ponsel");
        dtmPengguna.addColumn("Penempatan");
        dtmPengguna.addColumn("Jabatan");
        dtmPengguna.addColumn("Password");
        dtmPengguna.addColumn("User Update");
        dtmPengguna.addColumn("Update At");
        

        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);

            dtmPengguna.addRow(new Object[]{row.get("id_user"),row.get("nama_user"),row.get("alamat"),row.get("ponsel"),row.get("branch"),row.get("jabatan"),"**************",row.get("user_update"),row.get("update_at")});
        }
        tblPengguna.setModel(dtmPengguna);
        
    }
    public HashMap getFormPengguna(){
        HashMap hashMap=new HashMap();
        if (idPengguna.getText().equals("")) {
            Random rand = new Random();
            int randomNum = rand.nextInt((10000 - 999) +1) + 999;
            System.out.println(randomNum);
            String kd="99"+randomNum;
            hashMap.put("id_user", Integer.valueOf(kd));
        }else{
            hashMap.put("id_user", Integer.valueOf(idPengguna.getText()));
        }
        hashMap.put("nama_user", namaPengguna.getText().toString());
        hashMap.put("alamat", alamat.getText().toString());
        hashMap.put("workplaces", idBranch.getText().toString());
        hashMap.put("ponsel", noHpPengguna.getText().toString());
        hashMap.put("jabatan", jabatan.getSelectedItem().toString());
        hashMap.put("password", password.getText().toString());
        hashMap.put("user_update", getUserID());;
        
        return hashMap;
    }
    public void showFormPengguna(){
        
        fdisplay=new JFrame("DISPLAY PENGGUNA");
        
        JLabel lidPengguna=new JLabel("ID Pengguna");
        lidPengguna.setBounds(30, 20, 180, 30);
        lidPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        lidPengguna.setForeground(Color.white);
        
        idPengguna=new JTextField();
        idPengguna.setBounds(200, 20, 180, 30);
        idPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        idPengguna.setForeground(Color.black);
        idPengguna.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        idPengguna.setEnabled(false);
        
        JLabel lnamaPengguna=new JLabel("Nama Pengguna");
        lnamaPengguna.setBounds(30, 80, 180, 30);
        lnamaPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        lnamaPengguna.setForeground(Color.white);
        
        namaPengguna=new JTextField();
        namaPengguna.setBounds(200, 80, 180, 30);
        namaPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        namaPengguna.setForeground(Color.black);
        namaPengguna.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lAlamatPengguna=new JLabel("Alamat");
        lAlamatPengguna.setBounds(30, 140, 180, 30);
        lAlamatPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        lAlamatPengguna.setForeground(Color.white);
        
        alamat=new JTextField();
        alamat.setBounds(200, 140, 180, 30);
        alamat.setFont(new java.awt.Font("Calibri", 0, 21));
        alamat.setForeground(Color.black);
        alamat.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JLabel lNoHP=new JLabel("No Hp");
        lNoHP.setBounds(30, 200, 180, 30);
        lNoHP.setFont(new java.awt.Font("Calibri", 0, 21));
        lNoHP.setForeground(Color.white);
        
        noHpPengguna=new JTextField();
        noHpPengguna.setBounds(200, 200, 180, 30);
        noHpPengguna.setFont(new java.awt.Font("Calibri", 0, 21));
        noHpPengguna.setForeground(Color.black);
        noHpPengguna.setBorder(BorderFactory.createLineBorder(Color.black, 1));   
        noHpPengguna.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
            String value = noHpPengguna.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
               noHpPengguna.setEditable(true);
               
            } else {
               noHpPengguna.setEditable(false);
          
            }
         }
      });
        
        JLabel lidBranch=new JLabel("ID Branch");
        lidBranch.setBounds(30, 260, 130, 30);
        lidBranch.setFont(new java.awt.Font("Calibri", 1, 22));
        lidBranch.setForeground(Color.white);
        
        idBranch=new JTextField("0");
        idBranch.setEnabled(false);
        idBranch.setBounds(200, 260, 180, 30);
        idBranch.setFont(new java.awt.Font("Calibri", 1, 22));
        idBranch.setForeground(Color.black);
        idBranch.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        idBranch.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lnamaBranch=new JLabel("Nama Branch");
        lnamaBranch.setBounds(30, 320, 130, 30);
        lnamaBranch.setFont(new java.awt.Font("Calibri", 1, 22));
        lnamaBranch.setForeground(Color.white);
        
        jcbBranch=new JComboBox();
        jcbBranch.setBounds(200, 320, 180, 30);
        jcbBranch.setFont(new java.awt.Font("Calibri", 1, 22));
        jcbBranch.setForeground(Color.black);
        jcbBranch.setBackground(Color.white);
        jcbBranch.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lJabatan=new JLabel("Jabatan");
        lJabatan.setBounds(30, 380, 180, 30);
        lJabatan.setFont(new java.awt.Font("Calibri", 0, 21));
        lJabatan.setForeground(Color.white);
        
        jabatan=new JComboBox();
        jabatan.addItem("Pemilik");
        jabatan.addItem("Gudang");
        jabatan.addItem("Penjualan");
        jabatan.addItem("Resign");
        jabatan.setBounds(200, 380, 180, 30);
        jabatan.setFont(new java.awt.Font("Calibri", 0, 21));
        jabatan.setForeground(Color.black);
        jabatan.setBackground(Color.white);
        jabatan.setBorder(BorderFactory.createLineBorder(Color.black, 1));  
        
        JLabel lPassword=new JLabel("Password");
        lPassword.setBounds(30, 440, 180, 30);
        lPassword.setFont(new java.awt.Font("Calibri", 0, 21));
        lPassword.setForeground(Color.white);
        
        password=new JPasswordField();
        password.setBounds(200, 440, 180, 30);
        password.setFont(new java.awt.Font("Calibri", 0, 21));
        password.setForeground(Color.black);
        password.setBorder(BorderFactory.createLineBorder(Color.black, 1));  

        fdisplay.add(lidPengguna);
        fdisplay.add(idPengguna);
        fdisplay.add(lnamaPengguna);
        fdisplay.add(namaPengguna);
        fdisplay.add(lAlamatPengguna);
        fdisplay.add(alamat);
        fdisplay.add(lNoHP);
        fdisplay.add(noHpPengguna);
        fdisplay.add(lidBranch);
        fdisplay.add(idBranch);
        fdisplay.add(lnamaBranch);
        fdisplay.add(jcbBranch);
        fdisplay.add(lJabatan);
        fdisplay.add(jabatan);
        fdisplay.add(lPassword);
        fdisplay.add(password);
        fdisplay.add(btnSimpan);
        
        fdisplay.add(bg);
        fdisplay.setSize(450,600);
        fdisplay.setLayout(null);
        fdisplay.setVisible(true);
        fdisplay.setDefaultCloseOperation(1);
    }
    public void btnSavePenggunaAddActionListener(ActionListener a){
        btnSimpan.addActionListener(a);
    }
}
