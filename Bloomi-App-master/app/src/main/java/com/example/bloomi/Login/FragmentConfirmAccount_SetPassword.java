package com.example.bloomi.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.R;
import com.example.bloomi.homePage.MainNav;


public class FragmentConfirmAccount_SetPassword extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_account__set_password, container, false);
        TextView f_ConfirmAccount_CancelToLogIn2 = view.findViewById(R.id.f_ConfirmAccount_CancelToLogIn2);
        f_ConfirmAccount_CancelToLogIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getView().setVisibility(View.GONE);

            }
        });
        ImageView f_ConfirmAccount_BackToSendingMethod= view.findViewById(R.id.f_ConfirmAccount_BackToSendingMethod);
        f_ConfirmAccount_BackToSendingMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_forgotPassword, new FragmentConfirmAccount_ChooseSendingMethod());
                fragmentTransaction.commit();
            }
        });
        TextView f_ConfirmAccount_LogIn = view.findViewById(R.id.f_ConfirmAccount_LogIn);
        f_ConfirmAccount_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHomeFromLogIn = new Intent(getActivity(), MainNav.class);
                startActivity(goToHomeFromLogIn);

            }
        });
        return view;
    }
}