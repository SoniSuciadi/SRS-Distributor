/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.gudang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author sonys
 */
public class Ltime extends JLabel implements ActionListener {
    private SimpleDateFormat format;
    private Timer timer;
    private Date date;
 
    public Ltime() {
        timer = new Timer(1000, this);
        format = new SimpleDateFormat("dd MMMM yyyy, HH:m:ss");
        date = new Date();
        timer.start();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        date.setTime(System.currentTimeMillis());
        this.setText(format.format(date));
    }
    
}
