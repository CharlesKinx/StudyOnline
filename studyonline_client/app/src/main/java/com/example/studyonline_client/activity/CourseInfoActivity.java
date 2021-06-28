package com.example.studyonline_client.activity;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.studyonline_client.R;
import com.example.studyonline_client.fragment.CourseArrangementFragment;
import com.example.studyonline_client.fragment.CourseEvaluateFragment;
import com.example.studyonline_client.fragment.CourseInfoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CourseInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private List<androidx.fragment.app.Fragment> fragmentList = new ArrayList<>();
    private TextView textView;
    private Button beginStudy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]
                        {Manifest.permission.CAMERA}, 1);
            }
        }
        fragmentList.add(new CourseInfoFragment());
        fragmentList.add(new CourseEvaluateFragment());
        fragmentList.add(new CourseArrangementFragment());
        initView();

        textView.setOnClickListener(this);
        beginStudy.setOnClickListener(this);

    }



    private void initView() {
        textView = findViewById(R.id.course_back);
        beginStudy = findViewById(R.id.btn_begin_study);

        final TabLayout tab_layout = findViewById(R.id.tab_layout);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        MyAdapter fragmentAdapter = new  MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中某个tab
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //当tab从选择到未选择
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //已经选中tab后的重复点击tab
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab_layout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_begin_study:
                Intent intent = new Intent(CourseInfoActivity.this,CourseLiveActivity.class);
                startActivity(intent);
                break;
            case R.id.course_back:
                finish();
                break;
        }
    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public androidx.fragment.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

    }
}
