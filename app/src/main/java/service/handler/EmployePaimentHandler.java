package service.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.badge.NFCPaiement;

import java.io.InputStreamReader;

import adapters.Eleve_VAdapter;
import adapters.entity.Employe;

/**
 * Created by hp on 11/09/2018.
 */

public class EmployePaimentHandler extends WebServiceHandler {
    NFCPaiement nfcPaiement;

    public EmployePaimentHandler(String url, NFCPaiement nfcPaiement){
        this.nfcPaiement = nfcPaiement;
        this.url = url;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {
        if(stream  == null){

            this.nfcPaiement.errorPaiment();
            return;
        }


        Employe emp =  new Gson().fromJson(stream, new TypeToken<Employe>(){}.getType());

        if(emp == null)
            this.nfcPaiement.errorPaiment();
        else
            this.nfcPaiement.GotoMainPage();
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
