package sbt.kislin.hw11_PluginDownloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by axel on 13.09.2016.
 */
public class PluginManager {
    private final String pluginRootDirectory;
    private static Logger LOGGER = LogManager.getRootLogger();

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }
    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoaderForPlugin loader = new ClassLoaderForPlugin(new URL[]{new URL(pluginRootDirectory+pluginName+ "/")});
        Plugin plugin = (Plugin) loader.loadClass(pluginClassName).newInstance();
        try {
            loader.close();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        finally {
            try {
                loader.close();
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return plugin;
    }
}
