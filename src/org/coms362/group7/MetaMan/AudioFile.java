package org.coms362.group7.MetaMan;

import java.io.IOException;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;

/**
 * An AudioFile, extended from MetaManFile, file is any File that can fit through AudioFileFilter
 * @see AudioFileFilter
 * @see MetaManFile
 * @author COM S 363 Group 7
 * 
 */
public class AudioFile extends MetaManFile {

	/**
	 * Needed for proper inheritance from parent class MetaManFile
	 */
	private static final long serialVersionUID = -5447397507042213309L;

	/**
	 * The meta data of 'this' audio file. NOTE: This is taken from an external
	 * library called jAudioTagger
	 * 
	 * @see http://www.jthink.net/jaudiotagger/
	 */
	private org.jaudiotagger.audio.AudioFile metaData;

	/**
	 * Constructs a new AudioFile
	 * @param pathname The file path of the audio file to be constructed
	 * @throws MetaManException If the file could not be constructed properly.
	 */
	public AudioFile(String pathname) throws MetaManException {
		super(pathname);
		try {
			this.metaData = AudioFileIO.read(this);
		} catch (final CannotReadException e) {
			throw new MetaManException("Could not read audio file: "
					+ this.getAbsolutePath());
		} catch (final IOException e) {
			throw new MetaManException("Could not open audio file: "
					+ this.getAbsolutePath());
		} catch (final TagException e) {
			throw new MetaManException(
					"Could not read metadata for audio file: "
							+ this.getAbsolutePath());
		} catch (final ReadOnlyFileException e) {
			throw new MetaManException("Could not open read-only file: "
					+ this.getAbsolutePath());
		} catch (final InvalidAudioFrameException e) {
			throw new MetaManException("Could not read corrupted audio-file: "
					+ this.getAbsolutePath()
					+ " - Please remove this file from the directory.");
		}
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String getMetaData(FieldKey key) {
		try {
			return this.metaData.getTag().getFirst(key);
		} catch (final KeyNotFoundException e) {
			return null;
		}
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public boolean setMetaDataHelper(FieldKey key, String newValue) {
		try {
			this.metaData.getTag().setField(key, newValue);
			this.metaData.commit();
			return true;
		} catch (final KeyNotFoundException e) {
			return false;
		} catch (final FieldDataInvalidException e) {
			return false;
		} catch (final CannotWriteException e) {
			return false;
		}
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String view() {
		String retVal = "";
		final String title = this.getMetaData(FieldKey.TITLE);
		final String artist = this.getMetaData(FieldKey.ARTIST);
		final String track = this.getMetaData(FieldKey.TRACK);
		final String album = this.getMetaData(FieldKey.ALBUM);
		final String year = this.getMetaData(FieldKey.YEAR);
		retVal += "*****************************\n";
		retVal += "TITLE: " + title + "\n";
		retVal += "ARTIST: " + artist + "\n";
		retVal += "FILENAME: " + this.getName() + "\n";
		retVal += "TRACK: " + track + "\n";
		retVal += "YEAR: " + year + "\n";
		retVal += "ALBUM: " + album + "\n";
		retVal += "*****************************\n";

		return retVal;
	}

}
