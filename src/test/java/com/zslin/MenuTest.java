package com.zslin;

import com.zslin.basic.dto.MenuDto;
import com.zslin.basic.model.Menu;
import com.zslin.basic.service.MenuServiceImpl;
import com.zslin.basic.tools.AuthTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/26 11:30.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("zsl")
public class MenuTest {

    @Autowired
    private AuthTools authTools;

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    @Test
    public void initMenus() {
        //com/zslin/*/controller/**Controller.class
        authTools.buildSystemMenu("com/zslin/basic/controller/*Controller.class");
    }

    @Test
    public void testQueryMenus() {
        List<MenuDto> list = menuServiceImpl.queryMenuDtoNew(1);
        System.out.println(list.size());
        for(MenuDto md : list) {
            System.out.println("==========="+md.getPm().getName());
            for(Menu m : md.getChildren()) {
                System.out.println("    ++" + m.getName());
            }
        }
    }
}
