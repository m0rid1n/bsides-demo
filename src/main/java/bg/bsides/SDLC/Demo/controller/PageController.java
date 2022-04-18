package bg.bsides.SDLC.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
  @GetMapping("/*")
  public String home() {
    return "home";
  }

  @GetMapping("/file")
  public String file() {
    return "file";
  }

  @GetMapping("/level-1")
  public String level1() {
    return "level1";
  }

  @GetMapping("/level-2")
  public String level2() {
    return "level2";
  }

  @GetMapping("/level-3")
  public String level3() {
    return "level3";
  }
}
