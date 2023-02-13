package com.ousy.petadopt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title: UserLoginArreDto
 * @Author miller.ouyang
 * @Date: 2023/1/5 14:05
 * @Version 1.0
 */

@Data
public class UserLoginAddrDto {
	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "用户名")
	private String name;

	@ApiModelProperty(value = "手机号码")
	private String phone;

	@ApiModelProperty(value = "状态（0-启用；1-停用）")
	private String status;

	@ApiModelProperty(value = "最后登录地点")
	private String lastLoginAddr;
}
