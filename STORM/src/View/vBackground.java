/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import admin.gudang.Ltime;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author sonys
 */
public class vBackground extends JFrame{
    JButton btnBarang, btnPesanan,btnSuplier, btnPengguna, btnLaporan,btnCabang,btnSimpan;
    JPanel bg;
    JLabel lNama;
    JLabel lUserid;
    JTextField tfJudul;
    JTextArea tfDeskripsi;
    JFrame fdisplay;
    public vBackground(){
         bg=new JPanel();
        bg.setBackground(Color.black);
            bg.setSize(1366,768);
        
        JLabel pHeader=new JLabel();
        pHeader.setIcon(new ImageIcon(getClass().getResource("/Picture/header_desktop1.jpg")));
        pHeader.setBounds(0, 0, 1366, 60);
        
        JLabel pMenu=new JLabel();
        pMenu.setIcon(new ImageIcon(getClass().getResource("/Picture/mainmenu1.jpg")));
        pMenu.setBounds(-5, 60, 400, 750);
        
        JLabel lTitle=new JLabel("STORM(Stock and Order Monitoring)");
        lTitle.setFont(new java.awt.Font("Tahoma", 1, 26));
        lTitle.setForeground(Color.white);
        lTitle.setBounds(10, 7,700, 40);

        JLabel waktu=new JLabel("Waktu   ");
        waktu.setFont(new java.awt.Font("Tahoma", 0, 18));
        waktu.setForeground(new java.awt.Color(255, 255, 255));
        waktu.setBounds(750, 7,100, 40);
        
        Ltime vWaktu= new Ltime();
        vWaktu.setFont(new java.awt.Font("Tahoma", 0, 18));
        vWaktu.setForeground(new java.awt.Color(255, 255, 255));
        vWaktu.setBounds(850, 7,300, 40);        
        
        JLabel iUser=new JLabel();
        iUser.setIcon(new ImageIcon(getClass().getResource("/Picture/user.png")));
        iUser.setBounds(20 , 80, 100,100);
        
        lNama=new JLabel();
        lNama.setForeground(Color.white);
        lNama.setFont(new java.awt.Font("Tahoma", 1, 26));
        lNama.setBounds(130 , 65, 300,100);
        
        lUserid=new JLabel();
        lUserid.setForeground(Color.white);
        lUserid.setFont(new java.awt.Font("Tahoma", 1, 22));
        lUserid.setBounds(130 , 95, 100,100);
      
        JLabel btnClose=new JLabel();
        btnClose.setIcon(new ImageIcon(getClass().getResource("/Picture/cancel.png")));
        btnClose.setBounds(1315, 5,50, 50);
        btnClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int opsi = JOptionPane.showConfirmDialog(null, "Yakin Ingin Keluar ?", "Keluar", JOptionPane.YES_NO_OPTION);
                if (opsi == JOptionPane.YES_OPTION)
                    System.exit(0);
                else
                    JOptionPane.showMessageDialog(null, "Dibatalkan");
            }
            

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        });
        
        JLabel btnNotif=new JLabel();
        btnNotif.setIcon(new ImageIcon(getClass().getResource("/Picture/notif.png")));
        btnNotif.setBounds(1247, 5,50, 50);
        btnNotif.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showFormNotifikasi();
            }
            

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        });
        
        btnBarang=new JButton("BARANG");
        btnBarang.setForeground(Color.black);
        btnBarang.setFont(new java.awt.Font("Tahoma", 0, 30));
        btnBarang.setBounds(28, 230, 335, 60);
        btnBarang.setBackground(Color.WHITE);
        
        btnPesanan=new JButton("PESANAN");
        btnPesanan.setForeground(Color.black);
        btnPesanan.setFont(new java.awt.Font("Tahoma", 0, 30));
        btnPesanan.setBounds(28, 310, 335, 60);
        btnPesanan.setBackground(Color.WHITE);
        
        btnSuplier=new JButton("SUPLIER");
        btnSuplier.setForeground(Color.black);
        btnSuplier.setFont(new java.awt.Font("Tahoma", 0, 30));
        btnSuplier.setBounds(28, 390, 335, 60);
        btnSuplier.setBackground(Color.WHITE);
        
        btnCabang=new JButton("CABANG");
        btnCabang.setForeground(Color.black);
        btnCabang.setFont(new java.awt.Font("Tahoma", 0, 30));
        btnCabang.setBounds(28, 470, 335, 60);
        btnCabang.setBackground(Color.WHITE);
        
        
        btnPengguna=new JButton("PENGGUNA");
        btnPengguna.setForeground(Color.black);
        btnPengguna.setFont(new java.awt.Font("Tahoma", 0, 30));
        btnPengguna.setBounds(28, 550, 335, 60);
        btnPengguna.setBackground(Color.WHITE);
        
        btnLaporan=new JButton("LAPORAN");
        btnLaporan.setForeground(Color.black);
        btnLaporan.setFont(new java.awt.Font("Tahoma", 0, 30));
        btnLaporan.setBounds(28, 630, 335, 60);
        btnLaporan.setBackground(Color.WHITE);
        
        btnSimpan=new JButton("SIMPAN");
        btnSimpan.setBounds(127, 300, 160, 30);
        btnSimpan.setFont(new java.awt.Font("Calibri", 0, 21));
        btnSimpan.setForeground(Color.black);
        btnSimpan.setBackground(Color.white);
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fdisplay.setVisible(false);
            }
        });
