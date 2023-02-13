package com.ousy.petadopt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: UploadDto
 * @Author miller.ouyang
 * @Date: 2023/2/13 14:00
 * @Version 1.0
 */
@Data
public class UploadDto implements Serializable {
	private static final long serialVersionUID = 1L;
	/**图片名*/
	private String name;
	/**图片路径*/
	private String imgUrl;
	/**上传日期*/
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
}
