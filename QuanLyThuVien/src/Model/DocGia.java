
package Model;

/**
 *
 * @author Duy Anh
 */
public class DocGia {
    
    private String maDG;
    private String passWord;
    private String tenDG;
    private String ngaySinh;
    private String gioiTinh;
    private String email;
    private int soLuongMuon;
    private String trangThaiTK;
    private String mapm;
    private int tienphat;

    
    public DocGia() {
        
    }
    public DocGia(String maDG, String tenGD, String ngaySinh,String gioiTinh,String email,int soLuongMuon , String trangThaiTK ) {
        this.maDG = maDG;
        this.tenDG = tenGD;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.soLuongMuon = soLuongMuon;
        this.trangThaiTK = trangThaiTK;
    }
    //constructor dùng cho Thống kê có mã pm và tiền phạt
    public DocGia(String mapm,String maDG, String tenGD, String ngaySinh,
            String gioiTinh,String email,int tienphat ) {
         this.mapm = mapm;
         this.maDG = maDG;
        this.tenDG = tenGD;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.tienphat = tienphat;
    }
//end 
    public String getMapm() {
        return mapm;
    }

    public void setMapm(String mapm) {
        this.mapm = mapm;
    }

    public int getTienphat() {
        return tienphat;
    }

    public void setTienphat(int tienphat) {
        this.tienphat = tienphat;
    }
    
    
    public String getTrangThaiTK() {
        return trangThaiTK;
    }

    public void setTrangThaiTK(String trangThaiTK) {
        this.trangThaiTK = trangThaiTK;
    }
    
    public String getMaDG() {
        return maDG;
    }

    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(int soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }

    @Override
    public String toString() {
        return "DocGia{" + "maDG=" + maDG + ", passWord=" + passWord + ", tenDG=" + tenDG + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", email=" + email + ", soLuongMuon=" + soLuongMuon + ", trangThaiTK=" + trangThaiTK + ", mapm=" + mapm + ", tienphat=" + tienphat + '}';
    }

    
    

    
}
