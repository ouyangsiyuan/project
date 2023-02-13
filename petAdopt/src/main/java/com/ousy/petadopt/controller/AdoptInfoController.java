package com.ousy.petadopt.controller;


import com.ousy.petadopt.common.PageResultDto;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.dto.AdoptQueryDto;
import com.ousy.petadopt.dto.UploadDto;
import com.ousy.petadopt.entity.AdoptInfo;
import com.ousy.petadopt.service.AdoptInfoService;
import com.ousy.petadopt.service.OssService;
import com.ousy.petadopt.vo.AdoptStatisticsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 领养信息表 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/adoptInfo")
@Api(value = "领养信息管理", tags = "领养信息管理")
public class AdoptInfoController {

	@Resource
	private AdoptInfoService service;

	@Resource
	private OssService ossService;

	@ApiOperation(value = "条件分页查询", notes = "条件分页查询")
	@PostMapping("/getPageList")
	public PageResultDto<AdoptInfo> getPageList(@RequestBody AdoptQueryDto dto){
		return service.getPageList(dto);
	}

	@ApiOperation(value = "根据标题模糊查询领养信息", notes = "根据标题模糊查询领养信息")
	@PostMapping("/getLikeTitle/{title}")
	public ResultDto<List<AdoptInfo>> getLikeTitle(@PathVariable("title") String title){
		return service.getListByTitle(title);
	}

	@ApiOperation(value = "根据ID查询领养信息", notes = "根据ID查询领养信息")
	@PostMapping("/getById/{id}")
	public ResultDto<AdoptInfo> getListByTitle(@PathVariable("id") String id){
		return new ResultDto<>().data(service.getById(id));
	}

	@ApiOperation(value = "新增领养信息", notes = "新增领养信息")
	@PostMapping("/add")
	public ResultDto<Boolean> add(@RequestBody AdoptInfo info){
		return service.add(info);
	}

	@ApiOperation(value = "修改领养信息", notes = "修改领养信息")
	@PostMapping("/update")
	public ResultDto<Boolean> update(@RequestBody AdoptInfo info){
		return service.updateStatus(info);
	}

	@ApiOperation(value = "修改领养状态", notes = "修改领养状态")
	@PostMapping("/updateStatus")
	public ResultDto<Boolean> updateStatus(@RequestBody AdoptInfo info){
		return service.updateStatus(info);
	}

	@ApiOperation(value = "下载模板接口", notes = "下载模板接口")
	@PostMapping("/downloadTemplate")
	public ResultDto<Boolean> downloadTemplate(@RequestBody HttpServletResponse response){
		service.downloadTemplate(response);
		return ResultDto.ok();
	}

	@ApiOperation(value = "下载模板接口", notes = "下载模板接口")
	@PostMapping("/uploadAdopt")
	public ResultDto<Boolean> uploadAdopt(@RequestBody MultipartFile file){
		service.uploadAdopt(file);
		return ResultDto.ok();
	}

	@ApiOperation(value = "统计领养信息", notes = "统计领养信息")
	@PostMapping("/statisticsAdopt")
	public ResultDto<AdoptStatisticsVo> statisticsAdopt(){
		return service.statisticsAdopt();
	}
}

