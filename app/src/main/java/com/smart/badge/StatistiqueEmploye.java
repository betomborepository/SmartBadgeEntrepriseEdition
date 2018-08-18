package com.smart.badge;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapters.entity.User;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class StatistiqueEmploye extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satistique_employe);

        PieChartView pie = findViewById(R.id.chart);

        // TODO: 18/08/2018 add check null  and implement real data

        PieChartData data = generatePieChartData();
        pie.setPieChartData(data);

    }



    private  PieChartData generatePieChartData(){
        List<SliceValue> values = new ArrayList<SliceValue>();
        // TODO: 18/08/2018 populate with the right value 
        for (int i = 0; i < 7; ++i) {

            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            sliceValue.setLabel("label " + i);
            values.add(sliceValue);
        }

        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasCenterCircle(true);

        return  data;
    }
}
