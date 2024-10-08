package edu.westga.cs1302.cms.view;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private TextField name;
	@FXML
	private TextField grade;
	@FXML
	private Label studentGradeLabel;
	@FXML
	private Label averageGradeLabel;
	@FXML
	private ListView<Student> students;

	@FXML
	void addStudent(ActionEvent event) {
		String studentName = this.name.getText();
		try {
			int grade = Integer.parseInt(this.grade.getText());
			Student student = new Student(studentName, grade);
			this.students.getItems().add(student);
			this.updateAverageGrade();
		} catch (NumberFormatException errorThing) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText(
					"Unable to create student: " + errorThing.getMessage() + ". Please reenter grade and try again.");
			errorPopup.showAndWait();
		} catch (IllegalArgumentException errorObject) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText(
					"Unable to create student: " + errorObject.getMessage() + ". Please reenter name and try again.");
			errorPopup.showAndWait();
		}
	}

	@FXML
	void removeStudent(ActionEvent event) {
		Student student = this.students.getSelectionModel().getSelectedItem();
		if (student != null) {
			this.students.getItems().remove(student);
			this.studentGradeLabel.setText("");
			this.updateAverageGrade();
		} else {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("No student selected. Unable to remove.");
			errorPopup.showAndWait();
		}
	}
	
    @FXML
    void onSelectedStudent(MouseEvent event) {
    	Student selectedStudent = this.students.getSelectionModel().getSelectedItem();
    	if (selectedStudent != null) {
    		this.studentGradeLabel.setText(String.valueOf(selectedStudent.getGrade()));
    	} else {
    		this.studentGradeLabel.setText("");
    	}
    }
    
    private void updateAverageGrade() {
    	if (this.students.getItems().isEmpty()) {
    		this.averageGradeLabel.setText("");
    		return;
    	}
    	
    	int totalGrades = 0;
    	for (Student student : this.students.getItems()) {
    		totalGrades  += student.getGrade();
    	}
    	
    	double average = (double) totalGrades / this.students.getItems().size();
    	this.averageGradeLabel.setText(String.format("%.2f", average));
    }

	@FXML
	void initialize() {
		assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.studentGradeLabel != null : "fx:id=\"studentGradeLabel\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.averageGradeLabel != null : "fx:id=\"averageGradeLabel\" was not injected: check your FXML file 'MainWindow.fxml'.";
	}

}
