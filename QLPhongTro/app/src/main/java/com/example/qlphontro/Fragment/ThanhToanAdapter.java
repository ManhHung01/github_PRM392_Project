package com.example.qlphontro.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Model.ThanhToan;
import com.codeandroid.qlphongtro.R;

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

   
}
