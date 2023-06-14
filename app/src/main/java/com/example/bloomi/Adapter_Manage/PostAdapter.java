package com.example.bloomi.Adapter_Manage;

import static com.example.bloomi.MainActivity.user_login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloomi.Account.FragmentAccountFriend;
import com.example.bloomi.Account.menu_profile;
import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.R;
import com.example.bloomi.post_Bloom.OnePost;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<OnePost> posts;
    Context context;
    String avatar;
    Call_API call_api;
    public PostAdapter(Context context, List<OnePost> posts){
        this.context=context;
        this.posts=posts;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_post, parent, false);
        call_api=new Call_API(context);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OnePost onePost = posts.get(position);
        holder.name.setText(onePost.getName());
        // chua có anh nenn vo
//        Picasso.get().load(avatar).into(holder.avt);
//        Picasso.get().load(onePost.getImage()).into(holder.image);
        // load anh tu url
       // Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/bloomi-a2ac4.appspot.com/o/ID%20Account%3A%202%2Fimages%2F19f00c8f-99ed-4ce5-969e-31f70d61cf2d?alt=media&token=d6ded5a7-cfd9-42ff-b0de-5320e05dc130").into(holder.image);
        Picasso.get().load(onePost.getImage()).into(holder.image);
       // Picasso.get().load(onePost.getAvatarimage()).into(holder.avt);
        holder.content.setText(onePost.getContent());
        System.out.println(onePost.getContent());
        holder.likes.setText(" " +onePost.getLikes());
        holder.comment.setText(" " +onePost.getComment());
        holder.onePost_heartOrNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.onePost_heartOrNot.isChecked())
                {
                    holder.likes.setText(" " +(onePost.getLikes()+1));
                    call_api.callApiReactionPost(user_login.getAccout().getId(),onePost.getIdpost());
                    call_api.callAPIcreateNoTi(user_login.getAccout().getId(),1,3,onePost.getIdpost());
                }
                else
                {
                    holder.likes.setText(" " +onePost.getLikes());
                    call_api.callApiDeleteReactionPost(user_login.getAccout().getId(),onePost.getIdpost());
                }

            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.Activity_Main,new FragmentAccountFriend());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
