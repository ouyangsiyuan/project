package com.ousy.petadopt.controller;


import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptApplyLog;
import com.ousy.petadopt.service.AdoptApplyLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 申请领养日志表 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/adoptApplyLog")
@Api(value = "申请领养日志信息管理", tags = "申请领养日志信息管理")
public class AdoptApplyLogController {
	@Resource
	private AdoptApplyLogService service;

	@ApiOperation(value = "新增申请领养日志信息", notes = "新增申请领养日志信息")
	@PostMapping("/add")
	public ResultDto<Boolean> add(@RequestBody AdoptApplyLog adoptApplyLog){
		return service.add(adoptApplyLog);
	}
}

