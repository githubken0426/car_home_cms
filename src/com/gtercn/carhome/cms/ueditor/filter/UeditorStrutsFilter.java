package com.gtercn.carhome.cms.ueditor.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.gtercn.carhome.cms.ApplicationConfig;

/**
 * ueditor上传图片会被StrutsPrepareAndExecuteFilter拦截
 * 所以需重新定义filter
 * 
 * @author ken
 * 2017-2-24 上午10:27:36
 */
public class UeditorStrutsFilter extends StrutsPrepareAndExecuteFilter  {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;     
		String url = request.getRequestURI();
		if(ApplicationConfig.UEDITOR_FILTER_PATH.equals(url))
			chain.doFilter(request, res);
		else
			super.doFilter(req, res, chain);
	}
}
