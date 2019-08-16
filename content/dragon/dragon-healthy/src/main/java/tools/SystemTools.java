package tools;

import org.apache.log4j.Logger;
import tools.kit.PathKit;


public class SystemTools {

	private static final long serialVersionUID = 6761767368352810428L;
	
	private static final String webRootPath= PathKit.getWebRootPath();
	private static final String rootClassPath=PathKit.getWebRootPath();
	
	private static final String xmlDataPath=webRootPath+"\\WEB-INF\\data\\xml\\";
	private static final String xmlDataBackupPath=webRootPath+"\\WEB-INF\\data\\Backup";
	private static final String imagesDataPath=webRootPath+"\\WEB-INF\\data\\images\\";
	private static final String videoDataPath=webRootPath+"\\WEB-INF\\data\\videos\\";
	private static final String dataPath=webRootPath+"\\WEB-INF\\data\\";
	private static Logger log = Logger.getLogger(SystemTools.class);

	/**
	 * @return the datapath
	 */
	public static String getDatapath() {
		return dataPath;
	}


	/**
	 * @return the web rootpath
	 */
	public static String getWebrootpath() {
		return webRootPath;
	}


	/**
	 * @return the root classpath
	 */
	public static String getRootclasspath() {
		return rootClassPath;
	}


	/**
	 * @return the xmldatapath
	 */
	public static String getXmldatapath() {
		return xmlDataPath;
	}


	/**
	 * @return the xmldatabackuppath
	 */
	public static String getXmldatabackuppath() {
		return xmlDataBackupPath;
	}


	/**
	 * @return the imagesdatapath
	 */
	public static String getImagesdatapath() {
		return imagesDataPath;
	}


	/**
	 * @return the videodatapath
	 */
	public static String getVideodatapath() {
		return videoDataPath;
	}


	/**
	 * @return the webRootPath
	 */
	public static String getWebRootPath() {
		return webRootPath;
	}


	/**
	 * @return the rootClassPath
	 */
	public static String getRootClassPath() {
		return rootClassPath;
	}


	/**
	 * @return the xmldatapath
	 */
	public static String getXmlDataPath() {
		return xmlDataPath;
	}


	public static String getXmlDataBackupPath() {
		return xmlDataBackupPath;
	}
	
}
