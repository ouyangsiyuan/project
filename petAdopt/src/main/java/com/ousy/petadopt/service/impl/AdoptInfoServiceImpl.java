package com.ousy.petadopt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ousy.petadopt.common.PageResultDto;
import com.ousy.petadopt.common.ResultCode;
import com.ousy.petadopt.common.ResultDto;
import com.ousy.petadopt.common.SystemConstant;
import com.ousy.petadopt.dto.AdoptQueryDto;
import com.ousy.petadopt.entity.AdoptInfo;
import com.ousy.petadopt.mapper.AdoptInfoMapper;
import com.ousy.petadopt.service.AdoptInfoService;
import com.ousy.petadopt.utils.ExcelUtils;
import com.ousy.petadopt.utils.RegexUtils;
import com.ousy.petadopt.vo.AdoptStatisticsVo;
import com.ousy.petadopt.vo.IndexAdoptVo;
import com.ousy.petadopt.vo.IndexInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 领养信息表 服务实现类
 * </p>
 *
 * @author ousy
 * @since 2023-01-05
 */
@Service
@Slf4j
public class AdoptInfoServiceImpl extends ServiceImpl<AdoptInfoMapper, AdoptInfo> implements AdoptInfoService {

	@Resource
	private AdoptInfoMapper mapper;

	@Override
	public PageResultDto<AdoptInfo> getPageList(AdoptQueryDto dto) {
		QueryWrapper<AdoptInfo> qw = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(dto.getSpecies())){
			qw.eq("species",dto.getSpecies());
		}
		if(StringUtils.isNotEmpty(dto.getStatus())){
			qw.eq("status",dto.getStatus());
		}

		if(StringUtils.isNotEmpty(dto.getTitle())){
			qw.like("title",dto.getTitle());
		}

		qw.orderByDesc("created_date");

