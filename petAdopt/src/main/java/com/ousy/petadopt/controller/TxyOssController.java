package com.ousy.petadopt.controller;

import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.dto.UploadDto;
import com.ousy.petadopt.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: OssController
 * @Author miller.ouyang
 * @Date: 2023/2/13 14:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/oss")
@Api(value = "腾讯云OSS操作管理", tags = "腾讯云OSS操作管理")
public class TxyOssController {
	@Resource
	private OssService ossService;

	@ApiOperation(value = "图片上传", notes = "图片上传")
	@PostMapping("/upload")
	public ResultDto<UploadDto> upload(List<MultipartFile> files){
		if(CollectionUtils.isEmpty(files)){
			return ResultDto.error("文件不可为空！");
		}
		files.forEach(file->{
			try {
				ossService.upload(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return ResultDto.ok();
	}


	@ApiOperation(value = "图片删除", notes = "图片删除")
	@PostMapping("/delete")
	public ResultDto<String> upload(String key){
		if(StringUtils.isEmpty(key)){
			return ResultDto.error("文件不可为空！");
		}
		ossService.delete(key);
		return ResultDto.ok();
	}
}
