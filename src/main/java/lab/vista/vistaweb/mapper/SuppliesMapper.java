package lab.vista.vistaweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import lab.vista.vistaweb.entity.*;

@MapperScan
@Repository
public interface SuppliesMapper{
    @Insert("insert into supplies_info (name, type, storagearea, totalnum, borrowablenum, remark, category) values (#{name}, #{type}, #{storagearea}, #{totalnum}, #{borrowablenum}, #{remark}, #{category})")
    public int insert(String name, String type, String storagearea, String totalnum, String borrowablenum, String remark, String category);

    @Select("select * from supplies_info ORDER BY time DESC")
    public List<Supplies> findAll();

    @Select("select path from supplies_info where id = #{id}")
    public Supplies getPathById(String id);

    @Select("delete from supplies_info where id = #{id}")
    public String deleteById(String id);

}