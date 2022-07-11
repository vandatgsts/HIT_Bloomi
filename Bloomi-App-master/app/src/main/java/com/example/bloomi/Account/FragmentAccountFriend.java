package com.example.bloomi.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.bloomi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAccountFriend#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccountFriend extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_friend, container, false);
    }
}