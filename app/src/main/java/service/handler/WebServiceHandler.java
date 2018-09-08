package service.handler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.io.InputStreamReader;

/**
 * Created by hp on 28/08/2018.
 */

public abstract class WebServiceHandler {
    RecyclerView recyclerView;
    public Object objectToSend;
    String url;
    Activity activity;
    public abstract void onDataLoade(InputStreamReader stream);
    public abstract String GetUrl();


    public abstract String getRequestMethod();
}
