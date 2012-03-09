package org.coms362.group7.MetaMan;

/**
 * This exception should be through, with a useful message, when ever something goes wrong during MetaMan method calls.
 * @author COM S 362
 *
 */
public class MetaManException extends Exception {

	/**
	 * Needed for proper inheritance from Parent class MetaManException
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new MetaManException
	 * @param string The exception message.
	 */
	public MetaManException(String string) {
		super(string);
	}

}
