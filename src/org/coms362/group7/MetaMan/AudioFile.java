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


@SuppressWarnings("serial")
public class AudioFile extends MetaManFile{

	private org.jaudiotagger.audio.AudioFile metaData;
	
	public AudioFile(String pathname) {
		super(pathname);
		try {
			this.metaData = AudioFileIO.read(this);
		} catch (CannotReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getMetaData(FieldKey key){
		try {
//			return metaData.getTag().getFirst(decodeFieldKey(key));
			return metaData.getTag().getFirst(key);
		} catch (KeyNotFoundException e) {
			return "Not Found";
		}
	}
	
	public boolean setMetaData(FieldKey key, String newValue)
	{
		//FieldKey fieldKey = decodeFieldKey(key);
		try {
			metaData.getTag().setField(key, newValue);
		} catch (KeyNotFoundException e) {
			return false;
		} catch (FieldDataInvalidException e) {
			return false;
		}
		//change meta data in the actual file
		try {
			metaData.commit();
		} catch (CannotWriteException e) {
			return false;
		}
		return true;
	}
	
//	private FieldKey decodeFieldKey(String key){
//		if (key.toUpperCase().equals("ARTIST")) {
//			return FieldKey.ARTIST;
//		} else if (key.toUpperCase().equals("TITLE")) {
//			return FieldKey.TITLE;
//		}
//		else if (key.toUpperCase().equals("ALBUM")) {
//			return FieldKey.ALBUM;
//		}
//		else if (key.toUpperCase().equals("TRACK")) {
//			return FieldKey.TRACK;
//		}
//		else if (key.toUpperCase().equals("YEAR")) {
//			return FieldKey.YEAR;
//		}
//		throw new KeyNotFoundException();
//	}

	@Override
	public String view() {
		String retVal = "";
		String title = this.getMetaData(FieldKey.TITLE);
		String artist = this.getMetaData(FieldKey.ARTIST);
		String track = this.getMetaData(FieldKey.TRACK);
		String album = this.getMetaData(FieldKey.ALBUM);
		String year = this.getMetaData(FieldKey.YEAR);
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
