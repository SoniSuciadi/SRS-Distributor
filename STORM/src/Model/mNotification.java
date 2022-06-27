package Model;

import java.sql.PreparedStatement;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mNotification extends mySQLConnection{
    public void insertNotification(HashMap pesan){
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO `notification` (`judul`, `pesan`) VALUES ( ?, ?);";
        try {
            preparedStatement =this.connection.prepareStatement(query);
            preparedStatement.setString(1,pesan.get("judul").toString());
            preparedStatement.setString(2,pesan.get("deskripsi").toString());
            preparedStatement.execute();
            JOptionPane.showMessageDialog( null, "Berhasil Dibagikan");

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog( null, "Gagal Membagikan", "Error", JOptionPane.ERROR_MESSAGE);

           
        }
    }
}
