package service.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.badge.LoginActivity;
import com.smart.badge.R;

import java.io.InputStreamReader;

import adapters.entity.User;
import service.SessionCore;

/**
 * Created by hp on 01/09/2018.
 */

public class LoginHandler extends  WebServiceHandler {
    private  String login;
    private  String password;
    private  LoginActivity loginActivity;

    public LoginHandler(LoginActivity a, String login, String password){
        this.loginActivity = a;
        this.login = login;
        this. password = password;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {
        User user = new Gson().fromJson(stream, new TypeToken<User>(){}.getType());
        if(user.id != null){
            this.loginActivity.GoTomainPage();
            SessionCore.initialiseSession(this.loginActivity, user);
        }else{
            this.loginActivity.Error();
        }
    }

    @Override
    public String GetUrl() {
        String url = loginActivity.getResources().getString(R.string.web_service_login_url);
        String param = "?login=" + this.login + "&password=" + this.password;
        return url + param;
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }
}
