package com.ousy.petadopt.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    首页领养信息VO
 * @title: IndexAdoptVo
 * @Author miller.ouyang
 * @Date: 2023/1/5 15:14
 * @Version 1.0
 */
@Data
public class IndexAdoptVo {
	@ApiModelProperty(value = "领养信息ID")
	private Integer adoptId;

	@ApiModelProperty(value = "点赞数")
	private Integer likeNum;

	@ApiModelProperty(value = "图片url")
	private String pictureUrl;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "饲主名称")
	private String ownerName;

}
