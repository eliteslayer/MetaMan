package org.coms362.group7.MetaMan;

import java.io.File;
import java.util.List;

/**
 * A controller for the MetaMan Object. All method calls to MetaMan from any
 * view must go through this.
 * 
 * @author COM S 362 GROUP 7
 * 
 */
public class MetaManController {

	private final MetaMan metaMan;

	/**
	 * Creates a MetaMan Controller Object.
	 * 
	 * @param metaMan
	 *            - The MetaMan Object that this controller will Manipulate.
	 * @throws MetaManException
	 */
	public MetaManController(File startingDirectory) throws MetaManException {
		this.metaMan = new MetaMan(startingDirectory);
	}

	/**
	 * Changes the directory that MetaData is currently in to 'newPath'
	 * 
	 * @param newPath
	 *            The path that MetaData's current working directory will be
	 *            changed to.
	 * @return True if the change was made successfully.
	 */
	public boolean changeDirectory(String newPath) {
		return this.metaMan.changeDirectory(newPath);
	}

	/**
	 * Deletes the currently selected file.
	 * 
	 * @return True if the file is deleted
	 */
	public boolean deleteSelectedFile() {
		return this.metaMan.deleteSelectedFile();
	}

	/**
	 * Bumps the current working directory up one level
	 * 
	 * @return True is the move up was successful.
	 */
	public boolean goUpOneDirectory() {
		return this.metaMan.goUpOneDirectory();
	}

	/**
	 * Lists all the files in the current directory.
	 * 
	 * @return - A list of files/directories in the current directory
	 * @throws MetaManException
	 */
	public List<MetaManFile> listing() throws MetaManException {
		return this.metaMan.listing();
	}

	/**
	 * List only the audio files
	 * 
	 * @return list of all supported audio files in the directory
	 * @throws MetaManException
	 */
	public List<MetaManFile> listingAudioOnly() throws MetaManException {
		return this.metaMan.listingAudioOnly();
	}

	/**
	 * List the directories in the current working directory
	 * 
	 * @return list of all the directories
	 */
	public List<MetaManFile> listingDirectoiesOnly() {
		return this.metaMan.listingDirectoriesOnly();
	}

	public List<MetaManFile> listingImagesOnly() throws MetaManException {
		return this.metaMan.listingImagesOnly();
	}

	/**
	 * Locks the selected file
	 * 
	 * @return true if locked
	 */
	public boolean lockSelectedFile() {
		return this.metaMan.lockSelectedFile();

	}

	/**
	 * Modifies the metadata tag with the newValue
	 * 
	 * @param key
	 *            The meta data key to be changed
	 * @param newValue
	 *            The value the key will be changed to
	 * @return
	 * @throws MetaManException
	 */
	public boolean modMetaDataOfSelectedFile(String key, String newValue)
			throws MetaManException {
		return this.metaMan.modMetaDataOfSelectedFile(key, newValue);
	}

	/**
	 * Opens the selected file
	 * 
	 * @return true if the file is opened
	 */
	public boolean openSelectedFile() {
		return this.metaMan.openSelectedFile();
	}

	/**
	 * Prints the current working directory's absolute path
	 * 
	 * @return the absolute path of the file
	 */
	public String printWorkingDirectory() {
		return this.metaMan.printWorkingDirectory();
	}

	/**
	 * Sets the selected audio file
	 * 
	 * @param index
	 *            position of the file in the cache(this is the index that is
	 *            printed in the veiw)
	 * @return true if the file could be selected
	 * @throws MetaManException
	 */
	public boolean setSelectedFile(int index) throws MetaManException {
		return this.metaMan.setSelectedFile(index);
	}

	/**
	 * Unlocks the selected file
	 * 
	 * @return true if unlocked
	 */
	public boolean unlockSelectedFile() {
		return this.metaMan.unlockSelectedFile();
	}

	/**
	 * Gets a string representation of the meta data
	 * 
	 * @return the meta data of the file
	 * @throws MetaManException
	 */
	public String viewMetaDataOfSelectedFile() throws MetaManException {
		return this.metaMan.viewMetaDataOfSelectedFile();
	}
	
	/**
	 * Renames the selected file
	 * 
	 * @return True if the filename was changed
	 */
	public boolean renameSelectedFile(String newName) {
		return this.metaMan.renameSelectedFile(newName);
	}
}