package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.NFCConfiguration;
import com.smart.badge.R;

import java.util.List;

import adapters.entity.Employe;


/**
 * Created by hp on 22/05/2018.
 */

public class EleveConfiguration_VAdapter extends RecyclerView.Adapter<Eleve_VHolder>
{
    private List<Employe> ListEleves;
    private  Context context;

    public EleveConfiguration_VAdapter(List<Employe> eleves, Context context)
    {
        this.ListEleves =eleves;
        this.context = context;
    }


    @NonNull
    @Override
    public Eleve_VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_eleve, parent, false);
        return new Eleve_VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Eleve_VHolder holder, int position) {
        Employe el = getEleve(position);

        holder.itemView.setTag(el);
        holder.eleve_name.setText(el.getListName());
        holder.eleve_name.setTag(el);
        holder.eleve_detail.setText(el.shortDescription());
        holder.eleve_detail.setTag(el);
        holder.eleve_name.setTag(el);
        holder.card_container.setOnClickListener(new EleveConfiguratonOnClikListener(el));
        // populate the view holder
    }

    @Override
    public int getItemCount() {
        return ListEleves.size();
    }


    private Employe getEleve(int position)
    {
        return  ListEleves.get(position);
    }


    private class EleveConfiguratonOnClikListener implements View.OnClickListener{

        Employe currentEmploye;
        public EleveConfiguratonOnClikListener(Employe employe){
            currentEmploye = employe;
        }

        @Override
        public void onClick(View v) {
            // go to nfc
            Intent intent = new Intent( v.getContext(), NFCConfiguration.class);
            intent.putExtra("userEmploy",currentEmploye);
            v.getContext().startActivity(intent);
        }
    }
}
