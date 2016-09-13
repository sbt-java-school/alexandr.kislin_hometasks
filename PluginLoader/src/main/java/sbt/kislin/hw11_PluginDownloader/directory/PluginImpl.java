package sbt.kislin.hw11_PluginDownloader.directory;

import sbt.kislin.hw11_PluginDownloader.Plugin;

/**
 * Created by axel on 13.09.2016.
 */
public class PluginImpl implements Plugin {
    public PluginImpl() {
    }
    @Override
    public void doUsefull() {
        System.out.println("doing method");
    }
}
