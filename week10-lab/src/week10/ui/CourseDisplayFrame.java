package week10.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import week10.domain.Course;

public class CourseDisplayFrame extends JFrame {
    private ArrayList<Course> theCourses;
    private int position = -1; // Current position in the course list

    // JLabels and JTextFields
    private JLabel lblCode, lblName, lblEnrolled;
    private JTextField txtCode, txtCourseName, txtStudentCount;

    // Buttons
    private JButton btnPrevious, btnSave, btnNext;

    public CourseDisplayFrame(ArrayList<Course> courses) {
        super("Course Information Browser");
        this.theCourses = courses;
        initializeComponents();
        setupLayout();
        setupFrameDefaults();
        updateButtons();
    }

    private void initializeComponents() {
        lblCode = new JLabel("Code:");
        txtCode = new JTextField(10);
        lblName = new JLabel("Name:");
        txtCourseName = new JTextField(30);
        lblEnrolled = new JLabel("Enrolled:");
        txtStudentCount = new JTextField(3);

        btnPrevious = new JButton("Previous");
        btnSave = new JButton("Save");
        btnNext = new JButton("Next");

        // Create a single ButtonsResponder instance to handle all button actions
        ButtonsResponder responder = new ButtonsResponder();
        btnPrevious.addActionListener(responder);
        btnSave.addActionListener(responder);
        btnNext.addActionListener(responder);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(createInfoPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        pack();
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(createRowPanel(lblCode, txtCode));
        panel.add(createRowPanel(lblName, txtCourseName));
        panel.add(createRowPanel(lblEnrolled, txtStudentCount));
        return panel;
    }

    private JPanel createRowPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(btnPrevious);
        panel.add(btnSave);
        panel.add(btnNext);
        return panel;
    }

    private void setupFrameDefaults() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Inner class to handle button actions
    private class ButtonsResponder implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == btnNext) {
                moveNext();
            } else if (source == btnPrevious) {
                movePrevious();
            } else if (source == btnSave) {
                saveCurrentCourse();
            }
        }
    }
    private void movePrevious() {
        if (position > 0) {
            position--;
            updateCourseInfo();
            updateButtons();
        }
    }

    private void moveNext() {
        if (position < theCourses.size() - 1) {
            position++;
            updateCourseInfo();
            updateButtons();
        }
    }

    private void saveCurrentCourse() {
    	 String code = txtCode.getText().trim();
    	    String name = txtCourseName.getText().trim();
    	    String studentsText = txtStudentCount.getText().trim();

    	    // Early return if any field is empty
    	    if (code.isEmpty() || name.isEmpty() || studentsText.isEmpty()) {
    	        JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
    	        return;
    	    }

    	    try {
    	        int students = Integer.parseInt(studentsText);

    	        // Check if adding a new course or updating an existing one
    	        if (position == -1) { // No course is currently selected, add new
    	            Course newCourse = new Course(code, name, students);
    	            theCourses.add(newCourse);
    	            position = theCourses.size() - 1; // Set position to the new course
    	            JOptionPane.showMessageDialog(this, "New course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    	        } else { // Update existing course
    	            Course currentCourse = theCourses.get(position);
    	            currentCourse.setCourseCode(code);
    	            currentCourse.setCourseName(name);
    	            currentCourse.setNumStudents(students);
    	            JOptionPane.showMessageDialog(this, "Course updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    	        }
    	        updateButtons();
    	    } catch (NumberFormatException ex) {
    	        JOptionPane.showMessageDialog(this, "Number of students must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
    	    }
    	}

    private void updateCourseInfo() {
        if (position >= 0 && position < theCourses.size()) {
            Course currentCourse = theCourses.get(position);
            txtCode.setText(currentCourse.getCourseCode());
            txtCourseName.setText(currentCourse.getCourseName());
            txtStudentCount.setText(Integer.toString(currentCourse.getNumStudents()));
        }
    }
    private void updateButtons() {
        btnPrevious.setEnabled(position > 0);
        btnNext.setEnabled(position < theCourses.size() - 1);
        btnSave.setEnabled(true);
    }

    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("ITECH1103", "Big Data and Analytics", 102));
        courses.add(new Course("ITECH2003", "Web Design", 48));
        courses.add(new Course("ITECH2004", "Data Modelling", 73));
        courses.add(new Course("ITECH2306", "Agile Programming", 85));
        new CourseDisplayFrame(courses);
    }
} 