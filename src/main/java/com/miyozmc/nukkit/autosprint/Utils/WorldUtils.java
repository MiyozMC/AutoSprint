package com.miyozmc.nukkit.autosprint.Utils;

import java.util.List;

public class WorldUtils {

    public static boolean isWorldAllowed(String worldName, List<String> blackListWorlds, List<String> whiteListWorlds) {
        if (!whiteListWorlds.isEmpty()) {
            return whiteListWorlds.contains(worldName);
        }
        return !blackListWorlds.contains(worldName);
    }
}