/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sonys
 */
public class vSuplier extends vBackground{
    JTable tblsuplier;
    JButton btnTambah,jbsearchnama;
    DefaultTableModel dtmSuplier;
    JTextField jtnamasuplier;
    JTextField idSuplier,namaSuplier,alamatSuplier,ponselSuplier;
    JButton btnSaveSuplier;
    public vSuplier(){
        JLabel lTitle=new JLabel("Suplier");
        lTitle.setBounds(750, 50, 600, 100);
        lTitle.setFont(new java.awt.Font("Calibri", 1, 36));
        lTitle.setForeground(Color.white);
        
        tblsuplier=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblsuplier.setBounds(431, 201, 909, 431);
        tblsuplier.setFont(new java.awt.Font("Calibri", 0, 18));
        tblsuplier.setForeground(Color.black);
        tblsuplier.setBackground(Color.WHITE);
        tblsuplier.setRowHeight(30);
        tblsuplier.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        tblsuplier.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tblsuplier.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
               formSuplier();
               idSuplier.setText(tblsuplier.getValueAt(tblsuplier.getSelectedRow(), 0).toString()); 
               
               namaSuplier.setText( tblsuplier.getValueAt(tblsuplier.getSelectedRow(), 1).toString()); 
               alamatSuplier.setText(tblsuplier.getValueAt(tblsuplier.getSelectedRow(), 2).toString());
               ponselSuplier.setText( tblsuplier.getValueAt(tblsuplier.getSelectedRow(), 3).toString()); 
               

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
        
        JLabel lnamasuplier=new JLabel("Nama Suplier");
        lnamasuplier.setBounds(636, 160, 180, 30);
        lnamasuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        lnamasuplier.setForeground(Color.white);
        
        jtnamasuplier=new JTextField();
        jtnamasuplier.setBounds(800, 160, 180, 30);
        jtnamasuplier.setFont(new java.awt.Font("Calibri", 0, 21));
        jtnamasuplier.setForeground(Color.black);
        jtnamasuplier.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        jbsearchnama=new JButton("SEARCH");
        jbsearchnama.setBounds(1001, 160, 120, 30);
        jbsearchnama.setFont(new java.awt.Font("Calibri", 0, 21));
        jbsearchnama.setForeground(Color.black);
        jbsearchnama.setBackground(Color.white);
        jbsearchnama.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        btnTambah=new JButton("TAMBAH");
        btnTambah.setBounds(1189,674, 150, 60);
        btnTambah.setForeground(Color.black);
        btnTambah.setFont(new java.awt.Font("Calibri", 1, 22));
        btnTambah.setBackground(Color.white);
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                formSuplier();
            }
        });

        JScrollPane scrollPane = new JScrollPane(tblsuplier);
        scrollPane.setBounds(431, 201, 909, 431);
        
        btnSaveSuplier= new JButton("SIMPAN");
        btnSaveSuplier.setBounds(110, 300, 160, 50);
        btnSaveSuplier.setForeground(Color.black);
        btnSaveSuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        btnSaveSuplier.setBackground(Color.white);
        btnSaveSuplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.out.println(getFormSuplier());
            }
        });
        
        add(scrollPane);
        add(lnamasuplier);
        add(jtnamasuplier);
        add(jbsearchnama);        
        add(btnTambah);
        add(lTitle);
        add(bg);
        
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1366,768);
        setVisible(true);
    }
    public void btnSearchAddActionListener(ActionListener a){
        jbsearchnama.addActionListener(a);
    }
    public String getJTNamaSuplier(){
        return jtnamasuplier.getText();
    }
    public void setTableSuplier(ArrayList rows){
        dtmSuplier=new DefaultTableModel();
        dtmSuplier.setColumnCount(0);
        dtmSuplier.addColumn("ID");
        dtmSuplier.addColumn("Nama");
        dtmSuplier.addColumn("Alamat");
        dtmSuplier.addColumn("Ponsel");
        dtmSuplier.addColumn("User Update");
        dtmSuplier.addColumn("Update At");
        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);

            dtmSuplier.addRow(new Object[]{row.get("id_suplier"),row.get("nama_suplier"),row.get("alamat"),row.get("ponsel"),row.get("user_update"),row.get("update_at")});
        }
        tblsuplier.setModel(dtmSuplier);
        
    }
    public HashMap getFormSuplier(){
        HashMap hashMap=new HashMap();
        if (idSuplier.getText().equals("")) {
            Random rand = new Random();
            int randomNum = rand.nextInt((10000 - 999) +1) + 999;
            System.out.println(randomNum);
            String kd="11"+randomNum;
            hashMap.put("id_suplier", Integer.valueOf(kd));
        }else{
            hashMap.put("id_suplier", Integer.valueOf(idSuplier.getText()));
        }
        hashMap.put("nama_suplier", namaSuplier.getText().toString());
        hashMap.put("alamat", alamatSuplier.getText().toString());
        hashMap.put("ponsel", ponselSuplier.getText().toString());
        hashMap.put("user_update", getUserID());;
        
        return hashMap;
    }
    public void formSuplier(){
        
        JFrame formSuplier=new JFrame("FORM ENTRY SUPLIER ");
        
        JLabel lksuplier=new JLabel("ID Suplier ");
        lksuplier.setBounds(15, 20, 130, 30);
        lksuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        lksuplier.setForeground(Color.white);

        idSuplier=new JTextField();
        idSuplier.setBounds(210, 20, 160, 30);
        idSuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        idSuplier.setForeground(Color.black);
        idSuplier.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        idSuplier.setEditable(false);
        
        JLabel lsuplier=new JLabel("Nama Suplier");
        lsuplier.setBounds(15, 95, 130, 30);
        lsuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        lsuplier.setForeground(Color.white);
        
        namaSuplier=new JTextField();
        namaSuplier.setBounds(210, 95, 160, 30);
        namaSuplier.setFont(new java.awt.Font("Calibri", 0, 21));
        namaSuplier.setForeground(Color.black);
                
        JLabel lalamat=new JLabel("Alamat    ");
        lalamat.setBounds(15, 170, 130, 30);
        lalamat.setFont(new java.awt.Font("Calibri", 1, 22));
        lalamat.setForeground(Color.white);
        
        alamatSuplier=new JTextField();
        alamatSuplier.setBounds(210, 170, 160, 30);
        alamatSuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        alamatSuplier.setForeground(Color.black);
        alamatSuplier.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lnohp=new JLabel("Nomor HP  ");
        lnohp.setBounds(15, 245, 130, 30);
        lnohp.setFont(new java.awt.Font("Calibri", 1, 22));
        lnohp.setForeground(Color.white);
        
        ponselSuplier=new JTextField();
        ponselSuplier.setBounds(210, 245, 160, 30);
        ponselSuplier.setFont(new java.awt.Font("Calibri", 0, 20));
        ponselSuplier.setForeground(Color.black);
        ponselSuplier.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
            String value = ponselSuplier.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
               ponselSuplier.setEditable(true);
               
            } else {
               ponselSuplier.setEditable(false);
          
            }
         }
      });
       
        

        formSuplier.add(lksuplier);
        formSuplier.add(idSuplier);
        formSuplier.add(lsuplier);
        formSuplier.add(namaSuplier);
        formSuplier.add(lalamat);
        formSuplier.add(alamatSuplier);
        formSuplier.add(lnohp);
        formSuplier.add(ponselSuplier);
        formSuplier.add(btnSaveSuplier);
        formSuplier.add(bg);
        
        formSuplier.setSize(400,400);
        formSuplier.setLayout(null);
        formSuplier.setVisible(true);
        formSuplier.setDefaultCloseOperation(1);
    }
    public void btnSaveSuplierAddActionListener(ActionListener a){
        btnSaveSuplier.addActionListener(a);
    }
}
