import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MetaMan {

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

	public List<File> ls() throws CorruptedFileException {
		ArrayList<File> list = new ArrayList<File>();
		list.addAll(lsao());
		list.addAll(lsdo());
		return list;
	}

	public List<AudioFile> lsao() throws CorruptedFileException {
		ArrayList<AudioFile> list = new ArrayList<AudioFile>();

		for (File f : workingDirectory.listFiles(new OnlyExt("mp3"))) {
			list.add(new AudioFile(f.getAbsolutePath()));
		}
		return list;

	}

	public List<File> lsdo() {
		ArrayList<File> list = new ArrayList<File>();

		for (File f : workingDirectory.listFiles()) {
			if (workingDirectory.isDirectory())
				list.add(new File(f.getAbsolutePath()));
		}
		return list;

	}

	public List<AudioFile> modao(List<AudioFile> toMod, String key, int start, int end, String newValue)
			throws CorruptedFileException {
		ArrayList<AudioFile> toReturn = new ArrayList<AudioFile>();
		for (int i = 0; i < toMod.size(); i++) {
			if (i >= start && i <= end) {
				toMod.get(i).setMetaData(key, newValue);
				toMod.get(i).saveMetaData();
				toReturn.add(toMod.get(i));
			}
		}
		return toReturn;
	}

	private class OnlyExt implements FilenameFilter {
		String ext;

		public OnlyExt(String ext) {
			this.ext = "." + ext;
		}

		public boolean accept(File dir, String name) {
			return name.endsWith(ext);
		}
	}

	private final File HOME_DIR = new File(System.getProperty("user.home"));
	private File workingDirectory = HOME_DIR;

}
