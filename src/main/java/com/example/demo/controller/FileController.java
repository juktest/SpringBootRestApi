package com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.payload.FileUploadResponse;
import com.example.demo.service.FileUploadDownloadService;

@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileUploadDownloadService service;

    @GetMapping("/image")
    public String controllerMain() {
        return "Hello~ File Upload Test.";
    }

    @PostMapping("/image/uploadFile/{roomid}/{no}")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("roomid") int roomid, @PathVariable("no") int no) {
        String filename = "image"+ roomid + "-" + no;
        String fileName = service.storeFile(file,filename);
        StringTokenizer tockens = new StringTokenizer(fileName);
        tockens.nextToken(".");
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/"+roomid)
                .path("/"+no + "."+tockens.nextToken("."))
                .toUriString();

        return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }



    @GetMapping("/image/{roomid}/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("roomid")int roomid, @PathVariable("id")String  no, HttpServletRequest request){
        // Load file as Resource
        String fileName = "image"+roomid +"-"+ no;
        Resource resource = service.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/Community/{Univid}/{Postid}/image/{no}")
    public FileUploadResponse upcommunityimg(@RequestParam("file") MultipartFile file, @PathVariable("Univid") int Univid, @PathVariable("Postid") int Postid,@PathVariable("no") int no) {
        String filename = "Community"+ Univid + "-" + Postid + "+" + no;
        String fileName = service.storeFile(file,filename);
        StringTokenizer tockens = new StringTokenizer(fileName);
        tockens.nextToken(".");
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Community/"+Univid)
                .path("/"+Postid+"/image/"+no + "."+tockens.nextToken("."))
                .toUriString();

        return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }



    @GetMapping("/Community/{Univid}/{Postid}/image/{no}")
    public ResponseEntity<Resource> downcommunityimg( @PathVariable("Univid") int Univid, @PathVariable("Postid") int Postid, @PathVariable("no")String  no, HttpServletRequest request){
        // Load file as Resource
        String fileName = "Community"+ Univid + "-" + Postid + "+" + no;
        Resource resource = service.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}