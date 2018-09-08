package fragments;


import com.smart.badge.R;

import adapters.Pointage_VAdapter;
import service.DataCore;
import service.WebService;
import service.handler.ListEmployeHandler;
import service.handler.ListPointageHandler;

/**
 * Created by hp on 21/05/2018.
 */

public class Pointage extends ListBase {
    Pointage_VAdapter pointage_vAdapter;
    @Override
    protected void refresh_list() {

     //   new DataCore().GetListPointage(this.getActivity(),recyclerView);
        String url = getResources().getString(R.string.web_service_all_pointage_url);
        ListPointageHandler handler = new ListPointageHandler(url, this.getActivity(), recyclerView);
        WebService.SendRequest(handler);
    }
}
