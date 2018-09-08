package adapters.entity;

import java.util.Date;

/**
 * Created by hp on 22/05/2018.
 */

public class Transaction {
    public String ID;
    public String poste;
    public String date;
    public String nomEleve;
    public  String IDEleve;
    public  int montant;

    public Transaction()
    {

    }


    public Transaction(String id, String poste, String nomEleve, String IDEleve )
    {
        this.ID = id;
        this.poste = poste;
        this.date = new Date().toString();
        this.nomEleve =nomEleve;
        this.IDEleve = IDEleve;
    }




    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }




    public String getDate() {
        return date;
    }


}
