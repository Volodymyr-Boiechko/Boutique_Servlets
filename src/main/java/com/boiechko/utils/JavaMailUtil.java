package com.boiechko.utils;

import com.boiechko.entity.Person;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Random;

public class JavaMailUtil {

    private final static String myAccountEmail = "boiechko.work@gmail.com";
    private final static String password = "28171821";

    private final String code;
    private final String type;
    private Person person;
    private String recipient;
    private  String comment;

    public void setPerson(Person person) { this.person = person; }

    public void setComment(String comment) { this.comment = comment; }

    public String getCode() { return code; }

    public JavaMailUtil(String type) {

        code = generateCode();
        this.type = type;
    }

    public void sendMail(String recipient) {

        this.recipient = recipient;

        try {

            Properties properties = new Properties();
            properties.load(JavaMailUtil.class.getClassLoader().getResourceAsStream("mail.properties"));

            Session session = Session.getInstance(properties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });

            Message message = prepareMessage(session);

            assert message != null;
            Transport.send(message);

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }

    private Message prepareMessage(Session session) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject(getSubject());

            message.setContent(formHtmlCode(), "text/html;charset=UTF-8");

            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String getSubject() {

        switch (type) {
            case "confirmRegistration": return "Активація акаунту";
            case "recoverPassword": return "Відновлення паролю";
            case "questionFromUser": return "Запитання від користувача";
        }
        return null;
    }

    private String formHtmlCode() {

        String result = "";

        String pathToFile = "C:\\Users\\volod\\IdeaProjects\\Boutique_Servlets\\src" +
                "\\main\\resources\\files\\";

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(pathToFile + type + ".txt"), StandardCharsets.UTF_8))) {
            String buf;
            while ((buf = br.readLine()) != null) {
                result = String.format("%s%s\n", result, changeRow(buf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String changeRow(String buffer) {

        if (buffer.contains("user"))
            buffer = buffer.replace("user", recipient);
        else if (buffer.contains("number"))
            buffer = buffer.replace("number", code);
        else if (buffer.contains("href"))
            buffer = buffer.replace("href", "http://localhost:8080/registration/" + person.getActivationCode());
        else if (buffer.contains("firstName"))
            buffer = buffer.replace("firstName", person.getFirstName());
        else if (buffer.contains("surname"))
            buffer = buffer.replace("surname", person.getSurname());
        else if (buffer.contains("lastName"))
            buffer = buffer.replace("lastName", person.getLastName());
        else if (buffer.contains("email"))
            buffer = buffer.replace("email", person.getEmail());
        else if (buffer.contains("phoneNumber"))
            buffer = buffer.replace("phoneNumber", person.getPhoneNumber());
        else if (buffer.contains("comment"))
            buffer = buffer.replace("comment", comment);

        return buffer;
    }

    private String generateCode() {

        byte[] byteArray = new byte[8];

        for( int i = 0 ; i < byteArray.length; i++) byteArray[i] = generateRandomByte();

        for( int i = 1 ; i < byteArray.length; i++) {
            if( byteArray[i-1] == byteArray[i] ) {
                do {
                    byteArray[i-1]= generateRandomByte();
                } while( byteArray[i-1] == byteArray[i] );
            }
        }

        return new String(byteArray);
    }

    // digits 0-9 and all upper- and lowercase letters
    private byte generateRandomByte() {
        byte out_byte = 0;

        int randInt;

        byte[] mapByteArray = {0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,
                0x41,0x42,0x43,0x44,0x45,0x46,0x47,0x48,0x49,0x4a,0x4b,0x4c,0x4d,
                0x4e,0x4f,0x50,0x51,0x52,0x53,0x54,0x55,0x56,0x57,0x58,0x59,0x5a,
                0x61,0x62,0x63,0x64,0x65,0x66,0x67,0x68,0x69,0x6a,0x6b,0x6c,0x6d,
                0x6e,0x6f,0x70,0x71,0x72,0x73,0x74,0x75,0x76,0x77,0x78,0x79,0x7a
        };

        Random random = new Random();

        randInt = random.nextInt(62);
        out_byte = mapByteArray[randInt];

        return out_byte;
    }
}