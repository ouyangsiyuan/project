package com.ousy.petadopt.controller;


import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.dto.UserLoginAddrDto;
import com.ousy.petadopt.entity.User;
import com.ousy.petadopt.entity.User;
import com.ousy.petadopt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理", tags = {"用户管理"})
public class UserController {
	@Resource
	private UserService service;

	@ApiOperation(value = "新增角色", notes = "新增角色")
	@PostMapping("/add")
	public ResultDto<Boolean> add(@RequestBody User user){
		return service.add(user);
	}

	@ApiOperation(value = "删除角色", notes = "删除角色")
	@PostMapping("/delete/{id}")
	public ResultDto<Boolean> delete(@PathVariable("id") Integer id){
		return service.delete(id);
	}

	@ApiOperation(value = "修改角色", notes = "修改角色")
	@PostMapping("/update")
	public ResultDto<Boolean> delete(@RequestBody User user){
		return service.update(user);
	}

	@ApiOperation(value = "修改角色", notes = "修改角色")
	@PostMapping("/getById/{id}")
	public ResultDto<User> getById(@PathVariable("id") Integer id){
		return service.getById(id);
	}

	@ApiOperation(value = "根据条件查询角色", notes = "根据条件查询角色")
	@PostMapping("/getUserByParams")
	public ResultDto<List<User>> getUserByParams(@RequestBody User user){
		return service.getUserByParams(user);
	}

	@ApiOperation(value = "更新最后登录信息", notes = "更新最后登录信息")
	@PostMapping("/updateLoginAddr")
	public ResultDto<Boolean> updateLoginAddr(@RequestBody UserLoginAddrDto dto){
		return service.updateLastLogin(dto);
	}

}

