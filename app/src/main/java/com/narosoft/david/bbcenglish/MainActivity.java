package com.narosoft.david.bbcenglish;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabIndicator;
    private ViewPager mViewPager;
    private String[] titles = {"BBC六分钟","地道英语","BBC新闻","新闻词汇"};
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    void initView(){
        tabIndicator  = (TabLayout) findViewById(R.id.tab_head);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        fragments = new ArrayList<>();
        fragments.add(ContentFragment.newInstance());
        fragments.add(ContentFragment.newInstance());
        fragments.add(ContentFragment.newInstance());
        fragments.add(ContentFragment.newInstance());
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabIndicator.setupWithViewPager(mViewPager);
        tabIndicator.setTabMode(TabLayout.MODE_FIXED);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
