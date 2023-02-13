package com.ousy.petadopt.controller;


import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.dto.UserLoginAddrDto;
import com.ousy.petadopt.entity.Role;
import com.ousy.petadopt.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色管理", tags = {"角色管理"})
public class RoleController {

	@Resource
	private RoleService service;

	@ApiOperation(value = "新增角色", notes = "新增角色")
	@PostMapping("/add")
	public ResultDto<Boolean> add(@RequestBody Role role){
		return service.add(role);
	}

	@ApiOperation(value = "删除角色", notes = "删除角色")
	@PostMapping("/delete/{id}")
	public ResultDto<Boolean> delete(@PathVariable("id") Integer id){
		return service.delete(id);
	}

	@ApiOperation(value = "修改角色", notes = "修改角色")
	@PostMapping("/update")
	public ResultDto<Boolean> delete(@RequestBody Role role){
		return service.update(role);
	}

	@ApiOperation(value = "根据ID获取角色", notes = "根据ID获取角色")
	@PostMapping("/getById/{id}")
	public ResultDto<Role> getById(@PathVariable("id") Integer id){
		return service.getById(id);
	}

	@ApiOperation(value = "根据条件查询角色", notes = "根据条件查询角色")
	@PostMapping("/getRoleByParams")
	public ResultDto<List<Role>> getRoleByParams(@RequestBody Role role){
		return service.getRoleByParams(role);
	}

}

