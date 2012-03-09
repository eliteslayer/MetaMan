package org.coms362.group7.MetaMan;
import java.io.File;
import java.io.FileFilter;


public class AudioFileFilter implements FileFilter{

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		if (name.endsWith(".mp3") || name.endsWith(".flac") || name.endsWith(".fla")){
			return true;
		}
		return false;
	}

}
