package org.coms362.group7.MetaMan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.tag.FieldKey;

public class MetaMan {
	
	public MetaMan(){
		
	}

	public boolean cd(String dir) {

		
		File f = new File(dir);
		if (f.exists()) {
			workingDirectory = f;
			return true;
		}

		String oldValue = workingDirectory.getAbsolutePath();
		workingDirectory = new File(workingDirectory + "\\" + dir);
		if (workingDirectory.exists())
			return true;
		else {
			workingDirectory = new File(oldValue);
			return false;
		}
	}

	public boolean up() {
		
		if (workingDirectory.getParentFile() == null)
			return false;
		workingDirectory = workingDirectory.getParentFile();
		return true;

	}

	public String pwd() {
		return workingDirectory.getAbsolutePath();
	}

	public List<MetaManFile> ls() {
		ArrayList<MetaManFile> list = new ArrayList<MetaManFile>();
		list.addAll(lsao());
		list.addAll(lsdo());
		this.cache = list;
		return list;
	}

	public List<MetaManFile> lsao() {
		List<MetaManFile> list = new ArrayList<MetaManFile>();
		for (File f : workingDirectory.listFiles(new AudioFileFilter())) {
			list.add(new AudioFile(f.getAbsolutePath()));
		}
		this.cache = list;
		return list;

	}

	public List<MetaManFile> lsdo() {
		ArrayList<MetaManFile> list = new ArrayList<MetaManFile>();

		for (File f : workingDirectory.listFiles()) {
			if (workingDirectory.isDirectory())
				list.add(new MetaManDirectory(f.getAbsolutePath()));
		}
		return list;

	}
	
//	public boolean addToSelectedAudioFiles(List<AudioFile> toSelect){
//		return this.selectedAudioFiles.addAll(toSelect);
//	}
//	
//	public void clearSelectedAudioFiles(){
//		this.selectedAudioFiles.clear();
//	}

//	public List<AudioFile> modao(List<AudioFile> toMod, String key, String newValue) {
//		ArrayList<AudioFile> toReturn = new ArrayList<AudioFile>();
//		for (int i = 0; i < toMod.size(); i++) {
//				toMod.get(i).setMetaData(key, newValue);
//				toReturn.add(toMod.get(i));
//		}
//		return toReturn;
//	}

	private final File HOME_DIR = new File(System.getProperty("user.home"));
	private File workingDirectory = HOME_DIR;
	private AudioFile selectedAudioFile;
	private List<MetaManFile> cache; 
	
	//private List<AudioFile> selectedAudioFiles;
	public void setSelectedAudioFile(int i) {
		this.selectedAudioFile = (AudioFile) this.cache.get(i);
	}

	public AudioFile getSelectedAudioFile() {
		return this.selectedAudioFile;
	}

	public void openSelectedAudioFile() throws IOException {   
	    this.selectedAudioFile.open();
		
	}

	public boolean modSelectedFile(FieldKey key, String newValue) {
		this.selectedAudioFile.setMetaData(key, newValue);
		return true;
	}

}
