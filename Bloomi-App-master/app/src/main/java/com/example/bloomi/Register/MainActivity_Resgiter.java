package com.example.bloomi.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.Login.LogIn;
import com.example.bloomi.R;

public class MainActivity_Resgiter extends AppCompatActivity implements Sign_up1.IsendDataListener {
    public static User user=new User();
    public Call_API call_api=new Call_API(MainActivity_Resgiter.this);
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resigter);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flag1,new Sign_up1());
        fragmentTransaction.commit();
        back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_api.call_API_SignUp(user);
                Intent intent=new Intent(MainActivity_Resgiter.this, LogIn.class);
                System.out.println(user.getFirstName());
                startActivity(intent);

            }
        });

    }


    @Override
    public void sendData(User user) {

    }
}