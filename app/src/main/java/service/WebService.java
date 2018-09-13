package service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

import service.handler.WebServiceHandler;

/**
 * Created by hp on 26/08/2018.
 */

public  class WebService {



    public static void SendRequest(final WebServiceHandler handler){

       AsyncTask <String, Void, InputStreamReader> task = new AsyncTask<String, Void, InputStreamReader>() {
            @Override
            protected InputStreamReader doInBackground(String... strings) {
                try {
                    String URL = handler.GetUrl();
                    // Envoi de la requête
                    System.out.println(URL);

                    if(handler.objectToSend != null){
                        String objeParam = ObjectToParam(handler.objectToSend);
                        if(URL.contains("?")){
                            URL = URL + "&" + objeParam;
                        }else {
                            URL = URL + "?" + objeParam;
                        }
                    }


                    InputStream inputStream = sendRequest(new URL(URL), handler.getRequestMethod());

                    // Vérification de l'inputStream
                    if(inputStream != null) {
                        // Lecture de l'inputStream dans un reader
                        InputStreamReader reader = new InputStreamReader(inputStream);

                        // Retourne la liste désérialisée par le moteur GSON
                        return reader;
                    }

                } catch (Exception e) {
                    Log.e("WebService", "Impossible de rapatrier les données :(");
                }
                return null;
            }

            @Override
            protected void onPostExecute(InputStreamReader eleves) {
                super.onPostExecute(eleves);
                handler.onDataLoade(eleves);
            }

        };

       task.execute();
    }

    public static InputStream sendRequest(URL url, String methode) throws Exception {


        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(methode);

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
        return null;
    }

    private static String ObjectToParam(Object objectToSend) {
       Field[] tabField = objectToSend.getClass().getDeclaredFields();
       String listParam = "";
       for (Field field: tabField
             )
        {
            String name = field.getName();
            try {
                if(! (field.get(objectToSend) instanceof  String)  ){
                    continue;
                }
                String val = (String) field.get(objectToSend);
                listParam +=  name + "=" + val + "&";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return  listParam;
    }

}
