package com.ftp.mail;

import lombok.Data;

import java.util.List;

@Data
public class Mail {

    private String protocol;
    private String host;
    private String port;
    private String auth;
    private String enable;
    private String sendEmail;
    private List<String> reciverEmail;
    private String title;
    private String content;
    private String sendPass;

}
