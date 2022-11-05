package com.example;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
@Autowired
	StorageRepo repo;
	                       //abc.png
public String uploadImage(MultipartFile file) throws IOException {
	ImageData data=repo.save(ImageData.builder().name(file.getOriginalFilename()).type(file.getContentType()).imagedata(ImageUtil.compressImage(file.getBytes())).build());
	if(data!=null) {
		return "fileuploaded successfully..."+file.getOriginalFilename();
	}
	return null;
}


public byte[] downloadImage(String fileName) throws DataFormatException {
	Optional<ImageData> dbimage=repo.findByname(fileName);
byte[] image=ImageUtil.decompression(dbimage.get().getImagedata());
return image;
}

}
