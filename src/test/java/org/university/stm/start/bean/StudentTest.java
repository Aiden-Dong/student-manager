package org.university.stm.start.bean;

import org.junit.Test;
import org.university.stm.start.common.JsonUtil;

import java.io.IOException;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
public class StudentTest {
    @Test
    public void testBean() throws IOException {
        String testStr = "{\n" +
                "    \"stName\" : \"测试\",\n" +
                "    \"stStartTime\" : 1582617975,\n" +
                "    \"clsId\" : 2013021345,\n" +
                "    \"stCity\" : \"临沂\",\n" +
                "    \"stSex\" : \"男\",\n" +
                "    \"stPolitical\" : \"共青团员\" \n" +
                "}";

        Student student = JsonUtil.json2Bean(testStr, Student.class);
        System.out.println(student);
    }
}
