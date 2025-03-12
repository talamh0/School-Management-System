import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminOperationWindow implements ActionListener {
    DriverClass driverClass;

    JButton refresh = new JButton("refresh");
    JButton changePassword = new JButton("change password");
    JButton logout = new JButton("logout");
    JButton subjects = new JButton("subjects");



    JButton DoneAdmin = new JButton("insert");
    JButton DoneTeacher = new JButton("insert");
    JButton DoneStudent = new JButton("insert");
    JButton DoneSubject = new JButton("insert");
    JButton DoneClass = new JButton("insert");


    JTextField[] adminFields = new JTextField[6];
    JTextField[] teacherFields = new JTextField[7];
    JTextField[] studentFields = new JTextField[5];
    JTextField[] subjectFields = new JTextField[1];
    JTextField[] classFields = new JTextField[1];


    JTextField[] seachedTeacherFeilds = new JTextField[8];
    String[] seachedTeacherFeildsValues=new String[8];

    JTextField[] searchStudentFeilds = new JTextField[8];
    String[] seachedStudentFeildsValues=new String[8];


    JPanel insertsPanel = new JPanel();
    JPanel viewTeacherPanel = new JPanel();
    JScrollPane teacherTableScroll = new JScrollPane();


    JPanel viewStudentPanel = new JPanel();

    JScrollPane studentTableScroll=new JScrollPane();

    JTable teacher;
    JTable student;



    JButton searchByIDTeacher = new JButton("search by ID");
    JTextField searchByIDTeacherFeild = new JTextField();

    JButton deleteByIDTeacher = new JButton("delete by ID");
    JTextField deleteByIDTeacherFeild = new JTextField();





    JButton searchByIDStudent = new JButton("search by ID");
    JTextField searchByIDStudentFeild = new JTextField();

    JButton deleteByIDStudent = new JButton("delete by ID");
    JTextField deleteByIDStudentFeild = new JTextField();

    JButton saveTeacher=new JButton("save");
    JButton saveStudent=new JButton("save");

    JFrame frame;

    public AdminOperationWindow(DriverClass driverClass) {
        this.driverClass = driverClass;
        frame = new JFrame("Admin");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel(driverClass.userName);
        label.setBounds(20, 15, 150, 35);
        label.setFont(new Font("", Font.ITALIC, 30));
        frame.add(label);


        JPanel navBar = new JPanel();
        navBar.setBounds(10, 25, 840, 50);
        navBar.add(refresh);
        navBar.add(logout);
        navBar.add(changePassword);
        navBar.add(subjects);


        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        navBar.setLayout(layout);
        frame.add(navBar);


        refresh.addActionListener(this);
        logout.addActionListener(this);
        changePassword.addActionListener(this);
        subjects.addActionListener(this);

        inserts();
        viewteachers();
        viewstudents();
        frame.setSize(1400, 840);

        frame.setVisible(true);
    }

    public void inserts() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEADING);
        insertsPanel.setBounds(20, 50, 1380, 250);

        layout.setHgap(60);
        layout.setVgap(20);
        insertsPanel.setLayout(layout);
        Box admin = new Box(BoxLayout.Y_AXIS);
        adminFields = new JTextField[7];
        JLabel insertAdmin = new JLabel("insert Admin");
        insertAdmin.setFont(new Font("", Font.BOLD, 20));
        admin.add(insertAdmin);

        String[] adminLabels = {"First Name", "Last Name", "Id", "Address", "Phone number", "Salary"};
        Box[] adminBoxes = new Box[6];
        for (int i = 0; i < 6; i++) {
            adminBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(adminLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 25);
            adminBoxes[i].add(label);
            adminFields[i] = new JTextField();
            adminFields[i].setPreferredSize(new Dimension(10, 25));
            adminBoxes[i].add(adminFields[i]);
            admin.add(adminBoxes[i]);
        }

        admin.add(DoneAdmin);

        Box teacher = new Box(BoxLayout.Y_AXIS);
        teacherFields = new JTextField[7];
        JLabel insertTeacher = new JLabel("insert teacher");
        insertTeacher.setFont(new Font("", Font.BOLD, 20));
        teacher.add(insertTeacher);

        String[] teacherLabels = {"First Name", "Last Name", "TId", "Address", "Phone number", "Major", "Salary"};
        Box[] teacherBoxes = new Box[7];
        for (int i = 0; i < 7; i++) {
            teacherBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(teacherLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 25);
            teacherBoxes[i].add(label);
            teacherFields[i] = new JTextField();
            teacherFields[i].setPreferredSize(new Dimension(10, 25));
            teacherBoxes[i].add(teacherFields[i]);
            teacher.add(teacherBoxes[i]);
        }
        teacher.add(DoneTeacher);


        Box student = new Box(BoxLayout.Y_AXIS);


        studentFields = new JTextField[7];
        JLabel insertStudent = new JLabel("insert student");
        insertStudent.setFont(new Font("", Font.BOLD, 20));
        student.add(insertStudent);

        String[] studentLabels = {"First Name", "Last Name", "SId", "Address", "Birthdate"};
        Box[] studentBoxes = new Box[5];
        for (int i = 0; i < 5; i++) {
            studentBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(studentLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 30);
            studentBoxes[i].add(label);
            studentFields[i] = new JTextField();
            studentFields[i].setPreferredSize(new Dimension(10, 25));
            studentBoxes[i].add(studentFields[i]);
            student.add(studentBoxes[i]);
        }
        studentFields[4].setText("YYYY-MM-DD");


        student.add(DoneStudent);


        Box subject = new Box(BoxLayout.Y_AXIS);
        subjectFields = new JTextField[1];
        JLabel insertSubject = new JLabel("insert subject");
        insertSubject.setFont(new Font("", Font.BOLD, 20));
        subject.add(insertSubject);

        String[] subjectLabels = {"subject name"};
        Box[] subjectBoxes = new Box[1];
        for (int i = 0; i < 1; i++) {
            subjectBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(subjectLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 25);
            subjectBoxes[i].add(label);
            subjectFields[i] = new JTextField();
            subjectFields[i].setPreferredSize(new Dimension(10, 25));
            subjectBoxes[i].add(subjectFields[i]);
            subject.add(subjectBoxes[i]);
        }

        subject.add(DoneSubject);


        Box classes = new Box(BoxLayout.Y_AXIS);
        classFields = new JTextField[2];
        JLabel insertClass = new JLabel("insert class");
        insertClass.setFont(new Font("", Font.BOLD, 20));
        classes.add(insertClass);

        String[] classLabels = {"Level", "Letter"};
        Box[] classBoxes = new Box[2];
        for (int i = 0; i < 2; i++) {
            classBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(classLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 25);
            classBoxes[i].add(label);
            classFields[i] = new JTextField();
            classFields[i].setPreferredSize(new Dimension(10, 25));
            classBoxes[i].add(classFields[i]);
            classes.add(classBoxes[i]);
        }

        classes.add(DoneClass);


        insertsPanel.add(admin);
        insertsPanel.add(teacher);
        insertsPanel.add(student);
        insertsPanel.add(subject);
        insertsPanel.add(classes);


        DoneAdmin.addActionListener(this);
        DoneTeacher.addActionListener(this);
        DoneStudent.addActionListener(this);
        DoneSubject.addActionListener(this);
        DoneClass.addActionListener(this);

        frame.add(insertsPanel);

    }

    public void viewteachers() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEADING);
        viewTeacherPanel.setBounds(20, 320, 1380, 240);
        viewTeacherPanel.setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(40);

        JLabel teacherLabel=new JLabel("teachers");
        viewTeacherPanel.add(teacherLabel);
        Box controlButtons=new Box(BoxLayout.Y_AXIS);

        Box searchBox=new Box(BoxLayout.X_AXIS);
        searchByIDTeacher.setPreferredSize(new Dimension(105, 25));
        searchByIDTeacher.setSize(100, 25);
        searchByIDTeacher.addActionListener(this);
        searchByIDTeacherFeild.setPreferredSize(new Dimension(105, 25));
        searchByIDTeacherFeild.setSize(new Dimension(105, 25));

        Box deleteBox = new Box(BoxLayout.X_AXIS);
        deleteByIDTeacher.setPreferredSize(new Dimension(105, 25));
        deleteByIDTeacher.setSize(100, 25);
        deleteByIDTeacher.addActionListener(this);
        deleteByIDTeacherFeild.setPreferredSize(new Dimension(105, 25));
        deleteByIDTeacherFeild.setSize(new Dimension(105, 25));


        searchBox.add(searchByIDTeacher);
        searchBox.add(searchByIDTeacherFeild);
        deleteBox.add(deleteByIDTeacher);
        deleteBox.add(deleteByIDTeacherFeild);

        saveTeacher.addActionListener(this);
        controlButtons.add(searchBox);
        controlButtons.add(deleteBox);


        createTeacherTable();
        teacher.setOpaque(true);
        teacherTableScroll = new JScrollPane(teacher);
        teacherTableScroll.setPreferredSize(new Dimension(600,160));

        teacherTableScroll.createVerticalScrollBar();


        viewTeacherPanel.add(teacherTableScroll);

        viewTeacherPanel.add(controlButtons);

        Box searchTeacher=new Box(BoxLayout.Y_AXIS);
        String[] searchTeacherLabels = {"First Name", "Last Name", "Id", "Address", "Phone number","Major","adm_id", "Salary"};
        Box[] searchTeacherBoxes = new Box[8];
        for (int i = 0; i < 8; i++) {
            searchTeacherBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(searchTeacherLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 25);
            searchTeacherBoxes[i].add(label);
            seachedTeacherFeilds[i] = new JTextField();
            seachedTeacherFeilds[i].setText("");
            seachedTeacherFeilds[i].setPreferredSize(new Dimension(100, 25));

            searchTeacherBoxes[i].add(seachedTeacherFeilds[i]);
            searchTeacher.add(searchTeacherBoxes[i]);
        }

        viewTeacherPanel.add(searchTeacher);
        viewTeacherPanel.add(saveTeacher);
        frame.add(viewTeacherPanel);
    }

    public void createTeacherTable() {
        String[][] teachers = driverClass.retrieveTeachers();
        String[] headers = {"first name", "last name", "TId", "address", "phoneNum", "Major", "adm_ID", "salary"};
        teacher = new JTable(teachers, headers);
    }








    public void viewstudents() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEADING);
        viewStudentPanel.setBounds(20, 580, 1380, 240);
        viewStudentPanel.setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(40);

        JLabel studentsLabel=new JLabel("students");
        viewStudentPanel.add(studentsLabel);

        Box controlBox=new Box(BoxLayout.Y_AXIS);
        Box searchBox=new Box(BoxLayout.X_AXIS);
        searchByIDStudent.setPreferredSize(new Dimension(105, 25));
        searchByIDStudent.setSize(100, 25);
        searchByIDStudent.addActionListener(this);
        searchByIDStudentFeild.setPreferredSize(new Dimension(105, 25));
        searchByIDStudentFeild.setSize(new Dimension(105, 25));

        searchBox.add(searchByIDStudent);
        searchBox.add(searchByIDStudentFeild);



        Box deleteBox=new Box(BoxLayout.X_AXIS);
        deleteByIDStudent.setPreferredSize(new Dimension(105, 25));
        deleteByIDStudent.setSize(100, 25);
        deleteByIDStudent.addActionListener(this);
        deleteByIDStudentFeild.setPreferredSize(new Dimension(105, 25));
        deleteByIDStudentFeild.setSize(new Dimension(105, 25));
        deleteBox.add(deleteByIDStudent);
        deleteBox.add(deleteByIDStudentFeild);





       controlBox.add(searchBox);
       controlBox.add(deleteBox);

        createStudentTable();
        student.setOpaque(true);
        studentTableScroll = new JScrollPane(student);
        studentTableScroll.setPreferredSize(new Dimension(600,160));
        studentTableScroll.createVerticalScrollBar();


        viewStudentPanel.add(studentTableScroll);

        viewStudentPanel.add(controlBox);

        Box searchStudent=new Box(BoxLayout.Y_AXIS);
        String[] searchStudentLabels = {"First Name", "Last Name", "Id", "Address", "Birthdate","adm_id", "letter","level"};
        Box[] searchStudentBoxes = new Box[8];
        for (int i = 0; i < 8; i++) {
            searchStudentBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(searchStudentLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 25);
            searchStudentBoxes[i].add(label);
            searchStudentFeilds[i] = new JTextField();
            searchStudentFeilds[i].setText("");
            searchStudentFeilds[i].setPreferredSize(new Dimension(100, 25));

            searchStudentBoxes[i].add(searchStudentFeilds[i]);
            searchStudent.add(searchStudentBoxes[i]);
        }

        saveStudent.addActionListener(this);
        viewStudentPanel.add(searchStudent);
        viewStudentPanel.add(saveStudent);
        frame.add(viewStudentPanel);
    }

    public void createStudentTable() {
        String[][] students = driverClass.retrieveStudents();
        String[] headers = {"First Name", "Last Name", "Id", "Address", "Birthdate","adm_id", "letter","levelr"};
        student = new JTable(students, headers);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            frame.dispose();
            new AdminOperationWindow(driverClass);
        }

        else if (e.getSource() == changePassword) {
            driverClass.changePassword();
        }

        else if (e.getSource() == logout) {
            driverClass.logout();
            new LoginWindow(driverClass);
            frame.dispose();
        }
        else if(e.getSource()==subjects){
            new AdminSubjectsWindow(driverClass);
            frame.dispose();
        }

        else if (e.getSource() == DoneTeacher) {
            if(driverClass.insertTeacher(teacherFields)){
                JOptionPane.showMessageDialog(null,"insertion successed");
            }
            else JOptionPane.showMessageDialog(null,"insertion failed");
        }

        else if (e.getSource() == DoneSubject) {

            if(driverClass.insertSubject(subjectFields)){
                JOptionPane.showMessageDialog(null,"insertion successed");
            }
            else JOptionPane.showMessageDialog(null,"insertion failed");

        }

        else if (e.getSource() == DoneAdmin) {
            if( driverClass.insertAdmin(adminFields)){
                JOptionPane.showMessageDialog(null,"insertion successed");
            }
            else JOptionPane.showMessageDialog(null,"insertion failed");

        }

        else if (e.getSource() == DoneStudent) {
            if(driverClass.insertStudent(studentFields)){
                JOptionPane.showMessageDialog(null,"insertion successed");
            }
            else JOptionPane.showMessageDialog(null,"insertion failed");

        }

        else if (e.getSource() == DoneClass) {
            if(driverClass.insertClass(classFields)){
                JOptionPane.showMessageDialog(null,"insertion successed");
            }
            else JOptionPane.showMessageDialog(null,"insertion failed");

        }

        else if(e.getSource()==searchByIDTeacher){
           String[][] feilds=driverClass.retrieveTeachers(Integer.parseInt(searchByIDTeacherFeild.getText()));
           try {
               for (int i = 0; i < 8; i++) {
                   seachedTeacherFeilds[i].setText(feilds[0][i]);
                   seachedTeacherFeildsValues[i]=feilds[0][i];
               }
           }
           catch (Exception exp){
               JOptionPane.showMessageDialog(null,"unavailavle ID");
           }
        }

        else if(e.getSource()==searchByIDStudent){
            String[][] feilds=driverClass.retrieveStudents(Integer.parseInt(searchByIDStudentFeild.getText()));
            try {
                for (int i = 0; i < 8; i++) {
                    searchStudentFeilds[i].setText(feilds[0][i]);
                    if(feilds[0][i]==null)
                        seachedStudentFeildsValues[i]="";
                    else seachedStudentFeildsValues[i]=feilds[0][i];
                }
            }
            catch (Exception exp){
                System.out.println(exp);
                JOptionPane.showMessageDialog(null,"unavailavle ID");
            }
        }

        else if(e.getSource()==saveTeacher){
            if(seachedTeacherFeildsValues[2]==null) return;
            String[] headers = {"Fname", "Lname", "TId", "address", "phoneNum", "Major", "adm_ID", "salary"};
            if(!seachedTeacherFeildsValues[2].equals(seachedTeacherFeilds[2].getText())){
                JOptionPane.showMessageDialog(null,"you can't change the TID");
            }
            else {
                boolean b=true;
                for (int i = 0; i < 8; i++) {
                    if (!seachedTeacherFeildsValues[i].equals(seachedTeacherFeilds[i].getText())) {
                        b=driverClass.updateTeacher(headers[i], Integer.parseInt(seachedTeacherFeildsValues[2]),seachedTeacherFeilds[i].getText());
                        if(!b){
                            JOptionPane.showMessageDialog(null,"not all updates successed");
                            break;
                        }
                    }
                }
                if(b){
                    JOptionPane.showMessageDialog(null,"all updates successed");
                }
            }
        }


        else if(e.getSource()==saveStudent){
            if(seachedStudentFeildsValues[2]==null) return;
            String[] headers = {"Fname", "Lname", "SId", "address", "Birthdate", "adm_ID", "letter","level"};
            if(!seachedStudentFeildsValues[2].equals(searchStudentFeilds[2].getText())){
                JOptionPane.showMessageDialog(null,"you can't change the SID");
            }
            else {
                boolean b=true;
                for (int i = 0; i < 8; i++) {
                    if (!seachedStudentFeildsValues[i].equals(searchStudentFeilds[i].getText())) {
                        b=driverClass.updateStudent(headers[i], Integer.parseInt(seachedStudentFeildsValues[2]),searchStudentFeilds[i].getText());
                        if(!b){
                            JOptionPane.showMessageDialog(null,"not all updates successed");
                            break;
                        }
                    }
                }
                if(b){
                    JOptionPane.showMessageDialog(null,"all updates successed");
                }
            }
        }

        else if (e.getSource()==deleteByIDTeacher){
            int b=-2;
            String message="";
            try {
                 b=driverClass.deleteTeacher(Integer.parseInt(deleteByIDTeacherFeild.getText()));

            }catch (Exception exception){
                message="enter the TID first";
            }
            if(b==1) message="delete succesfully";
            else if(b==0) message="delete failed, wrong ID";
            else if(b==-1) message="delete failed, this teacher has subjects";
            JOptionPane.showMessageDialog(null,message);
        }

        else if (e.getSource()==deleteByIDStudent){
            int b;
            String message="";
            try {
                b=driverClass.deleteStudent(Integer.parseInt(deleteByIDStudentFeild.getText()));
            }catch (Exception exp){
                message="enter the SID first";
                b=-1;
            }

            if(b==1) message="delete succesfully";
            else if(b==0) message="delete failed, wrong ID";
            JOptionPane.showMessageDialog(null,message);
        }


    }
}
