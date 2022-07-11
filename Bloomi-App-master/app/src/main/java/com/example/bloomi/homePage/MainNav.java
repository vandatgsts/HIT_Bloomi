package com.example.bloomi.homePage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.bloomi.Adapter_Manage.Menu_Adapter;
import com.example.bloomi.CallAPI.Call_API;
import com.example.bloomi.MainActivity;
import com.example.bloomi.R;
import com.example.bloomi.databinding.ActivityMainNavBinding;
import com.example.bloomi.post_Bloom.OnePost;
import com.example.bloomi.uses_manage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainNav extends AppCompatActivity {
    ActivityMainNavBinding binding;
    public static List<OnePost> list=new ArrayList<>();
    uses_manage user= MainActivity.user_login.getAccout();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Thread callPost=new Thread(this::callPostham);
//        callPost.start();
//        callPost.getPriority();
        callPostham();
        Menu_Adapter menu_adapter=new Menu_Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.fragmentContainerMain.setAdapter(menu_adapter);
        binding.fragmentContainerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0 :
                        binding.navView.getMenu().findItem(R.id.Nav_Home).setChecked(true);
                        break;
                    case 1 :
                        binding.navView.getMenu().findItem(R.id.Nav_Group).setChecked(true);
                        break;
                    case 2:
                        binding.navView.getMenu().findItem(R.id.Nav_message).setChecked(true);
                        break;
                    case 3 :
                        binding.navView.getMenu().findItem(R.id.Nav_Account).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Nav_Home:
                        binding.fragmentContainerMain.setCurrentItem(0);
                        break;
                    case R.id.Nav_Group:
                        binding.fragmentContainerMain.setCurrentItem(1);
                        break;
                    case R.id.Nav_message:
                        binding.fragmentContainerMain.setCurrentItem(2);
                        break;
                    case R.id.Nav_Account:
                        binding.fragmentContainerMain.setCurrentItem(3);
                        break;

                }

                return false;
            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
    public void callPostham()
    {
        Call_API call_api=new Call_API(this);
        call_api.call_Api_Get_Post(user.getId());
    }
}