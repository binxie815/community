package life.flycode.community.controller;

import life.flycode.community.dto.PageInfoDATO;
import life.flycode.community.mapper.UserMapper;
import life.flycode.community.model.User;
import life.flycode.community.serivce.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther
 * @Date:2020/1/17
 * @Description:
 * @version:1.0
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/index/{action}")
    public String index(@PathVariable(name = "action") String action, Model model,
                        HttpServletRequest request,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)) {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PageInfoDATO pageInfoDATO = questionService.listByUserId(user.getId(), page, size);
        model.addAttribute("pageInfo",pageInfoDATO);
        return "profile";
    }
}
