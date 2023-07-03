package main.java;

public class StudentRecordSystem {

	private static StudentRecordSystem studentRecordSystem;
	private StudentRecord[] studentRecords;
	private int totalStudentRecords;	// Actual number of student records currently in the system
	
	static {
		studentRecordSystem = new StudentRecordSystem();
	}
	
	private StudentRecordSystem() {}
	
	public static StudentRecordSystem initializeInstance(int maxNumberOfStudentRecords){
		studentRecordSystem.studentRecords = new StudentRecord[maxNumberOfStudentRecords];
		studentRecordSystem.totalStudentRecords = 0;
        return studentRecordSystem;
    }
	
	// Getters
	public StudentRecord[] getStudentRecords() {
		return studentRecords;
	}
	
	public int getTotalStudentRecords() {
		return totalStudentRecords;
	}
	
	/*
	 * IMPLEMENT USING AN ENHANCED FOR LOOP.
	 * 
	 * Adds a new student record with the given parameters.
	 * 
	 * HINT: Create a new array twice the size of the original array if the totalStudentRecords
	 * exceeds the capacity of it.
	 */
	public void addStudentRecord(String id, String name, Gender gender, double gpa) {
		// YOUR CODE GOES HERE.
		if(totalStudentRecords + 1 >= studentRecords.length) {
			StudentRecord[] newList = new StudentRecord[studentRecords.length * 2];
			int i = 0;
			for(StudentRecord studentRecord : studentRecords) {
				newList[i] = studentRecord;
				i++;
			}
			studentRecords = newList;
		}
		StudentRecord newStudent =  new StudentRecord(id, name, gender, gpa);
		studentRecords[totalStudentRecords++] = newStudent;
	}
	
	/* IMPLEMENT USING A REGULAR FOR LOOP.
	 * 
	 * Returns an array of Strings where each entry represents a student record.
	 * 
	 * HINT: Use the toString method in the Student Record inner class.
	 */
	public String[] recordsToString() {
		// YOUR CODE GOES HERE.
		String[] stringRecords = new String[totalStudentRecords];
		for(int i = 0; i < totalStudentRecords; i++) {
			stringRecords[i] = studentRecords[i].toString();
		}
		return stringRecords;
	}
	
	/* IMPLEMENT USING A WHILE LOOP.
	 * 
	 * Searches the studentRecords with the given ID and returns their record.
	 * It returns NULL if no record is found
	 * 
	 * HINT: Remember that totalStudentRecords represent the actual number of
	 * student records.
	 */
	public StudentRecord searchStudentRecord(String id) {
		// YOUR CODE GOES HERE.
		int i = 0;
		while(i < totalStudentRecords) {
			if(studentRecords[i].getID().equals(id)) {
				return studentRecords[i];
			}
			i++;
		}
		return null;
	}
	
	/* IMPLEMENT WITH ANY LOOP.
	 * 
	 * Returns the average of the GPA's in student record system.
	 */
	public double averageStudentGPA() {
		// YOUR CODE GOES HERE.
		if(totalStudentRecords <= 0) { return 0; }
		double sum = 0.0;
		for(int i = 0; i < totalStudentRecords; i++) {
			sum += studentRecords[i].getGPA();
		}
		return sum / totalStudentRecords;
	}

	/* IMPLEMENT WITH ANY LOOP.
	 * 
	 * Returns a integer array with two elements where the first position represents
	 * the number of males in the system and the second element represents the number of
	 * females.
	 */
	public int[] countStudentsByGender() {
		// YOUR CODE GOES HERE.
		int[] genderCount = {0, 0};
		for(int i = 0; i < totalStudentRecords; i++) {
			if(studentRecords[i].getGender() == Gender.MALE) { genderCount[0]++; }
			else { genderCount[1]++; }
		}	
		return genderCount;
	}
	
	/* IMPLEMENT WITH NESTED LOOPS.
	 * 
	 * Returns true if two instances of StudentRecord have the same name, false otherwise.
	 * HINT: Use the Equals method.
	 */
	public boolean repeatedStudentNames() {
		// YOUR CODE GOES HERE.
		for(int i = 0; i < totalStudentRecords; i++) {
			for(int j = i+1; j < totalStudentRecords; j++) {
				if(studentRecords[i].getName().equals(studentRecords[j].getName())) {
					return true;
				}
			}
		}
		return false;
	}


	/*
	 * Enum of type Gender representing male or female.
	 */
	public enum Gender {
		MALE('M'), FEMALE('F');
		private final char letter;
		Gender(char letter) { this.letter = letter; }
		public char genderLetter() { return letter; }
	}
	
	/*
	 * Inner class representing student records in the student record system.
	 */
	public static class StudentRecord {
		
		private String id;
		private String name;
		private Gender gender;
		private double gpa;
		
		public StudentRecord(String id, String name, Gender gender, double gpa) {
			this.id = id;
			this.name = name;
			this.gender = gender;
			this.gpa = gpa;
		}

		public String toString() {
			return String.format("ID: " + id + ", Name: " + name + ", Gender: " + gender.genderLetter() + ", GPA: %.2f", gpa);
		}
		
		// Getters
		public String getID() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public Gender getGender() {
			return gender;
		}
		
		public double getGPA() {
			return gpa;
		}
	}
}
