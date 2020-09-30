package br.com.vinicius.services;

import java.lang.annotation.Target;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.vinicius.config.FileStoregeConfig;
import br.com.vinicius.exception.FileStorageException;
import br.com.vinicius.exception.MyFileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStoregeConfig fileStoregeConfig) {
		this.fileStorageLocation = Paths.get(fileStoregeConfig.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		}catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be storage",e);
		}
	}
	
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Filename contains invalid path sequence "+fileName);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		}catch (Exception e) {
			throw new FileStorageException("Could not store file "+fileName+ " please try again",e);
		}
		
	}
	
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists())
				return resource;
			else
				throw new MyFileNotFoundException("File not found " + fileName);
		}catch (Exception e) {
			throw new MyFileNotFoundException("File not found " + fileName, e);
		}
	}
	
}
