package com.example.bloomi.Login;

import android.content.Intent;
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

import com.example.bloomi.ConfirmAccount.FragmentConfirmAccount_ChooseSendingMethod;
import com.example.bloomi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class FragmentForgotPassword extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        ImageView f_ForgotPassword_ClearSearch = view.findViewById(R.id.f_ForgotPassword_ClearSearch);
        EditText f_ForgotPassword_enterPhoneOrEmail = view.findViewById(R.id.f_ForgotPassword_enterPhoneOrEmail);

        f_ForgotPassword_enterPhoneOrEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                f_ForgotPassword_ClearSearch.setVisibility(View.VISIBLE);
            }

        });
        f_ForgotPassword_ClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_ForgotPassword_enterPhoneOrEmail.setText("");
            }
        });

        TextView f_ForgotPassword_Cancel = view.findViewById(R.id.f_ForgotPassword_Cancel);
        f_ForgotPassword_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogIn.class);
                startActivity(intent);
            }
        });
        f_ForgotPassword_enterPhoneOrEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragment_forgotPassword, new FragmentConfirmAccount_ChooseSendingMethod());
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });
        return view;
    }


}