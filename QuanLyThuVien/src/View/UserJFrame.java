package View;

import DAO.ChiTietPhieuMuon_DAO;
import DAO.Connect_SQL;
import DAO.PhieuMuon_DAO;
import DAO.Sach_DAO;
import Model.ChiTietPhieuMuon;
import Model.PhieuMuon;
import Model.Sach;
import java.awt.Font;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author Home
 */
public final class UserJFrame extends javax.swing.JFrame {
    ArrayList<Sach> arrSach = new ArrayList<>();
    String titleTraCuuHome[]= {};
    DefaultTableModel model =  new DefaultTableModel(titleTraCuuHome,0);
    DefaultTableModel model_chonsach;
    PhieuMuon_DAO pm_dao = new PhieuMuon_DAO();
    ChiTietPhieuMuon_DAO ctpm_dao = new ChiTietPhieuMuon_DAO();
    int soLuong_ToiDaDuocMuon;
    String tenSinhVien = "";
    int current = 0;
    int soLanMuon = 0;
    String maSinhvien = "";
    private UserJFrame() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
  
    public UserJFrame(String maSV, String tenSV, int soLuongDaMuon) {
        tenSinhVien = tenSV;
        maSinhvien = maSV;
        initComponents();
        LoadDatatoArrayList();
        LoadDataArrayttoTable();
        model_chonsach = new DefaultTableModel();
         model_chonsach.addColumn("Mã sách");
        model_chonsach.addColumn("Tên sách");
        model_chonsach.addColumn("Tác giả");
        model_chonsach.addColumn("Nhà xuất bản");
        model_chonsach.addColumn("Năm xuất bản");
        Table_sachDaChon_User.setModel(model_chonsach);
        lb_soLuongDaChon.setText("0");
        lb_tensinhvien_user.setText("Tên: " + tenSinhVien);
        lb_maSinhvien_User.setText(maSinhvien.trim());
        if(lb_maSinhvien_User.getText().substring(0, 2).equals("GV")){
            lb_hienMa_User.setText("Mã giảng viên: ");
            soLuong_ToiDaDuocMuon = 10 - soLuongDaMuon;
            lb_soLuongtoiDa.setText(String.valueOf(soLuong_ToiDaDuocMuon));
        } 
        else{
            lb_hienMa_User.setText("Mã sinh viên: ");
           soLuong_ToiDaDuocMuon = 5 - soLuongDaMuon;
            lb_soLuongtoiDa.setText(String.valueOf(soLuong_ToiDaDuocMuon));
        }
        JLabel thongbaomuonsachthanhcong; 
        thongbaomuonsachthanhcong = new JLabel("Thành công! Mời bạn đến thư"
                        + " viện nhận sách trong thời gian sớm nhất!");
        thongbaomuonsachthanhcong.setFont(new Font("Arial", Font.BOLD, 22));
    }
    public void LoadDatatoArrayList(){
        arrSach.clear();
        try {
           Connection connection= Connect_SQL.openConnection();
            String sql = "SELECT * FROM SACH";
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
        model = (DefaultTableModel) Table_TraCuu_User.getModel();
        model.setRowCount(0);
        for(Sach sach : arrSach){
            model.addRow(new Object[]
                            { 
                            sach.getMaSach(),sach.getTenSach(),
                            sach.getMaDMSach(),sach.getMaTheLoai(),sach.getTacGia(),
                            sach.getNXB(),sach.getNamXuatBan(),sach.getSoLuongCon() }    
                     );
        }
        TableColumnModel columnModel = Table_TraCuu_User.getColumnModel();
        TableColumn cot1sach = columnModel.getColumn(1);
        cot1sach.setPreferredWidth(250);
        TableColumn cot0sach= columnModel.getColumn(0);
        cot0sach.setPreferredWidth(120);
    }
    public void LoadtuClick_ChonSach (int row){
        Sach sach = arrSach.get(row);
        model = (DefaultTableModel) Table_sachDaChon_User.getModel();
        model.addRow(new Object[]{ 
                            sach.getMaSach(),sach.getTenSach(),sach.getTacGia(),
                            sach.getNXB(),sach.getNamXuatBan()
                            }    
                     );
    }
    public void LoadtuClick_return (int row){
        Sach sach = arrSach.get(row);
        model = (DefaultTableModel) Table_TraCuu_User.getModel();
        model.addRow(new Object[]{ 
                            sach.getMaSach(),sach.getTenSach(),
                            sach.getMaDMSach(),sach.getMaTheLoai(),sach.getTacGia(),
                            sach.getNXB(),sach.getNamXuatBan(),sach.getSoLuongCon(),sach.getTomTatND()
                            }    
                     );
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btn_Themsachdachon = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_xacNhanMuonSach_User = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        lb_tensinhvien_user = new javax.swing.JLabel();
        txt_timKiemSach_user = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_sachDaChon_User = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lb_soLuongDaChon = new javax.swing.JLabel();
        lb_soLuongtoiDa = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_TraCuu_User = new javax.swing.JTable();
        btn_xoaSachdachon = new javax.swing.JButton();
        lb_maSinhvien_User = new javax.swing.JLabel();
        cbx_soNgayMuon_user = new javax.swing.JComboBox<>();
        lb_hienMa_User = new javax.swing.JLabel();
        btn_xemChiTietSach = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mượn sách");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentShown(evt);
            }
        });

        btn_Themsachdachon.setBackground(new java.awt.Color(102, 255, 102));
        btn_Themsachdachon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_Themsachdachon.setText("Thêm");
        btn_Themsachdachon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemsachdachonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Tổng số sách:");

        btn_xacNhanMuonSach_User.setBackground(new java.awt.Color(51, 255, 51));
        btn_xacNhanMuonSach_User.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_xacNhanMuonSach_User.setText("Xuất phiếu");
        btn_xacNhanMuonSach_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanMuonSach_UserActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/searchicon.png"))); // NOI18N
        jLabel9.setText("Tìm kiếm sách:");

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageIcon/arrow-next-3-icon.png"))); // NOI18N
        jButton4.setText("ĐĂNG XUẤT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lb_tensinhvien_user.setBackground(new java.awt.Color(255, 255, 255));
        lb_tensinhvien_user.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_tensinhvien_user.setForeground(new java.awt.Color(255, 0, 0));

        txt_timKiemSach_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemSach_userActionPerformed(evt);
            }
        });
        txt_timKiemSach_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemSach_userKeyReleased(evt);
            }
        });

        Table_sachDaChon_User.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_sachDaChon_User.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Mã danh mục sách", "Mã thể loại", "Tác giả", "Nhà xuất bản", "Năm XB", "Số lượng", "Nội dung"
            }
        ));
        jScrollPane2.setViewportView(Table_sachDaChon_User);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 102));
        jLabel5.setText("Số ngày mượn:");

        lb_soLuongDaChon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_soLuongDaChon.setForeground(new java.awt.Color(255, 0, 0));

        lb_soLuongtoiDa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel8.setText("/");

        Table_TraCuu_User.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        Table_TraCuu_User.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Mã danh mục sách", "Mã thể loại", "Tác giả", "Nhà xuất bản", "Năm XB", "Số lượng"
            }
        ));
        Table_TraCuu_User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_TraCuu_UserMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Table_TraCuu_User);

        btn_xoaSachdachon.setBackground(new java.awt.Color(255, 51, 51));
        btn_xoaSachdachon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_xoaSachdachon.setText("xóa");
        btn_xoaSachdachon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaSachdachonActionPerformed(evt);
            }
        });

        lb_maSinhvien_User.setBackground(new java.awt.Color(255, 255, 255));
        lb_maSinhvien_User.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_maSinhvien_User.setForeground(new java.awt.Color(255, 0, 0));

        cbx_soNgayMuon_user.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_soNgayMuon_user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15" }));
        cbx_soNgayMuon_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_soNgayMuon_userActionPerformed(evt);
            }
        });

        lb_hienMa_User.setBackground(new java.awt.Color(255, 255, 255));
        lb_hienMa_User.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_hienMa_User.setForeground(new java.awt.Color(255, 0, 0));
        lb_hienMa_User.setText("Mã sinh viên:");

        btn_xemChiTietSach.setBackground(new java.awt.Color(204, 255, 255));
        btn_xemChiTietSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_xemChiTietSach.setText("xem chi tiết");
        btn_xemChiTietSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xemChiTietSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timKiemSach_user, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lb_hienMa_User)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_maSinhvien_User, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_tensinhvien_user, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(119, 119, 119)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_Themsachdachon, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoaSachdachon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_xemChiTietSach)
                            .addComponent(btn_xacNhanMuonSach_User))
                        .addGap(12, 12, 12)))
                .addGap(33, 33, 33))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_soNgayMuon_user, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_soLuongDaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_soLuongtoiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_hienMa_User, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_maSinhvien_User, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addComponent(lb_tensinhvien_user, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_timKiemSach_user, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(4, 4, 4)))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lb_soLuongDaChon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_soLuongtoiDa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_xemChiTietSach))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Themsachdachon)
                        .addComponent(btn_xoaSachdachon)
                        .addComponent(btn_xacNhanMuonSach_User))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbx_soNgayMuon_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lb_maSinhvien_User, lb_tensinhvien_user});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentShown
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jPanel2ComponentShown

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        new LiberyJFrame().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_timKiemSach_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemSach_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemSach_userActionPerformed

    private void txt_timKiemSach_userKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemSach_userKeyReleased
        arrSach.clear();
        int soluongs = 10;
        try {
           Connection connection= Connect_SQL.openConnection();
           if(txt_timKiemSach_user.getText().isEmpty()){
               soluongs = 10;
           }
           else if (txt_timKiemSach_user.getText().chars().allMatch(Character::isDigit)) {
               soluongs = Integer.parseInt(txt_timKiemSach_user.getText());
           }
           String sql = "SELECT * FROM SACH WHERE MaSach LIKE N'%" + txt_timKiemSach_user.getText() 
                   + "%' OR TenSach LIKE N'%" + txt_timKiemSach_user.getText()
                   + "%' OR MaDanhMucSach LIKE N'%" + txt_timKiemSach_user.getText()
                   + "%' OR MaTheLoai LIKE N'%" + txt_timKiemSach_user.getText()
                   + "%' OR TacGia LIKE N'%" + txt_timKiemSach_user.getText()
                   + "%' OR MaNhaXB LIKE N'%" + txt_timKiemSach_user.getText()
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
            System.out.println(e);
        }
    }//GEN-LAST:event_txt_timKiemSach_userKeyReleased

    private void Table_TraCuu_UserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_TraCuu_UserMouseClicked
        
    }//GEN-LAST:event_Table_TraCuu_UserMouseClicked
    public void addDSMuon() throws Exception {
        current = Table_TraCuu_User.getSelectedRow();
        String masach=Table_TraCuu_User.getValueAt(current,0).toString();
       
        Sach_DAO dao_sach = new Sach_DAO();
        int checksoluong = dao_sach.laySoLuongSach(masach);
        if(checksoluong > 0){
            LoadtuClick_ChonSach(current);
            for(int i=0;i<arrSach.size();i++){
            if(arrSach.get(i).getMaSach().equals(masach))
                arrSach.remove(arrSach.get(i));
            }
            soLanMuon = Table_sachDaChon_User.getRowCount();
            lb_soLuongDaChon.setText("" + soLanMuon);
            LoadDataArrayttoTable(); 
            JLabel dathem = new JLabel("Đã thêm!");
            dathem.setFont(new Font("Time New Roman", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null,dathem,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else
           JOptionPane.showMessageDialog(this, "Sách đã hết!!!");
    }
    private void btn_ThemsachdachonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemsachdachonActionPerformed
        current = Table_TraCuu_User.getSelectedRow();
        if(Table_TraCuu_User.getSelectedRowCount()==1){
            if(soLanMuon >= soLuong_ToiDaDuocMuon){
                soLanMuon = 5;
                JLabel thongbaovtd = new JLabel("Vượt tối số đa sách được mượn!");
                thongbaovtd.setFont(new Font("Time New Roman", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,thongbaovtd,"Thông báo",JOptionPane.ERROR_MESSAGE);
            }else {
                try {
                    addDSMuon();
                } catch (Exception ex) {
                    Logger.getLogger(UserJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        }else{
            JLabel chon1dong = new JLabel("Chọn 1 dòng để thêm!");
            chon1dong.setFont(new Font("Time New Roman", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null,chon1dong,"Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemsachdachonActionPerformed

    private void btn_xoaSachdachonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaSachdachonActionPerformed
        model = (DefaultTableModel) Table_sachDaChon_User.getModel();
        if(Table_sachDaChon_User.getSelectedRowCount()==1){
            model.removeRow(Table_sachDaChon_User.getSelectedRow());
            soLanMuon = Table_sachDaChon_User.getRowCount();
            JLabel daxoas = new JLabel("Đã xóa!");
            daxoas.setFont(new Font("Time New Roman", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null,daxoas,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
            lb_soLuongDaChon.setText(""+soLanMuon ); 
        }else  {
            JLabel chon1dongx = new JLabel("Chọn 1 dòng để xóa!");
            chon1dongx.setFont(new Font("Time New Roman", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null,chon1dongx,"Thông báo",JOptionPane.ERROR_MESSAGE); 
        }
                     
    }//GEN-LAST:event_btn_xoaSachdachonActionPerformed

    private void btn_xacNhanMuonSach_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanMuonSach_UserActionPerformed
        
        int soDong_TlbChonSach = Table_sachDaChon_User.getRowCount();   
        if(soDong_TlbChonSach > 0){
            JLabel thongbao = new JLabel("Bạn có chắc muốn xuất phiếu?");
            thongbao.setFont(new Font("Time New Roman", Font.BOLD, 18));
            int result = JOptionPane.showConfirmDialog(this,thongbao, "Thông báo",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                String maPM = null;
                try {
                    maPM = pm_dao.getMaPMTop1(); //nó lấy top 1 trong table  PM: vi du lay dc pm0001
                } catch (Exception ex) {
                    System.out.println("loi cho nay");
                    Logger.getLogger(UserJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(int i = 0; i <  soDong_TlbChonSach; i++) {
                   try {
                        PhieuMuon phieuMuon = new PhieuMuon();
                        phieuMuon.setMaPM(maPM + Integer.toString(i)); // theem dc pm00010
                        phieuMuon.setGhiChu(cbx_soNgayMuon_user.getSelectedItem().toString());
                        phieuMuon.setMaDG_PM(maSinhvien);
                        phieuMuon.setMaQuanLy(null);
                        phieuMuon.setTrangThai("Chưa duyệt");
                        phieuMuon.setNgayMuon(null);
                        pm_dao.addPhieuMuon(phieuMuon);
                    } catch (Exception ex) {
                        Logger.getLogger(UserJFrame.class.getName()).log(Level.SEVERE, null, ex);  
                    }
                }
                //Thêm các pm và mã sách vào chi tiết phiếu mượn
                for(int i = 0; i < soDong_TlbChonSach; i++) {
                ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(maPM + Integer.toString(i) ,
                                    String.valueOf(Table_sachDaChon_User.getValueAt(i, 0)));
                    try {
                        ctpm_dao.insertChiTietPM_choUser(ctpm);
                    } catch (Exception ex) {
                        Logger.getLogger(UserJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                soLuong_ToiDaDuocMuon -=soDong_TlbChonSach;
                JLabel thongbaomuonsachthanhcong = new JLabel("Thành công! Mời bạn đến thư viện\n nhận sách trong thời gian sớm nhất!");
                thongbaomuonsachthanhcong.setFont(new Font("Time New Roman", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,thongbaomuonsachthanhcong,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                 model = (DefaultTableModel) Table_sachDaChon_User.getModel();
                 model.setRowCount(0);

            }
        }else {
            JLabel thongbaochonsach = new JLabel("Thêm sách vào bảng phía dưới!");
                thongbaochonsach.setFont(new Font("Time New Roman", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,thongbaochonsach,"Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_xacNhanMuonSach_UserActionPerformed

    private void cbx_soNgayMuon_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_soNgayMuon_userActionPerformed

    }//GEN-LAST:event_cbx_soNgayMuon_userActionPerformed

    private void btn_xemChiTietSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xemChiTietSachActionPerformed
         if(Table_TraCuu_User.getSelectedRow() >= 0){  String masach=null;
            masach=Table_TraCuu_User.getValueAt(Table_TraCuu_User.getSelectedRow(),0).toString();
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
            java.util.logging.Logger.getLogger(UserJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UserJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_TraCuu_User;
    private javax.swing.JTable Table_sachDaChon_User;
    private javax.swing.JButton btn_Themsachdachon;
    private javax.swing.JButton btn_xacNhanMuonSach_User;
    private javax.swing.JButton btn_xemChiTietSach;
    private javax.swing.JButton btn_xoaSachdachon;
    private javax.swing.JComboBox<String> cbx_soNgayMuon_user;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_hienMa_User;
    private javax.swing.JLabel lb_maSinhvien_User;
    private javax.swing.JLabel lb_soLuongDaChon;
    private javax.swing.JLabel lb_soLuongtoiDa;
    private javax.swing.JLabel lb_tensinhvien_user;
    private javax.swing.JTextField txt_timKiemSach_user;
    // End of variables declaration//GEN-END:variables
}
