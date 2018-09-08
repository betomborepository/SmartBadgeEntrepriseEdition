package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.smart.badge.NFCPaiement;
import com.smart.badge.PagePaiement;
import com.smart.badge.R;


/**
 * Created by hp on 21/05/2018.
 */

public class HomeCantine extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_cantine, container, false);
    }



}
