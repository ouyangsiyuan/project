package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.Role;
import com.ousy.petadopt.mapper.RoleMapper;
import com.ousy.petadopt.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Resource
	private RoleMapper mapper;

	@Override
	public ResultDto<Boolean> add(Role role) {
		int i = mapper.insert(role);
		if(i>0){
			return new ResultDto<>().data(true);
		}
		return new ResultDto<>().data(false);
	}

	@Override
	public ResultDto<Role> getById(Integer id) {
		Role role = mapper.selectById(id);
		if(role==null){
			return new ResultDto<>();
		}
		return new ResultDto<>().data(role);
	}

	@Override
	public ResultDto<List<Role>> getRoleByParams(Role role) {
		QueryWrapper<Role> qw = new QueryWrapper<>();
		List<Role> roles = mapper.selectList(qw);
		if(CollectionUtils.isEmpty(roles)){
			return new ResultDto<>();
		}
		return new ResultDto<>().data(roles);
	}

	@Override
	public ResultDto<Boolean> update(Role role) {
		if(role.getId()==null||role.getId()==0){
			return new ResultDto<>().error("ID不可为空，请检查！");
		}
		int i = mapper.updateById(role);
		if(i>0){
			return new ResultDto<>().data(true);
		}
		return new ResultDto<>().data(false);
	}

	@Override
	public ResultDto<Boolean> delete(Integer id) {
		if(id==null || id == 0){
			return new ResultDto<>().error("ID不可为空，请检查！");
		}
		int i = mapper.deleteById(id);
		if(i>0){
			return new ResultDto<>().data(true);
		}
		return new ResultDto<>().data(false);
	}
}
