package assignment.fpoly.quanlyquanao.classinfo;

/**
 * Created by tiepnguyen on 06/10/2016.
 */
public class MatHang {
    public int idmathang;
    public String tenmathang;
    public int idnloaimathang;
    public int soluong;
    public int soluongbd;
    public int gianhap;
    public int giaban;
    public String ngaynhapkho;

    public MatHang() {
    }

    public MatHang(int idmathang, String tenmathang, int idnloaimathang, int soluong, int soluongbd, int gianhap, int giaban, String ngaynhapkho) {
        this.idmathang = idmathang;
        this.tenmathang = tenmathang;
        this.idnloaimathang = idnloaimathang;
        this.soluong = soluong;
        this.soluongbd = soluongbd;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.ngaynhapkho = ngaynhapkho;
    }

    public int getIdmathang() {
        return idmathang;
    }

    public void setIdmathang(int idmathang) {
        this.idmathang = idmathang;
    }

    public String getTenmathang() {
        return tenmathang;
    }

    public void setTenmathang(String tenmathang) {
        this.tenmathang = tenmathang;
    }

    public int getIdnloaimathang() {
        return idnloaimathang;
    }

    public void setIdnloaimathang(int idnloaimathang) {
        this.idnloaimathang = idnloaimathang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getSoluongbd() {
        return soluongbd;
    }

    public void setSoluongbd(int soluongbd) {
        this.soluongbd = soluongbd;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public String getNgaynhapkho() {
        return ngaynhapkho;
    }

    public void setNgaynhapkho(String ngaynhapkho) {
        this.ngaynhapkho = ngaynhapkho;
    }

    @Override
    public String toString() {
        return tenmathang;
    }
}
