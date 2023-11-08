import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}

class StudentManagementGUI {
    private StudentManagementSystem system;
    private JFrame frame;

    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField gradeField;
    private JTextArea resultArea;

    public StudentManagementGUI(StudentManagementSystem system) {
        this.system = system;

        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        nameField = new JTextField(20);
        rollNumberField = new JTextField(20);
        gradeField = new JTextField(20);
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Roll Number:"));
        frame.add(rollNumberField);
        frame.add(new JLabel("Grade:"));
        frame.add(gradeField);
        frame.add(addButton);
        frame.add(searchButton);
        frame.add(displayButton);
        frame.add(new JScrollPane(resultArea));

        frame.pack();
        frame.setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        String rollNumber = rollNumberField.getText();
        String grade = gradeField.getText();

        if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) {
            Student student = new Student(name, rollNumber, grade);
            system.addStudent(student);
            clearFields();
            showMessage("Student added successfully.");
        } else {
            showMessage("Please fill in all fields.");
        }
    }

    private void searchStudent() {
        String rollNumber = rollNumberField.getText();
        if (!rollNumber.isEmpty()) {
            Student student = system.searchStudent(rollNumber);
            if (student != null) {
                showMessage("Student found: " + student);
            } else {
                showMessage("Student not found.");
            }
        } else {
            showMessage("Please enter a roll number for searching.");
        }
    }

    private void displayAllStudents() {
        ArrayList<Student> students = system.getAllStudents();
        if (!students.isEmpty()) {
            resultArea.setText("");
            for (Student student : students) {
                resultArea.append(student.toString() + "\n");
            }
        } else {
            showMessage("No students to display.");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }
}

public class Task5_Main {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        StudentManagementGUI gui = new StudentManagementGUI(system);
    }
}

