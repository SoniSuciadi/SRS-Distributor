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
import Model.mOrder;
import Model.mPengguna;
import Model.mPenjualan;
import Model.mSuplier;
import View.vBackground;
import View.vBarang;
import View.vLaporan;
import View.vLogin;
import View.vPengguna;
import View.vPesanan;
import View.vSuplier;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author sonys
 */
public class Home {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MainController controller=new MainController();
//
//        ArrayList stasiuncode=new ArrayList();
//        stasiuncode.add("96001");
//        stasiuncode.add("96002");
//        stasiuncode.add("96003");
//        stasiuncode.add("96004");
//        stasiuncode.add("96005");
//        stasiuncode.add("96006");
//        stasiuncode.add("96007");
//        stasiuncode.add("96008");
//        stasiuncode.add("96009");
//        stasiuncode.add("96010");
//        stasiuncode.add("96011");
//        stasiuncode.add("96012");
//        stasiuncode.add("96013");
//        stasiuncode.add("96014");
//        stasiuncode.add("96015");
//        stasiuncode.add("96016");
//        stasiuncode.add("96017");
//        stasiuncode.add("96018");
//        stasiuncode.add("96019");
//        stasiuncode.add("96020");
//        
//        ArrayList nama=new ArrayList();
//        nama.add("Stasiun Meteorologi Maimun Saleh");
//        nama.add("Stasiun Meteorologi Malikussaleh");
//        nama.add("Stasiun Meteorologi Sultan Iskandar Muda");
//        nama.add("Stasiun Meteorologi Cut Nyak Dhien Nagan Raya");
//        nama.add("Stasiun Klimatologi Aceh Besar");
//        nama.add("Stasiun Klimatologi Deli Serdang");
//        nama.add("Stasiun Meteorologi Maritim Belawan");
//        nama.add("Stasiun Geofisika Deli Serdang");
//        nama.add("Balai Besar Meteorologi Klimatologi dan Geofisika Wilayah I");
//        nama.add("Stasiun Meteorologi Silangit");
//        nama.add("Stasiun Meteorologi Aek Godang");
//        nama.add("Stasiun Meteorologi FL Tobing");
//        nama.add("Stasiun Geofisika Gunungsitol");
//        nama.add("Stasiun Meteorologi Binaka");
//        nama.add("Stasiun Meteorologi Hang Nadim");
//        nama.add("Stasiun Klimatologi Kampar");
//        nama.add("Stasiun Meteorologi Raja Haji Abdullah");
//        nama.add("Stasiun Meteorologi Sultan Syarif Kasim II");
//        nama.add("Stasiun Meteorologi Ranai");
//        nama.add("Stasiun Meteorologi Maritim Teluk Bayur");
//        mBranch b=new mBranch();
//        HashMap al;
//        
//        for (int a = 0; a < nama.size(); a++) {
//           al =new HashMap();
//        
//            for (int i = 2016  ; i < 2021; i++) {
//                
//                for (int o = 1; o < 13; o++) {
//                    al.put("t", String.valueOf(i)+"-"+String.valueOf(o));
//                    for (int j = 1; j < 30; j++) {
//                        al.put("k", stasiuncode.get(a));
//                        al.put("n", nama.get(a));
//                        Random rand = new Random();
//                        String kd = String.valueOf(rand.nextInt((1 - 0) +1) + 0);
//                        al.put(String.valueOf(j), kd);
//                    }
//                    b.insertdw(al);
//                }
//            }
//            
//            
//        }

//        
//        ArrayList pengguna=new ArrayList();
//        pengguna.add("2");
//        pengguna.add("4");
//        pengguna.add("995094");
//        pengguna.add("991734");
//        pengguna.add("998221");
//        pengguna.add("999352");
//        for (int m = 0; m < 6; m++) {
//            
//        
//        for (int o = 0; o < pengguna.size(); o++) {
//            
//        
//        for (int i = 2022; i < 2023; i++) {
//            for (int j = 1; j < 2; j++) {
//                for (int k = 1; k < 29; k++) {
//                    Random rand = new Random();
//                    
//                    String tgl=String.valueOf(i)+"-"+String.valueOf(j)+"-"+k;
//                    rand = new Random();
//                    int kd = rand.nextInt((10000 - 999) +1) + 999;
//                    String kdpesan=String.valueOf(k)+String.valueOf(j)+kd;
//                    int total=0;
//                    int keuntungan=0;
//                    ArrayList pesan=new ArrayList();
//                    for (int l = 0; l < 6; l++) {
//                        
//                        rand = new Random();
//                        int jumlah = rand.nextInt((5 - 1) +1) + 1;
//                        ArrayList barang=new mBarang().getBarang();
//                        int br = rand.nextInt(((barang.size()-1) - 0) +1) + 0;
//                        HashMap tpm= (HashMap) barang.get(br);
////                        System.out.println(br);
//                        HashMap hashMap=new HashMap();
//                        hashMap.put("id_seles", kdpesan);
//                        hashMap.put("id_barang",tpm.get("id_barang"));
//                        hashMap.put("harga", tpm.get("harga_jual"));
//                        hashMap.put("jumlah",jumlah );
//                        hashMap.put("total",total );
//                        pesan.add(hashMap);
//                        total+=Integer.valueOf(tpm.get("harga_jual").toString())*jumlah;
//                        keuntungan+=(Integer.valueOf(tpm.get("harga_jual").toString())-Integer.valueOf(tpm.get("harga_beli").toString()))*jumlah;
//                        
//                        
//                        
//                    }
//                    
//                    HashMap fin=new HashMap();
//                    fin.put("id", kdpesan);
//                    fin.put("id_pengguna", pengguna.get(o));
//                    fin.put("tanggal_seles", tgl);
//                    fin.put("jumlah", total);
//                    fin.put("keuntungan", keuntungan);
//                    System.out.println(fin);
//                    new mPenjualan().addpenjualan(fin);
//                    new mPenjualan().adddpenjualan(pesan);
//                    
//                }
//            }
//        }
//        }
//        }
//System.out.println(Calendar.YEAR);
//    mBarang barang=new mBarang();
//    Random rand = new Random();
//    ArrayList tanggal=new ArrayList();
//    tanggal.add("");
//    int jumlah = rand.nextInt((5 - 4) +1) + 4;
//            mOrder order=new mOrder();
//            order.approveOrder(2, 0);
//vBarang b=new vBarang();
//vBackground b=new vBackground();
//vBarang b=new vBarang();
//vPesanan pesanan=new vPesanan();
//vPengguna  pengguna =new vPengguna();
//vSuplier suplier=new vSuplier();
//vLaporan laporan=new vLaporan();
//mBranch b=new mBranch();
//        mBarang  barang=new mBarang();
//        System.out.println(b.getBranch());
//        HashMap hm= (HashMap) barang.getBarang().get(0);
//        coba cba=new coba(hm);
//mDetailOrder detailOrder=new mDetailOrder();
//        mOrder order=new mOrder();
//        mPengguna pengguna=new mPengguna();
//mSuplier suplier=new mSuplier();
//        mOrder order=new mOrder();
//mBranchStok branchSto=new mBranchStok();
//branchSto.tambahStok("77416975", "886921", 100);
//        System.out.println();
        
//        barang.insertBarang(hm);
//        HashMap hashMap=new HashMap();
//        hashMap.put("id", 1);
//        hashMap.put("nama_cabang", "anjay");
//        hashMap.put("alamat", "aa");
//        hashMap.put("telepon", "1");
//        hashMap.put("branch_head", 1);
//        hashMap.put("user_update", 1);
//        new mBranch().updateSuplier(hashMap);

    }
   
    
//     public Integer randomnumber(){
//            Random rand = new Random();
//            int jumlah = rand.nextInt((5 - 4) +1) + 4;
//            return jumlah;
//        }
}
