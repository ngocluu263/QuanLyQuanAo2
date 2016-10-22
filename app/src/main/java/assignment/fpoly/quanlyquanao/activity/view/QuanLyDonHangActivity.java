package assignment.fpoly.quanlyquanao.activity.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import assignment.fpoly.quanlyquanao.R;
import assignment.fpoly.quanlyquanao.adapter.ListAdapterGiaoDich;
import assignment.fpoly.quanlyquanao.classinfo.GiaoDich;
import assignment.fpoly.quanlyquanao.sql.DBHelper;

public class QuanLyDonHangActivity extends ActionBarActivity {
    ListView lvQLDH;
    DBHelper db;
    GiaoDich giaoDich;
    List<GiaoDich> giaoDichList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_don_hang);
        db=new DBHelper(this);
        lvQLDH=(ListView)findViewById(R.id.listViewQLDonHang);
        giaoDich=new GiaoDich();
        giaoDichList=new ArrayList<>();
        giaoDichList=db.laydsgiaodich();
        ListAdapterGiaoDich listAdapterGiaoDich=new ListAdapterGiaoDich(getApplicationContext(),R.layout.item_listview_dsmathang,giaoDichList);
        lvQLDH.setAdapter(listAdapterGiaoDich);

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
                Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
