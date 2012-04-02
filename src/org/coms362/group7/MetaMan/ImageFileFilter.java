package org.coms362.group7.MetaMan;

import java.io.File;
import java.io.FileFilter;

/**
 * A FileFilter that only lets Image Files through its only method, accept().
 * 
 * @see FileFilter
 * @author COM S 362 Group 7
 * 
 */
public class ImageFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		final String name = file.getName();
		if (name.endsWith(".jpg") || name.endsWith(".JPG")) {
			return true;
		}
		return false;
	}

}
