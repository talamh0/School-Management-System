import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DriverClass {
    Connection connection;
    int userID;
    String userName;
    DriverClass(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public boolean login(String userID,String password){
        String url="jdbc:mysql://localhost:3306/school";
        try {
            connection= DriverManager.getConnection(url,userID,password);
            this.userID=Integer.parseInt(userID);
        } catch (Exception throwables) {
            System.out.println(throwables);
            return false;
        }
        return true;
    }

    public void logout(){
        try {
            connection.close();
            userID=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public boolean insertTeacher(JTextField[] data){
        Statement statement;
        try {
            statement = connection.createStatement();
            String query="insert into teacher(FName,LName,Tid,Address," +
                    "PhoneNum,Major,adm_id,salary) values (";
            query+="\""+data[0].getText()+"\",";
            query+="\""+data[1].getText()+"\",";
            query+=data[2].getText()+",";
            query+="\""+data[3].getText()+"\",";
            query+="\""+data[4].getText()+"\",";
            query+="\""+data[5].getText()+"\",";
            query+=userID+",";
            query+=data[6].getText()+")";

            statement.execute(query);

            statement=connection.createStatement();
            String createQuery="create user "+'\''+data[2].getText()+'\''+"@localhost identified by "+'\''+data[2].getText()+'\'';

            statement.execute(createQuery);
            statement.execute("grant teacher to "+'\''+data[2].getText()+'\''+"@localhost");
            statement.execute("SET DEFAULT ROLE 'teacher' to "+'\''+data[2].getText()+'\''+"@localhost");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean insertAdmin(JTextField[] data){
        Statement statement;
        try {
            statement = connection.createStatement();
            String query="insert into Admin(FName,LName,id,Address," +
                    "PhoneNum,salary) values (";
            query+="\""+data[0].getText()+"\",";
            query+="\""+data[1].getText()+"\",";
            query+=data[2].getText()+",";
            query+="\""+data[3].getText()+"\",";
            query+="\""+data[4].getText()+"\",";
            query+=data[5].getText()+")";

            statement.execute(query);

            statement=connection.createStatement();
            String createQuery="create user "+'\''+data[2].getText()+'\''+"@localhost identified by "+'\''+data[2].getText()+'\'';
            statement.execute(createQuery);
            statement.execute("grant admin to "+'\''+data[2].getText()+'\''+"@localhost");
            statement.execute("SET DEFAULT ROLE 'admin' to "+'\''+data[2].getText()+'\''+"@localhost");

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean insertStudent(JTextField[] data){
        Statement statement;
        try {
            statement = connection.createStatement();
            String query="insert into student(FName,LName,Sid,Address," +
                    "Birthdate,adm_id) values (";
            query+="\""+data[0].getText()+"\",";
            query+="\""+data[1].getText()+"\",";
            query+=data[2].getText()+",";
            query+="\""+data[3].getText()+"\",";
            query+="\'"+data[4].getText()+"',";
            query+=userID+")";

            statement.execute(query);

            String createQuery="create user "+'\''+data[2].getText()+'\''+"@localhost identified by "+'\''+data[2].getText()+'\'';
            statement.execute(createQuery);
            statement.execute("grant student to "+'\''+data[2].getText()+'\''+"@localhost");
            statement.execute("SET DEFAULT ROLE 'student' to "+'\''+data[2].getText()+'\''+"@localhost");

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean insertSubject(JTextField[] data){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query="insert into Subject(name_subject) values (";
            query+="\""+data[0].getText()+"\")";

            statement.execute(query);

            return true;
        } catch (SQLException throwables) {
            return false;
        }

    }

    public boolean insertClass(JTextField[] data){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query="insert into Class(Level,Letter) values (";
            query+=data[0].getText()+",";
            query+="\""+data[1].getText()+"\")";
            statement.execute(query);

            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public String[][] retrieveTeachers(){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from teacher");
            ArrayList<ArrayList<String>> teachers=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(resultSet.getString(2));
                row.add(String.valueOf(resultSet.getInt(3)));
                row.add(resultSet.getString(4));
                row.add(resultSet.getString(5));
                row.add(resultSet.getString(6));
                row.add(String.valueOf(resultSet.getInt(7)));
                row.add(String.valueOf(resultSet.getInt(8)));

                teachers.add(row);
            }

            String [][] table=new String[teachers.size()][8];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=teachers.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {}

        return null;
    }

    public String[][] retrieveTeachers(int TID){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from teacher where TID="+String.valueOf(TID));
            ArrayList<ArrayList<String>> teachers=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(resultSet.getString(2));
                row.add(String.valueOf(resultSet.getInt(3)));
                row.add(resultSet.getString(4));
                row.add(resultSet.getString(5));
                row.add(resultSet.getString(6));
                row.add(String.valueOf(resultSet.getInt(7)));
                row.add(String.valueOf(resultSet.getInt(8)));

                teachers.add(row);
            }

            String [][] table=new String[teachers.size()][8];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=teachers.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String[][] retrieveSubjects(int ID,String role){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet;
            if(role.equals("teacher"))resultSet=statement.executeQuery("select name_subject from subject where TID = "+ID);
            else resultSet=statement.executeQuery("select name_subject from studys where SID = "+ID);
            ArrayList<ArrayList<String>> teachers=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                teachers.add(row);
            }

            String [][] table=new String[teachers.size()][1];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=teachers.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String[][] retrieveSubjects(){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from subject");
            ArrayList<ArrayList<String>> teachers=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(String.valueOf(resultSet.getInt(2)));
                teachers.add(row);
            }

            String [][] table=new String[teachers.size()][2];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=teachers.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String[][] retrieveSubject(String subjectName){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from subject where name_subject='"+subjectName+"'");
            ArrayList<ArrayList<String>> teachers=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(String.valueOf(resultSet.getInt(2)));
                teachers.add(row);
            }

            String [][] table=new String[teachers.size()][2];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=teachers.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String[][] retrieveStudentsInSubjects(String subjectName){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select student.SID,Fname,Lname from studys,student where name_subject='"+subjectName+"'"
            +" and student.SID=studys.SID");

            ArrayList<ArrayList<String>> students=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(String.valueOf(resultSet.getInt(1)));
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));

                students.add(row);
            }

            String [][] table=new String[students.size()][3];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=students.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    public String[][] retrieveStudentsSubject(int SID){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select name_subject,midtermMark,finalMark from studys where SID = "+SID);

            ArrayList<ArrayList<String>> students=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(String.valueOf(resultSet.getInt(2)));
                row.add(String.valueOf(resultSet.getInt(3)));
                students.add(row);
            }

            String [][] table=new String[students.size()][3];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=students.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String[][] retrieveStudentsInSubjects(String subjectName,int SID){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select student.SID,Fname,Lname,midtermMark,finalMark from studys,student,subject where subject.name_subject='"+subjectName+"'"
                    +" and student.SID=studys.SID and subject.name_subject=studys.name_subject and student.SID = "+SID);

            ArrayList<ArrayList<String>> students=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(String.valueOf(resultSet.getInt(1)));
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                row.add(String.valueOf(resultSet.getInt(4)));
                row.add(String.valueOf(resultSet.getInt(5)));


                students.add(row);
            }

            String [][] table=new String[students.size()][5];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=students.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public String[][] retrieveStudentsInSubjectsWithMarks(String subjectName){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select student.SID,Fname,Lname,midtermMark,finalMark from studys,student,subject where subject.name_subject='"+subjectName+"'"
                    +" and student.SID=studys.SID and subject.name_subject=studys.name_subject and subject.TID = "+userID);

            ArrayList<ArrayList<String>> students=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(String.valueOf(resultSet.getInt(1)));
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                row.add(String.valueOf(resultSet.getInt(4)));
                row.add(String.valueOf(resultSet.getInt(5)));


                students.add(row);
            }

            String [][] table=new String[students.size()][5];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=students.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean updateTeacher(String feild,int TID,String newValue){
        try {
            Statement statement=connection.createStatement();
            String query;
            if(feild.equals("adm_id")||feild.equals("salary"))
            query="update teacher set "+feild+" = "+newValue+" where TID = "+TID;
            else{
                query="update teacher set "+feild+" = '"+newValue+"' where TID = "+TID;
            }


            statement.execute(query);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    public String[][] retrieveStudents(){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from student");
            ArrayList<ArrayList<String>> teachers=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(resultSet.getString(2));
                row.add(String.valueOf(resultSet.getInt(3)));
                row.add(resultSet.getString(4));
                row.add(resultSet.getString(5));
                row.add(resultSet.getString(6));
                row.add(resultSet.getString(7));
                row.add(String.valueOf(resultSet.getInt(8)));

                teachers.add(row);
            }

            String [][] table=new String[teachers.size()][8];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=teachers.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    public String[][] retrieveStudents(int SID){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from student where sid="+String.valueOf(SID));
            ArrayList<ArrayList<String>> teachers=new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> row=new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(resultSet.getString(2));
                row.add(String.valueOf(resultSet.getInt(3)));
                row.add(resultSet.getString(4));
                row.add(resultSet.getString(5));
                row.add(resultSet.getString(6));
                row.add(resultSet.getString(7));
                row.add(String.valueOf(resultSet.getInt(8)));

                teachers.add(row);
            }

            String [][] table=new String[teachers.size()][8];
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[i].length;j++){
                    table[i][j]=teachers.get(i).get(j);
                }
            }

            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public boolean updateStudent(String feild,int SID,String newValue){
        try {
            Statement statement=connection.createStatement();
            String query;
            if(feild.equals("adm_id")||feild.equals("level"))
                query="update student set "+feild+" = "+newValue+" where SID = "+SID;
            else{
                query="update student set "+feild+" = '"+newValue+"' where SID = "+SID;
            }

            statement.execute(query);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean updateSubject(int TID,String name){
        try {
            Statement statement=connection.createStatement();
            String query="update subject set TID = "+TID+" where name_subject = '"+name+"'";

            statement.execute(query);
            return true;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

    public void changePassword(){
        try {
            Statement statement=connection.createStatement();
            String newPassword=JOptionPane.showInputDialog("enter the new password");
            if(newPassword==null || newPassword.equals("")) return;
            statement.execute("alter user User() identified by "+"'"+newPassword+"'");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public int deleteTeacher(int TID){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from teacher where TID = "+TID);
            if(resultSet.next()) {
                statement.execute("delete from teacher where TID = " + TID);
                statement.execute("drop user '"+TID+"'@localhost");
                return 1;
            }
            else return 0;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteStudent(int SID){
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from student where SID = "+SID);
            if(resultSet.next()) {
                statement = connection.createStatement();
                statement.execute("delete from studys where SID = " + SID);
                statement.execute("delete from student where SID = " + SID);
                statement.execute("drop user '" + SID + "'@localhost");
                return 1;
            }
            else return 0;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int deleteSubject(String subjectName){
        try {
            Statement statement=connection.createStatement();
            statement.execute("delete from studys where name_subject = '"+subjectName+"'");
            statement.execute("delete from subject where name_subject = '"+subjectName+"'");
            return 1;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            return -1;
        }
    }



    public boolean updateMarks(String [][] marks,String subjectName){
        try {
            Statement statement=connection.createStatement();
            for(int i=0;i<marks.length;i++){
                statement.execute("update studys set midtermMark = "+marks[i][1]+" where SID="+marks[i][0]
                        +" and name_subject = '"+subjectName+"'");
                statement.execute("update studys set finalMark = "+marks[i][2]+" where SID="+marks[i][0]
                        +" and name_subject = '"+subjectName+"'");
            }

            return true;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            throwables.printStackTrace();
        }
        return false;
    }


    public boolean updateTeacher(String [] teacher){
        try {

            Statement statement=connection.createStatement();
            for(int i=0;i<teacher.length;i++){
                statement.execute("update teacher set FName = '"+teacher[0]+"' where TID="+teacher[2]);
                statement.execute("update teacher set LName = '"+teacher[1]+"' where TID="+teacher[2]);
                statement.execute("update teacher set address = '"+teacher[3]+"' where TID="+teacher[2]);
                statement.execute("update teacher set phoneNum = '"+teacher[4]+"' where TID="+teacher[2]);
            }

            return true;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            throwables.printStackTrace();
        }
        return false;
    }



    public boolean updateStudent(String[] student) {
        try {
            Statement statement=connection.createStatement();
            for(int i=0;i<student.length;i++){
                statement.execute("update student set FName = '"+student[0]+"' where SID="+student[2]);
                statement.execute("update student set LName = '"+student[1]+"' where SID="+student[2]);
                statement.execute("update student set address = '"+student[3]+"' where SID="+student[2]);
                statement.execute("update student set birthdate = '"+student[4]+"' where SID="+student[2]);
            }
            return true;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean insertSubjectToStudent(String subjectName){
        try {
            Statement statement=connection.createStatement();
            statement.execute("insert into studys(name_subject,SID) values ('"+subjectName+"',"+userID+" )");
            return true;
        } catch (Exception throwables) {

            return false;
        }
    }

    public boolean deleteSubjectFromStudent(String subjectName){
        try {
            Statement statement=connection.createStatement();
            statement.execute("delete from studys where name_subject='"+subjectName+"' and SID="+userID);
            return true;
        } catch (Exception throwables) {
            System.out.println(throwables);
            return false;
        }
    }
}
