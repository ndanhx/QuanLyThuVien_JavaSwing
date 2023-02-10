
package Model;

/**
 *
 * @author Duy Anh
 */
public class ChiTietPhieuMuon {
    private String maPM = "";
    private String maSach = "";
    private String ngayThucTra = "";
    private int tienPhat = 0;
    private String tinhTrangSach = "";
    
    
    public ChiTietPhieuMuon() {
        
    }
    public ChiTietPhieuMuon(String maPM, String maSach) {
        this.maPM = maPM;
        this.maSach = maSach;
        
    }
    public ChiTietPhieuMuon(String maPM, String maSach,String ngayThucTra,int tienPhat,String tinhTrangSach) {
        this.maPM = maPM;
        this.maSach = maSach;
        this.ngayThucTra=ngayThucTra;
        this.tienPhat=tienPhat;
        this.tinhTrangSach=tinhTrangSach;
    }

    public String getMaPM() {
        return maPM;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getNgayThucTra() {
        return ngayThucTra;
    }

    public String getTinhTrangSach() {
        return tinhTrangSach;
    }

    public int getTienPhat() {
        return tienPhat;
    }

    public void setMaPM(String maPM) {
        this.maPM = maPM;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setNgayThucTra(String ngayThucTra) {
        this.ngayThucTra = ngayThucTra;
    }

    public void setTinhTrangSach(String tinhTrangSach) {
        this.tinhTrangSach = tinhTrangSach;
    }

    public void setTienPhat(int tienPhat) {
        this.tienPhat = tienPhat;
    }
    
    
}
