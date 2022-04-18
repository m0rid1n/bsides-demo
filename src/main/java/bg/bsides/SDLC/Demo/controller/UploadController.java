package bg.bsides.SDLC.Demo.controller;

import bg.bsides.SDLC.Demo.service.UploadService;
import bg.bsides.SDLC.Demo.service.VirusTotalService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class UploadController {
  private final UploadService uploadService;
  private final VirusTotalService virusTotalService;

  @ResponseBody
  @PostMapping("/file1")
  public ResponseEntity<String> uploadFile1(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok(uploadService.uploadFileInsecure(file));
  }

  @ResponseBody
  @PostMapping("/file2")
  public ResponseEntity<String> uploadFile2(@RequestParam("file") MultipartFile file)
      throws IOException {
    String body = uploadService.uploadFileSecure(file);
    if (body.contains("Success")) {
      return ResponseEntity.ok(body.split(",")[1]);
    }
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ResponseBody
  @PostMapping("/file3")
  public ResponseEntity<String> uploadFile3(@RequestParam("file") MultipartFile file)
      throws IOException {
    String scanResult = virusTotalService.scanFile(file);
    if (scanResult != null) {
      return new ResponseEntity<>(scanResult, HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(uploadService.uploadFileSecure(file));
  }

  @GetMapping("/file/{name:.+}/")
  public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String name)
      throws IOException {
    return uploadService.downloadFile(name);
  }
}
