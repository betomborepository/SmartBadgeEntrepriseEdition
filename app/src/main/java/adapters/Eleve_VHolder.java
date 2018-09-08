package adapters;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.smart.badge.R;

/**
 * Created by hp on 22/05/2018.
 */

public class Eleve_VHolder extends RecyclerView.ViewHolder{

    public AppCompatTextView eleve_name, eleve_detail;
    public RelativeLayout card_container;
    public Eleve_VHolder(View itemView)
    {
        super(itemView);

        eleve_name= itemView.findViewById(R.id.title);
        eleve_detail= itemView.findViewById(R.id.detail);
        card_container = itemView.findViewById(R.id.rel_donnee);
    }
}
