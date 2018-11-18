package ru.jnster.SpringFramework.logic;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *class for comparing maps, where the key is Java.net.URL
 *
 * @author Anton Klimov (Jnster@yandex.ru)
 */
public class SiteMapComparator implements IUrlComparator {
    private Map<URL,String> mFirst;
    private Map<URL,String> mSecond;

    public SiteMapComparator(Map<URL, String> first, Map<URL, String> second) {
        this.mFirst = first;
        this.mSecond = second;
    }


    public List<URL> changed() {
        List<URL> result = new ArrayList<URL>();
        for(URL urlFirst: mFirst.keySet()){
            for(URL urlSecond: mSecond.keySet()){
                if ((urlFirst.equals(urlSecond)) && !(mFirst.get(urlFirst).equals(mSecond.get(urlSecond)))){
                    result.add(urlFirst);
                }
            }
        }
        return  result;
    }

    public List<URL> removed(){
        List<URL> result = new ArrayList<URL>();
        boolean isFounded = false;
        for(URL urlFirst: mFirst.keySet()){
            for(URL urlSecond: mSecond.keySet()){
                isFounded |= urlFirst.equals(urlSecond);
            }
            if (!isFounded){
                result.add(urlFirst);
                isFounded = false;
            }
        }
        return result;
    }

    public List<URL> created(){
        List<URL> result = new ArrayList<URL>();
        boolean isFounded = false;
        for(URL urlSecond: mSecond.keySet()){
            for(URL urlFirst: mFirst.keySet()){
                isFounded |= urlFirst.equals(urlSecond);
            }
            if (!isFounded){
                result.add(urlSecond);
                isFounded = false;
            }
        }
        return result;
    }
}
