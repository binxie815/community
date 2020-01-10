package life.flycode.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther
 * @Date:2020/1/10
 * @Description:
 * @version:1.0
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publich(){
        return "publish";
    }
}
