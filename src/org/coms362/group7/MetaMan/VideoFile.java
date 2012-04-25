package org.coms362.group7.MetaMan;

import java.util.ArrayList;

public class VideoFile extends MetaManFile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5899832089085175252L;

	public VideoFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMetaData(String tag) throws MetaManException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean renameByMetaData() throws MetaManException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean setMetaDataHelper(String tag, String value)
			throws MetaManException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String view() throws MetaManException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ArrayList<String> viewNullTags() throws MetaManException {
		throw new UnsupportedOperationException();
	}

}
