package com.example.bloomi.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.MainActivity;
import com.example.bloomi.R;

public class FragmentAccountFriend extends Fragment {

    TextView addFriend;
    ImageView back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_account_friend, container, false);

        Layid(view);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.remove(FragmentAccountFriend.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addFriend.getText().toString().equals("Waiting for response"))
                {
                    addFriend.setText("Add Friend");
                }
                else
                {
                    addFriend.setText("Waiting for response");
                    Call_API call_api=new Call_API(getActivity());
                    call_api.callAPIcreateNoTi(1, MainActivity.user_login.getAccout().getId(),1,0);
                    call_api.call_Api_AddFriend(MainActivity.user_login.getAccout().getId(),1);
                }

            }
        });
        return view;
    }
    void Layid(View view)
    {
        addFriend=view.findViewById(R.id.f_add_Friend);
        back=view.findViewById(R.id.f_back_friend);
    }

}