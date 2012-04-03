package org.coms362.group7.MetaMan;

import java.io.IOException;

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
	 * @throws MetaManException
	 */
	public abstract String getMetaData(String key) throws MetaManException;

	/**
	 * Locks the file from being modified by meta man
	 * 
	 * @return
	 */
	public boolean lock() {
		return this.setWritable(false);
	}

	/**
	 * Opens the file with the operating systems default program
	 * 
	 * @throws IOException
	 */
	public boolean open() {
		if (!this.canWrite()) {
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

	public boolean setMetaData(String key, String value)
			throws MetaManException {
		if (!this.canWrite()) {
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
	 * @throws MetaManException
	 */
	protected abstract boolean setMetaDataHelper(String key, String value)
			throws MetaManException;

	/**
	 * @return
	 * 
	 */
	public boolean unlock() {
		return this.setWritable(true);
	}

	/**
	 * Gets a string view of the files metadata
	 * 
	 * @return string of metadata
	 * @throws MetaManException
	 */
	public abstract String view() throws MetaManException;
	
	/**
	 * Deletes this MetaManFile from the FileSystem.  Note this function simply overrides the parent function delete() inherited from File--This was mainly implemented for readability of the program.
	 * 
	 * @return True if the this MetaManFile was deleted successfully.
	 */
	@Override
	public boolean delete() {
		return super.delete();
	}
}
