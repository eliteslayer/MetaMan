package org.coms362.group7.MetaMan;

import java.io.IOException;

import org.jaudiotagger.tag.FieldKey;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


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

	/**
	 * Gets a string view of the files metadata
	 * @return string of metadata 
	 */
	public abstract String view();
	
	/**
	 * Set meta data
	 * @param key key to set
	 * @param value value to set to
	 * @return true if successful
	 */
	public abstract boolean setMetaData(FieldKey key, String value);
	
	/**
	 * Gets the specified meta data
	 * @param key key to retrieve
	 * @return String of retrieved data or null if it does not exist
	 */
	public abstract String getMetaData(FieldKey key);
	
	/**
	 * Opens the file with the operating systems default program
	 * @throws IOException 
	 */
	public void open() throws IOException{
		Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+ this);
	}
	/**
	 * Locks the file from being modified by meta man
	 */
	public void lock(){
		//TODO: checks METAMANLOCKER file for file names
		//throw exception tell to check locker file
		throw new NotImplementedException();
	}
	/**
	 * 
	 */
	public void unlock(){
		//TODO: checks METAMANLOCKER file for file names
		//throw exception tell to check locker file
		throw new NotImplementedException();
	}
}
