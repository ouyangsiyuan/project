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
 * 领养步骤表
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdoptStep对象", description="领养步骤表")
public class AdoptStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "步骤序号")
    private String serialNum;

    @ApiModelProperty(value = "步骤名称")
    private String name;

    @ApiModelProperty(value = "本环节填报人")
    private String linkApplicant;

    @ApiModelProperty(value = "本环节审批人")
    private String linkApprover;

    @ApiModelProperty(value = "归属机构ID")
    private Integer organizationId;

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
