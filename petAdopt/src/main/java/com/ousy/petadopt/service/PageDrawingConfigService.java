package com.ousy.petadopt.service;

import com.ousy.petadopt.entity.PageDrawingConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 页面图配置表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface PageDrawingConfigService extends IService<PageDrawingConfig> {
	PageDrawingConfig getConfigByType(String type);
}
