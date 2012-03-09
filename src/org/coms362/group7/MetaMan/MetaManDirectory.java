package org.coms362.group7.MetaMan;


import org.jaudiotagger.tag.FieldKey;


/**
 * 
 * @author COM S 362 Group 7
 * 
 */
public class MetaManDirectory extends MetaManFile {

	/**
	 * Needed for proper inheritance from parent Class MetaManFile
	 */
	private static final long serialVersionUID = 6040146303193263835L;


	/**
	 * 
	 * @param pathname
	 */
	public MetaManDirectory(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String getMetaData(FieldKey key) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public boolean setMetaDataHelper(FieldKey key, String value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see MetaManFile Documentation
	 */
	@Override
	public String view() {
		throw new UnsupportedOperationException();
	}

}
