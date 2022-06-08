package fr.joupi.jhome.common;

import fr.joupi.jhome.utils.file.yaml.AbstractConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class Config extends AbstractConfig {

    public Config(JavaPlugin plugin) {
        super(plugin, "config.yml");
    }

}
