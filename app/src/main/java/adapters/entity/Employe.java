package adapters.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 22/05/2018.
 */

public class Employe implements Serializable{
    public  String id;
    public String nom;
    public  String prenom;
    public  String description;
    public  String matricul;
    public Fonction employeFonction;
    public  String age;
    public  String adresse;
    List<Pointage> listPointage;
    public int argent = 20;

    public Employe(){

    }
    public Employe(String name, String surName, String description, String immatricul)
    {
        this.nom = name;
        this.prenom = surName;
        this.description = description;
        this.matricul = immatricul;
    }


    public String shortDescription()
    {
        return description.substring(0, 20) + "...";
    }

    public String getListName()
    {
        String listName = nom + " " + prenom + "  [" + matricul + "]";
        int tailleLimit = 35;
        if(listName.length() >= tailleLimit)
        return listName.substring(0, tailleLimit) + "...";

        return  listName;
    }

    public void generatePointage()
    {
        listPointage = new ArrayList<Pointage>();
    }

    public Transaction depenser(int montant){
        this.argent --;
        Transaction t = new Transaction();
        t.montant = montant;
        t.nomEleve = this.nom;
        t.idEleve = this.matricul;
        return  t;
    }


}

