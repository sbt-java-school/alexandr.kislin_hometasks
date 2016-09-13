package sbt.kislin.hw11_PluginDownloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * Created by axel on 13.09.2016.
 */
public class ClassLoaderForPlugin extends URLClassLoader {

    public ClassLoaderForPlugin(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public ClassLoaderForPlugin(URL[] urls) {
        super(urls);
    }

    public ClassLoaderForPlugin(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try
        {
            return findClass(name);
        }
        catch (ClassNotFoundException e) {
            e.getMessage();
            return super.loadClass(name);
        }
    }
}
