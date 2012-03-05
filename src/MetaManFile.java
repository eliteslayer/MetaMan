
/**
 * An abstract version of a file that MetaMan Supports.  See java.io.File.  Known subclasses are AudioFile.
 * @author COM S 362 GROUP 7
 *
 */
@SuppressWarnings("serial")
abstract class MetaManFile extends java.io.File {

	/**
	 * Creates a file that is supported by MetaMan.
	 * @param pathname - The physical path to the file.
	 */
	public MetaManFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

}
