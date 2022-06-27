/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sonys
 */
public class mDetailOrder extends mySQLConnection{
    public ArrayList getDetailOrder(String a) throws SQLException{
        ArrayList detailOrder=new ArrayList();
        statement= connection.createStatement();
        resultSet=statement.executeQuery("SELECT order_details.id_order,barang.nama,barang.harga_jual,order_details.jumlah,\n" +
                                    "order_details.jumlah * barang.harga_jual AS subtotal, barang.id \n" +
                                    "FROM order_details,barang WHERE order_details.id_barang=barang.id AND order_details.id_order=\""+a+"\";");
        while (resultSet.next()) {            
            HashMap hm=new HashMap();
            hm.put("id_order", resultSet.getString(1));
            hm.put("nama_barang", resultSet.getString(2));
            hm.put("harga_jual", resultSet.getString(3));
            hm.put("jumlah", resultSet.getString(4));
            hm.put("subtotal", resultSet.getString(5));
            hm.put("idbarang", resultSet.getString(6));
            detailOrder.add(hm);
        }
        return detailOrder;

    }
}
