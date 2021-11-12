package Backend;

import java.util.ArrayList;

public class CampaignVoucherMap extends ArrayMap<String, ArrayList<Voucher>> {

    public CampaignVoucherMap() {
    }

    public boolean addVoucher(Voucher v) {
        if(containsKey(v.getEmail())) {
            ArrayList<Voucher> list = get(v.getEmail());
            if(list.contains(v))
                return false;
            else {
                list.add(v);
                put(v.getEmail(), list);
                return true;
            }
        }
        else {
            ArrayList<Voucher> list = new ArrayList<>();
            list.add(v);
            put(v.getEmail(), list);
            return true;
        }
    }
}
