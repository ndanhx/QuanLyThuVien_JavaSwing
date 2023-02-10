package View;
import Model.NhaXuatBan;
import Model.PhieuMuon;
import Model.DanhMucSach;
import Model.DocGia;
import Model.ChiTietPhieuMuon;
import Model.Sach;
import DAO.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author Home
 */
final class AdminJFrame extends javax.swing.JFrame {
    int current =0;
    Sach_DAO dao_sach = new  Sach_DAO();
    ArrayList<Sach> arrSach = new ArrayList<>();   
    ArrayList<DanhMucSach> arrDanhMuc = new ArrayList<>();
    ArrayList<PhieuMuon> arrPhieuMuon = new ArrayList<>();
    ArrayList<ChiTietPhieuMuon> arrCTPM = new ArrayList<>();
    ArrayList<NhaXuatBan> arrNXB = new ArrayList<>();
    String titleTraCuuHome[]= {};
    DefaultTableModel model,model1,model4,model2,model3  =  new DefaultTableModel(titleTraCuuHome,0);
    ArrayList<DocGia> arrSinhVien = new ArrayList<>();
    public AdminJFrame() throws Exception {
        initComponents();
        LoadDatatoArrayList();
        LoadDataArrayttoTable(); 
        LoadDatatoArrayList_TableNhaXuatBAn();
        LoadDataArrayttoTable_tableNhaXuatBAn();
        load_MaTheLoai();
        load_MaDanhMuc();
        load_maNhaXuatBAn();
        LoadDatatoArrayList_tableDanhMuc();
        LoadDataArrayttoTable_tableDanhMuc();
        LoadDatatoArrayList_tableSinHVien();
        LoadDataArrayttoTable_tableSinhVien(); 
        LoadDatatoArrayList_tablePhieuMuon(); 
        LoadDataArrayttoTable_tablePhieuMuon(); 
        LoadDatatoArrayList_tableChiTietPhieuMuon(); 
        LoadDataArrayttoTable_tableChiTietPhieuMuon(); 
    }
    //Đỗ dữ liều từ table lên các textfield
    public void loadTuClick_tableSach(int row){
        Sach sach = arrSach.get(row);
        txt_maSach.setText(sach.getMaSach());
        txt_tenSach.setText(sach.getTenSach());
        cbx_maTheLoai.setSelectedItem(sach.getMaTheLoai());
        txt_soLuongCon.setText(""+sach.getSoLuongCon());
        txt_namXuatBan.setText(""+sach.getNamXuatBan());
        cbx_maDanhMuc.setSelectedItem(sach.getMaDMSach());
        txt_tacGia.setText(sach.getTacGia());
        txAre_tomTatND.setText(sach.getTomTatND());
        cbx_maNhaXuatBan.setSelectedItem(sach.getNXB());
   } 
    public void loadTuClick_tableDanhMucSach(int row){
        DanhMucSach danhmucsach = arrDanhMuc.get(row);
        txt_madDanhMuc_QLDM.setText(danhmucsach.getMaDM());
        txt_tenDanhMuc_QLDM.setText(danhmucsach.getTenDM());
   } 
   public void loadTuClick_tableSinhVien_QLSV(int row){
       DocGia sinhvien = arrSinhVien.get(row);
       txt_maSinhVien_QLSV.setText(sinhvien.getMaDG());
       txt_tenSinhVien_QLSV.setText(sinhvien.getTenDG());
       txt_ngaySinhSV_QLSV.setText(sinhvien.getNgaySinh());
       txt_emailSinhvien_QLSV.setText(sinhvien.getEmail());
       String gioitinh = sinhvien.getGioiTinh();
       if(gioitinh.equals("Nam") ) rdo_gtNam.setSelected(true) ;
       else rdo_gtNu.setSelected(true);
   }
   //Đỗ dữ liệu từ Database vào arraylist
    public void LoadDatatoArrayList() throws Exception{
        arrSach.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM Sach";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
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
                Sach sach =  new Sach(masach, tensach, madanhmucsach, matheloai, tacgia, nxb, namxuatban, soluong, nd);
               arrSach.add(sach);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Khong ket noi duoc khi add vao list ");
        }
    }
    //Đổ dữ liệu vào table
    public void LoadDataArrayttoTable (){
        model = (DefaultTableModel) Table_QuanLySach_Admin.getModel();
        model1 = (DefaultTableModel) Table_TraCuu_Admin_s.getModel();
        model2= (DefaultTableModel) Table_ThongKeSach_ThongKe.getModel();
        model.setRowCount(0);
        model1.setRowCount(0);
        model2.setRowCount(0);
        for(Sach sach : arrSach){
            model.addRow(new Object[]
                        { sach.getMaSach(),sach.getTenSach(),
                          sach.getMaDMSach(),sach.getMaTheLoai(),sach.getTacGia(),
                          sach.getNXB(),sach.getNamXuatBan(),sach.getSoLuongCon()  }    
                     );
            model1.addRow(new Object[]
                        { sach.getMaSach(),sach.getTenSach(),
                          sach.getMaDMSach(),sach.getMaTheLoai(),sach.getTacGia(),
                          sach.getNXB(),sach.getNamXuatBan(),sach.getSoLuongCon()  }    
                     );
            model2.addRow(new Object[]
                        { sach.getMaSach(),sach.getTenSach(),
                          sach.getMaDMSach(),sach.getMaTheLoai(),sach.getTacGia(),
                          sach.getNXB(),sach.getNamXuatBan(),sach.getSoLuongCon()  }    
                     );
        }
        TableColumnModel columnModel = Table_QuanLySach_Admin.getColumnModel();
        TableColumn cot1sach = columnModel.getColumn(1);
        cot1sach.setPreferredWidth(250);
        TableColumn cot0sach= columnModel.getColumn(0);
        cot0sach.setPreferredWidth(120);
        TableColumnModel columnModel_1 = Table_ThongKeSach_ThongKe.getColumnModel();
        TableColumn cot1sach_tk = columnModel_1.getColumn(1);
        TableColumn cot0sach_tk= columnModel_1.getColumn(0);
        cot0sach_tk.setPreferredWidth(120);
        cot1sach_tk.setPreferredWidth(250);
        TableColumnModel columnModel_tracuu = Table_TraCuu_Admin_s.getColumnModel();
        TableColumn cot1_tracuu = columnModel_tracuu.getColumn(1);
        cot1_tracuu.setPreferredWidth(250);
        TableColumn cot0sach_tracuu= columnModel_tracuu.getColumn(0);
        cot0sach_tracuu.setPreferredWidth(120);
    }
    
    public void LoadDatatoArrayList_tableSinHVien(){
        arrSinhVien.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM DocGia";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String masv = rs.getString("MaDG");
                String tensv =rs.getString("Ten");
                String ngaysinhsv = rs.getString("NgaySinh");
                String gioitinhsv = rs.getString("GioiTinh");
                String emailsv = rs.getString("Email");
                int soluongmuon = rs.getInt("SoLuongMuon");
                String trangthaitk=rs.getString("TrangThaiTK");
                DocGia sinhvien = new DocGia(masv, tensv, ngaysinhsv, gioitinhsv, emailsv,soluongmuon,trangthaitk);
                arrSinhVien.add(sinhvien);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Khong ket noi duoc khi add vao list ");
        }
    }
      
    public void LoadDataArrayttoTable_tableSinhVien (){
        model = (DefaultTableModel) Table_QuanLySinhVien_Admin.getModel();
        model1 = (DefaultTableModel) Table_TraCuuSinhVien_QuanLySinhVien_Admin1.getModel();
        model2=(DefaultTableModel) Table_ThongKeSinhVien_ThongKe.getModel();
        model.setRowCount(0);
        model1.setRowCount(0);
        model2.setRowCount(0);
        for(DocGia sinhvien : arrSinhVien){
            model.addRow(new Object[]{ 
                            sinhvien.getMaDG(),sinhvien.getTenDG(),sinhvien.getNgaySinh(),
                sinhvien.getGioiTinh(),sinhvien.getEmail(),sinhvien.getTrangThaiTK() });
            
            model1.addRow(new Object[]{ 
                            sinhvien.getMaDG(),sinhvien.getTenDG(),sinhvien.getNgaySinh(),
                sinhvien.getGioiTinh(),sinhvien.getEmail(),sinhvien.getTrangThaiTK() });    
            model2.addRow(new Object[]{ 
                            sinhvien.getMaDG(),sinhvien.getTenDG(),sinhvien.getNgaySinh(),
                sinhvien.getGioiTinh(),sinhvien.getEmail(),sinhvien.getTrangThaiTK() }); 
        }
    } 
     
    public void LoadDatatoArrayList_tableDanhMuc(){
        arrDanhMuc.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM DanhMucSach;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String madanhmucsach = rs.getString(1);
                String tendanhmucsach =rs.getString(2);
               DanhMucSach danhmuc = new DanhMucSach(madanhmucsach, tendanhmucsach);
               arrDanhMuc.add(danhmuc);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Khong ket noi duoc khi add vao list ");
        }
    }
    
    //Đổ dữ liệu vào table
    public void LoadDataArrayttoTable_tableDanhMuc (){
        model = (DefaultTableModel) Table_QuanLyDanhMuc_Admin.getModel();
        model.setRowCount(0);
        for(DanhMucSach danhmuc : arrDanhMuc){
            model.addRow(new Object[]{ 
                            danhmuc.getMaDM(),danhmuc.getTenDM() } );
        }
    }
    public void LoadDatatoArrayList_TableNhaXuatBAn(){
        arrNXB.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM NhaXuatBan";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String manxb = rs.getString(1);
                String tennxb =rs.getString(2);
                NhaXuatBan nxb = new NhaXuatBan(manxb, tennxb);
                arrNXB.add(nxb);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Khong ket noi duoc khi add vao list ");
        }
    }
    
    public void LoadDataArrayttoTable_tableNhaXuatBAn  (){
        model = (DefaultTableModel) Table_QuanLyNHaXuatBAn_Admin.getModel();
        model.setRowCount(0);
        for(NhaXuatBan nxb : arrNXB){
            model.addRow(new Object[]{ 
                            nxb.getMaNhaXuatBan(),nxb.getTenNhaXuatBan() });
        }
    } 
     public void LoadDatatoArrayList_tablePhieuMuon(){
        arrPhieuMuon.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM PhieuMuon";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maPM = rs.getString("MaPM");
                String ngayMuon = rs.getString("NgayMuon");
                String masv = rs.getString("MaDG");
                String maql = rs.getString("MaQL");
                String ghichu = rs.getString("GhiChu");
                String trangthai=rs.getString("TrangThai");
                PhieuMuon phieumuon = new PhieuMuon(maPM, ngayMuon, masv, maql, ghichu,trangthai);
                arrPhieuMuon.add(phieumuon);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Khong ket noi duoc khi add vao list ");
        }
    }
     public void LoadDataArrayttoTable_tablePhieuMuon (){
        model = (DefaultTableModel) Table_DanhSachMuon_QLPM.getModel();
        model.setRowCount(0);
        for(PhieuMuon phieumuon : arrPhieuMuon){
            model.addRow(new Object[]{ 
                            phieumuon.getMaPM(),phieumuon.getNgayMuon(),phieumuon.getMaDG_PM(),phieumuon.getMaQuanLy(),phieumuon.getGhiChu(),phieumuon.getTrangThai() } );
        }
    }
    public void LoadDatatoArrayList_tableChiTietPhieuMuon(){
        arrCTPM.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM ChiTietPhieuMuon";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maPM = rs.getString("MaPM");
                String masach = rs.getString("MaSach");
                String ngaytra = rs.getString("NgayTra");
                int tienphat = rs.getInt("TienPhat");
                String tinhtrangsach = rs.getString("TinhTrangSach");
                ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(maPM, masach, ngaytra, tienphat, tinhtrangsach);
                arrCTPM.add(ctpm);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Khong ket noi duoc khi add vao list ");
        }
    } 
    public void LoadDataArrayttoTable_tableChiTietPhieuMuon (){
        model = (DefaultTableModel) Table_ChiTietPhieuMuon_QLPM.getModel();
        model.setRowCount(0);
        for(ChiTietPhieuMuon ctpm : arrCTPM){
            model.addRow(new Object[]{ 
                           ctpm.getMaPM(),ctpm.getMaSach(),ctpm.getNgayThucTra(),ctpm.getTienPhat(),ctpm.getTinhTrangSach() } );
        }
    }
    public void load_maNhaXuatBAn(){
        cbx_maNhaXuatBan.removeAllItems();
        try{
           Connection connection  =  Connect_SQL.openConnection();
           String sql = "SELECT maNhaXB FROM NhaXuatBan";
           Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                this.cbx_maNhaXuatBan.addItem(rs.getString("maNhaXB"));
            }
        }catch(Exception e){
            System.out.println("lỗi sql nha");
        }
    }
    public void load_MaDanhMuc() throws Exception{
        
        cbx_maDanhMuc.removeAllItems();
        try{
           Connection connection  =  Connect_SQL.openConnection();
           String sql = "SELECT MaDanhMucSach FROM DanhMucSach";
           Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                this.cbx_maDanhMuc.addItem(rs.getString("MaDanhMucSach"));
            }
        }catch(Exception e){
            System.out.println("lỗi sql nha");
        }
    }
   
    public void load_MaTheLoai() throws Exception{
        cbx_maTheLoai.removeAllItems();
        try{
           Connection connection  =  Connect_SQL.openConnection();
           String sql = "SELECT MaTheLoai FROM TheLoai";
           Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                this.cbx_maTheLoai.addItem(rs.getString("MaTheLoai"));
            }
        }catch(Exception e){
            System.out.println("lỗi sql nha");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnGroup_GioiTinh_QLSV = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_maSinhVien_QLSV = new javax.swing.JTextField();
        txt_tenSinhVien_QLSV = new javax.swing.JTextField();
        txt_ngaySinhSV_QLSV = new javax.swing.JTextField();
        txt_emailSinhvien_QLSV = new javax.swing.JTextField();
        rdo_gtNam = new javax.swing.JRadioButton();
        rdo_gtNu = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        btn_add_Sach1 = new javax.swing.JButton();
        btn_Update_sinhvien_QLSV = new javax.swing.JButton();
        btn_khoaTaiKhoan_QLSV = new javax.swing.JButton();
        btn_moTaiKhoan_QLSV = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        Table_QuanLySinhVien_Admin = new javax.swing.JTable();
        btn_delete_SinhVien_QLSV = new javax.swing.JButton();
        btn_lamMoi_QLSV = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txt_timKiemSinhVien_QLSV_Admin1 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        Table_TraCuuSinhVien_QuanLySinhVien_Admin1 = new javax.swing.JTable();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_maSach = new javax.swing.JTextField();
        txt_tenSach = new javax.swing.JTextField();
        txt_soLuongCon = new javax.swing.JTextField();
        txt_namXuatBan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_tacGia = new javax.swing.JTextField();
        btn_add_Sach = new javax.swing.JButton();
        btn_Update_Sach = new javax.swing.JButton();
        btn_lamMoi_Sach = new javax.swing.JButton();
        btn_delete_Sach = new javax.swing.JButton();
        cbx_maDanhMuc = new javax.swing.JComboBox<>();
        cbx_maTheLoai = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        Table_QuanLySach_Admin = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        txAre_tomTatND = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txt_timKiemSach_qlSach_Admin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_tenTheLoai_QLSach = new javax.swing.JTextField();
        txt_tenDanhMuc_QLSach = new javax.swing.JTextField();
        txt_tenNhaXb = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cbx_maNhaXuatBan = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_madDanhMuc_QLDM = new javax.swing.JTextField();
        txt_tenDanhMuc_QLDM = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        Table_QuanLyDanhMuc_Admin = new javax.swing.JTable();
        btn_add_DanhMuc_QLDM = new javax.swing.JButton();
        btn_Update_DanhMuc_QLDM = new javax.swing.JButton();
        btn_delete_DanhMuc_QLDM = new javax.swing.JButton();
        btn_lamMoi_DAnhMuc_QLDM = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txt_timKiemDanhMuc_QLDM = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txt_maNhaXuatrBan_QLNXB = new javax.swing.JTextField();
        txt_tenNhXuatBan_QLNXB = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_timKiemNhaXuatBAn_QNXB = new javax.swing.JTextField();
        btn_add_NXB_QLNXB = new javax.swing.JButton();
        btn_Update_NXB_QLNXB = new javax.swing.JButton();
        btn_lamMoi_NXB_QLNXB = new javax.swing.JButton();
        btn_delete_NXB_QLNXB = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        Table_QuanLyNHaXuatBAn_Admin = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        Table_TraCuu_Admin_s = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        txt_timKiemSach_TraCuu_Admin1 = new javax.swing.JTextField();
        btn_xemChiTietSach = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        Table_ThongKeSinhVien_ThongKe = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        cbx_soThangDOCGIA_ThongKe = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        cbx_sothangSACH_THONGKE = new javax.swing.JComboBox<>();
        jScrollPane14 = new javax.swing.JScrollPane();
        Table_ThongKeSach_ThongKe = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        cbx_sothanTienPhat_THONGKE = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        Table_ThongekeTienPhat_ThongKe = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        lb_TongTienPhat_ThongKe = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_DanhSachMuon_QLPM = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbx_trangThaiPhieuMuon_QLPM = new javax.swing.JComboBox<>();
        btn_timkiem_DSMuon_QLPM = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbx_tinhtrangsacPhieuMuon_QLPM1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_ChiTietPhieuMuon_QLPM = new javax.swing.JTable();
        btn_timkiem_CTPM_QLPM = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý thư viện");
        setBackground(new java.awt.Color(255, 255, 204));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 204));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setAlignmentX(0.0F);
        jTabbedPane1.setAlignmentY(0.0F);
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jTabbedPane1.setInheritsPopupMenu(true);
        jTabbedPane1.setName(""); // NOI18N

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 204));
        jTabbedPane4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("Ngày sinh:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Giới tính:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setText("Email:");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setText("Mã độc giả:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setText("Tên độc giả:");

        txt_maSinhVien_QLSV.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_tenSinhVien_QLSV.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_ngaySinhSV_QLSV.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_ngaySinhSV_QLSV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ngaySinhSV_QLSVFocusLost(evt);
            }
        });

        txt_emailSinhvien_QLSV.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        btnGroup_GioiTinh_QLSV.add(rdo_gtNam);
        rdo_gtNam.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        rdo_gtNam.setText("  Nam");

        btnGroup_GioiTinh_QLSV.add(rdo_gtNu);
        rdo_gtNu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        rdo_gtNu.setText("  Nữ");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/arrow-next-3-icon.png"))); // NOI18N
        jButton1.setText("Đăng xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_add_Sach1.setBackground(new java.awt.Color(102, 255, 102));
        btn_add_Sach1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add_Sach1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/add-1-icon.png"))); // NOI18N
        btn_add_Sach1.setText("Thêm");
        btn_add_Sach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_Sach1ActionPerformed(evt);
            }
        });

        btn_Update_sinhvien_QLSV.setBackground(new java.awt.Color(102, 255, 102));
        btn_Update_sinhvien_QLSV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_Update_sinhvien_QLSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/pencil-icon.png"))); // NOI18N
        btn_Update_sinhvien_QLSV.setText(" Sửa");
        btn_Update_sinhvien_QLSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Update_sinhvien_QLSVActionPerformed(evt);
            }
        });

        btn_khoaTaiKhoan_QLSV.setBackground(new java.awt.Color(255, 102, 102));
        btn_khoaTaiKhoan_QLSV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_khoaTaiKhoan_QLSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Lock-icon.png"))); // NOI18N
        btn_khoaTaiKhoan_QLSV.setText("Khóa tài khoản");
        btn_khoaTaiKhoan_QLSV.setToolTipText("");
        btn_khoaTaiKhoan_QLSV.setActionCommand("");
        btn_khoaTaiKhoan_QLSV.setName(""); // NOI18N
        btn_khoaTaiKhoan_QLSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khoaTaiKhoan_QLSVActionPerformed(evt);
            }
        });

        btn_moTaiKhoan_QLSV.setBackground(new java.awt.Color(102, 255, 102));
        btn_moTaiKhoan_QLSV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_moTaiKhoan_QLSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Unlock-icon.png"))); // NOI18N
        btn_moTaiKhoan_QLSV.setText("Mở khóa");
        btn_moTaiKhoan_QLSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_moTaiKhoan_QLSVActionPerformed(evt);
            }
        });

        Table_QuanLySinhVien_Admin.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_QuanLySinhVien_Admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Ngày sinh", "Giới tính", "Email", "Trạng thái"
            }
        ));
        Table_QuanLySinhVien_Admin.setGridColor(new java.awt.Color(255, 255, 255));
        Table_QuanLySinhVien_Admin.setMinimumSize(new java.awt.Dimension(50, 110));
        Table_QuanLySinhVien_Admin.setRowHeight(30);
        Table_QuanLySinhVien_Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_QuanLySinhVien_AdminMouseClicked(evt);
            }
        });
        Table_QuanLySinhVien_Admin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table_QuanLySinhVien_AdminKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table_QuanLySinhVien_AdminKeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(Table_QuanLySinhVien_Admin);

        btn_delete_SinhVien_QLSV.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete_SinhVien_QLSV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete_SinhVien_QLSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/xoa.png"))); // NOI18N
        btn_delete_SinhVien_QLSV.setLabel("Xóa");
        btn_delete_SinhVien_QLSV.setName(""); // NOI18N
        btn_delete_SinhVien_QLSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_SinhVien_QLSVActionPerformed(evt);
            }
        });

        btn_lamMoi_QLSV.setBackground(new java.awt.Color(102, 255, 102));
        btn_lamMoi_QLSV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_lamMoi_QLSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Actions-folder-new-icon.png"))); // NOI18N
        btn_lamMoi_QLSV.setText("Làm mới");
        btn_lamMoi_QLSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoi_QLSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(574, Short.MAX_VALUE)
                .addComponent(btn_lamMoi_QLSV)
                .addGap(42, 42, 42)
                .addComponent(btn_khoaTaiKhoan_QLSV)
                .addGap(37, 37, 37)
                .addComponent(btn_delete_SinhVien_QLSV)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btn_add_Sach1)
                        .addGap(43, 43, 43)
                        .addComponent(btn_Update_sinhvien_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btn_moTaiKhoan_QLSV)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_gtNam, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_gtNu, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_maSinhVien_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tenSinhVien_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(101, 101, 101)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_emailSinhvien_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(368, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_ngaySinhSV_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(37, 37, 37))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel18)
                            .addComponent(txt_maSinhVien_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngaySinhSV_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(txt_tenSinhVien_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(txt_emailSinhvien_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(rdo_gtNam)
                            .addComponent(rdo_gtNu)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add_Sach1)
                    .addComponent(btn_Update_sinhvien_QLSV)
                    .addComponent(btn_moTaiKhoan_QLSV)
                    .addComponent(btn_lamMoi_QLSV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_khoaTaiKhoan_QLSV)
                    .addComponent(btn_delete_SinhVien_QLSV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_Update_sinhvien_QLSV, btn_add_Sach1, btn_delete_SinhVien_QLSV, btn_khoaTaiKhoan_QLSV, btn_lamMoi_QLSV, btn_moTaiKhoan_QLSV, jButton1});

        jTabbedPane4.addTab("Quản lý độc giả", jPanel6);

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        jLabel19.setText("Tìm kiếm độc giả:");

        txt_timKiemSinhVien_QLSV_Admin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemSinhVien_QLSV_Admin1ActionPerformed(evt);
            }
        });
        txt_timKiemSinhVien_QLSV_Admin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemSinhVien_QLSV_Admin1KeyReleased(evt);
            }
        });

        Table_TraCuuSinhVien_QuanLySinhVien_Admin1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_TraCuuSinhVien_QuanLySinhVien_Admin1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Giới tính", "Email", "Trạng thái"
            }
        ));
        Table_TraCuuSinhVien_QuanLySinhVien_Admin1.setGridColor(new java.awt.Color(255, 255, 255));
        Table_TraCuuSinhVien_QuanLySinhVien_Admin1.setMinimumSize(new java.awt.Dimension(100, 110));
        Table_TraCuuSinhVien_QuanLySinhVien_Admin1.setRowHeight(30);
        Table_TraCuuSinhVien_QuanLySinhVien_Admin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_TraCuuSinhVien_QuanLySinhVien_Admin1MouseClicked(evt);
            }
        });
        Table_TraCuuSinhVien_QuanLySinhVien_Admin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(Table_TraCuuSinhVien_QuanLySinhVien_Admin1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel19)
                        .addGap(37, 37, 37)
                        .addComponent(txt_timKiemSinhVien_QLSV_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_timKiemSinhVien_QLSV_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Xem thông tin độc giả", jPanel4);

        jTabbedPane1.addTab("    QUẢN LÝ ĐỘC GIẢ   ", new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Student-3-icon.png")), jTabbedPane4); // NOI18N

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 204));
        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Mã ISBN:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Tên sách:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Mã thể loại:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Số lượng còn:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Năm xuất bản:");

        txt_maSach.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_tenSach.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_soLuongCon.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_namXuatBan.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Mã danh mục:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Tác giả:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Nhà xuất bản:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Tóm tắt nội dung:");

        txt_tacGia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        btn_add_Sach.setBackground(new java.awt.Color(102, 255, 102));
        btn_add_Sach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add_Sach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/add-1-icon.png"))); // NOI18N
        btn_add_Sach.setText("Thêm");
        btn_add_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_SachActionPerformed(evt);
            }
        });

        btn_Update_Sach.setBackground(new java.awt.Color(102, 255, 102));
        btn_Update_Sach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_Update_Sach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/pencil-icon.png"))); // NOI18N
        btn_Update_Sach.setText("   Sửa   ");
        btn_Update_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Update_SachActionPerformed(evt);
            }
        });

        btn_lamMoi_Sach.setBackground(new java.awt.Color(102, 255, 102));
        btn_lamMoi_Sach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_lamMoi_Sach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Actions-folder-new-icon.png"))); // NOI18N
        btn_lamMoi_Sach.setText("Làm mới");
        btn_lamMoi_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoi_SachActionPerformed(evt);
            }
        });

        btn_delete_Sach.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete_Sach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete_Sach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/xoa.png"))); // NOI18N
        btn_delete_Sach.setLabel("Xóa");
        btn_delete_Sach.setName(""); // NOI18N
        btn_delete_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_SachActionPerformed(evt);
            }
        });

        cbx_maDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_maDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_maDanhMucActionPerformed(evt);
            }
        });

        cbx_maTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_maTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_maTheLoaiActionPerformed(evt);
            }
        });

        Table_QuanLySach_Admin.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_QuanLySach_Admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Mã danh mục sách", "Mã thể loại", "Tác giả", "Nhà xuất bản", "Năm XB", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_QuanLySach_Admin.setGridColor(new java.awt.Color(255, 255, 255));
        Table_QuanLySach_Admin.setMinimumSize(new java.awt.Dimension(100, 110));
        Table_QuanLySach_Admin.setRowHeight(30);
        Table_QuanLySach_Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_QuanLySach_AdminMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(Table_QuanLySach_Admin);

        txAre_tomTatND.setColumns(20);
        txAre_tomTatND.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txAre_tomTatND.setLineWrap(true);
        txAre_tomTatND.setRows(5);
        jScrollPane5.setViewportView(txAre_tomTatND);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        jLabel12.setText("Tìm kiếm sách:");

        txt_timKiemSach_qlSach_Admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemSach_qlSach_AdminActionPerformed(evt);
            }
        });
        txt_timKiemSach_qlSach_Admin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemSach_qlSach_AdminKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Tên thể loại:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Tên danh mục:");

        txt_tenTheLoai_QLSach.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tenTheLoai_QLSach.setEnabled(false);

        txt_tenDanhMuc_QLSach.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tenDanhMuc_QLSach.setEnabled(false);

        txt_tenNhaXb.setEditable(false);
        txt_tenNhaXb.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel33.setText("Tên nhà xuất bản:");

        cbx_maNhaXuatBan.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_maNhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_maNhaXuatBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tenTheLoai_QLSach, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_soLuongCon, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_namXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_maSach, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tenSach)
                                    .addComponent(cbx_maTheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbx_maDanhMuc, 0, 210, Short.MAX_VALUE)
                                    .addComponent(txt_tenDanhMuc_QLSach)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(jLabel11)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_tacGia, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tenNhaXb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(cbx_maNhaXuatBan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_add_Sach)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(341, 341, 341)
                                .addComponent(btn_Update_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)
                                .addComponent(btn_lamMoi_Sach)
                                .addGap(137, 137, 137)
                                .addComponent(btn_delete_Sach)))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel12)
                                .addGap(39, 39, 39)
                                .addComponent(txt_timKiemSach_qlSach_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_tacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbx_maNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txt_tenNhaXb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbx_maDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txt_tenDanhMuc_QLSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_maSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_tenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbx_maTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_tenTheLoai_QLSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_soLuongCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_namXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Update_Sach)
                    .addComponent(btn_add_Sach)
                    .addComponent(btn_lamMoi_Sach)
                    .addComponent(btn_delete_Sach))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_timKiemSach_qlSach_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Quản lý sách", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Mã danh mục:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Tên danh mục:");

        txt_madDanhMuc_QLDM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_tenDanhMuc_QLDM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        Table_QuanLyDanhMuc_Admin.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_QuanLyDanhMuc_Admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã danh mục sách", "Tên danh mục "
            }
        ));
        Table_QuanLyDanhMuc_Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_QuanLyDanhMuc_AdminMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Table_QuanLyDanhMuc_Admin);

        btn_add_DanhMuc_QLDM.setBackground(new java.awt.Color(102, 255, 102));
        btn_add_DanhMuc_QLDM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add_DanhMuc_QLDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/add-1-icon.png"))); // NOI18N
        btn_add_DanhMuc_QLDM.setText("Thêm");
        btn_add_DanhMuc_QLDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_DanhMuc_QLDMActionPerformed(evt);
            }
        });

        btn_Update_DanhMuc_QLDM.setBackground(new java.awt.Color(102, 255, 102));
        btn_Update_DanhMuc_QLDM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_Update_DanhMuc_QLDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/pencil-icon.png"))); // NOI18N
        btn_Update_DanhMuc_QLDM.setText("Sửa");
        btn_Update_DanhMuc_QLDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Update_DanhMuc_QLDMActionPerformed(evt);
            }
        });

        btn_delete_DanhMuc_QLDM.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete_DanhMuc_QLDM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete_DanhMuc_QLDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/xoa.png"))); // NOI18N
        btn_delete_DanhMuc_QLDM.setLabel("Xóa");
        btn_delete_DanhMuc_QLDM.setName(""); // NOI18N
        btn_delete_DanhMuc_QLDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_DanhMuc_QLDMActionPerformed(evt);
            }
        });

        btn_lamMoi_DAnhMuc_QLDM.setBackground(new java.awt.Color(102, 255, 102));
        btn_lamMoi_DAnhMuc_QLDM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_lamMoi_DAnhMuc_QLDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Actions-folder-new-icon.png"))); // NOI18N
        btn_lamMoi_DAnhMuc_QLDM.setText("Làm mới");
        btn_lamMoi_DAnhMuc_QLDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoi_DAnhMuc_QLDMActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        jLabel16.setText("Tìm kiếm danh mục:");

        txt_timKiemDanhMuc_QLDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemDanhMuc_QLDMActionPerformed(evt);
            }
        });
        txt_timKiemDanhMuc_QLDM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemDanhMuc_QLDMKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btn_add_DanhMuc_QLDM)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txt_tenDanhMuc_QLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(133, 133, 133)
                                .addComponent(txt_timKiemDanhMuc_QLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(btn_Update_DanhMuc_QLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(btn_lamMoi_DAnhMuc_QLDM)
                                .addGap(91, 91, 91)
                                .addComponent(btn_delete_DanhMuc_QLDM))))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(txt_madDanhMuc_QLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(jLabel16)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_madDanhMuc_QLDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tenDanhMuc_QLDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timKiemDanhMuc_QLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete_DanhMuc_QLDM)
                    .addComponent(btn_lamMoi_DAnhMuc_QLDM)
                    .addComponent(btn_Update_DanhMuc_QLDM)
                    .addComponent(btn_add_DanhMuc_QLDM))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Quản lý danh mục", jPanel2);

        jPanel9.setBackground(new java.awt.Color(255, 255, 204));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Mã nhà xuất bản:");

        txt_maNhaXuatrBan_QLNXB.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_tenNhXuatBan_QLNXB.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel31.setText("Tên nhà xuất bản:");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        jLabel32.setText("Tìm kiếm NXB:");

        txt_timKiemNhaXuatBAn_QNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemNhaXuatBAn_QNXBActionPerformed(evt);
            }
        });
        txt_timKiemNhaXuatBAn_QNXB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemNhaXuatBAn_QNXBKeyReleased(evt);
            }
        });

        btn_add_NXB_QLNXB.setBackground(new java.awt.Color(102, 255, 102));
        btn_add_NXB_QLNXB.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add_NXB_QLNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/add-1-icon.png"))); // NOI18N
        btn_add_NXB_QLNXB.setText("Thêm");
        btn_add_NXB_QLNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_NXB_QLNXBActionPerformed(evt);
            }
        });

        btn_Update_NXB_QLNXB.setBackground(new java.awt.Color(102, 255, 102));
        btn_Update_NXB_QLNXB.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_Update_NXB_QLNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/pencil-icon.png"))); // NOI18N
        btn_Update_NXB_QLNXB.setText("Sửa");
        btn_Update_NXB_QLNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Update_NXB_QLNXBActionPerformed(evt);
            }
        });

        btn_lamMoi_NXB_QLNXB.setBackground(new java.awt.Color(102, 255, 102));
        btn_lamMoi_NXB_QLNXB.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_lamMoi_NXB_QLNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Actions-folder-new-icon.png"))); // NOI18N
        btn_lamMoi_NXB_QLNXB.setText("Làm mới");
        btn_lamMoi_NXB_QLNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoi_NXB_QLNXBActionPerformed(evt);
            }
        });

        btn_delete_NXB_QLNXB.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete_NXB_QLNXB.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete_NXB_QLNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/xoa.png"))); // NOI18N
        btn_delete_NXB_QLNXB.setLabel("Xóa");
        btn_delete_NXB_QLNXB.setName(""); // NOI18N
        btn_delete_NXB_QLNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_NXB_QLNXBActionPerformed(evt);
            }
        });

        Table_QuanLyNHaXuatBAn_Admin.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_QuanLyNHaXuatBAn_Admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã nhà xuất bản", "Tên nhà xuất bản"
            }
        ));
        Table_QuanLyNHaXuatBAn_Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_QuanLyNHaXuatBAn_AdminMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(Table_QuanLyNHaXuatBAn_Admin);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btn_add_NXB_QLNXB)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txt_tenNhXuatBan_QLNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(133, 133, 133)
                                .addComponent(txt_timKiemNhaXuatBAn_QNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(btn_Update_NXB_QLNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(btn_lamMoi_NXB_QLNXB)
                                .addGap(91, 91, 91)
                                .addComponent(btn_delete_NXB_QLNXB))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(txt_maNhaXuatrBan_QLNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(jLabel32)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(836, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_maNhaXuatrBan_QLNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tenNhXuatBan_QLNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timKiemNhaXuatBAn_QNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete_NXB_QLNXB)
                    .addComponent(btn_lamMoi_NXB_QLNXB)
                    .addComponent(btn_Update_NXB_QLNXB)
                    .addComponent(btn_add_NXB_QLNXB))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Quản lý nhà xuất bản", jPanel9);

        jTabbedPane1.addTab("        QUẢN LÝ SÁCH      ", new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Book-icon.png")), jTabbedPane3); // NOI18N
        jTabbedPane3.getAccessibleContext().setAccessibleName("");

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        Table_TraCuu_Admin_s.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_TraCuu_Admin_s.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Mã danh mục sách", "Mã thể loại", "Tác giả", "Nhà xuất bản", "Năm XB", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_TraCuu_Admin_s.setGridColor(new java.awt.Color(255, 255, 255));
        Table_TraCuu_Admin_s.setMinimumSize(new java.awt.Dimension(100, 110));
        Table_TraCuu_Admin_s.setRowHeight(30);
        Table_TraCuu_Admin_s.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_TraCuu_Admin_sMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(Table_TraCuu_Admin_s);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        jLabel20.setText("Tìm kiếm sách:");

        txt_timKiemSach_TraCuu_Admin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemSach_TraCuu_Admin1ActionPerformed(evt);
            }
        });
        txt_timKiemSach_TraCuu_Admin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemSach_TraCuu_Admin1KeyReleased(evt);
            }
        });

        btn_xemChiTietSach.setBackground(new java.awt.Color(204, 255, 255));
        btn_xemChiTietSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_xemChiTietSach.setText("xem chi tiết");
        btn_xemChiTietSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xemChiTietSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txt_timKiemSach_TraCuu_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btn_xemChiTietSach, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timKiemSach_TraCuu_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xemChiTietSach, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_xemChiTietSach, jLabel20, txt_timKiemSach_TraCuu_Admin1});

        jTabbedPane1.addTab("       TRA CỨU SÁCH        ", new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/search-icon.png")), jPanel5); // NOI18N

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 204));
        jTabbedPane2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 204));

        Table_ThongKeSinhVien_ThongKe.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_ThongKeSinhVien_ThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Ngày sinh", "Giới tính", "Email", "Trạng thái"
            }
        ));
        Table_ThongKeSinhVien_ThongKe.setGridColor(new java.awt.Color(255, 255, 255));
        Table_ThongKeSinhVien_ThongKe.setMinimumSize(new java.awt.Dimension(50, 110));
        Table_ThongKeSinhVien_ThongKe.setRowHeight(30);
        jScrollPane13.setViewportView(Table_ThongKeSinhVien_ThongKe);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setText("Thông tin độc giả đã mượn sách trong tháng:");

        cbx_soThangDOCGIA_ThongKe.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbx_soThangDOCGIA_ThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbx_soThangDOCGIA_ThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_soThangDOCGIA_ThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(cbx_soThangDOCGIA_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(462, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbx_soThangDOCGIA_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(524, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(142, 142, 142)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Thống kê độc giả ", jPanel8);

        jPanel11.setBackground(new java.awt.Color(255, 255, 204));
        jPanel11.setForeground(new java.awt.Color(255, 255, 204));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel29.setText("Thông tin sách đã được mượn trong tháng:");

        cbx_sothangSACH_THONGKE.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbx_sothangSACH_THONGKE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbx_sothangSACH_THONGKE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_sothangSACH_THONGKEActionPerformed(evt);
            }
        });

        Table_ThongKeSach_ThongKe.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_ThongKeSach_ThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Mã danh mục sách", "Mã thể loại", "Tác giả", "Nhà xuất bản", "Năm XB", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_ThongKeSach_ThongKe.setGridColor(new java.awt.Color(255, 255, 255));
        Table_ThongKeSach_ThongKe.setMinimumSize(new java.awt.Dimension(100, 110));
        Table_ThongKeSach_ThongKe.setRowHeight(30);
        jScrollPane14.setViewportView(Table_ThongKeSach_ThongKe);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_sothangSACH_THONGKE, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbx_sothangSACH_THONGKE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thống kê sách", jPanel11);

        jPanel10.setBackground(new java.awt.Color(255, 255, 204));

        cbx_sothanTienPhat_THONGKE.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbx_sothanTienPhat_THONGKE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbx_sothanTienPhat_THONGKE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_sothanTienPhat_THONGKEActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel30.setText("Tổng tiền và thông tin đóng phạt trong tháng:");

        Table_ThongekeTienPhat_ThongKe.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_ThongekeTienPhat_ThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã độc giả", "Tên độc giả", "Ngày sinh", "Giới tính", "Email", "Tiền phạt"
            }
        ));
        Table_ThongekeTienPhat_ThongKe.setGridColor(new java.awt.Color(255, 255, 255));
        Table_ThongekeTienPhat_ThongKe.setMinimumSize(new java.awt.Dimension(50, 110));
        Table_ThongekeTienPhat_ThongKe.setRowHeight(30);
        jScrollPane15.setViewportView(Table_ThongekeTienPhat_ThongKe);

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel34.setText("Tổng tiền:");

        lb_TongTienPhat_ThongKe.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_TongTienPhat_ThongKe.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_TongTienPhat_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(cbx_sothanTienPhat_THONGKE, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cbx_sothanTienPhat_THONGKE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(lb_TongTienPhat_ThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane2.addTab("Thống kế tiền phạt", jPanel10);

        jTabbedPane1.addTab("           THỐNG KÊ             ", new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/dollar-stats-icon.png")), jTabbedPane2); // NOI18N
        jTabbedPane2.getAccessibleContext().setAccessibleName("");

        jTabbedPane5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        Table_DanhSachMuon_QLPM.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_DanhSachMuon_QLPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Ngày mượn", "Mã độc giả", "Mã thủ thư", "Ghi chú", "Trạng thái"
            }
        ));
        Table_DanhSachMuon_QLPM.setRowHeight(30);
        jScrollPane2.setViewportView(Table_DanhSachMuon_QLPM);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Trạng thái phiếu mượn:");

        cbx_trangThaiPhieuMuon_QLPM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbx_trangThaiPhieuMuon_QLPM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa trả", "Đã trả", "Chưa duyệt" }));

        btn_timkiem_DSMuon_QLPM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_timkiem_DSMuon_QLPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        btn_timkiem_DSMuon_QLPM.setText("Tìm kiếm");
        btn_timkiem_DSMuon_QLPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiem_DSMuon_QLPMActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 255, 102));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Picture-Manager-icon.png"))); // NOI18N
        jButton2.setText("Quản lý thông tin");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_trangThaiPhieuMuon_QLPM, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btn_timkiem_DSMuon_QLPM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jButton2)
                        .addGap(146, 146, 146))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_trangThaiPhieuMuon_QLPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timkiem_DSMuon_QLPM)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Danh sách mượn", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 204));
        jPanel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Tình trạng sách:");

        cbx_tinhtrangsacPhieuMuon_QLPM1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbx_tinhtrangsacPhieuMuon_QLPM1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bình thường", "Mất sách", "Làm hỏng", "Quá hạn" }));

        Table_ChiTietPhieuMuon_QLPM.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_ChiTietPhieuMuon_QLPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã sách", "Ngày trả", "Tiền phạt", "Tình trạng sách"
            }
        ));
        Table_ChiTietPhieuMuon_QLPM.setRowHeight(30);
        jScrollPane3.setViewportView(Table_ChiTietPhieuMuon_QLPM);

        btn_timkiem_CTPM_QLPM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_timkiem_CTPM_QLPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        btn_timkiem_CTPM_QLPM.setText("Tìm kiếm");
        btn_timkiem_CTPM_QLPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiem_CTPM_QLPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_tinhtrangsacPhieuMuon_QLPM1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btn_timkiem_CTPM_QLPM))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tinhtrangsacPhieuMuon_QLPM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timkiem_CTPM_QLPM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jTabbedPane5.addTab("Chi tiết phiếu mượn", jPanel7);

        jTabbedPane1.addTab("     XEM PHIẾU MƯỢN     ", new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/news-icon.png")), jTabbedPane5); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName(""); // NOI18N

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Table_QuanLySach_AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_QuanLySach_AdminMouseClicked
        // TODO add your handling code here:
        current=Table_QuanLySach_Admin.getSelectedRow();
        loadTuClick_tableSach(current);
    }//GEN-LAST:event_Table_QuanLySach_AdminMouseClicked

    private void btn_add_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_SachActionPerformed
        // TODO add your handling code here:
        int check = 0;
        if(txt_maSach.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bắt buộc nhập mã sách!");
            txt_maSach.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "INSERT INTO Sach values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maSach.getText());
            st.setString(2,txt_tenSach.getText());
            st.setString(3, (String) cbx_maDanhMuc.getSelectedItem());
            st.setString(4, (String) cbx_maTheLoai.getSelectedItem());
            st.setString(5,txt_tacGia.getText());
            st.setString(6,(String) cbx_maNhaXuatBan.getSelectedItem());
            st.setString(7,txt_namXuatBan.getText());
            st.setString(8,txt_soLuongCon.getText());
            st.setString(9,txAre_tomTatND.getText());

            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
            else
                JOptionPane.showMessageDialog(this, "Trùng mã sách. Nhập lại!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Không Thêm dữ liệu được!");
        }
        try {
            LoadDatatoArrayList();
        } catch (Exception ex) {
            Logger.getLogger(AdminJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadDataArrayttoTable();
    }//GEN-LAST:event_btn_add_SachActionPerformed

    private void btn_Update_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Update_SachActionPerformed
        int check = 0;
        if(txt_maSach.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập MÃ SÁCH để sửa!");
            txt_maSach.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "UPDATE Sach SET TenSach = ?, MaDanhMucSach = ?, "
                    + "MaTheLoai = ?, TacGia = ?, maNhaXB = ?, NamXB = ?, SoLuong = ?,"
                    + " NoiDung= ? WHERE MaSach = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_tenSach.getText());
            st.setString(2, (String) cbx_maDanhMuc.getSelectedItem());
            st.setString(3, (String) cbx_maTheLoai.getSelectedItem());
            st.setString(4,txt_tacGia.getText());
            st.setString(5,(String)cbx_maNhaXuatBan.getSelectedItem());
            st.setString(6,txt_namXuatBan.getText());
            st.setString(7,txt_soLuongCon.getText());
            st.setString(8,txAre_tomTatND.getText());
            st.setString(9,txt_maSach.getText());

            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Nhập MÃ SÁCH SAI!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Không sửa được!");
        }
        try {
            LoadDatatoArrayList();
        } catch (Exception ex) {
            Logger.getLogger(AdminJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadDataArrayttoTable();
    }//GEN-LAST:event_btn_Update_SachActionPerformed

    private void btn_delete_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_SachActionPerformed
        int check=0;
        if(txt_maSach.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập MÃ SÁCH để xóa!");
            txt_maSach.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "DELETE FROM SACH WHERE MaSach = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maSach.getText());
            check=st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Nhập MÃ SÁCH SAI!");

            connection.close();
            loadTuClick_tableSach(current--);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
        try {
            LoadDatatoArrayList();
        } catch (Exception ex) {
            Logger.getLogger(AdminJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadDataArrayttoTable();
    }//GEN-LAST:event_btn_delete_SachActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();    
        new LiberyJFrame().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_timKiemSach_qlSach_AdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemSach_qlSach_AdminActionPerformed
        
    }//GEN-LAST:event_txt_timKiemSach_qlSach_AdminActionPerformed
    
    private void txt_timKiemSach_qlSach_AdminKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemSach_qlSach_AdminKeyReleased
        arrSach.clear();
        int soluongs = 10;
        try {
            Connection connection= Connect_SQL.openConnection();
            if(txt_timKiemSach_qlSach_Admin.getText().isEmpty()){
                soluongs = 10;
            }
            else if (txt_timKiemSach_qlSach_Admin.getText().chars().allMatch(Character::isDigit)) {
                soluongs = Integer.parseInt(txt_timKiemSach_qlSach_Admin.getText());
            }
            String sql = "SELECT * FROM SACH WHERE MaSach LIKE N'%" + txt_timKiemSach_qlSach_Admin.getText()
            + "%' OR TenSach LIKE N'%" + txt_timKiemSach_qlSach_Admin.getText()
            + "%' OR MaDanhMucSach LIKE N'%" + txt_timKiemSach_qlSach_Admin.getText()
            + "%' OR MaTheLoai LIKE N'%" + txt_timKiemSach_qlSach_Admin.getText()
            + "%' OR TacGia LIKE N'%" + txt_timKiemSach_qlSach_Admin.getText()
            + "%' OR MaNhaXB LIKE N'%" + txt_timKiemSach_qlSach_Admin.getText()
            + "%' OR SoLuong = " + soluongs;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
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
                Sach sach =  new Sach(masach, tensach, madanhmucsach, matheloai, tacgia, nxb, namxuatban, soluong, nd);
                arrSach.add(sach);
            }
            connection.close();
            LoadDataArrayttoTable();
        } catch (Exception e) {
            System.out.println("lõi ssssssssssssss");
        }
    }//GEN-LAST:event_txt_timKiemSach_qlSach_AdminKeyReleased

    private void btn_lamMoi_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoi_SachActionPerformed
       txt_maSach.setText("");
        txt_tenSach.setText("");
        cbx_maDanhMuc.setSelectedIndex(-1);
        cbx_maTheLoai.setSelectedIndex(-1);
        txt_soLuongCon.setText("");
        txt_namXuatBan.setText("");
        cbx_maNhaXuatBan.setSelectedIndex(-1);
        txt_tacGia.setText("");
        txAre_tomTatND.setText("");
    }//GEN-LAST:event_btn_lamMoi_SachActionPerformed
 
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
         
    }//GEN-LAST:event_formComponentShown

    private void btn_add_DanhMuc_QLDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_DanhMuc_QLDMActionPerformed
        // TODO add your handling code here:
        int check = 0;
        if(txt_tenDanhMuc_QLDM.getText().equals("") || txt_madDanhMuc_QLDM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bắt buộc nhập đầy đủ thông!");
            txt_madDanhMuc_QLDM.requestFocus();
            txt_tenDanhMuc_QLDM.requestFocus();
            return;
        } else {
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "INSERT INTO DanhMucSach values (?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_madDanhMuc_QLDM.getText());
            st.setString(2,txt_tenDanhMuc_QLDM.getText());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
            else
                JOptionPane.showMessageDialog(this, "Không thêm được!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Không Thêm dữ liệu được!");
        }
        LoadDatatoArrayList_tableDanhMuc();
        LoadDataArrayttoTable_tableDanhMuc();
    }//GEN-LAST:event_btn_add_DanhMuc_QLDMActionPerformed

    private void btn_Update_DanhMuc_QLDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Update_DanhMuc_QLDMActionPerformed
        int check = 0;
        if(txt_tenDanhMuc_QLDM.getText().equals("") || txt_madDanhMuc_QLDM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bắt buộc nhập đầy đủ thông!");
            txt_madDanhMuc_QLDM.requestFocus();
            txt_tenDanhMuc_QLDM.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "UPDATE DanhMucSach SET TenDMSach = ? WHERE MaDanhMucSach = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_tenDanhMuc_QLDM.getText());
            st.setString(2,txt_madDanhMuc_QLDM.getText());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Không sửa được!");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Không sửa được!");
        }
        LoadDatatoArrayList_tableDanhMuc();
        LoadDataArrayttoTable_tableDanhMuc();
    }//GEN-LAST:event_btn_Update_DanhMuc_QLDMActionPerformed

    private void btn_delete_DanhMuc_QLDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_DanhMuc_QLDMActionPerformed
        int check = 0;
        if(txt_madDanhMuc_QLDM.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Bắt buộc nhập MÃ DANH MỤC để xóa!");
            txt_madDanhMuc_QLDM.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "DELETE FROM DanhMucSach WHERE MaDanhMucSach = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_madDanhMuc_QLDM.getText());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Không xóa được!");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Xảy ra lỗi!");
        }
        LoadDatatoArrayList_tableDanhMuc();
        LoadDataArrayttoTable_tableDanhMuc();
    }//GEN-LAST:event_btn_delete_DanhMuc_QLDMActionPerformed

    private void txt_timKiemDanhMuc_QLDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemDanhMuc_QLDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemDanhMuc_QLDMActionPerformed

    private void txt_timKiemDanhMuc_QLDMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemDanhMuc_QLDMKeyReleased
        arrDanhMuc.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM DanhMucSach WHERE MaDanhMucSach LIKE N'%"+txt_timKiemDanhMuc_QLDM.getText()
                    + "%' OR TenDMSach LIKE N'%"+txt_timKiemDanhMuc_QLDM.getText()+"%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String madanhmucsach = rs.getString(1);
                String tendanhmucsach =rs.getString(2);
                DanhMucSach danhmuc_sach = new DanhMucSach(madanhmucsach, tendanhmucsach);
                arrDanhMuc.add(danhmuc_sach);
            }
            connection.close();
            LoadDataArrayttoTable_tableDanhMuc();
        } catch (Exception e) {
            System.out.println("lõi ssssssssssssss");
        }
        
    }//GEN-LAST:event_txt_timKiemDanhMuc_QLDMKeyReleased
    
    private void Table_QuanLyDanhMuc_AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_QuanLyDanhMuc_AdminMouseClicked
        // TODO add your handling code here:
        current=Table_QuanLyDanhMuc_Admin.getSelectedRow();
        loadTuClick_tableDanhMucSach(current);
    }//GEN-LAST:event_Table_QuanLyDanhMuc_AdminMouseClicked

    private void btn_lamMoi_DAnhMuc_QLDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoi_DAnhMuc_QLDMActionPerformed
        txt_madDanhMuc_QLDM.setText("");
        txt_tenDanhMuc_QLDM.setText("");
        txt_timKiemDanhMuc_QLDM.setText("");
    }//GEN-LAST:event_btn_lamMoi_DAnhMuc_QLDMActionPerformed

    private void cbx_maTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_maTheLoaiActionPerformed
        String sql = "SELECT TenTheLoai FROM TheLoai WHERE MaTheLoai = ?";
        try{
            Connection connection = Connect_SQL.openConnection();
            String maTheLoaiString = (String) cbx_maTheLoai.getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                txt_tenTheLoai_QLSach.setText(rs.getString("tenTheLoai"));
            }
        } catch(Exception e){
            System.out.println("lỗi");
        }
    }//GEN-LAST:event_cbx_maTheLoaiActionPerformed

    private void cbx_maDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_maDanhMucActionPerformed
        String sql = "SELECT TenDMSach FROM DanhMucSach WHERE MaDanhMucSach = ?";
        try{
            Connection connection = Connect_SQL.openConnection();
            String maDMString = (String) cbx_maDanhMuc.getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maDMString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                txt_tenDanhMuc_QLSach.setText(rs.getString("TenDMSach"));
            }
        } catch(Exception e){
            System.out.println("lỗi");
        }
    }//GEN-LAST:event_cbx_maDanhMucActionPerformed

    private void btn_add_Sach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_Sach1ActionPerformed
        int check = 0;
        if(txt_maSinhVien_QLSV.getText().equals("")|| txt_tenSinhVien_QLSV.getText().equals("") ||  txt_emailSinhvien_QLSV.getText().equals("")||  txt_ngaySinhSV_QLSV.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Hãy nhập đâ đủ thông tin!");
            txt_maSinhVien_QLSV.requestFocus();
            return;
        }
        String gioitinh = "";
        if(rdo_gtNam.isSelected())
            gioitinh = "Nam";
        else 
            gioitinh = "Nữ";
        
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "INSERT INTO DocGia values (?,?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maSinhVien_QLSV.getText());
            st.setString(2,Encryptor.encryptString( txt_maSinhVien_QLSV.getText())); //lấy mk là mssv. mặc định
            st.setString(3,txt_tenSinhVien_QLSV.getText());
            st.setString(4,txt_ngaySinhSV_QLSV.getText());
            st.setString(5, gioitinh);
            st.setString(6,txt_emailSinhvien_QLSV.getText());
            st.setInt(7,0);
            st.setString(8,"Bình thường");
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
            else
                JOptionPane.showMessageDialog(this, "Trùng mã độc giả. Nhập lại!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Không thêm được!");
        }
        LoadDatatoArrayList_tableSinHVien();
        LoadDataArrayttoTable_tableSinhVien();
    }//GEN-LAST:event_btn_add_Sach1ActionPerformed

    private void btn_Update_sinhvien_QLSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Update_sinhvien_QLSVActionPerformed
        int check = 0;
        if(txt_maSinhVien_QLSV.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập  mã độc giả để sửa!");
            txt_maSinhVien_QLSV.requestFocus();
            return;
        }
        String gioitinh = "";
        if(rdo_gtNam.isSelected())
            gioitinh = "Nam";
        else 
            gioitinh = "Nữ";
        
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "UPDATE DocGia SET Ten = ?, NgaySinh = ?, "
                    + "Email = ?, GioiTinh = ? WHERE MaDG = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_tenSinhVien_QLSV.getText());
            st.setString(2,txt_ngaySinhSV_QLSV.getText());
            st.setString(3,txt_emailSinhvien_QLSV.getText());
            st.setString(4,gioitinh);
            st.setString(5,txt_maSinhVien_QLSV.getText());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Nhập mã độc giả sai!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Không sửa được!");
        }
        LoadDatatoArrayList_tableSinHVien();
        LoadDataArrayttoTable_tableSinhVien();
    }//GEN-LAST:event_btn_Update_sinhvien_QLSVActionPerformed

    private void btn_khoaTaiKhoan_QLSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khoaTaiKhoan_QLSVActionPerformed
        int check = 0;
        if(txt_maSinhVien_QLSV.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập mã độc giả để khóa!");
            txt_maSinhVien_QLSV.requestFocus();
            return;
        }
        int result = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn khóa tài khoản?", "Thông báo",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                try {
                    Connection connection = Connect_SQL.openConnection()   ;
                    String sql = "UPDATE DocGia SET TrangThaiTK = ? WHERE MaDG = ?";
                    PreparedStatement st = connection.prepareStatement(sql); 
                    st.setString(1, "Đã khóa");
                    st.setString(2,txt_maSinhVien_QLSV.getText());
                    check = st.executeUpdate();
                    if(check > 0)
                        JOptionPane.showMessageDialog(this, "Khóa thành công!");
                    else
                        JOptionPane.showMessageDialog(this, "Nhập mã độc giả sai!");
                    connection.close();
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, "Không khóa được!");
                }
            }
        LoadDatatoArrayList_tableSinHVien();
        LoadDataArrayttoTable_tableSinhVien();
    }//GEN-LAST:event_btn_khoaTaiKhoan_QLSVActionPerformed

    private void btn_moTaiKhoan_QLSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moTaiKhoan_QLSVActionPerformed
        int check = 0;
        if(txt_maSinhVien_QLSV.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập mã độc giả để mở khóa!");
            txt_maSinhVien_QLSV.requestFocus();
            return;
        }
        int result = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn mở khóa tài khoản?", "Thông báo",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                try {
                    Connection connection = Connect_SQL.openConnection()   ;
                    String sql = "UPDATE DocGia SET TrangThaiTK = ? WHERE MaDG = ?";
                    PreparedStatement st = connection.prepareStatement(sql);
                    st.setString(1, "Bình thường");
                    st.setString(2,txt_maSinhVien_QLSV.getText());
                    check = st.executeUpdate();
                    if(check > 0)
                        JOptionPane.showMessageDialog(this, "Đã mở khóa!");
                    else
                        JOptionPane.showMessageDialog(this, "Nhập mã độc giả sai!");
                    connection.close();
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, "Không Mở khóa được!");
                }
            }
        LoadDatatoArrayList_tableSinHVien();
        LoadDataArrayttoTable_tableSinhVien();
    }//GEN-LAST:event_btn_moTaiKhoan_QLSVActionPerformed

    private void Table_QuanLySinhVien_AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_QuanLySinhVien_AdminMouseClicked
        current=Table_QuanLySinhVien_Admin.getSelectedRow();
        loadTuClick_tableSinhVien_QLSV(current);
    }//GEN-LAST:event_Table_QuanLySinhVien_AdminMouseClicked

    private void Table_QuanLySinhVien_AdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_QuanLySinhVien_AdminKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_QuanLySinhVien_AdminKeyPressed

    private void Table_QuanLySinhVien_AdminKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_QuanLySinhVien_AdminKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_QuanLySinhVien_AdminKeyReleased

    private void btn_delete_SinhVien_QLSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_SinhVien_QLSVActionPerformed
        int check = 0;
        if(txt_maSinhVien_QLSV.getText().equals("")|| txt_tenSinhVien_QLSV.getText().equals("") ||  txt_emailSinhvien_QLSV.getText().equals("")||  txt_ngaySinhSV_QLSV.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Hãy nhập đâ đủ thông tin!");
            txt_maSinhVien_QLSV.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "DELETE FROM DocGia WHERE MaDG = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maSinhVien_QLSV.getText());
            check=st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Nhập MÃ độc giả SAI!");
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
        LoadDatatoArrayList_tableSinHVien();
        LoadDataArrayttoTable_tableSinhVien();
    }//GEN-LAST:event_btn_delete_SinhVien_QLSVActionPerformed

    private void txt_timKiemSinhVien_QLSV_Admin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemSinhVien_QLSV_Admin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemSinhVien_QLSV_Admin1ActionPerformed
    
    private void txt_timKiemSinhVien_QLSV_Admin1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemSinhVien_QLSV_Admin1KeyReleased
        arrSinhVien.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM DocGia WHERE MaDG LIKE N'%"+ txt_timKiemSinhVien_QLSV_Admin1.getText()
                    + "%' OR Ten LIKE N'%"+txt_timKiemSinhVien_QLSV_Admin1.getText()+"%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String masv = rs.getString("MaDG");
                String tensv =rs.getString("Ten");
                String ngaysinhsv = rs.getString("NgaySinh");
                String gioitinhsv = rs.getString("GioiTinh");
                String emailsv = rs.getString("Email");
                int soluongmuon = rs.getInt("SoLuongMuon");
                String trangthaitk=rs.getString("TrangThaiTK");
                DocGia sinhvien = new DocGia(masv, tensv, ngaysinhsv, gioitinhsv, emailsv,soluongmuon,trangthaitk);
                arrSinhVien.add(sinhvien);
            }
            connection.close();
            LoadDataArrayttoTable_tableSinhVien();
        } catch (Exception e) {
            System.out.println("lõi ssssssssssssss");
        }
    }//GEN-LAST:event_txt_timKiemSinhVien_QLSV_Admin1KeyReleased

    private void Table_TraCuuSinhVien_QuanLySinhVien_Admin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_TraCuuSinhVien_QuanLySinhVien_Admin1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_TraCuuSinhVien_QuanLySinhVien_Admin1MouseClicked

    private void Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyPressed

    private void Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_TraCuuSinhVien_QuanLySinhVien_Admin1KeyReleased

    private void txt_ngaySinhSV_QLSVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ngaySinhSV_QLSVFocusLost
        String dateString = txt_ngaySinhSV_QLSV.getText();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
        try {df.parse(dateString); // parse dateString thành kiểu Date
        }
        catch (ParseException e) { 
           JOptionPane.showMessageDialog(this, "Sai định dạng! Nhâp lại! (VD: 2001-01-01");
           txt_ngaySinhSV_QLSV.requestFocus();
           txt_ngaySinhSV_QLSV.setText("");
        }
    }//GEN-LAST:event_txt_ngaySinhSV_QLSVFocusLost

    private void Table_TraCuu_Admin_sMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_TraCuu_Admin_sMouseClicked
        
    }//GEN-LAST:event_Table_TraCuu_Admin_sMouseClicked

    private void txt_timKiemSach_TraCuu_Admin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemSach_TraCuu_Admin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemSach_TraCuu_Admin1ActionPerformed

    private void txt_timKiemSach_TraCuu_Admin1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemSach_TraCuu_Admin1KeyReleased
        arrSach.clear();
        int soluongs = 10;
        try {
            Connection connection= Connect_SQL.openConnection();
            if(txt_timKiemSach_TraCuu_Admin1.getText().isEmpty()){
                soluongs = 10;
            }
            else if (txt_timKiemSach_TraCuu_Admin1.getText().chars().allMatch(Character::isDigit)) {
                soluongs = Integer.parseInt(txt_timKiemSach_TraCuu_Admin1.getText());
            }
            String sql = "SELECT * FROM SACH WHERE MaSach LIKE N'%" + txt_timKiemSach_TraCuu_Admin1.getText()
            + "%' OR TenSach LIKE N'%" + txt_timKiemSach_TraCuu_Admin1.getText()
            + "%' OR MaDanhMucSach LIKE N'%" + txt_timKiemSach_TraCuu_Admin1.getText()
            + "%' OR MaTheLoai LIKE N'%" + txt_timKiemSach_TraCuu_Admin1.getText()
            + "%' OR TacGia LIKE N'%" + txt_timKiemSach_TraCuu_Admin1.getText()
            + "%' OR MaNhaXB LIKE N'%" + txt_timKiemSach_TraCuu_Admin1.getText()
            + "%' OR SoLuong = " + soluongs;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
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
                Sach sach =  new Sach(masach, tensach, madanhmucsach, matheloai, tacgia, nxb, namxuatban, soluong, nd);
                arrSach.add(sach);
            }
            connection.close();
            LoadDataArrayttoTable();
        } catch (Exception e) {System.out.println("lõi ssssssssssssss");}
    }//GEN-LAST:event_txt_timKiemSach_TraCuu_Admin1KeyReleased

    private void btn_lamMoi_QLSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoi_QLSVActionPerformed
        txt_maSinhVien_QLSV.setText("");
        txt_tenSinhVien_QLSV.setText("");
        txt_ngaySinhSV_QLSV.setText("");
        txt_emailSinhvien_QLSV.setText("");
        
    }//GEN-LAST:event_btn_lamMoi_QLSVActionPerformed

    private void btn_timkiem_DSMuon_QLPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiem_DSMuon_QLPMActionPerformed
        arrPhieuMuon.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM PhieuMuon WHERE TrangThai LIKE N'%"+ cbx_trangThaiPhieuMuon_QLPM.getSelectedItem()+ "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maPM = rs.getString("MaPM");
                String ngayMuon = rs.getString("NgayMuon");
                String masv = rs.getString("MaDG");
                String maql = rs.getString("MaQL");
                String ghichu = rs.getString("GhiChu");
                String trangthai=rs.getString("TrangThai");
                PhieuMuon phieumuon = new PhieuMuon(maPM, ngayMuon, masv, maql, ghichu,trangthai);
                arrPhieuMuon.add(phieumuon);
            }
            connection.close();
            LoadDataArrayttoTable_tablePhieuMuon();
        } catch (Exception e) {
            System.out.println("lõi ssssssssssssss");
        }
    }//GEN-LAST:event_btn_timkiem_DSMuon_QLPMActionPerformed

    private void btn_timkiem_CTPM_QLPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiem_CTPM_QLPMActionPerformed
        arrCTPM.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM ChiTietPhieuMuon WHERE TinhTrangSach LIKE N'%"+ cbx_tinhtrangsacPhieuMuon_QLPM1.getSelectedItem()+ "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maPM = rs.getString("MaPM");
                String masach = rs.getString("MaSach");
                String ngaytra = rs.getString("NgayTra");
                int tienphat = rs.getInt("TienPhat");
                String tinhtrangsach = rs.getString("TinhTrangSach");
                ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(maPM, masach, ngaytra, tienphat, tinhtrangsach);
                arrCTPM.add(ctpm);
            }
            connection.close();
            LoadDataArrayttoTable_tableChiTietPhieuMuon();
        } catch (Exception e) {
            System.out.println("lõi ssssssssssssss");
        }
    }//GEN-LAST:event_btn_timkiem_CTPM_QLPMActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        try {
            new AdminJFrame_QLPM().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_timKiemNhaXuatBAn_QNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemNhaXuatBAn_QNXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemNhaXuatBAn_QNXBActionPerformed

    private void txt_timKiemNhaXuatBAn_QNXBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemNhaXuatBAn_QNXBKeyReleased
       arrNXB.clear();
        try {
            Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM NhaXuatBan WHERE MaNhaXB LIKE '%"+txt_timKiemNhaXuatBAn_QNXB.getText()+"%' OR TenNhaXuatBan LIKE '%"+txt_timKiemNhaXuatBAn_QNXB.getText()+"%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String manhaxuatban = rs.getString(1);
                String tennhaxuatban =rs.getString(2);
                NhaXuatBan nxb = new NhaXuatBan(manhaxuatban, tennhaxuatban);
                arrNXB.add(nxb);
            }
            connection.close();
            LoadDataArrayttoTable_tableNhaXuatBAn();
        } catch (Exception e) { System.out.println("lõi ssssssssssssss");}
    }//GEN-LAST:event_txt_timKiemNhaXuatBAn_QNXBKeyReleased

    private void btn_add_NXB_QLNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_NXB_QLNXBActionPerformed
        int check = 0;
        if(txt_maNhaXuatrBan_QLNXB.getText().equals("") || txt_tenNhXuatBan_QLNXB.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bắt buộc nhập đầy đủ thông!");
            txt_maNhaXuatrBan_QLNXB.requestFocus();
            return;
        } else {
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "INSERT INTO NhaXuatBan values (?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maNhaXuatrBan_QLNXB.getText());
            st.setString(2,txt_tenNhXuatBan_QLNXB.getText());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
            else
                JOptionPane.showMessageDialog(this, "Không thêm được!");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Không Thêm dữ liệu được!");
        }
        LoadDatatoArrayList_TableNhaXuatBAn();
        LoadDataArrayttoTable_tableNhaXuatBAn();
    }//GEN-LAST:event_btn_add_NXB_QLNXBActionPerformed

    private void btn_Update_NXB_QLNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Update_NXB_QLNXBActionPerformed
       int check = 0;
        if(txt_maNhaXuatrBan_QLNXB.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập  mã nhà xuất để sửa!");
            txt_maNhaXuatrBan_QLNXB.requestFocus();
            return;
        }
       
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "UPDATE NhaXuatBan SET TenNhaXuatBan = ? WHERE MaNhaXB = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_tenNhXuatBan_QLNXB.getText());
            st.setString(2,txt_maNhaXuatrBan_QLNXB.getText());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Nhập mã nhà xuất bản sai!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Nhập mã nhà xuất bản sai!");
        }
        LoadDatatoArrayList_TableNhaXuatBAn();
        LoadDataArrayttoTable_tableNhaXuatBAn();
    }//GEN-LAST:event_btn_Update_NXB_QLNXBActionPerformed

    private void btn_lamMoi_NXB_QLNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoi_NXB_QLNXBActionPerformed
        txt_maNhaXuatrBan_QLNXB.setText("");
        txt_tenNhXuatBan_QLNXB.setText("");
    }//GEN-LAST:event_btn_lamMoi_NXB_QLNXBActionPerformed

    private void btn_delete_NXB_QLNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_NXB_QLNXBActionPerformed
         int check = 0;
        if(txt_maNhaXuatrBan_QLNXB.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Bắt buộc nhập MÃ DANH MỤC để xóa!");
            txt_maNhaXuatrBan_QLNXB.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "DELETE FROM NhaXuatBan WHERE MaNhaXB = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maNhaXuatrBan_QLNXB.getText());
            check = st.executeUpdate();
            if(check > 0) JOptionPane.showMessageDialog(this, "Xóa thành công!");
            else JOptionPane.showMessageDialog(this, "Không xóa được!");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Xảy ra lỗi!");
        }
        LoadDatatoArrayList_TableNhaXuatBAn();
        LoadDataArrayttoTable_tableNhaXuatBAn();
    }//GEN-LAST:event_btn_delete_NXB_QLNXBActionPerformed
    public void loadTuClick_tableNhaXuatBan_QLNXB(int row){
         NhaXuatBan nxb = arrNXB.get(row);
        txt_maNhaXuatrBan_QLNXB.setText(nxb.getMaNhaXuatBan());
        txt_tenNhXuatBan_QLNXB.setText(nxb.getTenNhaXuatBan());
    }
    private void Table_QuanLyNHaXuatBAn_AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_QuanLyNHaXuatBAn_AdminMouseClicked
        current=Table_QuanLyNHaXuatBAn_Admin.getSelectedRow();
        loadTuClick_tableNhaXuatBan_QLNXB(current);
    }//GEN-LAST:event_Table_QuanLyNHaXuatBAn_AdminMouseClicked

    private void cbx_maNhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_maNhaXuatBanActionPerformed
       String sql = "SELECT TenNhaXuatBan FROM NhaXuatBan WHERE maNhaXB = ?";
        try{
            Connection connection = Connect_SQL.openConnection();
            String maTheLoaiString = (String) cbx_maNhaXuatBan.getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                txt_tenNhaXb.setText(rs.getString("TenNhaXuatBan"));
        } catch(Exception e){
            System.out.println("lỗi");
        }
    }//GEN-LAST:event_cbx_maNhaXuatBanActionPerformed

    private void cbx_soThangDOCGIA_ThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_soThangDOCGIA_ThongKeActionPerformed
        arrSinhVien.clear();
        model2=(DefaultTableModel) Table_ThongKeSinhVien_ThongKe.getModel();
        int chon = cbx_soThangDOCGIA_ThongKe.getSelectedIndex();
        DocGia_DAO dao_docgia = new DocGia_DAO() ;
        try {
            if(chon == 0 || chon == 1 || chon == 2 || chon == 3 || chon == 4 ||chon == 5|| chon == 6|| chon == 7|| chon == 8 || chon == 9 || chon == 10 || chon == 11 ){
                List<DocGia> s = dao_docgia.ThongTinDocGiaMuonTrongThang(chon+1);
                model2.setRowCount(0);
                for(DocGia sinhvien : s){
                model2.addRow(new Object[]{ 
                            sinhvien.getMaDG(),sinhvien.getTenDG(),sinhvien.getNgaySinh(),
                sinhvien.getGioiTinh(),sinhvien.getEmail(),sinhvien.getTrangThaiTK() }); 
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }  
    }//GEN-LAST:event_cbx_soThangDOCGIA_ThongKeActionPerformed
    
    private void cbx_sothangSACH_THONGKEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_sothangSACH_THONGKEActionPerformed
        arrSach.clear();
        model2=(DefaultTableModel) Table_ThongKeSach_ThongKe.getModel();
        int chon = cbx_sothangSACH_THONGKE.getSelectedIndex();
        Sach_DAO daosach = new Sach_DAO() ;
        try {
            if(chon == 0 || chon == 1 || chon == 2 || chon == 3 || chon == 4 ||chon == 5|| chon == 6|| chon == 7|| chon == 8 || chon == 9 || chon == 10 || chon == 11 ){
                List<Sach> s = daosach.SachDuocMuonTrongThang(chon+1);
                model2.setRowCount(0);
                for(Sach sach : s){
                    model2.addRow(new Object[]{ sach.getMaSach(),sach.getTenSach(),sach.getMaDMSach(),sach.getMaTheLoai(),sach.getTacGia(),sach.getNXB(),sach.getNamXuatBan(),sach.getSoLuongCon(),sach.getTomTatND()  } );
               }
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbx_sothangSACH_THONGKEActionPerformed

    private void cbx_sothanTienPhat_THONGKEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_sothanTienPhat_THONGKEActionPerformed
        DocGia_DAO dao_docgia = new DocGia_DAO() ;
        List<DocGia> s = null;
        try { s = dao_docgia.ThongTinDocGiaMuonTrongThang_COTIENPHAT(1);} 
        catch (Exception ex) {
            Logger.getLogger(AdminJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.clear();
        model2=(DefaultTableModel) Table_ThongekeTienPhat_ThongKe.getModel();
        model2.setRowCount(0);
        int chon = cbx_sothanTienPhat_THONGKE.getSelectedIndex();
       
        Sach_DAO daosach  =new Sach_DAO()   ;
        try {
            if(chon == 0 || chon == 1 || chon == 2 || chon == 3 || chon == 4 ||chon == 5|| chon == 6|| chon == 7|| chon == 8 || chon == 9 || chon == 10 || chon == 11 ){
                s = dao_docgia.ThongTinDocGiaMuonTrongThang_COTIENPHAT(chon+1);
                for(DocGia sinhvien : s){
                model2.addRow(new Object[]{ sinhvien.getMapm(),sinhvien.getMaDG(),sinhvien.getTenDG(),sinhvien.getNgaySinh(),sinhvien.getGioiTinh(),sinhvien.getEmail(),sinhvien.getTienphat() });  }
                int i = daosach.TongTienPhatTrongThai(chon+1);
                lb_TongTienPhat_ThongKe.setText(""+i);
            } 
            model2.fireTableDataChanged();
        } catch (Exception e) { System.out.println(e);}
    }//GEN-LAST:event_cbx_sothanTienPhat_THONGKEActionPerformed

    private void btn_xemChiTietSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xemChiTietSachActionPerformed
        if(Table_TraCuu_Admin_s.getSelectedRow() >= 0){  String masach=null;
            masach=Table_TraCuu_Admin_s.getValueAt(Table_TraCuu_Admin_s.getSelectedRow(),0).toString();
            Book_Detail chitiet = new Book_Detail(masach.trim());
            chitiet.setVisible(true);
        }
        else JOptionPane.showMessageDialog(this, "Hãy chọn 1 cuốn sách bên dưới để xem thông tin chi tiết");
    }//GEN-LAST:event_btn_xemChiTietSachActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
/*
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new AdminJFrame().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(AdminJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_ChiTietPhieuMuon_QLPM;
    private javax.swing.JTable Table_DanhSachMuon_QLPM;
    private javax.swing.JTable Table_QuanLyDanhMuc_Admin;
    private javax.swing.JTable Table_QuanLyNHaXuatBAn_Admin;
    private javax.swing.JTable Table_QuanLySach_Admin;
    private javax.swing.JTable Table_QuanLySinhVien_Admin;
    private javax.swing.JTable Table_ThongKeSach_ThongKe;
    private javax.swing.JTable Table_ThongKeSinhVien_ThongKe;
    private javax.swing.JTable Table_ThongekeTienPhat_ThongKe;
    private javax.swing.JTable Table_TraCuuSinhVien_QuanLySinhVien_Admin1;
    private javax.swing.JTable Table_TraCuu_Admin_s;
    private javax.swing.ButtonGroup btnGroup_GioiTinh_QLSV;
    private javax.swing.JButton btn_Update_DanhMuc_QLDM;
    private javax.swing.JButton btn_Update_NXB_QLNXB;
    private javax.swing.JButton btn_Update_Sach;
    private javax.swing.JButton btn_Update_sinhvien_QLSV;
    private javax.swing.JButton btn_add_DanhMuc_QLDM;
    private javax.swing.JButton btn_add_NXB_QLNXB;
    private javax.swing.JButton btn_add_Sach;
    private javax.swing.JButton btn_add_Sach1;
    private javax.swing.JButton btn_delete_DanhMuc_QLDM;
    private javax.swing.JButton btn_delete_NXB_QLNXB;
    private javax.swing.JButton btn_delete_Sach;
    private javax.swing.JButton btn_delete_SinhVien_QLSV;
    private javax.swing.JButton btn_khoaTaiKhoan_QLSV;
    private javax.swing.JButton btn_lamMoi_DAnhMuc_QLDM;
    private javax.swing.JButton btn_lamMoi_NXB_QLNXB;
    private javax.swing.JButton btn_lamMoi_QLSV;
    private javax.swing.JButton btn_lamMoi_Sach;
    private javax.swing.JButton btn_moTaiKhoan_QLSV;
    private javax.swing.JButton btn_timkiem_CTPM_QLPM;
    private javax.swing.JButton btn_timkiem_DSMuon_QLPM;
    private javax.swing.JButton btn_xemChiTietSach;
    private javax.swing.JComboBox<String> cbx_maDanhMuc;
    private javax.swing.JComboBox<String> cbx_maNhaXuatBan;
    private javax.swing.JComboBox<String> cbx_maTheLoai;
    private javax.swing.JComboBox<String> cbx_soThangDOCGIA_ThongKe;
    private javax.swing.JComboBox<String> cbx_sothanTienPhat_THONGKE;
    private javax.swing.JComboBox<String> cbx_sothangSACH_THONGKE;
    private javax.swing.JComboBox<String> cbx_tinhtrangsacPhieuMuon_QLPM1;
    private javax.swing.JComboBox<String> cbx_trangThaiPhieuMuon_QLPM;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb_TongTienPhat_ThongKe;
    private javax.swing.JRadioButton rdo_gtNam;
    private javax.swing.JRadioButton rdo_gtNu;
    private javax.swing.JTextArea txAre_tomTatND;
    private javax.swing.JTextField txt_emailSinhvien_QLSV;
    private javax.swing.JTextField txt_maNhaXuatrBan_QLNXB;
    private javax.swing.JTextField txt_maSach;
    private javax.swing.JTextField txt_maSinhVien_QLSV;
    private javax.swing.JTextField txt_madDanhMuc_QLDM;
    private javax.swing.JTextField txt_namXuatBan;
    private javax.swing.JTextField txt_ngaySinhSV_QLSV;
    private javax.swing.JTextField txt_soLuongCon;
    private javax.swing.JTextField txt_tacGia;
    private javax.swing.JTextField txt_tenDanhMuc_QLDM;
    private javax.swing.JTextField txt_tenDanhMuc_QLSach;
    private javax.swing.JTextField txt_tenNhXuatBan_QLNXB;
    private javax.swing.JTextField txt_tenNhaXb;
    private javax.swing.JTextField txt_tenSach;
    private javax.swing.JTextField txt_tenSinhVien_QLSV;
    private javax.swing.JTextField txt_tenTheLoai_QLSach;
    private javax.swing.JTextField txt_timKiemDanhMuc_QLDM;
    private javax.swing.JTextField txt_timKiemNhaXuatBAn_QNXB;
    private javax.swing.JTextField txt_timKiemSach_TraCuu_Admin1;
    private javax.swing.JTextField txt_timKiemSach_qlSach_Admin;
    private javax.swing.JTextField txt_timKiemSinhVien_QLSV_Admin1;
    // End of variables declaration//GEN-END:variables
}
