package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptInfoComment;
import com.ousy.petadopt.mapper.AdoptInfoCommentMapper;
import com.ousy.petadopt.service.AdoptInfoCommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 领养信息评论表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
public class AdoptInfoCommentServiceImpl extends ServiceImpl<AdoptInfoCommentMapper, AdoptInfoComment> implements AdoptInfoCommentService {

	@Resource
	AdoptInfoCommentMapper mapper;

	@Override
	public ResultDto<AdoptInfoComment> getByAdoptId(Integer adoptId) {
		QueryWrapper<AdoptInfoComment> qw = new QueryWrapper<>();
		qw.eq("adopt_id",adoptId);
		List<AdoptInfoComment> adoptInfoComments = mapper.selectList(qw);
		if(CollectionUtils.isEmpty(adoptInfoComments)){
			return new ResultDto<>();
		}
		return new ResultDto<>().data(adoptInfoComments);
	}

	@Override
	public ResultDto<Boolean> addComment(AdoptInfoComment adoptInfoComment) {
		if(adoptInfoComment.getAdoptId()==null || adoptInfoComment.getAdoptId() == 0){
			return ResultDto.error("领养ID信息不可为空，请检查！");
		}
		if(StringUtils.isEmpty(adoptInfoComment.getCommentContent())){
			return ResultDto.error("评论内容不可为空，请检查！");
		}
		return new ResultDto<>().data(mapper.insert(adoptInfoComment));
	}
}
