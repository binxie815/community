package life.flycode.community.mapper;

import life.flycode.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther
 * @Date:2020/1/12
 * @Description:
 * @version:1.0
 */
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO QUESTION(TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) VALUES (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("SELECT * FROM QUESTION limit #{page},#{size}")
    List<Question> list(@Param("page") Integer page,@Param("size") Integer size);

    @Select("SELECT count(ID) from QUESTION")
    Integer count();
}
