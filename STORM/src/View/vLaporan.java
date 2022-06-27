/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sonys
 */
public class vLaporan extends vBackground{
    JComboBox jcbKriteria;
    JLabel lParameter,lKeterangan;
    JDateChooser chooser;
    JTable tblLaporan;
    DefaultTableModel dtmLaporan;
    JTextField parameter;
    JButton btnPilih,btnSearch;
    public vLaporan(){
        JLabel lTitle=new JLabel("Laporan");
        lTitle.setBounds(824, 50, 600, 100);
        lTitle.setFont(new java.awt.Font("Calibri", 1, 36));
        lTitle.setForeground(Color.white);
        
        JLabel lkriteria=new JLabel("Cari Berdasarkan");
        lkriteria.setBounds(630, 135, 250, 30); 
        lkriteria.setFont(new java.awt.Font("Calibri", 1, 22));
        lkriteria.setForeground(Color.white);
        
        lParameter =new JLabel();
        lParameter.setBounds(630, 175, 250, 30); 
        lParameter.setFont(new java.awt.Font("Calibri", 1, 22));
        lParameter.setForeground(Color.white);
        
        chooser = new JDateChooser();
        chooser.setLocale(Locale.US);
        chooser.setPreferredSize(new Dimension(180, 30));
        chooser.setDateFormatString("dd-MM-yyyy");
        chooser.setEnabled(true);
        chooser.setFont(new java.awt.Font("Calibri", 1, 18));
        chooser.setForeground(Color.white);
        
        parameter=new JTextField();
        parameter.setBounds(820, 175, 180, 30);
        parameter.setFont(new java.awt.Font("Calibri", 1, 22));
        parameter.setForeground(Color.black);
        parameter.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = parameter.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar()=='-'|| ke.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
               parameter.setEditable(true);
               
            } else {
               parameter.setEditable(false);
               
            }
         }
      });
        parameter.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        jcbKriteria=new JComboBox();
        jcbKriteria.addItem("Pemasukan");
        jcbKriteria.addItem("Sisa Barang");
        jcbKriteria.setBounds(820, 135, 180, 30);
        jcbKriteria.setFont(new java.awt.Font("Calibri", 0, 21));
        jcbKriteria.setForeground(Color.black);
        jcbKriteria.setBackground(Color.white);
        jcbKriteria.setBorder(BorderFactory.createLineBorder(Color.black, 1)); 
        
        btnPilih=new JButton("SET");
        btnPilih.setBounds(1031, 135, 100, 30);
        btnPilih.setFont(new java.awt.Font("Calibri", 0, 21));
        btnPilih.setBackground(Color.WHITE);
        btnPilih.setForeground(Color.BLACK);
        btnPilih.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jcbKriteria.getSelectedItem().toString().equals("Pemasukan")) {
                    lParameter.setText("Tanggal Transaksi");
                    
                }else if (jcbKriteria.getSelectedItem().toString().equals("Sisa Barang")) {
                    lParameter.setText("Stok Kurang Dari");
                }
            }
        });
        
        btnSearch=new JButton("Search");
        btnSearch.setBounds(1031, 175, 100, 30);
        btnSearch.setFont(new java.awt.Font("Calibri", 0, 21));
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.BLACK);
        
        tblLaporan=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblLaporan.setBounds(431, 250, 909, 431);
        tblLaporan.setFont(new java.awt.Font("Calibri", 0, 18));
        tblLaporan.setForeground(Color.black);
        tblLaporan.setBackground(Color.WHITE);
        tblLaporan.setRowHeight(30);
        tblLaporan.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JScrollPane scrollPane = new JScrollPane(tblLaporan);
        scrollPane.setBounds(431, 250, 909, 431);
        
        lKeterangan=new JLabel("INI ADALAH LAPORAN");
        lKeterangan.setBounds(431, 680, 1000, 50); 
        lKeterangan.setFont(new java.awt.Font("Calibri", 1, 22));
        lKeterangan.setForeground(Color.black);
        
        add(lTitle);
        add(lkriteria);
        add(lParameter);
        add(jcbKriteria);
        add(scrollPane);
        add(parameter);
        add(btnPilih);
        add(btnSearch);
        add(lKeterangan);
        add(bg);
        
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1366,768);
        setVisible(true);
    }
    public void setTableLaporanBarang(ArrayList rows){
        dtmLaporan=new DefaultTableModel();
        dtmLaporan.setColumnCount(0);
        dtmLaporan.addColumn("ID");
        dtmLaporan.addColumn("Nama");
        dtmLaporan.addColumn("Stok");
        dtmLaporan.addColumn("Harga Beli");
        dtmLaporan.addColumn("Harga Jual");
        dtmLaporan.addColumn("Nama Suplier");
        dtmLaporan.addColumn("Status");
        dtmLaporan.addColumn("User Update");
        dtmLaporan.addColumn("Update At");
        int totalBarang=0;
        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);
            if (Integer.valueOf(row.get("stok").toString())<=Integer.valueOf(parameter.getText().toString())) {
                dtmLaporan.addRow(new Object[]{row.get("id_barang"),row.get("nama_barang"),row.get("stok"),row.get("harga_beli"),row.get("harga_jual"),row.get("nama_suplier"),row.get("status"),row.get("user_update"),row.get("update_at")});
                totalBarang+=1;
            }
            
        }
        lKeterangan.setText("Jumlah Barang Yang Stok Kurang Dari Sama Dengan "+parameter.getText()+" Sebanyak "+totalBarang);
        tblLaporan.setModel(dtmLaporan);
        
    }
    public void setTableLaporanPemasukan(ArrayList rows){
        dtmLaporan=new DefaultTableModel();
        dtmLaporan.setColumnCount(0);
        dtmLaporan.addColumn("ID");
        dtmLaporan.addColumn("Nama Pembeli");
        dtmLaporan.addColumn("Total");
        dtmLaporan.addColumn("Tanggal");
        dtmLaporan.addColumn("Status");
        dtmLaporan.addColumn("User Update");
        dtmLaporan.addColumn("Update At");
        int totalKas=0;
         
        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);
            totalKas+=Integer.valueOf(row.get("total").toString());
            dtmLaporan.addRow(new Object[]{row.get("id_order"),row.get("nama_pembeli"),row.get("total"),row.get("tanggal_pesanan"),row.get("status"),row.get("user_approve"),row.get("update_at")});
        }
        lKeterangan.setText("Total Kas Hari Ini Adalah Rp. "+totalKas );
        tblLaporan.setModel(dtmLaporan);
        
    }
    public String getKriteria(){
        return jcbKriteria.getSelectedItem().toString();
    }
    public String getParameter(){
        return parameter.getText();
    }
    public void btnSearchAddActionListener(ActionListener a){
        btnSearch.addActionListener(a);
    }
}
