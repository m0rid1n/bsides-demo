package bg.bsides.SDLC.Demo.service;

import java.io.IOException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
  String uploadFileInsecure(MultipartFile file);

  String uploadFileSecure(MultipartFile file) throws IOException;

  ResponseEntity<InputStreamResource> downloadFile(String name) throws IOException;
}
