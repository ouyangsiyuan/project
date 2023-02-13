package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ousy.petadopt.entity.PageDrawingConfig;
import com.ousy.petadopt.mapper.PageDrawingConfigMapper;
import com.ousy.petadopt.service.PageDrawingConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.service.PageDrawingConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 页面图配置表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
public class PageDrawingConfigServiceImpl extends ServiceImpl<PageDrawingConfigMapper, PageDrawingConfig> implements PageDrawingConfigService {

	@Resource
	private PageDrawingConfigMapper mapper;
	@Override
	public PageDrawingConfig getConfigByType(String type) {
		QueryWrapper<PageDrawingConfig> qw = new QueryWrapper<>();
		qw.eq("drawing_type",type);
		PageDrawingConfig config = mapper.selectOne(qw);
		if(config==null){
			return null;
		}
		return config;
	}
}
