package com.example.mobilexam;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mobilexam.Pojos.HStokListReq;
import com.example.mobilexam.Pojos.HStokListResp;
import com.example.mobilexam.Pojos.HStokListResp;

import java.util.ArrayList;

public class StokListAdapter extends BaseAdapter {

    ArrayList<HStokListResp.Stocks> _stokList;
    Context _context;
    LayoutInflater _inflater;
    MyAes _myAes;
    private ViewHolder _holder;
    public StokListAdapter(Context contex, ArrayList<HStokListResp.Stocks> list)
    {
        _context=contex;
        _stokList=list;
        _myAes=new MyAes();
    }

    @Override
    public int getCount() {
        return _stokList.size();
    }

    @Override
    public Object getItem(int i) {
        return _stokList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HStokListResp.Stocks st=(HStokListResp.Stocks)getItem(i);
        if(view==null)
        {
            _inflater =(LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=_inflater.inflate(R.layout.stok_list,null);

            _holder=new ViewHolder();

            _holder._textViewSembol=(TextView)view.findViewById(R.id.textViewSembol);
            _holder._textViewFiyat=(TextView)view.findViewById(R.id.textViewFiyat);
            _holder._textViewFark=(TextView)view.findViewById(R.id.textViewFark);
            _holder._textViewHacim=(TextView)view.findViewById(R.id.textViewHacim);
           _holder._textViewAlis=(TextView)view.findViewById(R.id.textViewAlis);
            _holder._textViewSatis=(TextView)view.findViewById(R.id.textViewSatis);
            _holder._imageViewDegisim=(ImageView)view.findViewById(R.id.ImgDegisim);

            view.setTag(_holder);
        }
        else
            _holder=(ViewHolder)view.getTag();

        _holder._textViewSatis.setText(st.getBid().toString());
        _holder._textViewAlis.setText(st.getOffer().toString());
        long volume=Math.round(st.getVolume());
        _holder._textViewHacim.setText(st.getVolume().toString());
        _holder._textViewFark.setText(st.getDifference().toString());
        _holder._textViewFiyat.setText(st.getPrice().toString());
        _holder._textViewFiyat.setTag(st.getId());

        if (st.getIsUp()==true)
            _holder._imageViewDegisim.setImageResource(R.mipmap.uppppp);
        else if(st.getIsDown()==true)
            _holder._imageViewDegisim.setImageResource(R.mipmap.downnn);
        else
            _holder._imageViewDegisim.setImageResource(R.mipmap.constant);


       android.support.v7.widget.GridLayout gl=(android.support.v7.widget.GridLayout)view.findViewById(R.id.GLStokItem);

    if (i%2==0)
        gl.setBackgroundColor(100);
    else
        gl.setBackgroundColor(200);



      //  text1.setText(st.getSymbol());





        return view;
    }

    class ViewHolder
    {
        TextView _textViewSembol;
        TextView _textViewFiyat;
        TextView _textViewFark;
        TextView _textViewHacim;
        TextView _textViewAlis;
        TextView _textViewSatis;
        ImageView _imageViewDegisim;
    }
}
