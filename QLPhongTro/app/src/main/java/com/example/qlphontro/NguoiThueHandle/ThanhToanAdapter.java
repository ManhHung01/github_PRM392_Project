package com.example.qlphontro.NguoiThueHandle;

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

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.ViewHolder> {
    List<ThanhToan> list;
    Context  context;

    public List<ThanhToan> getList() {
        return list;
    }

    public void setList(List<ThanhToan> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ThanhToanAdapter(List<ThanhToan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_lich_su_tt, parent, false);
        ViewHolder viewHolders = new ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhToan thanhToan= list.get(position);
        holder.tvNgay.setText("Date of payment: "+thanhToan.getNgayThanhToan());
        holder.tvTien.setText("Fee: "+thanhToan.getTongTien()+" VNĐ");
        holder.tvNoiDung.setText("Content: " + thanhToan.getNoiDung());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgay, tvTien, tvNoiDung;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgay= itemView.findViewById(R.id.tvNgayTT);
            tvTien= itemView.findViewById(R.id.tvTienTT);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
        }
    }
}
