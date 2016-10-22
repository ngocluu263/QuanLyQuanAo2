package assignment.fpoly.quanlyquanao.classinfo;

/**
 * Created by tiepnguyen on 06/10/2016.
 */
public class LoaiMatHang {
    public int idLoaiMatHang;
    public String tenLoaiMatHang;

    public LoaiMatHang() {
    }

    public LoaiMatHang(int idLoaiMatHang, String tenLoaiMatHang) {
        this.idLoaiMatHang = idLoaiMatHang;
        this.tenLoaiMatHang = tenLoaiMatHang;
    }

    public int getIdLoaiMatHang() {
        return idLoaiMatHang;
    }

    public void setIdLoaiMatHang(int idLoaiMatHang) {
        this.idLoaiMatHang = idLoaiMatHang;
    }

    public String getTenLoaiMatHang() {
        return tenLoaiMatHang;
    }

    public void setTenLoaiMatHang(String tenLoaiMatHang) {
        this.tenLoaiMatHang = tenLoaiMatHang;
    }

    @Override
    public String toString() {
        return tenLoaiMatHang;
    }
}
