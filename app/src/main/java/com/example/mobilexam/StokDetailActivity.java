package com.example.mobilexam;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilexam.Pojos.HStokDetailResp;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class StokDetailActivity extends AppCompatActivity {

    TextView _textViewSembol;
    TextView _textViewFiyat;
    TextView _textViewFark;
    TextView _textViewHacim;
    TextView _textViewAlis;
    TextView _textViewSatis;

    TextView _textViewLow;
    TextView _textViewHigh;
    TextView _textViewCount;
    TextView _textViewMax;
    TextView _textViewMin;
    ImageView _imgViewDeg;

    LineChartView lineChartView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok_detail);
        InitViews();
        InitData();
    }

    private void InitViews()
    {
        try {
            _textViewSembol=(TextView)findViewById(R.id.textSembol);
            _textViewFiyat=(TextView)findViewById(R.id.textFiyat);
            _textViewFark=(TextView)findViewById(R.id.textFark);
            _textViewAlis=(TextView)findViewById(R.id.textAlis);
            _textViewHacim=(TextView)findViewById(R.id.textHacim);
            _textViewSatis=(TextView)findViewById(R.id.textSatis);

            _textViewMax=(TextView)findViewById(R.id.textViewMax);
            _textViewMin=(TextView)findViewById(R.id.textViewMin);
            _textViewCount=(TextView)findViewById(R.id.textViewAdet);
            _textViewLow=(TextView)findViewById(R.id.textViewLow);
            _textViewHigh=(TextView)findViewById(R.id.textViewHigh);
            _imgViewDeg=(ImageView)findViewById(R.id.ImgDeg);
        }catch (Exception ex)
        {
            Log.e("InitViews",ex.getMessage());
        }

    }

    private void DrawLineChart()
    {
        lineChartView = findViewById(R.id.chart);

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));
/*
        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }
*/
        HStokDetailResp.GraphicData[] gd=Enums.StokDetail.getGraphicData();
        for (int i = 0; i < gd.length; i++) {
            yAxisValues.add(new PointValue(i, gd[i].getValue().floatValue()));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();

        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
       // viewport.top = 100;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
    }

    private void InitData()
    {
        try {
            HStokDetailResp det=Enums.StokDetail;
            _textViewSembol.setText("sembol");
            _textViewFiyat.setText(det.getPrice().toString());
            _textViewFark.setText(det.getDifference().toString());
            _textViewHacim.setText(det.getVolume().toString());
            _textViewAlis.setText(det.getBid().toString());
            _textViewSatis.setText(det.getOffer().toString());

            _textViewMin.setText(det.getLowest().toString());
            _textViewMax.setText(det.getHighest().toString());
            _textViewCount.setText(det.getCount().toString());
            _textViewLow.setText(det.getLowest().toString());
            _textViewHigh.setText(det.getHighest().toString());

            if (det.getIsDown()==true)
                _imgViewDeg.setImageResource(R.mipmap.downnn);
            else if(det.getIsUp()==true)
                _imgViewDeg.setImageResource(R.mipmap.uppppp);
            else
                _imgViewDeg.setImageResource(R.mipmap.constant);

            DrawLineChart();
        }catch (Exception ex)
        {
            Log.e("Init Data",ex.getMessage());
        }


    }
}
