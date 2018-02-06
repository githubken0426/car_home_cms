package com.gtercn.carhome.cms.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.annotation.PermissionException;

/**
 * dom解析html
 * 
 * @author ken 2017-2-27 下午01:48:48
 */
public final class GeneratorHtmlToFtp {
	
	/**
	 * 读取模板
	 * @param templatePath
	 * @param content
	 * @return
	 * @throws IOException
	 * 2017-2-27 下午05:30:31
	 */
	public static File generatorHtml(String templatePath,String targetPath,String content)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader=null;
		BufferedWriter write=null;
		FileInputStream input =null;
		try {
			input = new FileInputStream(templatePath);
			reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
			String tempStr = "";
			while ((tempStr = reader.readLine()) != null){
				sb.append(tempStr+"\n");
				if(tempStr.endsWith("shouhoubaozhang'>"))
					sb.append(content+"\n");
			}
			File target = new File(targetPath);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(target),"UTF-8");
			write = new BufferedWriter(out);
			write.write(sb.toString());
			write.flush();
			File file=new File(targetPath);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(write!=null)
				write.close();
			if(reader!=null)
				reader.close();
			if(input!=null)
				input.close();
		}
	}
	

	/**
	 * 生成html上传至ftp 返回路径
	 * 
	 * @param path
	 * @param content
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 * @throws PermissionException
	 * @throws Exception
	 *             2017-2-27 下午02:36:22
	 */
	public static String uploadHtmlToFtp(String serverPath,String []ftpPaths, String content) throws Exception {
		StringBuffer sb = new StringBuffer();
		FileInputStream input=null;
		try {
			String fileName = System.currentTimeMillis() + ".html";
			String targetnPath=serverPath+ File.separator +fileName;//生成新文件存储路径
			String templatePath=serverPath + File.separator + "template.html";//模板路径
			
			File sourceFile = generatorHtml(templatePath,targetnPath, content);
			input = new FileInputStream(sourceFile);
			UploadFtpFileTools.uploadFile(ftpPaths, fileName, input);
			
			List<String> result = Arrays.asList(ftpPaths);
			Iterator<String> it = result.iterator();
			sb.append(File.separator);
			while (it.hasNext()) {
				String val = it.next();
				sb.append(val+File.separator);
			}
			sb.append(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(input!=null)
				input.close();
		}
		return sb.toString();
	}

	public static File test(String targetPath,String content) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedWriter write=null;
		sb.append("<!DOCTYPE html PUBLIC \""
				+ "-//W3C//DTD HTML 4.01 Transitional//EN\" " + "\""
				+ "http://www.w3.org/TR/html4/loose.dtd" + "\">\n");
		sb.append("<html xmlns=\"" + "http://www.w3.org/1999/xhtml" + "\">\n");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"" + "Content-Type\"" + " content=\""
				+ "text/html;charset=utf-8\">\n");
		sb .append("<meta name=\""
						+ "viewport\""
						+ " content=\""
						+ "user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0, target-densitydpi=device-dpi\">\n");
		sb.append("<title>达人圈</title>\n");
		sb.append("<link rel=\"" + "stylesheet\"" + " href=\""
				+ "../css/style02.css\"/>\n");
		sb.append("<script type=\"" + "text/javascript\"" + " src=\""
				+ "../js/jquery-1.10.1.min.js\"/>\n");
		sb .append("<script>(function () {document.addEventListener('DOMContentLoaded', function () {\n");
		sb.append("var html = document.documentElement;\n");
		sb.append("var windowWidth = html.clientWidth;\n");
		sb.append("html.style.fontSize = windowWidth / 6.4 + 'px';\n");
		sb.append("}, false);\n");
		sb.append("})();\n");
		sb.append("</head>\n");

		sb.append("<body style=\"" + "margin: 0px;padding: 0px;\">\n");
		sb.append("<div class=\"" + "shouhoubaozhang\">\n");
		//追加内容
		sb.append(content+"\n");
		
		sb.append("</div>\n");
		sb.append("</body>\n");
		sb.append("</html>");
		File target = new File(targetPath);
		write = new BufferedWriter(new FileWriter(target));
		write.write(sb.toString());
		File file=new File(targetPath);
		return file;
	}
	public static void main(String[] args) throws PermissionException, Exception {
		try {
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,
					ApplicationConfig.FTP_ARTICLE_PATH,
					ApplicationConfig.FTP_HTML_PATH,"userId" };
			String path=uploadHtmlToFtp("D:\\",ftpPaths,"统治意象鲜明的商品如公仔、文具等下架停售，并停止发放相关文宣、停播开闭馆");
			System.out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
