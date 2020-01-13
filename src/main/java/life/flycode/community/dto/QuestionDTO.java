package life.flycode.community.dto;

import life.flycode.community.model.Question;
import life.flycode.community.model.User;
import lombok.Data;

/**
 * @Auther
 * @Date:2020/1/13
 * @Description:
 * @version:1.0
 */
@Data
public class QuestionDTO {
    private Question question;
    private User user;
}
