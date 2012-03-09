package org.coms362.group7.MetaMan;

import java.io.IOException;

import org.jaudiotagger.tag.FieldKey;

/**
 * An abstract version of a file that MetaMan Supports. See java.io.File. Known
 * subclasses are AudioFile.
 * 
 * @author COM S 362 GROUP 7
 * 
 */
abstract class MetaManFile extends java.io.File {

	/**
	 * Needed for proper inheritance from parent class java.io.File
	 */
	private static final long serialVersionUID = 1622605674363717694L;

	/**
	 * Creates a file that is supported by MetaMan.
	 * 
	 * @param pathname
	 *            - The physical path to the file.
	 */
	public MetaManFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
		if (!o.getClass().equals(this.getClass())) {
			return false;
		}
		if (!((MetaManFile) o).getAbsolutePath().equals(this.getAbsolutePath())) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the specified meta data
	 * 
	 * @param key
	 *            key to retrieve
	 * @return String of retrieved data or null if it does not exist
	 */
	public abstract String getMetaData(FieldKey key);

	/**
	 * Locks the file from being modified by meta man
	 * 
	 * @return
	 */
	public boolean lock() {
		GlobalData.LOCKED_FILES.add(this);
		return this.setReadOnly();
	}

	/**
	 * Opens the file with the operating systems default program
	 * 
	 * @throws IOException
	 */
	public boolean open() {
		if (GlobalData.LOCKED_FILES.contains(this)) {
			return false;
		}
		try {
			Runtime.getRuntime().exec(
					"rundll32 SHELL32.DLL,ShellExec_RunDLL " + this);
		} catch (final IOException e) {
			return false;
		}
		return true;
	}

	public boolean setMetaData(FieldKey key, String value) {
		if (GlobalData.LOCKED_FILES.contains(this)) {
			return false;
		}
		return this.setMetaDataHelper(key, value);
	}

	/**
	 * Set meta data
	 * 
	 * @param key
	 *            key to set
	 * @param value
	 *            value to set to
	 * @return true if successful
	 */
	protected abstract boolean setMetaDataHelper(FieldKey key, String value);

	/**
	 * @return
	 * 
	 */
	public boolean unlock() {
		GlobalData.LOCKED_FILES.remove(GlobalData.LOCKED_FILES.indexOf(this));
		return true;
	}

	/**
	 * Gets a string view of the files metadata
	 * 
	 * @return string of metadata
	 */
	public abstract String view();
}
