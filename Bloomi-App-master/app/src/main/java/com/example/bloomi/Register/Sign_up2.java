package com.example.bloomi.Register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Sign_up2 extends Fragment {

    View mview;
    Button signUpWithGoogle;
    BottomNavigationView bottomNavigationView;

    AlertDialog.Builder signupgg;
    Button button_phone,button_email;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_phone_email, new register_phone());
        fragmentTransaction.commit();
        mview =inflater.inflate(R.layout.fragment_sign_up2, container, false);
        Layid();
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.email:
//                        setFragment(new register_email());
//                        break;
//                    case R.id.phone:
//                        setFragment(new register_phone());
//                        break;
//                }
//                return true;
//            }
//        });
        button_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layout_phone_email, new register_phone());
                fragmentTransaction.commit();
            }
        });
        button_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layout_phone_email, new register_email());
                fragmentTransaction.commit();
            }
        });
        signUpWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupgg=new AlertDialog.Builder(view.getContext());
                InitSigupGG();
            }
        });
        return mview;
    }
    private  void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.signUp2, fragment).commit();
    }
    private void InitSigupGG()
    {
        signupgg.setTitle("Choose your GooGle acccont");
        signupgg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //chuyen den gg
            }
        });
        signupgg.setNegativeButton("Cance", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        signupgg.setMessage("Ndatgs@gmail.com");
        signupgg.create();
        signupgg.show();
    }
    private void Layid()
    {
        //bottomNavigationView=mview.findViewById(R.id.botview);
        button_phone=mview.findViewById(R.id.button_phone_resigeter);
        button_email=mview.findViewById(R.id.button_email_register);
        signUpWithGoogle=mview.findViewById(R.id.Google_register);

    }


}