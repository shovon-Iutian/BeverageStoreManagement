package com.example.appengine.java8.Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MailSender", value = "/mailSender")
public class MailServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("Sending simple email.");
        sendSimpleMail();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("Sending simple email.");
        sendSimpleMail();
    }

    private void sendSimpleMail() {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("alnoman.cse@gmail.com", "Abdullah Al Noman"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("abdullah-al.noman@stud.uni-bamberg.de", "Mr. Abdullah Al Noman"));
            msg.setSubject("Hello world");
            msg.setText("This is a test");
            Transport.send(msg);
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException e) {
            // ...
        }
        // [END simple_example]
    }
}