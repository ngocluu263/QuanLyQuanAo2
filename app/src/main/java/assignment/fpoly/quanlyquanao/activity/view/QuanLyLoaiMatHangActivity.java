package assignment.fpoly.quanlyquanao.activity.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import assignment.fpoly.quanlyquanao.R;
import assignment.fpoly.quanlyquanao.classinfo.LoaiMatHang;
import assignment.fpoly.quanlyquanao.sql.DBHelper;

public class QuanLyLoaiMatHangActivity extends ActionBarActivity {
    static ListView lvDSMH;
    static DBHelper db;
    LoaiMatHang loaiMatHang;
    static List<LoaiMatHang> loaiMatHangList;
    public static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_mat_hang);
        setTitle("lab 07");
        db=new DBHelper(this);
        anhxa();
        resetlv(getApplicationContext());


    }

    public void anhxa(){
        lvDSMH=(ListView)findViewById(R.id.listViewDSQLMatHang);
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
//                Intent intent=new Intent(getApplicationContext(),ThemMatHangActivity.class);
//                startActivity(intent);
                displayAlertDialog();


                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public static void  resetlv(Context context){
        loaiMatHangList=new ArrayList<>();
        loaiMatHangList=db.laydsloaimathang();

        arrayAdapter = new ArrayAdapter(context, R.layout.spinner_view,loaiMatHangList);
        lvDSMH.setAdapter(arrayAdapter);
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_them_mat_hang, null);
        final EditText tenloaimathang = (EditText) alertLayout.findViewById(R.id.editTextNhaptenmathang);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thêm Mặt Hàng");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loaiMatHang = new LoaiMatHang();
                loaiMatHang.setTenLoaiMatHang(tenloaimathang.getText().toString());
                db.themloaimathang(loaiMatHang);
                resetlv(getApplicationContext());
            }
        });
        final AlertDialog dialog = alert.create();
        dialog.show();
    }
}
