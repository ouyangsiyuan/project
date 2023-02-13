package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptApply;
import com.ousy.petadopt.mapper.AdoptApplyMapper;
import com.ousy.petadopt.service.AdoptApplyService;
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
public class AdoptApplyServiceImpl extends ServiceImpl<AdoptApplyMapper, AdoptApply> implements AdoptApplyService {

	@Resource
	private AdoptApplyMapper mapper;

	@Override
	public ResultDto<Boolean> add(AdoptApply adoptApply) {
		String errMsg = this.checkParams(adoptApply);
		if(StringUtils.isNotEmpty(errMsg)){
			return ResultDto.error(errMsg);
		}
		return new ResultDto<>().data(mapper.insert(adoptApply));
	}

	private String checkParams(AdoptApply adoptApply){
		return  "";
	}
}
