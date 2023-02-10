
package DAO;

import Model.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Home
 */
public class Sach_DAO {
    ArrayList<Sach> arrSach = new ArrayList<>();
    
    public int TongTienPhatTrongThai(int chon) throws Exception{
        Connection con = Connect_SQL.openConnection();
        String sql = "SELECT SUM(TienPhat) AS TienPhat FROM ChiTietPhieuMuon WHERE MONTH(NgayTra) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int TienPhat = rs.getInt("TienPhat");
                return TienPhat;
            }   
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return 0;
    }
    public static void main(String[] args) throws Exception {
        
    }
    public List<Sach> SachDuocMuonTrongThang(int chon) throws Exception{
        Sach sach=null;
        Connection con = Connect_SQL.openConnection();
        String sql = "select * from Sach "
                + "INNER JOIN ChiTietPhieuMuon ON ChiTietPhieuMuon.MaSach = Sach.MaSach "
                + "INNER JOIN PhieuMuon ON ChiTietPhieuMuon.MaPM=PhieuMuon.MaPM "
                + "WHERE MONTH(PhieuMuon.NgayMuon) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String masach = rs.getString(1);
                String tensach =rs.getString(2);
                String madanhmucsach = rs.getString(3);
                String matheloai = rs.getString(4);
                String tacgia = rs.getString(5);
                String nxb=rs.getString(6);
                int namxuatban =rs.getInt("NamXB");
                int soluong =rs.getInt("SoLuong");
                String nd = rs.getString(9);
                sach =  new Sach(masach, tensach, madanhmucsach, matheloai, tacgia, nxb, namxuatban, soluong, nd);
                arrSach.add(sach);
            }
               
        } catch (SQLException e) {
            System.out.println(e);
        }
        return arrSach;
    }
   public int laySoLuongSach(String masach) throws Exception{
        Sach sach = new Sach();
        
        Connection connection= Connect_SQL.openConnection();
        ResultSet rs=null;
         try {
            
            String sql =  "SELECT SoLuong FROM Sach WHERE MaSach = ?";
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, masach);
            rs = st.executeQuery();
            while(rs.next()){
                int soluongsach = rs.getInt(1);
                sach.setSoLuongCon(soluongsach);
            }
        } catch (Exception e) {
            System.out.println("lõi ssssssssssssss");
        }
        finally{
             connection.close();
         }     
        return sach.getSoLuongCon();
    }
    public List<Sach> getDSSach() throws Exception{
        List<Sach> list_Sach = new ArrayList<>();
        
        Connection connection = Connect_SQL.openConnection();
        
        String sql = "SELECT * FROM Sach";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Sach sach = new Sach();
                
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setMaDMSach(rs.getString("MaDanhMucSach"));
                sach.setMaTheLoai(rs.getString("MaTheLoai"));
                sach.setTacGia(rs.getString("TacGia"));
                sach.setNXB(rs.getString("NXB"));
                sach.setNamXuatBan(rs.getInt("NamXB"));
                sach.setSoLuongCon(rs.getInt("SoLuong"));
                sach.setTomTatND(rs.getString("NoiDung"));
               
                
                list_Sach.add(sach);
                
            }
        }catch (SQLException e){
            
        }
        return list_Sach;
    }
    public void addSach(Sach sach) throws Exception{
        Connection connection = Connect_SQL.openConnection();
        String sql = "INSERT INTO Sach VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sach.getMaSach());
            preparedStatement.setString(2, sach.getTenSach()); 
            preparedStatement.setString(3, sach.getMaDMSach());
            preparedStatement.setString(4, sach.getMaTheLoai()); 
            preparedStatement.setString(5, sach.getTacGia());
            preparedStatement.setString(6, sach.getNXB()); 
            preparedStatement.setString(7, String.valueOf(sach.getNamXuatBan()));
            preparedStatement.setString(8, String.valueOf(sach.getSoLuongCon())); 
            preparedStatement.setString(9, sach.getTomTatND()); 
            preparedStatement.executeUpdate();
        }catch(Exception e){
            
        }
    }
    
    public void updateSach(Sach sach) throws Exception{
        Connection connection = Connect_SQL.openConnection();
        String sql = "UPDATE Sach SET TenSach = ?, MaDanhMucSach = ?, MaTheLoai = ?, TacGia = ?, NXB = ?, NamXB = ?, SoLuong = ?, NoiDung = ? WHERE MaSach = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setString(1, sach.getMaSach());
            preparedStatement.setString(1, sach.getTenSach());
            preparedStatement.setString(2, sach.getMaDMSach()); 
            preparedStatement.setString(3, sach.getMaTheLoai());
            preparedStatement.setString(4, sach.getTacGia());
            preparedStatement.setString(5, sach.getNXB()); 
            preparedStatement.setString(6, String.valueOf(sach.getNamXuatBan()));
            preparedStatement.setString(7, String.valueOf(sach.getSoLuongCon())); 
            preparedStatement.setString(8, sach.getTomTatND());
            preparedStatement.setString(9, sach.getMaSach());
            
            preparedStatement.executeUpdate();
        }catch(Exception e){
            
        }
    }
    
     public void deleteSach(String maSach) throws Exception{
        Connection connection = Connect_SQL.openConnection();
        String sql = "DELETE Sach WHERE MaSach LIKE '%" + maSach + "%'";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        }catch(Exception e){
            
        }
    }
     
    public List<Sach> setListSach(ResultSet rs) throws SQLException {
        List<Sach> s = new ArrayList<>();
        while(rs.next()){
                Sach sach = new Sach();
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setMaDMSach(rs.getString("MaDanhMucSach"));
                sach.setMaTheLoai(rs.getString("MaTheLoai"));
                sach.setTacGia(rs.getString("TacGia"));
                sach.setNXB(rs.getString("NXB"));
                sach.setNamXuatBan(rs.getInt("NamXB"));
                sach.setSoLuongCon(rs.getInt("SoLuong"));
                sach.setTomTatND(rs.getString("NoiDung"));
                s.add(sach);
            }
        return s;
    }
     
