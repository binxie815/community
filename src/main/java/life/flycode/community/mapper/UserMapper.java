package life.flycode.community.mapper;

import life.flycode.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther
 * @Date:2020/1/9
 * @Description:
 * @version:1.0
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(ACCOUNT_ID, NAME, TOKEN, GMT_CREATE, GMT_MODIFIED) VALUES (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    @Select("SELECT * from USER WHERE TOKEN = #{token}")
    User findByToken(@Param("token") String token);
}
