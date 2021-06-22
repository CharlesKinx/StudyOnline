package com.example.studyonline_client.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageViewFragmentAdapter extends FragmentPagerAdapter {
    private final int NUM = 3;

    private Fragment fragment;
    public PageViewFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public PageViewFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public PageViewFragmentAdapter(Fragment fragment,FragmentManager fm){
        super(fm);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM;
    }
}
