import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminSubjectsWindow implements ActionListener {
    DriverClass driverClass;
    JFrame frame;
    StudentsInSubject subjectFrame;

    JButton refresh = new JButton("refresh");
    JButton changePassword = new JButton("change password");
    JButton logout = new JButton("logout");
    JButton tables=new JButton("tables");

    public AdminSubjectsWindow(DriverClass driverClass){
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
        navBar.add(tables);


        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        navBar.setLayout(layout);
        frame.add(navBar);


        refresh.addActionListener(this);
        logout.addActionListener(this);
        changePassword.addActionListener(this);
        tables.addActionListener(this);

        viewSubjects();

        frame.setSize(970, 340);
        frame.setVisible(true);
    }


    JPanel viewSubjectPanel=new JPanel();
    JButton searchByNameSubject=new JButton("search");
    JTextField searchByNameSubjectFeild=new JTextField();

    JButton deleteByNameSubject=new JButton("delete");
    JTextField deleteByNameSubjectFeild=new JTextField();

    JButton saveSubject=new JButton("save");

    JTable subject;
    JScrollPane subjectTableScroll=new JScrollPane();




    JTextField[] searchSubjectFeilds=new JTextField[2];
    String[] searchSubjectFeildsValues=new String[2];
    public void viewSubjects(){
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEADING);
        viewSubjectPanel.setBounds(20, 120, 940, 240);
        viewSubjectPanel.setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(40);

        JLabel teacherLabel=new JLabel("subjects");
        viewSubjectPanel.add(teacherLabel);
        Box controlButtons=new Box(BoxLayout.Y_AXIS);

        Box searchBox=new Box(BoxLayout.X_AXIS);
        searchByNameSubject.setPreferredSize(new Dimension(105, 25));
        searchByNameSubject.setSize(100, 25);

        searchByNameSubjectFeild.setPreferredSize(new Dimension(105, 25));
        searchByNameSubjectFeild.setSize(new Dimension(105, 25));

        Box deleteBox = new Box(BoxLayout.X_AXIS);
        deleteByNameSubject.setPreferredSize(new Dimension(105, 25));
        deleteByNameSubject.setSize(100, 25);

        deleteByNameSubjectFeild.setPreferredSize(new Dimension(105, 25));
        deleteByNameSubjectFeild.setSize(new Dimension(105, 25));


        searchBox.add(searchByNameSubject);
        searchBox.add(searchByNameSubjectFeild);
        deleteBox.add(deleteByNameSubject);
        deleteBox.add(deleteByNameSubjectFeild);

        searchByNameSubject.addActionListener(this);
        deleteByNameSubject.addActionListener(this);
        saveSubject.addActionListener(this);
        controlButtons.add(searchBox);
        controlButtons.add(deleteBox);


        createSubjectTable();
        subject.setEnabled(false);
        subject.setOpaque(true);
        subjectTableScroll = new JScrollPane(subject);
        subjectTableScroll.setPreferredSize(new Dimension(200,160));

        subjectTableScroll.createVerticalScrollBar();


        viewSubjectPanel.add(subjectTableScroll);




        viewSubjectPanel.add(controlButtons);

        Box searchSubject=new Box(BoxLayout.Y_AXIS);
        String[] searchSubjectLabels = {"subject Name", "TID"};
        Box[] searchSubjectBoxes = new Box[8];
        for (int i = 0; i < 2; i++) {
            searchSubjectBoxes[i] = new Box(BoxLayout.X_AXIS);
            JLabel label = new JLabel(searchSubjectLabels[i]);
            label.setPreferredSize(new Dimension(100, 25));
            label.setSize(100, 25);
            searchSubjectBoxes[i].add(label);
            searchSubjectFeilds[i] = new JTextField();
            searchSubjectFeilds[i].setText("");
            searchSubjectFeilds[i].setPreferredSize(new Dimension(100, 25));

            searchSubjectBoxes[i].add(searchSubjectFeilds[i]);
            searchSubject.add(searchSubjectBoxes[i]);
        }

        viewSubjectPanel.add(searchSubject);

        viewSubjectPanel.add(saveSubject);
        frame.add(viewSubjectPanel);
    }

    public void createSubjectTable() {
        String[][] teachers = driverClass.retrieveSubjects();
        String[] headers = {"subject name", "TId"};
        subject = new JTable(teachers, headers);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            frame.dispose();
            new AdminSubjectsWindow(driverClass);
        }

        else if (e.getSource() == changePassword) {
            driverClass.changePassword();
        }

        else if (e.getSource() == logout) {
            driverClass.logout();
            new LoginWindow(driverClass);
            frame.dispose();
        }
        else if(e.getSource()==tables){
            new AdminOperationWindow(driverClass);
            frame.dispose();
        }

        else if (e.getSource()==searchByNameSubject){

            String[][] feilds=driverClass.retrieveSubject(searchByNameSubjectFeild.getText());
            try {
                for (int i = 0; i < 2; i++) {
                    searchSubjectFeilds[i].setText(feilds[0][i]);
                    if(feilds[0][i]==null)
                        searchSubjectFeildsValues[i]="";
                    else searchSubjectFeildsValues[i]=feilds[0][i];
                }
            }
            catch (Exception exp){
                System.out.println(exp);
                JOptionPane.showMessageDialog(null,"unavailavle subject");
                return;
            }

           if(subjectFrame!=null) {
               subjectFrame.frame.dispose();
               subjectFrame.frame=null;
           }
           subjectFrame=new StudentsInSubject(driverClass,searchByNameSubjectFeild.getText());
        }
        else if (e.getSource()==deleteByNameSubject){
            int b=driverClass.deleteSubject(deleteByNameSubjectFeild.getText());
            if(b==1) JOptionPane.showMessageDialog(null,"delete successfully");
            else JOptionPane.showMessageDialog(null,"delete failed");
        }

        else if (e.getSource()==saveSubject){
            if(searchSubjectFeildsValues[0]==null) return;
            String[] headers = {"name_subject", "TId"};
            if(!searchSubjectFeildsValues[0].equals(searchSubjectFeilds[0].getText())){
                JOptionPane.showMessageDialog(null,"you can't change the subject name");
            }
            else {
                boolean b=true;
                for (int i = 0; i < 2; i++) {
                    if (!searchSubjectFeildsValues[i].equals(searchSubjectFeilds[i].getText())) {
                        b=driverClass.updateSubject(Integer.parseInt(searchSubjectFeilds[1].getText()),searchSubjectFeildsValues[0]);
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

    }
}
