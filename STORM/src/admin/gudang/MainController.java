/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.gudang;

import Model.mBarang;
import Model.mBranch;
import Model.mBranchStok;
import Model.mDetailOrder;
import Model.mNotification;
import Model.mOrder;
import Model.mPengguna;
import Model.mSuplier;
import View.vBarang;
import View.vBranch;
import View.vLaporan;
import View.vLogin;
import View.vPengguna;
import View.vPesanan;
import View.vSuplier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author sonys
 */
public class MainController {
    boolean login;
    String nama; 
    String userid;
    String jabatan;
    
    public MainController(){
        
        
        vLogin vlogin=new vLogin();
        vlogin.btnLoginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean login=false;
                try {
                    for (int i = 0; i < new mPengguna().getPengguna().size(); i++) {
                        HashMap hm=(HashMap) new mPengguna().getPengguna().get(i);
                        if (vlogin.getUsername().equals(hm.get("id_user").toString())&&vlogin.getPassword().equals(hm.get("password").toString())) {
                            nama=hm.get("nama_user").toString();
                            userid=hm.get("id_user").toString();
                            jabatan=hm.get("jabatan").toString();
                            login=true;
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (login) {
                    if (jabatan.equals("Gudang")|| jabatan.equals("Pemilik")) {
                        JOptionPane.showMessageDialog(null, "LOGIN BERHASIL");
                        vlogin.dispose();
                        controllerBarang();
                    }else{
                        login=false;
                        JOptionPane.showMessageDialog(null, "Maaf Anda Tidak Memiliki Hak Akses");
                        vlogin.dispose();
                    }
                                      
                    
                }
                else{
                    login=false;
                    JOptionPane.showMessageDialog(null, "LOGIN GAGAL");
                    vlogin.dispose();
                }

            }
            
        });
    }
    public void controllerBarang(){
        vBarang vbarang=new vBarang();
        vbarang.setUserData(nama, userid);
        try {
            vbarang.setTableBarang(new mBarang().getBarang());
            vbarang.setjcbSuplier(new mSuplier().getSuplier());
            vbarang.getTable().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    try {
                        vbarang.showFormBarang();
                        HashMap hm= (HashMap) new mBarang().getBarangbyID(Integer.valueOf(vbarang.getTable().getValueAt(vbarang.getTable().getSelectedRow(), 0).toString())).get(0);
           
                        vbarang.setFormBarang(hm);
                        System.out.println(hm);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    
                }
            });
            vbarang.getSuplier().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        HashMap idsuplier=(HashMap) new mSuplier().getSuplierbyName(vbarang.getSuplier().getSelectedItem().toString()).get(0);
                        vbarang.setIDSuplier(idsuplier.get("id_suplier").toString());
                                } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vbarang.postNotification(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new mNotification().insertNotification(vbarang.getFormNotif());
            }
        });
        vbarang.btnSimpanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                if (Integer.valueOf(vbarang.getForm().get("id_suplier").toString())==0) {
//                    JOptionPane.showMessageDialog( null, "Suplier Belum Dipilih", "Error", JOptionPane.ERROR_MESSAGE);
//                }else if (Integer.valueOf(vbarang.getForm().get("harga_jual").toString())<=Integer.valueOf(vbarang.getForm().get("harga_beli").toString())) {
//                    JOptionPane.showMessageDialog( null, "Harga Jual Lebih Kecil dari Harga Jual", "Error", JOptionPane.ERROR_MESSAGE);
//                }else if(Integer.valueOf(vbarang.getForm().get("harga_jual").toString())<=0 || Integer.valueOf(vbarang.getForm().get("harga_jual").toString())<=0){
//                    JOptionPane.showMessageDialog( null, "Harga Invalid", "Error", JOptionPane.ERROR_MESSAGE);
//                }else if (Integer.valueOf(vbarang.getForm().get("id_suplier").toString())<=0) {
//                    JOptionPane.showMessageDialog( null, "Stok Invalid", "Error", JOptionPane.ERROR_MESSAGE);
//                }else if (vbarang.getForm().get("nama_barang").toString().equals("")) {
//                    JOptionPane.showMessageDialog( null, "Nama Barang Invalid", "Error", JOptionPane.ERROR_MESSAGE);
//                }   else{
                  
                   
//                }
                boolean update=false;
                boolean formValid=true;
                HashMap barang=vbarang.getForm();
                if (barang.get("nama_barang").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Nama Barang Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    formValid=false;
                }else if (Integer.valueOf(barang.get("stok").toString())<=0) {
                    JOptionPane.showMessageDialog( null, "Stok Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    formValid=false;
                }else if (Integer.valueOf(barang.get("harga_beli").toString())<=0) {
                    JOptionPane.showMessageDialog( null, "Harga Beli Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    formValid=false;
                }else if (Integer.valueOf(barang.get("harga_jual").toString())<=0) {
                    JOptionPane.showMessageDialog( null, "Harga Jual Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    formValid=false;
                }else if (Integer.valueOf(barang.get("id_suplier").toString())<=0) {
                    JOptionPane.showMessageDialog( null, "Suplier Belum Dipilih", "Error", JOptionPane.ERROR_MESSAGE);
                    formValid=false;
                }else if (Integer.valueOf(barang.get("harga_jual").toString())<=Integer.valueOf(barang.get("harga_beli").toString())) {
                    JOptionPane.showMessageDialog( null, "H.Jual Lebih Kecil dari H.Beli", "Error", JOptionPane.ERROR_MESSAGE);
                    formValid=false;
                }else{
                    try {
                        ArrayList arrbarang=new mBarang().getBarang();
                        for (int i = 0; i < arrbarang.size(); i++) {
                            HashMap hmbarang= (HashMap) arrbarang.get(i);
                            if (hmbarang.get("id_barang").toString().equals(barang.get("id_barang").toString())) {
                                update=true;
//                                System.out.println("sama");
                                break;
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("Gagal");
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if (update==true&&formValid==true) {
                    new mBarang().updatetBarang(barang);
                    new mBranchStok().updateStok("77123321", barang.get("id_barang").toString(), Integer.valueOf(barang.get("stok").toString()));
                
                }else if (update==false&&formValid==true) {
                    try {
                        new mBarang().insertBarang(barang);
                        
                        for (int i = 0; i < new mBranch().getBranch().size(); i++) {
                            HashMap tp=(HashMap) new mBranch().getBranch().get(i);
                            if (tp.get("id").toString()!="77123321") {
                                new mBranchStok().declaration(barang.get("id_barang").toString(), tp.get("id").toString());
                            }
                        new mBranchStok().updateStok("77123321", barang.get("id_barang").toString(), Integer.valueOf(barang.get("stok").toString()));    
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    vbarang.setTableBarang(new mBarang().getBarang());
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
        vbarang.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    vbarang.setTableBarang(new mBarang().getBarangbyName(vbarang.getSearchField()));
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        vbarang.btnPesananAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbarang.dispose();
                controllerPesanan();
                
                
            }
        });
        vbarang.btnCabangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbarang.dispose();
                controllerCabang();
            }
        });
        vbarang.btnPenggunaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbarang.dispose();
                controllerPengguna();
                
            }
        });
        vbarang.btnSuplierAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbarang.dispose();
                controllerSuplier();
            }
        });
        vbarang.btnLaporanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbarang.dispose();
                controllerLaporan();
                
            }
        });
        
    }
    public void controllerPesanan(){
        vPesanan vpesan=new vPesanan();
        vpesan.setUserData(nama, userid);
        try {
            Date date = new Date();//Get system time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nowTime = sdf.format(date);
            vpesan.setTableBarang(new mOrder().getOrder(nowTime));
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vpesan.postNotification(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new mNotification().insertNotification(vpesan.getFormNotif());
            }
        });
        vpesan.getTablePesanan().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    vpesan.setTableDetailOrder(new mDetailOrder().getDetailOrder(vpesan.getTablePesanan().getValueAt(vpesan.getTablePesanan().getSelectedRow(), 0).toString()));
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
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
        vpesan.btnApproveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                mOrder order =new mOrder();
                int opsi = JOptionPane.showConfirmDialog(null, "Terima Pesanan ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (opsi == JOptionPane.YES_OPTION){
                    try {
                        for (int i = 0; i < new mDetailOrder().getDetailOrder(vpesan.getTablePesanan().getValueAt(vpesan.getTablePesanan().getSelectedRow(), 0).toString()).size(); i++) {
                            HashMap tp= (HashMap) new mDetailOrder().getDetailOrder(vpesan.getTablePesanan().getValueAt(vpesan.getTablePesanan().getSelectedRow(), 0).toString()).get(i);
                            new mBranchStok().tambahStok(tp.get("id_order").toString(), tp.get("idbarang").toString(), Integer.parseInt(tp.get("jumlah").toString()));
                            new mBranchStok().kurangStok(tp.get("idbarang").toString(), Integer.parseInt(tp.get("jumlah").toString()));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    order.approveOrder(Integer.valueOf(vpesan.getApproveData().get("userID").toString()),Integer.valueOf( vpesan.getApproveData().get("invoice").toString()));
                    try {
                        Date date = new Date();//Get system time
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String nowTime = sdf.format(date);
                        vpesan.setTableBarang(new mOrder().getOrder(nowTime));
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }else
                    JOptionPane.showMessageDialog(null, "Dibatalkan");
            }
                
            
        });
        vpesan.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    vpesan.setTableBarang(new mOrder().getOrder(vpesan.getTanggal()));
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                            }
        });
        vpesan.btnBarangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpesan.dispose();
                controllerBarang();
            }
        });
        vpesan.btnPenggunaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpesan.dispose();
                controllerPengguna();
            }
        });
        vpesan.btnSuplierAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpesan.dispose();
                controllerSuplier();
            }
        });
        vpesan.btnCabangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpesan.dispose();
                controllerCabang();
            }
        });
        vpesan.btnLaporanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpesan.dispose();
                controllerLaporan();
            }
        });
    }
    public void controllerPengguna(){
        vPengguna vpengguna=new vPengguna();
        vpengguna.setUserData(nama, userid);
        try {
            vpengguna.setTablePengguna(new mPengguna().getPengguna());
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vpengguna.postNotification(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new mNotification().insertNotification(vpengguna.getFormNotif());
            }
        });
        vpengguna.getTblPengguna().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                vpengguna.showFormPengguna();
              
                try {
                    for (int i = 0; i < new mBranch().getBranch().size(); i++) {
                        HashMap tmp=(HashMap) new mBranch().getBranch().get(i);
                        vpengguna.getJcbBranch().addItem(tmp.get("nama_cabang"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                vpengguna.getId().setText(vpengguna.getTblPengguna().getValueAt(vpengguna.getTblPengguna().getSelectedRow(), 0).toString()); 
                vpengguna.getNama().setText( vpengguna.getTblPengguna().getValueAt(vpengguna.getTblPengguna().getSelectedRow(), 1).toString()); 
                vpengguna.getAlamat().setText(vpengguna.getTblPengguna().getValueAt(vpengguna.getTblPengguna().getSelectedRow(), 2).toString());
                vpengguna.getNohp().setText( vpengguna.getTblPengguna().getValueAt(vpengguna.getTblPengguna().getSelectedRow(), 3).toString()); 
                vpengguna.getJcbBranch().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            HashMap cbg=(HashMap) new mBranch().getBranchbyName(vpengguna.getJcbBranch().getSelectedItem().toString()).get(0);
                            vpengguna.getidBranch().setText(cbg.get("id").toString());
                        } catch (SQLException ex) {
                            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
//                vpengguna.getidBranch().setText( vpengguna.getTblPengguna().getValueAt(vpengguna.getTblPengguna().getSelectedRow(), 4).toString()); 
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
        vpengguna.getbtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    vpengguna.setTablePengguna(new mPengguna().getPenggunabyName(vpengguna.getjtNama().getText().toString()));
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        vpengguna.getbtnTambah().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpengguna.showFormPengguna();
                try {
                    for (int i = 0; i < new mBranch().getBranch().size(); i++) {
                        HashMap tmp=(HashMap) new mBranch().getBranch().get(i);
                        vpengguna.getJcbBranch().addItem(tmp.get("nama_cabang"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                vpengguna.getJcbBranch().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            HashMap cbg=(HashMap) new mBranch().getBranchbyName(vpengguna.getJcbBranch().getSelectedItem().toString()).get(0);
                            vpengguna.getidBranch().setText(cbg.get("id").toString());
                        } catch (SQLException ex) {
                            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        vpengguna.btnSavePenggunaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              HashMap hm=vpengguna.getFormPengguna();
              boolean update=false;
              boolean exc= true;
                if (hm.get("nama_user").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Nama Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }else if (hm.get("alamat").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Alamat Tidak Boleh Kosng", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }else if (hm.get("ponsel").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Ponsel Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }else if (hm.get("jabatan").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Jabatan Belum Dipilih", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }else if (hm.get("password").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Password Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }
                
                if (exc==true) {
                    
                  try {
                      for (int i = 0; i < new mPengguna().getPengguna().size(); i++) {
                          HashMap pengguna=(HashMap) new mPengguna().getPengguna().get(i);
                          if (pengguna.get("id_user").toString().equals(hm.get("id_user").toString())) {
                              update =true;
                          }
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
                
                
                
                if (exc==true && update==false) {
                  try {
                      new mPengguna().insertPengguna(hm);
                      vpengguna.setTablePengguna(new mPengguna().getPengguna());
                  } catch (SQLException ex) {
                      Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }else if (exc==true && update==true) {
                    new mPengguna().updatePengguna(hm);
                  try {
                      vpengguna.setTablePengguna(new mPengguna().getPengguna());
                  } catch (SQLException ex) {
                      Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
            
              
            }
        });
        
        vpengguna.btnBarangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpengguna.dispose();
                controllerBarang();
            }
        });
        vpengguna.btnCabangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpengguna.dispose();
                controllerCabang();
            }
        });
        vpengguna.btnPesananAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpengguna.dispose();
                controllerPesanan();
            }
        });
        vpengguna.btnSuplierAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpengguna.dispose();
                controllerSuplier();
            }
        });
        vpengguna.btnLaporanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vpengguna.dispose();
                controllerLaporan();
            }
        });
    }
    public void controllerSuplier(){
        vSuplier vSuplier=new vSuplier();
        vSuplier.setUserData(nama, userid);
        try {
            vSuplier.setTableSuplier(new mSuplier().getSuplier());
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vSuplier.postNotification(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new mNotification().insertNotification(vSuplier.getFormNotif());
            }
        });
        vSuplier.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    vSuplier.setTableSuplier(new mSuplier().getSuplierbyName(vSuplier.getJTNamaSuplier()));
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        vSuplier.btnSaveSuplierAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              HashMap hm=vSuplier.getFormSuplier();
              boolean update=false;
              boolean exc= true;
                if (hm.get("nama_suplier").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Nama Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }else if (hm.get("alamat").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Alamat Tidak Boleh Kosng", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }else if (hm.get("ponsel").toString().equals("")) {
                    JOptionPane.showMessageDialog( null, "Ponsel Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    exc= false;
                }
                if (exc==true) {
                    
                  try {
                      for (int i = 0; i < new mSuplier().getSuplier().size(); i++) {
                          HashMap suplier=(HashMap) new mSuplier().getSuplier().get(i);
                          if (suplier.get("id_suplier").toString().equals(hm.get("id_suplier").toString())) {
                              update =true;
                          }
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
                if (exc==true && update==false) {
                  try {
                      new mSuplier().insertSuplier(hm);
                      vSuplier.setTableSuplier(new mSuplier().getSuplier());
                  } catch (SQLException ex) {
                      Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }else if (exc==true && update==true) {
                    new mSuplier().updateSuplier(hm);
                  try {
                      vSuplier.setTableSuplier(new mSuplier().getSuplier());
                  } catch (SQLException ex) {
                      Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
            }
        });
        vSuplier.btnBarangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vSuplier.dispose();
                controllerBarang();
            }
        });
        vSuplier.btnPesananAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vSuplier.dispose();
                controllerPesanan();
            }
        });
        vSuplier.btnCabangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vSuplier.dispose();
                controllerCabang();
            }
        });
        vSuplier.btnPenggunaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vSuplier.dispose();
                controllerPengguna();
            }
        });
        vSuplier.btnLaporanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vSuplier.dispose();
                controllerLaporan();
            }
        });
    }
    public void controllerLaporan(){
        vLaporan vlaporan=new vLaporan();
        vlaporan.setUserData(nama, userid);
        vlaporan.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                System.out.println("OKOK");
                if (vlaporan.getKriteria().toString().equals("Pemasukan")) {
                  System.out.println("Pemasukan");
                    try {
                        vlaporan.setTableLaporanPemasukan(new mOrder().getOrder(vlaporan.getParameter()));
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex);
                    }

                }else if (vlaporan.getKriteria().toString().equals("Sisa Barang")) {
                    System.out.println("Sisa Barang");
                    try {
                        
                        vlaporan.setTableLaporanBarang(new mBarang().getBarang());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex);
                    }
                }
            }
        });
        vlaporan.postNotification(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new mNotification().insertNotification(vlaporan.getFormNotif());
            }
        });
        vlaporan.btnBarangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vlaporan.dispose();
                controllerBarang();
            }
        });
        vlaporan.btnPesananAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vlaporan.dispose();
                controllerPesanan();
            }
        });
        vlaporan.btnCabangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vlaporan.dispose();
                controllerCabang();
            }
        });
        vlaporan.btnSuplierAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vlaporan.dispose();
                controllerSuplier();
            }
        });
        vlaporan.btnPenggunaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vlaporan.dispose();
                controllerPengguna();
            }
        });
    }
    public void controllerCabang(){
        vBranch vbranch=new vBranch();
        vbranch.setUserData(nama, userid);
        try {
            vbranch.setTableCabang(new mBranch().getBranch());
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vbranch.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbranch.showFormCabang();
                try {
                    for (int i = 0; i < new mPengguna().getPengguna().size(); i++) {
                        HashMap hm= (HashMap) new mPengguna().getPengguna().get(i);
                        vbranch.getJcbUsers().addItem(hm.get("nama_user").toString());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                vbranch.getJcbUsers().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        HashMap hm= (HashMap) new mPengguna().getPenggunabyName(vbranch.getJcbUsers().getSelectedItem().toString()).get(0);
                        vbranch.getIdPenanggungJawab().setText(hm.get("id_user").toString());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            }
        });
        
        vbranch.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        vbranch.getTblBranch().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                vbranch.showFormCabang();
                vbranch.getIdCabang().setText(vbranch.getTblBranch().getValueAt(vbranch.getTblBranch().getSelectedRow(), 0).toString());
                vbranch.getNamaCabang().setText(vbranch.getTblBranch().getValueAt(vbranch.getTblBranch().getSelectedRow(), 1).toString());
                vbranch.getAlamat().setText(vbranch.getTblBranch().getValueAt(vbranch.getTblBranch().getSelectedRow(), 2).toString());
                vbranch.getTelepon().setText(vbranch.getTblBranch().getValueAt(vbranch.getTblBranch().getSelectedRow(), 3).toString());
                vbranch.getIdPenanggungJawab().setText(vbranch.getTblBranch().getValueAt(vbranch.getTblBranch().getSelectedRow(), 4).toString());
              try {
                    for (int i = 0; i < new mPengguna().getPengguna().size(); i++) {
                        HashMap hm= (HashMap) new mPengguna().getPengguna().get(i);
                        vbranch.getJcbUsers().addItem(hm.get("nama_user").toString());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                vbranch.getJcbUsers().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        HashMap hm= (HashMap) new mPengguna().getPenggunabyName(vbranch.getJcbUsers().getSelectedItem().toString()).get(0);
                        vbranch.getIdPenanggungJawab().setText(hm.get("id_user").toString());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
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
        vbranch.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    vbranch.setTableCabang(new mBranch().getBranchbyName(vbranch.getJtNama().getText().toString()));
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        vbranch.getBtnSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(vbranch.getForm());
                boolean run=true;
                if (vbranch.getNamaCabang().getText().equals("")) {
                    JOptionPane.showMessageDialog( null, "Nama Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    run=false;
                }else if (vbranch.getAlamat().getText().equals("")) {
                    JOptionPane.showMessageDialog( null, "Alamat Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    run=false;
                }else if (vbranch.getTelepon().getText().equals("")) {
                    JOptionPane.showMessageDialog( null, "Telepon Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    run=false;
                }else if (vbranch.getIdPenanggungJawab().equals("")) {
                    JOptionPane.showMessageDialog( null, "HS Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    run=false;
                }
                if (vbranch.getIdCabang().getText().toString().equals("0") && run==true) {
                    HashMap tmp=vbranch.getForm();
                    Random rand = new Random();
                    int randomNum = rand.nextInt((1000000 - 99999) +1) + 99999;
                    System.out.println(randomNum);
                    String kd="77"+randomNum;
                    tmp.put("id", kd);
                    new mBranch().insertBranch(tmp);

                    try {
                        for (int i = 0; i < new mBarang().getBarang().size(); i++) {
                            HashMap tp=(HashMap) new mBarang().getBarang().get(i);
                            new mBranchStok().declaration(tp.get("id_barang").toString(), kd);
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        vbranch.setTableCabang(new mBranch().getBranch());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if (vbranch.getIdCabang().getText().toString()!="0" && run==true) {
                    HashMap tmp=vbranch.getForm();
//                    tmp.put("id", vbranch.getIdCabang());
                    new mBranch().updateSuplier(tmp);
                    try {
                        vbranch.setTableCabang(new mBranch().getBranch());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        vbranch.btnBarangAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbranch.dispose();
                controllerBarang();
            }
        });
        vbranch.btnPesananAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbranch.dispose();
                controllerPesanan();
            }
        });
        vbranch.btnSuplierAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbranch.dispose();
                controllerSuplier();
            }
        });
        vbranch.btnPenggunaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbranch.dispose();
                controllerPengguna();
            }
        });
        vbranch.btnLaporanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vbranch.dispose();
                controllerLaporan();
            }
        });
    }
    
   
}