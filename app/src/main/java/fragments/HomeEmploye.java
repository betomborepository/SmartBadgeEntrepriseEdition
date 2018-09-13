package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.R;

import service.SessionCore;


/**
 * Created by hp on 21/05/2018.
 */

public class HomeEmploye extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_employe, container, false);
        View image = view.findViewById(R.id.image_employe_fiche);
        adapters.entity.Employe ep = SessionCore.getCurrentUser(this.getActivity()).userEmploy;

        image.setTag(ep);

        return view;
    }
}
