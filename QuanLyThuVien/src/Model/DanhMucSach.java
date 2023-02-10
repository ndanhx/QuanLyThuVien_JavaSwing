
package Model;

/**
 *
 * @author Duy Anh
 */
public class DanhMucSach {
    private String maDM = "";
    private String tenDM="";

    public DanhMucSach() {
    }
    public DanhMucSach(String maDM, String tenDM) {
        this.maDM=maDM;
        this.tenDM=tenDM;
    }
    

  
    
    
    public String getMaDM() {
        return maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }
    
    
}
