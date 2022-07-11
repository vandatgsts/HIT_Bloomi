package com.example.bloomi.homePage;



import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloomi.Adapter_Manage.PostAdapter;
import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.MainActivity;
import com.example.bloomi.Notification.FragmentNotification;
import com.example.bloomi.R;
import com.example.bloomi.SearchAccount.FragmentSearchAccount;
import com.example.bloomi.post_Bloom.OnePost;
import com.example.bloomi.post_Bloom.create_new_post;
import com.example.bloomi.uses_manage;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        System.out.println("CHECK LIST IN CALL");
        PostAdapter postAdapter = new PostAdapter(getActivity(), MainNav.list, "NguyenVanDat");
        System.out.println(MainNav.list);
        recyclerView = view.findViewById(R.id.f_home_rv);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ImageView f_home_searchAccount = view.findViewById(R.id.f_home_searchAccount);
        f_home_searchAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragmentContainer_main, new FragmentSearchAccount());
                    fragmentTransaction.commit();
                }
            });
            RelativeLayout f_home_addPost = view.findViewById(R.id.f_home_addPost);
            f_home_addPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Check lenh");
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.checkvui, new create_new_post());
                    fragmentTransaction.commit(); }


            });

            ImageView f_home_notification = view.findViewById(R.id.f_home_notification);
            f_home_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragmentContainer_main, new FragmentNotification());
                    fragmentTransaction.commit();
                }
            });
        return view;
    }

    // menu đăng xuất
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v == btn){
            getMenuInflater().inflate(R.menu.context_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.context1:
                Toast.makeText(getBaseContext(),"context1",Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }
}