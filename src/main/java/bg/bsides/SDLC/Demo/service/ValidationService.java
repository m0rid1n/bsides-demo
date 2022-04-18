package bg.bsides.SDLC.Demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ValidationService {

  String validateFile(MultipartFile file);
}
