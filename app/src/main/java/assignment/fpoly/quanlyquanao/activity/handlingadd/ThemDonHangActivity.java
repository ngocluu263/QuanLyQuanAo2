package assignment.fpoly.quanlyquanao.activity.handlingadd;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import assignment.fpoly.quanlyquanao.R;
import assignment.fpoly.quanlyquanao.classinfo.GiaoDich;
import assignment.fpoly.quanlyquanao.classinfo.LoaiMatHang;
import assignment.fpoly.quanlyquanao.classinfo.MatHang;
import assignment.fpoly.quanlyquanao.sql.DBHelper;

public class ThemDonHangActivity extends ActionBarActivity {
    Spinner spnTenMatHang,spnLoaiMatHang;
    TextView tvSoLuong,tvGia,tvNow,tvTru,tvCong;
    TextView tvDate;
    Button btnXoaTrang, btnLuu;
    DBHelper db;
    Calendar cal;
    Date dateFinish;
    ArrayList<String> listTenMH;
    ArrayList<String> listLoaiMatHang;
    List<MatHang> matHangListAll;
    List<LoaiMatHang> loaiMatHangListAll;
    List<MatHang> matHangList;
    List<MatHang> matHangList2;
    List<MatHang> matHangListAll2;
    GiaoDich giaoDich;
    MatHang matHang;
    MatHang matHangid;
    int idmathang;
    int giabandau;
    int idloaimathang;
    int soluongtoida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_don_hang);
        setTitle("Thêm Đơn Hàng");
        db=new DBHelper(this);
        anhxa();
        getDateTime();
        themvaospn();
        spnLoaiMatHang.setOnItemSelectedListener(myItemSelect);
        spnTenMatHang.setOnItemSelectedListener(myItemSelectMatHang);
        tvDate.setOnClickListener(myOnlick);
        tvNow.setOnClickListener(myOnlick);
        tvTru.setOnClickListener(myOnlick);
        tvCong.setOnClickListener(myOnlick);
        btnXoaTrang.setOnClickListener(myOnlick);
        btnLuu.setOnClickListener(myOnlick);
    }

    public void anhxa(){
        spnTenMatHang=(Spinner)findViewById(R.id.spinnerTenMatHang);
        spnLoaiMatHang=(Spinner)findViewById(R.id.spinnerLoaiMatHang);
        tvGia=(TextView) findViewById(R.id.textViewGia);
        tvSoLuong=(TextView) findViewById(R.id.textViewSoLuong);
        tvDate=(TextView) findViewById(R.id.textViewDate);
        tvNow=(TextView)findViewById(R.id.textViewNow);
        tvTru=(TextView)findViewById(R.id.textViewTru);
        tvCong=(TextView)findViewById(R.id.textViewCong);
        btnXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
        btnLuu=(Button)findViewById(R.id.buttonLuu);

    }

    public void themvaospn(){
        loaiMatHangListAll=new ArrayList<>();
        loaiMatHangListAll=db.laydsloaimathang();
        ArrayAdapter arrayAdapter= new ArrayAdapter(getApplicationContext(),R.layout.spinner_view,loaiMatHangListAll);
        spnLoaiMatHang.setAdapter(arrayAdapter);

        matHangListAll=new ArrayList<>();
        matHangListAll2=new ArrayList<>();
        matHangListAll=db.laydsmathang();
        for (int i=0; i<matHangListAll.size();i++){
            if(matHangListAll.get(i).getSoluong()!=0){
                matHangListAll2.add(new MatHang(
                        matHangListAll.get(i).getIdmathang(),
                        matHangListAll.get(i).getTenmathang(),
                        matHangListAll.get(i).getIdnloaimathang(),
                        matHangListAll.get(i).getSoluong(),
                        matHangListAll.get(i).getSoluongbd(),
                        matHangListAll.get(i).getGianhap(),
                        matHangListAll.get(i).getGiaban(),
                        matHangListAll.get(i).getNgaynhapkho()
                ));
            }
        }

        ArrayAdapter arrayAdapter2= new ArrayAdapter(getApplicationContext(),R.layout.spinner_view,matHangListAll2);
        spnTenMatHang.setAdapter(arrayAdapter2);

    }
    public void resettien(){
        int soluong= Integer.parseInt(tvSoLuong.getText().toString());
        tvGia.setText(soluong*giabandau+"");
    }

    View.OnClickListener myOnlick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v== tvDate) showDatePickerDialog();
            if (v==tvNow) getDateTime();
            if (v==btnXoaTrang){
                finish();
            }
            if (v==tvTru){
                if (tvSoLuong.getText().toString().equals("1")){
                    Toast.makeText(getApplicationContext(),"Số lượng đã nhỏ nhất rồi!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    tvSoLuong.setText(Integer.parseInt(tvSoLuong.getText().toString())-1+"");
                    int soluong= Integer.parseInt(tvSoLuong.getText().toString());
                    tvGia.setText(soluong*giabandau+"");

                }
            }
            if(v==tvCong){
                if (Integer.parseInt(tvSoLuong.getText().toString())== soluongtoida){
                    Toast.makeText(getApplicationContext(),"Số lượng đã lớn nhất rồi!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    tvSoLuong.setText(Integer.parseInt(tvSoLuong.getText().toString())+1+"");
                    int soluong= Integer.parseInt(tvSoLuong.getText().toString());
                    tvGia.setText(soluong*giabandau+"");
                }
            }
            if (v==btnLuu){
                giaoDich = new GiaoDich();
                giaoDich.setIdmathanggd(idmathang);
                giaoDich.setIdloaimathanggd(idloaimathang);
                giaoDich.setSoluonggd(Integer.parseInt(tvSoLuong.getText().toString()));
                giaoDich.setGiagd(Integer.parseInt(tvGia.getText().toString()));
                giaoDich.setDategd(getDateFormat(dateFinish));
                db.themgiaodich(giaoDich);

                matHangid=new MatHang();
                matHangid=db.layDSMatHangTheoID(idmathang);
                matHang=new MatHang();
                matHang.setIdmathang(matHangid.getIdmathang());
                matHang.setTenmathang(matHangid.getTenmathang());
                matHang.setIdnloaimathang(matHangid.getIdnloaimathang());
                int soluong= matHangid.getSoluong();
                int soluongdt=soluong-Integer.parseInt(tvSoLuong.getText().toString());
                matHang.setSoluong(soluongdt);
                matHang.setSoluongbd(matHangid.getSoluongbd());
                matHang.setGianhap(matHangid.getGianhap());
                matHang.setGiaban(matHangid.getGiaban());
                matHang.setNgaynhapkho(matHangid.getNgaynhapkho());
                db.CapNhatSoLuongMatHang(matHang);

            }
        }
    };

    AdapterView.OnItemSelectedListener myItemSelect= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            laydsmathangtheoid(loaiMatHangListAll.get(position).getIdLoaiMatHang());
            idloaimathang= loaiMatHangListAll.get(position).getIdLoaiMatHang();
            //Toast.makeText(getApplicationContext(),loaiMatHangListAll.get(position).getIdLoaiMatHang()+"",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(),"Bạn Chưa Thêm Măt Hàng!!!! Vui lòng thêm mặt hàng!!!",Toast.LENGTH_SHORT).show();

        }
    };

    AdapterView.OnItemSelectedListener myItemSelectMatHang= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //laydsmathangtheoid(loaiMatHangListAll.get(position).getIdLoaiMatHang());
            tvSoLuong.setText("1");
            idmathang= matHangList2.get(position).getIdmathang();

            tvGia.setText(matHangList2.get(position).getGiaban()+"");
            giabandau=matHangList2.get(position).getGiaban();
            soluongtoida=matHangList2.get(position).getSoluong();
            resettien();
            //Toast.makeText(getApplicationContext(),matHangList.get(position).getIdmathang()+"",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(),"Bạn Chưa Thêm Măt Hàng!!!! Vui lòng thêm mặt hàng!!!",Toast.LENGTH_SHORT).show();

        }
    };

    public void laydsmathangtheoid(int id){
        //matHangListAll=new ArrayList<>();
        matHangList=new ArrayList<>();
        matHangListAll2=db.laydsmathang();
        for (int i=0;i<matHangListAll2.size();i++){
            if (id==matHangListAll2.get(i).getIdnloaimathang()){
                matHangList.add( new MatHang(
                        matHangListAll2.get(i).getIdmathang(),
                        matHangListAll2.get(i).getTenmathang(),
                        matHangListAll2.get(i).getIdnloaimathang(),
                        matHangListAll2.get(i).getSoluong(),
                        matHangListAll2.get(i).getSoluongbd(),
                        matHangListAll2.get(i).getGianhap(),
                        matHangListAll2.get(i).getGiaban(),
                        matHangListAll2.get(i).getNgaynhapkho()
                ));
            }
        }
        matHangList2=new ArrayList<>();
        for (int t=0;t<matHangList.size();t++){
            if (matHangList.get(t).getSoluong()!=0){
                matHangList2.add(new MatHang(
                        matHangList.get(t).getIdmathang(),
                        matHangList.get(t).getTenmathang(),
                        matHangList.get(t).getIdnloaimathang(),
                        matHangList.get(t).getSoluong(),
                        matHangList.get(t).getSoluongbd(),
                        matHangList.get(t).getGianhap(),
                        matHangList.get(t).getGiaban(),
                        matHangList.get(t).getNgaynhapkho()
                ));

            }
        }
        ArrayAdapter arrayAdapter2= new ArrayAdapter(getApplicationContext(),R.layout.spinner_view,matHangList2);
        spnTenMatHang.setAdapter(arrayAdapter2);
    }

    /**
     * Hàm hiển thị DatePicker dialog
     */
    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                tvDate.setText((dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year+"");
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish = cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = tvDate.getText() + "";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1]) - 1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(
                ThemDonHangActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    public void getDateTime() {

        cal = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        tvDate.setText(strDate);

        dateFinish = cal.getTime();
    }

    //chuyen doi ngay gio de nhap vao mang va sql
    public String getDateFormat(Date d) {
        SimpleDateFormat dft = new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }
}
