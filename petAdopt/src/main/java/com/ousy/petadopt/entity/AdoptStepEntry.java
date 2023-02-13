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
 * 领养步骤填写项表
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdoptStepEntry对象", description="领养步骤填写项表")
public class AdoptStepEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "步骤序号")
    private String serialNum;

    @ApiModelProperty(value = "填写类型（0-姓名；1-性别；2-联系方式；3-居住地址；4-其他）")
    private String entryType;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "填写项编号（1-填写项1；2-填写项2；3-填写项3；4-填写项4；5-填写项5；6-填写项6）")
    private String entryCode;

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
