package ru.jnster;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * class to send text emails
 *
 * @author Anton Klimov (jnster@yandex.ru)
 */

public class EmailSender {
    private String mAddresser;
    private String mAddressee;
    private Session mSession;
    private Properties mProps;
    private MimeMessage mMessage;

    /**
     * Конструктор с параметрами
     * @param addresser Адрес электоронной почты отправителя
     * @param addressee Адрес электронной почты получателя
     * @param host      Адрес SMTP-сервера
     * @param password  Пароль от почты отправителя
     * @param port      Порт для SMTP-сервера
     */
    public EmailSender (final String addresser, String addressee, final String host, final String password, int port){
        mAddresser = addresser;
        mAddressee = addressee;
        mProps = System.getProperties();
        mProps.put("mail.smtp.auth", "true");
        mProps.put("mail.smtp.host", host);
        mProps.put("mail.smtp.port", String.valueOf(port));
        mProps.put("mail.smtp.socketFactory.port", String.valueOf(port));
        mProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mSession = Session.getInstance(mProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(addresser, password);
            }
        });
        mMessage = new MimeMessage(mSession);
    }

    /**
     * Метод отправки письма
     * @param Addresser Адрес электронной почты отправителя
     * @param Addressee Адрес электронной почты получателя
     * @param subject   Заголовок письма
     * @param text      Текс письма
     */

    public void send(String Addresser, String Addressee, String subject, String text) {
        try {
            mMessage.setFrom(new InternetAddress(Addresser));
            mMessage.addRecipients(Message.RecipientType.TO, Addressee);
            mMessage.setSubject(subject);
            mMessage.setText(text);
            Transport.send(mMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод отправки письма
     * @param addressee Адрес электронной почты получателя
     * @param subject   Заголовок письма
     * @param text      Текст письма
     */
    public void send (String addressee, String subject, String text){
        send(mAddresser,addressee,subject,text);
    }

    /**
     * Метод отправки письма
     * @param subject  Заголовок письма
     * @param text     Текст письма
     */

    public void send (String subject, String text){
        send(mAddresser,mAddressee, subject, text);
    }

    public  void setAddresser (String addresser, String password){
        mAddresser = addresser;
        mSession = Session.getInstance(mProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return  new PasswordAuthentication(mAddresser, password);
            }
        });
        mMessage = new MimeMessage(mSession);
    }

    public void setAddressee(String mAddressee) {
        this.mAddressee = mAddressee;
    }

    public String getAddresser(){
        return mAddresser;
    }

    public String getAddressee(){
        return mAddressee;
    }
}
