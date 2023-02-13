package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptInfo;
import com.ousy.petadopt.entity.RescueStationInfo;
import com.ousy.petadopt.entity.User;
import com.ousy.petadopt.mapper.RescueStationInfoMapper;
import com.ousy.petadopt.service.RescueStationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.service.RescueStationInfoService;
import com.ousy.petadopt.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 救助站信息表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
public class RescueStationInfoServiceImpl extends ServiceImpl<RescueStationInfoMapper, RescueStationInfo> implements RescueStationInfoService {

	@Resource
	private RescueStationInfoMapper mapper;

	@Resource
	private UserService userService;

	@Override
	public ResultDto<List<RescueStationInfo>> getList(String city) {
		QueryWrapper<RescueStationInfo> qw = new QueryWrapper<>();
		qw.eq("status","0");
		List<RescueStationInfo> rescueStationInfos = mapper.selectList(qw);
		if(CollectionUtils.isEmpty(rescueStationInfos)){
			return new ResultDto<>();
		}
		List<RescueStationInfo> cityStation = new ArrayList<>();
		List<RescueStationInfo> notCityStation = new ArrayList<>();
		rescueStationInfos.forEach(info->{
			if(info.getStationAddr().contains(city)){
				cityStation.add(info);
			}else{
				notCityStation.add(info);
			}
		});
		cityStation.sort(Comparator.comparing(RescueStationInfo::getCreatedDate).reversed());
		notCityStation.sort(Comparator.comparing(RescueStationInfo::getCreatedDate).reversed());
		List<RescueStationInfo> sortList = new ArrayList<>();
		sortList.addAll(cityStation);
		sortList.addAll(notCityStation);
		return new ResultDto<>().data(sortList);
	}

	@Override
	public ResultDto<RescueStationInfo> getByUserId(Integer userId) {
		ResultDto<User> userResult = userService.getById(userId);
		User user = userResult.getData();
		Integer organizationId = user.getOrganizationId();
		if(organizationId==null || organizationId==0){
			return new ResultDto<>();
		}
		RescueStationInfo rescueStationInfo = mapper.selectById(organizationId);
		if(rescueStationInfo==null){
			return new ResultDto<>();
		}
		return new ResultDto<>().data(rescueStationInfo);
	}
}
