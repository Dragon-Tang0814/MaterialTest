package com.example.materialtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Pirate[] pirates={new Pirate("路飞",R.drawable.lufei),
            new Pirate("索隆",R.drawable.suolong),new Pirate("山治",R.drawable.shanzhi),
            new Pirate("娜美",R.drawable.namei),new Pirate("乌索普",R.drawable.wusuopu),
            new Pirate("罗宾",R.drawable.luobin),new Pirate("乔巴",R.drawable.qiaoba),
            new Pirate("布鲁克",R.drawable.buluke),new Pirate("弗兰奇",R.drawable.fulanqi),
    };
    private List<Pirate> pirateList=new ArrayList<>();
    private PirateAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary); //设置下拉刷新进度条的颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            //当触发的时候就会回调onRefresh()方法
            public void onRefresh() {
                refreshFruits();
            }
        });
        initPirate();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new PirateAdapter(pirateList);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //线程先沉睡两秒钟,因为本地刷新速度太快了看不出来
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //将线程切回主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //重新加载海贼
                        initPirate();
                        //通知数据发生了变化
                        adapter.notifyDataSetChanged();
                        //表示刷新事件结束,并隐藏刷新进度条
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initPirate() {
        pirateList.clear();
        for (int i = 0; i < 35; i++) {
            Random random = new Random();
            int index = random.nextInt(pirates.length);
            pirateList.add(pirates[index]);
        }
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"You clicked Backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You clicked Delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Settings",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}