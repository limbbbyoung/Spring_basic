package com.ict.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ict.mapper.BoardAttachMapper;
import com.ict.persistence.BoardAttachVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	
	@Autowired
	private BoardAttachMapper attachMapper;
	
	private String getFolderYesterday() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	} // getFolderYesterday end
	

	@Scheduled(cron="0 * * * * *") // 크론 설정은 다음과 같다. 초(0~59), 분(0~59), 일(1~31), 달(1~12), 요일(1~7)
									//, (연도)(특수), *은 와일드카드
	public void checkFiles() throws Exception {
		
		log.warn("FIle Check Task run ......");
		log.warn(new Date());
		// get file list in db
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		// check file and directory path
		List<Path> fileListPaths = fileList.stream().map(
				vo -> Paths.get("c:\\upload_data\\temp", vo.getUploadPath(),
								vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());
		
		// image file thumbnail
		fileList.stream().filter(vo -> vo.isFileType() == true)
						.map(vo -> Paths.get("c\\upload_data\\temp", vo.getUploadPath(), "s_" + 
											vo.getUuid() + "_" + vo.getFileName()))
						.forEach(p -> fileListPaths.add(p));
		
		log.warn("=============================================");
		
		fileListPaths.forEach(p -> log.warn(p));
		
		// files in yesterday directory
		File targetDir = Paths.get("c:\\upload_data\\temp", getFolderYesterday()).toFile();
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("=============================================");
		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
	} // checkFiles end
	
	
} // class end
