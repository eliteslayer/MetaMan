import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CMDView {

	
	public CMDView(MetaManController metaManController) {
		this.controller = metaManController;
		//this.workingDirectoryCache = controller.lsao();
		this.controller.lsao();
		this.populateOperationMap();
	}

	public void startup() throws IOException {

		// Print a welcome message to the user.
		println(welcomeMessage());

		Scanner scanner = new Scanner(System.in);

		// Loop always waiting for input until user types 'exit' or 'e'
		while (true) {

			// Print the directory MetaMan is currently looking in. NOTE: This
			// will be the users home directory at the startup of the program.
			print(controller.pwd() + "> ");

			// Scan in command and params form user
			userInput = scanner.nextLine();

			// Extract the command and the params from the input
			extractCommandAndParamsFromInput();

			// Exe the command using the given commands
			executeUserCommand();

		}
	}
	

	private void pwd() {
		println(controller.pwd());
	}

	private void ls() {
		println();
		println("     SUPORTED FILES/DIRS");
		println("------------------------------");
		println("#         NAME           TYPE");
		println("------------------------------");

		try {
			int count = 0;
			for (File f : controller.ls()) {
				if (f.isDirectory()) {
					print(count + "");
					String count_s = count + "";
					printSpace(10 - count_s.length());
					String name = f.getName();
					if (name.length() > 10) {
						name = name.substring(0, 10) + "...";
					}
					print(name);
					printSpace(15 - name.length());
					print("DIR");
					print("\n");
				} else {
					print(count + "");
					String count_s = count + "";
					printSpace(10 - count_s.length());
					String name = f.getName();
					if (name.length() > 10) {
						name = name.substring(0, 10) + "...";
					}
					print(name);
					printSpace(15 - name.length());
					print(f.getName().substring(f.getName().lastIndexOf('.'))
							.toUpperCase().substring(1));
					print("\n");
				}
				count++;
			}
		} catch (StringIndexOutOfBoundsException e) {
			// Do nothing
		}

		println();
		println();
	}

	private void lsdo() {

		println();
		println("     DIRECTORIES");
		println("------------------------------");
		println("#         NAME           ");
		println("------------------------------");

		try {
			int count = 0;
			for (File f : controller.lsdo()) {
				if (f.isDirectory()) {
					print(count + "");
					String count_s = count + "";
					printSpace(10 - count_s.length());
					String name = f.getName();
					if (name.length() > 10) {
						name = name.substring(0, 10) + "...";
					}
					print(name);
					printSpace(15 - name.length());
					print("\n");
				}
				count++;
			}
		} catch (StringIndexOutOfBoundsException e) {
			// Do nothing
		}
		println();

	}

	private void lsao() {

		println();
		println("     SUPORTED FILES/DIRS");
		println("------------------------------");
		println("#         TITLE          ARTIST");
		println("------------------------------");

		try {
			int count = 0;
			ArrayList<AudioFile> list = new ArrayList<AudioFile>();
			list = (ArrayList<AudioFile>) controller.lsao();
			for (AudioFile f : list) {
				print(count + "");
				String count_s = count + "";
				printSpace(10 - count_s.length());
				String name = f.getMetaData("TITLE");
				if (name.length() > 10) {
					name = name.substring(0, 10) + "...";
				}
				print(name);
				printSpace(15 - name.length());
				print(f.getMetaData("ARTIST"));
				print("\n");
				count++;
			}
		} catch (StringIndexOutOfBoundsException e) {
			// Do nothing
		}

		println();

	}

	private boolean up() {
		return controller.up();
	}

	private void cd(String newPath) {
		//lsao();
		if (!controller.cd(newPath))
			println("MetaMan could not find the path given.");
		println();
	}

	private void exit() {
		println("Goodbye");
		System.exit(0);
	}

	private void unknownCmd() {
		println("'"
				+ userInput
				+ "' is not recognized as a MetaMan command.  Please be sure the command exists and you have entered the correct paramaters.");
		println();
	}

	private void help() {
		println();
		println("Support MetaMan Commands:");
		println();
		println("MODAO {tag} {startIndex} {endIndex} {newName}:");
		println("     Modifies meta data info for audio files in the current directory");
		println();
		println("PWD:");
		println("     Prints out the current working directory");
		println("LS:");
		println("     Lists all the files MetaMan supports within the current directory");
		println("LSAO:");
		println("     Lists all the audio files MetaMan supports within the current directory");
		println("LSDO:");
		println("     Lists all the directorys within the current directory");
		println();

	}

//	private void modao() {
//		String element = "";
//		for (int i = 3; i < userParams.length; i++) {
//			element += userParams[i] + " ";
//		}
//		element.trim();
//
//		ArrayList<AudioFile> toSelect = new ArrayList<AudioFile>();
//		
//		for(int i = Integer.parseInt(userParams[1]) ; i <= Integer.parseInt(userParams[2]) ; i++ ){
//			toSelect.add(this.workingDirectoryCache.get(i));
//		}
//		
//		
//		ArrayList<AudioFile> changedFiles = (ArrayList<AudioFile>) controller
//				.modao(this.workingDirectoryCache, userParams[0], element);
//		
//		println(changedFiles.size() + " files where modified successfully");
//		println();
//		
//		
//
//	}
	
//	private void modAudioFileRange(List<Integer> range, String key, String newValue){
//		int nSuccessfulChanges = 0;
//		for(int i : range){
//			this.controller.selectAudioFile(i);
//			this.controller.modSelectedAudio(key, newValue);
//			nSuccessfulChanges++;
//		}
//		println(nSuccessfulChanges + " file(s) where modified successfully");
//		println();
//		
//	}

	private String welcomeMessage() {
		return "MetaMan -v 1.0\n--------------\nWelcome To MetaMan!\nEnter 'help' if needed\n";
	}

	private void print(String message) {
		System.out.print(message);
	}

	private void println(String message) {
		System.out.println(message);
	}

	private void println() {
		System.out.println();
	}

	private MetaManController controller;
	private HashMap<String, String> operation_map;
	private String userCmd;
	private String[] userParams;
	private String userInput;

	private void populateOperationMap() {
		this.operation_map = new HashMap<String, String>();
		this.operation_map.put("pwd", "pwd");
		this.operation_map.put("exit", "exit");
		this.operation_map.put("e", "exit");
		this.operation_map.put("ls", "ls");
		this.operation_map.put("../", "up");
		this.operation_map.put("up", "up");
		this.operation_map.put("..", "up");
		this.operation_map.put("cd", "cd");
		this.operation_map.put("help", "help");
		this.operation_map.put("lsao", "lsao");
		this.operation_map.put("lsdo", "lsdo");
		this.operation_map.put("mod", "mod");
		this.operation_map.put("view", "view");
		this.operation_map.put("open", "open");
	}

	private void executeUserCommand() throws IOException {
			if (operation_map.get(userCmd).equals("pwd"))
				pwd();
			else if (operation_map.get(userCmd).equals("exit"))
				exit();
			else if (operation_map.get(userCmd).equals("ls"))
				ls();
			else if (operation_map.get(userCmd).equals("up"))
				up();
			else if (operation_map.get(userCmd).equals("cd"))
				cd(userParams[0]);
			else if (operation_map.get(userCmd).equals("help"))
				help();
			else if (operation_map.get(userCmd).equals("lsao"))
				lsao();
//			else if (operation_map.get(userCmd).equals("mod")){
//				int start = Integer.parseInt(userParams[0]);
//			    int end = Integer.parseInt(userParams[1]);
//				ArrayList<Integer> range = new ArrayList<Integer>();
//				for(int i = start ; i <= end ; i++){
//					range.add(i);
//				}
//				this.modAudioFileRange(range, userParams[2], userParams[3]);
//			}
			else if (operation_map.get(userCmd).equals("lsdo"))
				lsdo();
			else if (operation_map.get(userCmd).equals("view")){
				this.selectAudioFile(Integer.parseInt(userParams[0]));
				this.view();
			}	
			else if (operation_map.get(userCmd).equals("open")){
				this.selectAudioFile(Integer.parseInt(userParams[0]));
				this.open();
			}
			else if (operation_map.get(userCmd).equals("mod")){
				this.selectAudioFile(Integer.parseInt(userParams[0]));
				this.modSelected(userParams[1],userParams[2]);
			}
				
			else{
				this.unknownCmd();
			}
	}

	private void modSelected(String key, String newValue) {
		this.controller.modSelectedAudio(key, newValue);	
	}

	private void view() {
		
		AudioFile selectedFile = this.controller.getSelectedFile();
		
		String filename = selectedFile.getName();
		String title = selectedFile.getMetaData("TITLE");
		String artist = selectedFile.getMetaData("ARTIST");
		String track = selectedFile.getMetaData("TRACK");
		String album = selectedFile.getMetaData("ALBUM");
		String year = selectedFile.getMetaData("YEAR");
		
		println("*****************************");
		println("TITLE: " + title);
		println("ARTIST: " + artist);
		println("FILENAME: " + filename);
		println("TRACK: " + track);
		println("YEAR: " + year);
		println("ALBUM: " + album);
		println("*****************************");
		
		
	}
	
	private void selectAudioFile(int index){
		this.controller.selectAudioFile(index);
	}
	
	private void open() throws IOException{
		String songTitle = this.controller.getSelectedFile().getMetaData("TITLE");
		
		println("OPENING: " + songTitle + " ...");
	    
		this.controller.openSelectedAudioFile();
		
	}
	

	private void printSpace(int count) {
		printChar(count, ' ');
	}

	private void printChar(int count, char x) {
		for (int i = 0; i < count; i++) {
			print(x + "");
		}
	}

	private void extractCommandAndParamsFromInput() {

		try {
			userCmd = userInput.substring(0, userInput.indexOf(' '));
			userParams = userInput.substring(userInput.indexOf(' ')).trim()
					.split(" ");
			userCmd = userCmd.toLowerCase().trim();
		} catch (StringIndexOutOfBoundsException e) {
			// If in here, no parameters were found
			userCmd = userInput;
			userParams = null;
			userCmd = userCmd.toLowerCase().trim();
		}
	}
	
	//private List<MetaManFile> workingDirectoryCache;

}
