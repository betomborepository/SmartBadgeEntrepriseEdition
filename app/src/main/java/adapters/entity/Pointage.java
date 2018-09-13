package adapters.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hp on 22/05/2018.
 */

public class Pointage {
    public String ID;
    public String poste;
    public String createdAt;
    public Employe pointageEmploye;
    public Pointage()
    {

    }


    public Pointage(String id, String poste )
    {
        this.ID = id;
        this.poste = poste;
        this.createdAt = new Date().toString();

    }




    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }




    public String getCreatedAt()
    {
        try {
            if(createdAt == null)
                return  "";
            Date date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(createdAt.replaceAll("Z$", "+0000"));
            return  new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return createdAt;
    }


}
