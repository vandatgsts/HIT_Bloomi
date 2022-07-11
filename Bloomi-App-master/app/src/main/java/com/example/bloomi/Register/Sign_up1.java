package com.example.bloomi.Register;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloomi.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Sign_up1 extends Fragment  {
    private View mview;
    EditText firstName,lastName;
    ImageButton settimeBirthday;
    TextView getTimeBirthday;
    Button next;
    RadioButton Male,Female;
    IsendDataListener mIsenDataListener;
    DatePickerDialog datePickerDialog;
    public interface IsendDataListener
    {
        void sendData(User user);
    }
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        mIsenDataListener=(IsendDataListener) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview= inflater.inflate(R.layout.fragment_sign_up1, container, false);
        Layid(mview);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Yourname errol!".toString(), Toast.LENGTH_SHORT).show();
                } else {
                    SaveData();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.signUp1, new Sign_up2());
                    fragmentTransaction.commit();
                    next.setVisibility(View.GONE);
                    settimeBirthday.setVisibility(View.GONE);
                }
            }
        });
        initTime();
        settimeBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    buttonOpenTime();
            }
        });
        return mview;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void Layid(View view)
    {

        firstName=view.findViewById(R.id.First_name);
        lastName=view.findViewById(R.id.Last_name);
        settimeBirthday=view.findViewById(R.id.settime);
        getTimeBirthday=view.findViewById(R.id.gettime);
        next=view.findViewById(R.id.Next_of_Signup1);
        Male=view.findViewById(R.id.radioButton_Male);
        Female=view.findViewById(R.id.radioButton_Female);
    }
    private void initTime()
    {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int years, int month, int day) {
                month=month+1;
                String date= makeDateString(day,month,years);
                getTimeBirthday.setText(date);
            }
        };
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int style=AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog =new DatePickerDialog(this.getActivity(),style,dateSetListener,year,month,day);
    }
    private String makeDateString(int day, int month, int years)
    {
        return day+" "+makeMonth(month)+" "+years;
    }
    private void buttonOpenTime()
    {
        datePickerDialog.show();
    }
    private String makeMonth(int month)
    {
        switch (month)
        {
            case 1:return "January";
            case 2:return "February";
            case 3:return "March";
            case 4:return "Aprill";
            case 5:return "May";
            case 6:return "June";
            case 7:return "July";
            case 8:return "August";
            case 9:return "September";
            case 10:return "October";
            case 11:return "November";
            case 12:return "Decembe";
        }
        return "123";
    }
    private void SaveData()
    {

        MainActivity_Resgiter.user.setFirstName(firstName.getText().toString());
        MainActivity_Resgiter.user.setLastName(lastName.getText().toString());
        MainActivity_Resgiter.user.setBirthDay(getTimeBirthday.getText().toString());
        if(Male.isChecked()) MainActivity_Resgiter.user.setSex("Male");
        else MainActivity_Resgiter.user.setSex("Female");
    }




}