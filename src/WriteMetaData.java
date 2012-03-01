import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class WriteMetaData {
	public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		AudioFile f = AudioFileIO.read(fc.getSelectedFile());
		Tag tag = f.getTag();
		
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader typestuff = new BufferedReader(istream);

		System.out.println("Current ARTIST: " + f.getTag().getFirst(FieldKey.ARTIST).toString());
		System.out.println("Who should the ARTIST be?");
		String artist = typestuff.readLine();
		tag.setField(FieldKey.ARTIST,artist);
		
		System.out.println("Current ALBUM: " + f.getTag().getFirst(FieldKey.ALBUM).toString());
		System.out.println("What should the ALBUM be?");
		String album = typestuff.readLine();
		tag.setField(FieldKey.ALBUM,album);

		System.out.println("Current TITLE: " + f.getTag().getFirst(FieldKey.TITLE).toString());
		System.out.println("What should the TITLE be?");
		String title = typestuff.readLine();
		tag.setField(FieldKey.TITLE,title);

		System.out.println("Current YEAR: " + f.getTag().getFirst(FieldKey.YEAR).toString());
		System.out.println("What should the YEAR be?");
		String year = typestuff.readLine();
		tag.setField(FieldKey.YEAR,year);

		System.out.println("Current TRACK NUMBER: " + f.getTag().getFirst(FieldKey.TRACK).toString());
		System.out.println("What should the TRACK NUMBER be?");
		String trackno = typestuff.readLine();
		tag.setField(FieldKey.TRACK,trackno);

		f.commit();
		
		
	}

}
