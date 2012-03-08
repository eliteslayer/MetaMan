import java.io.IOException;
import java.util.ArrayList;

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
	
	public boolean saveMetaData() {
		try {
			metaData.commit();
		} catch (CannotWriteException e) {
			return true;
		}
		return true;
	}
	
	public String getMetaData(String key){
		try {
			return metaData.getTag().getFirst(decodeFieldKey(key));
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
		}
		return key;
		
	}
	
	public boolean setMetaData(String key, String newValue)
	{
		FieldKey fieldKey = decodeFieldKey(key);
		try {
			metaData.getTag().setField(fieldKey, newValue);
		} catch (KeyNotFoundException e) {
			return false;
		} catch (FieldDataInvalidException e) {
			return false;
		}
		return true;
	}
	
	private FieldKey decodeFieldKey(String key){
		if (key.toUpperCase().equals("ARTIST")) {
			return FieldKey.ARTIST;
		} else if (key.toUpperCase().equals("TITLE")) {
			return FieldKey.TITLE;
		}
		else if (key.toUpperCase().equals("ALBUM")) {
			return FieldKey.ALBUM;
		}
		else if (key.toUpperCase().equals("TRACK")) {
			return FieldKey.TRACK;
		}
		else if (key.toUpperCase().equals("YEAR")) {
			return FieldKey.YEAR;
		}
		throw new KeyNotFoundException();
	}
	
	
}
