package com.example.bloomi.Register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class register_email extends Fragment {
    View view;
    Button nextOfSignUp2_E;
    EditText email_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_register_email, container, false);
        email_register= (EditText) view.findViewById(R.id.email_register);
        nextOfSignUp2_E=view.findViewById(R.id.Next_of_Signup2_E);
        nextOfSignUp2_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String check=email_register.getText().toString();
//                System.out.println(check);

                if (email_register.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Email errol!", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity_Resgiter.user.setEmail(email_register.getText().toString());
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.signUp2, new register_Password());
                    fragmentTransaction.commit();
                    nextOfSignUp2_E.setVisibility(View.GONE);
                }

            }
        });

        return view;
    }
}