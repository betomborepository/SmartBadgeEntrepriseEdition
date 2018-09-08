package com.smart.badge;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import adapters.Profile;
import adapters.entity.Eleve;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatTextView detail_name, detail_about, detail_active_time;

    private Profile profile;
    private Eleve currentEleve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Détail Employé");
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));


        //Getting data from the previous activity (MainActivity)
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            try {
                Eleve el = (Eleve) getIntent().getSerializableExtra("eleve");
                currentEleve = el;
                initialize(el);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "ERROR on parsing received data: \n"+e.toString(),
                        Toast.LENGTH_LONG).show();
            }

        }
    }


    private void initialize(Eleve el) {

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
        Intent intent = new Intent(DetailActivity.this, NFCApplyTagEleve.class);
        intent.putExtra("eleve",currentEleve);
        startActivity(intent);

    }

    public void onClickBouttonModifier(View v){
        Intent intent = new Intent(DetailActivity.this, CreationActivity.class);
        intent.putExtra("userEmploy",currentEleve);
        startActivity(intent);
    }


    public  void onClickBouttonRetour(View view){
        this.finish();
    }
}
