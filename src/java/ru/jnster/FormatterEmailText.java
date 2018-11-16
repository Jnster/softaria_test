package ru.jnster;

import java.net.URL;
import java.util.List;

public  class FormatterEmailText {
    public static String formating(List<URL> changed, List<URL> removed, List<URL> created){
        String changedUrl = "", removedUrl = "", createdUrl = "";
        for (int counter = 1; counter <=  changed.size(); counter++){
            changedUrl += counter + ") " + changed.get(counter - 1).toString() + "\n";
        }

        for (int counter = 1; counter <= removed.size(); counter++){
            removedUrl += counter + ") " + removed.get(counter - 1).toString() + "\n";
        }

        for (int counter = 1; counter <= created.size(); counter++){
            createdUrl += counter + ") " + created.get(counter - 1).toString() + "\n";
        }
        return  "Здравствуйте, дорогая и.о. секретаря\n" +
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n" +
                "\n" +
                "1. Исчезли следующие страницы:\n" + removedUrl +
                "2. Появились следующие новые страницы: \n" + createdUrl +
                "3. Изменились следующие страницы: \n" + changedUrl +
                "\n" +
                "С уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.";

    }
}
