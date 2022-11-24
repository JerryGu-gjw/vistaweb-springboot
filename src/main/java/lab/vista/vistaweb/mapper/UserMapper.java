package lab.vista.vistaweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import lab.vista.vistaweb.entity.*;

@MapperScan
@Repository
public interface UserMapper{
    @Insert("insert into User (u_account, u_password, u_email) values (#{u_account}, #{u_password}, #{u_email})")
    public int insert(String u_account, String u_password, String u_email);

    @Select("select * from User")
    public List<User> findAll();

    @Select("select * from User where u_account=#{u_account} AND u_password=#{u_password}")
    public User login(String u_account, String u_password);

    @Select("select * from User where u_account=#{u_account}")
    public User findByAccount(String u_account);

    @Select("select * from User where u_id=#{u_id}")
    public User findById(int u_id);

    @Delete("delete from User where u_account=#{u_account}")
    public int delete(String account);
}
