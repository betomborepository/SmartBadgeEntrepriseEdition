package fragments;


import com.smart.badge.R;

import adapters.Pointage_VAdapter;
import service.DataCore;
import service.SessionCore;
import service.WebService;
import service.handler.ListPointageHandler;

/**
 * Created by hp on 21/05/2018.
 */

public class PointageEmploye extends ListBase {
    Pointage_VAdapter pointage_vAdapter;
    @Override
    protected void refresh_list() {
        //   new DataCore().GetListPointage(this.getActivity(),recyclerView);

        String idemp = SessionCore.getCurrentUser(this.getActivity()).userEmploy.id;
        String url = getResources().getString(R.string.web_service_all_pointage_by_employe_url) + "?id=" + idemp;
        ListPointageHandler handler = new ListPointageHandler(url, this.getActivity(), recyclerView);
        WebService.SendRequest(handler);

    }
}
