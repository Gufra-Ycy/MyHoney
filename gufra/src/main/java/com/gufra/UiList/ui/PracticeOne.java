package com.gufra.UiList.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gufra.UiList.widgets.BasicView;
import com.gufra.UiList.widgets.HistogramView;
import com.gufra.UiList.widgets.PathView;
import com.gufra.UiList.widgets.PieChartViw;
import com.gufra.gufra.R;

import java.util.ArrayList;
import java.util.List;

public class PracticeOne extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice1);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout)findViewById(R.id.p1_tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.p1_viewPager);
        List<String>titles = new ArrayList<>();
        titles.add("基础Basic图形");
        titles.add("path");
        titles.add("直方图");
        titles.add("饼图");
        List<com.example.yuanping.uilist.ui.UIFragment>fragments = new ArrayList<>();
        fragments.add(new com.example.yuanping.uilist.ui.UIFragment(new BasicView(this)));
        fragments.add(new com.example.yuanping.uilist.ui.UIFragment(new PathView(this)));
        fragments.add(new com.example.yuanping.uilist.ui.UIFragment(new HistogramView(this)));
        fragments.add(new com.example.yuanping.uilist.ui.UIFragment(new PieChartViw(this)));

        UIPagerAdapter adapter = new UIPagerAdapter(titles,fragments,getSupportFragmentManager());

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
