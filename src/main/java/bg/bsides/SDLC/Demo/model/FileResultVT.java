package bg.bsides.SDLC.Demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileResultVT {

  private VTData data;

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class VTData {
    private VTAttributes attributes;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class VTAttributes {
    @JsonProperty("popular_threat_classification")
    private VTThreatClassification popularThreatClassification;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VTThreatClassification {
      @JsonProperty("suggested_threat_label")
      private String suggestedThreatLabel;
    }
  }
}
