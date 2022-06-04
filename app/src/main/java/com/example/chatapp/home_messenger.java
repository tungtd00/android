package com.example.chatapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.chatapp.Fragment.ViewPagerA;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class home_messenger extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager2 viewPager;
    private ViewPagerA viewPagerA;
    private TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger);
        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.pager);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPagerA = new ViewPagerA(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewPagerA);
        textView = findViewById(R.id.text_home);


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.page_1).setChecked(true);
                        textView.setText("Chat");
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.page_2).setChecked(true);
                        textView.setText("Users");
                        break;
                }
            }
        });
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int idItem = item.getItemId();
                switch (idItem) {
                    case R.id.page_1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.page_2:
                        viewPager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });

    }


}
