package assignment.fpoly.quanlyquanao.activity.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import assignment.fpoly.quanlyquanao.R;
import assignment.fpoly.quanlyquanao.activity.handlingadd.ThemMatHangActivity;
import assignment.fpoly.quanlyquanao.adapter.ListAdapterMatHang;
import assignment.fpoly.quanlyquanao.classinfo.MatHang;
import assignment.fpoly.quanlyquanao.sql.DBHelper;

public class QuanLyKhoMatHangActivity extends ActionBarActivity {
    ListView lvDSMH;
    List<MatHang> matHangListAll;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_kho_mat_hang);
        setTitle("Quản Lý Mặt Hàng");
        db= new DBHelper(this);
        lvDSMH=(ListView)findViewById(R.id.listViewDSMatHang);

        matHangListAll=new ArrayList<>();
        matHangListAll=db.laydsmathang();
        ListAdapterMatHang listAdapterMatHang=new ListAdapterMatHang(getApplicationContext(),R.layout.item_listview_dsmathang,matHangListAll);
        lvDSMH.setAdapter(listAdapterMatHang);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quanlydonhang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent=new Intent(getApplicationContext(),ThemMatHangActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
