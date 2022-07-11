package com.example.bloomi.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.MainActivity;
import com.example.bloomi.R;
import com.example.bloomi.Register.MainActivity_Resgiter;
import com.example.bloomi.homePage.MainNav;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {
    EditText mailOrPhone;
    EditText password;
    Call_API login=new Call_API(LogIn.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView LogIn_forgotPassword = findViewById(R.id.LogIn_ForgotPassword);
        LogIn_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_forgot_password();
            }
        });
        mailOrPhone=findViewById(R.id.LogIn_Email);
        password=findViewById(R.id.LogIn_Password);
        TextView Login_Login = findViewById(R.id.LogIn_LogIn);
        Login_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    final Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
                    if(!emailPattern.matcher(mailOrPhone.getText().toString().trim()).matches()){
                        Toast.makeText(getBaseContext(),"Invalid email",Toast.LENGTH_SHORT).show();

                    }
                    else
                    login.callAPISignIn(mailOrPhone.getText().toString(),password.getText().toString());

                }

        });
        Button goSignUp=findViewById(R.id.LogIn_CreateAccount);
        goSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goSignUpFromLogin=new Intent(LogIn.this, MainActivity_Resgiter.class);
                startActivity(goSignUpFromLogin);
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
    private void dialog_forgot_password(){
        Dialog dialogLogIn = new Dialog(this);
        dialogLogIn.setContentView(R.layout.dialog_forgot_password);
        dialogLogIn.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // constraintlayout must have
        Window window = dialogLogIn.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView forgotPassword = dialogLogIn.findViewById(R.id.d_forgotPassword_forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogIn.dismiss();
                RelativeLayout fragment_forgotPassword = findViewById(R.id.fragment_forgotPassword);
                fragment_forgotPassword.setVisibility(View.VISIBLE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_forgotPassword, new FragmentForgotPassword());
                fragmentTransaction.commit();

            }
        });
        TextView tryAgain = dialogLogIn.findViewById(R.id.d_forgotPassword_tryAgain);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogIn.dismiss();
            }
        });
        dialogLogIn.show();
    }

}