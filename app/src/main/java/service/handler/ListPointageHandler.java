package service.handler;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

import adapters.Pointage_VAdapter;
import adapters.entity.Pointage;

/**
 * Created by hp on 01/09/2018.
 */

public class ListPointageHandler extends  WebServiceHandler {
    public ListPointageHandler(String url, FragmentActivity activity, RecyclerView recyclerView) {
        super();
        this.url = url;
        this.recyclerView = recyclerView;
        this.activity = activity;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {
        if (stream == null)
            return;
        List<Pointage> listEleve = new Gson().fromJson(stream, new TypeToken<List<Pointage>>(){}.getType());



        Pointage_VAdapter eleve_vAdapter = new Pointage_VAdapter(listEleve, this.activity);
        //attaching data from the adapter to the real recyclerview
        recyclerView.setAdapter(eleve_vAdapter);
    }

    @Override
    public String GetUrl() {
        return this.url;
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }
}
