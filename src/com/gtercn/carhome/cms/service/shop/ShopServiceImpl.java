package com.gtercn.carhome.cms.service.shop;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.ShopMapper;
import com.gtercn.carhome.cms.entity.Rescue;
import com.gtercn.carhome.cms.entity.ServiceEn;
import com.gtercn.carhome.cms.entity.Shop;
import com.gtercn.carhome.cms.service.rescue.RescueService;
import com.gtercn.carhome.cms.service.service.ServiceService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.ExcelStyle;

@Service(value = "shopService")
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopMapper dao;
	
	@Autowired
	private RescueService rescueService;
	
	@Autowired
	private ServiceService serviceService;

	@Override
	public List<Shop> queryAllData(Map<String, Object> map) {
		List<Shop> list = dao.queryAllData(map);
		
		for (int i = 0; i < list.size(); i++) {
			
			//救援
			if (StringUtils.equals("2000", list.get(i).getRescueService())) {
				list.get(i).setRescueService("救援");
			} else if (StringUtils.equals("0", list.get(i).getRescueService())) {
				list.get(i).setRescueService("无");
			}
			// 洗车
			if (StringUtils.equals("4100", list.get(i).getCleanService())) {
				list.get(i).setCleanService("洗车");
			} else if (StringUtils.equals("0", list.get(i).getCleanService())) {
				list.get(i).setCleanService("无");
			}
			// 修车
			if (StringUtils.equals("5100", list.get(i).getRepairService())) {
				list.get(i).setRepairService("修车");
			} else if (StringUtils.equals("0", list.get(i).getRepairService())) {
				list.get(i).setRepairService("无");
			}
			
			// 保养
			if (StringUtils.equals("6100", list.get(i).getMaintainService())) {
				list.get(i).setMaintainService("保养");
			} else if (StringUtils.equals("0", list.get(i).getMaintainService())) {
				list.get(i).setMaintainService("无");
			}
			
			// 轮胎
			if (StringUtils.equals("7100", list.get(i).getTyreService())) {
				list.get(i).setTyreService("轮胎");
			} else if (StringUtils.equals("0", list.get(i).getTyreService())) {
				list.get(i).setTyreService("无");
			}
		}
		return list;
	}

	@Override
	public Shop getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(Shop o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int addData(Shop o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteSomeData(String id) throws Exception {
		
		List<ServiceEn> list = serviceService.getDataByShopId(id);
		ServiceEn serviceEn = new ServiceEn();
		for (int i = 0; i < list.size(); i++) {
			serviceEn = list.get(i);
			serviceEn.setDeleteFlag(1);
			serviceService.updateData(serviceEn);
			serviceEn = new ServiceEn();
		}
		List<Rescue> rescueList = rescueService.getDataByShopId(id);
		Rescue rescue = new Rescue();
		if (rescueList.size() > 0) {
			rescue = rescueList.get(0);
			rescue.setDeleteFlag(1);
			rescueService.updateData(rescue);
		}
		Shop shop = dao.selectByPrimaryKey(id);
		if (shop != null) {
			shop.setDeleteFlag(1);
			dao.updateByPrimaryKey(shop);
		}
	}

	@Override
	public int addSomeData(Shop entity, Map<String, Object> map) throws Exception {
		
		//取数据
		String[] rescueServiceArr = null;
		String experience = null;
		String myFileFileName = null;
		String productDescription = null;
		String shopDescription = null;
		String repairService = null;
		String cleanService = null;
		String maintainService = null;
		String tyreService = null;
		if (map.get("rescueServiceArr") != null) {
			rescueServiceArr = (String[])map.get("rescueServiceArr");
		}
		if (map.get("experience") != null) {
			experience = map.get("experience").toString();
		}		
		if (map.get("myFileFileName") != null) {
			myFileFileName = map.get("myFileFileName").toString();
		}
		if (map.get("productDescription") != null) {
			productDescription = map.get("productDescription").toString();
		}
		if (map.get("shopDescription") != null) {
			shopDescription = map.get("shopDescription").toString();
		}
		if (map.get("repairService") != null) {
			repairService = map.get("repairService").toString();
		}
		if (map.get("cleanService") != null) {
			cleanService = map.get("cleanService").toString();
		}
		if (map.get("maintainService") != null) {
			maintainService = map.get("maintainService").toString();
		}
		if (map.get("tyreService") != null) {
			tyreService = map.get("tyreService").toString();
		}
		
		// 救援表添加数据
		if (rescueServiceArr != null) {
			Rescue rescue = new Rescue();
			String rescueUuid = CommonUtil.getUID();
			rescue.setId(rescueUuid);
			rescue.setShopId(entity.getId());
			String typeValue = "";
			for (int i = 0; i < rescueServiceArr.length; i++) {
				if (i != 0) {
					typeValue = typeValue + ",";
				}
				typeValue += rescueServiceArr[i];
			}
			rescue.setTypeValue(typeValue);
			rescue.setExperience(experience);
			if (entity.getShopPicUrl() != null) {
				rescue.setHeadPortraitUrl(File.separator + "carhome" + File.separator + "shop" + File.separator + entity.getId() 
				+ File.separator + "thumbnail" + File.separator + myFileFileName);
			}
			rescue.setProductDescription(productDescription);
			rescue.setDeleteFlag(0);
			rescueService.addData(rescue);
		}
		
		// 服务表添加数据
		serviceService.addSomeData(entity.getId(), repairService, cleanService, maintainService, tyreService);
		
		// 店铺表添加数据
		if (rescueServiceArr != null) {
			entity.setRescueService("2000");
		} else {
			entity.setRescueService("0");
		}
		if (StringUtils.equals("1", repairService)) {
			entity.setRepairService("5100");
		} else {
			entity.setRepairService("0");
		}
		if (StringUtils.equals("1", cleanService)) {
			entity.setCleanService("4100");
		} else {
			entity.setCleanService("0");
		}
		if (StringUtils.equals("1", maintainService)) {
			entity.setMaintainService("6100");
		} else {
			entity.setMaintainService("0");
		}
		if (StringUtils.equals("1", tyreService)) {
			entity.setTyreService("7100");
		} else {
			entity.setTyreService("0");
		}
		return dao.insert(entity);		
	}
	
	@Override
	public int updateSomeData(Shop o, Map<String, Object> map) throws Exception {
		
		//取数据
		String experience = null;
		String repairService = null;
		String cleanService = null;
		String maintainService = null;
		String tyreService = null;
		String rescueService1 = null;
		String rescueService2 = null;
		String rescueService3 = null;
		String rescueService4 = null;
		String rescueService5 = null;
		String rescueId = null;
		String typeValue = null;

		if (map.get("experience") != null) {
			experience = map.get("experience").toString();
		}		
		if (map.get("repairService") != null) {
			repairService = map.get("repairService").toString();
		}
		if (map.get("cleanService") != null) {
			cleanService = map.get("cleanService").toString();
		}
		if (map.get("maintainService") != null) {
			maintainService = map.get("maintainService").toString();
		}
		if (map.get("tyreService") != null) {
			tyreService = map.get("tyreService").toString();
		}
		if (map.get("rescueService1") != null) {
			rescueService1 = map.get("rescueService1").toString();
			typeValue = "";
			typeValue += rescueService1;
		}
		if (map.get("rescueService2") != null) {
			rescueService2 = map.get("rescueService2").toString();
			if (typeValue == null) {
				typeValue = "";
				typeValue += rescueService2;
			} else {
				typeValue = typeValue + ",";
				typeValue += rescueService2;
			}
		}
		if (map.get("rescueService3") != null) {
			rescueService3 = map.get("rescueService3").toString();
			if (typeValue == null) {
				typeValue = "";
				typeValue += rescueService3;
			} else {
				typeValue = typeValue + ",";
				typeValue += rescueService3;
			}
		}
		if (map.get("rescueService4") != null) {
			rescueService4 = map.get("rescueService4").toString();
			if (typeValue == null) {
				typeValue = "";
				typeValue += rescueService4;
			} else {
				typeValue = typeValue + ",";
				typeValue += rescueService4;
			}
		}
		if (map.get("rescueService5") != null) {
			rescueService5 = map.get("rescueService5").toString();
			if (typeValue == null) {
				typeValue = "";
				typeValue += rescueService5;
			} else {
				typeValue = typeValue + ",";
				typeValue += rescueService5;
			}
			
		}
		if (map.get("rescueId") != null) {
			rescueId = map.get("rescueId").toString();
		}
		
		// 救援表添加数据
		Rescue rescue = new Rescue();
		if (typeValue != null) {
			if (StringUtils.isNotBlank(rescueId)) {
				rescue.setId(rescueId);
				rescue.setTypeValue(typeValue);
				rescue.setHeadPortraitUrl(o.getShopPicUrl());
				rescue.setProductDescription(o.getProductDescription());
				rescue.setExperience(experience);
				rescueService.updateData(rescue);
			} else {
				rescue.setId(CommonUtil.getUID());
				rescue.setShopId(o.getId());
				rescue.setTypeValue(typeValue);
				rescue.setHeadPortraitUrl(o.getShopPicUrl());
				rescue.setProductDescription(o.getProductDescription());
				rescue.setExperience(experience);
				rescue.setDeleteFlag(0);
				rescueService.addData(rescue);
			}
		} else {
			rescue.setDeleteFlag(1);
			rescue.setId(rescueId);
			rescueService.updateData(rescue);
		}
		if (typeValue == null) {
			o.setRescueService("0");
		} else {
			o.setRescueService("2000");
		}
		
		List<ServiceEn> list = serviceService.getDataByShopId(o.getId());
		Map<String, Object> mapCheck = new HashMap<String, Object>();
		mapCheck.put("cleanFlag", false);
		mapCheck.put("repairFlag", false);
		mapCheck.put("mainFlag", false);
		mapCheck.put("tyreFlag", false);
		// 修车服务存在，记录进表中
		for (int i = 0; i < list.size(); i++ ) {
			if (list.get(i).getServiceType() == 4100) {
				mapCheck.put("cleanFlag", true);
				mapCheck.put("cleanId", list.get(i).getId());
			}
			if (list.get(i).getServiceType() == 5100) {
				mapCheck.put("repairFlag", true);
				mapCheck.put("repairId", list.get(i).getId());
			}
			if (list.get(i).getServiceType() == 6100) {
				mapCheck.put("mainFlag", true);
				mapCheck.put("mainId", list.get(i).getId());
			}
			if (list.get(i).getServiceType() == 7100) {
				mapCheck.put("tyreFlag", true);
				mapCheck.put("tyreId", list.get(i).getId());
			}
		}
		// 洗车
		ServiceEn service = new ServiceEn();
		if ((Boolean)mapCheck.get("cleanFlag") && cleanService == null) {
			service.setDeleteFlag(1);
			service.setId(mapCheck.get("cleanId").toString());
			serviceService.updateData(service);
			o.setCleanService("0");
		} else if (!(Boolean)mapCheck.get("cleanFlag") && cleanService != null) {
			service.setId(CommonUtil.getUID());
			service.setShopId(o.getId());
			service.setServiceType(4100);
			service.setDeleteFlag(0);
			serviceService.addData(service);
			o.setCleanService("4100");
		}
		// 修车
		service = new ServiceEn();
		if ((Boolean)mapCheck.get("repairFlag") && repairService == null) {
			service.setDeleteFlag(1);
			service.setId(mapCheck.get("repairId").toString());
			serviceService.updateData(service);
			o.setRepairService("0");
		} else if (!(Boolean)mapCheck.get("repairFlag") && repairService != null) {
			service.setId(CommonUtil.getUID());
			service.setShopId(o.getId());
			service.setServiceType(5100);
			service.setDeleteFlag(0);
			serviceService.addData(service);
			o.setRepairService("5100");
		}
		// 保养
		service = new ServiceEn();
		if ((Boolean)mapCheck.get("mainFlag") && maintainService == null) {
			service.setDeleteFlag(1);
			service.setId(mapCheck.get("mainId").toString());
			serviceService.updateData(service);
			o.setMaintainService("0");
		} else if (!(Boolean)mapCheck.get("mainFlag") && maintainService != null) {
			service.setId(CommonUtil.getUID());
			service.setShopId(o.getId());
			service.setServiceType(6100);
			service.setDeleteFlag(0);
			serviceService.addData(service);
			o.setMaintainService("6100");
		}
		// 轮胎
		service = new ServiceEn();
		if ((Boolean)mapCheck.get("tyreFlag") && tyreService == null) {
			service.setDeleteFlag(1);
			service.setId(mapCheck.get("tyreId").toString());
			serviceService.updateData(service);
			o.setTyreService("0");
		} else if (!(Boolean)mapCheck.get("tyreFlag") && tyreService != null) {
			service.setId(CommonUtil.getUID());
			service.setShopId(o.getId());
			service.setServiceType(7100);
			service.setDeleteFlag(0);
			serviceService.addData(service);
			o.setTyreService("7100");
		}
		
		return dao.updateByPrimaryKeySelective(o);
	}
	
	@Override
	public void exprotData(OutputStream out) throws IOException {
		// 声明一个工作薄   
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("店铺信息");
        // 生成一个标题样式   
        HSSFCellStyle style = com.gtercn.carhome.cms.util.ExcelStyle.titleStyle(workbook);
        
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        // 设置行高
        row.setHeightInPoints(19);
        //1.救援服务
        HSSFCell cell1 = row.createCell(0); 
        cell1.setCellStyle(style);  
        HSSFRichTextString text1 = new HSSFRichTextString("救援服务");  
        cell1.setCellValue(text1);
        sheet.setColumnWidth(0, 6000);
        //2.修车服务
        HSSFCell cell2 = row.createCell(1); 
        cell2.setCellStyle(style);  
        HSSFRichTextString text2 = new HSSFRichTextString("修车服务");  
        cell2.setCellValue(text2);
        sheet.setColumnWidth(1, 6000);
        //3.洗车服务
        HSSFCell cell3 = row.createCell(2); 
        cell3.setCellStyle(style);  
        HSSFRichTextString text3 = new HSSFRichTextString("洗车服务");  
        cell3.setCellValue(text3);
        sheet.setColumnWidth(2, 6000);
        //4.保养服务
        HSSFCell cell4 = row.createCell(3); 
        cell4.setCellStyle(style);  
        HSSFRichTextString text4 = new HSSFRichTextString("保养服务");  
        cell4.setCellValue(text4);
        sheet.setColumnWidth(3, 6000);
        //5.轮胎服务
        HSSFCell cell5 = row.createCell(4); 
        cell5.setCellStyle(style);  
        HSSFRichTextString text5 = new HSSFRichTextString("轮胎服务");  
        cell5.setCellValue(text5);
        sheet.setColumnWidth(4, 6000);
        //6.店铺名称
        HSSFCell cell6 = row.createCell(5); 
        cell6.setCellStyle(style);  
        HSSFRichTextString text6 = new HSSFRichTextString("店铺名称");  
        cell6.setCellValue(text6);
        sheet.setColumnWidth(5, 6000);
        //7.店铺评分
        HSSFCell cell7 = row.createCell(6); 
        cell7.setCellStyle(style);  
        HSSFRichTextString text7 = new HSSFRichTextString("店铺评分");  
        cell7.setCellValue(text7);
        sheet.setColumnWidth(6, 6000);
        //8.店铺描述
        HSSFCell cell8 = row.createCell(7); 
        cell8.setCellStyle(style);  
        HSSFRichTextString text8 = new HSSFRichTextString("店铺描述");  
        cell8.setCellValue(text8);
        sheet.setColumnWidth(7, 12000);
        //9.店铺所在地经度
        HSSFCell cell9 = row.createCell(8); 
        cell9.setCellStyle(style);  
        HSSFRichTextString text9 = new HSSFRichTextString("经度");  
        cell9.setCellValue(text9);
        sheet.setColumnWidth(8, 6000);
        //10.店铺所在地纬度
        HSSFCell cell10 = row.createCell(9); 
        cell10.setCellStyle(style);  
        HSSFRichTextString text10 = new HSSFRichTextString("纬度");  
        cell10.setCellValue(text10);
        sheet.setColumnWidth(9, 6000);
        //11.省
        HSSFCell cell11 = row.createCell(10); 
        cell11.setCellStyle(style);  
        HSSFRichTextString text11 = new HSSFRichTextString("省");  
        cell11.setCellValue(text11);
        sheet.setColumnWidth(10, 6000);
        //12.市
        HSSFCell cell12 = row.createCell(11); 
        cell12.setCellStyle(style);  
        HSSFRichTextString text12 = new HSSFRichTextString("市");  
        cell12.setCellValue(text12);
        sheet.setColumnWidth(11, 6000);
        //13.区
//        HSSFCell cell13 = row.createCell(12); 
//        cell13.setCellStyle(style);  
//        HSSFRichTextString text13 = new HSSFRichTextString("城市编码");  
//        cell13.setCellValue(text13);
//        sheet.setColumnWidth(12, 6000);
        //14.详细地址
        HSSFCell cell13 = row.createCell(12); 
        cell13.setCellStyle(style);  
        HSSFRichTextString text13 = new HSSFRichTextString("详细地址");  
        cell13.setCellValue(text13);
        sheet.setColumnWidth(12, 12000);
        //15.联系电话
        HSSFCell cell14 = row.createCell(13); 
        cell14.setCellStyle(style);  
        HSSFRichTextString text14 = new HSSFRichTextString("联系电话");  
        cell14.setCellValue(text14);
        sheet.setColumnWidth(13, 8000);
        //16.救援类型
        HSSFCell cell15 = row.createCell(14); 
        cell15.setCellStyle(style);  
        HSSFRichTextString text15 = new HSSFRichTextString("救援类型");  
        cell15.setCellValue(text15);
        sheet.setColumnWidth(14, 8000);
        //17.救援经验
//        HSSFCell cell16 = row.createCell(15); 
//        cell16.setCellStyle(style);  
//        HSSFRichTextString text16 = new HSSFRichTextString("救援经验");  
//        cell16.setCellValue(text16);
//        sheet.setColumnWidth(15, 8000);
        //18.救援描述
        HSSFCell cell16 = row.createCell(15); 
        cell16.setCellStyle(style);  
        HSSFRichTextString text16 = new HSSFRichTextString("救援描述");  
        cell16.setCellValue(text16);
        sheet.setColumnWidth(15, 8000);
        
        // 生成一个表格
        HSSFSheet sheet1 = workbook.createSheet("数据说明");
        
        // 产生表格标题行
        HSSFRow row1 = sheet1.createRow(0);
        HSSFCell cell21 = row1.createCell(0);
        HSSFRichTextString text21 = new HSSFRichTextString("电话之间注意用半角逗号分开','！");  
        cell21.setCellValue(text21);
        row1.setHeightInPoints(19); 
        sheet1.setColumnWidth(0, 2500);
        
        HSSFRow row4 = sheet1.createRow(1);
        HSSFCell cella = row4.createCell(0);
        HSSFRichTextString texta = new HSSFRichTextString("救援类型:1:现场抢修,2:拖车,3:紧急加水,4:紧急送油,5:配钥匙！");
        cella.setCellValue(texta);
        row4.setHeightInPoints(19);
        
        HSSFRow row5 = sheet1.createRow(2);
        HSSFCell cellb = row5.createCell(0);
        HSSFRichTextString textb = new HSSFRichTextString("救援服务：2000；洗车服务：4100；修车服务：5100；保养服务：6100；轮胎服务：7100；不存在这些服务填写0！");
        cellb.setCellValue(textb);
        row5.setHeightInPoints(19);
        
        HSSFRow row6 = sheet1.createRow(3);
        HSSFCell cellc = row6.createCell(0);
        HSSFRichTextString textc = new HSSFRichTextString("联系电话在填写时，请把单元格格式选择为文本！");
        cellc.setCellValue(textc);
        row6.setHeightInPoints(19);
        
        //例如
        HSSFRow row2 = sheet1.createRow(4);
        HSSFCell cell22 = row2.createCell(0); 
        HSSFRichTextString text22 = new HSSFRichTextString("例如：");  
        cell22.setCellValue(text22);
        sheet1.setColumnWidth(0, 4500);
        
        HSSFRow row3 = sheet1.createRow(5);
        
        HSSFCell cell23 = row3.createCell(0);
        cell23.setCellStyle(style);  
        HSSFRichTextString text23 = new HSSFRichTextString("救援服务");
        cell23.setCellValue(text23);
        row3.setHeightInPoints(18);
        sheet1.setColumnWidth(0, 4500);
        
        HSSFCell cell24 = row3.createCell(1); 
        cell24.setCellStyle(style);  
        HSSFRichTextString text24 = new HSSFRichTextString("修车服务");  
        cell24.setCellValue(text24);
        sheet1.setColumnWidth(1, 4500);
        
        HSSFCell cell25 = row3.createCell(2); 
        cell25.setCellStyle(style);  
        HSSFRichTextString text25 = new HSSFRichTextString("洗车服务");  
        cell25.setCellValue(text25);
        sheet1.setColumnWidth(2, 4500);
        
        HSSFCell cell26 = row3.createCell(3); 
        cell26.setCellStyle(style);  
        HSSFRichTextString text26 = new HSSFRichTextString("保养服务");  
        cell26.setCellValue(text26);
        sheet1.setColumnWidth(3, 4500);
        
        HSSFCell cell27 = row3.createCell(4); 
        cell27.setCellStyle(style);  
        HSSFRichTextString text27 = new HSSFRichTextString("轮胎服务");  
        cell27.setCellValue(text27);
        sheet1.setColumnWidth(4, 4500);
        
        HSSFCell cell28 = row3.createCell(5); 
        cell28.setCellStyle(style);  
        HSSFRichTextString text28 = new HSSFRichTextString("店铺名称");  
        cell28.setCellValue(text28);
        sheet1.setColumnWidth(5, 4500);
        
        HSSFCell cell29 = row3.createCell(6); 
        cell29.setCellStyle(style);  
        HSSFRichTextString text29 = new HSSFRichTextString("店铺评分");  
        cell29.setCellValue(text29);
        sheet1.setColumnWidth(6, 4500);
        
        HSSFCell cell30 = row3.createCell(7); 
        cell30.setCellStyle(style);  
        HSSFRichTextString text30 = new HSSFRichTextString("店铺描述");  
        cell30.setCellValue(text30);
        sheet1.setColumnWidth(7, 12000);
        
        HSSFCell cell31 = row3.createCell(8); 
        cell31.setCellStyle(style);  
        HSSFRichTextString text31 = new HSSFRichTextString("经度");  
        cell31.setCellValue(text31);
        sheet1.setColumnWidth(8, 4500);
        
        HSSFCell cell32 = row3.createCell(9); 
        cell32.setCellStyle(style);  
        HSSFRichTextString text32 = new HSSFRichTextString("纬度");  
        cell32.setCellValue(text32);
        sheet1.setColumnWidth(9, 4500);
        
        HSSFCell cell33 = row3.createCell(10); 
        cell33.setCellStyle(style);  
        HSSFRichTextString text33 = new HSSFRichTextString("省");  
        cell33.setCellValue(text33);
        sheet1.setColumnWidth(10, 4500);
        
        HSSFCell cell34 = row3.createCell(11); 
        cell34.setCellStyle(style);  
        HSSFRichTextString text34 = new HSSFRichTextString("市");  
        cell34.setCellValue(text34);
        sheet1.setColumnWidth(11, 4500);
        
//        HSSFCell cell35 = row3.createCell(12); 
//        cell35.setCellStyle(style);  
//        HSSFRichTextString text35 = new HSSFRichTextString("城市编码");  
//        cell35.setCellValue(text35);
//        sheet1.setColumnWidth(12, 4500);
        
        HSSFCell cell35 = row3.createCell(12); 
        cell35.setCellStyle(style);  
        HSSFRichTextString text35 = new HSSFRichTextString("详细地址");  
        cell35.setCellValue(text35);
        sheet1.setColumnWidth(12, 12000);
        
        HSSFCell cell36 = row3.createCell(13); 
        cell36.setCellStyle(style);  
        HSSFRichTextString text36 = new HSSFRichTextString("联系电话");  
        cell36.setCellValue(text36);
        sheet1.setColumnWidth(13, 4500);
        
        HSSFCell cell37 = row3.createCell(14); 
        cell37.setCellStyle(style);  
        HSSFRichTextString text37 = new HSSFRichTextString("救援类型");  
        cell37.setCellValue(text37);
        sheet1.setColumnWidth(14, 4500);
        
//        HSSFCell cell38 = row3.createCell(15); 
//        cell38.setCellStyle(style);  
//        HSSFRichTextString text38 = new HSSFRichTextString("救援经验");  
//        cell38.setCellValue(text38);
//        sheet1.setColumnWidth(15, 4500);
        
        HSSFCell cell38 = row3.createCell(15); 
        cell38.setCellStyle(style);  
        HSSFRichTextString text38 = new HSSFRichTextString("救援描述");  
        cell38.setCellValue(text38);
        sheet1.setColumnWidth(15, 12000);
        
        HSSFRow rowInfo = sheet1.createRow(6);
        HSSFCellStyle styleInfo = ExcelStyle.infoStyle(workbook,0); 

        HSSFCell cellInfo1 = rowInfo.createCell(0); 
        cellInfo1.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo1 = new HSSFRichTextString("2000");  
        cellInfo1.setCellValue(textInfo1);
        
        HSSFCell cellInfo2 = rowInfo.createCell(1); 
        cellInfo2.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo2 = new HSSFRichTextString("5100");  
        cellInfo2.setCellValue(textInfo2);
        
        HSSFCell cellInfo3 = rowInfo.createCell(2); 
        cellInfo3.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo3 = new HSSFRichTextString("0");  
        cellInfo3.setCellValue(textInfo3);
        
        HSSFCell cellInfo4 = rowInfo.createCell(3); 
        cellInfo4.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo4 = new HSSFRichTextString("6100");  
        cellInfo4.setCellValue(textInfo4);
        
        HSSFCell cellInfo5 = rowInfo.createCell(4); 
        cellInfo5.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo5 = new HSSFRichTextString("0");  
        cellInfo5.setCellValue(textInfo5);
        
        HSSFCell cellInfo6 = rowInfo.createCell(5); 
        cellInfo6.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo6 = new HSSFRichTextString("维修中心");  
        cellInfo6.setCellValue(textInfo6);
        
        HSSFCell cellInfo7 = rowInfo.createCell(6); 
        cellInfo7.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo7 = new HSSFRichTextString("5");  
        cellInfo7.setCellValue(textInfo7);
        
        HSSFCell cellInfo8 = rowInfo.createCell(7); 
        cellInfo8.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo8 = new HSSFRichTextString("公司位于阜新市XXX路，地理位置优越，主要经营汽车轮胎，保养，快修。");  
        cellInfo8.setCellValue(textInfo8);
        
        HSSFCell cellInfo9 = rowInfo.createCell(8); 
        cellInfo9.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo9 = new HSSFRichTextString("121.1");  
        cellInfo9.setCellValue(textInfo9);
        
        HSSFCell cellInfo10 = rowInfo.createCell(9); 
        cellInfo10.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo10 = new HSSFRichTextString("42");  
        cellInfo10.setCellValue(textInfo10);
        
        HSSFCell cellInfo11 = rowInfo.createCell(10); 
        cellInfo11.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo11 = new HSSFRichTextString("辽宁省");  
        cellInfo11.setCellValue(textInfo11);
        
        HSSFCell cellInfo12 = rowInfo.createCell(11); 
        cellInfo12.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo12 = new HSSFRichTextString("阜新市");  
        cellInfo12.setCellValue(textInfo12);
        
//        HSSFCell cellInfo13 = rowInfo.createCell(12); 
//        cellInfo13.setCellStyle(styleInfo);  
//        HSSFRichTextString textInfo13 = new HSSFRichTextString("210900");  
//        cellInfo13.setCellValue(textInfo13);
        
        HSSFCell cellInfo13 = rowInfo.createCell(12); 
        cellInfo13.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo13 = new HSSFRichTextString("阜蒙县三沟救场西二百米路北");  
        cellInfo13.setCellValue(textInfo13);
        
        HSSFCell cellInfo14 = rowInfo.createCell(13); 
        cellInfo14.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo14 = new HSSFRichTextString("15001020304");  
        cellInfo14.setCellValue(textInfo14);
        
        HSSFCell cellInfo15 = rowInfo.createCell(14); 
        cellInfo15.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo15 = new HSSFRichTextString("1,2,3,4,5");  
        cellInfo15.setCellValue(textInfo15);
        
//        HSSFCell cellInfo16 = rowInfo.createCell(15); 
//        cellInfo16.setCellStyle(styleInfo);  
//        HSSFRichTextString textInfo16 = new HSSFRichTextString("5");  
//        cellInfo16.setCellValue(textInfo16);
        
        HSSFCell cellInfo16 = rowInfo.createCell(15); 
        cellInfo16.setCellStyle(styleInfo);  
        HSSFRichTextString textInfo16 = new HSSFRichTextString("各种车辆钣金、喷漆、烤漆、内设高档烤漆房、精美车辆饰品、座垫");  
        cellInfo16.setCellValue(textInfo16);
        
        workbook.write(out);
	}
	
	@Override
	public void addImport(List<Shop> list) throws Exception {
		//dao.deleteAllInfo();
		
		List<Shop> shopList = dao.selectAllInfo();
		boolean flag = false;
		
		//增量部署
		for (Shop shop : list) {
			// 判断新增的店铺是否已经存在
			for (int i = 0; i < shopList.size(); i++) {
				if (StringUtils.equals(shop.getShopName(), shopList.get(i).getShopName())
						&& StringUtils.equals(shop.getCityCode(), shopList.get(i).getCityCode())) {
					flag = true;
					break;
				}
			}
			// 存在跳出循环
			if (flag) {
				flag = false;
				continue;
			}
			
			shop.setId(CommonUtil.getUID());
			shop.setDeleteFlag(0);
			shop.setIsTop(0);
			shop.setDisplayPriority(0);
			dao.insert(shop);
			
			//服务表添加数据
			ServiceEn serviceEn = new ServiceEn();
			if (StringUtils.equals("5100", shop.getRepairService())) {
				serviceEn.setId(CommonUtil.getUID());
				serviceEn.setShopId(shop.getId());
				serviceEn.setServiceType(5100);
				serviceEn.setDeleteFlag(0);
				serviceService.addData(serviceEn);
			} 
			if (StringUtils.equals("4100", shop.getCleanService())) {
				serviceEn = new ServiceEn();
				serviceEn.setId(CommonUtil.getUID());
				serviceEn.setShopId(shop.getId());
				serviceEn.setServiceType(4100);
				serviceEn.setDeleteFlag(0);
				serviceService.addData(serviceEn);
			}
			if (StringUtils.equals("6100", shop.getMaintainService())) {
				serviceEn = new ServiceEn();
				serviceEn.setId(CommonUtil.getUID());
				serviceEn.setShopId(shop.getId());
				serviceEn.setServiceType(6100);
				serviceEn.setDeleteFlag(0);
				serviceService.addData(serviceEn);
			}
			if (StringUtils.equals("7100", shop.getTyreService())) {
				serviceEn = new ServiceEn();
				serviceEn.setId(CommonUtil.getUID());
				serviceEn.setShopId(shop.getId());
				serviceEn.setServiceType(7100);
				serviceEn.setDeleteFlag(0);
				serviceService.addData(serviceEn);
			}
			
			// 救援表添加数据
			Rescue rescue = new Rescue();
			if (StringUtils.isNotBlank(shop.getTypeValue())) {
				rescue.setId(CommonUtil.getUID());
				rescue.setShopId(shop.getId());
				rescue.setTypeValue(shop.getTypeValue());
				rescue.setExperience(shop.getExperience());
				rescue.setProductDescription(shop.getProductDescription());
				rescue.setDeleteFlag(0);
				rescueService.addData(rescue);
			}
		}
	}

	@Override
	public Shop queryShopByName(Map<String, Object> map) {
		return dao.queryShopByName(map);
	}
}
