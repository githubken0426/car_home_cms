package com.gtercn.carhome.cms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.City;
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.Rescue;
import com.gtercn.carhome.cms.entity.Shop;
import com.gtercn.carhome.cms.service.city.CityService;
import com.gtercn.carhome.cms.service.rescue.RescueService;
import com.gtercn.carhome.cms.service.shop.ShopService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class ShopAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ShopService shopService;
	@Autowired
	private RescueService rescueService;
	@Autowired
	private CityService cityService;

	private Shop entity;

	private JSONArray json;
	
	private File myFile;
	// myFileContentType属性用来封装上传文件的类型  
    private String myFileContentType;  
    // myFileFileName属性用来封装上传文件的文件名  
    private String myFileFileName;
    
    private File displayPicUrl1;
    
    private File displayPicUrl2;
    
    private File displayPicUrl3;
    
    private File displayPicUrl4;
    
    private File displayPicUrl5;
    
    private String displayPicUrl1FileName;
    
    private String displayPicUrl2FileName;
    
    private String displayPicUrl3FileName;
    
    private String displayPicUrl4FileName;
    
    private String displayPicUrl5FileName;

	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}

	public Shop getEntity() {
		return entity;
	}

	public void setEntity(Shop entity) {
		this.entity = entity;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public File getDisplayPicUrl1() {
		return displayPicUrl1;
	}

	public void setDisplayPicUrl1(File displayPicUrl1) {
		this.displayPicUrl1 = displayPicUrl1;
	}

	public File getDisplayPicUrl2() {
		return displayPicUrl2;
	}

	public void setDisplayPicUrl2(File displayPicUrl2) {
		this.displayPicUrl2 = displayPicUrl2;
	}

	public File getDisplayPicUrl3() {
		return displayPicUrl3;
	}

	public void setDisplayPicUrl3(File displayPicUrl3) {
		this.displayPicUrl3 = displayPicUrl3;
	}

	public File getDisplayPicUrl4() {
		return displayPicUrl4;
	}

	public void setDisplayPicUrl4(File displayPicUrl4) {
		this.displayPicUrl4 = displayPicUrl4;
	}

	public File getDisplayPicUrl5() {
		return displayPicUrl5;
	}

	public void setDisplayPicUrl5(File displayPicUrl5) {
		this.displayPicUrl5 = displayPicUrl5;
	}

	public String getDisplayPicUrl1FileName() {
		return displayPicUrl1FileName;
	}

	public void setDisplayPicUrl1FileName(String displayPicUrl1FileName) {
		this.displayPicUrl1FileName = displayPicUrl1FileName;
	}

	public String getDisplayPicUrl2FileName() {
		return displayPicUrl2FileName;
	}

	public void setDisplayPicUrl2FileName(String displayPicUrl2FileName) {
		this.displayPicUrl2FileName = displayPicUrl2FileName;
	}

	public String getDisplayPicUrl3FileName() {
		return displayPicUrl3FileName;
	}

	public void setDisplayPicUrl3FileName(String displayPicUrl3FileName) {
		this.displayPicUrl3FileName = displayPicUrl3FileName;
	}

	public String getDisplayPicUrl4FileName() {
		return displayPicUrl4FileName;
	}

	public void setDisplayPicUrl4FileName(String displayPicUrl4FileName) {
		this.displayPicUrl4FileName = displayPicUrl4FileName;
	}

	public String getDisplayPicUrl5FileName() {
		return displayPicUrl5FileName;
	}

	public void setDisplayPicUrl5FileName(String displayPicUrl5FileName) {
		this.displayPicUrl5FileName = displayPicUrl5FileName;
	}
	
	private File uploadFile;
	private String uploadFileFileName;

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
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
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			String index = request.getParameter("pno");
			
			// 接收查询参数
			String shopName = request.getParameter("shopName");
			if (StringUtils.isNotBlank(shopName) && index != null && index != "") {
				shopName = new String(shopName.getBytes("iso8859-1"),"UTF-8");
			}
			map.put("shopName", shopName);
			
			// 区分服务类型
			String type = request.getParameter("type");
			if (StringUtils.equals("2000", type)) {
				map.put("rescueService", "2000");
			} else if (StringUtils.equals("4100", type)) {
				map.put("cleanService", "4100");
			} else if (StringUtils.equals("5100", type)) {
				map.put("repairService", "5100");
			} else if (StringUtils.equals("6100", type)) {
				map.put("maintainService", "6100");
			} else if (StringUtils.equals("7100", type)) {
				map.put("tyreService", "7100");
			}
			// 是否禁用
			String deleteFlag = request.getParameter("deleteFlag");
			if (StringUtils.isBlank(deleteFlag)) {
				deleteFlag = null;
			}
			map.put("deleteFlag", deleteFlag);
			
			DealerUser user = (DealerUser)session.get("dealer_user");
			map.put("cityCode", user.getCityCode());

			String detailAddress = request.getParameter("detailAddress");
			if (StringUtils.isNotBlank(detailAddress) && index != null) {
				detailAddress = new String(detailAddress.getBytes("iso8859-1"),"UTF-8");
			}
			map.put("detailAddress", detailAddress);

			String telNumber = request.getParameter("telNumber");
			map.put("telNumber", telNumber);


			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示10条
			int totalCount = shopService.getTotalCount(map);
			int currentIndex = 0;// 当前页
			
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			int totalPages = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			map.put("beginResult", (currentIndex - 1) * pageSize);
			map.put("pageSize", pageSize);
			List<Shop> list = shopService.queryAllData(map);
			
			context.put("ShopList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("shopName", shopName);
			context.put("detailAddress", detailAddress);
			context.put("telNumber", telNumber);
			context.put("type", type);
			context.put("deleteFlag", deleteFlag);

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
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			context.put("currentIndex", currentIndex);
			
			DealerUser user = (DealerUser)session.get("dealer_user");
			try {
				City city = cityService.getDataByCityCode(user.getCityCode());
				context.put("city", city.getCityName());
				context.put("cityId", user.getCityCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "add";
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String addData() throws IOException {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream file1 = null;
		InputStream file3 = null;
		InputStream file2 = null;
		InputStream file4 = null;
		InputStream file5 = null;
		try {
			String uuid = CommonUtil.getUID();
			entity.setId(uuid);
			entity.setDeleteFlag(0);
			
			String[] savePath = {ApplicationConfig.FTP_ROOTPATH, "shop", entity.getId(), "thumbnail"};
			if (myFile != null) {
				InputStream fileis = new FileInputStream(myFile);
				int s = myFileFileName.lastIndexOf(".");
		        myFileContentType = myFileFileName.substring(s + 1, myFileFileName.length());
		        String filename = System.currentTimeMillis() + "." + myFileContentType;
				UploadFtpFileTools.uploadFile(savePath, filename, fileis);
				entity.setShopPicUrl(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
						+ File.separator + "thumbnail" + File.separator + filename);
			}
			
			String repairService = request.getParameter("repairService");
			String cleanService = request.getParameter("cleanService");
			String maintainService = request.getParameter("maintainService");
			String tyreService = request.getParameter("tyreService");
			String[] rescueServiceArr = request.getParameterValues("rescueService");
			String experience = request.getParameter("experience");
			String productDescription = request.getParameter("productDescription");
			String shopDescription = request.getParameter("shopDescription");
			String cityId = request.getParameter("cityId");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("repairService", repairService);
			map.put("cleanService", cleanService);
			map.put("maintainService", maintainService);
			map.put("tyreService", tyreService);
			map.put("experience", experience);
			map.put("productDescription", productDescription);
			map.put("myFileFileName", myFileFileName);
			map.put("rescueServiceArr", rescueServiceArr);
			
			// 获取城市
			City city = cityService.getDataByCityCode(cityId);
			if (city != null) {
				entity.setCity(city.getCityName());
				entity.setCityCode(cityId);
			}
			
			String[] savePath1 = {ApplicationConfig.FTP_ROOTPATH, "shop", entity.getId(), "detail"};
			if (displayPicUrl1 != null) {
				file1 = new FileInputStream(displayPicUrl1);
				int s = displayPicUrl1FileName.lastIndexOf(".");
		        String displayPicUrl1FileType = displayPicUrl1FileName.substring(s + 1, displayPicUrl1FileName.length());
		        String file1name = System.currentTimeMillis() + "." + displayPicUrl1FileType;
		        
				UploadFtpFileTools.uploadFile(savePath1, file1name, file1);
				entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
						+ File.separator + "detail" + File.separator + file1name);
			}
			
			if (displayPicUrl2 != null) {
				file2 = new FileInputStream(displayPicUrl2);
				//String file2name = displayPicUrl2FileName;
				
				int s = displayPicUrl2FileName.lastIndexOf(".");
		        String displayPicUrl2FileType = displayPicUrl2FileName.substring(s + 1, displayPicUrl2FileName.length());
		        String file2name = System.currentTimeMillis() + "." + displayPicUrl2FileType;
		        
				UploadFtpFileTools.uploadFile(savePath1, file2name, file2);
				if (entity.getDisplayPicUrlList() != null) {
					String url = entity.getDisplayPicUrlList();
					url = url + ",";
					url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file2name;
					entity.setDisplayPicUrlList(url);
				} else {
					entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file2name);
				}
			}
			
			if (displayPicUrl3 != null) {
				file3 = new FileInputStream(displayPicUrl3);
				//String file3name = displayPicUrl3FileName;
				int s = displayPicUrl3FileName.lastIndexOf(".");
		        String displayPicUrl3FileType = displayPicUrl3FileName.substring(s + 1, displayPicUrl3FileName.length());
		        String file3name = System.currentTimeMillis() + "." + displayPicUrl3FileType;
				
				UploadFtpFileTools.uploadFile(savePath1, file3name, file3);
				if (entity.getDisplayPicUrlList() != null) {
					String url = entity.getDisplayPicUrlList();
					url = url + ",";
					url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file3name;
					entity.setDisplayPicUrlList(url);
				} else {
					entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file3name);
				}
			}
			
			if (displayPicUrl4 != null) {
				file4 = new FileInputStream(displayPicUrl4);
				//String file4name = displayPicUrl4FileName;
				int s = displayPicUrl4FileName.lastIndexOf(".");
		        String displayPicUrl4FileType = displayPicUrl4FileName.substring(s + 1, displayPicUrl4FileName.length());
		        String file4name = System.currentTimeMillis() + "." + displayPicUrl4FileType;
				
				UploadFtpFileTools.uploadFile(savePath1, file4name, file4);
				if (entity.getDisplayPicUrlList() != null) {
					String url = entity.getDisplayPicUrlList();
					url = url + ",";
					url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file4name;
					entity.setDisplayPicUrlList(url);
				} else {
					entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file4name);
				}
			}
			
			if (displayPicUrl5 != null) {
				file5 = new FileInputStream(displayPicUrl5);
				//String file5name = displayPicUrl5FileName;
				
				int s = displayPicUrl5FileName.lastIndexOf(".");
		        String displayPicUrl5FileType = displayPicUrl5FileName.substring(s + 1, displayPicUrl5FileName.length());
		        String file5name = System.currentTimeMillis() + "." + displayPicUrl5FileType;
				
				UploadFtpFileTools.uploadFile(savePath1, file5name, file5);
				
				if (entity.getDisplayPicUrlList() != null) {
					String url = entity.getDisplayPicUrlList();
					url = url + ",";
					url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file5name;
					entity.setDisplayPicUrlList(url);
				} else {
					entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file5name);
				}
			}
			
			entity.setIsTop(0);
			entity.setDisplayPriority(0);
			entity.setShopDescription(shopDescription);
			entity.setProductDescription(productDescription);

			int ret = shopService.addSomeData(entity, map);
			System.out.println("ret = " + ret);
			writer = response.getWriter();

			System.out.println("entity2 = " + entity.toString());

			writer.print("<script>alert('添加成功!');window.location.href='shop!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='shop!listData.action';</script>");
		} finally {
			if (file1 != null) {
				file1.close();
			}
			if (file2 != null) {
				file2.close();
			}
			if (file3 != null) {
				file3.close();
			}
			if (file4 != null) {
				file4.close();
			}
			if (file5 != null) {
				file5.close();
			}
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
			for (String id : ids) {
				shopService.deleteSomeData(id);
			}
			writer.print("<script>alert('删除成功!');window.location.href='shop!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='shop!listData.action';</script>");
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
		Map<String, Object> session = ActionContext.getContext().getSession();
		String id = request.getParameter("id");
		
		try {
			if (id != null) {
				entity = shopService.getDataById(id);
				List<Rescue> list = rescueService.getDataByShopId(id);
				if (list.size() != 0) {
					Rescue rescue = list.get(0);
					String typeValue = rescue.getTypeValue();
					String[] typeValueArr = typeValue.split(",");
					for (int i = 0; i < typeValueArr.length; i++) {
						if (StringUtils.equals("1", typeValueArr[i])) {
							context.put("rescueService1", 1);
						} else if (StringUtils.equals("2", typeValueArr[i])) {
							context.put("rescueService2", 2);
						} else if (StringUtils.equals("3", typeValueArr[i])) {
							context.put("rescueService3", 3);
						} else if (StringUtils.equals("4", typeValueArr[i])) {
							context.put("rescueService4", 4);
						} else if (StringUtils.equals("5", typeValueArr[i])) {
							context.put("rescueService5", 5);
						}
					}
					context.put("experience", list.get(0).getExperience());
					context.put("rescueId", list.get(0).getId());
					context.put("productDescription", list.get(0).getProductDescription());
				}
				if (StringUtils.isNotBlank(entity.getShopPicUrl())) {
					entity.setShopPicUrl(entity.getShopPicUrl());
				}
				String url = entity.getDisplayPicUrlList();
				String[] urlArr = null;
				if (StringUtils.isNotBlank(url)) {
					urlArr = url.split(",");
				}
				if (urlArr != null && urlArr.length >= 1) {
					entity.setDisplayPicUrl1(urlArr[0]);
				}
				if (urlArr != null && urlArr.length >= 2) {
					entity.setDisplayPicUrl2(urlArr[1]);
				}
				if (urlArr != null && urlArr.length >= 3) {
					entity.setDisplayPicUrl3(urlArr[2]);
				}
				if (urlArr != null && urlArr.length >= 4) {
					entity.setDisplayPicUrl4(urlArr[3]);
				}
				if (urlArr != null && urlArr.length >= 5) {
					entity.setDisplayPicUrl5(urlArr[4]);
				}
				DealerUser user = (DealerUser)session.get("dealer_user");
				try {
					City city = cityService.getDataByCityCode(user.getCityCode());
					entity.setCity(city.getCityName());
					context.put("cityId", user.getCityCode());
				} catch (Exception e) {
					e.printStackTrace();
				}

				context.put("entity", entity);
				context.put("ftpServerIp", ApplicationConfig.HTTP_PROTOCOL_IP);
			}
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
	 * @throws IOException 
	 */
	public String updateData() throws IOException {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream file1 = null;
		InputStream file3 = null;
		InputStream file2 = null;
		InputStream file4 = null;
		InputStream file5 = null;
		try {
			
			String repairService = request.getParameter("repairService");
			String cleanService = request.getParameter("cleanService");
			String maintainService = request.getParameter("maintainService");
			String tyreService = request.getParameter("tyreService");
			String experience = request.getParameter("experience");
			String rescueService1 = request.getParameter("rescueService1");
			String rescueService2 = request.getParameter("rescueService2");
			String rescueService3 = request.getParameter("rescueService3");
			String rescueService4 = request.getParameter("rescueService4");
			String rescueService5 = request.getParameter("rescueService5");
			String rescueId = request.getParameter("rescueId");
			String picFlag = request.getParameter("picFlag");
			String picFlag1 = request.getParameter("picFlag1");
			String picFlag2 = request.getParameter("picFlag2");
			String picFlag3 = request.getParameter("picFlag3");
			String picFlag4 = request.getParameter("picFlag4");
			String picFlag5 = request.getParameter("picFlag5");
			String cityId = request.getParameter("cityId");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("repairService", repairService);
			map.put("cleanService", cleanService);
			map.put("maintainService", maintainService);
			map.put("tyreService", tyreService);
			map.put("experience", experience);
			//map.put("productDescription", productDescription);
			map.put("myFileFileName", myFileFileName);
			map.put("rescueService1", rescueService1);
			map.put("rescueService2", rescueService2);
			map.put("rescueService3", rescueService3);
			map.put("rescueService4", rescueService4);
			map.put("rescueService5", rescueService5);
			map.put("rescueId", rescueId);
			
			
			Shop shop = shopService.getDataById(entity.getId());
			if (shop != null && shop.getCityCode() != cityId) {
				entity.setCityCode(cityId);
				City city = cityService.getDataByCityCode(cityId);
				entity.setCity(city.getCityName());
			}
			String[] savePath = {ApplicationConfig.FTP_ROOTPATH, "shop", entity.getId(), "thumbnail"};
			// 公司头像图片没有改变
			if (StringUtils.isNotBlank(picFlag) && StringUtils.isBlank(myFileFileName)) {
				
				entity.setShopPicUrl(picFlag);
			// 图片改变
			} else if (StringUtils.isBlank(picFlag)|| picFlag.indexOf(myFileFileName) == -1) {
				
				String picturePath = shop.getShopPicUrl();
				// 删除ftp上的图片
				if (picturePath != null && picturePath.length() > 1) {
					UploadFtpFileTools.deleteFile(picturePath.substring(1));
				}
				
				if (StringUtils.isNotBlank(myFileFileName)) {
					// 上传ftp新的图片
					InputStream file = new FileInputStream(myFile);
					//String filename = myFileFileName;
					
					int s = myFileFileName.lastIndexOf(".");
			        String myFileType = myFileFileName.substring(s + 1, myFileFileName.length());
			        String filename = System.currentTimeMillis() + "." + myFileType;
					
					UploadFtpFileTools.uploadFile(savePath, filename, file);
					entity.setShopPicUrl(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "thumbnail" + File.separator + filename);
				} else {
					entity.setShopPicUrl("");
				}
			} 
			
			String url = null;
			String[] oldDisplayPicUrlArr = null;
			
			String oldDisplayPicUrlList = shop.getDisplayPicUrlList();
			if (StringUtils.isNotBlank(oldDisplayPicUrlList)) {
				oldDisplayPicUrlArr = oldDisplayPicUrlList.split(",");
			}
			String[] savePath1 = {ApplicationConfig.FTP_ROOTPATH, "shop", entity.getId(), "detail"};
			
			// 图片没有改变
			if (StringUtils.isNotBlank(picFlag1) && StringUtils.isBlank(displayPicUrl1FileName)) {
				
				url = picFlag1;
				entity.setDisplayPicUrlList(url);
			// 图片改变
			} else if (StringUtils.isBlank(picFlag1) || picFlag1.indexOf(displayPicUrl1FileName) == -1) {
				
				String picturePath1 = "";
				if (oldDisplayPicUrlArr !=null && oldDisplayPicUrlArr.length > 0) {
					picturePath1 = oldDisplayPicUrlArr[0];
					// 删除ftp上的图片
					UploadFtpFileTools.deleteFile(picturePath1.substring(1));
				}
				
				/*if (picturePath1.length() > 1) {
					
				}*/
				if (StringUtils.isNotBlank(displayPicUrl1FileName)) {
					// 上传ftp新的图片
					file1 = new FileInputStream(displayPicUrl1);
					//String file1name = displayPicUrl1FileName;
					
					int s = displayPicUrl1FileName.lastIndexOf(".");
			        String displayPicUrl1FileType = displayPicUrl1FileName.substring(s + 1, displayPicUrl1FileName.length());
			        String file1name = System.currentTimeMillis() + "." + displayPicUrl1FileType;
					
					UploadFtpFileTools.uploadFile(savePath1, file1name, file1);
					entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
							+ File.separator + "detail" + File.separator + file1name);
				}
			} 
			
			// 图片2没有改变
			if (StringUtils.isNotBlank(picFlag2) && StringUtils.isBlank(displayPicUrl2FileName)) {
				
				if (url != null) {
					url = url + ",";
					url += picFlag2;
				} else {
					url = picFlag2;
				}
				entity.setDisplayPicUrlList(url);
			} else if (StringUtils.isBlank(picFlag2) || picFlag2.indexOf(displayPicUrl2FileName) == -1) {
				
				String picturePath2 = "";
				if (oldDisplayPicUrlArr !=null && oldDisplayPicUrlArr.length > 1) {
					picturePath2 = oldDisplayPicUrlArr[1];
					// 删除ftp上的图片
					UploadFtpFileTools.deleteFile(picturePath2.substring(1));
				}
				
				/*if (picturePath2.length() > 1) {
					
				}*/
				
				if (StringUtils.isNotBlank(displayPicUrl2FileName)) {
					file2 = new FileInputStream(displayPicUrl2);
					//String file2name = displayPicUrl2FileName;
					
					int s = displayPicUrl2FileName.lastIndexOf(".");
			        String displayPicUrl2FileType = displayPicUrl2FileName.substring(s + 1, displayPicUrl2FileName.length());
			        String file2name = System.currentTimeMillis() + "." + displayPicUrl2FileType;
					
					UploadFtpFileTools.uploadFile(savePath1, file2name, file2);
					if (entity.getDisplayPicUrlList() != null) {
						url = entity.getDisplayPicUrlList();
						url = url + ",";
						url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file2name;
						entity.setDisplayPicUrlList(url);
					} else {
						entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file2name);
					}
				}
			}
			
			// 图片3没有改变
			if (StringUtils.isNotBlank(picFlag3) && StringUtils.isBlank(displayPicUrl3FileName)) {
				
				if (url != null) {
					url = url + ",";
					url += picFlag3;
				} else {
					url = picFlag3;
				}
				entity.setDisplayPicUrlList(url);
			} else if (StringUtils.isBlank(picFlag3) || picFlag3.indexOf(displayPicUrl3FileName) == -1) {
				
				String picturePath3 = "";
				if (oldDisplayPicUrlArr !=null && oldDisplayPicUrlArr.length > 2) {
					picturePath3 = oldDisplayPicUrlArr[2];
					// 删除ftp上的图片
					UploadFtpFileTools.deleteFile(picturePath3.substring(1));
				}
				
				/*if (picturePath3.length() > 1) {
					
				}*/
				
				if (StringUtils.isNotBlank(displayPicUrl3FileName)) {
					file3 = new FileInputStream(displayPicUrl3);
					//String file3name = displayPicUrl3FileName;
					
					int s = displayPicUrl3FileName.lastIndexOf(".");
			        String displayPicUrl3FileType = displayPicUrl3FileName.substring(s + 1, displayPicUrl3FileName.length());
			        String file3name = System.currentTimeMillis() + "." + displayPicUrl3FileType;
					
					UploadFtpFileTools.uploadFile(savePath1, file3name, file3);
					if (entity.getDisplayPicUrlList() != null) {
						url = entity.getDisplayPicUrlList();
						url = url + ",";
						url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file3name;
						entity.setDisplayPicUrlList(url);
					} else {
						entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file3name);
					}
				}
			}
			
			// 图片4没有改变
			if (StringUtils.isNotBlank(picFlag4) && StringUtils.isBlank(displayPicUrl4FileName)) {
				
				if (url != null) {
					url = url + ",";
					url += picFlag4;
				} else {
					url = picFlag4;
				}
				entity.setDisplayPicUrlList(url);
			} else if (StringUtils.isBlank(picFlag4) || picFlag4.indexOf(displayPicUrl4FileName) == -1) {
				
				String picturePath4 = "";
				if (oldDisplayPicUrlArr !=null && oldDisplayPicUrlArr.length > 3) {
					picturePath4 = oldDisplayPicUrlArr[3];
					// 删除ftp上的图片
					UploadFtpFileTools.deleteFile(picturePath4.substring(1));
				}		
				
				/*if (picturePath4.length() > 1) {
					
				}*/
				
				if (StringUtils.isNotBlank(displayPicUrl4FileName)) {
					file4 = new FileInputStream(displayPicUrl4);
					//String file4name = displayPicUrl4FileName;
					
					int s = displayPicUrl4FileName.lastIndexOf(".");
			        String displayPicUrl4FileType = displayPicUrl4FileName.substring(s + 1, displayPicUrl4FileName.length());
			        String file4name = System.currentTimeMillis() + "." + displayPicUrl4FileType;
					
					UploadFtpFileTools.uploadFile(savePath1, file4name, file4);
					if (entity.getDisplayPicUrlList() != null) {
						url = entity.getDisplayPicUrlList();
						url = url + ",";
						url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file4name;
						entity.setDisplayPicUrlList(url);
					} else {
						entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file4name);
					}
				}
			}
			
			// 图片5没有改变
			if (StringUtils.isNotBlank(picFlag5) && StringUtils.isBlank(displayPicUrl5FileName)) {
				
				if (url != null) {
					url = url + ",";
					url += picFlag5;
				} else {
					url = picFlag5;
				}
				entity.setDisplayPicUrlList(url);
			} else if (StringUtils.isBlank(picFlag5) || picFlag5.indexOf(displayPicUrl5FileName) == -1) {
				
				String picturePath5 = "";
				if (oldDisplayPicUrlArr !=null && oldDisplayPicUrlArr.length > 4) {
					picturePath5 = oldDisplayPicUrlArr[4];
					// 删除ftp上的图片
					UploadFtpFileTools.deleteFile(picturePath5.substring(1));
				}
				
				/*if (picturePath5.length() > 1) {
					
				}*/
				
				if (StringUtils.isNotBlank(displayPicUrl5FileName)) {
					file5 = new FileInputStream(displayPicUrl5);
					//String file5name = displayPicUrl5FileName;
					int s = displayPicUrl5FileName.lastIndexOf(".");
			        String displayPicUrl5FileType = displayPicUrl5FileName.substring(s + 1, displayPicUrl5FileName.length());
			        String file5name = System.currentTimeMillis() + "." + displayPicUrl5FileType;
					
					UploadFtpFileTools.uploadFile(savePath1, file5name, file5);
					if (entity.getDisplayPicUrlList() != null) {
						url = entity.getDisplayPicUrlList();
						url = url + ",";
						url = url + File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file5name;
						entity.setDisplayPicUrlList(url);
					} else {
						entity.setDisplayPicUrlList(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + "shop" + File.separator + entity.getId() 
								+ File.separator + "detail" + File.separator + file5name);
					}
				}
			}
			if (entity.getDisplayPicUrlList() == null) {
				entity.setDisplayPicUrlList("");
			}
			
			System.out.println("entity = " + entity.toString());
			
			int ret = shopService.updateSomeData(entity, map);
			System.out.println("ret = " + ret);
			writer = response.getWriter();

			System.out.println("entity2 = " + entity.toString());

			writer.print("<script>alert('修改成功!');window.location.href='shop!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='shop!listData.action';</script>");
		} finally {
			if (file1 != null) {
				file1.close();
			}
			if (file2 != null) {
				file2.close();
			}
			if (file3 != null) {
				file3.close();
			}
			if (file4 != null) {
				file4.close();
			}
			if (file5 != null) {
				file5.close();
			}
		}
		return null;
	}

	/*
	 * 全部数据导出
	 */
	public String exportAll() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		
		OutputStream out=null;
		try {
			out = response.getOutputStream();
			String fileName = "店铺信息.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.flushBuffer();
			//导出excel
			shopService.exprotData(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/*
	 * 导入数据
	 */
	public void doUpload() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");  
        response.setHeader("Cache-Control", "no-store");  
        response.setHeader("Pragma", "no-cache");  
        response.setDateHeader("Expires", 0);
        
        Map<String, Object> map = new HashMap<String, Object>();
        List<City> cityList = cityService.getAllInfo(map);
        
		if (this.uploadFile == null) {
			out.write("-1");
		} else {
			String uploadFileFileName = this.uploadFileFileName;
			String strs[] = uploadFileFileName.split("\\.");
			if (strs.length >= 2) {
				if (!StringUtils.equals(strs[strs.length - 1], "xls")) {
					out.write("-2");
				} else {
					FileInputStream is = new FileInputStream(this.uploadFile);
					Workbook wb = new HSSFWorkbook(is);
					Sheet sheet = wb.getSheetAt(0);
					int rowNum = sheet.getLastRowNum() + 1;

					Shop shop = new Shop();
					List<Shop> list1 = new ArrayList<Shop>();
					
					boolean errFlag = false;

					try {
			            out.write("1");

			            for (int i = 1; i < rowNum; i++) {
			    			Row row = sheet.getRow(i);
			    			DecimalFormat df = new DecimalFormat("######0");  
			    			// 救援
			    			Cell cell = row.getCell(0);
			    			if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("08");
								errFlag = true;
								break;
			    			} else {
			    				shop.setRescueService(String.valueOf(df.format(cell.getNumericCellValue())));
			    			}
			    			
			    			// 修车
			    			Cell cell1 = row.getCell(1);
			    			if (cell1 == null || cell1.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("09");
								errFlag = true;
								break;
			    			} else {
			    				shop.setRepairService(String.valueOf(df.format(cell1.getNumericCellValue())));
			    			}
			    			
			    			// 洗车
			    			Cell cell2 = row.getCell(2);
			    			if (cell2 == null || cell2.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("10");
								errFlag = true;
								break;
			    			} else {
			    				shop.setCleanService(String.valueOf(df.format(cell2.getNumericCellValue())));
			    			}
			    			
			    			// 保养
			    			Cell cell3 = row.getCell(3);
			    			if (cell3 == null || cell3.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("11");
								errFlag = true;
								break;
			    			} else {
			    				shop.setMaintainService(String.valueOf(df.format(cell3.getNumericCellValue())));
			    			}
			    			
			    			// 轮胎
			    			Cell cell4 = row.getCell(4);
			    			if (cell4 == null || cell4.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("12");
								errFlag = true;
								break;
			    			} else {
			    				shop.setTyreService(String.valueOf(df.format(cell4.getNumericCellValue())));
			    			}
			    			
			    			// 公司名称
			    			Cell cell5 = row.getCell(5);
			    			if (cell5 == null || cell5.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("13");
								errFlag = true;
								break;
			    			} else {
			    				shop.setShopName(cell5.getStringCellValue());
			    			}
			    			
			    			// 公司评分
			    			Cell cell6 = row.getCell(6);
			    			if (cell6 == null || cell6.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("14");
								errFlag = true;
								break;
			    			} else {
			    				shop.setShopScore(String.valueOf(df.format(cell6.getNumericCellValue())));
			    			}
			    			
			    			// 公司描述(可以为空)
			    			Cell cell7 = row.getCell(7);
			    			if (cell7 != null && cell7.getCellType() != Cell.CELL_TYPE_BLANK) {
			    				shop.setShopDescription(cell7.getStringCellValue());
			    			}
			    			
			    			// 经度
			    			Cell cell8 = row.getCell(8);
			    			if (cell8 == null || cell8.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("15");
								errFlag = true;
								break;
			    			} else {
			    				shop.setLongitude(String.valueOf(cell8.getNumericCellValue()));
			    			}
			    			
			    			// 纬度
			    			Cell cell9 = row.getCell(9);
			    			if (cell9 == null || cell9.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("16");
								errFlag = true;
								break;
			    			} else {
			    				shop.setLatitude(String.valueOf(cell9.getNumericCellValue()));
			    			}
			    			
			    			// 省
			    			Cell cell10 = row.getCell(10);
			    			if (cell10 == null || cell10.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("17");
								errFlag = true;
								break;
			    			} else {
			    				shop.setProvince(cell10.getStringCellValue());
			    			}
			    			
			    			// 市
			    			Cell cell11 = row.getCell(11);
			    			if (cell11 == null || cell11.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("18");
								errFlag = true;
								break;
			    			} else {
			    				shop.setCity(cell11.getStringCellValue());
			    			}
			    			
			    			// 详细地址
			    			Cell cell12 = row.getCell(12);
			    			if (cell12 == null || cell12.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("20");
								errFlag = true;
								break;
			    			} else {
			    				shop.setDetailAddress(cell12.getStringCellValue());
			    			}
			    			
			    			// 电话
			    			Cell cell13 = row.getCell(13);
			    			if (cell13 == null || cell13.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("21");
								errFlag = true;
								break;
			    			} else {
			    				shop.setTelNumberList(String.valueOf(cell13));
			    			}
			    			
			    			// 救援类型
			    			Cell cell14 = row.getCell(14);
			    			if (cell14 == null || cell14.getCellType() == Cell.CELL_TYPE_BLANK) {
			    				out.write("22");
								errFlag = true;
								break;
			    			} else {
			    				shop.setTypeValue(String.valueOf(cell14));
			    			}
			    			
			    			// 救援经验
//			    			Cell cell15 = row.getCell(15);
//			    			if (cell15 == null || cell15.getCellType() == Cell.CELL_TYPE_BLANK) {
//			    				out.write("23");
//								errFlag = true;
//								break;
//			    			} else {
//			    				shop.setExperience(String.valueOf(df.format(cell15.getNumericCellValue())));
//			    			}
			    			
			    			// 救援描述
			    			Cell cell15 = row.getCell(15);
			    			if (cell15 != null && cell15.getCellType() != Cell.CELL_TYPE_BLANK) {
			    				shop.setProductDescription(cell15.getStringCellValue());
			    			}
			    			
			    			// 城市编号查找
			    			for (City city : cityList) {
			    				if (StringUtils.equals(shop.getCity(), city.getCityName())) {
			    					shop.setCityCode(city.getCityCode());
			    					break;
			    				}
			    			}
			    			
			    			list1.add(shop);
			    			shop = new Shop();
			    		}
			            
			            if (!errFlag) {
			            	shopService.addImport(list1);
			            }
			            
					} catch (Exception e) {
						out.write("7");
						e.printStackTrace();
					} finally {   
			            out.close();  
			        } 
					is.close();
					this.uploadFile.delete();
				}
			}
		}
	}

}
