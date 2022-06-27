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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mOrder extends mySQLConnection{
    public ArrayList getOrder(String a) throws SQLException{
        ArrayList orders=new ArrayList();
        statement= connection.createStatement();
            resultSet=statement.executeQuery("SELECT orders.id,users.nama,orders.total, orders.tanggal_pesanan, orders.status,"
                    + "orders.user_approve,orders.update_at "
                    + "FROM users,orders WHERE  orders.id_pengguna=users.id   AND orders.tanggal_pesanan='"+a+"';");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_order", resultSet.getString(1));
            hm.put("nama_pembeli", resultSet.getString(2));
            hm.put("total", resultSet.getString(3));
            hm.put("tanggal_pesanan", resultSet.getString(4));
            hm.put("status", resultSet.getString(5));
            hm.put("user_approve", resultSet.getString(6));
            hm.put("update_at", resultSet.getString(7));
            orders.add(hm);
        }
        return orders;
    }
    public boolean approveOrder(Integer userID, Integer idInvoice){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `orders` SET `status` = 'Approve', `user_approve` = ? WHERE `orders`.`id` = ?;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2,idInvoice);
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Diterima");
            return true;
        
        } catch (SQLException ex) {
            Logger.getLogger(mOrder.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog( null, "Gagal Menerima", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
//    public ArrayList getDataPesananbyDate(String date) throws SQLException{
//        statement= connection.createStatement();
//        resultSet=statement.executeQuery("SELECT orders.id,users.nama,orders.total, orders.tanggal_pesanan, orders.status,orders.user_approve "
//                + "FROM users,orders "
//                + "WHERE users.id=orders.id_pembeli AND orders.tanggal_pesanan = '"+date+"'");
//        
//        ArrayList dataPesanan=new ArrayList();
//        while (resultSet.next()) {      
//            HashMap hm=new HashMap();
//            hm.put("id_order", resultSet.getString(1));
//            hm.put("nama_pembeli", resultSet.getString(2));
//            hm.put("total", resultSet.getString(3));
//            hm.put("tanggal_pesanan", resultSet.getString(4));
//            hm.put("status", resultSet.getString(5));
//            hm.put("user_approve", resultSet.getString(6));
//            dataPesanan.add(hm);
//        }
//        return dataPesanan;
//    
//    }
}
