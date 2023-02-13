package com.ousy.petadopt.service;

import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptApply;
import com.ousy.petadopt.entity.AdoptApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 申请领养表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface AdoptApplyService extends IService<AdoptApply> {
	ResultDto<Boolean> add (AdoptApply adoptApply);
}
