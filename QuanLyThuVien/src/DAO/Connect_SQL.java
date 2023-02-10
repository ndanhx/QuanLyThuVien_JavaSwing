
package DAO;

import java.sql.*;

/**
 *
 * @author Home
 */
public class Connect_SQL {
    
    public static Connection openConnection() throws Exception{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=QuanLyThuVien;"
                    + "encrypt=true;trustservercertificate=true; sslProtocol=TLSv1.2"; // source connect db
            String userDB = "sa"; //username database
            String passwordDB = "123456"; //pass
            Connection conn = DriverManager.getConnection(url,userDB,passwordDB);
            return conn ;
}
    
    
    
    
//    public Connection getConnection(){
//        Connection conn = null;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://localhost;databaseName=DBTest;encrypt=true;trustservercertificate=true; sslProtocol=TLSv1.2";
//            String user = "sa";
//            String password = "123456";
//            conn = DriverManager.getConnection(url,user,password);
//            if(conn !=null){
//                JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu thành công!");
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        return conn;
        

    PreparedStatement prepareStatement(String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
