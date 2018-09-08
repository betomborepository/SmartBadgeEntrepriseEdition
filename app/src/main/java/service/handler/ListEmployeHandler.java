package service.handler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

import adapters.Eleve_VAdapter;
import adapters.entity.Eleve;

/**
 * Created by hp on 28/08/2018.
 */

public class ListEmployeHandler extends WebServiceHandler{


    public ListEmployeHandler(String url, Activity activity, RecyclerView v ){
        this.url = url;
        recyclerView = v;
        this.activity = activity;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {
        if (stream == null)
            return;
        List<Eleve> listEleve = new Gson().fromJson(stream, new TypeToken<List<Eleve>>(){}.getType());
        Eleve_VAdapter eleve_vAdapter = new Eleve_VAdapter(listEleve, this.activity);
        //attaching data from the adapter to the real recyclerview
        recyclerView.setAdapter(eleve_vAdapter);

    }

    @Override
    public String GetUrl() {
        return url;
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }
}
