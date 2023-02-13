package com.ousy.petadopt.service;

import com.ousy.petadopt.dto.UploadDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title: OssService
 * @Author miller.ouyang
 * @Date: 2023/2/13 13:59
 * @Version 1.0
 */
public interface OssService {

	UploadDto upload(MultipartFile file) throws Exception;

	UploadDto upload(MultipartFile file, String filePath) throws Exception;

	byte[] download(String key);

	void delete(String key);
}
