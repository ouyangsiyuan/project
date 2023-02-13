package com.ousy.petadopt.service;

import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.RescueStationInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 救助站信息表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface RescueStationInfoService extends IService<RescueStationInfo> {

	ResultDto<List<RescueStationInfo>> getList (String city) ;

	ResultDto<RescueStationInfo>getByUserId(Integer userId);
}
