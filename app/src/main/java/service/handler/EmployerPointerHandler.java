package service.handler;

import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.badge.MainActivity;
import com.smart.badge.NFCPointer;
import com.smart.badge.R;

import java.io.InputStreamReader;
import java.util.List;

import adapters.entity.Eleve;
import service.DataCore;
import service.WebService;

/**
 * Created by hp on 08/09/2018.
 */

public class EmployerPointerHandler  extends  WebServiceHandler{
    NFCPointer nfcPointer;
    List<String> listID;
    String  lieu;

    public EmployerPointerHandler(NFCPointer nfcPointer, List<String> listID ){
        this.nfcPointer = nfcPointer;
        this.listID = listID;
    }

@Override
public void onDataLoade(InputStreamReader stream) {
        if (stream == null)
        return;
    List<Eleve> listEleve = new Gson().fromJson(stream, new TypeToken<List<Eleve>>(){}.getType());
    for (Eleve emp: listEleve) {
        if(listID.contains( emp.matricul)){

            //update
           Toast.makeText(this.nfcPointer.getApplicationContext(), "l'élève a été pointer. Verifier son heure dans la secion pointage",
                    Toast.LENGTH_SHORT).show();

            String updateUrl = this.nfcPointer.getResources().getString(R.string.web_service_pointage_update_url) + "?employe=" + emp.id + "&lieu=" + this.lieu;
            WebService.SendRequest(new UpdateHandler(updateUrl, null, this.nfcPointer));
            Intent intent = new Intent(this.nfcPointer, MainActivity.class);
            this.nfcPointer.startActivity(intent);
            return;
        }

    }
    nfcPointer.errorPointage();

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
