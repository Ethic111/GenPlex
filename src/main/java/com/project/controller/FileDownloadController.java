package com.project.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.DoctorVO;
import com.project.service.DoctorService;
import com.project.utils.BaseMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileDownloadController {

	DoctorService doctorService;

	public FileDownloadController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam("filePath") String filePath) {
		try {
			Path path = Paths.get(filePath);
			File file = path.toFile();
			// System.out.println(file);
			System.out.println(filePath);

//			if (!file.exists()) {
//				throw new RuntimeException("File not found");
//			}

			Resource resource = new FileSystemResource(file);

			System.out.println(resource);

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

			return ResponseEntity.ok().headers(headers).body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error downloading file: " + e.getMessage());
		}
	}
}
