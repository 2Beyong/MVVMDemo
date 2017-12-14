package com.hongon.solarmvvmdemo;

import android.app.FragmentManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hongon.solarmvvmdemo.converter.ConverterListFragment;

public class MainActivity extends AppCompatActivity {
    //--------------listener functions----------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initial
        setupAppBar();
        setupNavigationView();
    }
    //--------------appbar setting--------------

    /*设置AppBar*/
    private void setupAppBar()
    {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layer);
        Toolbar mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        //图标大小没有解决
        mtoolbar.setNavigationIcon(R.drawable.ic_settings_applications_48pt);

        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //菜单监听
        mtoolbar.setOnMenuItemClickListener(menuListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        MenuItem login =menu.findItem(R.id.login_btn);
        MenuItem logout=menu.findItem(R.id.logout_btn);
        if(isLogin==true)
        {
         login.setVisible(false);
         logout.setVisible(true);
        }
        else
        {
            login.setVisible(true);
            logout.setVisible(false);
        }
        return true;
    }


    /*菜单按钮监听事件*/
    private Toolbar.OnMenuItemClickListener menuListener = new Toolbar.OnMenuItemClickListener()
    {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId())
            {

                case R.id.login_btn:
                    Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
                    isLogin=true;
                    supportInvalidateOptionsMenu();


                    break;
                case R.id.logout_btn:
                    Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();

                    isLogin=false;
                    supportInvalidateOptionsMenu();
                    break;
                default:break;
            }
            return true;
        }
    };
    //------------------------------------------




    //--------------navigation setting----------
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView ;
    private void setupNavigationView()
    {
        mNavigationView = (NavigationView) mDrawerLayout.findViewById(R.id.navigation);
        //


        //监听Navi事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.solar_navi_menu_item:
                        // todo
                        setNewFragment(new SolarFragment());
                        break;
                    case R.id.battery_navi_menu_item:
                        // todo
                        setNewFragment(new BatteryFragment());
                        break;
                    case R.id.converter_navi_menu_item:
                        // todo
                        setNewFragment(new ConverterListFragment());
                        break;
                    default:break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }



    //------------------------------------------





    //--------------Login setting---------------
    private boolean isLogin = false;
    //------------------------------------------






    //--------------Tab setting-----------------
    public final int  APTAB = 0;
    public final int  NETTAB = 1;
    private void setupTab()
    {

    }

    //------------------------------------------

    //--------------Fragment setting------------
    //碎片声明
    // theFragment是当前正在显示的Fragment的代号
    private Fragment theFragment=null;
    public android.support.v4.app.FragmentManager fragmentManager =getSupportFragmentManager();
    public void setNewFragment(Fragment newFragment)
    {
        if(theFragment!=null&&newFragment.getClass().equals(theFragment.getClass()))
        {
            Log.d("Test","MainActivity::setNewFragment : exist the same Fragment.");
            return;}
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_layer,newFragment);
        // 加入回退栈
        // 听说会有bug ，目前没遇到，先这么用着吧.
        transaction.addToBackStack("");
        transaction.commit();

        theFragment = newFragment;

    }

    public void setupFragment()
    {

    }

    public void PopFragment()
    {
        if(fragmentManager.getBackStackEntryCount()>0)
            fragmentManager.popBackStack();
    }
    //------------------------------------------

    //-------------EXIT setting-----------------
    /*主要实现连击返回键退出的功能*/
    private long mLastBack;
    private boolean ExitFlag=false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
        {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit(){
        long mThisBack = System.currentTimeMillis();
        if(ExitFlag == false&&mThisBack-mLastBack <1000)
        {
            ExitFlag =true;
            Toast.makeText(MainActivity.this,"按返回键退出",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(ExitFlag == true&&mThisBack-mLastBack<2000)
        {
            finish();
            System.exit(0);
        }
        PopFragment();
        ExitFlag = false;
        mLastBack=mThisBack;
    }
}
