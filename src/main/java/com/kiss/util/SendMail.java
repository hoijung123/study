package com.kiss.util;

import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	 
    public void sendMail(String sContent) {
         
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail
        p.put("mail.smtp.host", "smtp.naver.com");      // smtp 
        p.put("mail.smtp.auth","true");                 // gmail
        p.put("mail.smtp.port", "587");                 // gmail 
           
        Authenticator auth = new MyAuthentication();
         
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);
         
        try{
            msg.setSentDate(new Date());
             
            InternetAddress from = new InternetAddress() ;
             
             
            from = new InternetAddress("kinobebe@naver.com");
             
            msg.setFrom(from);
             
             
            InternetAddress to = new InternetAddress("kinobebe@naver.com");
            msg.setRecipient(Message.RecipientType.TO, to);
             
            msg.setSubject("���� ���� �׽�Ʈ", "UTF-8");
             
            msg.setText("����", "UTF-8");
             
            msg.setHeader("content-Type", "text/html");
             
            javax.mail.Transport.send(msg);
             
        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }
    }
 
}
 
 
class MyAuthentication extends Authenticator {
      
    PasswordAuthentication pa;
    
 
    public MyAuthentication(){
         
        String id = "kinobebe@naver.com";      
        String pw = "!nana1379";          
 
        pa = new PasswordAuthentication(id, pw);
      
    }
 
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}

