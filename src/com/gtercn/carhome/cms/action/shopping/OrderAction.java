package com.gtercn.carhome.cms.action.shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.ExpertTop;
import com.gtercn.carhome.cms.entity.shopping.Order;
import com.gtercn.carhome.cms.service.shopping.order.LogisticsService;
import com.gtercn.carhome.cms.service.shopping.order.OrderService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 订单
 * @author ken 2017-2-23 下午03:39:05
 */
public class OrderAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderService orderService;
	@Autowired
	private LogisticsService logisticsService;
	
	private Order entity;
	public Order getEntity() {
		return entity;
	}
	public void setEntity(Order entity) {
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
			String expertId = request.getParameter("expertId");
			if(StringUtils.isNotBlank(expertId) && (!"-1".equals(expertId)))
				map.put("expertId", expertId);
			String orderStatus = request.getParameter("orderStatus");
			if(StringUtils.isNotBlank(orderStatus) && (!"-1".equals(orderStatus)))
				map.put("orderStatus", orderStatus);
			String orderNo = request.getParameter("orderNo");
			if (orderNo != null && !orderNo.equals(""))
				map.put("orderNo", orderNo);
			
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = orderService.getTotalCount(map);
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
			List<Order> list = orderService.queryAllData(map);
			List<ExpertTop> expertList=orderService.queryAllExpert(cityCode);
			
			context.put("list", list);
			context.put("expertList", expertList);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);
			// 设置查询参数
			context.put("expertId", expertId);
			context.put("orderStatus", orderStatus);
			context.put("orderNo", orderNo);
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
			String orderId=request.getParameter("orderId");
			Order order = orderService.selectByPrimaryKey(orderId);
			context.put("order", order);
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
