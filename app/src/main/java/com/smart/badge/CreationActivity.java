package com.smart.badge;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import adapters.Profile;
import adapters.entity.Employe;
import service.WebService;
import service.handler.UpdateEmployeHandler;

public class CreationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatTextView detail_name, detail_about, detail_active_time;

    private Profile profile;
    public Employe currentEleve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toolbar tb = findViewById(R.id.toolbar);


        tb.setTitle("Creation");

        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));
        Employe employe = (Employe) this.getIntent().getSerializableExtra("userEmploy");
        currentEleve = employe;
        Button valider = this.findViewById(R.id.boutton_valider);
        valider.setTag("creation");


        if(employe != null){
            tb.setTitle("Modification");
            valider.setText("Modifier");
            valider.setTag("modification");
            EditText nom = this.findViewById(R.id.nom);
            nom.setText(employe.nom);
            EditText prenom = this.findViewById(R.id.prenom);
            prenom.setText(employe.prenom);
            EditText age = this.findViewById(R.id.age);
            age.setText(employe.age);
            EditText adresse = this.findViewById(R.id.adresse  );
            adresse.setText(employe.adresse);

            EditText matricul = this.findViewById(R.id.matricule);
            EditText nomFonction = this.findViewById(R.id.nom_fonction);

        }

        Spinner spinner = (Spinner) findViewById(R.id.departement);

        List<CharSequence> list = new ArrayList<>();
        list.add("Informatique");
        list.add("Communication");
        list.add("RH");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
    }


    private void initialize(Employe el) {

        toolbar.setTitle(el.shortDescription());
        AppCompatTextView heading1 = findViewById(R.id.detail_heading_1);
        AppCompatTextView heading2 = findViewById(R.id.detail_heading_2);

        heading1.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        heading2.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


        AppCompatTextView detail_immatricul = findViewById(R.id.detail_matricul_value);
        AppCompatTextView detail_surName = findViewById(R.id.detail_surname_value);
         detail_name = findViewById(R.id.detail_name_value);

        //AppCompatTextView detail_description = findViewById(R.id.det);

        detail_immatricul.setText(el.matricul);
        detail_name.setText(el.nom);
        detail_surName.setText(el.prenom);
    }

    public void goToAssignEleveToTag(View v)
    {
        Intent intent = new Intent(CreationActivity.this, NFCApplyTagEleve.class);
        intent.putExtra("eleve",currentEleve);
        startActivity(intent);

    }

    public void onClickBouttonVallider(View v){
        EditText nom = this.findViewById(R.id.nom);
        EditText prenom = this.findViewById(R.id.prenom);
        EditText age = this.findViewById(R.id.age);
        EditText adresse = this.findViewById(R.id.adresse  );

        EditText matricul = this.findViewById(R.id.matricule);
        EditText nomFonction = this.findViewById(R.id.nom_fonction);
        Spinner spinner = this.findViewById(R.id.departement);
        String departement = spinner.getSelectedItem().toString();
        detail_name = findViewById(R.id.detail_name_value);


        String param = "?id=" + currentEleve.id +"&nom=" + nom.getText() + "&prenom=" + prenom.getText() + "&age=" + age.getText()
                + "&adresse=" + adresse.getText() + "&matricul=" + matricul.getText() + "&nomFonction=" + nomFonction.getText()
                +  "&departementFonction=" + departement;
        String url = this.getResources().getString(R.string.web_service_update_employe_url) + param;

        WebService.SendRequest(new UpdateEmployeHandler(url, this));
    }

    public  void onClickBouttonAnnuler(View view){
        this.finish();
    }
    public  void GoDetail(){
        Intent intent = new Intent(CreationActivity.this, DetailActivity.class);
        intent.putExtra("eleve",currentEleve);
        startActivity(intent);
    }
}
