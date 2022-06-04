package com.example.chatapp.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerA extends FragmentStateAdapter {

    public ViewPagerA(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerA(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerA(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UserMesesgerFragment();
            case 1:
                return new UserFragment();
            default:
                return new UserMesesgerFragment();
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
