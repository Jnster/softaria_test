package ru.jnster.javax;

import java.net.URL;
import java.util.List;

public interface IUrlComparator {
    List<URL> changed();
    List<URL> removed();
    List<URL> created();
}
