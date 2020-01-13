package life.flycode.community.serivce;

import life.flycode.community.dto.QuestionDTO;
import life.flycode.community.mapper.QuestionMapper;
import life.flycode.community.mapper.UserMapper;
import life.flycode.community.model.Question;
import life.flycode.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther
 * @Date:2020/1/13
 * @Description:
 * @version:1.0
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list(Integer page, Integer size) {
        page = size * (page -1);
        List<Question> questionList = questionMapper.list(page,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
