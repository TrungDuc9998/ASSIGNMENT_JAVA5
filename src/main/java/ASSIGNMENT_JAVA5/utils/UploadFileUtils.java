package ASSIGNMENT_JAVA5.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileUtils {
	public File handleUpLoadFile(MultipartFile uploadFile){
		String folderPath="E:\\Tai_Lieu_Java5\\code\\ASSIGNMENT_JAVA5\\src\\main\\webapp\\storage";
		File myUploadFolder=new File(folderPath);
		System.out.println(myUploadFolder);
		
		//kiểm tra thư mục lưu trữ có tồn tại? nếu ko thì tạo mới thư mục
		if(!myUploadFolder.exists()) {
			myUploadFolder.mkdirs();
		}
		
		File saveFile=null;
		try {
			//Lưu file vào thư mục đã chọn
//			String uuid=UUID.randomUUID().toString();
//			String fileName=uuid+"_"+uploadFile.getOriginalFilename();
			String fileName=uploadFile.getOriginalFilename();
			 saveFile=new File(myUploadFolder,fileName);
			 System.out.println("********************* save file **********************:"+saveFile);
			uploadFile.transferTo(saveFile);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return saveFile;
}
}
