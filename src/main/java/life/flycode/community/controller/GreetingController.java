package life.flycode.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther
 * @Date:2019/12/26
 * @Description:
 * @version:1.0
 */
@Controller
public class GreetingController {
    @GetMapping("/")
    public String greeting() {
        return "index";
    }
}
