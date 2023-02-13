package com.ousy.petadopt.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * @title: PageQueryDto
 * @Author miller.ouyang
 * @Date: 2023/1/6 17:09
 * @Version 1.0
 */
public class PageQueryDto {
	@ApiModelProperty(
			value = "页号",
			example = "1"
	)
	protected Integer pageNum = 1;
	@ApiModelProperty(
			value = "每页记录数",
			example = "5"
	)
	protected Integer pageSize = 10;

	@JsonIgnore
	public void setPageInfo(PageResultDto resultDto) {
		resultDto.setPageNum((long)this.pageNum);
		resultDto.setPageSize((long)this.pageSize);
		resultDto.setTotalCount(resultDto.getTotalCount());
		resultDto.setTotalPages(resultDto.getTotalCount() % (long)this.pageSize == 0L ? resultDto.getTotalCount() / (long)this.pageSize : resultDto.getTotalCount() / (long)this.pageSize + 1L);
		this.pageNum = (this.pageNum - 1) * this.pageSize;
	}

	public PageQueryDto() {
	}

	public Integer getPageNum() {
		return this.pageNum;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
