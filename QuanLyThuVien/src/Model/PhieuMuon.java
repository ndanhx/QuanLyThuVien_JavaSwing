
package Model;

/**
 *
 * @author Duy Anh
 */
public class PhieuMuon {
    private String maPM = "";
    private String ngayMuon = "";
    private String maDG_PM = "";
    private String maQuanLy = "";
    private String ghiChu = "";
    private String trangThai = "Chưa trả";

    public PhieuMuon() {
    }
    
    public PhieuMuon(String maPM, String ngayMuon, String maDG_PM, String maQuanLy, String ghiChu, String trangThai) {
        this.maPM = maPM;
        this.ngayMuon = ngayMuon;
        this.maDG_PM = maDG_PM;
        this.maQuanLy = maQuanLy;
        this.ghiChu = ghiChu;
        this.trangThai=trangThai;
    }

    public String getMaPM() {
        return maPM;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

 

    public String getMaDG_PM() {
        return maDG_PM;
    }

    public String getMaQuanLy() {
        return maQuanLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setMaPM(String maPM) {
        this.maPM = maPM;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

  

    public void setMaDG_PM(String maDG_PM) {
        this.maDG_PM = maDG_PM;
    }

    public void setMaQuanLy(String maQuanLy) {
        this.maQuanLy = maQuanLy;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
