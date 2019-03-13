package com.gufra.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.gufra.gufra.R;

public class DrawerLayoutActivity extends AppCompatActivity{

    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigation;
    private BottomNavigationBar mBottomNavigationBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        initView();
        initBottomNavigation();

    }

    private void initView() {
        mToolBar = (Toolbar)findViewById(R.id.drawer_tool);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//1.显示
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolBar,
                R.string.drawer_start,R.string.drawer_end);//2.传入tool可以点击
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);//监听变化


        mNavigation = (NavigationView)findViewById(R.id.mynavigation);
        //navigation列表点击监听
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(getApplicationContext(),"点击的ID="+menuItem.getItemId()+"&&Title="+menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                showSnackBar();
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();//4.同步状态
    }

    private void initBottomNavigation(){
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.drawer_bottom_navigation);
        //设置mode
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);//有一点偏移
        //2.设置BackgroundStyle
//        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        //3.设置背景色
        mBottomNavigationBar.setBarBackgroundColor(android.R.color.holo_blue_light);
        //4.设置每个Item
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.yugan, "小鱼干").setActiveColorResource(android.R.color.holo_blue_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ying, "饮料").setActiveColorResource(android.R.color.holo_green_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.shutiao, "薯条").setActiveColorResource(android.R.color.holo_orange_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.jitui, "鸡腿").setActiveColorResource(android.R.color.holo_green_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.guazi, "瓜子").setActiveColorResource(android.R.color.holo_orange_dark));
        //5.初始化
        mBottomNavigationBar.initialise();
        //6.指定初始时刻的位置：
        mBottomNavigationBar.setFirstSelectedPosition(2).initialise();
        //动态改变位置
        //mBottomNavigationBar.selectTab(2);
        /*
        //7.设置角标
        BadgeItem badgeItem = new BadgeItem()
                .setBackgroundColorResource(android.R.color.holo_red_dark) //设置角标背景色
                .setText("5") //设置角标的文字
                .setTextColorResource(android.R.color.white) //设置角标文字颜色
                .setHideOnSelect(true); //在选中时是否隐藏角标
mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_5, "Item 5")
    .setActiveColorResource(android.R.color.holo_orange_dark)
    .setBadgeItem(badgeItem));
       */

        //8.监听item的切换
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //某个Item从未选中状态变为选中状态时回调
                Toast.makeText(getApplicationContext(),"onTabSelected"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(int position) {
                //某个Item从选中变为未选中时回调
                Toast.makeText(getApplicationContext(),"onTabUnselected"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(int position) {
                //某个Item已经处于选中状态，但是它又被再次点击了，那么回调这个函数。
                Toast.makeText(getApplicationContext(),"onTabReselected"+position,Toast.LENGTH_SHORT).show();
            }
        });
        //隐藏/显示
//        mBottomNavigationBar.hide(true);
//        mBottomNavigationBar.unHide(true);
    }

    //SnackBar
    private void showSnackBar(){
        Snackbar mSnackBar = Snackbar.make(mDrawerLayout,"MessageView",Snackbar.LENGTH_LONG);
        mSnackBar.setActionTextColor(Color.RED);
        mSnackBar.setAction("ActionView", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"showSnackBar",Toast.LENGTH_SHORT).show();

            }
        });
        mSnackBar.show();
    }
}
