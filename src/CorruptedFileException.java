import java.io.File;

/**
 * An exception that holds a corrupted file.  Use this when a given file is corrupted and the caller should be notified.  
 * @author COM S 389 GROUP 7
 *
 */
@SuppressWarnings("serial")
public class CorruptedFileException extends Exception {
	
	/**
	 * Creates a CorruptedFileException
	 * @param f - The corrupted file.
	 */
	public CorruptedFileException(File f){
		this.file = f;
	}
	
	/**
	 * Fetches the corrupted file for the user.
	 * @return file - The Corrupted file.
	 */
	public String getFilename(){
		return this.file.getName();
	}
	
	/**
	 * The corrupted file
	 */
	private File file;
}
