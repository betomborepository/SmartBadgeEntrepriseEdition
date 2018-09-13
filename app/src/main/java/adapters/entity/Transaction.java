package adapters.entity;

import java.util.Date;

/**
 * Created by hp on 22/05/2018.
 */

public class Transaction {
    public String id;
    public String poste;
    public String date;
    public String createdAt;
    public String nomEleve;
    public  String idEleve;
    public  int montant;

    public Transaction()
    {

    }


    public Transaction(String id, String poste, String nomEleve, String IdEleve)
    {
        this.id = id;
        this.poste = poste;
        this.date = new Date().toString();
        this.nomEleve =nomEleve;
        this.idEleve = IdEleve;
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
