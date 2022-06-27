/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mPenjualan extends mySQLConnection{
    public void addpenjualan(HashMap a){

        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `seles` (`id`, `id_pengguna`, `tanggal_seles`, `jumlah`, `keuntungan`) VALUES (?, ?,?, ?, ?)";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf(a.get("id").toString()));
            preparedStatement.setInt(2,  Integer.valueOf(a.get("id_pengguna").toString()));
            preparedStatement.setString(3,  a.get("tanggal_seles").toString());
            preparedStatement.setInt(4,  Integer.valueOf(a.get("jumlah").toString()));
            preparedStatement.setInt(5,  Integer.valueOf(a.get("keuntungan").toString()));
            preparedStatement.execute();
//            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");
            
        } catch (Exception e) {
//            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void adddpenjualan(ArrayList a) throws SQLException{
        for (int i = 0; i < a.size(); i++) {
            HashMap hm=(HashMap) a.get(i);
            PreparedStatement preparedStatement=null;
            String query="INSERT INTO `seles_detail` (`id`, `id_seles`, `id_barang`, `harga`, `jumlah`) VALUES (NULL, ?, ?, ?,?);";

                preparedStatement =this.connection.prepareStatement(query);

                preparedStatement.setInt(1, Integer.valueOf(hm.get("id_seles").toString()));
                preparedStatement.setInt(2, Integer.valueOf(hm.get("id_barang").toString()));
                preparedStatement.setInt(3, Integer.valueOf(hm.get("harga").toString()));
                preparedStatement.setInt(4, Integer.valueOf(hm.get("jumlah").toString()));
                preparedStatement.execute();

        }
    }
}
