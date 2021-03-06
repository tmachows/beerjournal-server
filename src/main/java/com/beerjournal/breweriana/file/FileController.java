package com.beerjournal.breweriana.file;

import com.mongodb.gridfs.GridFSDBFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("files/{fileId}")
    public ResponseEntity<InputStreamResource> getImageItem(@PathVariable("fileId") String fileId) {
        GridFSDBFile file = fileService.getFile(fileId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType((file.getContentType())))
                .body(new InputStreamResource(file.getInputStream()));
    }

}
