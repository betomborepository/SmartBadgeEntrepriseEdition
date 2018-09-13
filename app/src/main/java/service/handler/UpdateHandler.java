package service.handler;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

import adapters.entity.Employe;

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
//        List<Employe> listEleve = new Gson().fromJson(stream, new TypeToken<List<Employe>>(){}.getType());

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
