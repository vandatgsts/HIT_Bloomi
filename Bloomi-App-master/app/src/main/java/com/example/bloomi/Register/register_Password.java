package com.example.bloomi.Register;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.Login.FragmentForgotPassword;
import com.example.bloomi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class register_Password extends Fragment {

    View mview;
    Button Next_of_c_Password;
    EditText pass,re_pass;
    //Call_API call_api=new Call_API(getActivity());
    //User user=MainActivity_Resgiter.user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mview= inflater.inflate(R.layout.fragment_register__password, container, false);
        pass=mview.findViewById(R.id.create_newPassword);
        re_pass=mview.findViewById(R.id.reCreare_NewPassWord);
        Next_of_c_Password=mview.findViewById(R.id.Next_of_new_PassWord);
        Next_of_c_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals(re_pass.getText().toString()) ==false || pass.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(),"Password errol!",Toast.LENGTH_SHORT).show();
                }
                else
                {   //dialog_forgot_password();
                    MainActivity_Resgiter.user.setPassword(pass.getText().toString());
                    MainActivity_Resgiter.user.setPassword(pass.getText().toString());
                    Toast.makeText(getActivity(),"Register Concuragtion",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mview;
    }
    private void dialog_forgot_password(){
        Dialog dialogLogIn = new Dialog(getActivity());
        dialogLogIn.setContentView(R.layout.dialog_register_enter_code);
        dialogLogIn.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // constraintlayout must have
        Window window = dialogLogIn.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogLogIn.show();
    }
}