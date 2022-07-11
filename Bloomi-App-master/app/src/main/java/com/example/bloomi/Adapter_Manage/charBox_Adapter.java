package com.example.bloomi.Adapter_Manage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloomi.Message.chatBox;
import com.example.bloomi.R;

import java.util.List;

public class charBox_Adapter extends RecyclerView.Adapter<charBox_Adapter.ViewHoder> {
    Context context;
    List<chatBox> chatBoxList;

    public charBox_Adapter(Context context, List<chatBox> chatBoxList) {
        this.context = context;
        this.chatBoxList = chatBoxList;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.chax_box,parent,false);
        ViewHoder viewHoder=new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull charBox_Adapter.ViewHoder holder, int position) {
        chatBox chatbox=chatBoxList.get(position);
        holder.avatar.setImageResource(chatbox.getAvatar());
        holder.Name.setText(chatbox.getName());
        holder.time.setText(chatbox.getTime());
        holder.message.setText(chatbox.getMessage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// chuyen activity sau khi bam
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatBoxList.isEmpty()?0:chatBoxList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView Name,time,message;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            avatar=itemView.findViewById(R.id.message_image);
            Name =itemView.findViewById(R.id.name_Message);
            time=itemView.findViewById(R.id.time_Message);
            message=itemView.findViewById(R.id.message_message);
        }
    }
}
