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
 * 领养步骤选择项表
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdoptStepOption对象", description="领养步骤选择项表")
public class AdoptStepOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "步骤序号")
    private String serialNum;

    @ApiModelProperty(value = "选题名称")
    private String topicName;

    @ApiModelProperty(value = "选项A名称")
    private String optionNameA;

    @ApiModelProperty(value = "选项A链接的领养步骤ID")
    private Integer linkAdoptA;

    @ApiModelProperty(value = "选项B名称")
    private String optionNameB;

    @ApiModelProperty(value = "选项B链接的领养步骤ID")
    private Integer linkAdoptB;

    @ApiModelProperty(value = "选项C名称")
    private String optionNameC;

    @ApiModelProperty(value = "选项C链接的领养步骤ID")
    private Integer linkAdoptC;

    @ApiModelProperty(value = "选项D名称")
    private String optionNameD;

    @ApiModelProperty(value = "选项D链接的领养步骤ID")
    private Integer linkAdoptD;

    @ApiModelProperty(value = "选择项编号（1-选择项1；2-选择项2；3-选择项3）")
    private String optionCode;

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
