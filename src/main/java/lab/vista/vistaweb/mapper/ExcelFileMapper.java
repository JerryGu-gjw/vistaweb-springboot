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
public interface ExcelFileMapper{
    @Insert("insert into excelfile (filename, auther, path) values (#{filename}, #{auther}, #{path})")
    public int insert(String filename, String auther, String path);

    @Select("select * from excelfile ORDER BY uploadedtime DESC")
    public List<ExcelFile> findAll();

}