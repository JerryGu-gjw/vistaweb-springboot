package lab.vista.vistaweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import lab.vista.vistaweb.entity.*;

@MapperScan
@Repository
public interface InvoiceMapper{
    @Insert("insert into invoice_info (invoicename, applicant, amount, path, remark, category) values (#{invoicename}, #{applicant}, #{amount}, #{path}, #{remark}, #{category})")
    public int insert(String invoicename, String applicant, String amount, String path, String remark, String category);

    @Select("select * from invoice_info ORDER BY time DESC")
    public List<Invoice> findAll();

    @Select("select path from invoice_info where id = #{id}")
    public Invoice getPathById(String id);

    @Select("delete from invoice_info where id = #{id}")
    public String deleteById(String id);


}