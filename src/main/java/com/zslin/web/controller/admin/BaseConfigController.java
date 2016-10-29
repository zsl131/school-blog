package com.zslin.web.controller.admin;

import com.zslin.basic.annotations.AdminAuth;
import com.zslin.basic.tools.ConfigTools;
import com.zslin.basic.tools.MyBeanUtils;
import com.zslin.basic.tools.NormalTools;
import com.zslin.web.model.BaseConfig;
import com.zslin.web.service.IBaseConfigService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 15:49.
 */
@Controller
@RequestMapping(value = "admin/baseConfig")
@AdminAuth(name="配置管理", orderNum=10, psn="系统应用", pentity=0, porderNum=20)
public class BaseConfigController {

    @Autowired
    private IBaseConfigService baseConfigService;

    @Autowired
    private ConfigTools configTools;

    @AdminAuth(name="网站配置", orderNum=1, icon="glyphicon glyphicon-cog", type="1")
    @RequestMapping(value="index", method= RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        BaseConfig baseConfig = baseConfigService.loadOne();
        baseConfig = baseConfig == null?new BaseConfig() : baseConfig;
        model.addAttribute("baseConfig", baseConfig);
        return "admin/baseConfig/index";
    }

    @RequestMapping(value="index", method=RequestMethod.POST)
    public String index(Model model, BaseConfig baseConfig, HttpServletRequest request, @RequestParam("file")MultipartFile[] files) {
        BaseConfig bc = baseConfigService.loadOne();

        if(files!=null && files.length>=1) {
            BufferedOutputStream bw = null;
            try {
                String fileName = files[0].getOriginalFilename();
                if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                    File outFile = new File(configTools.getUploadPath("school") + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                    baseConfig.setLogoPic(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                    FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                }

                if(files.length>=2) {
                    String fileName2 = files[1].getOriginalFilename();
                    if (fileName2 != null && !"".equalsIgnoreCase(fileName2.trim()) && NormalTools.isImageFile(fileName2)) {
                        File outFile = new File(configTools.getUploadPath("school") + "/" + UUID.randomUUID().toString() + NormalTools.getFileType(fileName2));
                        baseConfig.setHeadimg(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                        FileUtils.copyInputStreamToFile(files[1].getInputStream(), outFile);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(bw!=null) {bw.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(bc==null) {
            baseConfigService.save(baseConfig);
        } else {
            MyBeanUtils.copyProperties(baseConfig, bc, new String[]{"id"});
            baseConfigService.save(bc);
        }
        request.getSession().setAttribute("baseConfig", baseConfig); //修改后需要修改一次Session中的值
        return "redirect:/admin/baseConfig/index";
    }
}
