package Backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class VMS {
    private ArrayMap<Integer, Campaign> campaigns;
    private ArrayMap<Integer, User> users;
    private ArrayList<Voucher> vouchers;
    private static VMS instance;

    private VMS() {
        users = new ArrayMap<>();
        campaigns = new ArrayMap<>();
        vouchers = new ArrayList<>();
    }


    public static synchronized VMS getInstance( ) {
        if (instance == null)
            instance = new VMS();
        return instance;
    }

    public Set<Map.Entry<Integer, Campaign>> getCampaigns() {
        return campaigns.entrySet();
    }

    public Campaign getCampaign(Integer id) {
        if (campaigns.containsKey(id) && campaigns.get(id) != null)
            return campaigns.get(id);
        return null;
    }

    public void addCampaign(Campaign campaign) {
        if(campaigns.containsKey(campaign.getID()))
            return;
        else if(campaign != null)
            campaigns.put(campaign.getID(), campaign);
    }

    public void updateCampaign(Integer id, Campaign campaign) {
        if(campaigns.containsKey(campaign.getID())) {
            campaigns.get(id).setId(campaign.getID());
            campaigns.get(id).setName(campaign.getName());
            campaigns.get(id).setDescription(campaign.getDescription());
            campaigns.get(id).setStart(campaign.getStart());
            campaigns.get(id).setEnd(campaign.getEnd());
            campaigns.get(id).setNoVouchers(campaign.getNoVouchers());
            campaigns.get(id).setVouchersAv(campaign.getVouchersAv());
        }
    }

    public void cancelCampaign(Integer id) {
        if(campaigns.containsKey(id)) {
            campaigns.get(id).setCampaignStatusType("CANCELLED");
            campaigns.remove(id);
        }
    }

    public Set<Map.Entry<Integer, User>> getUsers() {
        return users.entrySet();
    }

    public User getUser(Integer id) {
        if(!users.containsKey(id))
            return null;
        return users.get(id);
    }

    public void addUsers(User user) {
        if(users.containsKey(user.getID()))
            return;
        else {
            users.put(user.getID(), user);
        }
    }

    public int getLatestId() {
        Set<Map.Entry<Integer, User>> usersEntries = Test.vms.getUsers();
        int biggest = 0;
        for (Map.Entry<Integer, User> userEntry : usersEntries) {
            if (userEntry.getValue().getID() > biggest) {
                biggest = userEntry.getValue().getID();
            }
        }
        return biggest;
    }

    public ArrayList<Voucher> getVouchers() {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        if(Test.vms.getCampaigns() != null) {
            Iterator itr = Test.vms.getCampaigns().iterator();
            while(itr.hasNext()) {
                Map.Entry<Integer, Campaign> campaign = (Map.Entry<Integer, Campaign>) itr.next();
                ArrayList<Voucher> vouchers2 = campaign.getValue().getGivenVouchers();
                for (Voucher v: vouchers2) {
                    vouchers.add(v);
                }
            }
        }
        return vouchers;
    }
}
