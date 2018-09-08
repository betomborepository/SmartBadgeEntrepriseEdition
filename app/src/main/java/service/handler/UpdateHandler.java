package service.handler;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

import adapters.entity.Eleve;

/**
 * Created by hp on 08/09/2018.
 */

public class UpdateHandler extends  WebServiceHandler {

    UpdateHandler(String url, Object obj, Activity activity){
        this.url = url;
        this.objectToSend = obj;
        this.activity = activity;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {
        if (stream == null)
            return;
        List<Eleve> listEleve = new Gson().fromJson(stream, new TypeToken<List<Eleve>>(){}.getType());

    }

    @Override
    public String GetUrl() {
        return this.url;
    }

    @Override
    public String getRequestMethod() {
        return "POST";
    }
}
