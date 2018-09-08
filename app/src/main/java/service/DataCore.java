package service;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import adapters.EleveConfiguration_VAdapter;
import adapters.Eleve_VAdapter;
import adapters.PointageEmploye_VAdapter;
import adapters.Pointage_VAdapter;
import adapters.entity.Transaction;
import adapters.Transaction_VAdapter;
import adapters.entity.Eleve;
import adapters.entity.Pointage;

/**
 * Created by hp on 22/05/2018.
 */

public  class DataCore
{
    private DatabaseReference mDatabase;
// ...

    public  List<Eleve> GetListEleve(final Activity a,final RecyclerView recyclerView)
    {
         final List<Eleve>  listEleve = new ArrayList<Eleve>() ;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("eleves");
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 Log.d("RealTimeDatabase", "Value is 1: ");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Eleve e = postSnapshot.getValue(Eleve.class);

                    listEleve.add(e);
                    Log.d("eleve",e.nom);

                }
                Eleve_VAdapter eleve_vAdapter = new Eleve_VAdapter(listEleve, a);

                //attaching data from the adapter to the real recyclerview
                recyclerView.setAdapter(eleve_vAdapter);

                //  ProductAdapter productAdapter = new ProductAdapter(activity, productList);
                //recyclerView.setAdapter(productAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RealTimeDatabase", "Failed to read value.", databaseError.toException());
            }
        });
        return  listEleve;
    }


    public  List<Eleve> GetListEleveConfirutation(final Activity a,final RecyclerView recyclerView)
    {
        final List<Eleve>  listEleve = new ArrayList<Eleve>() ;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("eleves");
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("RealTimeDatabase", "Value is 1: ");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Eleve e = postSnapshot.getValue(Eleve.class);

                    listEleve.add(e);
                    Log.d("eleve",e.nom);

                }
                EleveConfiguration_VAdapter eleve_vAdapter = new EleveConfiguration_VAdapter(listEleve, a);

                //attaching data from the adapter to the real recyclerview
                recyclerView.setAdapter(eleve_vAdapter);

                //  ProductAdapter productAdapter = new ProductAdapter(activity, productList);
                //recyclerView.setAdapter(productAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RealTimeDatabase", "Failed to read value.", databaseError.toException());
            }
        });
        return  listEleve;
    }




    public  List<Pointage> GetListPointage(final Activity a,final RecyclerView recyclerView)
    {
        final List<Pointage>  listPointages = new ArrayList<Pointage>() ;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("pointages");
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Pointage e = postSnapshot.getValue(Pointage.class);
                    listPointages.add(e);
                }
                Pointage_VAdapter pointage_vAdapter = new Pointage_VAdapter(listPointages, a);
                
                //attaching data from the adapter to the real recyclerview
                recyclerView.setAdapter(pointage_vAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RealTimeDatabase", "Failed to read value.", databaseError.toException());
            }
        });
        return  listPointages;
    }


    public  List<Pointage> GetListPointageByEmploye(final Activity a,final RecyclerView recyclerView)
    {
        final List<Pointage>  listPointages = new ArrayList<Pointage>() ;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("pointages");
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Pointage e = postSnapshot.getValue(Pointage.class);
                    listPointages.add(e);
                }
                //todo filter by userEmploy
                PointageEmploye_VAdapter pointage_vAdapter = new PointageEmploye_VAdapter(listPointages, a);

                //attaching data from the adapter to the real recyclerview
                recyclerView.setAdapter(pointage_vAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RealTimeDatabase", "Failed to read value.", databaseError.toException());
            }
        });
        return  listPointages;
    }
   /* public static List<Pointage> GetListPointage()
    {
        List<Pointage>  listPointPage = new ArrayList<Pointage>() ;
        Pointage p = new Pointage("6532", "porte4", "BETOMBO", "ID007");
        Pointage p1 = new Pointage("6532", "porte5", "Matiasy", "00715");

        listPointPage.add(p);
        listPointPage.add(p1);
        return  listPointPage;
    }*/


   
    void updateEmploye(Eleve el, Pointage p)
    {
            // chercher ajouter le pontage dans l'élève
        // ajouter un pointage dans le pointge genenal
    }


    public static boolean CanLog(String username, String password) {
        return  true;
    }


    public Eleve GetEleveByImmatricule(final String id)
    {
        final List<Eleve>  listEleve = new ArrayList<Eleve>() ;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("eleves");
        Eleve r = new Eleve() ;
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("RealTimeDatabase", "Value is 1: ");
                Eleve retour =new Eleve();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Eleve e = postSnapshot.getValue(Eleve.class);

                    listEleve.add(e);
                    Log.d("eleve",e.nom);
                    if(e.matricul.equals(id)){
                        retour = e;
                    }
                }
                Log.d("detected nom",retour.nom);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RealTimeDatabase", "Failed to read value.", databaseError.toException());
            }
        });

        return r;
    }

    public   void updateEmploye(Eleve el)
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("pointages");
        String key = mDatabase.child("child").push().getKey();
        Pointage p =    new Pointage("testid","poste 777", el.shortDescription(), el.matricul);
        mDatabase.child(key).setValue(p);
       // mDatabase.child("house").child(key).setValue(p);
    }


    public void refreshListTransaction(FragmentActivity activity, RecyclerView recyclerView)
    {
        // get list from database
        List<Transaction> listTransaction = new ArrayList<Transaction>(){};

        Transaction t = new Transaction();
        t.nomEleve = "Eleve";
        t.IDEleve = "EL54";
        t.date = new Date().toString();
        t.montant = 400;

        listTransaction.add(t);
        Transaction_VAdapter transaction_vAdapter = new Transaction_VAdapter(listTransaction, activity);

        //attaching data from the adapter to the real recyclerview
        recyclerView.setAdapter(transaction_vAdapter);
    }


    public  void createTransaction (Transaction t){
        // todo implement update of transaction
    }
}
