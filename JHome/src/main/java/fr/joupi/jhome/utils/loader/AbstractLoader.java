package fr.joupi.jhome.utils.loader;

import lombok.Data;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Data
public abstract class AbstractLoader<P extends JavaPlugin> {

    private final P plugin;

    public abstract void load();

    public void unload() {}

    public void registerListeners(Listener... listeners) {
        Arrays.asList(listeners)
                .forEach(this::registerListener);
    }

    private void registerListener(Listener listener) {
        getPlugin().getServer().getPluginManager().registerEvents(listener, getPlugin());
    }

}
