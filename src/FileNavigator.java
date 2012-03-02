import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFileChooser;

public class FileNavigator {
	private File currentFile;

	/**
	 * Creates a FileNavigator whose current file is the path
	 * 
	 * @param path
	 */
	public FileNavigator(String path) {
		currentFile = new File(path);
	}

	/**
	 * Create a FileNavigator whose current file is file
	 * 
	 * @param file
	 */
	public FileNavigator(File file) {
		currentFile = file;
	}

	/**
	 * Changes the current file to the directory specified by the string
	 * 
	 * @param newDirectory
	 *            the directory to change to
	 * @throws FileNotFoundException
	 *             if the new directory does not exist
	 */
	public void changeDirectory(String newDirectory)
			throws FileNotFoundException {
		File tempFile = new File(newDirectory);
		if (tempFile.exists()) {
			currentFile = tempFile;
		} else {
			throw new FileNotFoundException("File Does Not Exist: "
					+ newDirectory);
		}
	}

	/**
	 * Changes the current file to its parent if it exits otherwise the current
	 * file remains the same.
	 * 
	 * @return true if the operation succeeded in change the file to its parent
	 */
	public boolean changeToParentDirectory() {
		if (currentFile.getParentFile() != null) {
			currentFile = currentFile.getParentFile();
			return true;
		} else
			return false;
	}

	/**
	 * Get the parent file
	 * 
	 * @return File that is the current files parent.
	 */
	public File getParent() {
		return currentFile.getParentFile();
	}

	/**
	 * Gets all of the children of the current file
	 * 
	 * @return File[] holding all of the child files/directories
	 */
	public File[] getChildrenFiles() {
		File[] children = currentFile.listFiles();
		Arrays.sort(children);
		return children;
	}

	/**
	 * Gets the String representation of the absolute path
	 * 
	 * @return the absolute path of the current file
	 */
	public String getAbsolutePath() {
		return currentFile.getAbsolutePath();
	}

	/**
	 * Gets the current file of the navigator
	 * 
	 * @return
	 */
	public File getCurrentFile() {
		return currentFile;
	}

	/**
	 * Checks write permissions
	 * 
	 * @return True if it is writable
	 */
	public boolean canWrite() {
		return currentFile.canWrite();
	}

	/**
	 * Check read Permissions
	 * 
	 * @return True if It is readable
	 */
	public boolean canRead() {
		return currentFile.canRead();
	}

	/**
	 * Moves the file to the given destination file. If the operation is
	 * successful then the currentFile is in the Directory that it was moved to.
	 * 
	 * @param destinationDirectory
	 *            The Directory to be moved to
	 * @return true if it was successfully moved
	 * @throws IOException
	 */
	public boolean moveCurrentFile(String destinationDirectory)
			throws IOException {
		File f = new File(destinationDirectory);
		return currentFile.renameTo(new File(f, currentFile.getName()));
	}

	/**
	 * Sets the currentFile using the selected file from a JFileChoser
	 * @return true if a file was choosen
	 */
	public boolean setCurrentFileByFileChooser() {
		JFileChooser fc = new JFileChooser();
		if (currentFile.isDirectory()) {
			fc.setCurrentDirectory(currentFile);
		} else
			fc.setCurrentDirectory(currentFile.getParentFile());
		int result = fc.showDialog(null, "Select");
		if(result == JFileChooser.APPROVE_OPTION){
			currentFile = fc.getSelectedFile();
			return true;
		}
		else
			return false;
	}
}
