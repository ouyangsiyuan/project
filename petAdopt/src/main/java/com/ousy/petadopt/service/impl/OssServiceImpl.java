package com.ousy.petadopt.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import com.ousy.petadopt.dto.UploadDto;
import com.ousy.petadopt.entity.TencentProperties;
import com.ousy.petadopt.service.OssService;
import com.ousy.petadopt.utils.OssUtils;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @title: OssServiceImpl
 * @Author miller.ouyang
 * @Date: 2023/2/13 14:03
 * @Version 1.0
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {

	@Resource
	private TencentProperties tencentProperties;

	@Override
	public UploadDto upload(MultipartFile file) throws Exception{
		return upload(file, null);
	}

	@Override
	public UploadDto upload(MultipartFile file, String filePath) throws Exception{
		//文件名
		String fileFullName = FileUtil.getName(file.getOriginalFilename());
		InputStream inputStream = file.getInputStream();
		return upload(inputStream, fileFullName, filePath);
	}

	@Override
	public byte[] download(String key) {
		return new byte[0];
	}

	@Override
	public void delete(String key) {

	}

	public UploadDto upload(InputStream inputStream, String fileFullName, String filePath) throws Exception {

		if (inputStream == null) {
			throw new Exception("上传文件不能为空");
		}
		TransferManager transferManager = OssUtils.createTransferManager(tencentProperties);
		String bucketName = tencentProperties.getBucketName();

		//int inputStreamLength = 1024 * 1024;
//        byte data[] = new byte[inputStreamLength];
//        InputStream inputStream = new ByteArrayInputStream(data);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		// 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
		// 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了
		//objectMetadata.setContentLength(inputStreamLength);
		try {
			//时间戳
			String timestamp = String.valueOf(System.currentTimeMillis());
			//文件扩展名
			String extension = FileUtil.getSuffix(fileFullName);
			String fileName = FileUtil.getPrefix(fileFullName);
			List<String> fileTypes = tencentProperties.getFileTypes();
			if (fileTypes != null) {
				boolean flag = fileTypes.contains(extension);
				Assert.isTrue(flag, "不支持上传的文件类型：" + extension);
			}
			String upFilePath = StringUtils.join(fileName, "_", timestamp, ".", extension);
			if (filePath != null) {
				upFilePath = StringUtils.join(filePath, "/", upFilePath);
			}
			String key = upFilePath;
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
			// 高级接口会返回一个异步结果Upload
			// 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
			Upload upload = transferManager.upload(putObjectRequest);
			UploadResult uploadResult = upload.waitForUploadResult();
			if (uploadResult == null) {
				log.error("上传附件到腾讯云失败 fileName={}", upFilePath);
				throw new Exception("上传附件 " + upFilePath + " 到腾讯云失败 ");
			}

			log.info("cos fileName:" + upFilePath);
			//返回上传结果
			UploadDto uploadDto = new UploadDto();
			uploadDto.setName(upFilePath);
//            uploadDto.setKey(upFilePath);
			uploadDto.setCreateTime(DateUtil.date());
			return uploadDto;
		} catch (Exception e) {
			log.error("cos 上传失败", e);
			throw new RuntimeException("文件=" + fileFullName + " 上传失败");
		} finally {
			OssUtils.shutdownTransferManager(transferManager);
		}
	}
}
