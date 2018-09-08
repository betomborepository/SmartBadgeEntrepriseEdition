package service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import adapters.entity.Eleve;
import adapters.entity.User;

/**
 * Created by hp on 19/08/2018.
 */

public class SessionCore {
    private static String PREFERENCE_KEY = "smart_badge_session";

    public static void logout(Activity context){
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void initialiseSession(Activity context, User user){

        SharedPreferences.Editor editor = getSharedPreference(context).edit();

        editor.putString("login", user.login);
        editor.putString("type", user.type);
        editor.putString("user_immatricule", user.id);
        editor.putString("password", user.password);


        //todo get userEmploy from user if cantine or userEmploy
        editor.putString("nom", user.userEmploy.nom);
        editor.putString("prenom", user.userEmploy.prenom);
        editor.putString("matricule", user.userEmploy.matricul);



        editor.commit();
    }

    public  static  SharedPreferences getSharedPreference(Activity context){
         return context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);

    }

    public static User getCurrentUser(Activity context){
        SharedPreferences pref = getSharedPreference(context);
        String nom = pref.getString("nom", "");
        String prenom = pref.getString("prenom", "");
        String matricule = pref.getString("matricule", "");


        String login = pref.getString("login", "");
        String type = pref.getString("type", "");
        String password = pref.getString("password", "");
        String usermatricule = pref.getString("user_immatricule", "");
        User user =new User();
        if(usermatricule.isEmpty())
            return  null;

        user.login = login;
        user.type = type;
        user.password = password;
        user.id = usermatricule;

        user.userEmploy = new Eleve();
        user.userEmploy.matricul = matricule;
        user.userEmploy.prenom = prenom;
        user.userEmploy.nom = nom;


        return  user;
    }



}
