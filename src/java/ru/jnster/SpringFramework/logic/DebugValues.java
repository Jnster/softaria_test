package ru.jnster.SpringFramework.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DebugValues {
    private Map<URL, String> mFirst;
    private Map<URL, String> mSecond;

    public DebugValues(){
        mFirst = new HashMap<>();
        mSecond = new HashMap<>();
        String html1 = "html1", html2 = "html2", html3 = "html3";
        try {
            URL changed = new URL("http", "changed.ru", 80, "/index.html"),
                    removed = new URL("http", "removed.ru", 80, "/index.html"),
                    created = new URL("http", "created.ru", 80, "/index.html"),
                    someOne = new URL("https", "some.ru", 80, "/index.html");
            mFirst.put(changed, html1);
            mSecond.put(changed, html2);
            mFirst.put(removed, html3);
            mSecond.put(created, html1);
            mFirst.put(someOne, html1);
            mSecond.put(someOne, html1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Map<URL, String> getSecond(){
        return mSecond;
    }

    public Map<URL, String> getFirst(){
        return mFirst;
    }
}
