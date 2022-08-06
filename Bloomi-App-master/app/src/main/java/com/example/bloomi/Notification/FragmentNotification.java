package com.example.bloomi.Notification;

import static com.example.bloomi.MainActivity.user_login;
import static com.example.bloomi.homePage.MainNav.listnoti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bloomi.Adapter_Manage.Noti_Adapter;
import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.R;

import java.util.List;


public class FragmentNotification extends Fragment {

    Call_API call_api;

    RecyclerView recyclerView;
    ImageView f_noti_backToHome;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        call_api=new Call_API(getActivity());
        call_api.callApigetNoti(user_login.getAccout().getId());
        Noti_Adapter noti_adapter=new Noti_Adapter(getActivity(),listnoti);
        recyclerView=view.findViewById(R.id.f_home_rv);
        recyclerView.setAdapter(noti_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        f_noti_backToHome=view.findViewById(R.id.f_noti_backToHome);
        f_noti_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.remove(FragmentNotification.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}