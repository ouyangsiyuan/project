package com.ousy.petadopt.controller;


import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.RescueStationInfo;
import com.ousy.petadopt.service.RescueStationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.relation.RelationSupport;
import java.util.List;

/**
 * <p>
 * 救助站信息表 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/rescueStationInfo")
@Api(value = "救助站信息管理",tags = "救助站信息管理")
public class RescueStationInfoController {
	@Resource
	private RescueStationInfoService service;

	@ApiOperation(value = "查询救助站信息-同城优先", notes = "查询救助站信息-同城优先")
	@PostMapping("getList/{city}")
	public ResultDto<List<RescueStationInfo>> getList(@PathVariable("city")String city){
		return service.getList(city);
	}

	@ApiOperation(value = "根据用户ID查询所属救助站信息", notes = "根据用户ID查询所属救助站信息")
	@PostMapping("getByUserId/{userId}")
	public ResultDto<RescueStationInfo> getList(@PathVariable("userId")Integer userId){
		return service.getByUserId(userId);
	}
}

