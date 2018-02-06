package com.gtercn.carhome.cms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.Promotion;
import com.gtercn.carhome.cms.entity.Shop;
import com.gtercn.carhome.cms.service.promotion.PromotionService;
import com.gtercn.carhome.cms.service.shop.ShopService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.GeneratorHtmlToFtp;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 促销
 * 
 * @author ken 2017-2-23 下午03:39:05
 */
public class PromotionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private ShopService shopService;

	private Promotion promotion;
	
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	/**
	 * 检索数据
	 * 
	 * @return
	 */
	public String listData() {
		Map<String, Object> map = new HashMap<String, Object>();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> session=ActionContext.getContext().getSession();
		try {
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode = user.getCityCode();
			cityCode = StringUtils.isNotBlank(cityCode) ? cityCode : ApplicationConfig.DEFAULT_CITY_CODE;
			map.put("cityCode", cityCode);
			String deleteFlag = request.getParameter("deleteFlag");
			if (deleteFlag != null && !deleteFlag.equals("-1")) {
				map.put("deleteFlag", deleteFlag);
			}
			String shopName = request.getParameter("shopName");
			if (shopName != null && !shopName.equals("")) {
				shopName = URLDecoder.decode(shopName, "UTF-8");
				map.put("shopName", shopName);
			}
			String beginTime = request.getParameter("beginTime");
			if (beginTime != null && !beginTime.equals(""))
				map.put("beginTime", beginTime);
			String endTime = request.getParameter("endTime");
			if (endTime != null && !endTime.equals(""))
				map.put("endTime", endTime);
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = promotionService.getTotalCount(map);
			int currentIndex = 0;// 当前页
			String index = request.getParameter("pno");
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			int totalPages = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			map.put("beginResult", (currentIndex - 1) * pageSize);
			map.put("pageSize", pageSize);
			List<Promotion> list = promotionService.queryAllData(map);

			context.put("list", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("deleteFlag", deleteFlag);
			context.put("shopName", shopName);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "list";
	}

	/**
	 * 添加数据(进入画面)
	 * 
	 * @return
	 */
	public String addDataPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String deleteFlag = request.getParameter("deleteFlag");
			String shopName = request.getParameter("shopName");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			context.put("currentIndex", currentIndex);
			context.put("deleteFlag", deleteFlag);
			context.put("shopName", shopName);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "add";
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String addData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream viewIn =null;
		InputStream backIn = null;
		try {
			Random random=new Random();
			writer = response.getWriter();
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String uuid = CommonUtil.getUID();
			String content=promotion.getContent();
			promotion.setId(uuid);
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode = user.getCityCode();
			cityCode = StringUtils.isNotBlank(cityCode) ? cityCode : ApplicationConfig.DEFAULT_CITY_CODE;
			promotion.setCityCode(cityCode);
			
			String serverPath =  request.getSession().getServletContext().getRealPath("/")
					+ "resources" ;
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,ApplicationConfig.FTP_HTML_PATH,
					ApplicationConfig.FTP_PROMOTION_PATH, uuid };
			String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
			promotion.setHtmlUrl(htmlPath);
			// 上传展示图片
			File[] pictureList = multipartRequest.getFiles("pictureList");
			if (pictureList != null) {
				for (File file : pictureList) {
					viewIn = new FileInputStream(file);
					String fileName = random.nextInt(100)+ System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths, fileName, viewIn);
					if (bool) {
						String dbPictureList = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_PROMOTION_PATH
								+ File.separator + uuid + File.separator + fileName;
						promotion.setPictureList(dbPictureList);
					}
				}
			}
			//背景图片
			File[] backgroundImage = multipartRequest.getFiles("backgroundImage");
			if (backgroundImage != null) {
				for (File file : backgroundImage) {
					backIn = new FileInputStream(file);
					String fileName = System.currentTimeMillis()+ random.nextInt(100) + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,
							fileName, backIn);
					if (bool) {
						String dbBackgroundImage = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_PROMOTION_PATH
								+ File.separator + uuid + File.separator
								+ fileName;
						promotion.setBackgroundImage(dbBackgroundImage);
					}
				}
			}
			promotionService.insert(promotion);
			writer.print("<script>alert('添加成功!');window.location.href='promotion_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='promotion_listData.action';</script>");
		}finally{
			if(viewIn !=null)
				viewIn.close();
			if(backIn !=null)
				backIn.close();
		}
		return null;
	}

	
	/**
	 * 修改数据(进入画面)
	 * 
	 * @return
	 */
	public String updateDataPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String id = request.getParameter("id");
			String deleteFlag = request.getParameter("deleteFlag");
			String shopName = request.getParameter("shopName");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			
			Promotion promotion = promotionService.selectByPrimaryKey(id);
			context.put("promotion", promotion);
			context.put("currentIndex", currentIndex);
			context.put("deleteFlag", deleteFlag);
			context.put("shopName", shopName);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
			context.put("ftpServerIp", ApplicationConfig.HTTP_PROTOCOL_IP);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "update";
	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public String updateData() throws Exception{
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream viewIn =null;
		InputStream backIn =null;
		try {
			Random random=new Random();
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			writer = response.getWriter();
			String id=promotion.getId();
			String content=promotion.getContent();
			Promotion dbpromotion=promotionService.selectByPrimaryKey(id);			
			String dbContent=(dbpromotion!=null) ? dbpromotion.getContent():"";
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,ApplicationConfig.FTP_HTML_PATH,
					ApplicationConfig.FTP_PROMOTION_PATH ,id };
			if(StringUtils.isNotBlank(dbContent) && !dbContent.equals(content)){
				String serverPath =  request.getSession().getServletContext().getRealPath("/") + "resources" ;
				String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
				promotion.setHtmlUrl(htmlPath);
			}else{
				String dbHtmlPath=(dbpromotion!=null) ? dbpromotion.getHtmlUrl():"";
				promotion.setHtmlUrl(dbHtmlPath);
			}
			// 上传展示图片
			String pictureListFlag=request.getParameter("pictureListFlag");
			if(StringUtils.isNotBlank(pictureListFlag))
				promotion.setPictureList(pictureListFlag);
			File[] pictureList = multipartRequest.getFiles("pictureList");
			if (pictureList != null) {
				for (File file : pictureList) {
					viewIn = new FileInputStream(file);
					String fileName = random.nextInt(100)+System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,fileName, viewIn);
					if (bool) {
						String dbPictureList = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_PROMOTION_PATH
								+ File.separator + id + File.separator + fileName;
						promotion.setPictureList(dbPictureList);
					}
				}
			}
			//背景图片
			String backgroundImageFlag=request.getParameter("backgroundImageFlag");
			if(StringUtils.isNotBlank(backgroundImageFlag))
				promotion.setBackgroundImage(backgroundImageFlag);
			File[] backgroundImage = multipartRequest.getFiles("backgroundImage");
			if (backgroundImage != null) {
				for (File file : backgroundImage) {
					backIn = new FileInputStream(file);
					String fileName = System.currentTimeMillis()+random.nextInt(100) + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,fileName, backIn);
					if (bool) {
						String dbBackgroundImage = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_PROMOTION_PATH
								+ File.separator + id + File.separator + fileName;
						promotion.setBackgroundImage(dbBackgroundImage);
					}
				}
			}
			promotionService.updateByPrimaryKey(promotion);
			writer.print("<script>alert('修改成功!');window.location.href='promotion_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='promotion_listData.action';</script>");
		}finally{
			if(viewIn !=null)
				viewIn.close();
			if(backIn !=null)
				backIn.close();
		}
		return null;
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	public String deleteData() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String ids[] = request.getParameterValues("id");
			promotionService.deleteBatch(ids);
			writer.print("<script>alert('删除成功!');window.location.href='promotion_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='promotion_listData.action';</script>");
		}
		return null;
	}
	
	public String getShopByName(){
		Map<String, Object> map = new HashMap<String, Object>();
		ServletResponse response = ServletActionContext.getResponse();
		ServletRequest request=ServletActionContext.getRequest();
		Map<String,Object> session=ActionContext.getContext().getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode = user.getCityCode();
			cityCode = StringUtils.isNotBlank(cityCode) ? cityCode : ApplicationConfig.DEFAULT_CITY_CODE;
			String shopName = request.getParameter("shopName");
			String id = request.getParameter("id");
			map.put("shopName", shopName);
			map.put("id", id);
			map.put("cityCode", cityCode);
			Shop shop=shopService.queryShopByName(map);
			JSONObject json=JSONObject.fromObject(shop);
			writer.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		} 
		return null;
	}

}
