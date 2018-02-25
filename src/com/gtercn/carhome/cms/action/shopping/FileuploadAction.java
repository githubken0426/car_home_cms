package com.gtercn.carhome.cms.action.shopping;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品
 * 
 * @author ken 2017-2-23 下午03:39:05
 */
public class FileuploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private File multiFile;
	
	public File getMultiFile() {
		return multiFile;
	}
	public void setMultiFile(File multiFile) {
		this.multiFile = multiFile;
	}

	/**
	 * 上传商品图
	 * 
	 * @return
	 * @throws Exception
	 */
	public void upload() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		/*// 1.创建DiskFileItemFactory对象，配置缓存用
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2. 创建 ServletFileUpload对象
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3. 设置文件名称编码
		servletFileUpload.setHeaderEncoding("utf-8");
		List<FileItem> items = servletFileUpload.parseRequest(request);
		// 1. 获取文件名称
		FileItem file = items.get(i);*/
		try {
			String root = ApplicationConfig.FTP_SHOPPING_PATH;
			String goods = ApplicationConfig.FTP_GOODS_PATH;
			String picType = ApplicationConfig.FTP_SMALL_PATH;
			String type=request.getParameter("picType");
			if("1".equals(type))
				picType = ApplicationConfig.FTP_SMALL_PATH;
			else if("2".equals(type))
				picType = ApplicationConfig.FTP_BIG_PATH;
			else
				picType = ApplicationConfig.FTP_DETAIL_PATH;
			String ftpPaths[] = { root, goods, picType };
			File file = this.getMultiFile();
			// 2. 获取文件的实际内容
			InputStream input = new BufferedInputStream(new FileInputStream(file));
			// 3. 保存文件
			// FileUtils.copyInputStreamToFile(input, new File(serverPath + "/" + name));
			String newName = System.currentTimeMillis() + ".jpg";
			boolean bool = UploadFtpFileTools.uploadFile(ftpPaths, newName, input);
			if (bool) {
				String resultPath = ApplicationConfig.HTTP_PROTOCOL_IP + File.separator + root + File.separator
						+ goods + File.separator + picType + File.separator + newName;
				response.getWriter().println(resultPath);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error");
		}
	}
}
