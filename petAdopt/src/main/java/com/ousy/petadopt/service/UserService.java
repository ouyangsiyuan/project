package com.ousy.petadopt.service;

import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.dto.UserLoginAddrDto;
import com.ousy.petadopt.entity.User;
import com.ousy.petadopt.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface UserService extends IService<User> {

	ResultDto<Boolean> updateLastLogin(UserLoginAddrDto dto);

	ResultDto<Boolean> add (User user);

	ResultDto<User> getById (Integer id);

	ResultDto<List<User>> getUserByParams(User user);

	ResultDto<Boolean> update (User user);

	ResultDto<Boolean> delete (Integer id);
	
}
