package com.smart.badge;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import adapters.entity.Employe;
import service.NFCCore;

public class NFCConfiguration extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private DatabaseReference mDatabase;
    Employe currentEmploye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_onfiguration);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0,new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        this.currentEmploye = (Employe) this.getIntent().getSerializableExtra("employe");
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
            assignerTag(intent);
        }
    }




    public void assignerTag(Intent intent)
    {
        NdefRecord[] records= { new NFCCore().createRecord(currentEmploye.matricul)};
        if( new NFCCore().saveDataToTag(records,intent))
        {
            Toast.makeText(getApplicationContext(), "Ce tag a été assigné avec succés à " + currentEmploye.prenom + " avec comme immatriucle " + currentEmploye.matricul,
                    Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(NFCConfiguration.this, MainActivity.class);
            intent.putExtra("eleve",currentEmploye);
            startActivity(intent2);
        }else
        {
            error();
        }
    }

    public void error()
    {
        Toast.makeText(getApplicationContext(), "Le tag n'est pas assez proche!",
                Toast.LENGTH_SHORT).show();
    }


}
