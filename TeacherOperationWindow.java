import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherOperationWindow implements ActionListener {
    JFrame frame;
    int TID;

    JButton refresh = new JButton("refresh");
    JButton changePassword = new JButton("change password");
    JButton logout = new JButton("logout");


    DriverClass driverClass;

    JTable profile;
    JScrollPane profileScroll;

    JTable subject;
    JScrollPane subjectsScroll;

    JButton searchByNameSubject=new JButton("search");
    JTextField searchByNameSubjectFeild=new JTextField();

    JPanel panel=new JPanel();

    JButton save=new JButton("save");
     StudentInSubjectWithMarks subjectFrame;

    TeacherOperationWindow(DriverClass driverClass,int TID){
        this.driverClass=driverClass;
        this.TID=TID;

        this.driverClass = driverClass;
        frame = new JFrame("User");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel navBar = new JPanel();
        navBar.setBounds(10, 25, 840, 50);

        navBar.add(refresh);
        navBar.add(logout);
        navBar.add(changePassword);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        navBar.setLayout(layout);
        frame.add(navBar);


        refresh.addActionListener(this);
        logout.addActionListener(this);
        changePassword.addActionListener(this);

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



        JLabel teacherLabel=new JLabel("subjects");
        panel.add(teacherLabel);
        Box controlButtons=new Box(BoxLayout.Y_AXIS);





        Box searchBox=new Box(BoxLayout.X_AXIS);
        searchByNameSubject.setPreferredSize(new Dimension(105, 25));
        searchByNameSubject.setSize(100, 25);

        searchByNameSubjectFeild.setPreferredSize(new Dimension(105, 25));
        searchByNameSubjectFeild.setSize(new Dimension(105, 25));



        searchBox.add(searchByNameSubject);
        searchBox.add(searchByNameSubjectFeild);


        searchByNameSubject.addActionListener(this);

        controlButtons.add(searchBox);


        createSubjectTable();
        subject.setEnabled(false);
        subject.setOpaque(true);
        subjectsScroll = new JScrollPane(subject);
        subjectsScroll.setPreferredSize(new Dimension(200,160));
        subjectsScroll.createVerticalScrollBar();
        panel.add(subjectsScroll);



        panel.add(controlButtons);

        save.addActionListener(this);


        frame.add(panel);
    }

    public void createSubjectTable() {
        String[][] teachers = driverClass.retrieveSubjects(TID,"teacher");
        String[] headers = {"subject name"};
        subject = new JTable(teachers, headers);
    }

    public void createProfile() {
        String[][] teacher = driverClass.retrieveTeachers(TID);
        String[] headers = {"first name", "last name", "TId", "address", "phoneNum", "Major", "adm_ID", "salary"};
        profile = new JTable(teacher, headers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            frame.dispose();
            new TeacherOperationWindow(driverClass,TID);
        }

        if(e.getSource()==searchByNameSubject){
            if(subjectFrame!=null) {
                subjectFrame.frame.dispose();
                subjectFrame.frame=null;
            }

            subjectFrame=new StudentInSubjectWithMarks(driverClass,searchByNameSubjectFeild.getText());
        }
        else if(e.getSource()==save){
            String[] prof=new String[5];
                prof[0]= (String) profile.getModel().getValueAt(0,0);
                prof[1]= (String) profile.getModel().getValueAt(0,1);
                prof[2]= (String) profile.getModel().getValueAt(0,2);
                prof[3]= (String) profile.getModel().getValueAt(0,3);
                prof[4]= (String) profile.getModel().getValueAt(0,4);
            driverClass.updateTeacher(prof);
        }
        else if (e.getSource() == changePassword) {
            driverClass.changePassword();
        }

        else if (e.getSource() == logout) {
            driverClass.logout();
            new LoginWindow(driverClass);
            frame.dispose();
        }
    }
}
