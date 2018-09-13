package com.smart.badge;

import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

import adapters.entity.Employe;
import service.handler.WebServiceHandler;

/**
 * Created by hp on 08/09/2018.
 */

class IdentificationHandler extends WebServiceHandler {
    NFCIdentification nfcIdentification;
    List<String> listId;
    public IdentificationHandler(NFCIdentification nfcIdentification, List<String> listId) {
        super();
        this.nfcIdentification = nfcIdentification;
        this.listId = listId;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {
        List<Employe> listDateDat = new Gson().fromJson(stream, new TypeToken<List<Employe>>(){}.getType());

        for (Employe emp: listDateDat) {
            if(listId.contains(emp.matricul)){
                Toast.makeText(this.nfcIdentification.getApplicationContext(), "l'élève a été identifier",
                        Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this.nfcIdentification, DetailActivity.class);
                intent.putExtra("eleve", emp);


                nfcIdentification.startActivity(intent);
                break;
            }
            nfcIdentification.errorPointage();
        }
    }

    @Override
    public String GetUrl() {
        return this.nfcIdentification.getResources().getString(R.string.web_service_all_employe_url);
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }
}
