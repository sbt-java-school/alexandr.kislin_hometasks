package sbt.kislin.hw11_PluginDownloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * Created by axel on 13.09.2016.
 */
public class ClassLoaderForPlugin extends URLClassLoader {

    private static Logger LOGGER = LogManager.getRootLogger();
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
            LOGGER.info(e);
            return super.loadClass(name);
        }
    }
}
