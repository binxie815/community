package life.flycode.community.controller;

import life.flycode.community.dto.AccessTokenDTO;
import life.flycode.community.dto.GitHubUser;
import life.flycode.community.mapper.UserMapper;
import life.flycode.community.model.User;
import life.flycode.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Auther
 * @Date:2020/1/5
 * @Description:
 * @version:1.0
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}") //从配置文件加载对应值
    private String clientId;
    @Value("${github.client.secret}") //从配置文件加载对应值
    private String clientSecret;
    @Value("${github.redirect.url}") //从配置文件加载对应值
    private String redirectUrl;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken); //shift + F6 (可以修改选择中的代码)
        if(gitHubUser != null){
            String token = UUID.randomUUID().toString();
            User user = new User();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            userMapper.insertUser(user);
            //自定义cookie值
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}
