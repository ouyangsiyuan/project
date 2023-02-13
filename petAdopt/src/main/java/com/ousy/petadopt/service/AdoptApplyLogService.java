package com.ousy.petadopt.service;

import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptApply;
import com.ousy.petadopt.entity.AdoptApplyLog;
import com.ousy.petadopt.entity.AdoptApplyLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 申请领养表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface AdoptApplyLogService extends IService<AdoptApplyLog> {
	ResultDto<Boolean> add (AdoptApplyLog adoptApplyLog);
}
