package com.smart.badge;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class PagePaiement extends AppCompatActivity {


    AppCompatButton login_btn;
    AppCompatEditText uname, passwd;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_paiement);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Smart Badge");
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));


    }




    public void  ValidateTransaction(View view)
    {
        EditText montant =  this.findViewById(R.id.montant);
        Intent intent = new Intent(PagePaiement.this, NFCPaiement.class);
        String m = montant.getText().toString();
        intent.putExtra("montant", m);
        startActivity(intent);
    }

    public void  AnnulerTransaction(View view)
    {
        this.finish();
    }


    private void Error()
    {
        Toast.makeText(getApplicationContext(), "Mot de passe ou compte erroné!",
                Toast.LENGTH_SHORT).show();
    }



}
