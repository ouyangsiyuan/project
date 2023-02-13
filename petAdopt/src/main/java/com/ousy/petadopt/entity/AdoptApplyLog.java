package com.ousy.petadopt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 申请领养表
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdoptApplyLog对象", description="申请领养表")
public class AdoptApplyLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "领养信息ID")
    private Integer adoptId;

    @ApiModelProperty(value = "申请领养信息ID")
    private Integer applyId;

    @ApiModelProperty(value = "当前步骤ID")
    private Integer stepId;

    @ApiModelProperty(value = "当前步骤序号")
    private String serialNum;

    @ApiModelProperty(value = "填写项1")
    private String entry1;

    @ApiModelProperty(value = "填写项2")
    private String entry2;

    @ApiModelProperty(value = "填写项3")
    private String entry3;

    @ApiModelProperty(value = "填写项4")
    private String entry4;

    @ApiModelProperty(value = "填写项5")
    private String entry5;

    @ApiModelProperty(value = "填写项6")
    private String entry6;

    @ApiModelProperty(value = "选择项1")
    private String option1;

    @ApiModelProperty(value = "选择项2")
    private String option2;

    @ApiModelProperty(value = "选择项3")
    private String option3;

    @ApiModelProperty(value = "凭证1")
    private String certificate1;

    @ApiModelProperty(value = "凭证2")
    private String certificate2;

    @ApiModelProperty(value = "填写人")
    private String applicantName;

    @ApiModelProperty(value = "审批信息（0-同意；1-拒绝；2无需审批）")
    private String approvalStatus;

    @ApiModelProperty(value = "审批信息")
    private String approvalMessage;

    @ApiModelProperty(value = "审批人")
    private String approvalName;

    @ApiModelProperty(value = "乐观锁")
    private String version;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedDate;


}
