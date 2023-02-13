package com.ousy.petadopt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title: IndexQueryDto
 * @Author miller.ouyang
 * @Date: 2023/1/5 15:49
 * @Version 1.0
 */

@Data
public class IndexQueryDto {
	@ApiModelProperty(value = "用户ID")
	private Integer userId;
	@ApiModelProperty(value = "城市")
	private String city;
}
