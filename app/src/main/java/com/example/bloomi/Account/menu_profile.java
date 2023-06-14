package com.example.bloomi.Account;

import static android.app.Activity.RESULT_OK;

import static com.example.bloomi.Account.FragmentAccountMe.avatar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloomi.MainActivity;
import com.example.bloomi.Notification.FragmentNotification;
import com.example.bloomi.R;
import com.example.bloomi.Register.Sign_up1;
import com.example.bloomi.Register.Sign_up2;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;


public class menu_profile extends Fragment {
    private static final int PICK_IMAGE_REQUEST =100;
    Uri filePath;
    ImageView backToMeAcc;
    TextView f_menu_NameAcc,f_menu_gender,f_menu_birthday;
    TextView f_Update_Profile_Picture,f_Update_Cover;
    ImageView f_menu_update_gender,f_menu_update_birthday;
    DatePickerDialog datePickerDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview= inflater.inflate(R.layout.fragment_menu_profile, container, false);
        layid(mview);
        backToMeAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.remove(menu_profile.this);
                fragmentTransaction.commit();
            }
        });
        f_menu_birthday.setText(MainActivity.user_login.getAccout().getBirthday());
        initTime();
        f_menu_update_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOpenTime();
            }
        });
        f_Update_Profile_Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImageFromGallery();
            }
        });
        return mview;
    }
    private void chooseImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {       filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.requireActivity().getContentResolver(), filePath);
                avatar.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    void layid(View view)
    {
        backToMeAcc=view.findViewById(R.id.f_back_to_Me);
        f_menu_birthday=view.findViewById(R.id.f_menu_birthday);
        f_menu_gender=view.findViewById(R.id.f_menu_gender);
        f_menu_NameAcc=view.findViewById(R.id.f_menu_NameACC);
        f_Update_Cover=view.findViewById(R.id.f_Update_Cover);
        f_Update_Profile_Picture=view.findViewById(R.id.f_Update_Profile_Picture);
        f_menu_update_gender=view.findViewById(R.id.f_update_gender);
        f_menu_update_birthday=view.findViewById(R.id.f_menu_update_brithday);
    }
    private void initTime()
    {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int years, int month, int day) {
                month=month+1;
                String date= makeDateString(day,month,years);
                f_menu_birthday.setText(date);
            }
        };
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_LIGHT;
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

}