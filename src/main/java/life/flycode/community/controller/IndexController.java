package life.flycode.community.controller;

import life.flycode.community.serivce.QuestionService;
import life.flycode.community.dto.QuestionDTO;
import life.flycode.community.mapper.UserMapper;
import life.flycode.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther
 * @Date:2019/12/26
 * @Description:
 * @version:1.0
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String greeting(HttpServletRequest request, Model model,
                           @RequestParam(name = "page",defaultValue = "1") Integer page,
                           @RequestParam(name = "size",defaultValue = "0") Integer size) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        //compiler.automake.allow.when.app.running shift+ctrl+alt+? 设置在run或者debug情况下都自动部署
        List<QuestionDTO> questionDTOList= questionService.list(page,size);
        model.addAttribute("questions",questionDTOList);
        return "index";
    }
}
