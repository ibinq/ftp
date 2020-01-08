package com.ftp.ftp;

import com.ftp.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/ftp")
public class FtpController {

    @Autowired
    IFileService iFileService;
    @Autowired
    FtpConfig ftpConfig;

    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(HttpSession session, @RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,path,ftpConfig);
            FtpConfig config = new FtpConfig();
            String url = config.getIp() + targetFileName;
            return Result.success(url);
    }

}
