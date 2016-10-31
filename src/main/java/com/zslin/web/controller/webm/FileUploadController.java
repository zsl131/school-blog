package com.zslin.web.controller.webm;

import com.zslin.basic.tools.ConfigTools;
import com.zslin.basic.tools.NormalTools;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 使用wangEditor编辑器的文件上传Controller
 * Created by 钟述林 393156105@qq.com on 2016/10/31 14:55.
 */
@Controller
public class FileUploadController {

    @Autowired
    private ConfigTools configTools;

    @RequestMapping(value = "/upload")
    public @ResponseBody String upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("myFileName")MultipartFile[] files)
            throws ServletException, IOException {
        String filePath = "";
        try {
            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                        File outFile = new File(configTools.getUploadPath("article") + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        filePath = outFile.getAbsolutePath().replace(configTools.getUploadPath(), "");
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        filePath = RequestTools.getCurUrl(request)+filePath;
        return filePath;
    }
}
