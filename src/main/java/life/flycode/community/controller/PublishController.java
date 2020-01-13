package life.flycode.community.controller;

import life.flycode.community.mapper.QuestionMapper;
import life.flycode.community.mapper.UserMapper;
import life.flycode.community.model.Question;
import life.flycode.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther
 * @Date:2020/1/10
 * @Description:
 * @version:1.0
 */
@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publich(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPulish(Question question, HttpServletRequest request, Model model){
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        if(StringUtils.isEmpty(question.getTitle())){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(StringUtils.isEmpty(question.getDescription())){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
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
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "redirect:/";
    }
}
