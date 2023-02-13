package com.ousy.petadopt.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    首页推广位信息
 * @title: IndexGeneralizationBitVo
 * @Author miller.ouyang
 * @Date: 2023/1/5 15:25
 * @Version 1.0
 */
@Data
public class IndexGeneralizationBitVo {

	@ApiModelProperty(value = "推广位ID")
	private Integer generalizationId;

	@ApiModelProperty(value = "推广位编号")
	private String code;

	@ApiModelProperty(value = "推广位标题")
	private String title;

	@ApiModelProperty(value = "推广位链接")
	private String link;

	@ApiModelProperty(value = "推广位图片1")
	private String pictureUrl1;

	@ApiModelProperty(value = "推广位图片2")
	private String pictureUrl2;

	@ApiModelProperty(value = "推广位图片3")
	private String pictureUrl3;

}
