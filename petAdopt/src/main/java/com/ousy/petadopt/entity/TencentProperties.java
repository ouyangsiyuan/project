package com.ousy.petadopt.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @title: TencentProperties
 * @Author miller.ouyang
 * @Date: 2023/2/13 13:46
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.tencent")
public class TencentProperties {
	/**域名*/
	private String domain;
	/**地域节点*/
	private String region;
	/**存储桶名称*/
	private String bucketName;
	/**secretId*/
	private String secretId;
	/**secretKey*/
	private String secretKey;
	/**图片策略*/
	private String styleRule;
	/**缩略图策略*/
	private String thumbnailStyleRule;
	/**文件类型*/
	private List<String> fileTypes;
}
