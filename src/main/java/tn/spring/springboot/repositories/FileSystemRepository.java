package tn.spring.springboot.repositories;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
@Repository
public class FileSystemRepository {


    public String save(byte[] content, String imageName) throws Exception {
        String userDirectory = System.getProperty("user.dir");
        Path newFile = Paths.get(userDirectory+"\\assets\\images" + new Date().getTime() + "-" + imageName);

        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath()
                .toString();
    }

    public String saveVideo(byte[] data, String videoName) throws Exception {
        String userDirectory = System.getProperty("user.dir");
        Path newFile = Paths.get(userDirectory+"\\assets\\images\\" + new Date().getTime() + "-" + videoName);

        Files.createDirectories(newFile.getParent());
        Files.write(newFile, data);
        return newFile.toAbsolutePath()
                .toString();
    }

    public FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            // Handle access or file not found problems.
            throw new RuntimeException();
        }
    }
}
