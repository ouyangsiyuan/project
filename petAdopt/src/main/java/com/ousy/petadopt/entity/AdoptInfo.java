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
 * 领养信息表
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdoptInfo对象", description="领养信息表")
public class AdoptInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String titel;

    @ApiModelProperty(value = "状态（0-待审核；1-待领养；2-已领养；3-已下架；4-已删除；5-已拒绝；6-待修改）")
    private String status;

    @ApiModelProperty(value = "物种（0-狗狗；1-猫猫；2-其他）")
    private String species;

    @ApiModelProperty(value = "品种")
    private String variety;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别（0-男孩；1-女孩；2-搞不清）")
    private String sex;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "领养方式（0-有偿领养；1-无偿领养）")
    private String adoptType;

    @ApiModelProperty(value = "是否嘎了（0-是；1-否；2-不清楚）")
    private String isGar;

    @ApiModelProperty(value = "是否驱虫（0-是；1-否；2-不清楚）")
    private String isRepellent;

    @ApiModelProperty(value = "是否疫苗（0-是；1-否；2-不清楚）")
    private String isVaccine;

    @ApiModelProperty(value = "归属机构ID")
    private Integer organizationId;

    @ApiModelProperty(value = "领养条件")
    private String adoptCondition;

    @ApiModelProperty(value = "图片1")
    private String picture1;

    @ApiModelProperty(value = "图片2")
    private String picture2;

    @ApiModelProperty(value = "图片3")
    private String picture3;

    @ApiModelProperty(value = "图片4")
    private String picture4;

    @ApiModelProperty(value = "图片5")
    private String picture5;

    @ApiModelProperty(value = "点赞数")
    private Integer likeNum;

    @ApiModelProperty(value = "违法信息留证标识")
    private String beIllegalProof;

    @ApiModelProperty(value = "审核反馈")
    private String auditFeedback;

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
