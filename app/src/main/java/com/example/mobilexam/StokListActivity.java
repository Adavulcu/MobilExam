package com.example.mobilexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobilexam.MyRetrofit.RetroCalls;
import com.example.mobilexam.Pojos.HStokDetailReq;
import com.example.mobilexam.Pojos.HStokListResp;

public class StokListActivity extends AppCompatActivity {

    Thread _myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok_list);

        ListView listView=findViewById(R.id.HisseListView);
        StokListAdapter stokListAdapter=new StokListAdapter(this,Enums.Stocks);
        listView.setAdapter(stokListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout ly=(LinearLayout) view;
                TextView text=(TextView)ly.findViewById(R.id.textViewFiyat);
                Log.i("Tıklanan Fiyat = ",text.getText().toString()+"\n ID : "+text.getTag().toString());
                GetStokDetail(text.getTag().toString());
            }
        });

    }

    private void GetStokDetail(String id)
    {
        MyAes aes=new MyAes();
        HStokDetailReq hsd=new HStokDetailReq();

        String encyrptedId=aes.Encrypt(id,Enums.Autorizations.getAesKey(),Enums.Autorizations.getAesIV());
        hsd.setId(encyrptedId);

        RetroCalls.PostStokDetail(hsd);
        _myThread=new Thread(new Task());
        _myThread.start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            while (true)
            {
                if (Enums.IsStokDetailReady==true) {
                    Intent in = new Intent(".STOKDETAILACTIVITY");
                    startActivity(in);
                    break;
                }
            }
            Enums.IsStokDetailReady=false;
            _myThread.interrupt();
        }

    }

}
