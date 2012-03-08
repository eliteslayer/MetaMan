import java.io.IOException;

/**
 * The class starts the program using a command line view.
 * @author COM S 362 Group 7
 *
 */
public class ProgramStarter {

	/**
	 * Starting point of the program.  
	 * @param args - No arguments to the main program are excepted at this time.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Create a MetaMan Object
		MetaMan metaMan = new MetaMan();
		
		//Create a MetaMan Controller placing metaMan inside of it to be controlled.
		MetaManController controller = new MetaManController(metaMan);
		
		//Create the command line view of the program.  Place the controller inside of the view.
		CMDView cmd = new CMDView(controller);
		
		//Start the command line view of the program.
		cmd.startup();
	}

}
