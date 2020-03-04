package org.university.stm.start.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.university.stm.start.StudentManagerApplication;
import org.university.stm.start.bean.ClassOrganize;
import org.university.stm.start.bean.Student;
import org.university.stm.start.common.JsonUtil;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisSTMDaoTest {
    @Autowired
    private STMDao stmDao;

    @Test
    public void testClassMapper() throws IOException {
        String modeStr = "{\n" +
                "    \"clsName\" : \"八年级1班\",\n" +
                "    \"clsCreateTime\" : 1582617582000,\n" +
                "    \"clsManager\" : \"None\"\n" +
                "}";
        ClassOrganize classOrganize = JsonUtil.json2Bean(modeStr, ClassOrganize.class);
        stmDao.addClsOrganize(classOrganize);

        classOrganize.setClsManager("吴勇");

        stmDao.updateClsOrganize(classOrganize);

        List<ClassOrganize> classOrganizes = stmDao.getClassOrganizes();

        for(ClassOrganize cls : classOrganizes){
            System.out.println(cls);
        }

        stmDao.removeClsOrganize(classOrganize.getClsId());
    }


    @Test
    public void testStudents() throws IOException {
        String testStr = "{\n" +
                "    \"stName\" :  \"陈廷\",\n" +
                "    \"stStartTime\" : 787507200000,\n" +
                "    \"clsId\" : 2013170822,\n" +
                "    \"stCity\" : \"日照\",\n" +
                "    \"stSex\" : \"男\",\n" +
                "    \"stPolitical\" : \"共青团员\"\n" +
                "}";

        Student student = JsonUtil.json2Bean(testStr, Student.class);
        stmDao.addStudent(student);

        student.setStCity("青岛");
        stmDao.updateStudent(student);

        List<Student> students = stmDao.getStudents(2013170822);
        for(Student st : students){
            System.out.println(st);
        }
        stmDao.removeStudents(2013170822);
    }
}
