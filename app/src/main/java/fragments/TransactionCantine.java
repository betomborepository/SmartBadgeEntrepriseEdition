package fragments;


import com.smart.badge.R;

import adapters.Pointage_VAdapter;
import service.DataCore;
import service.WebService;
import service.handler.TransactionHandler;

/**
 * Created by hp on 21/05/2018.
 */

public class TransactionCantine extends ListBase {
    Pointage_VAdapter pointage_vAdapter;
    @Override
    protected void refresh_list() {

        //new DataCore().refreshListTransaction(this.getActivity(),recyclerView);
        String url = this.getActivity().getResources().getString(R.string.web_service_all_transaction_url);
        System.out.println(url);
        WebService.SendRequest(new TransactionHandler(url, this.getActivity() ,recyclerView));
    }
}
