package org.wyf;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wyf on 16/9/25.
 */
public class BaseTest {
    protected ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        this.context = new ClassPathXmlApplicationContext("spring-config.xml");
    }
}