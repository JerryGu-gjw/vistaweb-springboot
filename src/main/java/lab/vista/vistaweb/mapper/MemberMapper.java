package lab.vista.vistaweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import lab.vista.vistaweb.entity.*;

@MapperScan
@Repository
public interface MemberMapper{
    @Insert("insert into member_info (name, colleage, specialty, phonenum, email) values (#{name}, #{colleage}, #{specialty}, #{phonenum}, #{email})")
    public int insert(String name, String colleage, String specialty, String phonenum, String email);

    @Select("select * from member_info ORDER BY id")
    public List<Member> findAll();

    @Select("select path from invoice_info where id = #{id}")
    public Member getPathById(String id);

    @Select("delete from invoice_info where id = #{id}")
    public String deleteById(String id);


}