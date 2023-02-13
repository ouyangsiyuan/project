package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptApplyLog;
import com.ousy.petadopt.mapper.AdoptApplyLogMapper;
import com.ousy.petadopt.service.AdoptApplyLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 申请领养表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
public class AdoptApplyLogServiceImpl extends ServiceImpl<AdoptApplyLogMapper, AdoptApplyLog> implements AdoptApplyLogService {

	@Resource
	private AdoptApplyLogMapper mapper;

	@Override
	public ResultDto<Boolean> add(AdoptApplyLog adoptApplyLog) {
		String errMsg = this.checkParams(adoptApplyLog);
		if(StringUtils.isNotEmpty(errMsg)){
			return ResultDto.error(errMsg);
		}
		return new ResultDto<>().data(mapper.insert(adoptApplyLog));
	}

	private String checkParams(AdoptApplyLog adoptApplyLog){
		if(adoptApplyLog.getAdoptId()==null || adoptApplyLog.getAdoptId()==0){
			return "领养信息ID不可为空，请检查！";
		}
		if(adoptApplyLog.getApplyId()==null || adoptApplyLog.getApplyId()==0){
			return "申请领养信息ID不可为空，请检查！";
		}
		return  "";
	}
}
