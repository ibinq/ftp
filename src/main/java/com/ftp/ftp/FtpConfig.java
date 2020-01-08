package com.ftp.ftp;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:/ftp.properties")
public  class FtpConfig {
    @Value("${ip}")
    private String ip;
    @Value("${user}")
    private String user;
    @Value("${pass}")
    private String pass ;
}
