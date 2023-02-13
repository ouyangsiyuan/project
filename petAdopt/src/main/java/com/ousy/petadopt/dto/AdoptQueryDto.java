package com.ousy.petadopt.dto;

import com.ousy.petadopt.common.PageQueryDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title: AdoptQueryDto
 * @Author miller.ouyang
 * @Date: 2023/1/6 16:55
 * @Version 1.0
 */
@Data
public class AdoptQueryDto extends PageQueryDto {
	@ApiModelProperty(value = "物种（0-狗狗；1-猫猫；2-其他）")
	private String species;
	@ApiModelProperty(value = "状态（0-待审核；1-待领养；2-已领养；3-已下架；4-已删除；5-已拒绝；6-待修改）")
	private String status;
	@ApiModelProperty(value = "标题")
	private String title;
}
