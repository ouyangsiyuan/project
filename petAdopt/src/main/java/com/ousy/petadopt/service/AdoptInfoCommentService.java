package com.ousy.petadopt.service;

import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptInfoComment;
import com.ousy.petadopt.entity.AdoptInfoComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 领养信息评论表 服务类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
public interface AdoptInfoCommentService extends IService<AdoptInfoComment> {

	ResultDto<AdoptInfoComment> getByAdoptId(Integer adoptId);

	ResultDto<Boolean> addComment(AdoptInfoComment adoptInfoComment);
}
