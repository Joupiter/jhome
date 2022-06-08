package fr.joupi.jhome.utils.manager;

import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

@Data
public abstract class AbstractManager<P extends JavaPlugin> {

    private final P plugin;

}
