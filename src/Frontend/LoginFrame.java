package Frontend;

import Backend.Test;
import Backend.User;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.util.Map;
import java.util.Set;

public class LoginFrame extends JFrame {
    private JLabel usernameL;
    private JLabel passwordL;
    private JLabel emailL;
    private JTextField username;
    private JTextField email;
    private JPasswordField password;
    private JPanel panel;
    private JPanel panel2;
    private JButton validate;
    private JButton register;
    private JFileChooser jfc;
    private int frame = 1;
    public static String userLogged;
    public static String userType;
    public static User user;

    public LoginFrame() throws HeadlessException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(450,300);
        setMinimumSize(new Dimension(300, 100));
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(200,180));

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2 = new JPanel();
        usernameL = new JLabel("Username:");
        emailL = new JLabel("Email:       ");
        passwordL = new JLabel("Password: ");
        validate = new JButton("Login");
        register = new JButton("Register");
        username = new JTextField(10);
        email = new JTextField(10);
        password = new JPasswordField(10);
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setSize(new Dimension(100, 100));


        panel.setPreferredSize(new Dimension(230,100));
        panel.setOpaque(false);
        panel2.setOpaque(false);

        panel.add(usernameL);
        panel.add(username);
        panel.add(passwordL);
        panel.add(password);
        panel2.add(validate);
        panel2.add(register);

        validate.addActionListener(e -> {
            if (frame != 1)
                login();
            if(frame == 1 && checkCredentials(username.getText(), password.getText())) {
                setVisible(false);
                checkCredentials(username.getText(), password.getText());
                userLogged = username.getText();
                MainFrame.run();
            }
            frame = 1;
        });

        register.addActionListener(e -> {
            if(frame != 2)
                register();
            if(!(username.getText().equals(null) || password.getText().equals(null) || email.getText().equals(null)) && frame == 2) {
                User user = new User(Test.vms.getLatestId() + 1, username.getText(), email.getText(), password.getSelectedText(), User.UserType.GUEST);
                LoginFrame.user = user;
                Test.vms.addUsers(user);
                userType = "GUEST";
                userLogged = username.getText();
                setVisible(false);
                MainFrame.run();
            }
            frame = 2;
        });

        password.addActionListener(e -> {
            if ((username.getText().equals(null) || password.getText().equals(null) || email.getText().equals(null)) && frame == 1)
                JOptionPane.showMessageDialog(new JFrame(), "Completeaza campurile", "Eroare", JOptionPane.ERROR_MESSAGE);
            else if (!(username.getText().equals(null) || password.getText().equals(null) || email.getText().equals(null)) && frame == 2) {
                User user = new User(Test.vms.getLatestId() + 1, username.getText(), email.getText(), password.getSelectedText(), User.UserType.GUEST);
                LoginFrame.user = user;
                Test.vms.addUsers(user);
                setVisible(false);
                userType = "GUEST";
                userLogged = username.getText();
                MainFrame.run();
            }
            if(frame == 1 && checkCredentials(username.getText(), password.getText())) {
                setVisible(false);
                userLogged = username.getText();
                MainFrame.run();
            }
        });

        email.addActionListener(e -> {
            if ((username.getText().equals(null) || password.getText().equals(null) || email.getText().equals(null)) && frame == 2)
                JOptionPane.showMessageDialog(new JFrame(), "Completeaza campurile", "Eroare", JOptionPane.ERROR_MESSAGE);
        });

        username.addActionListener(e -> {
            if ((username.getText().equals(null) || password.getText().equals(null) || email.getText().equals(null)) && frame == 1)
                JOptionPane.showMessageDialog(new JFrame(), "Completeaza campurile", "Eroare", JOptionPane.ERROR_MESSAGE);
            if ((username.getText().equals(null) || password.getText().equals(null) || email.getText().equals(null)) && frame == 2) {
                User user = new User(Test.vms.getLatestId() + 1, username.getText(), email.getText(), password.getSelectedText(), User.UserType.GUEST);
                LoginFrame.user = user;
                Test.vms.addUsers(user);
                setVisible(false);
                userType = "GUEST";
                userLogged = username.getText();
                MainFrame.run();
            }
            if(checkCredentials(username.getText(), password.getText())) {
                setVisible(false);
                userLogged = username.getText();
                MainFrame.run();
            }

        });

        add(panel, BorderLayout.CENTER);
        add(panel2);

        pack();
        show();
    }

    private void login() {
        panel.removeAll();
        panel.add(usernameL);
        panel.add(username);
        panel.add(passwordL);
        panel.add(password);
        add(panel, BorderLayout.CENTER);
        add(panel2);
        pack();
    }

    private void register() {
        panel.removeAll();
        panel.add(usernameL);
        panel.add(username);
        panel.add(emailL);
        panel.add(email);
        panel.add(passwordL);
        panel.add(password);
        add(panel, BorderLayout.CENTER);
        add(panel2);
        pack();
    }

    private boolean checkCredentials(String username, String password) {
        Set<Map.Entry<Integer, User>> usersEntries = Test.vms.getUsers();
        for (Map.Entry<Integer, User> userEntry: usersEntries) {
            if(userEntry.getValue().getName().equals(username) && userEntry.getValue().getPassword().equals(password)) {
                userLogged = username;
                userType = userEntry.getValue().getUserType().toString();
                LoginFrame.user = userEntry.getValue();
                return true;
            }
        }
        JOptionPane.showMessageDialog(new JFrame(), "Datele introduse sunt incorecte.", "Eroare", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
