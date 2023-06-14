package com.example.bloomi.Register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.bloomi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register_phone#} factory method to
 * create an instance of this fragment.
 */
public class register_phone extends Fragment {
    View view;
    EditText phone_register;
    Button Next_of_signup2_P;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_register_phone, container, false);
        Next_of_signup2_P=view.findViewById(R.id.Next_of_Signup2_P);
        phone_register=(EditText) view.findViewById(R.id.Phone_register);
        Next_of_signup2_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String check=email_register.getText().toString();
//                System.out.println(check);
//                if(phone_register.getText().toString().isEmpty())
//                    Toast.makeText(getActivity(), "Phone number errol!", Toast.LENGTH_SHORT).show();
//                else
//                {
//                    MainActivity_Resgiter.user.setPhone(phone_register.getText().toString());
//                    FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.signUp2, new register_Password());
//                fragmentTransaction.commit();
//                Next_of_signup2_P.setVisibility(View.GONE);
//
//                }
                Toast.makeText(getActivity(),
                        R.string.baotridthoai_dky,
                        Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
//    private void SaveData()
//    {
//
//    }

}