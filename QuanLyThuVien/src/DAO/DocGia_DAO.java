
package DAO;


import Model.DocGia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class DocGia_DAO {
    ArrayList<DocGia> arrDocGia = new ArrayList<>();//tạo arraylist chứ thông tin độc giả
    
    public static ArrayList<DocGia> arrDocGia1 = new ArrayList<>();  //tạo arraylist chứ thông tin độc giả
    
    // check account có hay không hỗ trợ login
    //trên mạng hơi khó hiểu :(( 
    public DocGia checkLogin (String tenDangNhap, String matKhau)
            throws Exception{
        String sql = "SELECT MaDG, PassWord FROM DocGia WHERE MaDG = ? and PassWord = ?";
        try(
            Connection conn = Connect_SQL.openConnection()   ;
            PreparedStatement pstmt = conn.prepareStatement(sql);
           
        )
            {
                pstmt.setString(1,tenDangNhap);
                pstmt.setString(2,matKhau);
                try( ResultSet rs =  pstmt.executeQuery();)
                {
                    if(rs.next()){
                        DocGia us = new DocGia();
                        us.setMaDG(tenDangNhap);
                        return us;
                    }
                }
            }
       return null;      
    }
    //kiểm tra tài khoản có bị khóa hay không?
    public DocGia check_KhoaTaiKhoan(String tenDangNhapcheck) throws Exception{
        DocGia docgia = new DocGia();
        Connection connection= Connect_SQL.openConnection();
        ResultSet rs=null;
         try {
            String sql =  "SELECT Ten,SoLuongMuon,TrangThaiTK FROM DocGia WHERE MaDG = ?";
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, tenDangNhapcheck);
            rs = st.executeQuery();
            while(rs.next()){
                String tendg = rs.getString(1);
                int soluongmuon = rs.getInt("SoLuongMuon");
                String trangthaitk = rs.getString(3);
                docgia.setMaDG(tenDangNhapcheck);
                docgia.setTenDG(tendg);
                docgia.setSoLuongMuon(soluongmuon);
                docgia.setTrangThaiTK(trangthaitk); //set thông tin
            }
        } catch (SQLException e) {
            System.out.println("lõi ssssssssssssss");
        }
        finally{
             connection.close();
         }     
        return docgia;
    }
    //List thông tin độc giả mượn trong tháng
    public List<DocGia> ThongTinDocGiaMuonTrongThang(int chon) throws Exception{
        DocGia docgia=null;
        Connection con = Connect_SQL.openConnection();
        String sql = "SELECT * FROM DocGia " +
                    "INNER JOIN PhieuMuon ON DocGia.MaDG = PhieuMuon.MaDG " +
                    "WHERE MONTH(NgayMuon) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String madg = rs.getString("MaDG");
                String tendg = rs.getString("Ten");
                String ngaysinh = rs.getString("NgaySinh");
                String gioitinh =  rs.getString("GioiTinh");
                String email =  rs.getString("Email");
                int soluongmuon = rs.getInt("SoLuongMuon");
                String trangthai =  rs.getString("TrangThaiTK");
                docgia = new DocGia(madg, tendg, ngaysinh, gioitinh, email, soluongmuon, trangthai);
                arrDocGia.add(docgia);
            }
               
        } catch (SQLException e) {
            System.out.println(e);
        }
        return arrDocGia;
    }
    public List<DocGia> ThongTinDocGiaMuonTrongThang_COTIENPHAT(int chon) throws Exception{
        DocGia docgia=null;
        Connection con = Connect_SQL.openConnection();
        String sql = "SELECT PhieuMuon.MaPM,DocGia.MaDG,DocGia.Ten,DocGia.NgaySinh,DocGia.GioiTinh,DocGia.Email,ChiTietPhieuMuon.TienPhat  "
                + "FROM DocGia INNER JOIN PhieuMuon ON DocGia.MaDG = PhieuMuon.MaDG  "
                + "INNER JOIN ChiTietPhieuMuon ON PhieuMuon.MaPM = ChiTietPhieuMuon.MaPM "
                + " WHERE MONTH(ChiTietPhieuMuon.NgayTra)= ?  ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String mapm = rs.getString("MaPM");
                String madg = rs.getString("MaDG");
                String tendg = rs.getString("Ten");
                String ngaysinh = rs.getString("NgaySinh");
                String gioitinh =  rs.getString("GioiTinh");
                String email =  rs.getString("Email");
                int tienphat = rs.getInt("TienPhat");
                docgia = new DocGia(mapm, madg, tendg, ngaysinh, gioitinh, email, tienphat);
                arrDocGia1.add(docgia);
            }
               
        } catch (SQLException e) {
            System.out.println(e);
        }
        return arrDocGia1;
    }
    public static void main(String[] args) throws Exception {
       
       
        DocGia_DAO d = new DocGia_DAO();
        List<DocGia> f = d.ThongTinDocGiaMuonTrongThang_COTIENPHAT(12);
        
        for(DocGia x: f){
            System.out.println(x);
        }
    }
   
    
}
