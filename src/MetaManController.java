import java.io.File;
import java.util.List;

/**
 * A controller for the MetaMan Object.  All method calls to MetaMan from any view must go through this.
 * @author COM S 362 GROUP 7
 *
 */
public class MetaManController {

	/**
	 * Creates a MetaMan Controller Object.
	 * @param metaMan - The MetaMan Object that this controller will Manipulate.
	 */
	public MetaManController(MetaMan metaMan)
	{
		this.metaMan = metaMan;
	}
	
	/**
	 * Fetches the current working directory.
	 * @return The current working directory of MetaMan.
	 */
	public String pwd(){
		return metaMan.pwd();
	}
	
	/**
	 * Lists all the files in the current directory.
	 * @return - A list of files/directories in the current directory
	 * @throws CorruptedFileException
	 */
	public List<File> ls(){
		return metaMan.ls();
	}
	
	/**
	 * Bumps the current working directory up one level
	 * @return True is the move up was successful.
	 */
	public boolean up(){
		return metaMan.up();
	}
	
	/**
	 * Changes the directory that MetaData is currently in to 'newPath'
	 * @param newPath The path that MetaData's current working directory will be changed to.
	 * @return True if the change was made successfully.
	 */
	public boolean cd(String newPath){
		return metaMan.cd(newPath);
	}
	
	/**
	 * 
	 * @return
	 * @throws CorruptedFileException
	 */
	public List<AudioFile> lsao(){
		return metaMan.lsao();
	}
	
	public List<File> lsdo(){
		return metaMan.lsdo();
	}
	
	public List<AudioFile> modao(List<AudioFile> toMod, String key, String newValue){
		return metaMan.modao(toMod, key, newValue);
	}
	
//	public boolean addToSelectedAudioFiles(List<AudioFile> toSelect){
//		return this.metaMan.addToSelectedAudioFiles(toSelect);
//	}
//	
//	public void clearSelectedAudioFiles(){
//		this.metaMan.clearSelectedAudioFiles();
//	}
	
	private final MetaMan metaMan;

	public void selectAudioFile(int i) {
		AudioFile selectedAudioFile = this.metaMan.lsao().get(i);
		this.metaMan.setSelectedAudioFile(selectedAudioFile);
	}

	public AudioFile getSelectedFile() {
		return this.metaMan.getSelectedAudioFile();
	}

	public void openSelectedAudioFile() {
		this.metaMan.openSelectedAudioFile();
	}

	public boolean modSelectedAudio(String key, String newValue) {
		return this.metaMan.modSelectedFile(key, newValue);
	}
}
