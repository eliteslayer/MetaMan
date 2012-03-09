package org.coms362.group7.MetaMan;

import java.io.File;

/**
 * The class starts the program MetaMan using a using a view.
 * 
 * @author COM S 362 Group 7
 * 
 */
public class ProgramStarter {

	/**
	 * The current user's home directory
	 */

	private static final String HOME_DIR = System.getProperty("user.home");

	/**
	 * Starts the program.
	 * 
	 * @param args
	 *            - Unused
	 */
	public static void main(String[] args) {

		try {
			final MetaManCommandLineView cmd = new MetaManCommandLineView(new File(ProgramStarter.HOME_DIR));
			cmd.startup();

		} catch (final MetaManException e) {
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println("MetaMan must now exit...");
			System.exit(0);
		} catch (final Exception e) {
			System.out.println();
			System.out
					.println("An unknown error has occured, MetaMan must now exit...");
			e.printStackTrace();
			System.exit(0);
		}
	}
}
