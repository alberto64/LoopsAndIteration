import java.util.Scanner;

public class ShellMain {

	public static void main(String[] args) {
		while(true) {
			Scanner stream = new Scanner(System.in);
			String input;
			do {
				System.out.println("\nWrite Stuff");
				input = stream.next();
				
			} while(input == null);
			
			switch(input) {
				case "exit":
					return;
				default: 
					System.out.println("\nTry Again");
			}
			
		}

	}

}
