package com.ftp.ftp;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String upload(MultipartFile file, String path,FtpConfig ftpConfig);
}
