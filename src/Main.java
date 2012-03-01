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

		System.out.println("Artist: " + f.getTag().getFirst(FieldKey.ARTIST).toString());
		System.out.println("Album: " + f.getTag().getFirst(FieldKey.ALBUM).toString());
		System.out.println("Title: " + f.getTag().getFirst(FieldKey.TITLE).toString());
		System.out.println("Year: " + f.getTag().getFirst(FieldKey.YEAR).toString());
		System.out.println("Track: " + f.getTag().getFirst(FieldKey.TRACK).toString());
		
	}
}
