package org.coms362.group7.MetaMan;

import java.io.IOException;

import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.ExifTagConstants;

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
	 * The image info of 'this' image file. NOTE: This is taken from an external
	 * library called Metadata-Extractor
	 * 
	 * @see http://code.google.com/p/metadata-extractor/
	 */
	private ImageInfo imageInfo;

	/**
	 * The meta data of 'this' image file. NOTE: This is taken from an external
	 * library called Metadata-Extractor
	 * 
	 * @see http://code.google.com/p/metadata-extractor/
	 */
	private JpegImageMetadata metaData;

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
			this.imageInfo = Sanselan.getImageInfo(this);
			this.metaData = (JpegImageMetadata) Sanselan.getMetadata(this);
			// this.metaData = Sanselan.getMetadata(bytes);
		} catch (final ImageReadException e) {
			throw new MetaManException(e.getMessage());
		} catch (final IOException e) {
			throw new MetaManException(e.getMessage());
		}
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String getMetaData(String key) {
		if (key.equals("HEIGHT")) {
			return this.imageInfo.getHeight() + "";
		} else if (key.equals("WIDTH")) {
			return this.imageInfo.getWidth() + "";
		} else if (key.equals("DATE")) {
			final TiffField field = this.metaData
					.findEXIFValue(ExifTagConstants.EXIF_TAG_CREATE_DATE);
			if (field != null) {
				return field
						.getValueDescription()
						.toString()
						.substring(
								1,
								field.getValueDescription().toString().length() - 1);
			}
		}
		return "N/A";
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	protected boolean setMetaDataHelper(String key, String value) {
		throw new UnsupportedOperationException(
				"Setting the MetaData of an Image file is not Supported Yet");
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String view() {
		String retVal = "";
		final String height = this.getMetaData("HEIGHT");
		final String width = this.getMetaData("WIDTH");
		final String date = this.getMetaData("DATE");
		retVal += "*****************************\n";
		retVal += "WIDTH: " + width + "\n";
		retVal += "HEIGHT: " + height + "\n";
		retVal += "DATE: " + date + "\n";
		retVal += "*****************************\n";

		return retVal;
	}

}
