package com.example.bloomi.Account;

import static com.example.bloomi.MainActivity.user_login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bloomi.R;

public class FragmentAccountMe extends Fragment {

    TextView f_AccountMe_name;
    TextView f_AccountMe_countLikes,f_AccountMe_bio;
    TextView f_AccountMe_countFriends,f_AccountMe_countFollowers;
    EditText editText;
    ImageView imageView;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_account_me, container, false);
       Layid(view);
       editText.setVisibility(View.GONE);
       String text;
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editText.setVisibility(View.VISIBLE);
               f_AccountMe_bio.setText(editText.getText().toString());
           }
       });
       f_AccountMe_name.setText(user_login.getAccout().getFirstName()+" "+user_login.getAccout().getLastName());
       return view;
    }
    public void Layid(View view)
    {
        f_AccountMe_name=view.findViewById(R.id.f_AccountMe_name);
        f_AccountMe_countLikes=view.findViewById(R.id.f_AccountMe_countLikes);
        f_AccountMe_countFriends=view.findViewById(R.id.f_AccountMe_countFriends);
        f_AccountMe_countFollowers=view.findViewById(R.id.f_AccountMe_countFollowers);
        editText=view.findViewById(R.id.edit_vb);
        imageView=view.findViewById(R.id.f_AccountMe_editBio);
        f_AccountMe_bio=view.findViewById(R.id.f_AccountMe_bio);
    }
}