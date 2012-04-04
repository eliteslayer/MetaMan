package org.coms362.group7.MetaMan;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.jpeg.exifRewrite.ExifRewriter;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.apache.sanselan.formats.tiff.constants.ExifTagConstants;
import org.apache.sanselan.formats.tiff.constants.GPSTagConstants;
import org.apache.sanselan.formats.tiff.write.TiffOutputDirectory;
import org.apache.sanselan.formats.tiff.write.TiffOutputField;
import org.apache.sanselan.formats.tiff.write.TiffOutputSet;

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
		try {
			if (key.equals("HEIGHT")) {
				return this.imageInfo.getHeight() + "";
			} else if (key.equals("WIDTH")) {
				return this.imageInfo.getWidth() + "";
			} else if (key.equals("LAT")) {

				final TiffField field = this.metaData
						.findEXIFValue(GPSTagConstants.GPS_TAG_GPS_LATITUDE);
				if (field != null) {
					return field.toString();
				}
			} else if (key.equals("LONG")) {

				final TiffField field = this.metaData
						.findEXIFValue(GPSTagConstants.GPS_TAG_GPS_LONGITUDE);
				if (field != null) {
					return field.toString();
				}
			} else if (key.equals("NAME")) {
				return this.getName();
			} else if (key.equals("DATE")) {
				final TiffField field = this.metaData
						.findEXIFValue(ExifTagConstants.EXIF_TAG_CREATE_DATE);
				if (field != null) {
					return field
							.getValueDescription()
							.toString()
							.substring(
									1,
									field.getValueDescription().toString()
											.length() - 1);
				}
			}
		}

		catch (final NullPointerException e) {
			return "N/A";
		}

		return "N/A";
	}

	private void setGPS(double longitude, double latitude) throws IOException,
			ImageReadException, ImageWriteException {
		OutputStream os = null;
		try {
			TiffOutputSet outputSet = null;

			// note that metadata might be null if no metadata is found.
			final IImageMetadata metadata = Sanselan.getMetadata(this);
			final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
			if (null != jpegMetadata) {
				// note that exif might be null if no Exif metadata is found.
				final TiffImageMetadata exif = jpegMetadata.getExif();

				if (null != exif) {
					// TiffImageMetadata class is immutable (read-only).
					// TiffOutputSet class represents the Exif data to write.
					//
					// Usually, we want to update existing Exif metadata by
					// changing
					// the values of a few fields, or adding a field.
					// In these cases, it is easiest to use getOutputSet() to
					// start with a "copy" of the fields read from the image.
					try {
						outputSet = exif.getOutputSet();
					} catch (final ImageWriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			// if file does not contain any exif metadata, we create an empty
			// set of exif metadata. Otherwise, we keep all of the other
			// existing tags.
			if (null == outputSet) {
				outputSet = new TiffOutputSet();
			}

			{
				// Example of how to add a field/tag to the output set.
				//
				// Note that you should first remove the field/tag if it already
				// exists in this directory, or you may end up with duplicate
				// tags. See above.
				//
				// Certain fields/tags are expected in certain Exif directories;
				// Others can occur in more than one directory (and often have a
				// different meaning in different directories).
				//
				// TagInfo constants often contain a description of what
				// directories are associated with a given tag.
				//
				// see
				// org.apache.sanselan.formats.tiff.constants.AllTagConstants
				//
				final TiffOutputField aperture = TiffOutputField.create(
						ExifTagConstants.EXIF_TAG_APERTURE_VALUE,
						outputSet.byteOrder, new Double(0.3));
				final TiffOutputDirectory exifDirectory = outputSet
						.getOrCreateExifDirectory();
				// make sure to remove old value if present (this method will
				// not fail if the tag does not exist).
				exifDirectory
						.removeField(ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
				exifDirectory.add(aperture);

			}

			{
				// Example of how to add/update GPS info to output set.

				// New York City
				// double longitude = -74.0; // 74 degrees W (in Degrees East)
				// double latitude = 40 + 43 / 60.0; // 40 degrees N (in Degrees
				// North)
				outputSet.setGPSInDegrees(longitude, latitude);

			}

			// printTagValue(jpegMetadata, TiffConstants.TIFF_TAG_DATE_TIME);

			os = new FileOutputStream(this);
			os = new BufferedOutputStream(os);

			new ExifRewriter().updateExifMetadataLossless(this, os, outputSet);

			os.close();
			os = null;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (final IOException e) {

				}
			}
		}
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	protected boolean setMetaDataHelper(String key, String value) {
		if (key.equals("LAT")) {
			try {
				this.setGPS(Double.parseDouble(this.getMetaData("LONG")),
						Double.parseDouble(value.split(",")[0]));
			} catch (final NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final ImageReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final ImageWriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (key.equals("LONG")) {
			try {
				this.setGPS(Double.parseDouble(value.split(",")[1]),
						Double.parseDouble(this.getMetaData("LAT")));
			} catch (final NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final ImageReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final ImageWriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
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
		final String latitude = this.getMetaData("LAT");
		final String longitude = this.getMetaData("LONG");
		final String name = this.getName();
		retVal += "*****************************\n";
		retVal += "NAME: " + name + "\n";
		retVal += "WIDTH: " + width + "\n";
		retVal += "HEIGHT: " + height + "\n";
		retVal += "DATE: " + date + "\n";
		retVal += "LATITUDE: " + latitude + "\n";
		retVal += "LONGITUDE: " + longitude + "\n";
		retVal += "*****************************\n";

		return retVal;
	}

}
