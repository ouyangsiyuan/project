package com.ousy.petadopt.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title: AdoptStatisticsVo
 * @Author miller.ouyang
 * @Date: 2023/1/6 17:19
 * @Version 1.0
 */
@Data
public class AdoptStatisticsVo {
	@ApiModelProperty(value = "领养信息数量")
	private Integer totalNum;
	@ApiModelProperty(value = "待审核数量")
	private Integer toBeReviewedNum;
	@ApiModelProperty(value = "待领养数量")
	private Integer pendingAdoptionNum;
	@ApiModelProperty(value = "完成领养数量")
	private Integer haveCompletedNum;
}
