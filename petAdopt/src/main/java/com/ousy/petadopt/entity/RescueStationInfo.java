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
 * 救助站信息表
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RescueStationInfo对象", description="救助站信息表")
public class RescueStationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "救助站名称")
    private String name;

    @ApiModelProperty(value = "状态（0-启用；1-停用）")
    private String status;

    @ApiModelProperty(value = "创始人")
    private String originator;

    @ApiModelProperty(value = "救助站地址（省-市）")
    private String stationAddr;

    @ApiModelProperty(value = "创立时间")
    private Date establishmentDate;

    @ApiModelProperty(value = "支付宝账号")
    private String alipayAccount;

    @ApiModelProperty(value = "支付宝开户人")
    private String alipayAccountHolder;

    @ApiModelProperty(value = "支付宝二维码图片URL")
    private String alipayQrCode;

    @ApiModelProperty(value = "物资援助")
    private String materialAssistance;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "默认领养条件")
    private String defaultAdoptConditions;

    @ApiModelProperty(value = "图片1URL")
    private String picture1;

    @ApiModelProperty(value = "图片2URL")
    private String picture2;

    @ApiModelProperty(value = "图片3URL")
    private String picture3;

    @ApiModelProperty(value = "图片4URL")
    private String picture4;

    @ApiModelProperty(value = "图片5URL")
    private String picture5;

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
