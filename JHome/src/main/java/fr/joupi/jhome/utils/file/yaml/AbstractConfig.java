package fr.joupi.jhome.utils.file.yaml;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Getter
public abstract class AbstractConfig {

    private final JavaPlugin plugin;

    private final FileConfiguration fileConfiguration;
    private final File file;

    private final String configName;

    public AbstractConfig(JavaPlugin plugin, String configName) {
        this.plugin = plugin;
        this.configName = configName;
        this.file = new File(plugin.getDataFolder(), configName);
        this.fileConfiguration = new YamlConfiguration();

        createConfig();
    }

    private void createConfig() {
        saveDefaultConfig();

        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public void reloadConfig() {
        YamlConfiguration.loadConfiguration(file);
        final Reader reader = new InputStreamReader(getPlugin().getResource(configName), StandardCharsets.UTF_8);
        final YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(reader);

        fileConfiguration.setDefaults(defaultConfig);
        fileConfiguration.options().copyDefaults(true);
    }

    public void saveConfig() {
        try {
            getConfig().save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            getPlugin().saveResource(configName, false);
        }
    }

    public FileConfiguration getConfig() {
        if (fileConfiguration == null)
            reloadConfig();

        return fileConfiguration;
    }

}