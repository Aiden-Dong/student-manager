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
public class ClassOrganizeTest {
    @Test
    public void testBean() throws IOException {
        String modeStr = "{\n" +
                "    \"clsName\" : \"八年级1班\",\n" +
                "    \"clsCreateTime\" : 1582617582000,\n" +
                "    \"clsManager\" : \"None\"\n" +
                "}";
        ClassOrganize classOrganize = JsonUtil.json2Bean(modeStr, ClassOrganize.class);

        System.out.println(classOrganize);
    }
}


