package com.gtercn.carhome.cms.action.shopping;

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
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品
 * @author ken 2017-2-23 下午03:39:05
 */
public class OrderAction extends ActionSupport {
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
}
