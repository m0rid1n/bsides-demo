package bg.bsides.SDLC.Demo.service.impl;

import bg.bsides.SDLC.Demo.service.ValidationService;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ValidationServiceImpl implements ValidationService {
  private static final char DOT_CHARACTER = '.';
  private static final String PNG_FILE_MAGIC = "‰PNG␍␊␚␊";
  private static final int FIRST_BYTES_SIZE = 8;
  private static final String EXTENSION = "png";

  private static final int MAX_SIZE = 2000000;
  private static final int MIN_SIZE = 100;

  @Override
  public String validateFile(MultipartFile file) {
    long size = file.getSize();
    // Max size
    if (size > MAX_SIZE) {
      return "File too big! (2MB max size)";
    }
    // Min size
    if (size < MIN_SIZE) {
      return "File too small! (100b min size)";
    }
    // Extension
    if (!EXTENSION.equals(getExtension(file))) {
      return "Invalid file extension, expected " + "'" + EXTENSION + "'";
    }
    // Magic bytes
    if (!checkMagicBytes(file)) {
      return "Corrupted file!";
    }
    return "Valid";
  }

  private String getExtension(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    if (fileName == null
        || fileName.lastIndexOf(DOT_CHARACTER) == -1 && fileName.lastIndexOf(DOT_CHARACTER) == 0) {
      return "";
    }

    return fileName.substring(fileName.lastIndexOf(DOT_CHARACTER) + 1);
  }

  private boolean checkMagicBytes(MultipartFile file) {
    byte[] firstBytes = new byte[FIRST_BYTES_SIZE];
    int readBytes;
    try {
      readBytes = file.getInputStream().read(firstBytes);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return false;
    }
    byte[] array = {-119, 80, 78, 71, 13, 10, 26, 10};
    if (readBytes < FIRST_BYTES_SIZE) {
      return false;
    }
    byte[] validFirstBytes = PNG_FILE_MAGIC.getBytes(Charset.defaultCharset());
    return Arrays.equals(firstBytes, array);
  }
}
