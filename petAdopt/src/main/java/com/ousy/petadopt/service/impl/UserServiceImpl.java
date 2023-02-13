package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.common.SystemConstant;
import com.ousy.petadopt.dto.UserLoginAddrDto;
import com.ousy.petadopt.entity.User;
import com.ousy.petadopt.entity.User;
import com.ousy.petadopt.mapper.UserMapper;
import com.ousy.petadopt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Resource
	private UserMapper mapper;

	@Override
	public ResultDto<Boolean> updateLastLogin(UserLoginAddrDto dto) {
		try {
			if(StringUtils.isEmpty(dto.getPhone())){
				return ResultDto.error("用户电话号码不可为空，请检查！");
			}
			if(StringUtils.isEmpty(dto.getLastLoginAddr())){
				dto.setLastLoginAddr("南京");
			}
			User user = new User();
			BeanUtils.copyProperties(dto,user);
			user.setLastLoginDate(new Date());
			user.setUpdatedBy("admin");
			user.setUpdatedDate(new Date());
			return new ResultDto<>().data(this.updateById(user));
		}catch (Exception e){
			log.error(SystemConstant.ERROR_MSG_LOG,e);
			return ResultDto.error(SystemConstant.ERROR_MSG_RESULT);
		}

	}

	@Override
	public ResultDto<Boolean> add(User user) {
		int i = mapper.insert(user);
		if(i>0){
			return new ResultDto<>().data(true);
		}
		return new ResultDto<>().data(false);
	}

	@Override
	public ResultDto<User> getById(Integer id) {
		User user = mapper.selectById(id);
		if(user==null){
			return new ResultDto<>();
		}
		return new ResultDto<>().data(user);
	}

	@Override
	public ResultDto<List<User>> getUserByParams(User user) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		List<User> users = mapper.selectList(qw);
		if(CollectionUtils.isEmpty(users)){
			return new ResultDto<>();
		}
		return new ResultDto<>().data(users);
	}

	@Override
	public ResultDto<Boolean> update(User user) {
		if(user.getId()==null||user.getId()==0){
			return new ResultDto<>().error("ID不可为空，请检查！");
		}
		int i = mapper.updateById(user);
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
