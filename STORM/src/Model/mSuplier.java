/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mSuplier extends mySQLConnection{
     public ArrayList getSuplier() throws SQLException{
        ArrayList suplier=new ArrayList();
        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT supliers.id,supliers.nama,supliers.alamat,supliers.ponsel,users.nama,supliers.update_at "
                + "FROM users,supliers WHERE users.id=supliers.user_update");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_suplier", resultSet.getString(1));
            hm.put("nama_suplier", resultSet.getString(2));
            hm.put("alamat", resultSet.getString(3));
            hm.put("ponsel", resultSet.getString(4));
            hm.put("user_update", resultSet.getString(5));
            hm.put("update_at", resultSet.getString(6));
            suplier.add(hm);
        }
        return suplier;
    }
    public ArrayList getSuplierbyName(String a) throws SQLException{
        ArrayList suplier=new ArrayList();
        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT supliers.id,supliers.nama,supliers.alamat,supliers.ponsel,users.nama,supliers.update_at "
                + "FROM users,supliers WHERE users.id=supliers.user_update AND supliers.nama LIKE '%"+a+"%'");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_suplier", resultSet.getString(1));
            hm.put("nama_suplier", resultSet.getString(2));
            hm.put("alamat", resultSet.getString(3));
            hm.put("ponsel", resultSet.getString(4));
            hm.put("user_update", resultSet.getString(5));
            hm.put("update_at", resultSet.getString(6));
            suplier.add(hm);
        }
        return suplier;
    }
    public void insertSuplier(HashMap suplier){
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `supliers` (`id`, `nama`, `alamat`, `ponsel`, `user_update`) VALUES (?, ?, ?, ?, ?);";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf( suplier.get("id_suplier").toString()));
            preparedStatement.setString(2,  suplier.get("nama_suplier").toString());
            preparedStatement.setString(3, suplier.get("alamat").toString());
            preparedStatement.setString(4,  suplier.get("ponsel").toString());
            preparedStatement.setInt(5, Integer.valueOf(suplier.get("user_update").toString()));
            
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    public void updateSuplier(HashMap suplier){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `supliers` SET "
                + "`nama` = ?, "
                + "`alamat` = ?, "
                + "`ponsel` = ?, "
                + "`user_update` = ? "
                + "WHERE `supliers`.`id` = ?;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(5, Integer.valueOf( suplier.get("id_suplier").toString()));
            preparedStatement.setString(1,  suplier.get("nama_suplier").toString());
            preparedStatement.setString(2, suplier.get("alamat").toString());
            preparedStatement.setString(3,  suplier.get("ponsel").toString());
            preparedStatement.setInt(4, Integer.valueOf(suplier.get("user_update").toString()));
            
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
}
