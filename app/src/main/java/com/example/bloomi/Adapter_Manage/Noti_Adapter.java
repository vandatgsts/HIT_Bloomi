package com.example.bloomi.Adapter_Manage;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.Notification.Notification;
import com.example.bloomi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Noti_Adapter extends RecyclerView.Adapter<Noti_Adapter.noti_Viewhoder> {
    Context context;
    public static List<Notification> Noti_list;
    Call_API call_api;
    public Noti_Adapter(Context context, List<Notification> noti_list) {
        this.context = context;
        Noti_list = noti_list;
    }



    @NonNull
    @Override
    public noti_Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_noti, parent, false);
        call_api=new Call_API(context);
        return new noti_Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull noti_Viewhoder holder, int position) {
        Notification oneNoti=Noti_list.get(position);
       // Picasso.get().load(oneNoti.getNoti_Image()).into(holder.noti_avatar);
        holder.noti_action.setText(oneNoti.getNoti_action());
        holder.noti_name.setText(oneNoti.getNoti_name());
    }

    @Override
    public int getItemCount() {
        return  Noti_list.isEmpty()?0: Noti_list.size();
    }

    public class noti_Viewhoder extends RecyclerView.ViewHolder {
        ImageView noti_avatar;
        TextView noti_name,noti_action;
        public noti_Viewhoder(@NonNull View itemView) {
            super(itemView);
            noti_avatar=itemView.findViewById(R.id.f_noti_avatar);
            noti_name=itemView.findViewById(R.id.f_noti_name);
            noti_action=itemView.findViewById(R.id.f_noti_action);
        }
    }
}
