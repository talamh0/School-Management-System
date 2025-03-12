import javax.swing.*;
import java.awt.*;

public class StudentsInSubject {

    DriverClass driverClass;
    JFrame frame;
    String subjectName;

    JTable students;
    JScrollPane studentTableScroll=new JScrollPane();

    JPanel viewStudentPanel=new JPanel();
    StudentsInSubject(DriverClass driverClass,String subjectName){
        this.driverClass=driverClass;
        this.subjectName=subjectName;
        frame = new JFrame(subjectName);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        frame.setSize(480, 240);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEADING);
        viewStudentPanel.setBounds(0, 0, 480, 240);
        viewStudentPanel.setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(40);

        createStudentTable();
        students.setEnabled(false);
        students.setOpaque(true);
        studentTableScroll = new JScrollPane(students);
        studentTableScroll.setPreferredSize(new Dimension(200,160));

        studentTableScroll.createVerticalScrollBar();

        viewStudentPanel.add(studentTableScroll);


        frame.add(viewStudentPanel);
        frame.setLocation(400,400);
        frame.setVisible(true);
    }

    public void createStudentTable() {
        String[][] studentsTable = driverClass.retrieveStudentsInSubjects(subjectName);
        String[] headers = {"SID", "first Name","last Name"};
        students = new JTable(studentsTable, headers);
    }
}
