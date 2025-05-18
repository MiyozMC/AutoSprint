package com.miyozmc.nukkit.autosprint.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.Player;
import com.miyozmc.nukkit.autosprint.AutoSprint;
import com.miyozmc.nukkit.autosprint.Manager.SprintManager;

public class Sprint extends Command {

    private final AutoSprint plugin;

    public Sprint(AutoSprint plugin) {
        super("sprint", "切换自动疾跑状态");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("只有玩家可以使用此命令!");
            return true;
        }

        Player player = (Player) sender;
        SprintManager sprintManager = plugin.getSprintManager();
        String uuid = player.getUniqueId().toString();

        if (sprintManager.isAutoSprinting(uuid)) {
            sprintManager.removeAutoSprintPlayer(uuid);
            player.sendMessage("§a自动疾跑已禁用!");
        } else {
            sprintManager.addAutoSprintPlayer(uuid);
            player.sendMessage("§a自动疾跑已启用!");
        }
        return true;
    }
}