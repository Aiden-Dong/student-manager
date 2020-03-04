package org.university.stm.start.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.university.stm.start.bean.ClassOrganize;
import org.university.stm.start.bean.Student;
import org.university.stm.start.dao.mybatis.ClassOrganizeMapper;
import org.university.stm.start.dao.mybatis.StudentMapper;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 *     mybatis dao
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
@Repository
public class MybatisSTMDao implements STMDao {

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    @Override
    public List<Student> getStudents() throws IOException {
        try {
            return sessionTemplate.getMapper(StudentMapper.class).getStudents();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<Student> getStudents(long clsId) throws IOException {
        try {
            return sessionTemplate.getMapper(StudentMapper.class).getStudentsByClass(clsId);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


    @Override
    public void removeStudent(long stId) throws IOException {
        try {
            sessionTemplate.getMapper(StudentMapper.class).deleteStudent(stId);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void removeStudents(long clsId) throws IOException {
        try {
            sessionTemplate.getMapper(StudentMapper.class).deleteStudents(clsId);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void updateStudent(Student student) throws IOException {
        try {
            sessionTemplate.getMapper(StudentMapper.class).updateStudent(student);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void addStudent(Student student) throws IOException {
        try {
            sessionTemplate.getMapper(StudentMapper.class).insertStudent(student);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public ClassOrganize getClassOrganize(long clsId) throws IOException {
        try {
            return sessionTemplate.getMapper(ClassOrganizeMapper.class).getClassOrganize(clsId);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<ClassOrganize> getClassOrganizes() throws IOException {
        try {
            return sessionTemplate.getMapper(ClassOrganizeMapper.class).getClassOrganizes();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    @Transactional
    public void removeClsOrganize(long clsId) throws IOException {
        try {
            sessionTemplate.getMapper(StudentMapper.class).deleteStudents(clsId);
            sessionTemplate.getMapper(ClassOrganizeMapper.class).deleteClassOrganize(clsId);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void updateClsOrganize(ClassOrganize classOrganize) throws IOException {
        try {
            sessionTemplate.getMapper(ClassOrganizeMapper.class).updateClassOrganize(classOrganize);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void addClsOrganize(ClassOrganize classOrganize) throws IOException {
        try {
            sessionTemplate.getMapper(ClassOrganizeMapper.class).insertClassOrganize(classOrganize);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}