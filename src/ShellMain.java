import java.util.Scanner;

public class ShellMain {

	private static StudentRecord[] students;
	private static int totalStudents;
	
	public static void main(String[] args) {
		students = new StudentRecord[10];
		totalStudents = 0;
		System.out.println("\nWelcome to the Student Record System");
		Scanner stream = new Scanner(System.in);
		String input;
		while(true) {
			do {
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
			
			switch(input) {
				case "0":
					System.out.println("Goodbye");
					stream.close();
					return;
				case "1":
					System.out.println("\n Name of Student:");
					String name = stream.nextLine();
					String sID;
					do {
						System.out.println("\nStudent ID (8 Numbers):");
						sID = stream.nextLine();
					}while(sID.length() != 8 | !sID.matches("[0-9]{8}"));
					String sexLetter;
					do {
						System.out.println("\nStudent's Sex (M/F):");
						sexLetter = stream.nextLine();
					}while(!sexLetter.equals("F") && !sexLetter.equals("M"));
					Sex sex;
					if(sexLetter.equals("F")) { sex = Sex.FEMALE; } 
					else { sex = Sex.MALE; }
					float gpa = -1;
					do {
						System.out.println("\nStudent's GPA:");
						if(stream.hasNextDouble()) {
							gpa = (float) stream.nextDouble();
						}
						stream.nextLine();
					} while(gpa < 0);
					addStudent(name, sex, sID, gpa);
					break;
				case "2":
					printStudents();
					break;
				case "3":
					String sID1 = "";
					do {
						System.out.println("\nStudent ID (8 Numbers):");
						sID1 = stream.nextLine();
					}while(sID1.length() != 8 | !sID1.matches("[0-9]{8}"));
					searchStudent(sID1);
					break;
				case "4":
					countStudentsBySex();
					break;
				case "5":
					averageStudentGPA();
					break;
				default: 
					System.out.println("\nUnknown option try again.");
			}
			
		}
	}
	
	public static void setStudentRecords(StudentRecord[] studentList) {
		students = studentList;
	}
	
	private static void averageStudentGPA() {
		float avg = 0;
		for(int idx = 0; idx < totalStudents; idx++) {
			avg += students[idx].getStudentGPA();
		}
		avg /= totalStudents;
		System.out.println("GPA Average: " + avg);
	}

	private static void countStudentsBySex() {
		int male = 0;
		int female = 0;
		for(int idx = 0; idx < totalStudents; idx++) {
			if(students[idx].getStudentSex() == Sex.MALE) {
				male++;
			}
			else {
				female++;
			}
		}
		System.out.println("Males: "+ male + " Females: " + female);
	}

	private static void searchStudent(String sID) {
		int idx = 0;
		while(idx < totalStudents && !sID.equals(students[idx].getStudentID())) {
			idx++;
		}
		if(idx < totalStudents) {
			System.out.println(students[idx].toString());
		}
		else {
			System.out.println("No student found");
		}
		
	}

	private static void printStudents() {
		System.out.println("\nRecords In System:");
		for(int idx = 0; idx < totalStudents; idx++) {
			System.out.println(students[idx].toString());
		}
	}

	private static void addStudent(String name, Sex sex, String sID, float gpa) {
		if(totalStudents + 1 >= students.length) {
			StudentRecord[] newList = new StudentRecord[students.length*2];
			int idx = 0;
			for(StudentRecord st : students) {
				newList[idx] = st;
				idx++;
			}
			students = newList;
		}
		students[totalStudents++] = new StudentRecord(name, sex, sID, gpa);
	}

	public enum Sex {
		MALE('M'), FEMALE('F');
		private final char letter;
		Sex(char letter) { this.letter = letter; }
		public char sexLetter() { return letter; }
	}
	
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
			return sName + " " + sex + " " + sID + " " + gpa;
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
