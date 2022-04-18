package bg.bsides.SDLC.Demo.service.impl;

import bg.bsides.SDLC.Demo.service.UploadService;
import bg.bsides.SDLC.Demo.service.ValidationService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class UploadServiceImpl implements UploadService {

  private static final String EXT = "";

  public static final String UPLOAD_PATH_SECURE = "upload/";
  public static final String UPLOAD_PATH_INSECURE = "src/main/webapp/WEB-INF/uploads/";


  private final ValidationService validationService;

  @Override
  public String uploadFileInsecure(MultipartFile file) {
    return uploadFile(file, UPLOAD_PATH_INSECURE, "");
  }

  @Override
  public String uploadFileSecure(MultipartFile file) {
    String valid = validationService.validateFile(file);
    if ("Valid".equalsIgnoreCase(valid)) {
      return uploadFile(file, UPLOAD_PATH_SECURE, generateFileName(file));
    }
    return valid;
  }

  private String generateFileName(MultipartFile file) {
    String sanitizedName = Objects.requireNonNull(file.getOriginalFilename()).replaceAll("[^a-zA-Z\\d.-]", "_");
    return Timestamp.valueOf(LocalDateTime.now()).getTime() + "_" + sanitizedName + EXT;
  }

  @Override
  public ResponseEntity<InputStreamResource> downloadFile(String name) throws IOException {
    File file = new File(UPLOAD_PATH_SECURE + name);
    HttpHeaders headers = new HttpHeaders();
    if (file.exists()) {
      String mimeType = URLConnection.guessContentTypeFromName(file.getName());
      if (mimeType == null) {
        mimeType = "application/octet-stream";
      }

      headers.add(HttpHeaders.CONTENT_TYPE, mimeType);
      headers.add(HttpHeaders.CONTENT_TYPE, "text/html;charset=UTF-8");
      headers.add(HttpHeaders.CONTENT_LANGUAGE, "en-US");
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
    }
    return ResponseEntity.ok()
        .contentLength((int) file.length())
        .headers(headers)
        .body(new InputStreamResource(new FileInputStream(file)));
  }

  @NotNull
  private String uploadFile(MultipartFile file, String uploadPath, String fileName) {
    if (file.isEmpty()) {
      return "Empty file";
    }
    try {
      byte[] bytes = file.getBytes();
      String filePath = uploadPath + "/" + ("".equalsIgnoreCase(fileName) ? file.getOriginalFilename() : fileName);
      Path path = Paths.get(filePath);
      Files.write(path, bytes);
    } catch (IOException exception) {
      System.out.println(exception.getMessage());
      return "Error occurred";
    }
    return "Success," + ("".equalsIgnoreCase(fileName) ? file.getOriginalFilename() : fileName);
  }
}
