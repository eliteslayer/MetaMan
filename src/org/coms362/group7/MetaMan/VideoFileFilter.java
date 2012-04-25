package org.coms362.group7.MetaMan;

import java.io.File;
import java.io.FileFilter;

public class VideoFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		final String name = file.getName();
		if (name.endsWith(".mp4") || name.endsWith(".avi")) {
			return true;
		}
		return false;
	}

}
