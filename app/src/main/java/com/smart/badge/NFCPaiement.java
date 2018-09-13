package com.smart.badge;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import adapters.entity.Employe;
import service.DataCore;
import service.NFCCore;
import service.WebService;
import service.handler.EmployePaimentHandler;

public class NFCPaiement extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private  String montantDepense = "";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_paiement);

        this.montantDepense = this.getIntent().getStringExtra("montant");

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0,new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
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
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(action))
        {
            //Méthode qui va traiter le tag NFC
            Log.e("Entree NewIntent","mmmmmmmmmm-onNewIntent");
            List<String> nfcVal = NFCCore.getNFCRecordList(intent);

            DepenserEmployeArgent(nfcVal);
        }
    }

    public void DepenserEmployeArgent(final List<String> listId)
    {
        String parsedListID = parsedId(listId);
        String url = this.getResources().getString(R.string.web_service_employe_update_paiement_url) + "?id=" + parsedListID + "&argent=" + this.montantDepense;
        WebService.SendRequest(new EmployePaimentHandler(url, this));
    }

    public String  parsedId(List<String> listId){
        String delemitier = "";
        String parsed = "";
        for (String id: listId
             ) {

            parsed += id + delemitier;

            delemitier ="|";
        }
        return  parsed;
    }


    public void errorPaiment()
    {
        Toast.makeText(getApplicationContext(), "Montant insuffisant",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NFCPaiement.this, PagePaiement.class);
        startActivity(intent)  ;
    }

    public  void GotoMainPage(){

        Toast.makeText(getApplicationContext(), "Le paiement a été fait avec succés",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NFCPaiement.this, MainActivity.class);
        startActivity(intent);
    }




}