//     public List<Sach> getAllSachByOne(String searching, String type) throws Exception {
//        Connection con = Connect_SQL.openConnection();
//        List<Sach> s = new ArrayList<>();
//        String sql = "select * from Sach, DanhMucSach where tenDMSach = ? and Sach.maDMSach = DanhMucSach.maDMSach";
//        if(type.equalsIgnoreCase("theloai")) 
//            sql = "select * from Sach, TheLoai where tenTheLoai = ?"
//              + " and Sach.maTheLoai = TheLoai.maTheLoai";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, searching);
//            ResultSet rs = ps.executeQuery();
//            s = setListSach(rs);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return s;
//    }
//     
//     public List<Sach> getAllSachByBoth(String danhmuc, String theloai) {
//        Connection con = KetNoiSQL.getConnection();
//        String sql = "select * from Sach, DanhMucSach, TheLoai where tenDMSach = ? and tenTheLoai = ?"
//              + "and Sach.maDMSach = DanhMucSach.maDMSach and Sach.maTheLoai = TheLoai.maTheLoai";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, danhmuc);
//            ps.setString(2, theloai);
//            ResultSet rs = ps.executeQuery();
//            return setListSach(rs);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
     
    public Sach getSachByMS(String masach) throws Exception {
        Connection con = Connect_SQL.openConnection();
        String sql = "select * from Sach where MaSach = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, masach);
            ResultSet rs = ps.executeQuery();
            Sach sach = new Sach();
            rs.next();
           sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setMaDMSach(rs.getString("MaDanhMucSach"));
                sach.setMaTheLoai(rs.getString("MaTheLoai"));
                sach.setTacGia(rs.getString("TacGia"));
                sach.setNXB(rs.getString("NXB"));
                sach.setNamXuatBan(rs.getInt("NamXB"));
                sach.setSoLuongCon(rs.getInt("SoLuong"));
                sach.setTomTatND(rs.getString("NoiDung"));
            return sach;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
//    public String getDMSach(String maDM) {
//        Connection con = KetNoiSQL.getConnection();
//        String sql = "select tenDMSach from DanhMucSach where maDMSach = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, maDM);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            return rs.getString("tenDMSach");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return "";
//    }
//    
//    public String getDMTheLoai(String maTheLoai) {
//        Connection con = KetNoiSQL.getConnection();
//        String sql = "select tenTheLoai from TheLoai where maTheLoai = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, maTheLoai);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            return rs.getString("tenTheLoai");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return "";
//    }
}
