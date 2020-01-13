package life.flycode.community.model;

import lombok.Data;

/**
 * @Auther
 * @Date:2020/1/9
 * @Description:
 * @version:1.0
 */
@Data //该注解，会自动生成get/set方法
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
