package service.handler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

import adapters.Eleve_VAdapter;
import adapters.Transaction_VAdapter;
import adapters.entity.Employe;
import adapters.entity.Transaction;

/**
 * Created by hp on 12/09/2018.
 */

public class TransactionHandler extends  WebServiceHandler {

    public TransactionHandler(String url, Activity activity ,RecyclerView recyclerView){
        this.url = url;
        this.recyclerView = recyclerView;
        this.activity = activity;
    }

    @Override
    public void onDataLoade(InputStreamReader stream) {

        if(stream == null)
            return;
        List<Transaction> listTransaction = new Gson().fromJson(stream, new TypeToken<List<Transaction>>(){}.getType());
        Transaction_VAdapter transaction_vAdapter = new Transaction_VAdapter(listTransaction, activity);

        System.out.println("size :" + listTransaction.size());
        //attaching data from the adapter to the real recyclerview
        recyclerView.setAdapter(transaction_vAdapter);


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
