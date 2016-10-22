package assignment.fpoly.quanlyquanao.activity.statistic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import assignment.fpoly.quanlyquanao.R;

public class ThongKeActivity extends AppCompatActivity {
    Spinner spnThongKe;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        spnThongKe=(Spinner)findViewById(R.id.spinnerThongKe);
        list=new ArrayList<>();
        list.add("Tất cả");
        list.add("Hôm Nay");
        list.add("Tháng Này");
        list.add("Năm Này");
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);
        spnThongKe.setAdapter(arrayAdapter);


    }
}
