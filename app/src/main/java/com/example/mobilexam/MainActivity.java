package com.example.mobilexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.mobilexam.MyRetrofit.RetroCalls;
import com.example.mobilexam.Pojos.HStartReq;
import com.example.mobilexam.Pojos.HStokListReq;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String _periodAll="all";
    final String _periodIncreasing="increasing";
    final String _periodDecreasing="decreasing";
    final String _periodVolume30="volume30";
    final String _periodVolume50="volume50";
    final String _periodVolume100="volume100";

    private Thread _myThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        StartHandShake();
        Button btn=(Button)findViewById(R.id.BtnHisse);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetStokList(_periodAll);
                _myThread= new Thread(new Task());
                _myThread.start();
            }
        });

    }

        public void StartHandShake()
        {
            HStartReq hs=new HStartReq();
            hs.setDeviceId("abcd-123-ff987654abcd-123-ff987654");
            hs.setDeviceModel("iPhone XS Max");
            hs.setManifacturer("Apple");
            hs.setPlatformName("iOS");
            hs.setSystemVersion("12.2");
            RetroCalls.PostHandShake(hs);
        }

        private void GetStokList(String period)
        {
            MyAes myAes=new MyAes();
            HStokListReq hstok=new HStokListReq();



            String encryptedPeriod=myAes.Encrypt(period,Enums.Autorizations.getAesKey(),Enums.Autorizations.getAesIV());

            hstok.setPeriod(encryptedPeriod);

            RetroCalls.PostStokList(hstok);
        }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

    switch (item.getItemId())
    {

        case R.id.hisse_ve_endeksler:
            GetStokList(_periodAll);
           _myThread= new Thread(new Task());
           _myThread.start();

            break;
        case R.id.yukselen:
            GetStokList(_periodIncreasing);
            _myThread= new Thread(new Task());
            _myThread.start();
            break;
        case R.id.dusunler:
            GetStokList(_periodDecreasing);
            _myThread= new Thread(new Task());
            _myThread.start();
            break;
        case R.id.hacim30:
            GetStokList(_periodVolume30);
            _myThread= new Thread(new Task());
            _myThread.start();
            break;
        case R.id.hacim50:
            GetStokList(_periodVolume50);
            _myThread= new Thread(new Task());
            _myThread.start();
            break;
        case R.id.hacim100:
            GetStokList(_periodVolume100);
            _myThread= new Thread(new Task());
            _myThread.start();
                break;
    }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class Task implements Runnable {
        @Override
        public void run() {
            while (true)
            {
                if (Enums.IsStoksReady==true) {
                    Intent in = new Intent(".STOKLISTACTIVITY");
                    startActivity(in);
                    break;
                }
            }
            Enums.IsStoksReady=false;
            _myThread.interrupt();
        }

    }
}
