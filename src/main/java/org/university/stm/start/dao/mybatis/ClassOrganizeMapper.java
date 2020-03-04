package org.university.stm.start.dao.mybatis;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.university.stm.start.bean.ClassOrganize;

import java.util.List;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
@Repository
public interface ClassOrganizeMapper {

    @Select("select cls_id, cls_name, cls_create_time, cls_manager from `cls_meta`")
    public List<ClassOrganize> getClassOrganizes();

    @Select("select cls_id, cls_name, cls_create_time, cls_manager from `cls_meta` where cls_id = #{clsId}")
    public ClassOrganize getClassOrganize(@Param("clsId") long clsId);

    @Update("update `cls_meta` set cls_manager=#{clsManager} where cls_id=#{clsId}")
    public void updateClassOrganize(ClassOrganize classOrganize);

    @Delete("delete from `cls_meta` where cls_id=#{clsId}")
    public void deleteClassOrganize(@Param("clsId") long clsId);

    @Insert("insert into `cls_meta`(cls_id, cls_name, cls_create_time, cls_manager) " +
            "values(#{clsId}, #{clsName}, #{clsCreateTime}, #{clsManager}) " +
            "on duplicate key update cls_manager=values(cls_manager)")
    public void insertClassOrganize(ClassOrganize classOrganize);
}
