package com.example.bloomi.ConfirmAccount;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloomi.homePage.MainNav;
import com.example.bloomi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentConfirmAccount_SetPassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentConfirmAccount_SetPassword extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentConfirmAccount_SetPassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentForgotPassword_SetPassword.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentConfirmAccount_SetPassword newInstance(String param1, String param2) {
        FragmentConfirmAccount_SetPassword fragment = new FragmentConfirmAccount_SetPassword();
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