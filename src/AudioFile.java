import java.io.File;
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
	
	public AudioFile(String pathname) throws CorruptedFileException {
		
		super(pathname);
		try {
			metaData = AudioFileIO.read(this);
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
			throw new CorruptedFileException(new File(pathname));
		}
	}
	
	public boolean save() {
		try {
			metaData.commit();
		} catch (CannotWriteException e) {
			return true;
		}
		return true;
	}
	
	public String get(FieldKey key){
		return metaData.getTag().getFirst(key);
	}
	
	public boolean set(String key, String newValue)
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
		throw new KeyNotFoundException();
	}

}