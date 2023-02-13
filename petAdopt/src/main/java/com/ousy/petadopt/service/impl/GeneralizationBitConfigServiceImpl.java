package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ousy.petadopt.entity.GeneralizationBitConfig;
import com.ousy.petadopt.mapper.GeneralizationBitConfigMapper;
import com.ousy.petadopt.service.GeneralizationBitConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.service.GeneralizationBitConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.CollationElementIterator;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 推广位配置表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
public class GeneralizationBitConfigServiceImpl extends ServiceImpl<GeneralizationBitConfigMapper, GeneralizationBitConfig> implements GeneralizationBitConfigService {

	@Resource
	private GeneralizationBitConfigMapper mapper;
	@Override
	public List<GeneralizationBitConfig> getList(List<String> codes) {
		QueryWrapper<GeneralizationBitConfig> qw = new QueryWrapper<>();
		qw.in("code", codes);
		List<GeneralizationBitConfig> generalizationBitConfigs = mapper.selectList(qw);
		if(CollectionUtils.isEmpty(generalizationBitConfigs)){
			return null;
		}
		return generalizationBitConfigs;
	}
}
