
public class LoopsExampleMain {

	public static void main(String[] args) {
		
		char[] letters = {'a', 'b', 'd', 'f', 'j', 'o', 'p', 'z', 'y', 'h'};
		
		System.out.println("\nWhile:");
		int count1 = 0;
		while(count1 < 10) { 
			System.out.print(letters[count1] + " ");
			count1++;
		}
		
		System.out.println("\nDo/While:");
		int count2 = 0;
		do {
			System.out.print(letters[count2] + " ");
			count2++;
		}while(count2 < 10);
		
		System.out.println("\nFor:");
		for(int count3 = 0; count3 < 10; count3++) {
			System.out.print(letters[count3] + " ");
		}
		
		System.out.println("\nForeach:");
		for(char letter : letters) {
			System.out.print(letter + " ");
		}
	}

}
