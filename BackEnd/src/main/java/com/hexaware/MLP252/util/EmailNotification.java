package com.hexaware.MLP252.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotification {

  protected EmailNotification() {

  }

  /**
   *
   * @param recipient recipient address
   * @param subject   subject content
   * @param body      body of the email
   * @return 1
   * @throws AddressException   handling exception
   * @throws MessagingException handling exception
   */
  public static int sendEmail(final String recipient, final String subject, final String body)
      throws AddressException, MessagingException {

    final Properties properties = new Properties();

    System.out.println("Preparing Email");

    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    final String myAccount = "team.mlp252@gmail.com";
    final String pass = "Watson252";
    // user id: no.reply.mlp250.attendancetracker@gmail.com
    // password: skulcrushr

    final Session session = Session.getInstance(properties, new Authenticator() {

      @Override
      protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(myAccount, pass);

      }

    });

    final Message message = prepareMessage(session, myAccount, recipient, subject, body);

    Transport.send(message);

    System.out.println("Email Sent Successfully");
    return 1;

  }

  /**
   *
   * @param session   session
   * @param myAccount defining account
   * @param recipient recipient address
   * @param subject   subject
   * @param body      body
   * @return message
   * @throws AddressException   handling exception
   * @throws MessagingException handling exception
   */
  private static Message prepareMessage(final Session session, final String myAccount, final String recipient,
      final String subject, final String body) throws AddressException, MessagingException {
    final Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myAccount));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    String ccmail = "vijithiyagraj@gmail.com";
    message.setRecipient(Message.RecipientType.CC, new InternetAddress(ccmail));
    message.setSubject(subject);
    message.setContent(body, "text/html");

    return message;

  }

}
