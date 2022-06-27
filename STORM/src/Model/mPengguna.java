/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mPengguna extends mySQLConnection{
    public ArrayList getPengguna() throws SQLException{
        ArrayList pengguna=new ArrayList();
        statement= connection.createStatement();
        resultSet=statement.executeQuery("SELECT users.id,users.nama,users.alamat,users.ponsel,branch.nama_cabang,users.jabatan,users.password,users.user_update,users.update_at FROM users,branch WHERE users.workplaces=branch.id;");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_user", resultSet.getString(1));
            hm.put("nama_user", resultSet.getString(2));
            hm.put("alamat", resultSet.getString(3));
            hm.put("ponsel", resultSet.getString(4));
            hm.put("branch", resultSet.getString(5));
            hm.put("jabatan", resultSet.getString(6));
            hm.put("password", resultSet.getString(7));
            hm.put("user_update", resultSet.getString(8));
            hm.put("update_at", resultSet.getString(9));
            pengguna.add(hm);
        } 
        return pengguna;
    }
    public ArrayList getPenggunabyName(String a) throws SQLException{
        ArrayList pengguna=new ArrayList();
        statement= connection.createStatement();
        resultSet=statement.executeQuery("SELECT users.id,users.nama,users.alamat,users.ponsel,branch.nama_cabang,users.jabatan,users.password,users.user_update,users.update_at FROM users,branch WHERE users.workplaces=branch.id AND users.nama LIKE '%"+a+"%'");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_user", resultSet.getString(1));
            hm.put("nama_user", resultSet.getString(2));
            hm.put("alamat", resultSet.getString(3));
            hm.put("ponsel", resultSet.getString(4));
            hm.put("branch", resultSet.getString(5));
            hm.put("jabatan", resultSet.getString(6));
            hm.put("password", resultSet.getString(7));
            hm.put("user_update", resultSet.getString(8));
            hm.put("update_at", resultSet.getString(9));
            pengguna.add(hm);
        } 
        return pengguna;
    }
    public void insertPengguna(HashMap pengguna){
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `users` (`id`, `nama`, `alamat`, `ponsel`, `workplaces`,`jabatan`, `password`, `user_update`) VALUES (?,?, ?, ?, ?, ?, ?, ?);;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf(pengguna.get("id_user").toString()));
            preparedStatement.setString(2,  pengguna.get("nama_user").toString());
            preparedStatement.setString(3, pengguna.get("alamat").toString());
            preparedStatement.setString(4, pengguna.get("ponsel").toString());
            preparedStatement.setString(5, pengguna.get("workplaces").toString());
            preparedStatement.setString(6,  pengguna.get("jabatan").toString());
            preparedStatement.setString(7, pengguna.get("password").toString());
            preparedStatement.setInt(8, Integer.valueOf(pengguna.get("user_update").toString()));
            
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    public void updatePengguna(HashMap pengguna){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `users` SET "
                + "`nama` = ?, "
                + "`alamat` = ?, "
                + "`workplaces` = ?, "
                + "`ponsel` = ?, "
                + "`jabatan` = ?, "
                + "`password` = ?, "
                + "`user_update` = ? "
                + "WHERE `users`.`id` = ?;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setString(1,  pengguna.get("nama_user").toString());
            preparedStatement.setString(2, pengguna.get("alamat").toString());
            preparedStatement.setInt(3, Integer.valueOf(pengguna.get("workplaces").toString()));
            preparedStatement.setString(4, pengguna.get("ponsel").toString());
            preparedStatement.setString(5,  pengguna.get("jabatan").toString());
            preparedStatement.setString(6, pengguna.get("password").toString());
            preparedStatement.setInt(7, Integer.valueOf(pengguna.get("user_update").toString()));
            preparedStatement.setInt(8, Integer.valueOf(pengguna.get("id_user").toString()));
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
}
