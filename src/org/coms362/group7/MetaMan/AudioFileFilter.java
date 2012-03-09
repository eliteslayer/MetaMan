package org.coms362.group7.MetaMan;

import java.io.File;
import java.io.FileFilter;

/**
 * A FileFilter that only lets Audio Files through its only method, accept().
 * @see FileFilter
 * @author COM S 362 Group 7
 * 
 */
public class AudioFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		final String name = file.getName();
		if (name.endsWith(".mp3") || name.endsWith(".flac")
				|| name.endsWith(".fla")) {
			return true;
		}
		return false;
	}

}
