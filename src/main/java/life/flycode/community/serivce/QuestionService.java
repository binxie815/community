package life.flycode.community.serivce;

import life.flycode.community.dto.PageInfoDATO;
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

    public PageInfoDATO list(Integer page, Integer size) {
        int offpage = size * (page -1);
        List<Question> questionList = questionMapper.list(offpage,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PageInfoDATO pageInfoDATO = new PageInfoDATO();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageInfoDATO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.count();
        pageInfoDATO.setPagination(totalCount,page,size);
        return pageInfoDATO;
    }
}
