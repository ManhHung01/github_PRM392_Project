package com.example.qlphontro.Fragment.HandleThongKe;

import static com.example.qlphontro.LoginActivity.sqlHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlphontro.Model.ThanhToan;
import com.example.qlphontro.R;

import java.util.List;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.ViewHolder> {
    List<ThanhToan> list;
    String thang;
    Context context;

    public ThongKeAdapter(String thang, Context context) {
        this.thang = thang;
        this.context = context;
        list = sqlHelper.getAllThanhToanTheoThang(thang);
    }

    public List<ThanhToan> getList() {
        return list;
    }

    public void setList(List<ThanhToan> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_phong_thong_ke, parent, false);
        ViewHolder viewHolders = new ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhToan thanhToan= list.get(position);
        holder.tvPhong.setText(thanhToan.getSoPhong()+"");
        holder.tvTong.setText(thanhToan.getTongTien()+" VNĐ");
        holder.tvSDT.setText(thanhToan.getSdtTKTT());
        holder.tvNgayTra.setText(thanhToan.getNgayThanhToan());

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPhong, tvTong, tvNgayTra, tvSDT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPhong= itemView.findViewById(R.id.tvPhongTK);
            tvTong= itemView.findViewById(R.id.tvTongCuaPhong);
            tvNgayTra= itemView.findViewById(R.id.tvNgayTra);
            tvSDT= itemView.findViewById(R.id.tvSDTNguoiTra);
        }
    }
}
