/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author sonys
 */
public class vLogin extends JFrame{
    private JTextField tfUserId;
    private JPasswordField tfPassword;
    private JButton btnLogin, btnReset;
    public vLogin(){
        JLabel lTanggal =new JLabel("Tanggal  :");
        lTanggal.setFont(new java.awt.Font("Tahoma", 0, 14));
        lTanggal.setForeground(new java.awt.Color(0, 0, 0));
        lTanggal.setBounds(8, 0,150, 60);
        
        LocalDate tgl = LocalDate.now();
        JLabel tanggal =new JLabel(tgl.toString());
        tanggal.setFont(new java.awt.Font("Tahoma", 0, 14));
        tanggal.setForeground(new java.awt.Color(0, 0, 0));
        tanggal.setBounds(70, 0,150, 60);
        
        JLabel jLogo=new JLabel(new ImageIcon(getClass().getResource("/Picture/logo_strom_desktop.jpg")));
        jLogo.setBounds(0, 50,500, 200);
        jLogo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lWellcome =new JLabel("STROM",JLabel.CENTER);
        lWellcome.setFont(new java.awt.Font("Tahoma", 0, 14));
        lWellcome.setForeground(new java.awt.Color(0, 0, 0));
        lWellcome.setBounds(0,210,500, 30);
        
        JLabel lLogin =new JLabel("SILAKAN LOGIN",JLabel.CENTER);
        lLogin.setFont(new java.awt.Font("Tahoma", 0, 14));
        lLogin.setForeground(new java.awt.Color(0, 0, 0));
        lLogin.setBounds(0,230,500, 30);
        
        JLabel lUserid= new JLabel("Username");
        lUserid.setFont(new java.awt.Font("Tahoma", 0, 14));
        lUserid.setForeground(new java.awt.Color(0, 0, 0));
        lUserid.setHorizontalAlignment(SwingConstants.CENTER);
        lUserid.setBounds(150,260,200, 30);
        
        tfUserId= new JTextField();
        tfUserId.setFont(new java.awt.Font("Tahoma", 0, 14));
        tfUserId.setForeground(new java.awt.Color(0, 0, 0));
        tfUserId.setHorizontalAlignment(SwingConstants.CENTER);
        tfUserId.setBounds(150,290,200, 30);
        
        JLabel lPassword= new JLabel("Password");
        lPassword.setFont(new java.awt.Font("Tahoma", 0, 14));
        lPassword.setForeground(new java.awt.Color(0, 0, 0));
        lPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lPassword.setBounds(150,320,200, 30);
        
        tfPassword= new JPasswordField();
        tfPassword.setFont(new java.awt.Font("Tahoma", 0, 14));
        tfPassword.setForeground(new java.awt.Color(0, 0, 0));
        tfPassword.setHorizontalAlignment(SwingConstants.CENTER);
        tfPassword.setBounds(150,350,200, 30);
        
        btnLogin=new JButton("LOGIN");
        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnLogin.setBounds(150, 400, 90, 30);
        btnLogin.setBackground(Color.WHITE);
                
        
        btnReset=new JButton("Reset");
        btnReset.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnReset.setBounds(260, 400, 90, 30);
        btnReset.setBackground(Color.WHITE);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfUserId.setText("");
                tfPassword.setText("");
            }
        });
        add(lTanggal);
        add(tanggal);
        add(jLogo);
//        add(lWellcome);
//        add(lLogin);
        add(lUserid);
        add(tfUserId);
        add(lPassword);
        add(tfPassword);
        add(btnLogin);
        add(btnReset);
        
        getContentPane().setBackground(Color.WHITE);
        setTitle("LOGIN");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setVisible(true);
    }
    public void btnLoginAddActionListener(ActionListener actionListener){
        btnLogin.addActionListener(actionListener);
    }
    public String getUsername(){
        return tfUserId.getText().toString();
    }
    public String getPassword(){
        return tfPassword.getText().toString();
    }
}
