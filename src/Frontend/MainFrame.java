package Frontend;

import Backend.*;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class MainFrame {

    private JFrame frame;
    private JTable table;
    private JTable table2;
    private JTable table3;
    private DefaultTableModel vouchersModel;
    private DefaultTableModel campaignsModel;
    private DefaultTableModel notificationsModel;

    public MainFrame() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.getContentPane().setForeground(Color.DARK_GRAY);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNotifications = new JButton("Notifications");
        btnNotifications.setForeground(new Color(30, 144, 255));

        btnNotifications.setBounds(17, 128, 117, 29);
        frame.getContentPane().add(btnNotifications);

        JButton btnCampaigns = new JButton("Campaigns");
        btnCampaigns.setForeground(new Color(30, 144, 255));


        btnCampaigns.setBounds(17, 169, 117, 29);
        frame.getContentPane().add(btnCampaigns);

        JButton btnVouchers = new JButton("Vouchers");
        btnVouchers.setForeground(new Color(30, 144, 255));
        btnVouchers.setBounds(17, 210, 117, 29);
        frame.getContentPane().add(btnVouchers);

        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setForeground(new Color(30, 144, 255));
        lblMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblMenu.setBounds(24, 89, 54, 27);
        frame.getContentPane().add(lblMenu);

        JLabel lblType = new JLabel("      " + LoginFrame.userType);
        lblType.setBounds(17, 6, 69, 16);
        frame.getContentPane().add(lblType);

        JLabel lblLine = new JLabel("_______________");
        lblLine.setBounds(24, 100, 110, 16);
        frame.getContentPane().add(lblLine);

        JLabel label = new JLabel("________________________________");
        label.setBounds(195, 36, 226, 16);
        frame.getContentPane().add(label);

        JPanel panel = new JPanel();
        panel.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
        panel.setBounds(0, 0, 146, 278);
        frame.getContentPane().add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(65, 105, 225));
        panel_1.setBounds(144, 0, 306, 70);
        frame.getContentPane().add(panel_1);

        JLabel welcomeL = new JLabel("Welcome, " + LoginFrame.userLogged);
        panel_1.add(welcomeL);
        welcomeL.setFont(new Font("Tamil Sangam MN", Font.BOLD, 20));

        JButton btnGenerate = new JButton("Generate");
        btnGenerate.setBounds(175, 231, 117, 29);
        frame.getContentPane().add(btnGenerate);
        btnGenerate.setVisible(false);

        JLabel lblAuthor = new JLabel("Terțiu Claudiu ©℗®™");
        lblAuthor.setBounds(255, 265, 107, 13);
        frame.getContentPane().add(lblAuthor);
        lblAuthor.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        JButton btnNewButton_1 = new JButton("Redeem");
        btnNewButton_1.setBounds(304, 231, 117, 29);
        frame.getContentPane().add(btnNewButton_1);
        btnNewButton_1.setVisible(false);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(171, 231, 117, 29);
        frame.getContentPane().add(btnCancel);
        btnCancel.setVisible(false);

        table = new JTable();
        table.setBorder(UIManager.getBorder("DesktopIcon.border"));
        table.setBounds(146, 70, 305, 150);
        table.setVisible(false);

        table2 = new JTable();
        table2.setBorder(UIManager.getBorder("DesktopIcon.border"));
        table2.setBounds(146, 70, 305, 150);
        table2.setVisible(false);

        table3 = new JTable();
        table3.setBorder(UIManager.getBorder("DesktopIcon.border"));
        table3.setBounds(146, 70, 305, 150);
        table3.setVisible(false);

        vouchersModel = (DefaultTableModel) table.getModel();
        vouchersModel.addColumn("ID");
        vouchersModel.addColumn("Email");
        vouchersModel.addColumn("Code");
        vouchersModel.addColumn("Status");
        vouchersModel.addColumn("CampaignID");

        campaignsModel = (DefaultTableModel) table2.getModel();
        campaignsModel.addColumn("ID");
        campaignsModel.addColumn("Name");
        campaignsModel.addColumn("Description");
        campaignsModel.addColumn("Start");
        campaignsModel.addColumn("End");
        campaignsModel.addColumn("Strategy");

        notificationsModel = (DefaultTableModel) table3.getModel();
        notificationsModel.addColumn("Type");
        notificationsModel.addColumn("ID");

        frame.getContentPane().add(table);
        frame.getContentPane().add(table2);
        frame.getContentPane().add(table3);


        Iterator itr = Test.vms.getCampaigns().iterator();
        while (itr.hasNext()) {
            Map.Entry<Integer, Campaign> campaignEntry = (Map.Entry<Integer, Campaign>) itr.next();
            Campaign campaign = campaignEntry.getValue();
            Object[] row = {campaign.getID(), campaign.getName(), campaign.getDescription(), campaign.getStart(), campaign.getEnd(), campaign.getStrategyType()};
            campaignsModel.addRow(row);
        }

        if(LoginFrame.user != null) {
            ArrayList<Voucher> vouchers = Test.vms.getCampaign(LoginFrame.user.getID()).getVouchers();
            for (Voucher v : vouchers) {
                Object[] row = {v.getID(), v.getEmail(), v.getCode(), v.getStatus(), v.getCampID()};
                vouchersModel.addRow(row);
            }
        }


        btnVouchers.addActionListener(e -> {
            table2.setVisible(false);
            table3.setVisible(false);
            btnGenerate.setVisible(true);
            btnCancel.setVisible(false);
            btnNewButton_1.setVisible(false);
            table.setVisible(true);
            SwingUtilities.updateComponentTreeUI(frame);
        });

        btnCampaigns.addActionListener(e -> {
            table.setVisible(false);
            table3.setVisible(false);
            btnGenerate.setVisible(false);
            btnCancel.setVisible(true);
            btnNewButton_1.setVisible(true);
            table2.setVisible(true);
            SwingUtilities.updateComponentTreeUI(frame);
        });

        btnNotifications.addActionListener(e -> {
            table.setVisible(false);
            table2.setVisible(false);
            btnGenerate.setVisible(false);
            btnCancel.setVisible(false);
            btnNewButton_1.setVisible(false);
            table3.setVisible(true);
            SwingUtilities.updateComponentTreeUI(frame);
        });

        btnGenerate.addActionListener(e -> {
            Voucher v = Test.vms.getCampaign(LoginFrame.user.getID()).generateVoucher(LoginFrame.user.getEmail(), "A", 10);
            Object[] row = {v.getID(), v.getEmail(), v.getCode(), v.getStatus(), v.getCampID()};
            vouchersModel.addRow(row);
        });

        btnCancel.addActionListener(e -> {

        });
    }

    public static void run() {
        MainFrame window = new MainFrame();
        window.frame.setVisible(true);
    }
}
