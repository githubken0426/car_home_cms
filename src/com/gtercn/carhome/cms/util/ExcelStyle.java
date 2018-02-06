package com.gtercn.carhome.cms.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelStyle {
	/**
	 * 表头样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle titleStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        //设置填充方式
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //设置前景色
        style.setFillForegroundColor(HSSFColor.YELLOW.index);
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setFontHeightInPoints((short) 13);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        return style;
	}
	
	/**
	 * 内容样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle infoStyle(HSSFWorkbook workbook,int color){
		HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setFontHeightInPoints((short) 10); 
        if(color==0){//gray
        	font.setColor(HSSFColor.GREY_80_PERCENT.index);
        }
        if(color==1){//red
        	font.setColor(HSSFColor.RED.index);
        }
        if(color==2){//GREEN
        	font.setColor(HSSFColor.GREEN.index);
        }
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        return style;
	}
}
