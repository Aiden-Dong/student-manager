package org.university.stm.start.dao;

import org.university.stm.start.bean.ClassOrganize;
import org.university.stm.start.bean.Student;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 *     bean <-> database
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
public interface STMDao {

    public List<Student> getStudents() throws IOException;
    public List<Student> getStudents(long clsId) throws IOException;
    public void removeStudent(long stId) throws IOException;
    public void removeStudents(long clsId) throws IOException;
    public void updateStudent(Student student) throws IOException;
    public void addStudent(Student student) throws IOException;

    public List<ClassOrganize> getClassOrganizes() throws IOException;
    public ClassOrganize getClassOrganize(long clsId) throws IOException;
    public void removeClsOrganize(long clsId) throws IOException;
    public void updateClsOrganize(ClassOrganize classOrganize) throws IOException;
    public void addClsOrganize(ClassOrganize classOrganize) throws IOException;
}
