package service.handler;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.badge.R;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import adapters.entity.PointageSatistique;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by hp on 06/09/2018.
 */

public class PointageStatistiqueHandler extends  WebServiceHandler {
   private  ColumnChartView columnChartView;

    public PointageStatistiqueHandler(ColumnChartView col, Activity activity){
        this.columnChartView   = col;
        this.activity = activity;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDataLoade(InputStreamReader stream) {
        List<PointageSatistique> listDateDat = new Gson().fromJson(stream, new TypeToken<List<PointageSatistique>>(){}.getType());


        //current day
        Calendar c = Calendar.getInstance();
        Date currentDate = new Date();
        c.setTime(currentDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, -dayOfWeek);

        int [] countEmpDay = new int[5];
        for(int i =0; i < 5; i++){
            c.add(Calendar.DATE, 1);
            Date tmp = c.getTime();
            countEmpDay[i] = 0;
            for(int j =0; j< listDateDat.size(); j++){

                String defaultTimezone = TimeZone.getDefault().getID();
                Date date = null;
                try {
                    date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(listDateDat.get( j).date.replaceAll("Z$", "+0000"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date);

                boolean sameDay = cal1.get(Calendar.YEAR) == c.get(Calendar.YEAR) &&
                        cal1.get(Calendar.DAY_OF_YEAR) == c.get(Calendar.DAY_OF_YEAR);
                if(sameDay) {
                    countEmpDay[i] = listDateDat.get(j).count;
                    break;
                }

            }
        }


        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < 5; ++i) {

            values = new ArrayList<SubcolumnValue>();


            //todo create or find a custom algo to remove redoundance on column color
            SubcolumnValue subColVal = new SubcolumnValue( countEmpDay[i], ChartUtils.pickColor());
            values.add(subColVal);


            Column column = new Column(values);

            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }

        ColumnChartData data = new ColumnChartData(columns);

        String [] tabSemaine = new String[]{
                "lundi",
                "mardi",
                "mercredi",
                "jeudi",
                "vendredi",

        };
        Axis axisX = new Axis();
        axisX.setValues(generateAxisXValue(tabSemaine));
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Jour de la semaine");
        axisY.setName("Employé");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);


        this.columnChartView.setColumnChartData(data);

        //generate date for the remaining day
    }

    @Override
    public String GetUrl() {
        return this.activity.getResources().getString(R.string.web_service_pointage_stastique_url);
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }

    List<AxisValue> generateAxisXValue(String[] tabSemaine){
        List<AxisValue> axisXValues= new ArrayList<>();
        for(int i = 0; i< tabSemaine.length; i++){
            AxisValue axisVal = new AxisValue(i);
            axisVal.setLabel(tabSemaine[i]);
            axisXValues.add(axisVal);
        }
        return  axisXValues;
    }
}
