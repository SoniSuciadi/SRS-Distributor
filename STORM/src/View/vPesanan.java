/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author sonys
 */
public class vPesanan extends vBackground{
    JDateChooser chooser;
    JTable tblPesanan;
    JButton btnSearch,btnApprove;
    JFrame fdisplay;
    JLabel idPembeli,namaPembeli,total,noinvoice,tanggal;
    JTable tblDetailPesanan;
    DefaultTableModel dtmPesanan,dtmDetailPesanan;
    public vPesanan(){
        JLabel lTitle=new JLabel("Data Pesanan");
        lTitle.setBounds(779, 50, 600, 100);
        lTitle.setFont(new java.awt.Font("Calibri", 1, 36));
        lTitle.setForeground(Color.white);
        
        JLabel lTanggal=new JLabel("Tanggal");
        lTanggal.setBounds(680, 130, 180, 30); 
        lTanggal.setFont(new java.awt.Font("Calibri", 1, 22));
        lTanggal.setForeground(Color.white);
        
        chooser = new JDateChooser();
        chooser.setLocale(Locale.US);
        chooser.setPreferredSize(new Dimension(150, 30));
        chooser.setDateFormatString("yyyy-MM-dd");
        chooser.setEnabled(true);
        chooser.setFont(new java.awt.Font("Calibri", 1, 18));
        chooser.setForeground(Color.black);
        
        JPanel panel = new JPanel();
        panel.add(chooser);
        panel.setBounds(762, 125, 160, 40); 
        panel.setBackground(Color.black);
        
        btnSearch=new JButton("Cari");
        btnSearch.setBounds(923, 130, 160, 30);
        btnSearch.setFont(new java.awt.Font("Calibri", 1, 21));
        btnSearch.setForeground(Color.black);
        btnSearch.setBackground(Color.white);
        
        tblPesanan=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        btnApprove=new JButton("TERIMA");
        btnApprove.setForeground(Color.black);
        btnApprove.setFont(new java.awt.Font("Calibri", 1, 22));
        btnApprove.setBounds(200,610, 170, 40);
        btnApprove.setBackground(Color.WHITE);
        btnApprove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        
        tblPesanan.setBounds(431, 201, 909, 431);
        tblPesanan.setFont(new java.awt.Font("Calibri", 0, 18));
        tblPesanan.setForeground(Color.black);
        tblPesanan.setBackground(Color.WHITE);
        tblPesanan.setRowHeight(30);
        tblPesanan.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        tblPesanan.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tblPesanan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
//                fdisplay.dispose();
                showDetailOrder();
               noinvoice.setText(tblPesanan.getValueAt(tblPesanan.getSelectedRow(), 0).toString()); 
               namaPembeli.setText( tblPesanan.getValueAt(tblPesanan.getSelectedRow(), 1).toString()); 
               total.setText(tblPesanan.getValueAt(tblPesanan.getSelectedRow(), 2).toString());
               tanggal.setText( tblPesanan.getValueAt(tblPesanan.getSelectedRow(), 3).toString()); 
                if (tblPesanan.getValueAt(tblPesanan.getSelectedRow(), 4).toString().equals("Approve")) {
                    btnApprove.setEnabled(false);
                }else{
                    btnApprove.setEnabled(true);
                }
              
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
//        showDetailOrder();
        
        JScrollPane scrollPane = new JScrollPane(tblPesanan);
        scrollPane.setBounds(431, 201, 909, 431);
        scrollPane.setBackground(Color.WHITE);
        
        add(lTanggal);
        add(panel);
        add(btnSearch);
        add(scrollPane);
        add(lTitle);
        add(bg);
        
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1366,768);
        setVisible(true);
    }
    public void btnApproveAddActionListener(ActionListener a){
        btnApprove.addActionListener(a);
    }
    public void setTableDetailOrder(ArrayList rows){
        dtmDetailPesanan=new DefaultTableModel();
        dtmDetailPesanan.setColumnCount(0);
        dtmDetailPesanan.addColumn("No");
        dtmDetailPesanan.addColumn("Nama Barang");
        dtmDetailPesanan.addColumn("Harga");
        dtmDetailPesanan.addColumn("Jumlah");
        dtmDetailPesanan.addColumn("Subtotal");

        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);

            dtmDetailPesanan.addRow(new Object[]{i+1,row.get("nama_barang"),row.get("harga_jual"),row.get("jumlah"),row.get("subtotal")});
        }
        tblDetailPesanan.setModel(dtmDetailPesanan);
        
    }
    public void setTableBarang(ArrayList rows){
        dtmPesanan=new DefaultTableModel();
        dtmPesanan.setColumnCount(0);
        dtmPesanan.addColumn("Invoice");
        dtmPesanan.addColumn("Nama Pembeli");
        dtmPesanan.addColumn("Total");
        dtmPesanan.addColumn("Tanggal");
        dtmPesanan.addColumn("Status");
        dtmPesanan.addColumn("User Approve");
        dtmPesanan.addColumn("Update At");

        
        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);

            dtmPesanan.addRow(new Object[]{row.get("id_order"),row.get("nama_pembeli"),row.get("total"),row.get("tanggal_pesanan"),row.get("status"),row.get("user_approve"),row.get("update_at")});
        }
        tblPesanan.setModel(dtmPesanan);
        
    }
    public JTable getTablePesanan(){
        return tblPesanan;
    }
    public String getTanggal(){
        return new java.sql.Date(chooser.getDate().getTime()).toString();
    }
    public HashMap getApproveData(){
        HashMap hashMap=new HashMap();
        hashMap.put("userID", getUserID());
        hashMap.put("invoice", noinvoice.getText());
        return hashMap;
    }
    public void showDetailOrder(){
        fdisplay=new JFrame("DISPLAY PENJUALAN");

        JLabel lnamaPembeli=new JLabel("Nama Pembeli");
        lnamaPembeli.setBounds(115, 40, 180, 30);
        lnamaPembeli.setFont(new java.awt.Font("Calibri", 1, 22));
        lnamaPembeli.setForeground(Color.white);
        
        namaPembeli=new JLabel();
        namaPembeli.setBounds(285, 40, 180, 30);
        namaPembeli.setFont(new java.awt.Font("Calibri", 1, 22));
        namaPembeli.setForeground(Color.white);
        namaPembeli.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel ltotal=new JLabel("Total");
        ltotal.setBounds(115, 100, 180, 30);
        ltotal.setFont(new java.awt.Font("Calibri", 1, 22));
        ltotal.setForeground(Color.white);
        
        total=new JLabel("");
        total.setBounds(285, 100, 180, 30);
        total.setFont(new java.awt.Font("Calibri", 1, 22));
        total.setForeground(Color.white);
        total.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JLabel lnoinvoice=new JLabel("No. Invoice");
        lnoinvoice.setBounds(115, 160, 180, 30);
        lnoinvoice.setFont(new java.awt.Font("Calibri", 1, 22));
        lnoinvoice.setForeground(Color.white);
        
        noinvoice=new JLabel();
        noinvoice.setBounds(285, 160, 180, 30);
        noinvoice.setFont(new java.awt.Font("Calibri", 1, 22));
        noinvoice.setForeground(Color.white);
        noinvoice.setBorder(BorderFactory.createLineBorder(Color.black, 1));        
        
        JLabel ltanggal=new JLabel("Tanggal");
        ltanggal.setBounds(115, 220, 180, 30);
        ltanggal.setFont(new java.awt.Font("Calibri", 1, 22));
        ltanggal.setForeground(Color.white);
        
        tanggal=new JLabel();
        tanggal.setBounds(285, 220, 180, 30);
        tanggal.setFont(new java.awt.Font("Calibri", 1, 22));
        tanggal.setForeground(Color.white);
        tanggal.setBorder(BorderFactory.createLineBorder(Color.black, 1));  
        
        tblDetailPesanan = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDetailPesanan.setBounds(10, 240, 563,320);
        tblDetailPesanan.setFont(new java.awt.Font("Calibri", 0, 18));
        tblDetailPesanan.setForeground(Color.black);
        tblDetailPesanan.setBackground(Color.WHITE);
        tblDetailPesanan.setRowHeight(30);
        tblDetailPesanan.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        tblDetailPesanan.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        JScrollPane scrollPane = new JScrollPane(tblDetailPesanan);
        scrollPane.setBounds(10, 320, 563,280);
        
        
        
        fdisplay.add(scrollPane);
        fdisplay.add(lnamaPembeli);
        fdisplay.add(namaPembeli);
        fdisplay.add(ltotal);
        fdisplay.add(total);
        fdisplay.add(lnoinvoice);
        fdisplay.add(noinvoice);
        fdisplay.add(ltanggal);
        fdisplay.add(tanggal);
        fdisplay.add(btnApprove);
        fdisplay.add(bg);
        
        
        fdisplay.setSize(600,700);
        fdisplay.setLayout(null);
        fdisplay.setVisible(true);
        fdisplay.setDefaultCloseOperation(1);
        fdisplay.setBackground(Color.WHITE);
    }
    public void btnSearchAddActionListener(ActionListener a){
        btnSearch.addActionListener(a);
    }
}
