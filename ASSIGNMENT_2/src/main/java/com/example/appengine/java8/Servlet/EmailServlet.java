package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.Entity.VoteEntity;
import com.example.appengine.java8.Management.VoteManagement;
import com.google.appengine.api.datastore.Query;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmailSender", value = "/admin/emailSender")
public class EmailServlet extends HttpServlet {

    private VoteManagement voterManaging = new VoteManagement();
    private VoteEntity voteEntity = new VoteEntity();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.getWriter().print("Sending simple email.");
//        sendSimpleMail2();
    }

    private void sendSimpleMail2(List<Voter> voterList, String votingBoothUrl) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.port", "587");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abdullah-al.noman@stud.uni-bamberg.de", "Nn633250");
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("abdullah-al.noman@stud.uni-bamberg.de",
                    "Abdullah Al Noman"));
            msg.setSubject("Your voter information for voting");

            for(Voter voter : voterList){
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(voter.getEmail(), voter.getName()));

                String message = "<h2>Greetings "+voter.getName()+"!</h2><br>";
                message += "<h3>Wish you a nice day!</h3><br>";
                message += "<p>Your voter token: <u>"+voter.getToken()+"<u></p></br></br></br></br>";
                message += "<p>To cast your vote please go to <a href='"+votingBoothUrl+"/admin/candidates' target='_blank'>Voting booth</a></p>";


                msg.setContent(message, "text/html");
                Transport.send(msg);
            }
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException e) {
            // ...
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String votingBothUrl = getBaseUrl(req);
        Query query = new Query(voteEntity.getROOTKIND());
        System.out.println(query.toString());
        List<Voter> voterList = voterManaging.get(query);
        System.out.println(voterList);

        sendSimpleMail3(voterList, votingBothUrl);
    }

    private void sendSimpleMail3(List<Voter> voterList, String votingBoothUrl) {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("alnoman.cse@gmail.com", "Abdullah Al Noman"));
            msg.setSubject("Your voter information for voting");

            for(Voter voter : voterList){
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(voter.getEmail(), voter.getName()));

                String message = "<h2>Greetings "+voter.getName()+"!</h2><br>";
                message += "<h3>Wish you a nice day!</h3><br>";
                message += "<p>Your voter token: <u>"+voter.getToken()+"<u></p></br></br></br></br>";
                message += "<p>To cast your vote please go to <a href='"+votingBoothUrl+"/admin/candidates' target='_blank'>Voting booth</a></p>";


                msg.setContent(message, "text/html");
                Transport.send(msg);
            }
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException e) {
            // ...
        }
    }

    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
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