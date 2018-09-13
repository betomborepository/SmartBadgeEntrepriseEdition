package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import adapters.entity.Transaction;


/**
 * Created by hp on 22/05/2018.
 */

public class Transaction_VAdapter extends   RecyclerView.Adapter<Transaction_VAdapter.Transaction_VHolder>
{

    private List<Transaction> listTransactions;
    private Context context;

    public Transaction_VAdapter(List<Transaction> eleves, Context context)
    {
        this.listTransactions =eleves;
        this.context = context;
    }


    @NonNull
    @Override
    public Transaction_VAdapter.Transaction_VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_transaction, parent, false);
        return new Transaction_VAdapter.Transaction_VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Transaction_VAdapter.Transaction_VHolder holder, int position) {
        Transaction p = getTransaction(position);

        holder.montant.setText("" + p.montant);
        holder.nomEmploye.setText(p.nomEleve + " [" + p.idEleve + "]");
        try {
            Date date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(p.createdAt.replaceAll("Z$", "+0000"));
            holder.date.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //holder.pointage_poste.setText(p.getPoste());
    }

    @Override
    public int getItemCount() {
        return listTransactions.size();
    }

    private  Transaction getTransaction(int position)
    {
        return  listTransactions.get(position);
    }



    public class Transaction_VHolder extends RecyclerView.ViewHolder  {
        AppCompatTextView montant, date, nomEmploye;


        public Transaction_VHolder(View itemView) {
            super(itemView);
            montant = itemView.findViewById(R.id.montant);
            date = itemView.findViewById(R.id.date);
            nomEmploye = itemView.findViewById(R.id.nom);

        }
    }
}
