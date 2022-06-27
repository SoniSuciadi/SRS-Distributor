package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mBarang extends mySQLConnection{
    
    public ArrayList getBarang() throws SQLException{
        ArrayList barang=new ArrayList();
        statement= connection.createStatement();
        resultSet=statement.executeQuery("SELECT barang.id,barang.nama,branch_stock.jumlah,barang.harga_beli,barang.harga_jual, supliers.nama,barang.status,barang.image,users.nama,barang.update_at,barang.qr_code "
                + "FROM barang,supliers,users,branch_stock "
                + "WHERE barang.id_suplier=supliers.id AND barang.user_update=users.id AND barang.id=branch_stock.id_barang AND branch_stock.id_branch=77123321;");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_barang", resultSet.getInt(1));
            hm.put("nama_barang", resultSet.getString(2));
            hm.put("stok", resultSet.getString(3));
            hm.put("harga_beli", resultSet.getString(4));
            hm.put("harga_jual", resultSet.getString(5));
            hm.put("nama_suplier", resultSet.getString(6));
            hm.put("status", resultSet.getString(7));
            hm.put("image", resultSet.getBytes(8));
            hm.put("user_update", resultSet.getString(9));
            hm.put("update_at", resultSet.getString(10));
            hm.put("qr_code", resultSet.getBytes(11));
            barang.add(hm);
        }
        return barang;
       
    }
    public ArrayList getBarangbyName(String a) throws SQLException{
        ArrayList barang=new ArrayList();
        statement= connection.createStatement();
        resultSet=statement.executeQuery("SELECT barang.id,barang.nama,branch_stock.jumlah,barang.harga_beli,barang.harga_jual, supliers.nama,barang.status,barang.image,users.nama,barang.update_at,barang.qr_code "
                + "FROM barang,supliers,users,branch_stock "
                + "WHERE barang.id_suplier=supliers.id AND barang.user_update=users.id AND barang.id=branch_stock.id_barang AND branch_stock.id_branch=77123321 AND barang.nama LIKE '%"+a+"%'");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_barang", resultSet.getString(1));
            hm.put("nama_barang", resultSet.getString(2));
            hm.put("stok", resultSet.getString(3));
            hm.put("harga_beli", resultSet.getString(4));
            hm.put("harga_jual", resultSet.getString(5));
            hm.put("nama_suplier", resultSet.getString(6));
            hm.put("status", resultSet.getString(7));
            hm.put("image", resultSet.getBytes(8));
            hm.put("user_update", resultSet.getString(9));
            hm.put("update_at", resultSet.getString(10));
            hm.put("qr_code", resultSet.getBytes(11));
            barang.add(hm);
        }
        return barang;
    }
    public ArrayList getBarangbyID(Integer a) throws SQLException{
        ArrayList barang=new ArrayList();
        statement= connection.createStatement();
        resultSet=statement.executeQuery("SELECT barang.id,barang.nama,branch_stock.jumlah,barang.harga_beli,barang.harga_jual, supliers.nama,barang.status,barang.image,users.nama,barang.update_at,barang.qr_code "
                + "FROM barang,supliers,users,branch_stock "
                + "WHERE barang.id_suplier=supliers.id AND barang.user_update=users.id AND barang.id=branch_stock.id_barang AND branch_stock.id_branch=77123321 AND barang.id="+a);
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_barang", resultSet.getString(1));
            hm.put("nama_barang", resultSet.getString(2));
            hm.put("stok", resultSet.getString(3));
            hm.put("harga_beli", resultSet.getString(4));
            hm.put("harga_jual", resultSet.getString(5));
            hm.put("nama_suplier", resultSet.getString(6));
            hm.put("status", resultSet.getString(7));
            hm.put("image", resultSet.getBytes(8));
            hm.put("user_update", resultSet.getString(9));
            hm.put("update_at", resultSet.getString(10));
            hm.put("qr_code", resultSet.getBytes(11));
            barang.add(hm);
        }
        return barang;
    }
    public void editBarang(){
        
    }
    public void insertBarang(HashMap barang){

        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `barang` (`id`, `nama`, `harga_beli`, `harga_jual`, `image`, `status`, `id_suplier`, `user_update`,`qr_code`) \n" +
                    "VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(1, (int) barang.get("id_barang"));
            preparedStatement.setString(2,  barang.get("nama_barang").toString());
            preparedStatement.setInt(3, (int) barang.get("harga_beli"));
            preparedStatement.setInt(4, (int) barang.get("harga_jual"));
            preparedStatement.setBlob(5, (InputStream) barang.get("image"));
            preparedStatement.setString(6, barang.get("status").toString());
            preparedStatement.setInt(7, (int) barang.get("id_suplier"));
            preparedStatement.setInt(8, (int) barang.get("user_update"));
            preparedStatement.setBlob(9, (InputStream) barang.get("qr_code"));
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Disimpan");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    public void updatetBarang(HashMap barang){
        PreparedStatement preparedStatement=null;
        String query="UPDATE `barang` SET "
                + "`nama` = ?, "
                + "`harga_beli` = ?, "
                + "`harga_jual` = ?, "
                + "`image` = ?, "
                + "`status` = ?, "
                + "`user_update` = ? ,"
                + "`id_suplier`= ? "
                + "WHERE `barang`.`id` = ?;";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setInt(8, (int) barang.get("id_barang"));
            preparedStatement.setString(1,  barang.get("nama_barang").toString());
            preparedStatement.setInt(2, (int) barang.get("harga_beli"));
            preparedStatement.setInt(3, (int) barang.get("harga_jual"));
            preparedStatement.setBlob(4, (InputStream) barang.get("image"));
            preparedStatement.setString(5, barang.get("status").toString());
            preparedStatement.setInt(7, (int) barang.get("id_suplier"));
            preparedStatement.setInt(6, (int) barang.get("user_update"));
            
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Data Berhasil Diupdate");

          
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Penyimpanan Gagal", "Error", JOptionPane.ERROR_MESSAGE);
           
        }
    }
    
}