package com.ousy.petadopt.service;

import com.ousy.petadopt.entity.GeneralizationBitConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 推广位配置表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface GeneralizationBitConfigService extends IService<GeneralizationBitConfig> {
	List<GeneralizationBitConfig> getList (List<String> codes);
}
