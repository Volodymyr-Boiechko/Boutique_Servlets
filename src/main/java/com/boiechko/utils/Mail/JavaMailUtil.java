package com.boiechko.utils.Mail;

import com.boiechko.entity.Address;
import com.boiechko.entity.Order;
import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.AddressService;
import com.boiechko.service.interfaces.PersonService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

public class JavaMailUtil {

    private final static String myAccountEmail = "boiechko.work@gmail.com";
    private final static String password = "28171821";

    private final String emailSubject;
    private String verificationCode;
    private Person person;
    private String comment;

    private Order order = new Order();
    private List<Product> products;

    public String getVerificationCode() { return verificationCode; }

    public JavaMailUtil(String emailSubject, Person person) {
        this.emailSubject = emailSubject;
        this.person = person;
    }

    public JavaMailUtil(String emailSubject, Person person, String comment) {
        this.emailSubject = emailSubject;
        this.person = person;
        this.comment = comment;
    }

    public JavaMailUtil(String emailSubject, Order order, List<Product> products) {
        this.emailSubject = emailSubject;
        this.order = order;
        this.products = products;
    }

    private String getSubject() {

        switch (emailSubject) {
            case "confirmRegistration":
                return "Активація акаунту";
            case "recoverPassword":
                return "Відновлення паролю";
            case "questionFromUser":
                return "Запитання від користувача";
            case "orderDetail":
                return "Інформація про замовлення";
        }
        return null;
    }

    public void sendMail(final String recipient) {

        try {

            Properties properties = new Properties();
            properties.load(JavaMailUtil.class.getClassLoader().getResourceAsStream("mail.properties"));

            Session session = Session.getInstance(properties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });

            final Message message = prepareMessage(session, recipient);

            assert message != null;
            Transport.send(message);

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }

    private Message prepareMessage(final Session session, final String recipient) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(getSubject());

            String pathToFile = "C:\\Users\\volod\\IdeaProjects\\Boutique_Servlets\\src\\main\\resources\\files\\" +
                    emailSubject + ".txt";

            String htmlCode = getHtmlCode(pathToFile);

            if (emailSubject.contains("orderDetail")) message.setContent(getHtmlCodeWithImages(htmlCode));
            else message.setContent(replaceMarkersFromHtml(htmlCode), "text/html;charset=UTF-8");

            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getHtmlCode(final String pathToFile) {

        String result = "";

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(pathToFile), StandardCharsets.UTF_8)))
        {
            String buf;
            while ((buf = br.readLine()) != null) result = String.format("%s%s\n", result, buf);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return result;
    }

    private String addProducts(final String buf) {

        StringBuilder result = new StringBuilder();
        String pathToFile = "C:\\Users\\volod\\IdeaProjects\\Boutique_Servlets\\src\\main\\resources\\files\\productBlock.txt";

        if (buf.contains("<div class=\"productsWrapper\">")) {

            result.append("<div class=\"productsWrapper\">\n");

            String productBlock = getHtmlCode(pathToFile);

            for (Product product : products) {

                String[] searchList = {"${product.image}", "${product.typeName}", "${product.description}", "${product.price}", "${product.quantity}"};
                String[] replaceList = {Integer.toString(product.getIdProduct()), product.getTypeName(), product.getDescription(),
                        Integer.toString(product.getPrice()), Integer.toString(product.getQuantity())};

                result.append("\n").append(productBlock);

                for (int i = 0; i < searchList.length; i++)
                    result = new StringBuilder(result.toString().replace(searchList[i], replaceList[i]));

            }

        }

        return buf.replace("<div class=\"productsWrapper\">", result.toString());

    }

    private Multipart getHtmlCodeWithImages(final String htmlCode) {

        Multipart multipart = new MimeMultipart();

        try {
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(replaceMarkersFromHtml(addProducts(htmlCode)), "text/html;charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);

            String path = "C:\\Users\\volod\\IdeaProjects\\Boutique_Servlets\\web\\";

            for (Product product : products) {

                String imagePath = path + product.getImage().replace("/", "\\");;

                MimeBodyPart imagePart = new MimeBodyPart();
                try {
                    imagePart.setHeader("Content-ID", "<" + product.getIdProduct() + ">");
                    imagePart.setDisposition(MimeBodyPart.INLINE);
                    imagePart.attachFile(imagePath);
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }
                multipart.addBodyPart(imagePart);

            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }

        return multipart;

    }

    private String replaceMarkersFromHtml(String htmlText) {

        switch (emailSubject) {

            case "confirmRegistration":
                htmlText = htmlText.replace("user", person.getUsername());
                htmlText = htmlText.replace("href", "http://localhost:8080/registration/" + person.getActivationCode());
                break;
            case "recoverPassword":
                verificationCode = VerificationCode.generateCode();
                htmlText = htmlText.replace("user", person.getUsername());
                htmlText = htmlText.replace("number", verificationCode);
                break;
            case "questionFromUser": {

                String[] searchList = {"firstName", "surname", "lastName", "email", "phoneNumber", "comment"};
                String[] replaceList = {person.getFirstName(), person.getSurname(), person.getLastName(),
                        person.getEmail(), person.getPhoneNumber(), comment};

                for (int i = 0; i < searchList.length; i++) htmlText = htmlText.replace(searchList[i], replaceList[i]);
                break;
            }
            case "orderDetail": {

                PersonService personService = new PersonServiceImpl();
                AddressService addressService = new AddressServiceImpl();

                Person person = personService.getPersonById(order.getIdPerson());
                Address address = addressService.getAddressById(order.getIdAddress());

                String[] searchList = {"${username}", "${order.idOrder}", "${order.timeOrder}", "${products.size()} ${nameOfProduct}",
                        "${order.totalPrice}", "${person.firstName} ", "${person.surname}", "${address.street}", "${address.city}", "${address.country}", "${address.postCode}",
                        "${person.phoneNumber}", "${deliveryDate}"
                };
                String[] replaceList = {person.getUsername(), Integer.toString(order.getIdOrder()), order.getTimeOrder().toString(),
                        getTitleOfProducts(products.size()), Integer.toString(order.getTotalPrice()), person.getFirstName() + "\t", person.getSurname(),
                        address.getStreet(), address.getCity(), address.getCountry(), address.getPostCode(), person.getPhoneNumber(),
                        order.getTimeOrder().toLocalDate().plusDays(21).toString()

                };

                for (int i = 0; i < searchList.length; i++) htmlText = htmlText.replace(searchList[i], replaceList[i]);
                break;
            }
        }

        return htmlText;
    }

    private String getTitleOfProducts(final int size) {

        if (size == 1) {
            return size + " товар";
        } else if (size > 1 && size < 5)
            return size + " товари";
        else
            return size + " товарів";

    }
}