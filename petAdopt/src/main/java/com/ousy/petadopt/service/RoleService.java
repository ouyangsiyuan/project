package com.ousy.petadopt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface RoleService extends IService<Role> {
	ResultDto<Boolean> add (Role role);

	ResultDto<Role> getById (Integer id);

	ResultDto<List<Role>> getRoleByParams(Role role);

	ResultDto<Boolean> update (Role role);

	ResultDto<Boolean> delete (Integer id);
}
