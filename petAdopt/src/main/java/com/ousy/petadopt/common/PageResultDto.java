package com.ousy.petadopt.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @title: PageResultDto
 * @Author miller.ouyang
 * @Date: 2023/1/6 17:00
 * @Version 1.0
 */
@Data
public class PageResultDto<T> {
	@ApiModelProperty("状态码")
	protected Integer code;
	@ApiModelProperty("状态信息")
	protected String message;
	@ApiModelProperty("业务数据")
	private long pageNum = 0L;
	private long pageSize = 0L;
	private long totalPages = 0L;
	private long totalCount = 0L;
	private List<T> data = Collections.emptyList();

	public PageResultDto() {
	}
	public PageResultDto(long totalCount) {
		this.totalCount = totalCount;
	}

	public PageResultDto(List<T> data) {
		this.code = ResultCode.SUCCESS;
		this.message = "";
		this.data = data;
	}

	public PageResultDto(List<T> data, String msg) {
		this.code = ResultCode.SUCCESS;
		this.message = msg;
		this.data = data;
	}

	public PageResultDto(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static <T> PageResultDto<T> success(List<T> data) {
		return success("", data);
	}

	public static <T> PageResultDto<T> success(String message, List<T> data) {
		PageResultDto<T> resultDto = new PageResultDto(ResultCode.SUCCESS, message);
		resultDto.setData(data);
		return resultDto;
	}

	public static <T> PageResultDto<T> error(String msg) {
		return error(ResultCode.ERROR, msg);
	}

	public static <T> PageResultDto<T> error(int code, String msg) {
		PageResultDto<T> resultDto = new PageResultDto(code, msg);
		return resultDto;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public long getPageNum() {
		return this.pageNum;
	}

	public long getPageSize() {
		return this.pageSize;
	}

	public long getTotalPages() {
		return this.totalPages;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public List<T> getData() {
		return this.data;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
