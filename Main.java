import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.EventQueue;

final class Main {
    private Connection con = null;
    private String url = "jdbc:sqlite:./SQLite.db";
    private JFrame window = new JFrame("college Management v0.1");
    private JPanel body = new JPanel();
    private JButton[] btn;
    private JPanel head = new JPanel();
    private JPanel management = new JPanel();
    private JPanel studentmanagement = new JPanel();

    private JPanel usermanagement = new JPanel();
    private JButton usermanagementbtnback = new JButton("back");
    private JPanel reports = new JPanel();

    private JPanel idcards = new JPanel();

    private JPanel export = new JPanel();

    JPanel editstudentdetails = new JPanel();
    JButton editstudentbackbtn = new JButton("back to Student Management");
    private JButton[] managementbtn;
    private JButton[] studentmanagementbtn;
    JLabel lbl = new JLabel();

    private boolean loginkey = false;

    private void run() {
        lbl.setIcon(new ImageIcon("image.jpg"));
        lbl.setBounds(0, 0, 1720, 820);
        Font ft = new Font("Comic Sans MS", Font.BOLD, 18);
        // setting window sepecification
        window.setVisible(true);
        window.setLayout(null);
        window.setSize(2080, 820);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        head.setBackground(new Color(123, 250, 200));
        head.setBounds(0, 0, 360, 820);
        head.setLayout(null);

        // head components
        JLabel label1 = new JLabel("College Management");
        label1.setBounds(80, 120, 240, 40);
        label1.setForeground(Color.black);
        label1.setFont(ft);
        btn = new JButton[6];
        btn[0] = new JButton("login");
        btn[1] = new JButton("Documentation");
        btn[2] = new JButton("What's new ?");
        btn[3] = new JButton("Home");
        btn[4] = new JButton("Exit");
        btn[5] = new JButton("logout");
        btn[5].setVisible(false);
        int b = 120;
        for (int i = 0; i <= 5; i++) {
            b += 50;
            btn[i].setBounds(30, b, 260, 30);
            btn[i].setForeground(Color.white);
            btn[i].setBackground(Color.BLACK);
            btn[i].setFont(new Font("Adobe Garamond Pro Bold", Font.ITALIC, 15));
            btn[i].addActionListener(actions);
            head.add(btn[i]);
        }
        JLabel label2 = new JLabel("Beta Test ,Developed By srinivas");
        label2.setBounds(30, 620, 260, 120);
        label2.setForeground(Color.black);
        head.add(label1);
        head.add(label2);
        window.add(head);
        // body context

        body.add(lbl);

        body.setLayout(null);
        body.setBackground(Color.black);
        body.setBounds(360, 0, 1720, 820);
        window.add(body);

        window.add(management);
        // menu buttons
        JMenuBar mb = new JMenuBar();
        JMenu menubtn[] = new JMenu[4];
        menubtn[0] = new JMenu("Home");
        menubtn[1] = new JMenu("Exit");
        menubtn[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int check = JOptionPane.showConfirmDialog(window, "Do u want to close the window", "confirm", 1);
                if (check == 1) {
                    System.exit(0);
                }

            }
        });
        menubtn[2] = new JMenu("student Management\t");
        menubtn[3] = new JMenu("Reports");
        JMenuItem cut, copy, paste, selectAll;
        cut = new JMenuItem("Student management Window");
        copy = new JMenuItem("Usermanagement window");
        paste = new JMenuItem("Reports");
        selectAll = new JMenuItem("help");
        menubtn[0].add(cut);
        menubtn[0].add(copy);
        menubtn[0].add(paste);
        menubtn[0].add(selectAll);

        for (int i = 0; i < 4; i++) {
            mb.add(menubtn[i]);
        }
        window.add(mb);
        window.setJMenuBar(mb);
    }

    void management() {

        management.setBackground(new Color(122, 0, 180));
        management.setForeground(Color.BLACK);
        management.setBounds(360, 0, 1720, 820);
        management.setLayout(null);
        window.add(management);

        // button
        managementbtn = new JButton[5];
        managementbtn[0] = new JButton("user-management");// ui completed ,action and backend not completed
        managementbtn[1] = new JButton("student Management");// ui completed ,action
        managementbtn[2] = new JButton("Reports");
        managementbtn[3] = new JButton("Export to Excel");
        managementbtn[4] = new JButton("Exit");

        int b = 120;
        for (int i = 0; i < 5; i++) {
            b += 50;
            managementbtn[i].setBounds(30, b, 260, 30);
            managementbtn[i].setForeground(Color.white);
            managementbtn[i].setBackground(Color.BLACK);
            managementbtn[i].setFont(new Font("Adobe Garamond Pro Bold", Font.ITALIC, 15));
            managementbtn[i].addActionListener(actions);
            management.add(managementbtn[i]);
        }
        management.add(lbl);
    }

    void idcards() {
        idcards.setBackground(new Color(122, 0, 180));
        idcards.setForeground(Color.black);
        idcards.setBounds(360, 0, 1720, 820);
        usermanagement.setLayout(null);
        window.add(idcards);
    }

    void export() {
        export.setBackground(new Color(122, 0, 180));
        export.setForeground(Color.black);
        export.setBounds(360, 0, 1720, 820);
        export.setLayout(null);
        window.add(export);
    }

    void reports() {
        reports.setBackground(new Color(122, 0, 180));
        reports.setForeground(Color.black);
        reports.setBounds(360, 0, 1720, 820);
        reports.setLayout(null);
        window.add(reports);
        String id = JOptionPane.showInputDialog(reports, "Enter Student Hallticket Number");
        try {
            String input = "select * from student where hallticket =\'" + id + "\';";
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(input);
            ResultSet rs = pstmt.executeQuery();
            pstmt.close();
            con.commit();
            con.close();
            if (rs.next()) {
                JOptionPane.showMessageDialog(window, "Found Student:\t");
            } else {
                JOptionPane.showConfirmDialog(window, "wrong details\n Try again");
                reports();
            }
            while (rs.next()) {

            }
        } catch (SQLException a) {
            JOptionPane.showMessageDialog(window, "Not updated:\t" + a.getMessage());
        }
        reports.add(lbl);
    }

    void usermanagement() {
        usermanagement.setBackground(new Color(122, 0, 180));
        usermanagement.setForeground(Color.black);
        usermanagement.setBounds(360, 0, 1720, 820);
        usermanagement.setLayout(null);
        window.add(usermanagement);
        JLabel uname = new JLabel("username:\t");
        uname.setBounds(20, 180, 260, 30);
        JTextField unametxt = new JTextField("");
        unametxt.setBounds(120, 180, 260, 30);
        JLabel upass = new JLabel("password\t");
        upass.setBounds(20, 240, 260, 30);
        JTextField upasstxt = new JTextField("");
        upasstxt.setBounds(120, 240, 260, 30);
        JButton stbtn = new JButton("click here to Change ");
        stbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String passcode = JOptionPane.showInputDialog(window, "Enter original password to change ");
                String input = "UPDATE user SET username =\'" + unametxt.getText() + "\' ,password =\'"
                        + upass.getText() + "\' WHERE password = \'" + passcode + "\' ;";
                try {
                    con = DriverManager.getConnection(url);
                    con.setAutoCommit(false);
                    PreparedStatement pstmt = con.prepareStatement(input);
                    int a = pstmt.executeUpdate();
                    pstmt.close();
                    int b = JOptionPane.showConfirmDialog(window, "Do u want to change user details", "confirm ", 1);
                    if (b == 1) {

                        con.commit();
                        con.close();
                    } else {
                        // System.exit(0);
                    }
                    JOptionPane.showMessageDialog(window, "successfully updated:\t" + a);
                } catch (SQLException a) {
                    JOptionPane.showMessageDialog(window, "Not updated:\t" + a.getMessage());
                }
            }
        });
        stbtn.setBounds(120, 340, 460, 30);
        usermanagementbtnback.setBounds(340, 720, 120, 54);
        usermanagementbtnback.addActionListener(actions);
        usermanagement.add(uname);
        usermanagement.add(unametxt);
        usermanagement.add(upass);
        usermanagement.add(upasstxt);
        usermanagement.add(stbtn);
        usermanagement.add(usermanagementbtnback);
        usermanagement.add(lbl);
    }

    void studentmanagement() {
        studentmanagement.setBackground(new Color(122, 0, 180));
        studentmanagement.setForeground(Color.BLACK);
        studentmanagement.setBounds(360, 0, 1720, 820);
        studentmanagement.setLayout(null);
        window.add(studentmanagement);
        studentmanagementbtn = new JButton[5];
        studentmanagementbtn[0] = new JButton("Edit student Details");
        studentmanagementbtn[1] = new JButton("view student Details");
        studentmanagementbtn[2] = new JButton("Export student Details");
        studentmanagementbtn[3] = new JButton("Share student Details");
        studentmanagementbtn[4] = new JButton("View Student Reports");
        int b = 120;
        for (int i = 0; i < 5; i++) {
            studentmanagement.add(studentmanagementbtn[i]);
            b += 50;
            studentmanagementbtn[i].setBounds(30, b, 260, 30);
            studentmanagementbtn[i].setForeground(Color.white);
            studentmanagementbtn[i].setBackground(Color.BLACK);
            studentmanagementbtn[i].setFont(new Font("Adobe Garamond Pro Bold", Font.ITALIC, 15));
            studentmanagementbtn[i].addActionListener(actions);
        }
        studentmanagement.add(lbl);
    }

    void connection(int input) {
        try {
            con = DriverManager.getConnection(url);
            if (input == 1) {
                String username = JOptionPane.showInputDialog(window, "Enter username");
                String password = JOptionPane.showInputDialog(window, "Enter Password");
                String sql = "SELECT  username ,password FROM user where username=\'" + username + "\'and password =\'"
                        + password + "\';";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    loginkey = true;
                    JOptionPane.showMessageDialog(window, "User name and password  matched ");
                } else {
                    JOptionPane.showMessageDialog(window, "Error ");
                    loginkey = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            int key = JOptionPane.showConfirmDialog(window, "wrong input ");
            if (key == 1) {
                connection(1);
            } else {
                System.exit(0);
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    void editstudentdetails() {
        editstudentdetails.setBackground(Color.magenta);
        editstudentdetails.setForeground(Color.BLACK);
        editstudentdetails.setBounds(360, 0, 1720, 820);
        editstudentdetails.setLayout(null);
        editstudentdetails.setVisible(true);
        window.add(editstudentdetails);
        String name = null;
        String hallticket = null;
        String branch = null;
        String address = null;
        String gpa = null;
        JLabel sname = new JLabel("Student name");
        JTextArea snametxt = new JTextArea(name);
        JLabel shallticket = new JLabel("student hallticket");
        JTextArea halltickettxt = new JTextArea(hallticket);
        JLabel sbranch = new JLabel("student branch");
        JTextArea sbranchtxt = new JTextArea(branch);
        JLabel simage = new JLabel("student Image");
        JLabel saddress = new JLabel("student address ");
        JTextArea saddresstxt = new JTextArea(address);
        JLabel sgpa = new JLabel("student GPA");
        JTextArea sgpatxt = new JTextArea(gpa);
        JButton createstudentbtn = new JButton("create new student");// working
        createstudentbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql = "insert into student (name,hallticket,branch,address,gpa)values(\"" + snametxt.getText()
                        + "\",\"" + halltickettxt.getText() + "\",\"" + sbranchtxt.getText() + "\",\""
                        + saddresstxt.getText() + "\"," + sgpatxt.getText() + ");";
                try {
                    con = DriverManager.getConnection(url);
                    con.setAutoCommit(false);
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    int a = pstmt.executeUpdate();
                    pstmt.close();
                    con.commit();
                    con.close();
                    JOptionPane.showMessageDialog(window, "successfully created student:\t" + a);
                } catch (SQLException a) {
                    JOptionPane.showMessageDialog(window,
                            "Not updated:\t" + snametxt.getText() + "\t" + a.getMessage());
                }
            }
        });
        JButton deletestudentbtn = new JButton("Delete student");// working
        deletestudentbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String hall = JOptionPane.showInputDialog(window, "Enter the Hallticket Number to delete");
                try {
                    String sql = "DELETE FROM student WHERE hallticket =\'" + hall + "\';";
                    con = DriverManager.getConnection(url);
                    con.setAutoCommit(false);
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    int a = pstmt.executeUpdate();
                    pstmt.close();
                    con.commit();
                    con.close();
                    JOptionPane.showMessageDialog(window, "successfully Deleted student:\t" + a);

                } catch (SQLException alpha) {
                    JOptionPane.showMessageDialog(window, alpha.getMessage());
                }

            }
        });
        JButton updatebtn = new JButton("Update student");
        updatebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String in = JOptionPane.showInputDialog(window, "Enter Hallticket");

                String sql = "Update student  SET name=\'" + snametxt.getText() + "\',hallticket =\'"
                        + halltickettxt.getText() + "\',branch=\' " + sbranchtxt.getText() + "\',address =\'"
                        + saddresstxt.getText() + "\',gpa = " + sgpatxt.getText() + " where hallticket =\'" + in
                        + "\';";
                try {
                    con = DriverManager.getConnection(url);
                    con.setAutoCommit(false);
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    int check = pstmt.executeUpdate();
                    pstmt.close();
                    con.commit();
                    con.close();
                    JOptionPane.showMessageDialog(window, "successfully Updated student:\t" + check);
                } catch (SQLException a) {
                    JOptionPane.showMessageDialog(window,
                            "Not updated:\t" + snametxt.getText() + "\t" + a.getMessage());
                }
            }
        });
        editstudentbackbtn.addActionListener(actions);
        sname.setBounds(20, 140, 240, 34);
        snametxt.setBounds(300, 140, 240, 25);
        shallticket.setBounds(30, 180, 240, 34);
        halltickettxt.setBounds(300, 180, 240, 25);
        sbranch.setBounds(20, 220, 240, 34);
        sbranchtxt.setBounds(300, 220, 240, 25);
        simage.setBounds(760, 260, 240, 34);
        saddress.setBounds(20, 300, 240, 34);
        saddresstxt.setBounds(300, 300, 240, 25);
        sgpa.setBounds(20, 340, 240, 34);
        sgpatxt.setBounds(300, 340, 240, 25);
        createstudentbtn.setBounds(20, 420, 240, 30);
        deletestudentbtn.setBounds(360, 420, 240, 34);
        updatebtn.setBounds(680, 420, 240, 34);
        editstudentbackbtn.setBounds(480, 32, 250, 35);

        editstudentdetails.add(sname);
        editstudentdetails.add(snametxt);
        editstudentdetails.add(halltickettxt);
        editstudentdetails.add(sbranchtxt);
        editstudentdetails.add(saddresstxt);
        editstudentdetails.add(sgpatxt);
        editstudentdetails.add(shallticket);
        editstudentdetails.add(sbranch);
        editstudentdetails.add(simage);
        editstudentdetails.add(saddress);
        editstudentdetails.add(sgpa);
        editstudentdetails.add(createstudentbtn);
        editstudentdetails.add(deletestudentbtn);
        editstudentdetails.add(updatebtn);
        editstudentdetails.add(lbl);
    }

    void viewstudentdetails() {

    }

    void exportstudentdetails() {

    }

    void sharestudentdetails() {

    }

    void viewstudentreports() {

    }

    private ActionListener actions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btn[0]) {
                connection(1);
                if (loginkey == true) {
                    btn[0].setVisible(false);
                    body.setVisible(false);
                    btn[5].setVisible(true);
                    management();
                } else {
                    int i = JOptionPane.showInternalConfirmDialog(null, "ERROR try again or Exit the Window");
                    if (i == 1) {

                    } else {

                    }
                }
            } else if (e.getSource() == managementbtn[0]) {
                management.setVisible(false);
                usermanagement();
            } else if (e.getSource() == managementbtn[1]) {
                management.setVisible(false);
                studentmanagement();
            } else if (e.getSource() == managementbtn[2]) {
                management.setVisible(false);
                reports();
            } else if (e.getSource() == managementbtn[3]) {
                management.setVisible(false);
                export();
            } else if (e.getSource() == studentmanagementbtn[0]) {
                studentmanagement.setVisible(false);
                editstudentdetails();
            } else if (e.getSource() == studentmanagementbtn[1]) {

            } else if (e.getSource() == studentmanagementbtn[2]) {

            } else if (e.getSource() == studentmanagementbtn[3]) {

            } else if (e.getSource() == studentmanagementbtn[4]) {

            } else if (e.getSource() == editstudentbackbtn) {
                editstudentdetails.setVisible(false);
                studentmanagement.setVisible(true);
                studentmanagement();
            } else if (e.getSource() == btn[3]) {
                editstudentdetails.setVisible(false);
                studentmanagement.setVisible(false);
                usermanagement.setVisible(false);
                idcards.setVisible(false);
                export.setVisible(false);
                management.setVisible(true);
                management();
            } else if (e.getSource() == btn[4] || e.getSource() == managementbtn[4]) {
                int check = JOptionPane.showConfirmDialog(window, "Do u want to close the window", "confirm", 1);
                if (check == 1) {
                    System.exit(0);
                } else if (e.getSource() == usermanagementbtnback) {
                    usermanagement.setVisible(false);
                    management.setVisible(false);
                    usermanagement();
                }
            }
        }
    };

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}