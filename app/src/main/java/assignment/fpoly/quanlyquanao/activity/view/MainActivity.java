package assignment.fpoly.quanlyquanao.activity.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import assignment.fpoly.quanlyquanao.R;
import assignment.fpoly.quanlyquanao.activity.handlingadd.ThemDonHangActivity;
import assignment.fpoly.quanlyquanao.activity.statistic.ThongKeActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout lnThemDonHang,lnQLDonHang,lnQLMatHang,lnQLKho,lnThongKe,lnBieuDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        lnThemDonHang.setOnClickListener(myOnclick);
        lnQLDonHang.setOnClickListener(myOnclick);
        lnQLMatHang.setOnClickListener(myOnclick);
        lnQLKho.setOnClickListener(myOnclick);
        lnThongKe.setOnClickListener(myOnclick);
        lnBieuDo.setOnClickListener(myOnclick);

    }
    public void anhxa(){
        lnThemDonHang=(LinearLayout)findViewById(R.id.linnearThemDonHang);
        lnQLDonHang=(LinearLayout)findViewById(R.id.linnearQLDonHang);
        lnQLMatHang=(LinearLayout)findViewById(R.id.linnearQuanLyMatHang);
        lnQLKho=(LinearLayout)findViewById(R.id.linnearQuanLyKho);
        lnThongKe =(LinearLayout)findViewById(R.id.linnearThongKe);
        lnBieuDo=(LinearLayout)findViewById(R.id.linnearBieuDo);
    }
    //sự kiện Onclick
    View.OnClickListener myOnclick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v==lnThemDonHang){
                Intent mhTDH=new Intent(getApplication(),ThemDonHangActivity.class);
                startActivity(mhTDH);
            }
            if (v==lnQLDonHang){
                Intent mhTDH=new Intent(getApplication(),QuanLyDonHangActivity.class);
                startActivity(mhTDH);

            }
            if (v==lnQLMatHang){
                Intent mhTDH=new Intent(getApplication(),QuanLyLoaiMatHangActivity.class);
                startActivity(mhTDH);
            }
            if (v==lnQLKho){
                Intent mhTDH=new Intent(getApplication(),QuanLyKhoMatHangActivity.class);
                startActivity(mhTDH);
            }
            if (v==lnThongKe){
                Intent mhTDH=new Intent(getApplication(),ThongKeActivity.class);
                startActivity(mhTDH);
            }
            if (v==lnBieuDo){
                QuanLyLoaiMatHangActivity.resetlv(getApplicationContext());
                Toast.makeText(getApplicationContext(),"Biểu Đồ",Toast.LENGTH_SHORT).show();

            }
        }

    };
}
