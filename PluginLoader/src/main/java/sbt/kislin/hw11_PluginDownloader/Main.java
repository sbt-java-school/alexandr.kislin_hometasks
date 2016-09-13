package sbt.kislin.hw11_PluginDownloader;

import java.net.MalformedURLException;

/**
 * Created by axel on 13.09.2016.
 */
public class Main {
    private static final String PLUGIN_PATH = "file:///plugin/";
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        new sbt.kislin.hw11_PluginDownloader.directory.PluginImpl().doUsefull();
        PluginManager manager = new PluginManager(PLUGIN_PATH);
        Plugin loadedPlugin = manager.load("PluginImpl","sbt.kislin.hw11_PluginDownloader.directory.PluginImpl");
        loadedPlugin.doUsefull();
    }
}