		IPage<AdoptInfo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
		page = page(page, qw);
		PageResultDto<AdoptInfo> result = new PageResultDto<>();
		result.setPageNum(page.getCurrent());
		result.setPageSize(page.getSize());
		result.setTotalCount(page.getTotal());
		result.setCode(ResultCode.SUCCESS);
		result.setMessage("成功");
		result.setData(page.getRecords());
		return result;
	}

	@Override
	public ResultDto<List<AdoptInfo>> getListByTitle(String title) {

		try {
			if(StringUtils.isEmpty(title)){
				return ResultDto.error("标题不可为空，请检查！");
			}
			QueryWrapper<AdoptInfo> qw = new QueryWrapper<>();
			qw.like("title",title);
			qw.orderByDesc("like_num","created_date");
			List<AdoptInfo> adoptInfos = mapper.selectList(qw);
			if(CollectionUtils.isEmpty(adoptInfos)){
				return ResultDto.ok();
			}
			return new ResultDto<>().data(adoptInfos);
		}catch (Exception e){
			log.error(SystemConstant.ERROR_MSG_LOG,e);
			return ResultDto.error(SystemConstant.ERROR_MSG_RESULT);

		}
	}

	@Override
	public void setIndexAdoptInfo(IndexInfoVo indexInfoVo,String city) {
		List<IndexAdoptVo> bigAdoptPictures = new ArrayList<>();//大图轮播集合
		List<IndexAdoptVo> smallAdoptPictures = new ArrayList<>();//小图集合
		List<IndexAdoptVo> dogAdoptInfos = new ArrayList<>();//狗集合
		List<IndexAdoptVo> catAdoptInfos = new ArrayList<>();//猫集合
		//查询30天内所有领养数据
		QueryWrapper<AdoptInfo> qw = new QueryWrapper<>();
		qw.lambda().apply("now() - interval '1 month' <= date(created_date)");
		List<AdoptInfo> adoptInfos = mapper.selectList(qw);
		//根据物种分组
		Map<String, List<AdoptInfo>> groupAdoptMap = adoptInfos.stream().collect(Collectors.groupingBy(AdoptInfo::getSpecies));
		//遍历排序取数据
		Set<String> keySet = groupAdoptMap.keySet();
		for(String key : keySet){
			List<AdoptInfo> adoptInfosGroup = groupAdoptMap.get(key);
			adoptInfosGroup.sort(Comparator.comparing(AdoptInfo::getLikeNum).reversed());
			//狗集合
			if("0".equals(key)){
				if(adoptInfosGroup.size()>=9){
					List<AdoptInfo> dogAdoptInfo = adoptInfosGroup.subList(0, 9);
					for(AdoptInfo adoptInfo : dogAdoptInfo){
						dogAdoptInfos.add(this.setInfo(adoptInfo));
					}
				}else{
					for(AdoptInfo adoptInfo : adoptInfosGroup){
						dogAdoptInfos.add(this.setInfo(adoptInfo));
					}
				}
			}
			//猫集合
			if("1".equals(key)){
				if(adoptInfosGroup.size()>=9){
					List<AdoptInfo> catAdoptInfo = adoptInfosGroup.subList(0, 9);
					for(AdoptInfo adoptInfo : catAdoptInfo){
						catAdoptInfos.add(this.setInfo(adoptInfo));
					}
				}else{
					for(AdoptInfo adoptInfo : adoptInfosGroup){
						catAdoptInfos.add(this.setInfo(adoptInfo));
					}
				}
			}
			//数据大于等于3条，直接塞入大图集合
			if(adoptInfosGroup.size()>=3){
				List<AdoptInfo> subAdoptList = adoptInfosGroup.subList(0, 3);
				for(AdoptInfo adoptInfo: subAdoptList){
					bigAdoptPictures.add(this.setInfo(adoptInfo));
				}
			//数据小于3条直接塞入大图集合
			}else{
				for(AdoptInfo adoptInfo: adoptInfosGroup){
					bigAdoptPictures.add(this.setInfo(adoptInfo));
				}
			}
			//数据大于等于4条，取第4条塞入小图集合
			if (adoptInfosGroup.size()>=4){
				smallAdoptPictures.add(this.setInfo(adoptInfosGroup.get(3)));
			}
		}
		indexInfoVo.setBigAdoptPictures(bigAdoptPictures);
		indexInfoVo.setSmallAdoptPictures(smallAdoptPictures);
		indexInfoVo.setDogAdoptInfos(dogAdoptInfos);
		indexInfoVo.setCatAdoptInfos(catAdoptInfos);

		//获取推荐领养信息
		List<IndexAdoptVo> recommendIndexAdopts = new ArrayList<>();
		qw.orderByDesc("like_num");
		List<AdoptInfo> recommendAdoptInfos = mapper.selectList(qw);
		if(recommendAdoptInfos.size()>10){
			List<AdoptInfo> subRecommendAdopt = recommendAdoptInfos.subList(0, 10);
			for(AdoptInfo adoptInfo : subRecommendAdopt){
				recommendIndexAdopts.add(this.setInfo(adoptInfo));
			}
		}else{
			for(AdoptInfo adoptInfo : recommendAdoptInfos){
				recommendIndexAdopts.add(this.setInfo(adoptInfo));
			}
		}
		indexInfoVo.setRecommendAdoptInfos(recommendIndexAdopts);

		//获取同城领养信息
		List<IndexAdoptVo> cityIndexAdopts = new ArrayList<>();
		qw.eq("city",city);
		List<AdoptInfo> cityAdoptInfos = mapper.selectList(qw);
		if(cityAdoptInfos.size()>10){
			List<AdoptInfo> subCityAdopt = cityAdoptInfos.subList(0, 10);
			for(AdoptInfo adoptInfo : subCityAdopt){
				cityIndexAdopts.add(this.setInfo(adoptInfo));
			}
		}else{
			for(AdoptInfo adoptInfo : cityAdoptInfos){
				cityIndexAdopts.add(this.setInfo(adoptInfo));
			}
		}

		indexInfoVo.setCityAdoptInfos(cityIndexAdopts);
	}

	@Override
	public ResultDto<List<IndexAdoptVo>> getListOrderByLikeNum(String species) {
		QueryWrapper<AdoptInfo> qw = new QueryWrapper<>();
		qw.lambda().apply("now() - interval '1 month' <= date(created_date)");
		qw.orderByDesc("like_num");
		if(StringUtils.isNotEmpty(species)){
			qw.eq("species",species);
		}
		List<AdoptInfo> adoptInfos = mapper.selectList(qw);
		if(CollectionUtils.isEmpty(adoptInfos)){
			return new ResultDto<>();
		}
		return new ResultDto<>().data(adoptInfos);
	}

	@Override
	public ResultDto<Boolean> add(AdoptInfo info) {
		String errMsg = this.checkParams(info);
		if(StringUtils.isNotEmpty(errMsg)){
			return ResultDto.error(errMsg);
		}

		info.setTitel(info.getName()+"-"+info.getSpecies()+"-"+info.getAge());
		info.setStatus("0");

		return new ResultDto<>().data(mapper.insert(info));
	}

	@Override
	public ResultDto<Boolean> update(AdoptInfo info) {
		if(info.getId()==null || info.getId()==0){
			return ResultDto.error("领养ID不可为空，请检查！");
		}
		String errMsg = this.checkParams(info);
		if(StringUtils.isNotEmpty(errMsg)){
			return ResultDto.error(errMsg);
		}
		return new ResultDto<>().data(mapper.updateById(info));
	}

	@Override
	public ResultDto<Boolean> updateStatus(AdoptInfo info) {
		if(info.getId()==null || info.getId()==0){
			return ResultDto.error("领养信息ID不可为空，请检查！");
		}
		if(StringUtils.isEmpty(info.getStatus())){
			return ResultDto.error("领养状态不可为空，请检查！");
		}
		return new ResultDto<>().data(mapper.updateById(info));
	}

	@Override
	public void downloadTemplate(HttpServletResponse response) {
		ExcelUtils.downloadTemplate(response);
	}

	@Override
	public void uploadAdopt(MultipartFile file) {
		try {
			if (file == null) {
				throw new RuntimeException();
			}
			String filename = file.getOriginalFilename();
			if (filename == null) {
				throw new RuntimeException();
			}
			String a = "";
			try {
				// 调用解析文件方法
				String errMsg = parseRowCell(filename, file.getInputStream());
				if(StringUtils.isNotEmpty(errMsg)){
					throw new RuntimeException(errMsg);
				}
			} catch (IOException e) {
				throw new Exception(e.getMessage());
			}

		}catch (Exception e){

		}
	}

	@Override
	public ResultDto<AdoptStatisticsVo> statisticsAdopt() {
		return new ResultDto<>().data(mapper.statisticsAdopt());
	}


	public String parseRowCell(String filename, InputStream is){
		try {
			Workbook workbook = null;
			// 判断excel的后缀，不同的后缀用不同的对象去解析
			// xls是低版本的Excel文件
			if (filename.endsWith(".xls")) {
				workbook = new XSSFWorkbook(is);
			}
			// xlsx是高版本的Excel文件
			if (filename.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(is);
			}
			if (workbook == null) {
				throw new Exception();
			}

			// 取到excel 中的第一张工作表
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet == null) {
				throw new Exception();
			}

			// 工作表中第一行是表头，不获取，从第二行开始获取
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				AdoptInfo adoptInfo = new AdoptInfo();
				// 获取到这一行的数据
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				//姓名
				if (row.getCell(0) == null) {
					return "名字未填写！";
				}
				String name = row.getCell(0).getStringCellValue().trim();
				if(!RegexUtils.isLetterDigitOrChinese(name)){
					return "名字只能填中文、英文大小写、数字";
				}
				adoptInfo.setName(name);
				//年龄
				if (row.getCell(1) == null) {
					throw  new RuntimeException("年龄未填写！");
				}
				String ageStr = row.getCell(1).getStringCellValue().trim();
				if(!RegexUtils.isNumber(ageStr)){
					return "年龄只能填数字哦~";
				}
				adoptInfo.setAge(Integer.parseInt(ageStr));
				//物种
				if (row.getCell(2) == null) {
					return "物种未填写！";
				}
				String speciesStr = row.getCell(2).getStringCellValue().trim();
				if(checkOption(speciesStr)){
					return "物种输入有误";
				}
				adoptInfo.setSpecies(speciesStr);
				//性别
				if (row.getCell(3) == null) {
					return "性别未填写！";
				}
				String sexStr = row.getCell(3).getStringCellValue().trim();
				if(checkOption(sexStr)){
					return "性别输入有误";
				}
				adoptInfo.setSex(sexStr);
				//介绍
				if (row.getCell(4) == null) {
					return "宠物介绍未填写！";
				}
				adoptInfo.setIntroduce(row.getCell(4).getStringCellValue().trim());
				//省
				if (row.getCell(5) == null) {
					return "所在省份未填写！";
				}
				adoptInfo.setProvince(row.getCell(5).getStringCellValue().trim());
				//市
				if (row.getCell(6) == null) {
					return "所在城市未填写！";
				}
				adoptInfo.setCity(row.getCell(6).getStringCellValue().trim());
				//区/县
				if (row.getCell(7) == null) {
					return "所在区/县未填写！";
				}
				adoptInfo.setArea(row.getCell(7).getStringCellValue().trim());
				//详细地址
				if (row.getCell(8) == null) {
					return "所在区/县未填写！";
				}
				adoptInfo.setAddress(row.getCell(8).getStringCellValue().trim());
				//是否绝育
				if (row.getCell(9) != null) {
					String isGar = row.getCell(9).getStringCellValue().trim();
					if(!checkOption(isGar)){
						return "是否绝育输入有误！";
					}
					adoptInfo.setIsGar(isGar);
				}
				//是否驱虫
				if (row.getCell(10) != null) {
					String isRepellent = row.getCell(10).getStringCellValue().trim();
					if(!checkOption(isRepellent)){
						return "是否驱虫输入有误！";
					}
					adoptInfo.setIsRepellent(isRepellent);
				}
				//是否疫苗
				if (row.getCell(11) != null) {
					String isVaccine = row.getCell(11).getStringCellValue().trim();
					if(!checkOption(isVaccine)){
						return "是否疫苗输入有误！";
					}
					adoptInfo.setIsVaccine(isVaccine);
				}
				adoptInfo.setAdoptType("1");
				adoptInfo.setStatus("0");
				adoptInfo.setTitel(adoptInfo.getName()+"-"+adoptInfo.getSpecies()+"-"+adoptInfo.getAge());
				adoptInfo.setCreatedBy("admin");
				adoptInfo.setCreatedDate(new Date());
				adoptInfo.setUpdatedBy("admin");
				adoptInfo.setUpdatedDate(new Date());
				mapper.insert(adoptInfo);

			}
		}catch (Exception e){
			log.error(SystemConstant.ERROR_MSG_LOG,e);
			return SystemConstant.ERROR_MSG_RESULT;
		}
		return "";
	}

	private boolean checkOption(String str){
		if("0".equals(str)||"1".equals(str)||"2".equals(str)){
			return true;
		}
		return false;
	}

	private IndexAdoptVo setInfo(AdoptInfo adoptInfo){
		IndexAdoptVo indexAdoptVo = new IndexAdoptVo();
		indexAdoptVo.setAdoptId(adoptInfo.getId());
		indexAdoptVo.setTitle(adoptInfo.getTitel());
		indexAdoptVo.setLikeNum(adoptInfo.getLikeNum());
		indexAdoptVo.setOwnerName(adoptInfo.getCreatedBy());
		indexAdoptVo.setPictureUrl(adoptInfo.getPicture1());
		return indexAdoptVo;
	}

	private String checkParams(AdoptInfo info){
		if(StringUtils.isEmpty(info.getSpecies())){
			return "宝贝是猫猫还是狗狗啊？选一个呗~";
		}
		if(StringUtils.isEmpty(info.getSex())){
			return "哈，都不知道是男是女呢！";
		}
		if(StringUtils.isEmpty(info.getName())){
			return "取个名字呗，小宝贝也想有个名字！";
		}
		if(!RegexUtils.isLetterDigitOrChinese(info.getName())){
			return "名字只能填“中文、英文大小写、数字”哦~";
		}
		if(info.getAge()==null || info.getAge()==0){
			return "宝宝多大啦？";
		}
		if(StringUtils.isEmpty(info.getProvince())){
			return "宝宝在哪个省份呢？";
		}
		if(StringUtils.isEmpty(info.getCity())){
			return "宝宝在哪个城市呢？";
		}
		if(StringUtils.isEmpty(info.getArea())){
			return "宝宝在哪个区/县呢？";
		}
		if(StringUtils.isEmpty(info.getAddress())){
			return "宝宝在哪呢，填下详细地址呗！";
		}
		if(StringUtils.isEmpty(info.getPicture1())
				&&StringUtils.isEmpty(info.getPicture2())
				&&StringUtils.isEmpty(info.getPicture3())
				&&StringUtils.isEmpty(info.getPicture4())
				&&StringUtils.isEmpty(info.getPicture5())){
			return "上传个图片呗，不然大家都不知道宝贝的样子哦！";
		}
		return "";
	}
}
