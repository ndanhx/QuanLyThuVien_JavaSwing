/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Model.PhieuMuon;
import Model.ChiTietPhieuMuon;
import DAO.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author Home
 */
public class AdminJFrame_QLPM extends javax.swing.JFrame {

    int current =0;
    ArrayList<PhieuMuon> arrPhieuMuon = new ArrayList<>();
    ArrayList<ChiTietPhieuMuon> arrCTPM = new ArrayList<>();
    String titleTraCuuHome[]= {};
    DefaultTableModel model =  new DefaultTableModel(titleTraCuuHome,0);
    public AdminJFrame_QLPM() throws Exception {
        initComponents();
        load_MaQuanLy();
        LoadDatatoArrayList_tablePhieuMuon(); 
        LoadDataArrayttoTable_tablePhieuMuon(); 
        LoadDatatoArrayList_tableChiTietPhieuMuon(); 
        LoadDataArrayttoTable_tableChiTietPhieuMuon(); 
        
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
        model = (DefaultTableModel) Table_PhieUmUON.getModel();
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
        model = (DefaultTableModel) Table_ChiTietPhieuMuon.getModel();
        model.setRowCount(0);
        for(ChiTietPhieuMuon ctpm : arrCTPM){
            model.addRow(new Object[]{ 
                           ctpm.getMaPM(),ctpm.getMaSach(),ctpm.getNgayThucTra(),ctpm.getTienPhat(),ctpm.getTinhTrangSach() } );
        }
        TableColumnModel columnModel = Table_ChiTietPhieuMuon.getColumnModel();
        TableColumn cot1masach = columnModel.getColumn(1);
        cot1masach.setPreferredWidth(120);
    }
    public void loadTuClick_tablePM(int row){
        PhieuMuon pm = arrPhieuMuon.get(row);
        txt_maPM.setText(pm.getMaPM());
        txt_maSv.setText(pm.getMaDG_PM());
        txt_ngaymuon.setText(pm.getNgayMuon());
        cbx_soNgayMuon.setSelectedItem(pm.getGhiChu());
        cbx_trangThaiPM.setSelectedItem(pm.getTrangThai());
        cbx_maQL.setSelectedItem(pm.getMaQuanLy());
       
    }
    public void loadTuClick_tableChiTietPhieuMuon(int row){
        ChiTietPhieuMuon ctpm = arrCTPM.get(row);
        txt_maPM_CTPM.setText(ctpm.getMaPM());
        txt_maSach_CTPM.setText(ctpm.getMaSach());
        txt_ngayTra_CTPm.setText(ctpm.getNgayThucTra());
        cbx_tinhTrangSach_CtPm.setSelectedItem(ctpm.getTinhTrangSach());
        txt_tienPhat_CTPM.setText(""+ ctpm.getTienPhat());
   }
    public void load_MaQuanLy() throws Exception{
        cbx_maQL.removeAllItems();
        try{
           Connection connection  =  Connect_SQL.openConnection();
           String sql = "SELECT MaQL FROM QuanLy";
           Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                this.cbx_maQL.addItem(rs.getString("MaQL"));
            }
        }catch(Exception e){
            System.out.println("lỗi sql nha");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_maPM_CTPM = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_maSach_CTPM = new javax.swing.JTextField();
        txt_ngayTra_CTPm = new javax.swing.JTextField();
        cbx_tinhTrangSach_CtPm = new javax.swing.JComboBox<>();
        btn_add_CTPM = new javax.swing.JButton();
        btn_Update_CTPM = new javax.swing.JButton();
        btn_lamMoi_CTPM = new javax.swing.JButton();
        btn_delete_CTPM = new javax.swing.JButton();
        txt_tienPhat_CTPM = new javax.swing.JTextField();
        btn_tinhTienPhat_CTPM = new javax.swing.JButton();
        lb_tbsoNgay = new java.awt.Label();
        txt_songaymuon_ctpm = new javax.swing.JLabel();
        txt_setlaNgay = new javax.swing.JLabel();
        txt_setlaMuon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_maPM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_maSv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbx_soNgayMuon = new javax.swing.JComboBox<>();
        cbx_trangThaiPM = new javax.swing.JComboBox<>();
        txt_ngaymuon = new javax.swing.JTextField();
        cbx_maQL = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btn_add_PM = new javax.swing.JButton();
        btn_Update_PM = new javax.swing.JButton();
        btn_lamMoi_Sach = new javax.swing.JButton();
        btn_delete_PM = new javax.swing.JButton();
        txt_tenquanly = new javax.swing.JTextField();
        btn_back_pm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_ChiTietPhieuMuon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_PhieUmUON = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý phiếu mượn");
        setBackground(new java.awt.Color(255, 255, 204));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("CHI TIẾT PHIẾU MƯỢN");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Mã PM:");

        txt_maPM_CTPM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_maPM_CTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maPM_CTPMActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Mã ISBN:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Ngày trả:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Tình trạng sách:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 255));
        jLabel14.setText("*Tiền phạt:  - Trể 1 ngày là 10.000Đ");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 255));
        jLabel15.setText("- Mất sách: tính theo giá trị sách");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 255));
        jLabel16.setText("- Làm hỏng sách là 50.000Đ");

        txt_maSach_CTPM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_ngayTra_CTPm.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_ngayTra_CTPm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ngayTra_CTPmFocusLost(evt);
            }
        });
        txt_ngayTra_CTPm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngayTra_CTPmActionPerformed(evt);
            }
        });

        cbx_tinhTrangSach_CtPm.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        cbx_tinhTrangSach_CtPm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bình thường", "Quá hạn", "Làm hỏng", "Mất sách" }));
        cbx_tinhTrangSach_CtPm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tinhTrangSach_CtPmActionPerformed(evt);
            }
        });

        btn_add_CTPM.setBackground(new java.awt.Color(102, 255, 102));
        btn_add_CTPM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add_CTPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/add-1-icon.png"))); // NOI18N
        btn_add_CTPM.setText("Thêm");
        btn_add_CTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_CTPMActionPerformed(evt);
            }
        });

        btn_Update_CTPM.setBackground(new java.awt.Color(102, 255, 102));
        btn_Update_CTPM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_Update_CTPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/pencil-icon.png"))); // NOI18N
        btn_Update_CTPM.setLabel(" Sửa");
        btn_Update_CTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Update_CTPMActionPerformed(evt);
            }
        });

        btn_lamMoi_CTPM.setBackground(new java.awt.Color(102, 255, 102));
        btn_lamMoi_CTPM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_lamMoi_CTPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/Actions-folder-new-icon.png"))); // NOI18N
        btn_lamMoi_CTPM.setText("Làm mới");
        btn_lamMoi_CTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoi_CTPMActionPerformed(evt);
            }
        });

        btn_delete_CTPM.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete_CTPM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete_CTPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/xoa.png"))); // NOI18N
        btn_delete_CTPM.setLabel("Xóa");
        btn_delete_CTPM.setName(""); // NOI18N
        btn_delete_CTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_CTPMActionPerformed(evt);
            }
        });

        txt_tienPhat_CTPM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        btn_tinhTienPhat_CTPM.setBackground(new java.awt.Color(255, 51, 255));
        btn_tinhTienPhat_CTPM.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btn_tinhTienPhat_CTPM.setForeground(new java.awt.Color(153, 255, 255));
        btn_tinhTienPhat_CTPM.setText("Tính tiền phạt:");
        btn_tinhTienPhat_CTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tinhTienPhat_CTPMActionPerformed(evt);
            }
        });

        lb_tbsoNgay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_tbsoNgay.setForeground(new java.awt.Color(0, 0, 255));

        txt_songaymuon_ctpm.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        txt_songaymuon_ctpm.setForeground(new java.awt.Color(255, 51, 51));

        txt_setlaNgay.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        txt_setlaNgay.setForeground(new java.awt.Color(255, 51, 51));

        txt_setlaMuon.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        txt_setlaMuon.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_ngayTra_CTPm, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_tinhTrangSach_CtPm, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_setlaMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_songaymuon_ctpm, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_setlaNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(lb_tbsoNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_maPM_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_maSach_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tinhTienPhat_CTPM)
                        .addGap(12, 12, 12)
                        .addComponent(txt_tienPhat_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add_CTPM)
                .addGap(18, 18, 18)
                .addComponent(btn_Update_CTPM)
                .addGap(24, 24, 24)
                .addComponent(btn_lamMoi_CTPM)
                .addGap(18, 18, 18)
                .addComponent(btn_delete_CTPM)
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_maPM_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienPhat_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tinhTienPhat_CTPM))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_maSach_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_ngayTra_CTPm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lb_tbsoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_tinhTrangSach_CtPm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_setlaNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_songaymuon_ctpm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_setlaMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add_CTPM)
                    .addComponent(btn_lamMoi_CTPM)
                    .addComponent(btn_Update_CTPM)
                    .addComponent(btn_delete_CTPM))
                .addGap(28, 28, 28))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Mã PM:");

        txt_maPM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Mã độc giả:");

        txt_maSv.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Ngày mượn:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Mã quản lý:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Số ngày mượn:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Trạng thại:");

        cbx_soNgayMuon.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_soNgayMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15" }));
        cbx_soNgayMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_soNgayMuonActionPerformed(evt);
            }
        });

        cbx_trangThaiPM.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        cbx_trangThaiPM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa trả", "Đã trả", "chưa duyệt" }));
        cbx_trangThaiPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_trangThaiPMActionPerformed(evt);
            }
        });

        txt_ngaymuon.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_ngaymuon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ngaymuonFocusLost(evt);
            }
        });

        cbx_maQL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_maQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_maQLActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("PHIẾU MƯỢN");

        btn_add_PM.setBackground(new java.awt.Color(102, 255, 102));
        btn_add_PM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add_PM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/add-1-icon.png"))); // NOI18N
        btn_add_PM.setText("Thêm");
        btn_add_PM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_PMActionPerformed(evt);
            }
        });

        btn_Update_PM.setBackground(new java.awt.Color(102, 255, 102));
        btn_Update_PM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_Update_PM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/pencil-icon.png"))); // NOI18N
        btn_Update_PM.setLabel(" Sửa");
        btn_Update_PM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Update_PMActionPerformed(evt);
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

        btn_delete_PM.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete_PM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete_PM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/xoa.png"))); // NOI18N
        btn_delete_PM.setLabel("Xóa");
        btn_delete_PM.setName(""); // NOI18N
        btn_delete_PM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_PMActionPerformed(evt);
            }
        });

        txt_tenquanly.setEditable(false);
        txt_tenquanly.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txt_tenquanly.setForeground(new java.awt.Color(255, 51, 51));

        btn_back_pm.setBackground(new java.awt.Color(204, 255, 255));
        btn_back_pm.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_back_pm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/arrow-return-up-left-icon.png"))); // NOI18N
        btn_back_pm.setText("Trở về");
        btn_back_pm.setToolTipText("");
        btn_back_pm.setActionCommand("Trở về   ");
        btn_back_pm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back_pmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_trangThaiPM, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_soNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_maPM, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(txt_maSv)))
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_maQL, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenquanly, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btn_add_PM)
                .addGap(18, 18, 18)
                .addComponent(btn_Update_PM)
                .addGap(24, 24, 24)
                .addComponent(btn_lamMoi_Sach)
                .addGap(18, 18, 18)
                .addComponent(btn_delete_PM)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btn_back_pm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addComponent(btn_back_pm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_maPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_maQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_maSv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_ngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenquanly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbx_soNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbx_trangThaiPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add_PM)
                    .addComponent(btn_lamMoi_Sach)
                    .addComponent(btn_Update_PM)
                    .addComponent(btn_delete_PM))
                .addGap(27, 27, 27))
        );

        Table_ChiTietPhieuMuon.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_ChiTietPhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu", "Mã ISBN", "Ngày trả", "Tiền phạt", "Tình trạng sách"
            }
        ));
        Table_ChiTietPhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_ChiTietPhieuMuonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_ChiTietPhieuMuon);

        Table_PhieUmUON.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_PhieUmUON.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Ngày mượn", "Mã độc giả", "Mã quản lý", "Ghi chú", "Trạng thái"
            }
        ));
        Table_PhieUmUON.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_PhieUmUONMouseClicked(evt);
            }
        });
        Table_PhieUmUON.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table_PhieUmUONKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Table_PhieUmUON);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbx_soNgayMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_soNgayMuonActionPerformed
        
    }//GEN-LAST:event_cbx_soNgayMuonActionPerformed

    private void cbx_trangThaiPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_trangThaiPMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_trangThaiPMActionPerformed

    private void cbx_maQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_maQLActionPerformed
        String sql = "SELECT Ten FROM QuanLy WHERE MaQL = ?";
        try{
            Connection connection = Connect_SQL.openConnection();
            String maQuanLyString = (String) cbx_maQL.getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maQuanLyString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                txt_tenquanly.setText(rs.getString("Ten"));
            }
        } catch(Exception e){
            System.out.println("lỗi");
        }
    }//GEN-LAST:event_cbx_maQLActionPerformed

    private void cbx_tinhTrangSach_CtPmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tinhTrangSach_CtPmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_tinhTrangSach_CtPmActionPerformed

    private void Table_PhieUmUONKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_PhieUmUONKeyReleased
        
    }//GEN-LAST:event_Table_PhieUmUONKeyReleased

    private void Table_PhieUmUONMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_PhieUmUONMouseClicked
        current=Table_PhieUmUON.getSelectedRow();
        loadTuClick_tablePM(current);
    }//GEN-LAST:event_Table_PhieUmUONMouseClicked

    private void Table_ChiTietPhieuMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_ChiTietPhieuMuonMouseClicked
        current=Table_ChiTietPhieuMuon.getSelectedRow();
        loadTuClick_tableChiTietPhieuMuon(current);
    }//GEN-LAST:event_Table_ChiTietPhieuMuonMouseClicked

    private void btn_add_PMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_PMActionPerformed
        int check = 0;
        if(txt_maPM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bắt buộc nhập mã phiếu mượn!");
            txt_maPM.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "INSERT INTO PhieuMuon values (?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maPM.getText());
            st.setString(2,txt_ngaymuon.getText());
            st.setString(3,txt_maSv.getText());
            st.setString(4, (String) cbx_maQL.getSelectedItem());
            st.setString(5, (String) cbx_soNgayMuon.getSelectedItem());
            st.setString(6, (String) cbx_trangThaiPM.getSelectedItem());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
            else
                JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã SV!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã SV!");
        }
        LoadDatatoArrayList_tablePhieuMuon();
        LoadDataArrayttoTable_tablePhieuMuon();
    }//GEN-LAST:event_btn_add_PMActionPerformed

    private void btn_back_pmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back_pmActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            new AdminJFrame().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminJFrame_QLPM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_back_pmActionPerformed

    private void btn_Update_PMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Update_PMActionPerformed
        int check = 0;
        if(txt_maPM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập mã phiếu mượn để sửa!");
            txt_maPM.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "UPDATE PhieuMuon SET NgayMuon = ?, MaDG = ?, "
                    + "MaQL = ?, GhiChu = ?, TrangThai = ? WHERE MaPM = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_ngaymuon.getText());
            st.setString(2,txt_maSv.getText());
            st.setString(3, (String) cbx_maQL.getSelectedItem());
            st.setString(4, (String) cbx_soNgayMuon.getSelectedItem()); //laf ghi chu
            st.setString(5, (String) cbx_trangThaiPM.getSelectedItem());
            st.setString(6,txt_maPM.getText());
            check = st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã độc giả!");
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã độc giả!");
        }
        LoadDatatoArrayList_tablePhieuMuon();
        LoadDataArrayttoTable_tablePhieuMuon();
    }//GEN-LAST:event_btn_Update_PMActionPerformed

    private void btn_lamMoi_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoi_SachActionPerformed
        txt_maPM.setText("");
        txt_maSv.setText("");
        txt_ngaymuon.setText("");
        cbx_soNgayMuon.setSelectedItem(-1);
        cbx_trangThaiPM.setSelectedItem(-1);
        cbx_maQL.setSelectedItem(-1);
    }//GEN-LAST:event_btn_lamMoi_SachActionPerformed

    private void btn_delete_PMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_PMActionPerformed
        int check=0;
        if(txt_maPM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập mã phiếu mượn để xóa!");
            txt_maPM.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "DELETE FROM PhieuMuon WHERE MaPM = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maPM.getText().trim());
            check=st.executeUpdate();
            if(check > 0)
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            else
                JOptionPane.showMessageDialog(this, "Nhập mã phiếu mượn SAI!");

            connection.close();
            loadTuClick_tablePM(current--);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hãy xóa thông tin bên Chi Tiết Phiếu Mượn trước!");
            System.out.println(e);
        }
        LoadDatatoArrayList_tablePhieuMuon();
        LoadDataArrayttoTable_tablePhieuMuon();
    }//GEN-LAST:event_btn_delete_PMActionPerformed

    private void btn_add_CTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_CTPMActionPerformed
        try {                                             
            int check = 0;
            if(txt_maPM_CTPM.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Bắt buộc nhập mã phiếu mượn!");
                txt_maPM_CTPM.requestFocus();
                return;
            }
            String masach = txt_maSach_CTPM.getText().trim();
            Sach_DAO dao_sach = new Sach_DAO();
            int checksoluong = dao_sach.laySoLuongSach(masach);
            if(checksoluong > 0 ){
                try {
                Connection connection = Connect_SQL.openConnection()   ;
                String sql = "INSERT INTO ChiTietPhieuMuon values (?,?,?,?,?)";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1,txt_maPM_CTPM.getText());
                st.setString(2,txt_maSach_CTPM.getText());
                st.setString(3,txt_ngayTra_CTPm.getText());
                
                st.setString(4,txt_tienPhat_CTPM.getText());
                st.setString(5,"");
                check = st.executeUpdate();
                if(check > 0)
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
                else
                    JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã sách!");
                connection.close();
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã sách!");
                }
                LoadDatatoArrayList_tableChiTietPhieuMuon();
                LoadDataArrayttoTable_tableChiTietPhieuMuon();
            }
            else JOptionPane.showMessageDialog(this, "Sách này đã hết, chọn sách khác!");
          } catch (Exception ex) {Logger.getLogger(AdminJFrame_QLPM.class.getName()).log(Level.SEVERE, null,ex);}
    }//GEN-LAST:event_btn_add_CTPMActionPerformed

    private void btn_Update_CTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Update_CTPMActionPerformed
