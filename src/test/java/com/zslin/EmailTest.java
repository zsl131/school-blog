package com.zslin;

import com.zslin.web.tools.EmailTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/30 11:36.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("zsl")
public class EmailTest {

    @Autowired
    private EmailTools emailTools;

    @Test
    public void testCodeEmail() {
        emailTools.sendRegisterCode("398986099@qq.com", "123456");
    }
}
