package life.flycode.community.model;

import lombok.Data;

/**
 * @Auther
 * @Date:2020/1/12
 * @Description:
 * @version:1.0
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
}
