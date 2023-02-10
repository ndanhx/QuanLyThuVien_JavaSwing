
package Model;

/**
 *
 * @author Duy Anh
 */
public class Sach {
    
    private String maSach = "";
    private String tenSach;
    private String maDMSach;
    private String maTheLoai;
    private String tacGia;
    private String NXB;
    private int namXuatBan;
    private int soLuongCon;
    private String tomTatND ;

    public Sach(String maSach,String tenSach, String maDMSach, String maTheLoai, String tacGia,
            String NXB, int namXuatBan, int soLuongCon, String tomTatND) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maDMSach = maDMSach;
        this.maTheLoai = maTheLoai;
        this.tacGia = tacGia;
        this.NXB = NXB;
        this.namXuatBan = namXuatBan;
        this.soLuongCon = soLuongCon;
        this.tomTatND = tomTatND;
    }

    
    public Sach() {
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getMaDMSach() {
        return maDMSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }

    public String getTomTatND() {
        return tomTatND;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setMaDMSach(String maDMSach) {
        this.maDMSach = maDMSach;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public void setTomTatND(String tomTatND) {
        this.tomTatND = tomTatND;
    }

    @Override
    public String toString() {
        return "Sach{" + "maSach=" + maSach + ", tenSach=" + tenSach + ", maDMSach=" + maDMSach + ", maTheLoai=" + maTheLoai + ", tacGia=" + tacGia + ", NXB=" + NXB + ", namXuatBan=" + namXuatBan + ", soLuongCon=" + soLuongCon + ", tomTatND=" + tomTatND + '}';
    }
    
    
}
