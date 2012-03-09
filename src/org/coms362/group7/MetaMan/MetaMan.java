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
	 * 
	 */
	private List<MetaManFile> cache;

	/**
	 * 
	 */
	private AudioFile selectedAudioFile;

	/**
	 * 
	 */
	private File workingDirectory;

	public MetaMan(File startingDirectory) throws MetaManException {
		this.workingDirectory = startingDirectory;
		if (!this.workingDirectory.exists()) {
			throw new MetaManException("The home directory could not be loaded");
		}
	}

	/**
	 * 
	 * @param dir
	 * @return
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
	 * 
	 * @return
	 */
	public boolean goUpOneDirectory() {
		if (this.workingDirectory.getParentFile().exists()) {
			return false;
		}
		this.workingDirectory = this.workingDirectory.getParentFile();
		return true;
	}

	/**
	 * 
	 * @return
	 * @throws MetaManException
	 * @throws InvalidAudioFrameException
	 * @throws ReadOnlyFileException
	 * @throws TagException
	 * @throws IOException
	 * @throws CannotReadException
	 * @throws CorruptedFileException
	 */
	public List<MetaManFile> listing() throws MetaManException {
		final ArrayList<MetaManFile> list = new ArrayList<MetaManFile>();
		list.addAll(this.listingAudioOnly());
		list.addAll(this.listingDirectoriesOnly());
		this.cache = list;
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws MetaManException
	 * @throws CorruptedFileException
	 * @throws InvalidAudioFrameException
	 * @throws ReadOnlyFileException
	 * @throws TagException
	 * @throws IOException
	 * @throws CannotReadException
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
	 * 
	 * @return
	 * @throws InvalidAudioFrameException
	 * @throws ReadOnlyFileException
	 * @throws TagException
	 * @throws IOException
	 * @throws CannotReadException
	 * @throws CorruptedFileException
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

	public boolean lockSelectedFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.lock();
	}

	/**
	 * 
	 * @param key
	 * @param newValue
	 * @return
	 */
	public boolean modMetaDataOfSelectedFile(FieldKey key, String newValue) {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.setMetaData(key, newValue);
	}

	/**
	 * 
	 * @return
	 */
	public boolean openSelectedAudioFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.open();
	}

	/**
	 * 
	 * @return
	 */
	public String printWorkingDirectory() {
		return this.workingDirectory.getAbsolutePath();
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean setSelectedAudioFile(int index) {
		this.selectedAudioFile = (AudioFile) this.cache.get(index);
		return true;
	}

	public boolean unlockSelectedFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.unlock();
	}

	public String viewMetaDataOfSelectedFile() {
		if (this.selectedAudioFile == null) {
			throw new NullPointerException();
		}
		return this.selectedAudioFile.view();
	}

}
