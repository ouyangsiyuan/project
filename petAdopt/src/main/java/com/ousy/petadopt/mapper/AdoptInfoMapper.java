package com.ousy.petadopt.mapper;

import com.ousy.petadopt.entity.AdoptInfo;
import com.ousy.petadopt.entity.AdoptInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ousy.petadopt.vo.AdoptStatisticsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * 领养信息表 Mapper 接口
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Mapper
public interface AdoptInfoMapper extends BaseMapper<AdoptInfo> {

	AdoptStatisticsVo statisticsAdopt ();
}
