package DAO;

import Model.QuanLy;
import java.sql.*;

/**
 *
 * @author Home
 */
public class QuanLy_DAO{
    public QuanLy checkLogin (String tenDangNhap, String matKhau)
            throws Exception{
        String sql = "SELECT [MaQL], [PassWordQL] FROM [libery].[dbo].[QuanLy] WHERE [MaQL] = ? and [PassWordQL] = ?";
        try(
            Connection conn = Connect_SQL.openConnection()   ;
            PreparedStatement pstmt = conn.prepareStatement(sql);
           
        )
            {
                
                pstmt.setString(1,tenDangNhap);
                pstmt.setString(2,matKhau);
                try( ResultSet rs =  pstmt.executeQuery();){
                    if(rs.next()){
                        QuanLy us = new QuanLy();
                        us.setMaQuanLy(tenDangNhap);
                        return us;
                    }
                    
                }
                
            }
       
       return null;
             
    }
}
