import java.util.Scanner;

public class ShellMain {

	private static StudentRecord[] students;
	private static int totalStudents;
	
	public static void main(String[] args) {
		// Initialize global variables
		students = new StudentRecord[10];
		totalStudents = 0;
		
		// Main menu management
		System.out.println("\nWelcome to the Student Record System");
		Scanner stream = new Scanner(System.in);
		String input;
		
		// Infinite loop to keep the system running
		while(true) {
			do { // At least once request the user to give a number.
				System.out.println("\n Main Menu:");
				System.out.println("\t 1 - Add a student into the system.");
				System.out.println("\t 2 - Print all students in system.");
				System.out.println("\t 3 - Search Student by Student ID.");
				System.out.println("\t 4 - Calculate Student Statistics.");
				System.out.println("\t 5 - Calculate Grade Statistics.");
				System.out.println("\n\t 0 - Exit system.");
				System.out.print("\n Choose an action to execute from the list:");
				input = stream.nextLine();				
			} while(input == null | input.length() < 1 | input.length() > 1 | !Character.isDigit(input.charAt(0)));
			
			// Parse user option
			switch(input) {
				case "0": // Exit the system
					System.out.println("Goodbye");
					stream.close(); // Close stream before ending execution
					return;
				case "1": // Add a student to system
					
					// Request Student name
					System.out.println("\n Name of Student:");
					String name = stream.nextLine();
					
					// Request Student ID
					String sID;
					do {
						System.out.println("\nStudent ID (8 Numbers):");
						sID = stream.nextLine();
					}while(sID.length() != 8 | !sID.matches("[0-9]{8}"));
					
					// Request Student Sex
					String sexLetter;
					do {
						System.out.println("\nStudent's Sex (M/F):");
						sexLetter = stream.nextLine();
					}while(!sexLetter.equals("F") && !sexLetter.equals("M"));
					Sex sex;
					
					// Parse Sex Option
					if(sexLetter.equals("F")) { sex = Sex.FEMALE; } 
					else { sex = Sex.MALE; }
					
					// Request Student Grade Point Average
					float gpa = -1;
					do {
						System.out.println("\nStudent's GPA:");
						if(stream.hasNextDouble()) {
							gpa = (float) stream.nextDouble();
						}
						stream.nextLine();
					} while(gpa < 0);
					
					// Register student
					StudentRecord newSR = addStudent(name, sex, sID, gpa);
					System.out.println("Added " + newSR.toString() + " into system.");
					break;
				case "2": // Print All students registered
					String[] studentRecords = recordsToString(); // Get records in string
					System.out.println("\nRecords In System:");
					System.out.println("\tName\tSex\tStudentID\tGPA");
					for(String sr : studentRecords) {
						System.out.println("\t" + sr);
					}
					break;
				case "3": // Search a student by their ID
					
					// Request the Student ID
					String sID1 = "";
					do {
						System.out.println("\nStudent ID (8 Numbers):");
						sID1 = stream.nextLine();
					}while(sID1.length() != 8 | !sID1.matches("[0-9]{8}"));
					StudentRecord sr = searchStudent(sID1);
					
					// Print result
					if(sr != null) { System.out.println("Student found: " + sr.toString()); }
					else { System.out.println("No student found"); }
					break;
				case "4":
					int[] result = countStudentsBySex();
					System.out.println("Males: "+ result[0] + " Females: " + result[1]);
					break;
				case "5":
					float avggpa = averageStudentGPA();
					System.out.println("Average GPA: " + avggpa);
					break;
				default: 
					System.out.println("\nUnknown option try again.");
			}
			
		}
	}
	
	/**
	 * Creates an array of the records in form of a String
	 * @return
	 */
	private static String[] recordsToString() {
		String[] stringRecords = new String[totalStudents];
		for(int idx = 0; idx < totalStudents; idx++) {
			stringRecords[idx] = students[idx].toString();
		}
		return stringRecords;
	}

	/**
	 * Replaces the current student list with the given one. 
	 * It is assumed that the given list doesnt have nulls.
	 * @param studentList
	 */
	public static void setStudentRecords(StudentRecord[] studentList) {
		students = studentList;
		totalStudents = studentList.length;
	}
	
	/**
	 * Calculates the Average of the GPA's in system.
	 * @return
	 */
	private static float averageStudentGPA() {
		float avg = 0;
		for(int idx = 0; idx < totalStudents; idx++) {
			avg += students[idx].getStudentGPA();
		}
		avg /= totalStudents;
		return avg;
	}

	/**
	 * Counts the instances of male and female students.
	 * @return
	 */
	private static int[] countStudentsBySex() {
		int[] sexCount = {0, 0};
		for(int idx = 0; idx < totalStudents; idx++) {
			if(students[idx].getStudentSex() == Sex.MALE) { sexCount[0]++; }
			else { sexCount[1]++; }
		}	
		return sexCount;
	}

	/**
	 * Searches the students with the given ID and returns their record.
	 * @param sID
	 * @return
	 */
	private static StudentRecord searchStudent(String sID) {
		int idx = 0;
		while(idx < totalStudents && !sID.equals(students[idx].getStudentID())) {
			idx++;
		}
		if(idx < totalStudents) {
			return students[idx];
		}
		return null;
	}

	
	/**
	 * With the given parameters it creates and adds a new student record.
	 * @param name
	 * @param sex
	 * @param sID
	 * @param gpa
	 * @return
	 */
	private static StudentRecord addStudent(String name, Sex sex, String sID, float gpa) {
		if(totalStudents + 1 >= students.length) {
			StudentRecord[] newList = new StudentRecord[students.length*2];
			int idx = 0;
			for(StudentRecord st : students) {
				newList[idx] = st;
				idx++;
			}
			students = newList;
		}
		StudentRecord newStudent =  new StudentRecord(name, sex, sID, gpa);
		students[totalStudents++] = newStudent;
		return newStudent;
	}

	/**
	 * Sex Enum type for male and female
	 * @author alber
	 *
	 */
	public enum Sex {
		MALE('M'), FEMALE('F');
		private final char letter;
		Sex(char letter) { this.letter = letter; }
		public char sexLetter() { return letter; }
	}
	
	/**
	 * Student record.
	 * @author alber
	 *
	 */
	public static class StudentRecord {
		
		private String sName;
		private Sex sex;
		private String sID;
		private float gpa;
		
		public StudentRecord(String name, Sex sex, String sID, float gpa) {
			setStudentName(name);
			this.setStudentSex(sex);
			this.setStudentID(sID);
			this.setStudentGPA(gpa);
		}

		public String toString() {
			return sName + "\t" + sex + "\t" + sID + "\t" + gpa;
		}
		
		public String getStudentName() {
			return sName;
		}
		
		public Sex getStudentSex() {
			return sex;
		}
		
		public String getStudentID() {
			return sID;
		}
		
		public float getStudentGPA() {
			return gpa;
		}
		
		public void setStudentName(String sName) {
			this.sName = sName;
		}

		public void setStudentSex(Sex sex) {
			this.sex = sex;
		}

		public void setStudentID(String sID) {
			this.sID = sID;
		}

		public void setStudentGPA(float gpa) {
			this.gpa = gpa;
		}
	}
}
