package ru.jnster.SpringFramework.Controllers;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jnster.SpringFramework.config.MailConfig;
import ru.jnster.SpringFramework.logic.AFormatterEmailText;
import ru.jnster.SpringFramework.logic.DebugValues;
import ru.jnster.SpringFramework.logic.SiteMapComparator;

@RestController
public class SimpleEmailController {


    public JavaMailSender emailSender;

    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(@RequestParam String username, @RequestParam String password, @RequestParam String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        DebugValues debugValues = new DebugValues();
        SiteMapComparator siteMapComparator = new SiteMapComparator(debugValues.getFirst(),debugValues.getSecond());
        emailSender = MailConfig.getSslYandexSender(username, password);

        message.setTo(to);
        message.setFrom(username);
        message.setSubject("Результат Климова Антона");
        message.setText(AFormatterEmailText.formating(
                siteMapComparator.changed(),
                siteMapComparator.removed(),
                siteMapComparator.created()));

        this.emailSender.send(message);

        return "Email Sent!";
    }
}
