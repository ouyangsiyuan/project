package com.ousy.petadopt.service;

import com.ousy.petadopt.common.PageResultDto;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.dto.AdoptQueryDto;
import com.ousy.petadopt.entity.AdoptInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ousy.petadopt.vo.AdoptStatisticsVo;
import com.ousy.petadopt.vo.IndexAdoptVo;
import com.ousy.petadopt.vo.IndexInfoVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 领养信息表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface AdoptInfoService extends IService<AdoptInfo> {

	PageResultDto<AdoptInfo> getPageList(AdoptQueryDto dto);

	ResultDto<List<AdoptInfo>> getListByTitle(String title);

	void setIndexAdoptInfo(IndexInfoVo vo , String city);

	ResultDto<List<IndexAdoptVo>> getListOrderByLikeNum(String species);

	ResultDto<Boolean> add(AdoptInfo info);

	ResultDto<Boolean> update(AdoptInfo info);

	ResultDto<Boolean> updateStatus(AdoptInfo info);

	void downloadTemplate(HttpServletResponse response);

	void uploadAdopt(MultipartFile file);

	ResultDto<AdoptStatisticsVo> statisticsAdopt();
}
