package assignment.fpoly.quanlyquanao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import assignment.fpoly.quanlyquanao.R;
import assignment.fpoly.quanlyquanao.classinfo.MatHang;
/**
 * Created by tiepnguyen on 09/10/2016.
 */
public class ListAdapterMatHang extends ArrayAdapter<MatHang> {

    public ListAdapterMatHang(Context context, int resource, List<MatHang> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item_listview_dsmathang, null);
        }
        MatHang p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri

            TextView tvTenMH = (TextView) view.findViewById(R.id.textViewlvmhtenmathang);
            TextView tvSL = (TextView) view.findViewById(R.id.textViewlvmhsoluong);
            TextView tvGianhap = (TextView) view.findViewById(R.id.textViewlvmhgianhap);
            TextView tvGiaBan = (TextView) view.findViewById(R.id.textViewlvmhgiaban);
            tvTenMH.setText(p.getTenmathang());
            tvSL.setText("Số lượng: "+p.getSoluong());
            tvGianhap.setText("Giá nhập: "+p.getGianhap());
            tvGiaBan.setText("Giá bán: "+p.getGiaban());

        }
        return view;
    }


}
