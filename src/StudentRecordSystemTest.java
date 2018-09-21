import static org.junit.Assert.*;

import org.junit.Test;

public class StudentRecordSystemTest {

	@Test
	public void addStudentRecordTest() {
		StudentRecordSystem studentRecordSystem = StudentRecordSystem.initializeInstance(3);
		studentRecordSystem.addStudentRecord("802113679", "Pedro", StudentRecordSystem.Gender.MALE, 3.00);
		StudentRecordSystem.StudentRecord[] studentRecords = studentRecordSystem.getStudentRecords();
		assertEquals("Student record ID is incorrect.", "802113679", studentRecords[0].getID());
		assertEquals("Student record name is incorrect.", "Pedro", studentRecords[0].getName());
		assertEquals("Student record gender is incorrect.", StudentRecordSystem.Gender.MALE, studentRecords[0].getGender());
		assertEquals("Student record GPA is incorrect.",  3.00, studentRecords[0].getGPA(), 1e-10);
		studentRecordSystem.addStudentRecord("802174579", "Juan", StudentRecordSystem.Gender.MALE, 3.40);
		assertEquals("The totalStudentRecords variable is not being updated.", 2, studentRecordSystem.getTotalStudentRecords());
		studentRecordSystem.addStudentRecord("802122423", "Julia", StudentRecordSystem.Gender.FEMALE, 3.35);
		studentRecordSystem.addStudentRecord("802113349", "Eliezer", StudentRecordSystem.Gender.MALE, 3.90);
		assertEquals("The size of student records is not twice the size.", 6, studentRecordSystem.getStudentRecords().length);
	}
	
	@Test
	public void recordsToStringTest() {
		String[] expectedStrings = new String[3];
		expectedStrings[0] = "ID: 802174579, Name: Juan, Gender: M, GPA: 3.40";
		expectedStrings[1] = "ID: 802122423, Name: Julia, Gender: F, GPA: 3.35";
		expectedStrings[2] = "ID: 802113349, Name: Eliezer, Gender: M, GPA: 3.90";
		StudentRecordSystem studentRecordSystem = StudentRecordSystem.initializeInstance(3);
		String[] actualStrings = studentRecordSystem.recordsToString();
		assertEquals("The size of array of Strings doesn't have the right length.", 0, actualStrings.length);
		studentRecordSystem.addStudentRecord("802174579", "Juan", StudentRecordSystem.Gender.MALE, 3.40);
		studentRecordSystem.addStudentRecord("802122423", "Julia", StudentRecordSystem.Gender.FEMALE, 3.35);
		studentRecordSystem.addStudentRecord("802113349", "Eliezer", StudentRecordSystem.Gender.MALE, 3.90);
		actualStrings = studentRecordSystem.recordsToString();
		assertEquals("The size of array of Strings doesn't have the right length.", 3, actualStrings.length);
		assertEquals("Student Juan has incorrect record.", expectedStrings[0], actualStrings[0]);
		assertEquals("Student Julia has incorrect record.", expectedStrings[1], actualStrings[1]);
		assertEquals("Student Eliezer has incorrect record.", expectedStrings[2], actualStrings[2]);
	}

	@Test
	public void searchStudentRecordTest() {
		StudentRecordSystem studentRecordSystem = StudentRecordSystem.initializeInstance(3);
		studentRecordSystem.addStudentRecord("802122423", "Julia", StudentRecordSystem.Gender.FEMALE, 3.35);
		studentRecordSystem.addStudentRecord("802113679", "Pedro", StudentRecordSystem.Gender.MALE, 3.00); 
		studentRecordSystem.addStudentRecord("802113349", "Eliezer", StudentRecordSystem.Gender.MALE, 3.90);
		assertNull(studentRecordSystem.searchStudentRecord("000000000"));
		StudentRecordSystem.StudentRecord studentRecord = studentRecordSystem.searchStudentRecord("802113679");
		assertEquals("Student record ID is incorrect.", "802113679", studentRecord.getID());
		assertEquals("Student record name is incorrect.", "Pedro", studentRecord.getName());
		assertEquals("Student record gender is incorrect.", StudentRecordSystem.Gender.MALE, studentRecord.getGender());
		assertEquals("Student record GPA is incorrect.",  3.00, studentRecord.getGPA(), 1e-10);
	}
	
	@Test
	public void averageStudentGPATest() {
		StudentRecordSystem studentRecordSystem = StudentRecordSystem.initializeInstance(3);
		assertEquals("Student record GPA is incorrect.", 0.0, studentRecordSystem.averageStudentGPA(), 1E-10);
		studentRecordSystem.addStudentRecord("802122423", "Julia", StudentRecordSystem.Gender.FEMALE, 4.00);
		studentRecordSystem.addStudentRecord("802113679", "Pedro", StudentRecordSystem.Gender.MALE, 3.00); 
		studentRecordSystem.addStudentRecord("802113349", "Eliezer", StudentRecordSystem.Gender.MALE, 2.00);
		studentRecordSystem.addStudentRecord("802131831", "Zhoe", StudentRecordSystem.Gender.FEMALE, 1.00);
		assertEquals("Student record GPA is incorrect.", 2.5, studentRecordSystem.averageStudentGPA(), 1E-10);
	}
	
	@Test
	public void countStudentsByGenderTest() {
		StudentRecordSystem studentRecordSystem = StudentRecordSystem.initializeInstance(3);
		int[] empty = {0, 0};
		int[] threeStudents = {2, 1};
		assertEquals("Student gender count is incorrect.", empty[0], studentRecordSystem.countStudentsByGender()[0]);
		assertEquals("Student gender count is incorrect.", empty[1], studentRecordSystem.countStudentsByGender()[1]);
		studentRecordSystem.addStudentRecord("802122423", "Julia", StudentRecordSystem.Gender.FEMALE, 3.04);
		studentRecordSystem.addStudentRecord("802113679", "Pedro", StudentRecordSystem.Gender.MALE, 3.32); 
		studentRecordSystem.addStudentRecord("802113349", "Eliezer", StudentRecordSystem.Gender.MALE, 3.87);
		assertEquals("Student gender count is incorrect.", threeStudents[0], studentRecordSystem.countStudentsByGender()[0]);
		assertEquals("Student gender count is incorrect.", threeStudents[1], studentRecordSystem.countStudentsByGender()[1]);
	}
}
