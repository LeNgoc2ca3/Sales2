package com.Java6.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.Java6.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	ServletContext app;

    @Override
    public File save(MultipartFile file, String folder) {
//        String folderPath = "D:\\JAVA\\JAVA6\\BAITAP\\Java6_Assignment\\src\\main\\resources\\static\\assets\\images";
//		File myUploadFolder = new File(folderPath);
//
////		kiểm tra thử mục lưu trữ file có tồn tại hay không
//		if(!myUploadFolder.exists()) {
//			myUploadFolder.mkdir();
//		}
//
//		File saveFile = null;
//		String fileName = null;

//		try {
////		Lưu file vào thư mục đã chọn
//			fileName = folder;
//			saveFile = new File(myUploadFolder, fileName);
//			file.transferTo(saveFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		File dir = new File(app.getRealPath("/assets/" + folder));
		if(!dir.exists()){
			dir.mkdirs();
		}

		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));

		File savedFile = null;
		try {
			savedFile = new File(dir,name);
			file.transferTo(savedFile);
//			System.out.println(savedFile.getAbsolutePath());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return savedFile;
    }
    
}
