package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.springboot.Services.Implementation.FileLocationService;
import tn.spring.springboot.entities.Image;

@RestController
@RequestMapping("file-system")
public class FileSystemImageController {

    @Autowired
    FileLocationService iFileLocationService;

    @PostMapping(value = "/image" ,  consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Image> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            Image savedImage = iFileLocationService.save(image.getBytes(), image.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<FileSystemResource> downloadImage(@PathVariable Integer imageId) {
        try {
            FileSystemResource fileSystemResource = iFileLocationService.find(imageId);
            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileSystemResource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
