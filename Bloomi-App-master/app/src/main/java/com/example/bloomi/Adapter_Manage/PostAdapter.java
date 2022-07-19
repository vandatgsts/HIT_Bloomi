package com.example.bloomi.Adapter_Manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloomi.R;
import com.example.bloomi.post_Bloom.OnePost;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<OnePost> posts;
    Context context;
    String avatar;
    String userName;
    public PostAdapter(Context context, List<OnePost> posts, String userName){
        this.context=context;
        this.posts=posts;
        this.userName=userName;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_post, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OnePost onePost = posts.get(position);
        holder.name.setText(userName);
        // chua có anh nenn vo
//        Picasso.get().load(avatar).into(holder.avt);
//        Picasso.get().load(onePost.getImage()).into(holder.image);
        // load anh tu url
        Picasso.get().load(onePost.getImage()).into(holder.image);
        holder.content.setText(onePost.getContent());
        holder.likes.setText(" " +onePost.getLikes());
        holder.comment.setText(" " +onePost.getComment());
        holder.onePost_heartOrNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.onePost_heartOrNot.isChecked())
                {
                    holder.likes.setText(" " +(onePost.getLikes()+1));
                }
                else
                    holder.likes.setText(" " +onePost.getLikes());
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.isEmpty()?0: posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avt, image;
        TextView content, name,likes,comment;
        CheckBox onePost_heartOrNot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avt=itemView.findViewById(R.id.onePost_avt);
            image=itemView.findViewById(R.id.onePost_image);
            content=itemView.findViewById(R.id.onePost_content);
            name=itemView.findViewById(R.id.onePost_name);
            likes=itemView.findViewById(R.id.onePost_countHeart);
            comment=itemView.findViewById(R.id.onePost_countComment);
            onePost_heartOrNot=itemView.findViewById(R.id.onePost_heartOrNot);
        }
    }
}
