package com.example.bloomi.Register;

import static com.example.bloomi.MainActivity.user_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.Login.LogIn;
import com.example.bloomi.MemoryData;
import com.example.bloomi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_Resgiter extends AppCompatActivity implements Sign_up1.IsendDataListener {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bloomi-a2ac4-default-rtdb.firebaseio.com/");

    public static User user=new User();
    //public Call_API call_api=new Call_API(MainActivity_Resgiter.this);
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
                Intent intent=new Intent(MainActivity_Resgiter.this, LogIn.class);
                System.out.println(user.getFirstName());
                startActivity(intent);

            }
        });

    }
    public void regiterfirebaseretime()
    {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

             //   progressDialog.dismiss();
               {
                    databaseReference.child("ID_Account").child(""+user_login.getAccout().getId()).child("email").setValue(user_login.getAccout().getEmail());
                    //databaseReference.child("ID_Account").child(""+user_login.getAccout().getId()).child("name").setValue(user_login.getAccout().getPhone());
                    databaseReference.child("ID_Account").child(""+user_login.getAccout().getId()).child("profile_pic").setValue("");

                    // save mobile to memory
                    MemoryData.saveData(user_login.getAccout().getEmail(), MainActivity_Resgiter.this);

                    // save name to memory
                    MemoryData.saveName(user_login.getAccout().getFirstName()+user_login.getAccout().getLastName(), MainActivity_Resgiter.this);

                   // Toast.makeText(Register.this, "Success", Toast.LENGTH_LONG).show();



                    finish();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    @Override
    public void sendData(User user) {

    }
}