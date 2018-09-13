package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.R;

import java.util.List;

import adapters.entity.Pointage;


/**
 * Created by hp on 22/05/2018.
 */

public class PointageEmploye_VAdapter extends   Pointage_VAdapter
{

    private List<Pointage> listPointages;
    private Context context;


    public PointageEmploye_VAdapter(List<Pointage> eleves, Context context)
    {
        super(eleves, context);
        this.listPointages =eleves;
        this.context = context;
    }


    @NonNull
    @Override
    public Pointage_VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_pointage_employe, parent, false);
        return new Pointage_VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pointage_VHolder holder, int position) {
        Pointage p = getPointage(position);
         holder.pointage_heure.setText(p.getCreatedAt());
        //holder.pointage_poste.setText(p.getPoste());
    }

    @Override
    public int getItemCount() {
        return listPointages.size();
    }

    private  Pointage getPointage(int position)
    {
        return  listPointages.get(position);
    }
}
