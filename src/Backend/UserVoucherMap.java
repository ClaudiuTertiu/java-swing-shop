package Backend;

import java.util.ArrayList;

public class UserVoucherMap extends ArrayMap<Integer, ArrayList<Voucher>> {

    public UserVoucherMap() {
    }

    public boolean addVoucher(Voucher v) {
        if(containsKey(v.getID())) {
            ArrayList<Voucher> list = get(v.getEmail());
            if(list.contains(v))
                return false;
            else {
                list.add(v);
                put(v.getID(), list);
                return true;
            }
        }

        else {
            ArrayList<Voucher> list = new ArrayList<>();
            list.add(v);
            put(v.getID(), list);
            return true;
        }
    }
}
