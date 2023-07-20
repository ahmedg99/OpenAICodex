package tn.spring.springboot.Services.Implementation;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tn.spring.springboot.entities.Image;
import tn.spring.springboot.repositories.FileSystemRepository;
import tn.spring.springboot.repositories.ImageRepository;


@Service
public class FileLocationService {
    private final ImageRepository imageRepository;
    private  final FileSystemRepository fileSystemRepository ;
    public FileLocationService( ImageRepository imageRepository , FileSystemRepository fileSystemRepository) {
        this.imageRepository =  imageRepository;
        this.fileSystemRepository = fileSystemRepository ;
    }


    public Image save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);
        return imageRepository.save(new Image(imageName, location));
    }
    public FileSystemResource find(Integer imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return fileSystemRepository.findInFileSystem(image.getLocation());
    }



}
