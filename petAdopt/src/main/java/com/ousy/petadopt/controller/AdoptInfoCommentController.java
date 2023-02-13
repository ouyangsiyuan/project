package com.ousy.petadopt.controller;


import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.entity.AdoptInfoComment;
import com.ousy.petadopt.service.AdoptInfoCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 领养信息评论表 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/adoptInfoComment")
@Api(value = "领养评论管理", tags = "领养评论管理")
public class AdoptInfoCommentController {

	@Resource
	private AdoptInfoCommentService service;

	@ApiOperation(value = "根据领养信息查询评论", notes = "根据领养信息查询评论")
	@PostMapping("/getCommentByAdopt/{adoptId}")
	public ResultDto<AdoptInfoComment> getCommentByAdopt(@PathVariable("adoptId")Integer adoptId){
		return service.getByAdoptId(adoptId);
	}

	@ApiOperation(value = "发表评论", notes = "发表评论")
	@PostMapping("/add")
	public ResultDto<Boolean> addComment(@RequestBody AdoptInfoComment adoptInfoComment){
		return service.addComment(adoptInfoComment);
	}
}

