package com.miyozmc.nukkit.autosprint;

import cn.nukkit.plugin.PluginBase;
import com.miyozmc.nukkit.autosprint.Command.Sprint;
import com.miyozmc.nukkit.autosprint.Listener.MoveListener;
import com.miyozmc.nukkit.autosprint.Manager.ConfigManager;
import com.miyozmc.nukkit.autosprint.Manager.SprintManager;
import com.miyozmc.nukkit.autosprint.Utils.MetricsLite;

public class AutoSprint extends PluginBase {

    private ConfigManager configManager;
    private SprintManager sprintManager;

    @Override
    public void onEnable() {
        getLogger().info("AutoSprintPlugin 已启用!");
    }

    @Override
    public void onLoad() {
        this.configManager = new ConfigManager(this);
        this.sprintManager = new SprintManager();
        this.getServer().getCommandMap().register("autosprint", new Sprint(this));
        this.getServer().getPluginManager().registerEvents(new MoveListener(this), this);
        int PluginId = 25897;
        MetricsLite metrics = new MetricsLite(this, PluginId);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public SprintManager getSprintManager() {
        return sprintManager;
    }
}