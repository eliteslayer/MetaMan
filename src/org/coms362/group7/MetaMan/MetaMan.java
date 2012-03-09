package org.coms362.group7.MetaMan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;

/**
 * 
 * @author COM S 362 Group 7
 * @version 1.0
 * 
 */
public class MetaMan {

	/**
	 * List of files resulting from the last ls command
	 */
	private List<MetaManFile> cache;

	/**
	 * the currently selected audio file
	 */
	private AudioFile selectedAudioFile;

	/**
	 * The current working directory
	 */
	private File workingDirectory;

	public MetaMan(File startingDirectory) throws MetaManException {
		this.workingDirectory = startingDirectory;
		if (!this.workingDirectory.exists()) {
			throw new MetaManException("The home directory could not be loaded");
		}
	}

	/**
	 * Change the directory to the one specified
	 * 
	 * @param dir
	 *            the target directory to change to
	 * @return true if changing was successful
	 */
	public boolean changeDirectory(String dir) {
		File file;
		if ((file = new File(dir)).exists()) {
			this.workingDirectory = file;
			return true;
		} else if ((file = new File(this.workingDirectory + "\\" + dir))
				.exists()) {
			this.workingDirectory = file;
			return true;
		}
		return false;
	}

	/**
	 * Move to the parent directory
	 * 
	 * @return true if the move was successful
	 */
	public boolean goUpOneDirectory() {
		if (this.workingDirectory.getParentFile().exists()) {
			return false;
		}
		this.workingDirectory = this.workingDirectory.getParentFile();
		return true;
	}

	/**
	 * Lists the metaman files in the current directory
	 * 
	 * @return list of all of the compatible files
	 * @throws MetaManException
	 */
	public List<MetaManFile> listing() throws MetaManException {
		final ArrayList<MetaManFile> list = new ArrayList<MetaManFile>();
		list.addAll(this.listingDirectoriesOnly());
		list.addAll(this.listingAudioOnly());
		this.cache = list;
		return list;
	}

	/**
	 * List only the audio files
	 * 
	 * @return list of all supported audio files in the directory
	 * @throws MetaManException
	 */
	public List<MetaManFile> listingAudioOnly() throws MetaManException {
		final List<MetaManFile> list = new ArrayList<MetaManFile>();
		for (final File f : this.workingDirectory
				.listFiles(new AudioFileFilter())) {
			list.add(new AudioFile(f.getAbsolutePath()));
		}
		this.cache = list;
		return list;
	}

	/**
	 * List the directories in the current working directory
	 * 
	 * @return list of all the directories
	 */
	public List<MetaManFile> listingDirectoriesOnly() {
		final ArrayList<MetaManFile> list = new ArrayList<MetaManFile>();
		for (final File f : this.workingDirectory.listFiles()) {
			if (this.workingDirectory.isDirectory()) {
				list.add(new MetaManDirectory(f.getAbsolutePath()));
			}
		}
		return list;
	}

	/**
	 * Locks the selected file
	 * 
	 * @return true if locked
	 */
	public boolean lockSelectedFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.lock();
	}

	/**
	 * Modifies the metadata tag with the newValue
	 * 
	 * @param key
	 *            The meta data key to be changed
	 * @param newValue
	 *            The value the key will be changed to
	 * @return
	 */
	public boolean modMetaDataOfSelectedFile(FieldKey key, String newValue) {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.setMetaData(key, newValue);
	}

	/**
	 * Opens the selected file
	 * 
	 * @return true if the file is opened
	 */
	public boolean openSelectedAudioFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.open();
	}

	/**
	 * Prints the current working directory's absolute path
	 * 
	 * @return the absolute path of the file
	 */
	public String printWorkingDirectory() {
		return this.workingDirectory.getAbsolutePath();
	}

	/**
	 * Sets the selected audio file
	 * 
	 * @param index
	 *            position of the file in the cache(this is the index that is
	 *            printed in the veiw)
	 * @return true if the file could be selected
	 */
	public boolean setSelectedAudioFile(int index) {
		this.selectedAudioFile = (AudioFile) this.cache.get(index);
		return true;
	}

	/**
	 * Unlocks the selected file
	 * 
	 * @return true if unlocked
	 */
	public boolean unlockSelectedFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.unlock();
	}

	/**
	 * Gets a string representation of the meta data
	 * 
	 * @return the meta data of the file
	 */
	public String viewMetaDataOfSelectedFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.view();
	}

}
