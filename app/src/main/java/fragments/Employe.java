package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.R;

import adapters.Eleve_VAdapter;
import adapters.Profile;
import adapters.Profile_VAdapter;
import service.DataCore;
import service.WebService;
import service.handler.ListEmployeHandler;


/**
 * Created by hp on 21/05/2018.
 */

public class Employe extends ListBase {

    Eleve_VAdapter eleve_vAdapter;
    @Override
    protected void refresh_list() {

        Activity a = this.getActivity();


        String url =  getResources().getString(R.string.web_service_all_employe_url);
        ListEmployeHandler handler = new ListEmployeHandler(url, a, recyclerView);
        WebService.SendRequest(handler);
    }
}
