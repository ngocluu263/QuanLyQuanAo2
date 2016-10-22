package assignment.fpoly.quanlyquanao.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import assignment.fpoly.quanlyquanao.R;
import assignment.fpoly.quanlyquanao.classinfo.GiaoDich;
import assignment.fpoly.quanlyquanao.classinfo.MatHang;
import assignment.fpoly.quanlyquanao.sql.DBHelper;

/**
 * Created by tiepnguyen on 09/10/2016.
 */
public class ListAdapterGiaoDich extends ArrayAdapter<GiaoDich> {
    DBHelper db;
    List<MatHang> matHangListAll;
    public ListAdapterGiaoDich(Context context, int resource, List<GiaoDich> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item_listview_dsmathang, null);
        }
        GiaoDich p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            db=new DBHelper(getContext());
            matHangListAll= new ArrayList<>();
            matHangListAll=db.laydsmathang();
            TextView tvTenMH = (TextView) view.findViewById(R.id.textViewlvmhtenmathang);
            TextView tvSL = (TextView) view.findViewById(R.id.textViewlvmhsoluong);
            TextView tvGianhap = (TextView) view.findViewById(R.id.textViewlvmhgianhap);
            TextView tvGiaBan = (TextView) view.findViewById(R.id.textViewlvmhgiaban);
            //tvTenMH.setText(p.getIdmathanggd());
            for (int i=0;i<matHangListAll.size();i++){
                if (matHangListAll.get(i).getIdmathang()==p.getIdmathanggd()){
                    tvTenMH.setText(matHangListAll.get(i).getTenmathang());
                }
            }
            tvSL.setText("Số lượng: "+p.getSoluonggd());
            tvGianhap.setText("Tổng giá: "+p.getGiagd());
            tvGiaBan.setText(""+p.getDategd());

        }
        return view;
    }


}
