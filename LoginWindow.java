import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow implements ActionListener {
    DriverClass driverClass;

    JButton login=new JButton("login");
    JFrame frame;
    JTextField userID;
    JTextField password;

    JComboBox role;

    public LoginWindow(DriverClass driverClass){
        this.driverClass = driverClass;
        frame=new JFrame("school");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label=new JLabel("Login in");
        label.setBounds(160,25,150,35);
        label.setFont(new Font("",Font.ITALIC,30));
        frame.add(label);
        //first name
        JLabel userNameLabel=new JLabel("user ID");
        userNameLabel.setBounds(100,100,100,25);
        frame.add(userNameLabel);


        userID=new JTextField();
        userID.setBounds(170,100,200,25);
        frame.add(userID);


        //password
        JLabel passwordLabel=new JLabel("password");
        passwordLabel.setBounds(100,150,100,25);
        frame.add(passwordLabel);


        password=new JTextField();
        password.setBounds(170,150,200,25);
        frame.add(password);


        //password
        JLabel roleLabel=new JLabel("password");
        roleLabel.setBounds(100,200,100,25);
        frame.add(roleLabel);


        role=new JComboBox();
        JLabel admin=new JLabel("admin");

        JLabel teacher=new JLabel("teacher");
        JLabel student=new JLabel("student");

        role.addItem(admin.getText());
        role.addItem(teacher.getText());
        role.addItem(student.getText());

        role.setBounds(170,200,200,25);
        frame.add(role);



        login.addActionListener(this);
        login.setBounds(170,250,100,25);
        frame.add(login);

        frame.setSize(500,400);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            String userID=this.userID.getText();
            String pass=password.getText();

            if(driverClass.login(userID,pass)) {
                if (role.getSelectedItem().equals("admin")) {
                    new AdminOperationWindow(driverClass);
                    frame.dispose();
                }

                else if (role.getSelectedItem().equals("teacher")) {
                    new TeacherOperationWindow(driverClass,Integer.parseInt(userID));
                    frame.dispose();
                }
                else if (role.getSelectedItem().equals("student")) {
                    new StudentOperationWindow(driverClass,Integer.parseInt(userID));
                    frame.dispose();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"wrong ID or password");
            }
        }
    }
}
