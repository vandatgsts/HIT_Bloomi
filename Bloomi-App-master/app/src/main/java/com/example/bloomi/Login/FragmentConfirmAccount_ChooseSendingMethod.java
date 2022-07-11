package com.example.bloomi.Login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.bloomi.R;


public class FragmentConfirmAccount_ChooseSendingMethod extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_account__choose_sending_method, container, false);
        TextView f_ConfirmAccount_CancelToLogIn1 = view.findViewById(R.id.f_ConfirmAccount_CancelToLogIn1);
        f_ConfirmAccount_CancelToLogIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getView().setVisibility(View.GONE);
            }
        });
        ImageView f_ConfirmAccount_BackToSearchAccount = view.findViewById(R.id.f_ConfirmAccount_BackToSearchAccount);
        f_ConfirmAccount_BackToSearchAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, new FragmentForgotPassword());
                fragmentTransaction.commit();
            }
        });
        TextView f_ConfirmAccount_ContinueToEnterCode = view.findViewById(R.id.f_ConfirmAccount_ContinueToEnterCode);
        f_ConfirmAccount_ContinueToEnterCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_enter_code();
            }
        });

        TextView f_ConfirmAccount_BackToLogin = view.findViewById(R.id.f_ConfirmAccount_BackToLogin);
        f_ConfirmAccount_BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogIn.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void dialog_enter_code(){
        Dialog dialogConfirmAccount = new Dialog(getActivity());
        dialogConfirmAccount.setContentView(R.layout.dialog_forgot_password_enter_code);
        dialogConfirmAccount.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // constraintlayout must have
        Window window = dialogConfirmAccount.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView f_ConfirmAccount_ContinueToSetPassword = dialogConfirmAccount.findViewById(R.id.f_ConfirmAccount_ContinueToSetPassword);
        f_ConfirmAccount_ContinueToSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirmAccount.dismiss();
                //RelativeLayout fragment_forgotPassword = view.findViewById(R.id.fragment_forgotPassword);
                //fragment_forgotPassword.setVisibility(View.VISIBLE);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_forgotPassword, new FragmentConfirmAccount_SetPassword());
               fragmentTransaction.commit();


            }
        });
        TextView d_forgotPassword_SendCodeAgain = dialogConfirmAccount.findViewById(R.id.d_forgotPassword_SendCodeAgain);
        d_forgotPassword_SendCodeAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //typing what to send code again
            }
        });
        dialogConfirmAccount.show();
    }
}