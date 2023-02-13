package com.ousy.petadopt.controller;

import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.dto.IndexQueryDto;
import com.ousy.petadopt.entity.GeneralizationBitConfig;
import com.ousy.petadopt.entity.PageDrawingConfig;
import com.ousy.petadopt.entity.User;
import com.ousy.petadopt.service.AdoptInfoService;
import com.ousy.petadopt.service.GeneralizationBitConfigService;
import com.ousy.petadopt.service.PageDrawingConfigService;
import com.ousy.petadopt.service.UserService;
import com.ousy.petadopt.vo.IndexAdoptVo;
import com.ousy.petadopt.vo.IndexGeneralizationBitVo;
import com.ousy.petadopt.vo.IndexInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 首页信息 前端控制器
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/index")
@Api(value = "首页信息管理", tags = "首页信息管理")
public class IndexController {

	@Resource
	private AdoptInfoService adoptInfoService;

	@Resource
	private UserService userService;

	@Resource
	private PageDrawingConfigService pageDrawingConfigService;

	@Resource
	private GeneralizationBitConfigService generalizationBitConfigService;

	@ApiOperation(value = "根据标题模糊查询领养信息", notes = "根据标题模糊查询领养信息")
	@PostMapping("/getIndexInfo")
	public ResultDto<IndexInfoVo> getIndexInfo(@RequestBody IndexQueryDto dto){
		if(dto.getUserId()==null||dto.getUserId()==0){
			return ResultDto.error("用户ID不可为空，请检查！");
		}
		if(StringUtils.isEmpty(dto.getCity())){
			dto.setCity("南京");
		}

		//查询用户信息
		IndexInfoVo vo = new IndexInfoVo();
		ResultDto<User> userResult = userService.getById(dto.getUserId());
		User user = userResult.getData();
		vo.setUserHeadImgUrl(user.getHeadImgUrl());
		vo.setUserName(user.getName());

		//查询背景图片配置
		PageDrawingConfig pageDrawingConfig = pageDrawingConfigService.getConfigByType("0");
		vo.setBackgroundPicture(pageDrawingConfig.getUrlAddr());

		//查询宠物信息
		adoptInfoService.setIndexAdoptInfo(vo, dto.getCity());

		//查询推广位信息
		List<String> codes = Arrays.asList("1", "2", "3");
		List<GeneralizationBitConfig> generalizationBitList = generalizationBitConfigService.getList(codes);
		List<IndexGeneralizationBitVo> generalizationBits = new ArrayList<>();
		for(GeneralizationBitConfig config : generalizationBitList){
			IndexGeneralizationBitVo bitVo = new IndexGeneralizationBitVo();
			bitVo.setCode(config.getCode());
			bitVo.setLink(config.getLink());
			bitVo.setTitle(config.getTitle());
			bitVo.setGeneralizationId(config.getId());
			bitVo.setPictureUrl1(config.getPicture1());
			bitVo.setPictureUrl2(config.getPicture2());
			bitVo.setPictureUrl3(config.getPicture3());
			generalizationBits.add(bitVo);
		}
		vo.setGeneralizationBits(generalizationBits);
		return new ResultDto<>().data(vo);
	}


	@ApiOperation(value = "点击查看更多领养信息-物种", notes = "点击查看更多领养信息-物种")
	@PostMapping("/getListOrderByLikeNum/{species}")
	public ResultDto<List<IndexAdoptVo>> getListOrderByLikeNum(@PathVariable("species")String species){
		return adoptInfoService.getListOrderByLikeNum(species);
	}

}
