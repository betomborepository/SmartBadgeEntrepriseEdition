package service.handler;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.badge.R;

import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adapters.entity.Eleve;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;
import service.WebService;

/**
 * Created by hp on 03/09/2018.
 */

public class EmployeStatistiqueHandler extends WebServiceHandler
{

    PieChartView picharview ;
    public EmployeStatistiqueHandler(PieChartView pie, Activity a){
        this.activity = a;
        picharview = pie;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {
        if(stream == null)
            return;
        List<Eleve> eleves = new Gson().fromJson(stream, new TypeToken<List<Eleve>>(){}.getType());

        List<SliceValue> values = new ArrayList<SliceValue>();
        // TODO: 18/08/2018 populate with the right value
        HashMap map = new HashMap<>();


        for (int i = 0; i < eleves.size(); ++i) {
            String tmp = eleves.get(i).employeFonction.departement;

            if(tmp.isEmpty())
                continue;

            Integer count = (Integer) map.get(tmp);
            if(map.get(tmp) != null){
               count++;
               map.put(tmp, count);
            }else{
               map.put(tmp, new Integer(1));
            }
        }

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            SliceValue sliceValue = new SliceValue((Integer) pair.getValue(), ChartUtils.pickColor());
            sliceValue.setLabel(pair.getKey().toString());
            values.add(sliceValue);
        }




        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasCenterCircle(true);


        this.picharview.setPieChartData(data);
    }

    @Override
    public String GetUrl() {
        String url = this.activity.getResources().getString(R.string.web_service_employe_stastique_url);
        return url;
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }
}
