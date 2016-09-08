package com.example.zhangpan.myretrofitdemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhangpan.myretrofitdemo.Fragment.ReAuFragment;
import com.example.zhangpan.myretrofitdemo.Fragment.ReNoAuFragment;
import com.example.zhangpan.myretrofitdemo.Fragment.ReRxFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"Retrofit+Auth", "Retrofit+NoAuth", "Retrofit+RxJAVA"};
    private ArrayAdapter arrayAdapter;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews(); //获取控件

        toolbar.setTitle("Toolbar");//设置Toolbar标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite)); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };

        supportFragmentManager = getSupportFragmentManager();

        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        lvLeftMenu.setAdapter(arrayAdapter);

        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        switchToReWithAuth();
                        break;
                    case 1:
                        switchToReWithoutAuth();
                        break;
                    case 2:
                        switchToReRx();
                        break;
                }
                lvLeftMenu.setItemChecked(i, true);
                setTitle(lvs[i]);
                mDrawerLayout.closeDrawers();
            }
        });

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
    }

    private void switchToReWithAuth() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new ReAuFragment()).commit();
    }

    private void switchToReWithoutAuth() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new ReNoAuFragment()).commit();
    }

    private void switchToReRx() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new ReRxFragment()).commit();
    }
}
