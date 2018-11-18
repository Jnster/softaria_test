package ru.jnster.javax;

import java.net.URL;
import java.util.List;

public abstract class AFormatterEmailText {
    public static String formating(List<URL> changed, List<URL> removed, List<URL> created){
        String changedUrl, removedUrl, createdUrl;
        changedUrl = makeString(changed);
        removedUrl = makeString(removed);
        createdUrl = makeString(created);
        return  new StringBuilder().append("Здравствуйте, дорогая и.о. секретаря\n")
                .append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n")
                .append("1. Исчезли следующие страницы:\n")
                .append(removedUrl)
                .append("2. Появились следующие новые страницы: \n")
                .append(createdUrl)
                .append("3. Изменились следующие страницы: \n")
                .append(changedUrl)
                .append("\nС уважением,\nавтоматизированная система\nмониторинга.")
                .toString();
    }

    private static String makeString (List<URL> list){
        StringBuilder builder = new StringBuilder();
        for (int counter = 0; counter <  list.size(); counter++){
            builder.append(counter + 1)
                    .append(") ")
                    .append(list.get(counter).toString())
                    .append("\n");
        }
        return builder.toString();
    }
}
