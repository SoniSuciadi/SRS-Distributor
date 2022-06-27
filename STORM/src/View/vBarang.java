/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sonys
 */
public class vBarang extends vBackground{
    JButton btnAdd,btnSearch,btnSimpan,btnPilih;
    JTextField jtNama,tfkbarang,jtnamabarang,jtstok,jthbeli,jthjual;
    JTable tblBarang;
    JFrame jfmbarang;
    InputStream ins = null;
    InputStream insQR=null;
    JLabel jtkdSuplier,lGambar,lQR;
    JComboBox jcbSuplier,jcbStatus;
    DefaultTableModel dtmBarang;
    JFileChooser jfcSearch;
    String path=null;
    
    
    String filePath = "QRCode.png";
    String charset = "UTF-8"; // or "ISO-8859-1"
    File imageFile =null;
    public vBarang(){
        JLabel lTitle=new JLabel("Data Barang");
        lTitle.setBounds(786, 50, 600, 100);
        lTitle.setFont(new java.awt.Font("Calibri", 1, 36));
        lTitle.setForeground(Color.white);
        
        JLabel lnama=new JLabel("Nama Barang");
        lnama.setBounds(636, 160, 180, 30);
        lnama.setFont(new java.awt.Font("Calibri", 1, 22));
        lnama.setForeground(Color.white);
        
        jtNama=new JTextField();
        jtNama.setBounds(780, 160, 200, 30);
        jtNama.setFont(new java.awt.Font("Calibri", 0, 21));
        jtNama.setForeground(Color.BLACK);
        
        btnSearch=new JButton("Cari");
        btnSearch.setBounds(1001, 160, 120, 30);
        btnSearch.setFont(new java.awt.Font("Calibri", 0, 21));
        btnSearch.setForeground(Color.black);
        btnSearch.setBackground(Color.WHITE);
        
        tblBarang=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblBarang.setBounds(431, 201, 909, 431);
        tblBarang.setFont(new java.awt.Font("Calibri", 0, 18));
        tblBarang.setForeground(Color.black);
        tblBarang.setBackground(Color.white);
        tblBarang.setRowHeight(30);
        tblBarang.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        tblBarang.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
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
       
        JScrollPane scrollPane = new JScrollPane(tblBarang);
        scrollPane.setBounds(431, 201, 909, 431);
        
        
        btnAdd=new JButton("Tambah");
        btnAdd.setForeground(Color.black);
        btnAdd.setFont(new java.awt.Font("Calibri", 1, 22));
        btnAdd.setBounds(1200,674, 150, 60);
        btnAdd.setBackground(Color.WHITE);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                jfmbarang.dispose();
                showFormBarang();
            }
        });
        
        jcbSuplier=new JComboBox();
        jcbSuplier.setBounds(328, 320, 240, 30);
        jcbSuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        jcbSuplier.setForeground(Color.black);
        jcbSuplier.setBackground(Color.white);
        jcbSuplier.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        
        btnSimpan= new JButton("Simpan");
        btnSimpan.setBounds(197, 665, 150, 40);
        btnSimpan.setFont(new java.awt.Font("Calibri", 1, 22));
        btnSimpan.setForeground(Color.black);
        btnSimpan.setBackground(Color.WHITE);
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jfmbarang.dispose();
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
    public void setjcbSuplier(ArrayList a){
        for (int i = 0; i < a.size(); i++) {
            HashMap hm=(HashMap) a.get(i);
            jcbSuplier.addItem(hm.get("nama_suplier"));
            
        }
    }
    public JComboBox getSuplier (){
        return jcbSuplier;
    }
    public void setIDSuplier(String a){
        jtkdSuplier.setText(a);
    }
    public void setTableBarang(ArrayList rows){
        dtmBarang=new DefaultTableModel();
        dtmBarang.setColumnCount(0);
        dtmBarang.addColumn("ID");
        dtmBarang.addColumn("Nama");
        dtmBarang.addColumn("Stok");
        dtmBarang.addColumn("Harga Beli");
        dtmBarang.addColumn("Harga Jual");
        dtmBarang.addColumn("Nama Suplier");
        dtmBarang.addColumn("Status");
        dtmBarang.addColumn("User Update");
        dtmBarang.addColumn("Update At");

        for (int i = 0; i < rows.size(); i++) {
            HashMap row= (HashMap) rows.get(i);

            dtmBarang.addRow(new Object[]{row.get("id_barang"),row.get("nama_barang"),row.get("stok"),row.get("harga_beli"),row.get("harga_jual"),row.get("nama_suplier"),row.get("status"),row.get("user_update"),row.get("update_at")});
        }
        tblBarang.setModel(dtmBarang);
        
    }
    public String getSearchField(){
        return jtNama.getText();
    }
    public void btnSearchAddActionListener(ActionListener a){
        btnSearch.addActionListener(a);
    }
    public void disableTFIDBarang(){
        tfkbarang.setEditable(false);
    }
    public void setFormBarang(HashMap a){
        tfkbarang.setText(a.get("id_barang").toString());
//        tfkbarang.setEnabled(false);
        jtnamabarang.setText(a.get("nama_barang").toString());
        jtstok.setText(a.get("stok").toString());
        jthbeli.setText(a.get("harga_beli").toString());
        jthjual.setText(a.get("harga_jual").toString());
//        jtkdSuplier.setText(a.get("nama_suplier").toString());
        byte[] img=(byte[]) a.get("image");
        ins=new ByteArrayInputStream(img);
        ImageIcon image=new ImageIcon(img);
//        InputStream is=new FileInputStream(path)
        Image im=image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon ii=new ImageIcon(im);
        lGambar.setIcon(ii);
        
        byte[] imgQR=(byte[]) a.get("qr_code");
        insQR=new ByteArrayInputStream(imgQR);
        ImageIcon imageQR=new ImageIcon(imgQR);
//        InputStream is=new FileInputStream(path)
        Image imQR=imageQR.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iiQR=new ImageIcon(imQR);
        lQR.setIcon(iiQR);
        
    }
    
    public JTable getTable(){
        return tblBarang;
    }
    public HashMap getForm (){
        
        HashMap hm=new HashMap();
        if (tfkbarang.getText().equals("")) {
            Random rand = new Random();
            int randomNum = rand.nextInt((10000 - 999) +1) + 999;
            System.out.println(randomNum);
            String kd="88"+randomNum;
            hm.put("id_barang", Integer.valueOf(kd));
            
            //QR
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            try {
                createQRCode(kd, filePath, charset, hintMap, 200, 200);
                BufferedImage loadImg = loadImage(filePath);
            } catch (WriterException ex) {
                Logger.getLogger(vBarang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(vBarang.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage loadImg = loadImage(filePath);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {                         
                ImageIO.write(loadImg, "jpeg", os);
            } catch (IOException ex) {
                Logger.getLogger(vBarang.class.getName()).log(Level.SEVERE, null, ex);
            }
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            hm.put("qr_code", is);
        }else{
            hm.put("id_barang", Integer.valueOf(tfkbarang.getText()));
        }
        if (path==null) {
//            File fBlob = new File(lGambar.getr());
//            FileInputStream is = new FileInputStream ( fBlob );
            hm.put("image", ins);
          
        }else{
            try {
            ins = new FileInputStream(path);
            hm.put("image", ins);
         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(vBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        hm.put("nama_barang", jtnamabarang.getText());
        hm.put("stok", Integer.valueOf(jtstok.getText()));
        hm.put("harga_beli", Integer.valueOf(jthbeli.getText()));
        hm.put("harga_jual", Integer.valueOf(jthjual.getText()));
        hm.put("id_suplier",Integer.valueOf(jtkdSuplier.getText()));
        hm.put("status", jcbStatus.getSelectedItem().toString());
        hm.put("user_update",Integer.valueOf(getUserID())) ;
        return hm;
    }
    
    public void showFormBarang(){
        jfmbarang=new JFrame("FORM MANAGEMEN BARANG");
        
        
        
        JLabel lkbarang=new JLabel("Kode Barang ");
        lkbarang.setBounds(15, 20, 130, 30);
        lkbarang.setFont(new java.awt.Font("Calibri", 1, 22));
        lkbarang.setForeground(Color.white);

        tfkbarang=new JTextField();
        tfkbarang.setEnabled(false);
        tfkbarang.setBounds(328, 20, 240, 30);
        tfkbarang.setFont(new java.awt.Font("Calibri", 1, 22));
        tfkbarang.setFont(new java.awt.Font("Calibri", 1, 22));
        tfkbarang.setForeground(Color.black);
        tfkbarang.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lbarang=new JLabel("Nama Barang");
        lbarang.setBounds(15, 70, 130, 30);
        lbarang.setFont(new java.awt.Font("Calibri", 1, 22));
        lbarang.setForeground(Color.white);
        
        jtnamabarang=new JTextField();
        jtnamabarang.setBounds(328, 70, 240, 30);
        jtnamabarang.setFont(new java.awt.Font("Calibri", 1, 22));
        jtnamabarang.setForeground(Color.black);
        jtnamabarang.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lstok=new JLabel("Stok");
        lstok.setBounds(15, 120, 130, 30);
        lstok.setFont(new java.awt.Font("Calibri", 1, 22));
        lstok.setForeground(Color.white);
        
        
        jtstok=new JTextField();
        jtstok.setBounds(328, 120, 240, 30);
        jtstok.setFont(new java.awt.Font("Calibri", 1, 22));
        jtstok.setFont(new java.awt.Font("Calibri", 1, 22));
        jtstok.setForeground(Color.black);
        jtstok.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jtstok.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
            String value = jtstok.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
               jtstok.setEditable(true);
               
            } else {
               jtstok.setEditable(false);
          
            }
         }
      });
        
        JLabel lhbeli=new JLabel("Harga Beli");
        lhbeli.setBounds(15, 170, 130, 30);
        lhbeli.setFont(new java.awt.Font("Calibri", 1, 22));
        lhbeli.setForeground(Color.white);
        
        jthbeli=new JTextField();
        jthbeli.setBounds(328, 170, 240, 30);
        jthbeli.setFont(new java.awt.Font("Calibri", 1, 22));
        jthbeli.setForeground(Color.black);
        jthbeli.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jthbeli.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = jthbeli.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
               jthbeli.setEditable(true);
               
            } else {
               jthbeli.setEditable(false);
          
            }
         }
      });
        
        JLabel lhjual=new JLabel("Harga Jual ");
        lhjual.setBounds(15, 220, 130, 30);
        lhjual.setFont(new java.awt.Font("Calibri", 1, 22));
        lhjual.setForeground(Color.white);
        
        jthjual=new JTextField();
        jthjual.setBounds(328, 220, 240, 30);
        jthjual.setFont(new java.awt.Font("Calibri", 1, 22));
        jthjual.setForeground(Color.black);
        jthjual.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jthjual.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = jthjual.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
               jthjual.setEditable(true);
               
            } else {
               jthjual.setEditable(false);
          
            }
         }
      });
       
        JLabel lkdsuplier=new JLabel("ID Suplier");
        lkdsuplier.setBounds(15, 270, 130, 30);
        lkdsuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        lkdsuplier.setForeground(Color.white);
        
        jtkdSuplier=new JLabel("0");
        jtkdSuplier.setBounds(328, 270, 240, 30);
        jtkdSuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        jtkdSuplier.setForeground(Color.white);
        jtkdSuplier.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jtkdSuplier.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        JLabel lnamasuplier=new JLabel("Nama Suplier");
        lnamasuplier.setBounds(15, 320, 130, 30);
        lnamasuplier.setFont(new java.awt.Font("Calibri", 1, 22));
        lnamasuplier.setForeground(Color.white);
        
        JLabel lStatusBarang=new JLabel("Status");
        lStatusBarang.setBounds(15, 370, 240, 30);
        lStatusBarang.setFont(new java.awt.Font("Calibri", 1, 22));
        lStatusBarang.setForeground(Color.white);
        
        jcbStatus=new JComboBox();
        jcbStatus.addItem("Available");
        jcbStatus.addItem("Not Available");
        jcbStatus.setBounds(328, 370, 240, 30);
        jcbStatus.setFont(new java.awt.Font("Calibri", 1, 22));
        jcbStatus.setForeground(Color.black);
        jcbStatus.setBackground(Color.white);
        jcbStatus.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        lGambar=new JLabel();
        lGambar.setBounds(72, 420, 200, 200);
        lGambar.setFont(new java.awt.Font("Calibri", 1, 22));
        lGambar.setForeground(Color.black);
        lGambar.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        lQR=new JLabel();
        lQR.setBounds(309, 420, 200, 200);
        lQR.setFont(new java.awt.Font("Calibri", 1, 22));
        lQR.setForeground(Color.black);
        lQR.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        btnPilih= new JButton("Pilih");
        btnPilih.setBounds(136, 630, 80, 30);
        btnPilih.setFont(new java.awt.Font("Calibri", 1, 16));
        btnPilih.setForeground(Color.black);
        btnPilih.setBackground(Color.WHITE);
        btnPilih.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jfcSearch= new JFileChooser("SEACRH");
                jfcSearch.setCurrentDirectory(new java.io.File("C:\\Users\\sonys\\Pictures"));
                jfcSearch.setDialogTitle("Pilih Gambar");
                jfcSearch.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfcSearch.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg","jpeg", "png", "tif"));
                jfcSearch.setAcceptAllFileFilterUsed(false);
                int result=jfcSearch.showSaveDialog(null);
                if (result==JFileChooser.APPROVE_OPTION) {
                    File selectedFile=jfcSearch.getSelectedFile();
                    path=selectedFile.getAbsolutePath();
                    ImageIcon image=new ImageIcon(path);
                    Image im=image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon ii=new ImageIcon(im);
                    lGambar.setIcon(ii);
                    System.out.println(path);
                }
            }
        });
        
        
        
        
        
        jfmbarang.add(lkbarang);
        jfmbarang.add(tfkbarang);
        jfmbarang.add(lbarang);
        jfmbarang.add(jtnamabarang);
        jfmbarang.add(lstok);
        jfmbarang.add(jtstok);
        jfmbarang.add(lhbeli);
        jfmbarang.add(jthbeli);
        jfmbarang.add(lhjual);
        jfmbarang.add(jthjual);
        jfmbarang.add(lkdsuplier);
        jfmbarang.add(jtkdSuplier);
        jfmbarang.add(jthjual);
        jfmbarang.add(lnamasuplier);
        jfmbarang.add(jcbSuplier);
        jfmbarang.add(lStatusBarang);
        jfmbarang.add(jcbStatus);
        jfmbarang.add(lGambar);
        jfmbarang.add(lQR);
        jfmbarang.add(btnSimpan);
        jfmbarang.add(btnPilih);
        jfmbarang.add(bg);
        
        jfmbarang.setBackground(Color.black);
        
        jfmbarang.setSize(600,750);
        jfmbarang.setLayout(null);
        jfmbarang.setVisible(true);
        jfmbarang.setDefaultCloseOperation(1);
    
    }
    public void btnSimpanAddActionListener(ActionListener a){
        btnSimpan.addActionListener(a);
    }
    
    public static BufferedImage loadImage(String ref){
        BufferedImage bimg =null;
      try {
          bimg = ImageIO.read(new File(ref));
      } catch (IOException e) {
          }
         return bimg;
     }
     public static BufferedImage resize(BufferedImage img, int newW,int newH){
         int w = img.getWidth();
         int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
     }

     public static void createQRCode(String qrCodeData, String filePath,
        String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
        throws WriterException, IOException {
        @SuppressWarnings("unchecked")
        BitMatrix matrix = new MultiFormatWriter().encode(
        new String(qrCodeData.getBytes(charset), charset),
        BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
        .lastIndexOf('.') + 1), new File(filePath));
     }
 
}
