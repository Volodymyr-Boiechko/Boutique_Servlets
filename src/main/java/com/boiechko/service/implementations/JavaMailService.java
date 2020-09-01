package com.boiechko.service.implementations;

import com.boiechko.entity.Order;
import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
import com.boiechko.utils.Mail.JavaMailUtil;

import java.util.List;

public class JavaMailService {

    private static JavaMailUtil javaMailUtil = null;

    public static void sendConfirmRegistrationEmail(final String recipient, final String emailSubject, final Person person) {

        javaMailUtil = new JavaMailUtil(emailSubject, person);
        javaMailUtil.sendMail(recipient);

    }

    public static void sendQuestionFromUserEmail(final String recipient, final String emailSubject, final Person person,
                                                 final String comment) {

        javaMailUtil = new JavaMailUtil(emailSubject, person, comment);
        javaMailUtil.sendMail(recipient);
    }

    public static void sendOrderDetailsEmail(final String recipient, final String emailSubject, final Order order,
                                             final List<Product> shoppingBag) {

        javaMailUtil = new JavaMailUtil(emailSubject, order, shoppingBag);
        javaMailUtil.sendMail(recipient);
    }

    public static String sendRecoveryPasswordEmail(final String recipient, final String emailSubject, final Person person) {

        javaMailUtil = new JavaMailUtil(emailSubject, person);
        javaMailUtil.sendMail(recipient);

        return javaMailUtil.getVerificationCode();

    }

}

