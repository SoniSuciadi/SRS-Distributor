/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mBranchStok extends mySQLConnection{
    public void declaration(String barang, String branch){
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `branch_stock` ( `id_branch`, `id_barang`, `jumlah`) VALUES ( ?, ?, '0');";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf(branch));
            preparedStatement.setInt(2,  Integer.valueOf(barang));
            
            preparedStatement.execute();
//            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
        
    }
    public void tambahStok(String order, String barang, Integer jumlah){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `branch_stock` SET `jumlah` =`branch_stock`.`jumlah`+?  WHERE `branch_stock`.`id_branch` = (SELECT branch.id FROM branch, orders,users "
                + "WHERE orders.id_pengguna=users.id AND users.workplaces=branch.id AND orders.id= ?)AND `branch_stock`.`id_barang`= ?;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setString(2,  order);
            preparedStatement.setString(3, barang);
            preparedStatement.setInt(1, jumlah);
       
            
            preparedStatement.execute();
//            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    public void kurangStok( String barang, Integer jumlah){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `branch_stock` SET `jumlah` =`branch_stock`.`jumlah`-?  WHERE `branch_stock`.`id_branch` = 77123321 AND `branch_stock`.`id_barang`= ?;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setString(2, barang);
            preparedStatement.setInt(1, jumlah);
       
            
            preparedStatement.execute();
//            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    public void updateStok(String cabang, String barang, Integer jumlah){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `branch_stock` SET `jumlah` = ?"
                + " WHERE `branch_stock`.`id_branch` = ? AND `branch_stock`.`id_barang`= ? ;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setString(2,  cabang);
            preparedStatement.setString(3, barang);
            preparedStatement.setInt(1, jumlah);
       
            
            preparedStatement.execute();
//            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
}
