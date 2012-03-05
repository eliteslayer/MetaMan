import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {
	private static FileNavigator fn;
	private static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		
		int choice = 0;
		while(choice >= 0){
			switch(choice){
			case 0:
				startup();
				break;
			case 1:
				System.out.println(fn.getAbsolutePath());
				break;
			case 2: 
				for(File f: fn.getChildrenFiles()){
					System.out.println(f.getName());
				}
			}
			choice = in.nextInt();
		}
	}
	
	private static void startup(){
		System.out.println("Would you like to use the default direcotry?(y/n)");
		String input = in.next();
		while(!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")){
			System.out.println("Invalid input, try again.\n");
			input = in.next();
		}
		if(input.equals("y")){
			try {
				fn = new FileNavigator(".");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				input = "n";
			}
		}
		if(input.equals("n")){
			System.out.println("Enter Directory/File name:");
			String fName = in.nextLine();
			try{
				fn = new FileNavigator(fName);
			}catch(FileNotFoundException e){
				e.getMessage();
			}
		}
	}
	
	private boolean validateInput(String[] inputs){
		return false;
	}
	
	private static void printMainMenu(){
		System.out.println("1: Current path");
		System.out.println("2: List child files");
		System.out.println("3: Change to parent Directory");
		System.out.println("4: Change directory/file");
		System.out.println("5: Current Path");
	}
}
