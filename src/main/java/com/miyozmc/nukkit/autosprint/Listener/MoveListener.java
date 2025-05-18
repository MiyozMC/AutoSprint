package com.miyozmc.nukkit.autosprint.Listener;

import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import com.miyozmc.nukkit.autosprint.AutoSprint;
import com.miyozmc.nukkit.autosprint.Manager.SprintManager;
import com.miyozmc.nukkit.autosprint.Utils.WorldUtils;
import com.miyozmc.nukkit.autosprint.Manager.ConfigManager;

public class MoveListener implements Listener {

    private final AutoSprint plugin;

    public MoveListener(AutoSprint plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getLevel().getName();
        SprintManager sprintManager = plugin.getSprintManager();
        ConfigManager configManager = plugin.getConfigManager();

        // 检查世界是否允许疾跑
        if (!WorldUtils.isWorldAllowed(worldName,
                configManager.getBlackListWorlds(),
                configManager.getWhiteListWorlds())) {
            return;
        }

        // 检查玩家是否启用自动疾跑
        if (sprintManager.isAutoSprinting(player.getUniqueId().toString())) {
            if (!player.isSprinting()) {
                player.setSprinting(true);
            }
        }
    }
}