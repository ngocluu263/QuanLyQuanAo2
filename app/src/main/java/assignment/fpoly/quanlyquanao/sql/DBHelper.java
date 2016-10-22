package assignment.fpoly.quanlyquanao.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import assignment.fpoly.quanlyquanao.classinfo.GiaoDich;
import assignment.fpoly.quanlyquanao.classinfo.LoaiMatHang;
import assignment.fpoly.quanlyquanao.classinfo.MatHang;

/**
 * Created by tiepnguyen on 06/10/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_quanlyquanao";
    //bang loai mat hang
    public static final String TABLE_LOAIMATHANG = "loaimathang";
    public static final String KEY_IDLOAIMATHANG = "_idloaimathang";
    public static final String KEY_TENLOAIMATHANG = "tenloaimathang";
    //bang mat hang
    public static final String TABLE_MATHANG = "mathang";
    public static final String KEY_IDMATHANG = "_idmathang";
    public static final String KEY_TENMATHANG = "tenmathang";
    public static final String KEY_IDNLOAIMATHANG = "idnloaimathang";
    public static final String KEY_SOLUONGMATHANG = "soluongmathang";
    public static final String KEY_SOLUONGMATHANGBD = "soluongmathangbd";
    public static final String KEY_GIANHAP = "gianhap";
    public static final String KEY_GIABAN = "giaban";
    public static final String KEY_NGAYNHAPKHO = "ngaynhapkho";
//    //bang giao dich
//    public static final String TABLE_KHO = "kho";
//    public static final String KEY_IDKHO = "_idkho";
//    public static final String KEY_SOLUONG = "soluong";
//    public static final String KEY_IDNNLOAIMATHANG = "idnnloaimathang";
//    public static final String KEY_IDNNMATHANG = "idnnmathang";
//    public static final String KEY_NGAYNHAPKHO = "ngaynhapkho";
    //bang giao dich
    public static final String TABLE_GIAODICH = "giaodich";
    public static final String KEY_IDGD = "_idgd";
    public static final String KEY_IDMATHANGGD = "tenmathang";
    public static final String KEY_IDLOAIMATHANGGD = "loaimathang";
    public static final String KEY_SOLUONGD = "soluonggd";
    public static final String KEY_GIAGD = "giagd";
    public static final String KEY_NGAYGD = "ngaygd";





    Context context;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_LOAIMATHANG = "CREATE TABLE " + TABLE_LOAIMATHANG + " ("
                + KEY_IDLOAIMATHANG + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TENLOAIMATHANG + " nvarchar)";
        db.execSQL(CREATE_TABLE_LOAIMATHANG);

        String CREATE_TABLE_MATHANG = "CREATE TABLE " + TABLE_MATHANG + " ("
                + KEY_IDMATHANG + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TENMATHANG + " nvarchar,"
                + KEY_IDNLOAIMATHANG + " INTEGER,"
                + KEY_SOLUONGMATHANG + " INTEGER,"
                + KEY_SOLUONGMATHANGBD + " INTEGER,"
                + KEY_GIANHAP + " INTEGER,"
                + KEY_GIABAN + " INTEGER,"
                + KEY_NGAYNHAPKHO + " datetime)";
        db.execSQL(CREATE_TABLE_MATHANG);

//        String CREATE_TABLE_KHO = "CREATE TABLE " + TABLE_KHO + " ("
//                + KEY_IDKHO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + KEY_SOLUONG + " nvarchar,"
//                + KEY_GIANHAP + " INTEGER,"
//                + KEY_GIABAN + " INTEGER,"
//                + KEY_IDNNLOAIMATHANG + " INTEGER,"
//                + KEY_IDNNMATHANG + " INTEGER,"
//                + KEY_NGAYNHAPKHO + " DATETIME)";
//        db.execSQL(CREATE_TABLE_KHO);

        String CREATE_TABLE_GD = "CREATE TABLE " + TABLE_GIAODICH + " ("
                + KEY_IDGD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_IDMATHANGGD + " INTEGER,"
                + KEY_IDLOAIMATHANGGD + " INTEGER,"
                + KEY_SOLUONGD + " INTEGER,"
                + KEY_GIAGD + " INTEGER,"
                + KEY_NGAYGD + " DATETIME)";
        db.execSQL(CREATE_TABLE_GD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS "+TABLE_MATHANG);
        onCreate(db);
    }
    //lay ds thu chi(khoan thu, khoan chi)
    public List<LoaiMatHang> laydsloaimathang(){
        List<LoaiMatHang> list= new ArrayList<LoaiMatHang>();
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="select * from "+TABLE_LOAIMATHANG;
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LoaiMatHang loaiMatHang= new LoaiMatHang();
            loaiMatHang.setIdLoaiMatHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDLOAIMATHANG))));
            loaiMatHang.setTenLoaiMatHang(cursor.getString(cursor.getColumnIndex(KEY_TENLOAIMATHANG)));
            list.add(loaiMatHang);
            cursor.moveToNext();
        }
        return list;
    }
    //lay du lieu thu chi theo id
    public MatHang layDSMatHangTheoID(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="select * from "+ TABLE_MATHANG +" where " + KEY_IDMATHANG + " = " +id;
        Cursor cursor = db.rawQuery(sql,null);
        MatHang matHang= new MatHang();
        if(cursor.moveToFirst()){
            matHang.setIdmathang(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDMATHANG))));
            matHang.setTenmathang(cursor.getString(cursor.getColumnIndex(KEY_TENMATHANG)));
            matHang.setIdnloaimathang(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDNLOAIMATHANG))));
            matHang.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SOLUONGMATHANG))));
            matHang.setSoluongbd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SOLUONGMATHANGBD))));
            matHang.setGianhap(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_GIANHAP))));
            matHang.setGiaban(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_GIABAN))));
            matHang.setNgaynhapkho(cursor.getString(cursor.getColumnIndex(KEY_NGAYNHAPKHO)));
        }
        return matHang;
    }
    //lay ds thu chi(khoan thu, khoan chi)
    public List<MatHang> laydsmathang(){
        List<MatHang> list= new ArrayList<MatHang>();
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="select * from "+TABLE_MATHANG;
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MatHang matHang= new MatHang();
            matHang.setIdmathang(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDMATHANG))));
            matHang.setTenmathang(cursor.getString(cursor.getColumnIndex(KEY_TENMATHANG)));
            matHang.setIdnloaimathang(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDNLOAIMATHANG))));
            matHang.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SOLUONGMATHANG))));
            matHang.setSoluongbd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SOLUONGMATHANGBD))));
            matHang.setGianhap(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_GIANHAP))));
            matHang.setGiaban(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_GIABAN))));
            matHang.setNgaynhapkho(cursor.getString(cursor.getColumnIndex(KEY_NGAYNHAPKHO)));
            list.add(matHang);
            cursor.moveToNext();
        }
        return list;
    }

    //lay ds thu chi(khoan thu, khoan chi)
    public List<GiaoDich> laydsgiaodich(){
        List<GiaoDich> list= new ArrayList<GiaoDich>();
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="select * from "+TABLE_GIAODICH;
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GiaoDich giaoDich= new GiaoDich();
            giaoDich.setIdgd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDGD))));
            giaoDich.setIdmathanggd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDMATHANGGD))));
            giaoDich.setIdloaimathanggd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IDLOAIMATHANGGD))));
            giaoDich.setSoluonggd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SOLUONGD))));
            giaoDich.setGiagd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_GIAGD))));
            giaoDich.setDategd(cursor.getString(cursor.getColumnIndex(KEY_NGAYGD)));

            list.add(giaoDich);
            cursor.moveToNext();
        }
        return list;
    }

    public void themloaimathang(LoaiMatHang loaiMatHang){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_TENLOAIMATHANG, loaiMatHang.getTenLoaiMatHang());
        if(db.insert(TABLE_LOAIMATHANG, null, values)!=-1){
            Toast.makeText(context,"thêm thành công "+loaiMatHang.getTenLoaiMatHang(),Toast.LENGTH_SHORT).show();
        }
        else{Toast.makeText(context,"thêm that bai",Toast.LENGTH_SHORT).show();};
    }

    public void themmathang(MatHang matHang){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_TENMATHANG, matHang.getTenmathang());
        values.put(KEY_IDNLOAIMATHANG, matHang.getIdnloaimathang());
        values.put(KEY_SOLUONGMATHANG, matHang.getSoluong());
        values.put(KEY_SOLUONGMATHANGBD, matHang.getSoluongbd());
        values.put(KEY_GIANHAP, matHang.getGianhap());
        values.put(KEY_GIABAN, matHang.getGiaban());
        values.put(KEY_NGAYNHAPKHO, matHang.getNgaynhapkho());
        if(db.insert(TABLE_MATHANG, null, values)!=-1){
            Toast.makeText(context,"Thêm thành công mặt hàng "+matHang.getTenmathang(),Toast.LENGTH_SHORT).show();
        }
        else{Toast.makeText(context,"Thêm thất bại ",Toast.LENGTH_SHORT).show();};
    }


    public void themgiaodich(GiaoDich giaoDich){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_IDMATHANGGD, giaoDich.getIdmathanggd());
        values.put(KEY_IDLOAIMATHANGGD, giaoDich.getIdloaimathanggd());
        values.put(KEY_SOLUONGD, giaoDich.getSoluonggd());
        values.put(KEY_GIAGD, giaoDich.getGiagd());
        values.put(KEY_NGAYGD, giaoDich.getDategd());

        if(db.insert(TABLE_GIAODICH, null, values)!=-1){
            Toast.makeText(context,"Thêm thành công đơn hàng!!!!",Toast.LENGTH_SHORT).show();
        }
        else{Toast.makeText(context,"Thêm thất bại ",Toast.LENGTH_SHORT).show();};
    }
    //
    //cập nhật
    //
    public int CapNhatSoLuongMatHang(MatHang matHang){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TENMATHANG, matHang.getTenmathang());
        values.put(KEY_IDNLOAIMATHANG, matHang.getIdnloaimathang());
        values.put(KEY_SOLUONGMATHANG, matHang.getSoluong());
        values.put(KEY_SOLUONGMATHANGBD, matHang.getSoluongbd());
        values.put(KEY_GIANHAP, matHang.getGianhap());
        values.put(KEY_GIABAN, matHang.getGiaban());
        values.put(KEY_NGAYNHAPKHO, matHang.getNgaynhapkho());


        return db.update(TABLE_MATHANG,values,KEY_IDMATHANG+" =?", new String[]{String.valueOf(matHang.getIdmathang())});
    }
}
