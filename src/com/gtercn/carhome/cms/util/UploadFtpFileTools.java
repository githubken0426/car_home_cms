package com.gtercn.carhome.cms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gtercn.carhome.cms.ApplicationConfig;

/**
 * ftp上传文件
 * 
 * @author Administrator 2016-7-29 上午08:43:40
 */
public class UploadFtpFileTools {
	static String ip = ApplicationConfig.FTP_IP;
	static int port = ApplicationConfig.FTP_PORT;
	static String userName = ApplicationConfig.FTP_USERNAME;
	static String passWord = ApplicationConfig.FTP_PASSWORD;

	public static boolean uploadFile(String[] paths, String filename,InputStream input)  throws IOException {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ip, port);
			ftp.login(userName, passWord);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			for (String path : paths) {
				ftp.makeDirectory(path);
				ftp.changeWorkingDirectory(path);
			}
			ftp.setBufferSize(1024);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.setControlEncoding("UTF-8");
			filename= new String(filename.getBytes("UTF-8"),"iso-8859-1");
			boolean result = ftp.storeFile(filename, input);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
			if (ftp != null) {
				ftp.logout();
			}
			if (ftp.isConnected()) {
				ftp.disconnect();
			}
		}
		return false;
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param url
	 * @param filename
	 * @return 2017-2-22 下午03:29:39
	 */
	public static String downloadFile(HttpServletRequest request, String url,
			String filename) {
		FTPClient ftp = new FTPClient();
		FileOutputStream fos = null;
		String xturl = null;
		try {
			ftp.connect(ip, port);
			ftp.login(userName, passWord);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			ftp.changeWorkingDirectory(url);
			String remoteFileName = filename;
			String webRoot = System.getProperty("wxconsole.root");
			xturl = webRoot + "ftpimgtemp" + File.separator + filename;
			fos = new FileOutputStream(xturl);
			ftp.setFileType(2);
			ftp.retrieveFile(remoteFileName, fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();

			if (ftp.isConnected())
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					throw new RuntimeException("关闭FTP连接发生异常！", ioe);
				}
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					throw new RuntimeException("关闭FTP连接发生异常！", ioe);
				}
			}
		}

		return xturl;
	}

	public static InputStream getFtpInputStream(String remotePath,
			String fileName) {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ip, port);
			ftp.login(userName, passWord);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			ftp.changeWorkingDirectory(remotePath);
			ftp.setFileType(2);
			return ftp.retrieveFileStream(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					throw new RuntimeException("关闭FTP连接发生异常！", ioe);
				}
			}
			return null;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					throw new RuntimeException("关闭FTP连接发生异常！", ioe);
				}
			}
		}
	}

	/**
	 * 删除文件
	 */
	public static boolean deleteFile(String filePath) {
		boolean flag = false;
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ip, port);
			ftp.login(userName, passWord);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			flag = ftp.deleteFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 保存文件，并返回多个文件路径
	 * 
	 * @param savePath
	 *            保存的路径
	 * @param multipartRequest
	 * @return 并返回多个文件路径,逗号隔开
	 * @throws IOException
	 *             2016-12-16 下午02:05:08
	 * @throws ApiException
	 */
	public static String saveFileAndGetUrl(String[] savePath,
			MultipartHttpServletRequest multipartRequest) throws IOException,
			Exception {
		StringBuffer sb=new StringBuffer();
		String path="";
		for (String p : savePath) {
			path+=File.separator + p;
		}
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		Iterator<String> it = map.keySet().iterator();
		boolean hasNext=it.hasNext();
		while (hasNext) {
			String key = it.next();
			MultipartFile file = map.get(key);
			String filename = file.getOriginalFilename();
			String saveFileName = String.valueOf(System.currentTimeMillis())
					+ filename.substring(filename.lastIndexOf("."));
			// 上传到ftp
			boolean bool = uploadFile(savePath, saveFileName, file.getInputStream());
			if (!bool) {
				throw new Exception();
			}
			sb.append(path+File.separator + saveFileName);
			hasNext=it.hasNext();
			if(hasNext)
				sb.append(",");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String newName = System.currentTimeMillis() + ".gif";
		try {
			File file2 = new File(
					"C:\\Users\\Administrator\\Desktop\\顺驾天下\\img\\180.JPEG");
			FileInputStream in = new FileInputStream(file2);
			boolean flag = uploadFile(new String[] { "2016", "04", "20" },
					newName, in);
			System.out.println(flag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}