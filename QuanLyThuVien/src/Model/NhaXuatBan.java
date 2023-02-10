/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class NhaXuatBan {
    private String maNhaXuatBan = "";
    private String tenNhaXuatBan = "";

    public NhaXuatBan() {
    }
    public NhaXuatBan(String maNhaXuatBan,String tenNhaXuatBan) {
        this.maNhaXuatBan= maNhaXuatBan;
        this.tenNhaXuatBan= tenNhaXuatBan;
    }
    public String getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(String maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }
    
}
