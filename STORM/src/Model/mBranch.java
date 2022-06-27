/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
public class mBranch extends mySQLConnection{
    public ArrayList getBranch() throws SQLException{
        ArrayList Branch=new ArrayList();
        statement= connection.createStatement();
        resultSet=statement.executeQuery("SELECT * FROM `branch`");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id", resultSet.getString(1));
            hm.put("nama_cabang", resultSet.getString(2));
            hm.put("alamat", resultSet.getString(3));
            hm.put("telepon", resultSet.getString(4));
            hm.put("branch_head", resultSet.getString(5));
            hm.put("user_update", resultSet.getString(6));
            hm.put("update_at", resultSet.getString(7));
            Branch.add(hm);
        }
        return Branch;
    }
    public ArrayList getBranchbyName(String a) throws SQLException{
        ArrayList Branch=new ArrayList();
        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM `branch` WHERE nama_cabang LIKE '%"+a+"%'");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id", resultSet.getString(1));
            hm.put("nama_cabang", resultSet.getString(2));
            hm.put("alamat", resultSet.getString(3));
            hm.put("telepon", resultSet.getString(4));
            hm.put("branch_head", resultSet.getString(5));
            hm.put("user_update", resultSet.getString(6));
            hm.put("update_at", resultSet.getString(7));
            Branch.add(hm);
        }
        return Branch;
    }
    public void insertBranch(HashMap branch){
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `branch` (`id`, `nama_cabang`, `alamat`, `telepon`, `branch_head`, `user_update`) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf( branch.get("id").toString()));
            preparedStatement.setString(2,  branch.get("nama_cabang").toString());
            preparedStatement.setString(3, branch.get("alamat").toString());
            preparedStatement.setString(4,  branch.get("telepon").toString());
            preparedStatement.setInt(5,  Integer.valueOf(branch.get("branch_head").toString()));
            preparedStatement.setInt(6, Integer.valueOf(branch.get("user_update").toString()));
            
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    public void updateSuplier(HashMap branch){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `branch` SET "
                + "`nama_cabang` = ?, "
                + "`alamat` = ?, "
                + "`telepon` = ?, "
                + "`branch_head` = ?, "
                + "`user_update` = ? "
                + "WHERE `branch`.`id` = ?;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(6, Integer.valueOf( branch.get("id").toString()));
            preparedStatement.setString(1,  branch.get("nama_cabang").toString());
            preparedStatement.setString(2, branch.get("alamat").toString());
            preparedStatement.setString(3,  branch.get("telepon").toString());
            preparedStatement.setInt(4,  Integer.valueOf(branch.get("branch_head").toString()));
            preparedStatement.setInt(5, Integer.valueOf(branch.get("user_update").toString()));
            
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    public void insertdw(HashMap branch){
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `cuaca` (`Kode Stasiun`, `Nama Stasiun`, `Tahun Bulan`, `01`, `02`, `03`, `04`, `05`, `06`, `07`, `08`, `09`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            
            preparedStatement.setString(1,  branch.get("k").toString());
            preparedStatement.setString(2,  branch.get("n").toString());
            preparedStatement.setString(3,  branch.get("t").toString());
            preparedStatement.setString(4,  branch.get("1").toString());
            preparedStatement.setString(5,  branch.get("2").toString());
            preparedStatement.setString(6,  branch.get("3").toString());
            preparedStatement.setString(7,  branch.get("4").toString());
            preparedStatement.setString(8,  branch.get("5").toString());
            preparedStatement.setString(9,  branch.get("6").toString());
            preparedStatement.setString(10,  branch.get("7").toString());
            preparedStatement.setString(11,  branch.get("8").toString());
            preparedStatement.setString(12,  branch.get("9").toString());
            preparedStatement.setString(13,  branch.get("10").toString());
            preparedStatement.setString(14,  branch.get("11").toString());
            preparedStatement.setString(15,  branch.get("12").toString());
            preparedStatement.setString(16,  branch.get("13").toString());
            preparedStatement.setString(17,  branch.get("14").toString());
            preparedStatement.setString(18,  branch.get("15").toString());
            preparedStatement.setString(19,  branch.get("16").toString());
            preparedStatement.setString(20,  branch.get("17").toString());
            preparedStatement.setString(21,  branch.get("18").toString());
            preparedStatement.setString(22,  branch.get("19").toString());
            preparedStatement.setString(23,  branch.get("20").toString());
            preparedStatement.setString(24,  branch.get("21").toString());
            preparedStatement.setString(25,  branch.get("22").toString());
            preparedStatement.setString(26,  branch.get("23").toString());
            preparedStatement.setString(27,  branch.get("24").toString());
            preparedStatement.setString(28,  branch.get("25").toString());
            preparedStatement.setString(29,  branch.get("26").toString());
            preparedStatement.setString(30,  branch.get("27").toString());
            preparedStatement.setString(31,  branch.get("28").toString());
            preparedStatement.setString(32,  branch.get("29").toString());
            
            preparedStatement.execute();
//            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
}
