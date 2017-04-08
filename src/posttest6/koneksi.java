
package posttest6;
/**
 *
 * @author USER
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class koneksi {
    private static Connection con;
    public static Connection getConnection(){
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pvisual","root","");
            //Merupakan cara untuk melakukan koneksi ke server localhost dengan nama database pvisual dengan user root
              JOptionPane.showMessageDialog(null, "Selamat Koneksinya Telah Berhasil!!"); 
            //Jika berhasil akan muncul message pemberitahuan( JOptionpane ) yang berisikan string 'selamat koneksinya telah berhasil!!'
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Maaf Koneksinya Tidak Berhasil/Gagal! "+e.getMessage());
            //Jika gagal akan muncul message pemberitahuan( JOptionpane ) yang berisikan string 'maaf koneksinya tidak berhasil/gagal -__-!!!!'   
        }
           
      return con;  
    }
}
