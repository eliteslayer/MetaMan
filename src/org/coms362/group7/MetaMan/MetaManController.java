package org.coms362.group7.MetaMan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.tag.FieldKey;


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
	 * Bumps the current working directory up one level
	 * 
	 * @return True is the move up was successful.
	 */
	public boolean goUpOneDirectory() {
		return this.metaMan.goUpOneDirectory();
	}


	/**
	 * 
	 * @return
	 * @throws MetaManException
	 */
	public List<MetaManFile> listing() throws MetaManException {
		return this.metaMan.listing();
	}

	/**
	 * 
	 * @return
	 * @throws MetaManException
	 */
	public List<AudioFile> listingAudioOnly() throws MetaManException {
		final ArrayList<AudioFile> list = new ArrayList<AudioFile>();
		for (final MetaManFile f : this.metaMan.listingAudioOnly()) {
			list.add((AudioFile) f);
		}
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public List<MetaManFile> listingDirectoiesOnly() {
		return this.metaMan.listingDirectoriesOnly();
	}

	public boolean lockSelectedFile() {
		return this.metaMan.lockSelectedFile();

	}

	/**
	 * 
	 * @param key
	 * @param newValue
	 * @return
	 */
	public boolean modMetaDataOfSelectedFile(FieldKey key, String newValue) {
		return this.metaMan.modMetaDataOfSelectedFile(key, newValue);
	}

	/**
	 * 
	 * @return
	 */
	public boolean openSelectedFile() {
		return this.metaMan.openSelectedAudioFile();
	}

	/**
	 * 
	 * @return
	 */
	public String printWorkingDirectory() {
		return this.metaMan.printWorkingDirectory();
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean setSelectedAudioFile(int index) {
		return this.metaMan.setSelectedAudioFile(index);
	}

	/**
	 * 
	 * @return
	 */
	public boolean unlockSelectedFile() {
		return this.metaMan.unlockSelectedFile();
	}

	/**
	 * 
	 * @return
	 */
	public String viewMetaDataOfSelectedFile() {
		return this.metaMan.viewMetaDataOfSelectedFile();
	}
}
