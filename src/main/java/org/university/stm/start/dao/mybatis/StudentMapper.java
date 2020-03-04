package org.university.stm.start.dao.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.university.stm.start.bean.Student;

import java.util.List;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
@Repository
public interface StudentMapper {
    @Select("select st_id, st_name, st_start_time, cls_id, st_city, st_sex, st_political from `st_meta`")
    public List<Student> getStudents();

    @Select("select st_id, st_name, st_start_time, cls_id, st_city, st_sex, st_political from `st_meta` where cls_id=#{clsId}")
    public List<Student> getStudentsByClass(@Param("clsId")long clsId);

    @Delete("delete from `st_meta` where st_id = #{stId}")
    public void deleteStudent(@Param("stId") long stId);

    @Delete("delete from `st_meta` where cls_id=#{clsId}")
    public void deleteStudents(@Param("clsId")long clsId);

    @Update("update `st_meta` set st_city=#{stCity} where st_id = #{stId}")
    public void updateStudent(Student student);

    @Update("insert into `st_meta`(st_id, st_name, st_start_time, cls_id, st_city, st_sex, st_political) " +
            "values(#{stId}, #{stName}, #{stStartTime}, #{clsId}, #{stCity}, #{stSex}, #{stPolitical}) " +
            "on duplicate key update st_city=values(st_city), st_political=values(st_political)")
    public void insertStudent(Student student);
}
