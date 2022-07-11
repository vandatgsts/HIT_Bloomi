package com.example.bloomi.Adapter_Manage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloomi.Notification.Notification;

import java.util.List;

public class Noti_Adapter extends RecyclerView.Adapter<Noti_Adapter.noti_Viewhoder> {
    Context context;
    List<Notification> Noti_list;

    public Noti_Adapter(Context context, List<Notification> noti_list) {
        this.context = context;
        Noti_list = noti_list;
    }

    @NonNull
    @Override
    public noti_Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull noti_Viewhoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class noti_Viewhoder extends RecyclerView.ViewHolder {
        ImageView noti_avatar;

        public noti_Viewhoder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
