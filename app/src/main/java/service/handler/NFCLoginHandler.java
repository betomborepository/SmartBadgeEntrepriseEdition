package service.handler;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.badge.LoginActivity;
import com.smart.badge.NFCLoginIdentifiant;
import com.smart.badge.R;

import java.io.InputStreamReader;
import java.util.List;

import adapters.entity.User;


/**
 * Created by hp on 10/09/2018.
 */

public class NFCLoginHandler extends  WebServiceHandler {
    NFCLoginIdentifiant loginActivity;
    List<String> listId;

    public NFCLoginHandler(  NFCLoginIdentifiant activity, List<String> listId){
        this.loginActivity = activity;
        this.listId = listId;
    }


    @Override
    public void onDataLoade(InputStreamReader stream) {
        List<User> users = new Gson().fromJson(stream, new TypeToken<List<User>>(){}.getType());

        for (User user: users
             )
        {
            if (listId.contains(user.id)){
                Toast.makeText(this.loginActivity, "Votre badge n'a été identifié. Bienvenue " + user.login,
                        Toast.LENGTH_SHORT).show();
                this.loginActivity.Connecter(user);
            }
        }

        this.loginActivity.error();

    }

    @Override
    public String GetUrl() {
        return null;
    }

    @Override
    public String getRequestMethod() {
        return this.loginActivity.getResources().getString(R.string.web_service_all_user_url);
    }
}
