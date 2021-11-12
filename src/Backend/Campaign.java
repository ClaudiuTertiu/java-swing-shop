package Backend;

import java.time.LocalDateTime;;
import java.util.*;

public class Campaign {
    private Integer id;
    private String name;
    private String description;
    private int noVouchers;
    private int vouchersAv;
    private int generatorID = 0;
    private LocalDateTime start;
    private LocalDateTime end;
    private String strategyType;
    private String campaignStatusType;
    private ArrayList<Voucher> givenVouchers;
    private ArrayList<User> observers;
    private enum CampaignStatusType {
        NEW,
        STARTED,
        EXPIRED,
        CANCELLED
    }

    public Campaign() {
        observers = new ArrayList<>();
        givenVouchers = new ArrayList<>();
    }

    public Campaign(Integer id, String name, String description, LocalDateTime start, LocalDateTime end, int noVouchers, String strategyType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.noVouchers = noVouchers;
        this.vouchersAv = noVouchers;
        this.start = start;
        this.end = end;
        observers = new ArrayList<>();
        givenVouchers = new ArrayList<>();
        this.strategyType = strategyType;
    }

    public ArrayList<Voucher> getVouchers() {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        if(Test.vms.getCampaigns() != null) {
            Iterator itr = Test.vms.getCampaigns().iterator();
            while(itr.hasNext()) {
                Map.Entry<Integer, Campaign> campaign = (Map.Entry<Integer, Campaign>) itr.next();
                ArrayList<Voucher> vouchers2 = campaign.getValue().givenVouchers;
                for (Voucher v: vouchers2) {
                    vouchers.add(v);
                }
            }
        }
        return vouchers;
    }

    public Voucher getVoucher(String code) {
        ArrayList<Voucher> vouchers = getVouchers();
        for (Voucher v: vouchers) {
            if(v.getCode().equals(code))
                return v;
        }
        return null;
    }

    private String generateCouponCode() {
        Random rand = new Random();
        long rand_int = rand.nextInt(100000);
        return Long.toString(rand_int);
    }

    public Voucher generateVoucher(String email, String voucherType, float value) {
        Voucher newVoucher;
        LocalDateTime localDate = LocalDateTime.now();
        if(voucherType.equals("LoyaltyVoucher")) {
            newVoucher = new LoyalityVoucher(generatorID, generateCouponCode(), "NEW", localDate, email, getID(), value);
            givenVouchers.add(newVoucher);
        }
        else {
            newVoucher = new GiftVoucher(generatorID, generateCouponCode(), "NEW", localDate, email, getID(), value);
            givenVouchers.add(newVoucher);
        }
        noVouchers++;
        generatorID++;
        return newVoucher;
    }

    public void redeemVoucher(String code, LocalDateTime date) {
        getVoucher(code).setUse(date);
        getVoucher(code).setVoucherStatusType("USED");
    }

    public ArrayList<User> getObservers() {
        addObservers(Test.vms.getUsers());
        return observers;
    }

    public void addObserver(User user) {
        if(user.getVoucherMap() != null)
            observers.add(user);
    }

    public void addObservers(Set<Map.Entry<Integer, User>> users) {
        Iterator itr = users.iterator();
        while(itr.hasNext()) {
            Map.Entry<Integer, User> userEntry = (Map.Entry<Integer, User>) itr.next();
            User user = userEntry.getValue();
            if(user.getVoucherMap() != null)
                observers.add(user);
        }
    }

    public void removeObserver(Integer id) {
        Iterator itr = observers.iterator();
        while(itr.hasNext()) {
            User user = (User) itr.next();
            if(user.getID() == id)
                observers.remove(id);
        }
    }

    public void removeObserver(User user) {
        Iterator itr = observers.iterator();
        while(itr.hasNext()) {
            User findUser = (User) itr.next();
            if(findUser.getID() == id) {
                observers.remove(findUser);
                return;
            }
        }
    }

    public void notifyAllObservers(Notification  notification) {
        for (int i = 0; i < observers.size(); i++) {
            ArrayList<Notification> notifications = observers.get(i).getNotifications();
            notifications.add(notification);
            observers.get(i).setNotifications(notifications);
            return;
        }
    }


    public Integer getID() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoVouchers() {
        return noVouchers;
    }

    public void setNoVouchers(int noVouchers) {
        this.noVouchers = noVouchers;
    }

    public int getVouchersAv() {
        return vouchersAv;
    }

    public void setVouchersAv(int vouchersAv) {
        this.vouchersAv = vouchersAv;
    }

    public Integer getId() {
        return id;
    }

    public int getGeneratorID() {
        return generatorID;
    }

    public void setGeneratorID(int generatorID) {
        this.generatorID = generatorID;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public String getCampaignStatusType() {
        return campaignStatusType;
    }

    public void setCampaignStatusType(String campaignStatusType) {
        this.campaignStatusType = campaignStatusType;
    }

    public ArrayList<Voucher> getGivenVouchers() {
        return givenVouchers;
    }

    public void setGivenVouchers(ArrayList<Voucher> givenVouchers) {
        this.givenVouchers = givenVouchers;
    }

    public void setObservers(ArrayList<User> observers) {
        this.observers = observers;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", noVouchers=" + noVouchers +
                ", vouchersAv=" + vouchersAv +
                ", start=" + start +
                ", end=" + end +
                ", strategyType='" + strategyType + '\'' +
                ", campaignStatusType=" + campaignStatusType +
                ", givenVouchers=" + givenVouchers +
                '}';
    }
}
