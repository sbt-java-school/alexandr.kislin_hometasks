package sbt.kislin.hw11_PluginDownloader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by axel on 13.09.2016.
 */
public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }
    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoaderForPlugin loader = new ClassLoaderForPlugin(new URL[]{new URL(pluginRootDirectory+pluginName+ "/")});
        return (Plugin) loader.loadClass(pluginClassName).newInstance();
    }
}