int check = 0;
        if(txt_maPM_CTPM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập mã phiếu mượn để sửa!");
            txt_maPM_CTPM.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "UPDATE ChiTietPhieuMuon SET MaSach = ?, NgayTra = ?, TienPhat = ?, TinhTrangSach = ? WHERE MaPM = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maSach_CTPM.getText());
            st.setString(2,txt_ngayTra_CTPm.getText());
            st.setString(3,txt_tienPhat_CTPM.getText());
            st.setString(4, (String) cbx_tinhTrangSach_CtPm.getSelectedItem()); 
            st.setString(5,txt_maPM_CTPM.getText());
            check = st.executeUpdate();
            if(check > 0) JOptionPane.showMessageDialog(this, "Sửa thành công!");
            else  JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã sách!");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Kiểm tra lại mã PM hoặc mã sách!");
        }
        LoadDatatoArrayList_tableChiTietPhieuMuon();
        LoadDataArrayttoTable_tableChiTietPhieuMuon();
    }//GEN-LAST:event_btn_Update_CTPMActionPerformed

    private void btn_lamMoi_CTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoi_CTPMActionPerformed
        txt_maPM_CTPM.setText("");
        txt_maSach_CTPM.setText("");
        txt_ngayTra_CTPm.setText("");
        txt_tienPhat_CTPM.setText("");
        cbx_tinhTrangSach_CtPm.setSelectedItem(-1);
    }//GEN-LAST:event_btn_lamMoi_CTPMActionPerformed

    private void btn_delete_CTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_CTPMActionPerformed
        int check=0;
        if(txt_maPM_CTPM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập mã phiếu mượn để xóa!");
            txt_maPM_CTPM.requestFocus();
            return;
        }
        try {
            Connection connection = Connect_SQL.openConnection()   ;
            String sql = "DELETE FROM ChiTietPhieuMuon WHERE MaPM = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,txt_maPM_CTPM.getText().trim());
            check=st.executeUpdate();
            if(check > 0) JOptionPane.showMessageDialog(this, "Xóa thành công!");
            else JOptionPane.showMessageDialog(this, "Nhập mã phiếu mượn SAI!");
            connection.close();
            loadTuClick_tableChiTietPhieuMuon(current--);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập mã phiếu mượn SAI!");
            System.out.println(e);
        }
        LoadDatatoArrayList_tableChiTietPhieuMuon();
        LoadDataArrayttoTable_tableChiTietPhieuMuon();
    }//GEN-LAST:event_btn_delete_CTPMActionPerformed

    private void txt_ngaymuonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ngaymuonFocusLost
        String dateString = txt_ngaymuon.getText();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
        try { df.parse(dateString);} // parse dateString thành kiểu Date
        catch (ParseException e) { 
           JOptionPane.showMessageDialog(this, "Sai định dạng! Nhâp lại! (VD: 2001-01-01");
           txt_ngaymuon.requestFocus();
           txt_ngaymuon.setText("");
        }
    }//GEN-LAST:event_txt_ngaymuonFocusLost

    private void txt_ngayTra_CTPmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngayTra_CTPmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngayTra_CTPmActionPerformed

    private void txt_ngayTra_CTPmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ngayTra_CTPmFocusLost
        String dateString = txt_ngayTra_CTPm.getText();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
        try {
            df.parse(dateString); // parse dateString thành kiểu Date
        }
        catch (ParseException e) { 
           JOptionPane.showMessageDialog(this, "Sai định dạng! Nhâp lại! (VD: 2001-01-01");
           txt_ngayTra_CTPm.requestFocus();
           txt_ngayTra_CTPm.setText("");
        }
    }//GEN-LAST:event_txt_ngayTra_CTPmFocusLost

    private void btn_tinhTienPhat_CTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tinhTienPhat_CTPMActionPerformed
        PhieuMuon_DAO pm_dao = new PhieuMuon_DAO();DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat simpleDateFormat1 = new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 24);
        try {
           String startDate = pm_dao.layNgayMuon(txt_maPM_CTPM.getText());
           String endDate = txt_ngayTra_CTPm.getText();
           String songaymuonne = txt_songaymuon_ctpm.getText() ;
           Date date1 = simpleDateFormat.parse(startDate);
           Date date2 = simpleDateFormat.parse(endDate);
           Date date3 = simpleDateFormat1.parse(songaymuonne);
           int getDiff = (int) ((date2.getTime() - date1.getTime()) - date3.getTime());
           int getDaysDiff = (int) TimeUnit.MILLISECONDS.toDays(getDiff);
           int tienphatne = 0;
           lb_tbsoNgay.setText("*Đã quá hạn "+getDaysDiff+" ngày trả sách ");
           if(cbx_tinhTrangSach_CtPm.getSelectedItem().equals("Quá hạn")){
               tienphatne= getDaysDiff*10000;
               txt_tienPhat_CTPM.setText(""+tienphatne );
           } else if(cbx_tinhTrangSach_CtPm.getSelectedItem().equals("Làm hỏng")){
               tienphatne = 50000;
               txt_tienPhat_CTPM.setText(""+tienphatne);
            } else if(cbx_tinhTrangSach_CtPm.getSelectedItem().equals("Mất sách")){
               txt_tienPhat_CTPM.requestFocus();
            }
            else {  tienphatne = 0; txt_tienPhat_CTPM.setText(""+tienphatne);}   
        }
       catch (Exception e) {JOptionPane.showMessageDialog(this, "Enter textbox mã PM để lấy ngày mượn!");}
    }//GEN-LAST:event_btn_tinhTienPhat_CTPMActionPerformed

    private void txt_maPM_CTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maPM_CTPMActionPerformed
        String sql = "SELECT GhiChu FROM PhieuMuon WHERE MaPM = ?";
        try{
            Connection connection = Connect_SQL.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txt_maPM_CTPM.getText());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                txt_songaymuon_ctpm.setText(rs.getString("GhiChu"));
                txt_setlaMuon.setText("Mượn ");
                txt_setlaNgay.setText(" ngày.");
            }
        } catch(Exception e){System.out.println("lỗi"); }
    }//GEN-LAST:event_txt_maPM_CTPMActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame_QLPM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame_QLPM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame_QLPM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame_QLPM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { new AdminJFrame_QLPM().setVisible(true);} catch (Exception ex) {Logger.getLogger(AdminJFrame_QLPM.class.getName()).log(Level.SEVERE, null, ex);  }}});}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_ChiTietPhieuMuon;
    private javax.swing.JTable Table_PhieUmUON;
    private javax.swing.JButton btn_Update_CTPM;
    private javax.swing.JButton btn_Update_PM;
    private javax.swing.JButton btn_add_CTPM;
    private javax.swing.JButton btn_add_PM;
    private javax.swing.JButton btn_back_pm;
    private javax.swing.JButton btn_delete_CTPM;
    private javax.swing.JButton btn_delete_PM;
    private javax.swing.JButton btn_lamMoi_CTPM;
    private javax.swing.JButton btn_lamMoi_Sach;
    private javax.swing.JButton btn_tinhTienPhat_CTPM;
    private javax.swing.JComboBox<String> cbx_maQL;
    private javax.swing.JComboBox<String> cbx_soNgayMuon;
    private javax.swing.JComboBox<String> cbx_tinhTrangSach_CtPm;
    private javax.swing.JComboBox<String> cbx_trangThaiPM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label lb_tbsoNgay;
    private javax.swing.JTextField txt_maPM;
    private javax.swing.JTextField txt_maPM_CTPM;
    private javax.swing.JTextField txt_maSach_CTPM;
    private javax.swing.JTextField txt_maSv;
    private javax.swing.JTextField txt_ngayTra_CTPm;
    private javax.swing.JTextField txt_ngaymuon;
    private javax.swing.JLabel txt_setlaMuon;
    private javax.swing.JLabel txt_setlaNgay;
    private javax.swing.JLabel txt_songaymuon_ctpm;
    private javax.swing.JTextField txt_tenquanly;
    private javax.swing.JTextField txt_tienPhat_CTPM;
    // End of variables declaration//GEN-END:variables
}
