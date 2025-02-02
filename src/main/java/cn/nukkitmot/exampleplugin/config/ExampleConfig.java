package cn.nukkitmot.exampleplugin.config;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkitmot.exampleplugin.ExamplePlugin;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class ExampleConfig {
    private final Config config;

    @Getter
    private String aKey;
    @Getter
    private boolean anotherKey;
    @Getter
    private final KeyObject objectKey;
    @Getter
    private ArrayList<String> arrayKey;

    public ExampleConfig() {
        ExamplePlugin.getInstance().saveResource("config.yml");
        config = new Config(
                new File(ExamplePlugin.getInstance().getDataFolder(), "config.yml"),
                Config.YAML
                //Default values (not necessary)
                ,new ConfigSection(new LinkedHashMap<>() {{
                    put("this-is-a-key", "Hello! Config!");
                    put("another-key", true); //you can also put other standard objects!
                    put("object-key", new LinkedHashMap<String, Object>() {{
                        put("enabled", false);
                        put("subKey1", "nukkit");
                        put("subKey2", 2023);
                    }});
                    put("array-key", Arrays.asList(
                            "first element",
                            "second element",
                            "third element"
                    ));
                }})
        );

        setAKey(config.getString("this-is-a-key", "this-is-default-value"));
        setAnotherKey(config.getBoolean("another-key"));
        objectKey = new KeyObject(this);
        setArrayKey((ArrayList<String>) config.getStringList("array-key"));
    }

    public ExampleConfig setAKey(String value) {
        this.aKey = value;
        return this;
    }

    public ExampleConfig setAnotherKey(boolean value) {
        this.anotherKey = value;
        return this;
    }

    public ExampleConfig setArrayKey(ArrayList<String> value) {
        this.arrayKey = value;
        return this;
    }

    public void save() {
        if (config == null) return;
        config.set("this-is-a-key", aKey);
        config.set("another-key", anotherKey);
        config.set("array-key", arrayKey);
        config.save();
    }

    public class KeyObject {
        private final ConfigSection configSection;
        @Getter
        private final ExampleConfig parent;
        @Getter
        private boolean enabled;
        @Getter
        private String subKey1;
        @Getter
        private Integer subKey2;

        public KeyObject(ExampleConfig parent) {
            this.parent = parent;
            this.configSection = config.getSection("object-key");

            this.enabled = configSection.getBoolean("enable", false);
            this.subKey1 = configSection.getString("subKey2", "nukkit");
            this.subKey2 = configSection.getInt("subKey3", 2023);
        }

        public KeyObject setEnabled(boolean value) {
            enabled = value;
            configSection.set("enabled", enabled);
            return this;
        }

        public KeyObject setSubKey1(String value) {
            subKey1 = value;
            configSection.set("subKey1", subKey1);
            return this;
        }

        public KeyObject setSubKey2(Integer value) {
            subKey2 = value;
            configSection.set("subKey2", subKey2);
            return this;
        }

        public ExampleConfig save() {
            if (config == null) return null;
            configSection.set("enabled", enabled);
            configSection.set("subKey1", subKey1);
            configSection.set("subKey2", subKey2);
            config.save();
            return parent;
        }
    }
}