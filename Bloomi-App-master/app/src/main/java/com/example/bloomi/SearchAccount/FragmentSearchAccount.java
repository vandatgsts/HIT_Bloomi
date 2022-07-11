package com.example.bloomi.SearchAccount;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloomi.R;
import com.example.bloomi.homePage.FragmentHome;




public class FragmentSearchAccount extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_account, container, false);
        ImageView f_SearchAccount_clearInput= view.findViewById(R.id.f_SearchAccount_clearInput);
        EditText f_SearchAccount_input = view.findViewById(R.id.f_SearchAccount_input);
        f_SearchAccount_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                f_SearchAccount_clearInput.setVisibility(View.VISIBLE);
            }
        });
        f_SearchAccount_clearInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_SearchAccount_input.setText("");
            }
        });
        TextView f_SearchAccount_cancelToHome = view.findViewById(R.id.f_SearchAccount_cancelToHome);
        f_SearchAccount_cancelToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragmentContainer_main, new FragmentHome());
                fragmentTransaction.commit();
            }
        });
        f_SearchAccount_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragmentContainer_main, new FragmentSearchAccountResult());
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });
        return view;
    }
}