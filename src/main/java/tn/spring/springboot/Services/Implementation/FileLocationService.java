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

import java.io.*;


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


    public void writeStringToFile(String str, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String content = " public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        System.out.println(\"Hello\");\n" +
                    "    }\n" +
                    "}\n    ";
            writer.write(content);
             runJavaFile(fileName);
            // You don't need to explicitly close the writer when using try-with-resources
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
    }

    public static void compileJavaFile(String fileName) {
        String javaFileName = fileName.substring(0, fileName.lastIndexOf('.'));
        try {
            Process process = Runtime.getRuntime().exec("javac " + fileName);
            System.out.println("process : " + process.getErrorStream()) ;

            // Capture the error stream
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            boolean hasErrors = false;

            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
                if (line.contains("error")) {
                    hasErrors = true;
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0 && !hasErrors) {
                System.out.println("Compilation successful." + "exit code : " + exitCode )  ;
            } else {
                System.err.println("Compilation failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void runJavaFile(String fileName) {
        try {
            // Compile the Java file
            Process compileProcess = Runtime.getRuntime().exec("javac " + fileName);
            int compileExitCode = compileProcess.waitFor();

            if (compileExitCode == 0) {
                System.out.println("Compilation successful. Running the program...");

                // Execute the compiled class
                String className = fileName.replace(".java", "");
                Process runProcess = Runtime.getRuntime().exec("java " + className);
                InputStream inputStream = runProcess.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                int runExitCode = runProcess.waitFor();
                System.out.println("Program exited with code: " + runExitCode);
            } else {
                System.out.println("Compilation failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
