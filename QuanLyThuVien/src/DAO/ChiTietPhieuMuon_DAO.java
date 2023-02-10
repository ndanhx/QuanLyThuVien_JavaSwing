/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ChiTietPhieuMuon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Home
 */
public class ChiTietPhieuMuon_DAO {
    //thêm thông tin chi tiết phiếu mượn vào database
    public void insertChiTietPM_choUser(ChiTietPhieuMuon ctpm) throws Exception {
        Connection connection = Connect_SQL.openConnection()    ;
        String sql = "INSERT INTO ChiTietPhieuMuon (MaPM, MaSach) VALUES (?, ?)";
        try {           
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ctpm.getMaPM());
            preparedStatement.setString(2, ctpm.getMaSach());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
