package service.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.badge.CreationActivity;

import java.io.InputStreamReader;

import adapters.entity.Employe;
import service.SessionCore;

/**
 * Created by hp on 09/09/2018.
 */

public class UpdateEmployeHandler extends  WebServiceHandler{

    CreationActivity creationActivity;
    public  UpdateEmployeHandler(String url, CreationActivity creationActivity){
        this.url = url;
        this.creationActivity = creationActivity;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {

        Employe emp = new Gson().fromJson(stream, new TypeToken<Employe>(){}.getType());

        if(SessionCore.getCurrentUser(this.creationActivity).userEmploy.id == emp.id){
            SessionCore.updateCurrentUserEmploye(this.creationActivity,emp);
        }
        creationActivity.GoDetail();
        creationActivity.currentEleve = emp;

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
