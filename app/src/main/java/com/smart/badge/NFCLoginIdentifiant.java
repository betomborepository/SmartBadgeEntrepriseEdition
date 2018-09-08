package com.smart.badge;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.List;

import adapters.entity.Eleve;
import adapters.entity.User;
import service.NFCCore;
import service.SessionCore;
import service.WebService;
import service.handler.WebServiceHandler;


public class NFCLoginIdentifiant extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfclogin_identifiant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0,new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);



        Toast.makeText(getApplicationContext(), "Approcher voter badge pour scanner votre identifiant",
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume()
    {
        super.onResume();
        IntentFilter[] intentFiltersArray = new IntentFilter[]{};
        String[][] techListsArray = new String[][]{
                {android.nfc.tech.Ndef.class.getName()},
                {android.nfc.tech.NdefFormatable.class.getName()}};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray,
                techListsArray);
        Log.e("Entree NewIntent","mmmmmmmmm-onresume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
        Log.e("Entree NewIntent","mmmmmmmmmm-onpause");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        Log.e("Entree NewIntent","mmmmmmmmmm-onNewIntent");
        String action = intent.getAction();
        //Méthode qui va traiter le tag NFC
        Log.e("Entree NewIntent","mmmmmmmmmm-onNewIntent");
        final List<String> nfcVal = NFCCore.getNFCRecordList(intent);

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(action))
        {
            final NFCLoginIdentifiant activity = this;
            WebServiceHandler handler = new WebServiceHandler() {
                @Override
                public void onDataLoade(InputStreamReader stream) {
                    List<User> listEmp = new Gson().fromJson(stream, new TypeToken<List<User>>(){}.getType());
                    for (User user: listEmp) {
                        if(user.id.equals(nfcVal)){
                            activity.Connecter(user);
                        }
                    }
                    activity.error();
                }

                @Override
                public String GetUrl (){
                    return  activity.getResources().getString(R.string.web_service_all_user_url);
                }

                @Override
                public String getRequestMethod() {
                    return  "GET";
                }
            };




            //tester dans la base
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            mDatabase = db.getReference("users");
            ValueEventListener valueEventListener = mDatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        User user = postSnapshot.getValue(User.class);

                        if(nfcVal.contains(user.id))
                        {
                            Toast.makeText(getApplicationContext(), "Votre badge n'a été identifié. Bienvenue " + user.login,
                                    Toast.LENGTH_SHORT).show();
                            Connecter(user);
                            return;
                        }
                    }
                    error();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.wtf("une erreur dans firebase a été signalé", "une erreu dans firebase a été signalé");
                }
            });
        }
    }

    public void Connecter(User user)
    {
        SessionCore.initialiseSession(this, user);

        startActivity(new Intent(NFCLoginIdentifiant.this, MainActivity.class));
    }


    public void error()
    {
        Toast.makeText(getApplicationContext(), "Votre badge n'a été identifié",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
         startActivity(new Intent(NFCLoginIdentifiant.this, LoginActivity.class));
    }
}
