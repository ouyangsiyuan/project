package com.ousy.petadopt.controller;


import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptApply;
import com.ousy.petadopt.entity.AdoptInfo;
import com.ousy.petadopt.service.AdoptApplyService;
import com.ousy.petadopt.service.AdoptInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 申请领养表 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/adoptApply")
@Api(value = "申请领养信息管理", tags = "申请领养信息管理")
public class AdoptApplyController {
	@Resource
	private AdoptApplyService service;

	@ApiOperation(value = "新增申请领养信息", notes = "新增申请领养信息")
	@PostMapping("/add")
	public ResultDto<Boolean> add(@RequestBody AdoptApply adoptApply){
		return service.add(adoptApply);
	}
}

