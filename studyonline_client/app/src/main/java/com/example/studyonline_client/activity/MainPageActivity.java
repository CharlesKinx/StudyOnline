package com.example.studyonline_client.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;
import com.example.studyonline_client.fragment.CourseLiveFragment;
import com.example.studyonline_client.fragment.FriendContactFragment;
import com.example.studyonline_client.fragment.PersonalCenterFragment;
import com.example.studyonline_client.fragment.ScoreAnalysisFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPageActivity extends AppCompatActivity {

    private CourseLiveFragment courseLiveFragment;
    private FriendContactFragment friendContactFragment;
    private PersonalCenterFragment personalCenterFragment;
    private ScoreAnalysisFragment scoreAnalysisFragment;

    private TextView textView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_item1:
                    showFragment(R.id.navigation_item1);
                    return true;
                case R.id.navigation_item2:
                    showFragment(R.id.navigation_item2);
                    return true;
                case R.id.navigation_item3:
                    showFragment(R.id.navigation_item3);
                    return true;
                case R.id.navigation_item4:
                    showFragment(R.id.navigation_item4);
                    return true;
            }
            return false;
        }
    } ;


    private void initView(){
        courseLiveFragment = new CourseLiveFragment();
        friendContactFragment = new FriendContactFragment();
        personalCenterFragment = new PersonalCenterFragment();
        scoreAnalysisFragment = new ScoreAnalysisFragment();
        textView = findViewById(R.id.title_fragment);
        FragmentTransaction  fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content,courseLiveFragment).add(R.id.content, personalCenterFragment)
                .add(R.id.content,friendContactFragment).add(R.id.content,scoreAnalysisFragment);
        fragmentTransaction.hide(courseLiveFragment).hide(personalCenterFragment)
                .hide(friendContactFragment).hide(scoreAnalysisFragment);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        showFragment(R.id.navigation_item1);
    }

    private void showFragment(int item){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (item){
            case R.id.navigation_item1:
                fragmentTransaction.hide(personalCenterFragment).hide(scoreAnalysisFragment).hide(friendContactFragment);
                fragmentTransaction.show(courseLiveFragment);
                textView.setText("课程中心");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.navigation_item2:
                fragmentTransaction.hide(courseLiveFragment).hide(scoreAnalysisFragment).hide(personalCenterFragment);
                fragmentTransaction.show(friendContactFragment);
                textView.setText(" 交流 ");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.navigation_item3:
                fragmentTransaction.hide(courseLiveFragment).hide(friendContactFragment).hide(personalCenterFragment);
                fragmentTransaction.show(scoreAnalysisFragment);
                textView.setText(" 成绩 ");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.navigation_item4:
                fragmentTransaction.hide(courseLiveFragment).hide(friendContactFragment).hide(scoreAnalysisFragment);
                fragmentTransaction.show(personalCenterFragment);
                textView.setText("个人中心");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        initView();
        BottomNavigationView bottomNavigationView  = findViewById(R.id.bv);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

}
