package com.gtercn.carhome.cms.ueditor.uploadftp;

import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.Base64Uploader;
import com.baidu.ueditor.upload.BinaryUploader;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 反编译ueditor
 * 通过配置文件配置是上传本地服务器还是远程FTP服务器
 * 
 * @author ken
 * 2017-2-24 上午11:14:43
 */
public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;
		// 保留原有逻辑,在json.config中加入是否使用FTP上传配置项
		if ("true".equals(this.conf.get("isBase64")))
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		else {
			if ("true".equals(this.conf.get("useFtpUpload")))
				state = FtpUploader.save(request, conf);
			else
				state = BinaryUploader.save(this.request, this.conf);
		}

		return state;
	}
}
