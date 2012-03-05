import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MetaMan {

	public boolean cd(String dir) {
		String oldValue = wd.getAbsolutePath();
		wd = new File(wd + "\\" + dir);
		if (wd.exists())
			return true;
		else {
			wd = new File(oldValue);
			return false;
		}
	}

	public boolean up() {
		if (wd.getParentFile() == null)
			return false;
		wd = wd.getParentFile();
		return true;

	}

	public String pwd() {
		return wd.getAbsolutePath();
	}

	public List<File> ls() throws CorruptedFileException {
		ArrayList<File> list = new ArrayList<File>();
		list.addAll(lsao());
		list.addAll(lsdo());
		return list;
	}

	public List<AudioFile> lsao() throws CorruptedFileException {
		ArrayList<AudioFile> list = new ArrayList<AudioFile>();

		for (File f : wd.listFiles(new OnlyExt("mp3"))) {
			list.add(new AudioFile(f.getAbsolutePath()));
		}
		return list;

	}

	public List<File> lsdo() {
		ArrayList<File> list = new ArrayList<File>();

		for (File f : wd.listFiles()) {
			if (wd.isDirectory())
				list.add(new File(f.getAbsolutePath()));
		}
		return list;

	}

	public List<AudioFile> mod(String key, int start, int end, String newValue)
			throws CorruptedFileException {
		ArrayList<AudioFile> list = (ArrayList<AudioFile>) lsao();
		ArrayList<AudioFile> toReturn = new ArrayList<AudioFile>();
		for (int i = 0; i < list.size(); i++) {
			if (i >= start && i <= end) {
				list.get(i).set(key, newValue);
				list.get(i).save();
				toReturn.add(list.get(i));
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
	private File wd = HOME_DIR;

}
