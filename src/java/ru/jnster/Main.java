package ru.jnster;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        String textEmail, addresser, addressee, host, password, subjectEmail = "Результат Климова Антона.";
        SiteMapComparator siteMapComparator;
        EmailSender emailSender;
        Map<URL, String> yesterday = new HashMap<>(), today = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        //Подготовка данных для отладки.
        String html1 = "html1", html2 = "html2", html3 = "html3";
        try {
            URL changed = new URL("http", "changed.ru",80,"/index.html"),
                    removed = new URL("http", "removed.ru",80,"/index.html"),
                    created = new URL("http", "created.ru",80,"/index.html"),
                    someOne = new URL("https", "some.ru",80,"/index.html");
            yesterday.put(changed,html1);
            today.put(changed, html2);
            yesterday.put(removed, html3);
            today.put(created,html1);
            yesterday.put(someOne, html1);
            today.put(someOne,html1);

            //Выполнение задания
            siteMapComparator = new SiteMapComparator(yesterday,today);
            textEmail = FormatterEmailText.formating(siteMapComparator.changed(),siteMapComparator.removed(),siteMapComparator.created());

            System.out.print("Введите адрес электронной почты отправителя: ");
            addresser = scanner.next();
            System.out.print("Пароль электронной почты: ");
            password = scanner.next();
            System.out.print("Введите адрес электронной почты получателя: ");
            addressee = scanner.next();
            System.out.print("Введите адрес SMTP-сервер отправителя: ");
            host =scanner.next();

            emailSender = new EmailSender(addresser, addressee, host, password,465);
            emailSender.send(subjectEmail, textEmail);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
