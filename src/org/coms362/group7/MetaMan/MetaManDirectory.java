package org.coms362.group7.MetaMan;

import org.jaudiotagger.tag.FieldKey;


public class MetaManDirectory extends MetaManFile {

	public MetaManDirectory(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String view() {
		return null;
		//throw new UnsupoortedOperation();
	}

	@Override
	public boolean setMetaData(FieldKey key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMetaData(FieldKey key) {
		// TODO Auto-generated method stub
		return null;
	}

}