//        add(logo);
//        add(icon);
//        add(tanggal);
//        add(waktu);
//        add(close);
        add(lTitle);
        add(waktu);
        add(vWaktu);
        add(iUser);
        add(lNama);
        add(lUserid);
        add(btnBarang);
        add(btnPesanan);
        add(btnSuplier);
        add(btnCabang);
        add(btnPengguna);
        add(btnLaporan);
        add(btnClose);
        add(btnNotif);
        add(pHeader);
        add(pMenu);
//        add(bg);
        
//        setUndecorated(true);
//        setLayout(null);
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        setSize(1366,768);
//        setVisible(true);

}
    public void btnBarangAddActionListener(ActionListener a){
        btnBarang.addActionListener(a);
    }
    public void btnPesananAddActionListener(ActionListener a){
        btnPesanan.addActionListener(a);
    }
    public void btnPenggunaAddActionListener(ActionListener a){
        btnPengguna.addActionListener(a);
    }
    public void btnCabangAddActionListener(ActionListener a){
        btnCabang.addActionListener(a);
    }
    public void btnSuplierAddActionListener(ActionListener a){
        btnSuplier.addActionListener(a);
    }
    public void btnLaporanAddActionListener(ActionListener a){
        btnLaporan.addActionListener(a);
    }
    public void setUserData(String name, String userID){
        lNama.setText(name);
        lUserid.setText(userID);
    }
    public String getUserID(){
        return lUserid.getText();
    }
    
    public void showFormNotifikasi(){
        
        fdisplay=new JFrame("BAGIKAN NOTIFIKASI");
        
        JLabel ljudul=new JLabel("Judul");
        ljudul.setBounds(30, 20, 180, 30);
        ljudul.setFont(new java.awt.Font("Calibri", 0, 21));
        ljudul.setForeground(new java.awt.Color(170, 173, 234));
        
        tfJudul=new JTextField();
        tfJudul.setBounds(200, 20, 180, 30);
        tfJudul.setFont(new java.awt.Font("Calibri", 0, 21));
        tfJudul.setForeground(new java.awt.Color(170, 173, 234));
        tfJudul.setBorder(BorderFactory.createLineBorder(new java.awt.Color(170, 173, 234), 1));
//        tfJudul.setEnabled(false);
        
        JLabel ldeskripsi=new JLabel("Pesan");
        ldeskripsi.setBounds(30, 80, 180, 30);
        ldeskripsi.setFont(new java.awt.Font("Calibri", 0, 21));
        ldeskripsi.setForeground(new java.awt.Color(170, 173, 234));
        
        tfDeskripsi=new JTextArea();
        tfDeskripsi.setBounds(200, 80, 180, 150);
        tfDeskripsi.setFont(new java.awt.Font("Calibri", 0, 21));
        tfDeskripsi.setForeground(new java.awt.Color(170, 173, 234));
        tfDeskripsi.setBorder(BorderFactory.createLineBorder(new java.awt.Color(170, 173, 234), 1));
        
        
        fdisplay.add(ljudul);
        fdisplay.add(tfJudul);
        fdisplay.add(ldeskripsi);
        fdisplay.add(tfDeskripsi);
        
        fdisplay.add(btnSimpan);
        
        fdisplay.add(bg);
        fdisplay.setSize(450,380);
        fdisplay.setLayout(null);
        fdisplay.setVisible(true);
        fdisplay.setDefaultCloseOperation(1);
    }
    public void postNotification(ActionListener a){
        btnSimpan.addActionListener(a);
    }
    public HashMap getFormNotif(){
        HashMap hashMap=new HashMap();
        hashMap.put("judul", tfJudul.getText().toString());
        hashMap.put("deskripsi", tfDeskripsi.getText().toString());
        return hashMap;
    }
}
