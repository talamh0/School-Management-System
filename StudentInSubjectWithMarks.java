import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInSubjectWithMarks implements ActionListener {

    DriverClass driverClass;
    JFrame frame;
    String subjectName;

    JTable students;
    JScrollPane studentTableScroll = new JScrollPane();

    JPanel viewStudentPanel = new JPanel();

    JButton save=new JButton("save");

    StudentInSubjectWithMarks(DriverClass driverClass, String subjectName) {
        this.driverClass = driverClass;
        this.subjectName = subjectName;
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
        students.setOpaque(true);
        studentTableScroll = new JScrollPane(students);
        studentTableScroll.setPreferredSize(new Dimension(400, 160));

        studentTableScroll.createVerticalScrollBar();

        viewStudentPanel.add(studentTableScroll);

        viewStudentPanel.add(save);

        frame.add(viewStudentPanel);


        save.addActionListener(this);
        frame.setLocation(400, 400);
        frame.setVisible(true);
    }

    public void createStudentTable() {
        String[][] studentsTable = driverClass.retrieveStudentsInSubjectsWithMarks(subjectName);
        String[] headers = {"SID", "first Name", "last Name","midterm","final mark"};
        students = new JTable(studentsTable, headers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save){
            String[][] marks=new String[students.getRowCount()][3];
            for(int i=0;i<marks.length;i++){
                marks[i][0]= (String) students.getModel().getValueAt(i,0);
                marks[i][1]= (String) students.getModel().getValueAt(i,3);
                marks[i][2]= (String) students.getModel().getValueAt(i,4);
            }
            driverClass.updateMarks(marks,subjectName);
        }
    }
}
