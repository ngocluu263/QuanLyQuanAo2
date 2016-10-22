package assignment.fpoly.quanlyquanao.classinfo;

/**
 * Created by tiepnguyen on 09/10/2016.
 */
public class GiaoDich {
    public int idgd;
    public int idmathanggd;
    public int idloaimathanggd;
    public int soluonggd;
    public int giagd;
    public String dategd;

    public GiaoDich() {
    }

    public GiaoDich(int idgd, int idmathanggd, int idloaimathanggd, int soluonggd, int giagd, String dategd) {
        this.idgd = idgd;
        this.idmathanggd = idmathanggd;
        this.idloaimathanggd = idloaimathanggd;
        this.soluonggd = soluonggd;
        this.giagd = giagd;
        this.dategd = dategd;
    }

    public int getIdgd() {
        return idgd;
    }

    public void setIdgd(int idgd) {
        this.idgd = idgd;
    }

    public int getIdmathanggd() {
        return idmathanggd;
    }

    public void setIdmathanggd(int idmathanggd) {
        this.idmathanggd = idmathanggd;
    }

    public int getIdloaimathanggd() {
        return idloaimathanggd;
    }

    public void setIdloaimathanggd(int idloaimathanggd) {
        this.idloaimathanggd = idloaimathanggd;
    }

    public int getSoluonggd() {
        return soluonggd;
    }

    public void setSoluonggd(int soluonggd) {
        this.soluonggd = soluonggd;
    }

    public int getGiagd() {
        return giagd;
    }

    public void setGiagd(int giagd) {
        this.giagd = giagd;
    }

    public String getDategd() {
        return dategd;
    }

    public void setDategd(String dategd) {
        this.dategd = dategd;
    }

    @Override
    public String toString() {
        return idgd+idmathanggd+idloaimathanggd+soluonggd+giagd+dategd;
    }
}
