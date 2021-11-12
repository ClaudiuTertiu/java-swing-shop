package Backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {
    static public VMS vms = VMS.getInstance();
    static public ArrayList<String[]> events;

    public Test() {
        DateTimeFormatter formatter;
        events = new ArrayList<>();
        try {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            BufferedReader br = new BufferedReader(new FileReader("/Users/claudiutertiu/Desktop/Tema/src/VMStests/test03/input/campaigns.txt"));
            int noCampaigns = Integer.parseInt(br.readLine());
            String line = br.readLine();
            LocalDateTime date = LocalDateTime.parse(line, formatter);
            while ((line = br.readLine()) != null) {
                String[] subStrings = line.split(";");
                LocalDateTime dt2 = LocalDateTime.parse(subStrings[3], formatter);
                LocalDateTime dt3 = LocalDateTime.parse(subStrings[4], formatter);
                Campaign campaign = new Campaign(Integer.parseInt(subStrings[0]), subStrings[1], subStrings[2], dt2, dt3, Integer.parseInt(subStrings[5]), subStrings[6]);
                Test.vms.addCampaign(campaign);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Citirea din fisierul pentru users.
         */

        try {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            BufferedReader br = new BufferedReader(new FileReader("/Users/claudiutertiu/Desktop/Tema/src/VMStests/test03/input/users.txt"));
            int noUsers = Integer.parseInt(br.readLine());
            String line;
            while ((line = br.readLine()) != null) {
                String[] subStrings = line.split(";");
                if (subStrings[4].equals("ADMIN"))
                    Test.vms.addUsers(new User(Integer.parseInt(subStrings[0]), subStrings[1], subStrings[3], subStrings[2], User.UserType.ADMIN));
                else
                    Test.vms.addUsers(new User(Integer.parseInt(subStrings[0]), subStrings[1], subStrings[3], subStrings[2], User.UserType.GUEST));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Citirea din fisierul pentru events.
         */

        try {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            BufferedReader br = new BufferedReader(new FileReader("/Users/claudiutertiu/Desktop/Tema/src/VMStests/test03/input/events.txt"));
            LocalDateTime dt = LocalDateTime.parse(br.readLine(), formatter);
            int noEvents = Integer.parseInt(br.readLine());
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(";");
                events.add(lineArray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Parcurgerea evenimentelor din events.
         */

        for (String[] arr : events) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (arr[1].equals("addCampaign")) {
                if (Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    LocalDateTime dt = LocalDateTime.parse(arr[5], formatter);
                    LocalDateTime dt2 = LocalDateTime.parse(arr[6], formatter);
                    Campaign campaign = new Campaign(Integer.parseInt(arr[2]), arr[3], arr[4], dt, dt2, Integer.parseInt(arr[7]), arr[8]);
                    vms.addCampaign(campaign);
                }
            }

            if (arr[1].equals("editCampaign")) {
                if (Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    User user = Test.vms.getUser(Integer.parseInt(arr[0]));
                    if (user.getUserType().equals(User.UserType.ADMIN)) {
                        LocalDateTime dt = LocalDateTime.parse(arr[5]);
                        LocalDateTime dt2 = LocalDateTime.parse(arr[6]);
                        Campaign campaign = new Campaign(Integer.parseInt(arr[2]), arr[3], arr[4], dt, dt2, Integer.parseInt(arr[7]), arr[8]);
                        Test.vms.updateCampaign(Integer.parseInt(arr[0]), campaign);
                    }
                }
            }

            if (arr[1].equals("cancelCampaign")) {
                User user = Test.vms.getUser(Integer.parseInt(arr[0]));
                if (vms.getUser(Integer.parseInt(arr[0])) != null) {
                    if (user.getUserType().equals(User.UserType.ADMIN)) {
                        Test.vms.cancelCampaign(Integer.parseInt(arr[2]));
                    }
                }
            }

            if (arr[1].equals("generateVoucher")) {
                if(Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    Test.vms.getCampaign(Integer.parseInt(arr[2])).generateVoucher(arr[3], arr[4], Float.parseFloat(arr[5]));
                    System.out.println(Test.vms.getCampaign(Integer.parseInt(arr[2])).generateVoucher(arr[3], arr[4], Float.parseFloat(arr[5])));
                }
            }

            if (arr[1].equals("redeemVoucher")) {
                if (Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    Test.vms.getCampaign(Integer.parseInt(arr[2])).redeemVoucher(arr[3], LocalDateTime.parse(arr[4]));
                }
            }

            if (arr[1].equals("getVouchers")) {
                if (Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    if(Test.vms.getCampaign(Integer.parseInt(arr[0])) != null)
                        System.out.println(Test.vms.getCampaign(Integer.parseInt(arr[0])).getVouchers());
                }
            }

            if (arr[1].equals("getObservers")) {
                if (Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    if(Test.vms.getCampaign(Integer.parseInt(arr[2])) != null)
                        System.out.println(Test.vms.getCampaign(Integer.parseInt(arr[2])).getObservers());
                }
            }

            if (arr[1].equals("getNotifications")) {
                if (Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    System.out.println(Test.vms.getUser(Integer.parseInt(arr[0])).getNotifications());
                }
            }

            if (arr[1].equals("getVoucher")) {
                if (Test.vms.getUser(Integer.parseInt(arr[0])) != null) {
                    System.out.println(Test.vms.getCampaign(Integer.parseInt(arr[2])).getVoucher(arr[2]));
                }
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
    }
}
