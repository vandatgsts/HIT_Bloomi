package com.example.bloomi.Register;

import static com.example.bloomi.Register.MainActivity_Resgiter.user;

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
    Call_API call_api;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mview= inflater.inflate(R.layout.fragment_register__password, container, false);
        pass=mview.findViewById(R.id.create_newPassword);
        re_pass=mview.findViewById(R.id.reCreare_NewPassWord);
        Next_of_c_Password=mview.findViewById(R.id.Next_of_new_PassWord);
        call_api=new Call_API(getActivity());
        Next_of_c_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals(re_pass.getText().toString()) ==false || pass.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(),"Password errol!",Toast.LENGTH_SHORT).show();
                }
                else
                {   call_api.call_API_SignUp(user);
                    user.setPassword(pass.getText().toString());
                    user.setPassword(pass.getText().toString());
                    dialog_forgot_password();


                }
            }
        });
        return mview;
    }
    private void dialog_forgot_password(){
        Dialog dialogRegister = new Dialog(getActivity());
        dialogRegister.setContentView(R.layout.dialog_register_enter_code);
        dialogRegister.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // constraintlayout must have
        Window window = dialogRegister.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogRegister.show();
        EditText d_Register_code=dialogRegister.findViewById(R.id.d_Register_code);
        Button Clear_Resigter_Codee=dialogRegister.findViewById(R.id.Clear_Resigter_Codee);
        Clear_Resigter_Codee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_api.postCodeToAPI(d_Register_code.getText().toString());
            }
        });
    }
}