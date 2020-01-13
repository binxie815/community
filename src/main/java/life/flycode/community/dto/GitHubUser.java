package life.flycode.community.dto;

import lombok.Data;

/**
 * @Auther
 * @Date:2020/1/5
 * @Description:
 * @version:1.0
 */
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
