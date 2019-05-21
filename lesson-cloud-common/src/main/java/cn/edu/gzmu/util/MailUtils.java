package cn.edu.gzmu.util;

import cn.edu.gzmu.config.MailConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;

/**
 * @author Japoul
 * @version 1.0
 * @date 2019-05-21 14:45
 */
@Component
@Async
@Slf4j
public class MailUtils {

    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;
    private final TemplateEngine templateEngine;

    @Autowired(required = false)
    public MailUtils(JavaMailSender javaMailSender, MailConfig mailConfig, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.mailConfig = mailConfig;
        this.templateEngine = templateEngine;
    }

    /**
     * 简单文字邮件发送
     * @param toEmail 接收者邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String toEmail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailConfig.getUsername());
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    /**
     * 发送带模板的邮件
     * @param toEmail 接收者邮箱
     * @param type 邮件类型
     * @param subject 邮件主题
     * @param template 邮件模板名称(默认资源路径下)
     * @param values 模板内变量集合
     */
    public Boolean sendTemplateMail(String toEmail, String type, String subject, String template, HashMap values) {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        values.forEach((k,v)->context.setVariable((String) k, v));;
        String content = templateEngine.process(template, context);
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(mailConfig.getUsername());
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            log.info("向" + toEmail + type + "邮件发送成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            log.warn("向" + toEmail + type + "邮件发送失败");
            return false;
        }
    }
}

