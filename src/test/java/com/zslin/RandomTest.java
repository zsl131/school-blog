package com.zslin;

import com.zslin.web.tools.RandomTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/30 11:47.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("zsl")
public class RandomTest {

    @Test
    public void testRandom() {
        System.out.println(RandomTools.randomCode());
    }
}
