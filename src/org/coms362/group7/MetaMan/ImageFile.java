package org.coms362.group7.MetaMan;

import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;

/**
 * An ImageFile, extended from MetaManFile, file is any File that can fit
 * through ImageFileFilter
 * 
 * @see ImageFileFilter
 * @see MetaManFile
 * @author COM S 363 Group 7
 * 
 */
public class ImageFile extends MetaManFile {

	/**
	 * Needed for proper inheritance from parent class MetaManFile
	 */
	private static final long serialVersionUID = -8191065022586621996L;

	/**
	 * The meta data of 'this' image file. NOTE: This is taken from an external
	 * library called Metadata-Extractor
	 * 
	 * @see http://code.google.com/p/metadata-extractor/
	 */
	private com.drew.metadata.Metadata metaData;

	/**
	 * Constructs a new ImageFile
	 * 
	 * @param pathname
	 *            The file path of the audio file to be constructed
	 * @throws MetaManException
	 *             If the file could not be constructed properly.
	 */
	public ImageFile(String pathname) throws MetaManException {
		super(pathname);
		try {
			this.metaData = ImageMetadataReader.readMetadata(this);
		} catch (final ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String getMetaData(String key) {
		try {
			return this.metaData.getDirectory(ExifIFD0Directory.class)
					.getDate(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION)
					.toString();
		} catch (final NullPointerException e) {
			return "N/A";
		}

	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	protected boolean setMetaDataHelper(String key, String value) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String view() {
		String toReturn = "";
		for (final Directory directory : this.metaData.getDirectories()) {
			for (final Tag tag : directory.getTags()) {
				toReturn = toReturn + tag + "\n";
			}
		}
		return toReturn;
	}

}
