
package DAO;

import Model.PhieuMuon;
import java.sql.*;
/**
 *
 * @author Home
 */
public class PhieuMuon_DAO {
    public String layNgayMuon(String maPM) throws Exception{
        PhieuMuon pm = new PhieuMuon();
        
        Connection connection= Connect_SQL.openConnection();
        ResultSet rs=null;
         try {
            
            String sql =  "SELECT NgayMuon FROM PhieuMuon WHERE MaPM = ?";
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, maPM);
            rs = st.executeQuery();
            while(rs.next()){
                String ngaymuon = rs.getString("NgayMuon");
                pm.setNgayMuon(ngaymuon);
            }
        } catch (Exception e) {
            System.out.println("lõi ssssssssssssss");
        }
        finally{
             connection.close();
         }     
        return pm.getNgayMuon();
    }
    public void addPhieuMuon(PhieuMuon phieuMuon) throws Exception {
        Connection connection = Connect_SQL.openConnection();
            String sql = "INSERT INTO PhieuMuon VALUES (?,?,?,?,?,?)";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setString(1, phieuMuon.getMaPM());
                preparedStatement.setString(2, phieuMuon.getNgayMuon());
                preparedStatement.setString(3, phieuMuon.getMaDG_PM());
                preparedStatement.setString(4, phieuMuon.getMaQuanLy());
                preparedStatement.setString(5, phieuMuon.getGhiChu());
                preparedStatement.setString(6, phieuMuon.getTrangThai());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public String getMaPMTop1() throws Exception {
        Connection con = Connect_SQL.openConnection();
        String mapm;
        String sql = "SELECT TOP 1 maPM FROM PhieuMuon ORDER BY maPM DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
             mapm = rs.getString("MaPM");
            
            return mapm;
            
        } catch (SQLException e) {
            System.out.println(e);
            return "PM001";
            
        }
      
    } 
  
    
}
