package com.ousy.petadopt.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.NUMERIC;
import static org.apache.poi.ss.usermodel.DataValidationConstraint.ValidationType.FORMULA;
import static org.apache.tomcat.util.bcel.classfile.ElementValue.STRING;

/**
 * Created by ws
 * Date :2022/4/29
 * Description : excel导入工具类
 * Version :1.0
 */
@Slf4j
public class ExcelUtils {

	public static void downloadTemplate(HttpServletResponse response){
		log.info("下载模板开始~");
		//获取要下载的模板名称
		String fileName = "template.xlsx";
		//设置要下载的文件的名称
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		//通知客服文件的MIME类型
		response.setContentType("application/vnd.ms-template;charset=UTF-8");
		//获取文件的路径
		try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/批量领养信息发布模板.xlsx")) {
			//读取excel模板
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			log.error("下载模板出错：" + e);
		}
	}
}
