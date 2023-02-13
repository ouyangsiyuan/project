package com.ousy.petadopt.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.statement.create.table.Index;

import java.util.List;

/**
 * @title: IndexInfoVo
 * @Author miller.ouyang
 * @Date: 2023/1/5 15:06
 * @Version 1.0
 */
@Data
public class IndexInfoVo {
	@ApiModelProperty(value = "用户头像URL")
	private String userHeadImgUrl;
	@ApiModelProperty(value = "用户名称")
	private String userName;
	@ApiModelProperty(value = "首页背景图片")
	private String backgroundPicture;
	@ApiModelProperty(value = "首页领养信息轮播大图")
	private List<IndexAdoptVo> bigAdoptPictures;
	@ApiModelProperty(value = "首页领养信息小图")
	private List<IndexAdoptVo> smallAdoptPictures;
	@ApiModelProperty(value = "首页推广位信息")
	private List<IndexGeneralizationBitVo> generalizationBits;
	@ApiModelProperty(value = "首页狗狗领养信息-首条数据为大图")
	private List<IndexAdoptVo> dogAdoptInfos;
	@ApiModelProperty(value = "首页猫猫领养信息-首条数据为大图")
	private List<IndexAdoptVo> catAdoptInfos;
	@ApiModelProperty(value = "同城领养信息")
	private List<IndexAdoptVo> cityAdoptInfos;
	@ApiModelProperty(value = "推荐领养信息")
	private List<IndexAdoptVo> recommendAdoptInfos;



}
