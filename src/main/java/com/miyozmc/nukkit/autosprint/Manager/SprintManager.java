package com.miyozmc.nukkit.autosprint.Manager;

import java.util.HashSet;
import java.util.Set;

public class SprintManager {

    private final Set<String> autoSprintPlayers = new HashSet<>();

    public void addAutoSprintPlayer(String uuid) {
        autoSprintPlayers.add(uuid);
    }

    public void removeAutoSprintPlayer(String uuid) {
        autoSprintPlayers.remove(uuid);
    }

    public boolean isAutoSprinting(String uuid) {
        return autoSprintPlayers.contains(uuid);
    }

    public Set<String> getAutoSprintPlayers() {
        return autoSprintPlayers;
    }
}