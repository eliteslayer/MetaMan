package org.coms362.group7.MetaMan;

import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

/**
 * 
 * @author COM S 362 Group 7
 * 
 */
public class MetaManDirectory extends MetaManFile {

	/**
	 * Needed for proper inheritance from parent Class MetaManFile
	 */
	private static final long serialVersionUID = 6040146303193263835L;

	/**
	 * 
	 * @param pathname
	 * @throws InvalidAudioFrameException
	 * @throws ReadOnlyFileException
	 * @throws TagException
	 * @throws IOException
	 * @throws CannotReadException
	 * @throws CorruptedFileException
	 */
	public MetaManDirectory(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String getMetaData(String key) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public boolean setMetaDataHelper(String key, String value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String view() {
		throw new UnsupportedOperationException();
	}

}
