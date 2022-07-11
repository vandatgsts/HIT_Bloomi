package com.example.bloomi.ConfirmAccount;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloomi.Login.FragmentForgotPassword;
import com.example.bloomi.Login.LogIn;
import com.example.bloomi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentConfirmAccount_ChooseSendingMethod#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentConfirmAccount_ChooseSendingMethod extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentConfirmAccount_ChooseSendingMethod() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentConfirmAccount_ChooseSendingMethod.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentConfirmAccount_ChooseSendingMethod newInstance(String param1, String param2) {
        FragmentConfirmAccount_ChooseSendingMethod fragment = new FragmentConfirmAccount_ChooseSendingMethod();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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