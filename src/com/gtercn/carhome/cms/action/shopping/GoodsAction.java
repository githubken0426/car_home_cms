package com.gtercn.carhome.cms.action.shopping;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.shopping.Goods;
import com.gtercn.carhome.cms.entity.shopping.GoodsBrand;
import com.gtercn.carhome.cms.entity.shopping.GoodsCategory;
import com.gtercn.carhome.cms.entity.shopping.Spec;
import com.gtercn.carhome.cms.entity.shopping.SpecItemGoodsRelation;
import com.gtercn.carhome.cms.service.shopping.brand.GoodsBrandService;
import com.gtercn.carhome.cms.service.shopping.goods.GoodsService;
import com.gtercn.carhome.cms.service.shopping.goodscategory.GoodsCategoryService;
import com.gtercn.carhome.cms.service.shopping.spec.SpecService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品
 * @author ken 2017-2-23 下午03:39:05
 */
public class GoodsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsCategoryService categoryService;
	@Autowired
	private GoodsBrandService goodsBrandService;
	@Autowired
	private SpecService specService;
	
	
	private Goods entity;
	public Goods getEntity() {
		return entity;
	}
	public void setEntity(Goods entity) {
		this.entity = entity;
	}
	
	private String categoryId;

	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 分页检索数据
	 * @return
	 */
	public String list() {
		Map<String, Object> map = new HashMap<String, Object>();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = context.getSession();
		try {
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode =ApplicationConfig.DEFAULT_CITY_CODE;
			if(null!=user) 
				cityCode = user.getCityCode();
			map.put("cityCode", cityCode);
			String title = request.getParameter("title");
			if (title != null && !title.equals("")) {
				title = URLDecoder.decode(title, "UTF-8");
				map.put("goodsTitle", title);
			}
			String categoryId = request.getParameter("categoryId");
			if(StringUtils.isNotBlank(categoryId) && (!"-1".equals(categoryId)))
				map.put("categoryId", categoryId);
			String brandId = request.getParameter("brandId");
			if(StringUtils.isNotBlank(brandId) && (!"-1".equals(brandId)))
				map.put("brandId", brandId);
			String beginTime = request.getParameter("beginTime");
			if (beginTime != null && !beginTime.equals(""))
				map.put("beginTime", beginTime);
			String endTime = request.getParameter("endTime");
			if (endTime != null && !endTime.equals(""))
				map.put("endTime", endTime);
			
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = goodsService.getTotalCount(map);
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
			List<Goods> list = goodsService.queryAllData(map);
			List<GoodsCategory> categoryList=categoryService.selectAllCategory();
			List<GoodsBrand> brandList=goodsBrandService.queryDataByCategory(categoryId);
			
			context.put("brandList", brandList);
			context.put("categoryList", categoryList);
			context.put("list", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);
			// 设置查询参数
			context.put("categoryId", categoryId);
			context.put("brandId", brandId);
			context.put("title", title);
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
	public String addPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String title = request.getParameter("title");
			String categoryId = request.getParameter("categoryId");
			String brandId = request.getParameter("brandId");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String addCategoryId=request.getParameter("addCategoryId");
			
			map.put("categoryId", addCategoryId);
			List<Spec> specList=specService.selectGoodsSpecItems(map);
			context.put("specList", specList);
			List<GoodsBrand> brandList=goodsBrandService.queryDataByCategory(addCategoryId);
			context.put("brandList", brandList);
			
			context.put("currentIndex", currentIndex);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
			context.put("addCategoryId", addCategoryId);
			context.put("categoryId", categoryId);
			context.put("brandId", brandId);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "add";
	}

	/**
	 * 添加数据
	 * @return
	 * @throws Exception 
	 */
	public void add() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer  = response.getWriter();
		try {
			String uuid = CommonUtil.getUID();
			entity.setId(uuid);
			String sku=CommonUtil.randomUpperCode(5, uuid);
			entity.setSkuCode(sku);
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode =ApplicationConfig.DEFAULT_CITY_CODE;
			if(null!=user) 
				cityCode = user.getCityCode();
			entity.setCityCode(cityCode);
			String small[]=request.getParameterValues("smallPicture");
			String smallPaths = CommonUtil.arrayToString(small);
			entity.setSmallPicture(smallPaths);
			String big[] = request.getParameterValues("bigPicture");
			String bigPaths = CommonUtil.arrayToString(big);
			entity.setBigPicture(bigPaths);
			String dtail[] = request.getParameterValues("detailPicture");
			String dtailPaths = CommonUtil.arrayToString(dtail);
			entity.setGoodsDetail(dtailPaths);
			
			//goods item关系表
			List<SpecItemGoodsRelation> relationList=new ArrayList<SpecItemGoodsRelation>();
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("categoryId", categoryId);
			List<Spec> specList=specService.selectGoodsSpecItems(map);
			for (Spec spec : specList) {
				SpecItemGoodsRelation relation=new SpecItemGoodsRelation();
				String specItemId=request.getParameter(spec.getId());
				relation.setGoodsId(uuid);
				relation.setSpecItemId(specItemId);
				relationList.add(relation);
			}
			goodsService.insert(entity, relationList);
			writer.print("<script>alert('添加成功!');window.location.href='goods_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer .print("<script>alert('添加失败!');window.location.href='goods_list.action';</script>");
		}
	}
	
	/**
	 * 商品详情
	 * @return
	 */
	public String detail() {
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
			String title = request.getParameter("title");
			String categoryId = request.getParameter("categoryId");
			String brandId = request.getParameter("brandId");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String skuCode=request.getParameter("skuCode");
			Goods goods = goodsService.selectBySkuCode(skuCode);
			String goodsId = goods != null ? goods.getId() : "";
			List<Spec> specList = specService.selectDetailSpecByGoodsId(goodsId);
			
			context.put("specList", specList);
			context.put("entity", goods);
			
			context.put("currentIndex", currentIndex);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
			context.put("categoryId", categoryId);
			context.put("brandId", brandId);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "detail";
	}
	/**
	 * 修改数据(进入画面)
	 * 
	 * @return
	 */
	public String updateDataPage() {
		Map<String,Object> map=new HashMap<String,Object>();
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
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String id = request.getParameter("id");
			entity=goodsService.selectByPrimaryKey(id);
			
			String categoryId=entity!=null ?entity.getCategoryId():"";
			map.put("categoryId", categoryId);
			List<Spec> specList=specService.selectGoodsSpecItems(map);
			List<GoodsBrand> brandList=goodsBrandService.queryDataByCategory(categoryId);
			
			context.put("specList", specList);
			context.put("brandList", brandList);
			context.put("entity", entity);
			
			context.put("currentIndex", currentIndex);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
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
	 * @throws Exception 
	 */
	public void updateData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		InputStream input =null;
		PrintWriter writer = response.getWriter();
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String ftpPaths[] = { ApplicationConfig.FTP_SHOPPING_PATH, ApplicationConfig.FTP_ADVER_PATH };
			// 上传展示图片
			File[] viewResUrlList = multipartRequest.getFiles("resUrlList");
			for (File file : viewResUrlList) {
				input = new FileInputStream(file);
				String portraitFileName = System.currentTimeMillis() + ".jpg";
				boolean bool = UploadFtpFileTools.uploadFile(ftpPaths, portraitFileName, input);
				if (bool) {
					String picturePath = File.separator + ApplicationConfig.FTP_SHOPPING_PATH + File.separator
							+ ApplicationConfig.FTP_ADVER_PATH + File.separator + portraitFileName;
					//entity.setPicturePath(picturePath);
				}
			}
			
			writer .print("<script>alert('修改成功!');window.location.href='goods_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='goods_list.action';</script>");
		}finally{
			if(input!=null)
				input.close();
		}
	}

	/**
	 * 删除
	 * @return
	 */
	public void deleteData() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String ids[] = request.getParameterValues("id");
			goodsService.deleteBatch(ids);
			writer.print("<script>alert('商品下架成功!');window.location.href='goods_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('商品下架失败!');window.location.href='goods_list.action';</script>");
		}
	}
}
