import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;


@SuppressWarnings("serial")
public class AudioFile extends MetaManFile{

	private AudioFileMetaData metaData;
	
	public AudioFile(String pathname) throws CorruptedFileException {
		super(pathname);
		AudioFileMetaData metaData = new AudioFileMetaData();
		metaData.loadMetaData(this);
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
		throw new KeyNotFoundException();
	}

}
