package com.miyozmc.nukkit.autosprint.Manager;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import java.util.List;

public class ConfigManager {

    private final PluginBase plugin;
    private List<String> blackListWorlds;
    private List<String> whiteListWorlds;
    private Config config;

    public ConfigManager(PluginBase plugin) {
        this.plugin = plugin;
        this.plugin.saveDefaultConfig();
        this.config = this.plugin.getConfig();
        reloadConfig();
    }

    public void reloadConfig() {
        blackListWorlds = config.getStringList("BlackListWorld");
        whiteListWorlds = config.getStringList("WhiteListWorld");
    }

    public List<String> getBlackListWorlds() {
        return blackListWorlds;
    }

    public List<String> getWhiteListWorlds() {
        return whiteListWorlds;
    }

    public void saveConfig() {
        config.set("BlackListWorld", blackListWorlds);
        config.set("WhiteListWorld", whiteListWorlds);
        config.save();
        plugin.saveConfig();
    }
}