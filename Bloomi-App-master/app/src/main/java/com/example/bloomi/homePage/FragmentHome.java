package com.example.bloomi.homePage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloomi.Adapter_Manage.PostAdapter;
import com.example.bloomi.Login.SharedPrefManager;
import com.example.bloomi.Notification.FragmentNotification;
import com.example.bloomi.R;
import com.example.bloomi.SearchAccount.FragmentSearchAccount;
import com.example.bloomi.post_Bloom.create_new_post;

public class FragmentHome extends Fragment {

    RecyclerView recyclerView;
    PostAdapter postAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
       // System.out.println("CHECK LIST IN CALL");
        postAdapter= new PostAdapter(getActivity(), MainNav.list);
        System.out.println(MainNav.list);
        recyclerView = view.findViewById(R.id.f_home_rv);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ImageView f_home_searchAccount = view.findViewById(R.id.f_home_searchAccount);
        f_home_searchAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.Activity_Main, new FragmentSearchAccount());
                    fragmentTransaction.commit();
                }
            });
            RelativeLayout f_home_addPost = view.findViewById(R.id.f_home_addPost);
            f_home_addPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.Activity_Main, new create_new_post());
                    fragmentTransaction.commit(); }


            });
        TextView bloomiTxt= view.findViewById(R.id.Bloomitxt);
        bloomiTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), bloomiTxt);

                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_logout, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.add_account:
                                break;
                            case R.id.logout:
                                SharedPrefManager.getInstance(getActivity()).logout();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();

            }
        });
            ImageView f_home_notification = view.findViewById(R.id.f_home_notification);
            f_home_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.Activity_Main, new FragmentNotification());
                    fragmentTransaction.commit();
                }
            });
        return view;
    }

    // menu đăng xuất
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        if (v == btn){
//            getMenuInflater().inflate(R.menu.context_menu,menu);
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.context1:
//                Toast.makeText(getBaseContext(),"context1",Toast.LENGTH_LONG).show();
//        }
//        return super.onContextItemSelected(item);
//    }
}