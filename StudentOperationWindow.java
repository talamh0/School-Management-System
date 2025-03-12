import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentOperationWindow implements ActionListener {
    JFrame frame;
    int SID;

    JButton refresh=new JButton("refresh");
    JButton changePassword = new JButton("change password");
    JButton logout = new JButton("logout");

    JButton addSubject = new JButton("add subject");
    JButton deleteSubject = new JButton("delete subject");



    DriverClass driverClass;

    JTable profile;
    JScrollPane profileScroll;

    JTable subject;
    JScrollPane subjectsScroll;

    JButton searchByNameSubject=new JButton("search");
    JTextField searchByNameSubjectFeild=new JTextField();

    JPanel panel=new JPanel();

    JButton save=new JButton("save");
    StudentsInSubjectForStudent subjectFrame;

    public StudentOperationWindow(DriverClass driverClass,int SID){
        this.driverClass=driverClass;
        this.SID=SID;

        this.driverClass = driverClass;
        frame = new JFrame("User");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel navBar = new JPanel();
        navBar.setBounds(10, 25, 840, 50);

        navBar.add(refresh);
        navBar.add(logout);
        navBar.add(changePassword);
        navBar.add(addSubject);
        navBar.add(deleteSubject);


        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        navBar.setLayout(layout);
        frame.add(navBar);



        refresh.addActionListener(this);
        logout.addActionListener(this);
        changePassword.addActionListener(this);
        addSubject.addActionListener(this);
        deleteSubject.addActionListener(this);


        display();

        frame.setSize(670, 340);
        frame.setVisible(true);
    }

    public void display(){
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEADING);
        panel.setBounds(20, 70, 670, 340);
        panel.setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(30);

        createProfile();
        profile.setOpaque(true);
        profileScroll = new JScrollPane(profile);
        profileScroll.setPreferredSize(new Dimension(500,40));

        profileScroll.createVerticalScrollBar();

        panel.add(profileScroll);
        panel.add(save);



        JLabel studentLabel=new JLabel("subjects");
        panel.add(studentLabel);



        createSubjectTable();
        subject.setEnabled(false);
        subject.setOpaque(true);
        subjectsScroll = new JScrollPane(subject);
        subjectsScroll.setPreferredSize(new Dimension(300,160));
        subjectsScroll.createVerticalScrollBar();
        panel.add(subjectsScroll);

        save.addActionListener(this);


        frame.add(panel);
    }

    public void createSubjectTable() {
        String[][] students = driverClass.retrieveStudentsSubject(SID);
        String[] headers = {"subject name","midtermMark","finalMark"};
        subject = new JTable(students, headers);
    }

    public void createProfile() {
        String[][] student = driverClass.retrieveStudents(SID);
        String[] headers = {"first name", "last name", "SId", "address", "birthdate", "adm_ID", "letter","level"};
        profile = new JTable(student, headers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            frame.dispose();
            new StudentOperationWindow(driverClass,SID);
        }


        if(e.getSource()==searchByNameSubject){

            if(subjectFrame!=null) {
                subjectFrame.frame.dispose();
                subjectFrame.frame=null;
            }

            subjectFrame=new StudentsInSubjectForStudent(driverClass,searchByNameSubjectFeild.getText());
        }
        else if(e.getSource()==save){
            String[] prof=new String[5];
            prof[0]= (String) profile.getModel().getValueAt(0,0);
            prof[1]= (String) profile.getModel().getValueAt(0,1);
            prof[2]= (String) profile.getModel().getValueAt(0,2);
            prof[3]= (String) profile.getModel().getValueAt(0,3);
            prof[4]= (String) profile.getModel().getValueAt(0,4);
            driverClass.updateStudent(prof);
        }
        else if (e.getSource() == changePassword) {
            driverClass.changePassword();
        }

        else if (e.getSource() == logout) {
            driverClass.logout();
            new LoginWindow(driverClass);
            frame.dispose();
        }

        else if(e.getSource()==addSubject){
            String subject=JOptionPane.showInputDialog(null,"enter subject name");
            boolean b=driverClass.insertSubjectToStudent(subject);
            if(b) JOptionPane.showMessageDialog(null,"insertion successed");
            else JOptionPane.showMessageDialog(null,"insertion failed, wrong name or you already have this subject");
        }

        else if(e.getSource()==deleteSubject){
            String subject=JOptionPane.showInputDialog(null,"enter subject name");
            boolean b=driverClass.deleteSubjectFromStudent(subject);
            if(b) JOptionPane.showMessageDialog(null,"deletion successed");
            else JOptionPane.showMessageDialog(null,"deletion failed, wrong name or you don't have this subject");
        }
    }
}
