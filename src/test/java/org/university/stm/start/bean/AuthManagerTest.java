package org.university.stm.start.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.university.stm.start.StudentManagerApplication;

/**
 * <pre>
 * </pre>
 *
 * @author : aiden
 * @date : 2020-02-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthManagerTest {

    @Autowired
    private AuthManager authManager;

    @Test
    public void testAuthManager(){
        System.out.println(authManager);
    }
}
