package bg.bsides.SDLC.Demo.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface VirusTotalService {

  String scanFile(MultipartFile file) throws IOException;
}
