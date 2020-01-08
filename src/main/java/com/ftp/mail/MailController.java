package com.ftp.mail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ftp.common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@RestController
@RequestMapping("/mail")
public class MailController {

    @RequestMapping("/sendByQq")
    public Result sendByQq(@RequestBody String data) throws Exception{
         Mail mail =JSONObject.parseObject(data,Mail.class);
         Properties properties = new Properties();
         properties.put("mail.transport.protocol", mail.getProtocol());// 连接协议
         properties.put("mail.smtp.host", mail.getHost());// 主机名
         properties.put("mail.smtp.port", mail.getPort());// 端口号
         properties.put("mail.smtp.auth", mail.getAuth());
         properties.put("mail.smtp.ssl.enable", mail.getEnable());// 设置是否使用ssl安全连接 ---一般都使用
         properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
         // 得到回话对象
         Session session = Session.getInstance(properties);
         // 获取邮件对象
         Message message = new MimeMessage(session);
         // 设置发件人邮箱地址
         message.setFrom(new InternetAddress(mail.getSendEmail()));
         // 设置收件人邮箱地址
        InternetAddress[] addresses = new InternetAddress[mail.getReciverEmail().size()];
        for (int i=0; i<mail.getReciverEmail().size();i++){
             InternetAddress internetAddresses = new InternetAddress(mail.getReciverEmail().get(i));
             addresses[i]=internetAddresses;
        }
         message.setRecipients(Message.RecipientType.TO, addresses);
         // 设置邮件标题
         message.setSubject(mail.getTitle());
         // 设置邮件内容
         message.setText(mail.getContent());
         // 得到邮差对象
         Transport transport = session.getTransport();
         // 连接自己的邮箱账户
         transport.connect(mail.getSendEmail(), mail.getSendPass());// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
         // 发送邮件
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
         return Result.success();
    }
/* public Result sendByQq(Mail mail) throws Exception{
  Properties properties = new Properties();
  properties.put("mail.transport.protocol", "smtp");// 连接协议
  properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
  properties.put("mail.smtp.port", 465);// 端口号
  properties.put("mail.smtp.auth", "true");
  properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
  properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
  // 得到回话对象
  Session session = Session.getInstance(properties);
  // 获取邮件对象
  Message message = new MimeMessage(session);
  // 设置发件人邮箱地址
  message.setFrom(new InternetAddress("1289659617@qq.com"));
  // 设置收件人邮箱地址
  message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("zhoubingl_l@163.com")});
  //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
  // 设置邮件标题
  message.setSubject("xmqtest");
  // 设置邮件内容
  message.setText("邮件内容邮件内容邮件内容xmqtest");
  // 得到邮差对象
  Transport transport = session.getTransport();
  // 连接自己的邮箱账户
  transport.connect("1289659617@qq.com", "bjmnccqzdaizfibg");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
  // 发送邮件
  transport.sendMessage(message, message.getAllRecipients());
  transport.close();
  return Result.success();
 }*/

}
