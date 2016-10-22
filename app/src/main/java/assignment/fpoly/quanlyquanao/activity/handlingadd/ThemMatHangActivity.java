package assignment.fpoly.quanlyquanao.activity.handlingadd;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import assignment.fpoly.quanlyquanao.classinfo.LoaiMatHang;
import assignment.fpoly.quanlyquanao.classinfo.MatHang;
import assignment.fpoly.quanlyquanao.sql.DBHelper;

public class ThemMatHangActivity extends ActionBarActivity {
    EditText edtTenMatHang,edtSoluong,edtGiaNhap,edtGiaBan;
    Button btnLuu, btnXoaTrang;
    Spinner spnLoaiMatHang;
    TextView tvChonDate,tvDateNow;
    List<LoaiMatHang> loaiMatHangList;
    Calendar cal;
    Date dateFinish;
    int idloaimathang;
    List<LoaiMatHang> loaiMatHangListAll;
    List<LoaiMatHang> loaiMatHangListid;

    MatHang matHang;


    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_kho);
        setTitle("Thêm Mặt Hàng");
        db=new DBHelper(this);
        anhxa();
        getDateTime();
        loadspn();

        tvChonDate.setOnClickListener(myOnclick);
        tvDateNow.setOnClickListener(myOnclick);
        btnLuu.setOnClickListener(myOnclick);
        btnXoaTrang.setOnClickListener(myOnclick);
        spnLoaiMatHang.setOnItemSelectedListener(myItemSelect);

    }
    public void anhxa(){
        edtTenMatHang=(EditText)findViewById(R.id.editTextQLKtenmathang);
        edtSoluong=(EditText)findViewById(R.id.editTextQLKsoluong);
        edtGiaNhap=(EditText)findViewById(R.id.editTextQLKgianhap);
        edtGiaBan=(EditText)findViewById(R.id.editTextQLKgiaban);
        spnLoaiMatHang=(Spinner) findViewById(R.id.spinnerQLKLoaiMatHang);
        tvChonDate=(TextView)findViewById(R.id.textViewQLKChonDate);
        tvDateNow=(TextView)findViewById(R.id.textViewQLKDateNow);
        btnLuu=(Button)findViewById(R.id.buttonQLKLuu);
        btnXoaTrang=(Button)findViewById(R.id.buttonQLKXoaTrang);
    }
    public void loadspn(){
        loaiMatHangList= new ArrayList<>();
        loaiMatHangList=db.laydsloaimathang();
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.spinner_view,loaiMatHangList);
        spnLoaiMatHang.setAdapter(arrayAdapter);
    }

    AdapterView.OnItemSelectedListener myItemSelect= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //loaiMatHangListid= new ArrayList<>();
           idloaimathang= loaiMatHangList.get(position).getIdLoaiMatHang();
            Toast.makeText(getApplicationContext(),""+loaiMatHangList.toString(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener myOnclick= new  View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v==tvDateNow){
                getDateTime();
            }
            if (v==tvChonDate){
                showDatePickerDialog();
            }
            if (v== btnXoaTrang){
                edtTenMatHang.setText("");
                edtSoluong.setText("");
                edtGiaNhap.setText("");
                edtGiaBan.setText("");
            }
            if(v == btnLuu){
                matHang=new MatHang();
                matHang.setTenmathang(edtTenMatHang.getText().toString());
                matHang.setIdnloaimathang(idloaimathang);
                matHang.setSoluong(Integer.parseInt(edtSoluong.getText().toString()));
                matHang.setSoluongbd(Integer.parseInt(edtSoluong.getText().toString()));
                matHang.setGianhap(Integer.parseInt(edtGiaNhap.getText().toString()));
                matHang.setGiaban(Integer.parseInt(edtGiaBan.getText().toString()));
                matHang.setNgaynhapkho(getDateFormat(dateFinish));
                db.themmathang(matHang);
                //Toast.makeText(getApplicationContext(),getDateFormat(dateFinish),Toast.LENGTH_SHORT).show();


            }
        }
    };


    /**
     * Hàm hiển thị DatePicker dialog
     */
    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                tvChonDate.setText((dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year+"");
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish = cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = tvChonDate.getText() + "";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1]) - 1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(
                ThemMatHangActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    public void getDateTime() {

        cal = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        tvChonDate.setText(strDate);

        dateFinish = cal.getTime();
    }

    //chuyen doi ngay gio de nhap vao mang va sql
    public String getDateFormat(Date d) {
        SimpleDateFormat dft = new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }


}
