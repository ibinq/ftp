package com.ftp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/ftp")
public class FtpController {

    @Autowired
    IFileService iFileService;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpSession session,@RequestParam(value = "file",required = false) MultipartFile file,HttpServletRequest request){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,path);
            String url = "49.233.201.62/" + targetFileName;
            return url;
    }

}
