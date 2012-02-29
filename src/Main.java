import java.io.IOException;

import javax.swing.JFileChooser;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;

public class Main {
	public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		AudioFile f = AudioFileIO.read(fc.getSelectedFile());
		//System.out.println(f.getTag().toString());
		System.out.println(f.getTag().getFields(FieldKey.ARTIST).toString());
	}
}
