package com.example.mobilexam;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilexam.Pojos.HStokListResp;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {

    private ArrayList<HStokListResp.Stocks> _stocks;
    public Context _contex;
    LayoutInflater inflater;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewSembol;
        public TextView textViewFiyat;
        public TextView textViewFark;
        public TextView textViewHacim;
        public TextView textViewAlis;
        public TextView textViewSatis;
        public ImageView imageViewDegisim;

        public MyViewHolder(View view) {
            super(view);
              textViewSembol=(TextView)view.findViewById(R.id.textViewSembol);
              textViewFiyat=(TextView)view.findViewById(R.id.textViewFiyat);
              textViewFark=(TextView)view.findViewById(R.id.textViewFark);
              textViewHacim=(TextView)view.findViewById(R.id.textViewHacim);
              textViewAlis=(TextView)view.findViewById(R.id.textViewAlis);
              textViewSatis=(TextView)view.findViewById(R.id.textViewSatis);
              imageViewDegisim=(ImageView)view.findViewById(R.id.ImgDegisim);

              view.setOnClickListener((View.OnClickListener) this);
        }

        public void setData(HStokListResp.Stocks st, int position) {

            this.textViewSembol.setText(st.getSymbol());
            this.textViewFiyat.setText(st.getPrice().toString());
            this.textViewFark.setText(st.getDifference().toString());
            this.textViewHacim.setText(st.getVolume().toString());
            this.textViewAlis.setText(st.getBid().toString());
            this.textViewSatis.setText(st.getOffer().toString());


        }
    }

    public RecylerViewAdapter (ArrayList<HStokListResp.Stocks> stocks, Context context ){
        _stocks=stocks;
        _contex=context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecylerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.stok_list, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        HStokListResp.Stocks st=_stocks.get(i);
        myViewHolder.setData(st,i);
    }


    @Override
    public int getItemCount() {
        return _stocks.size();
    }
}
