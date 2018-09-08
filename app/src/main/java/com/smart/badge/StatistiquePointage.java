package com.smart.badge;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;
import service.WebService;
import service.handler.EmployeStatistiqueHandler;
import service.handler.PointageStatistiqueHandler;

public class StatistiquePointage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satistique_pointage);

        ColumnChartView col = findViewById(R.id.chart);

        // TODO: 18/08/2018 add check null  and implement real data

        ColumnChartData data = generatePieChartData();
        col.setColumnChartData(data);
        WebService.SendRequest(new PointageStatistiqueHandler(col, this));

    }



    private  ColumnChartData generatePieChartData(){

        List<Pair<String, Integer>> pointageData = generatePointageData();

        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < pointageData.size(); ++i) {

            values = new ArrayList<SubcolumnValue>();


            //todo create or find a custom algo to remove redoundance on column color
            SubcolumnValue subColVal = new SubcolumnValue( pointageData.get(i).second, ChartUtils.pickColor());
            values.add(subColVal);


            Column column = new Column(values);

            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }

        ColumnChartData data = new ColumnChartData(columns);

        Axis axisX = new Axis();
        axisX.setValues(generateAxisXValue(pointageData));
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Jour de la semaine");
        axisY.setName("Employé");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);


        return  data;
    }

    private List<Pair<String, Integer>> generatePointageData() {
        List<Pair<String, Integer>> data = new ArrayList<>();
        data.add(new Pair<String, Integer>("lundi", new Integer(12)));
        data.add(new Pair<String, Integer>("mardi", new Integer(24)));
        data.add(new Pair<String, Integer>("mercredi", new Integer(12)));
        data.add(new Pair<String, Integer>("jeudi", new Integer(24)));
        data.add(new Pair<String, Integer>("vendredi", new Integer(12)));
        data.add(new Pair<String, Integer>("samedi", new Integer(24)));

        return data;
    }


    List<AxisValue> generateAxisXValue(List<Pair<String, Integer>> tabSemaine){
        List<AxisValue> axisXValues= new ArrayList<>();
        for(int i = 0; i< tabSemaine.size(); i++){
            AxisValue axisVal = new AxisValue(i);
            axisVal.setLabel(tabSemaine.get(i).first);
            axisXValues.add(axisVal);
        }
        return  axisXValues;
    }
}
